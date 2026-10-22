package top.niunaijun.blackbox.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import org.json.JSONObject;

import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.NativeCore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Applies a per-app device fingerprint inside the guest process.
 *
 * <p>The configuration is read by the server in {@code ProcessRecord} and
 * handed to the guest through {@link top.niunaijun.blackbox.entity.AppConfig},
 * then applied before {@code Application.onCreate} runs so sdks create their
 * payload with the overridden values.</p>
 *
 * <p>The profile is the <b>whole</b> json object submitted for the package, not a
 * whitelist: a device档案 produced by the emu generator (brand / model / osVersion /
 * buildName / displayWidth / androidId / imei1 / mcc / mnc / timezone / cpuAbi …)
 * is accepted as-is. Long names from that generator and the short names used by
 * the settings screen are both understood.</p>
 */
public final class DeviceInfoManager {

    private static final String TAG = "DeviceInfoManager";

    /** Must match the constant used by the host UI. */
    public static final String CONFIG_FILE = "app_device.json";

    private static volatile JSONObject sProfile = new JSONObject();
    private static volatile boolean sHasProfile;
    private static volatile boolean sHasLocale;
    private static volatile boolean sHasTimeZone;
    private static volatile boolean sEmulatorProfile;
    private static volatile String sProcCpuInfoPath;
    private static volatile String sProcMemInfoPath;

    private static volatile String sBrand;
    private static volatile String sModel;
    private static volatile String sManufacturer;
    private static volatile String sDevice;
    private static volatile String sProduct;
    private static volatile int sWidth;
    private static volatile int sHeight;
    private static volatile int sDpi;

    private DeviceInfoManager() {
    }

    /** Runs in the server process. */
    public static String readStoredConfig(File hostFilesDir, String packageName) {
        if (hostFilesDir == null || packageName == null) {
            return null;
        }
        File file = new File(hostFilesDir, CONFIG_FILE);
        if (!file.exists()) {
            return null;
        }
        try {
            String raw = readText(file);
            if (raw == null || raw.isEmpty()) {
                return null;
            }
            return new JSONObject(raw).optString(packageName, null);
        } catch (Throwable t) {
            Slog.w(TAG, "parse device config file failed", t);
            return null;
        }
    }

    /** Runs inside the guest process, before Application.onCreate. */
    public static void setConfig(String raw) {
        sProfile = new JSONObject();
        sHasProfile = false;
        sHasLocale = false;
        sHasTimeZone = false;
        sBrand = null;
        sModel = null;
        sManufacturer = null;
        sDevice = null;
        sProduct = null;
        sWidth = 0;
        sHeight = 0;
        sDpi = 0;
        if (raw == null || raw.isEmpty()) {
            return;
        }
        try {
            JSONObject json = new JSONObject(raw);
            sProfile = json;
            sHasProfile = json.length() > 0;

            sBrand = text("brand");
            sModel = text("model");
            sManufacturer = text("manufacturer");
            // emu 的 hardwareName：真机档案里是代号（a05s / husky），模拟器档案里是
            // "<build> dev-keys"，所以只有真机档案才把它当成 Build.DEVICE/PRODUCT。
            sEmulatorProfile = json.optBoolean("isEmulator", false);
            sDevice = firstNonNull(text("device"),
                    sEmulatorProfile ? text("name", "model") : text("hardwareName", "name", "model"));
            sProduct = firstNonNull(text("product"),
                    sEmulatorProfile ? text("name", "model") : text("hardwareName", "name", "model"));
            sWidth = number("width", "displayWidth");
            sHeight = number("height", "displayHeight");
            sDpi = number("dpi", "screenDensityNumber");

            applyToBuild();
            applyToFingerprint();
            applyToVersion();
            applyToAbis();
            applyToLocale();
            applyToTimeZone();
            applyToSystemProperties();
            prepareProcFiles();
            applyToNative();
            Slog.d(TAG, "device configured: " + Build.MANUFACTURER + "/" + Build.MODEL
                    + " " + sWidth + "x" + sHeight + "@" + sDpi);
        } catch (Throwable t) {
            Slog.w(TAG, "parse device config failed", t);
        }
    }

    // ==================== Build / 系统常量 ====================

    private static void applyToBuild() {
        setStaticField("BRAND", sBrand);
        setStaticField("MODEL", sModel);
        setStaticField("MANUFACTURER", sManufacturer);
        setStaticField("DEVICE", sDevice);
        setStaticField("PRODUCT", sProduct);

        // 档案里额外给出的机型常量（emu 档案没有这些键时保持真机值）
        setStaticField("BOARD", text("board"));
        setStaticField("HARDWARE", firstNonNull(text("hardware"),
                sEmulatorProfile ? null : text("hardwareName")));
        setStaticField("BOOTLOADER", text("bootloader"));
        setStaticField("SERIAL", text("serial"));
        setStaticField("SOC_MANUFACTURER", text("socManufacturer"));
        setStaticField("SOC_MODEL", text("socModel"));

        // Build.ID / Build.DISPLAY：emu 的 buildName（AOSP 形态）与 buildDisplayId
        String buildId = text("buildName", "id", "buildId");
        setStaticField("ID", buildId);
        setStaticField("DISPLAY", text("buildDisplayId", "display", "hardwareName", "buildName"));
        setStaticField("TAGS", text("tags", "buildTags"));
        setStaticField("TYPE", text("buildType", "type"));
    }

    /**
     * Rebuilds {@link Build#FINGERPRINT} from the overridden fields.
     *
     * <p>Keeping the real fingerprint next to a spoofed model is a trivially
     * detectable mismatch, so the canonical
     * {@code brand/product/device:release/id/incremental:type/tags} form is
     * derived from the configured values.</p>
     */
    private static void applyToFingerprint() {
        String explicit = text("fingerprint");
        if (explicit != null) {
            setStaticField("FINGERPRINT", explicit);
            return;
        }
        if (sBrand == null && sProduct == null && sDevice == null) {
            return;
        }
        try {
            String brand = sBrand != null ? sBrand : Build.BRAND;
            String product = sProduct != null ? sProduct : Build.PRODUCT;
            String device = sDevice != null ? sDevice : Build.DEVICE;
            String fingerprint = brand + "/" + product + "/" + device + ":"
                    + Build.VERSION.RELEASE + "/" + Build.ID + "/" + Build.VERSION.INCREMENTAL
                    + ":" + Build.TYPE + "/" + Build.TAGS;
            setStaticField("FINGERPRINT", fingerprint);
        } catch (Throwable t) {
            Slog.w(TAG, "unable to override Build.FINGERPRINT: " + t.getMessage());
        }
    }

    /** Build.VERSION 类里的系统版本常量。 */
    private static void applyToVersion() {
        setStaticField("android.os.Build$VERSION", "RELEASE", text("osVersion", "release"));
        setStaticField("android.os.Build$VERSION", "INCREMENTAL", text("incremental"));
        setStaticField("android.os.Build$VERSION", "SECURITY_PATCH", text("securityPatch"));

        int sdk = number("apiLevel", "sdkInt", "sdk");
        if (sdk > 0) {
            setStaticIntField("android.os.Build$VERSION", "SDK_INT", sdk);
        }
    }

    /** Build.SUPPORTED_ABIS / CPU_ABI 等：emu 的 cpuAbi / cpuAbi2。 */
    private static void applyToAbis() {
        String abi = text("cpuAbi", "abi");
        String abi2 = text("cpuAbi2");
        if (abi == null) {
            return;
        }
        List<String> abis = new ArrayList<>();
        abis.add(abi);
        if (abi2 != null && !abi2.equals(abi)) {
            abis.add(abi2);
        }
        String[] array = abis.toArray(new String[0]);
        setStaticObjectField("SUPPORTED_ABIS", array);
        setStaticField("CPU_ABI", abi);
        if (abi2 != null) {
            setStaticField("CPU_ABI2", abi2);
        }
        // 32 / 64 位列表按真实位数拆（emu 只给主 ABI 时也要能对上）
        List<String> abi32 = new ArrayList<>();
        List<String> abi64 = new ArrayList<>();
        for (String item : abis) {
            if (item.endsWith("64")) {
                abi64.add(item);
            } else {
                abi32.add(item);
            }
        }
        setStaticObjectField("SUPPORTED_32_BIT_ABIS", abi32.toArray(new String[0]));
        setStaticObjectField("SUPPORTED_64_BIT_ABIS", abi64.toArray(new String[0]));
    }

    private static void applyToLocale() {
        String language = text("language");
        String country = text("countryCode", "country");
        String localeTag = text("locale");
        if (language == null && localeTag == null) {
            return;
        }
        try {
            String[] parts = splitLocale(localeTag, language, country);
            Locale locale = new Locale(parts[0], parts[1]);
            Locale.setDefault(locale);
            sHasLocale = true;
        } catch (Throwable t) {
            Slog.w(TAG, "unable to override locale: " + t.getMessage());
        }
    }

    private static void applyToTimeZone() {
        String tz = text("timezone", "timeZone");
        if (tz == null) {
            return;
        }
        try {
            TimeZone.setDefault(TimeZone.getTimeZone(tz));
            sHasTimeZone = true;
        } catch (Throwable t) {
            Slog.w(TAG, "unable to override timezone: " + t.getMessage());
        }
    }

    /**
     * 运行时属性：emu 的 {@code arch}（缺省时由 cpuAbi 推导）写到 {@code os.arch}，
     * 大量风控 SDK 通过 System.getProperty("os.arch") 判断 CPU 架构。
     */
    private static void applyToSystemProperties() {
        String arch = text("arch");
        if (arch == null) {
            arch = archOf(text("cpuAbi", "abi"));
        }
        if (arch == null) {
            return;
        }
        try {
            System.setProperty("os.arch", arch);
        } catch (Throwable t) {
            Slog.w(TAG, "unable to override os.arch: " + t.getMessage());
        }
    }

    private static String archOf(String abi) {
        if (abi == null) {
            return null;
        }
        switch (abi) {
            case "arm64-v8a":
                return "aarch64";
            case "armeabi-v7a":
            case "armeabi":
                return "armv7l";
            case "x86_64":
                return "amd64";
            case "x86":
                return "i386";
            default:
                return null;
        }
    }

    /**
     * 把 {@code cpuDetails} / 内存数值写成真实的临时文件，供 {@link #procRedirect} 重定向。
     *
     * <p>/proc/cpuinfo、/proc/meminfo 是"文件内容"型指纹，Java 侧没有对应 API，
     * 但 libcore 的所有文件打开都会经过 {@code Libcore.os.open}（OsStub 已代理），
     * 在那里把路径换成这个文件即可，无需 inline hook。</p>
     */
    private static void prepareProcFiles() {
        try {
            Context context = BlackBoxCore.getContext();
            if (context == null) {
                return;
            }
            File dir = new File(context.getCacheDir(), "proc_spoof");
            if (!dir.exists() && !dir.mkdirs()) {
                return;
            }
            String cpuInfo = text("cpuDetails");
            if (cpuInfo != null) {
                sProcCpuInfoPath = writeText(new File(dir, "cpuinfo"), cpuInfo);
            }
            long total = longNumber("totalMemory");
            if (total > 0) {
                sProcMemInfoPath = writeText(new File(dir, "meminfo"),
                        buildMemInfo(total, longNumber("availableMemory")));
            }
        } catch (Throwable t) {
            Slog.w(TAG, "prepare proc spoof files failed: " + t.getMessage());
        }
    }

    /**
     * 把 /proc 伪文件路径与开机时间交给 native 层（Dobby hook 的 open/openat、
     * clock_gettime），覆盖 C/C++ 代码直接读文件、读 SystemClock 的场景。
     */
    private static void applyToNative() {
        try {
            NativeCore.setProcPaths(sProcCpuInfoPath, sProcMemInfoPath);
        } catch (Throwable t) {
            Slog.w(TAG, "native setProcPaths unavailable: " + t.getMessage());
        }
        try {
            long bootTime = bootTimeMillis();
            if (bootTime > 0) {
                NativeCore.setBootTime(bootTime);
            }
        } catch (Throwable t) {
            Slog.w(TAG, "native setBootTime unavailable: " + t.getMessage());
        }
        // 延后到首帧之后安装：JniHook 的 RegisterNatives 在启动早期执行会影响
        // 该进程的渲染（窗口全黑），推迟到 UI 起来之后就不影响首屏。
        final String renderer = text("gpuRenderer", "glRenderer");
        final String vendor = text("gpuVendor", "glVendor");
        if (renderer != null || vendor != null) {
            try {
                new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(
                        () -> {
                            try {
                                NativeCore.setGpuInfo(renderer, vendor);
                                // 自检：直接走 Java API 读一次，确认 hook 生效
                                Slog.d(TAG, "gpu self-check renderer="
                                        + android.opengl.GLES20.glGetString(0x1F01)
                                        + ", vendor="
                                        + android.opengl.GLES20.glGetString(0x1F00));
                            } catch (Throwable t) {
                                Slog.w(TAG, "native setGpuInfo unavailable: " + t.getMessage());
                            }
                        }, 4000);
            } catch (Throwable t) {
                Slog.w(TAG, "schedule setGpuInfo failed: " + t.getMessage());
            }
        }
    }

    /** emu 的 bootTime（2026-10-19T11:34:12.054Z / ISO 8601）转 epoch 毫秒。 */
    private static long bootTimeMillis() {
        String value = text("bootTime");
        if (value == null) {
            return 0L;
        }
        String[] patterns = {"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss'Z'",
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "yyyy-MM-dd'T'HH:mm:ss"};
        for (String pattern : patterns) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
                if (pattern.endsWith("'Z'")) {
                    format.setTimeZone(TimeZone.getTimeZone("UTC"));
                }
                Date date = format.parse(value);
                if (date != null) {
                    return date.getTime();
                }
            } catch (Throwable ignored) {
            }
        }
        return 0L;
    }

    private static String writeText(File file, String content) {
        try (FileOutputStream out = new FileOutputStream(file, false)) {
            out.write(content.getBytes("UTF-8"));
            return file.getAbsolutePath();
        } catch (IOException e) {
            Slog.w(TAG, "write " + file.getName() + " failed: " + e.getMessage());
            return null;
        }
    }

    private static String buildMemInfo(long totalBytes, long availableBytes) {
        long total = totalBytes / 1024;
        long available = (availableBytes > 0 ? availableBytes : totalBytes / 3) / 1024;
        long free = Math.max(available / 3, 1024);
        long cached = Math.max(available - free, 1024);
        long buffers = Math.max(available / 20, 256);
        return "MemTotal:       " + total + " kB\n"
                + "MemFree:        " + free + " kB\n"
                + "MemAvailable:   " + available + " kB\n"
                + "Buffers:        " + buffers + " kB\n"
                + "Cached:         " + cached + " kB\n"
                + "SwapCached:     0 kB\n"
                + "SwapTotal:      0 kB\n"
                + "SwapFree:       0 kB\n";
    }

    /**
     * /proc 伪文件重定向：返回可读的替代路径，未配置该字段时返回 null。
     * 由 {@code OsStub} 在 {@code Libcore.os.open/stat/...} 调用前替换路径。
     */
    public static String procRedirect(String path) {
        if (path == null) {
            return null;
        }
        if ("/proc/cpuinfo".equals(path)) {
            return sProcCpuInfoPath;
        }
        if ("/proc/meminfo".equals(path)) {
            return sProcMemInfoPath;
        }
        return null;
    }

    // ==================== 展示 / 分辨率 ====================

    /** Applies the configured resolution / dpi to the given resources. */
    public static void applyToMetrics(Resources resources) {
        if (resources == null) {
            return;
        }
        try {
            if (sHasLocale) {
                Configuration configuration = resources.getConfiguration();
                if (configuration != null) {
                    String[] parts = splitLocale(text("locale"), text("language"),
                            text("countryCode", "country"));
                    configuration.setLocale(new Locale(parts[0], parts[1]));
                }
            }
            if (sWidth <= 0 && sHeight <= 0 && sDpi <= 0) {
                return;
            }
            DisplayMetrics metrics = resources.getDisplayMetrics();
            if (metrics == null) {
                return;
            }
            if (sDpi > 0) {
                metrics.densityDpi = sDpi;
                metrics.xdpi = sDpi;
                metrics.ydpi = sDpi;
                metrics.density = sDpi / 160f;
                metrics.scaledDensity = metrics.density;
            }
            if (sWidth > 0) {
                metrics.widthPixels = sWidth;
            }
            if (sHeight > 0) {
                metrics.heightPixels = sHeight;
            }
            applyToConfiguration(resources, metrics);
        } catch (Throwable t) {
            Slog.w(TAG, "apply display metrics failed", t);
        }
    }

    /**
     * Keeps {@link android.content.res.Configuration} in sync with the metrics.
     *
     * <p>Many sdks read {@code resources.configuration.screenWidthDp} (or the
     * smallest screen width) instead of the metrics, so overriding the metrics
     * alone leaves a real-device screen size visible.</p>
     */
    private static void applyToConfiguration(Resources resources, DisplayMetrics metrics) {
        try {
            Configuration config = resources.getConfiguration();
            if (config == null) {
                return;
            }
            float density = metrics.density > 0 ? metrics.density : 1f;
            int widthPixels = metrics.widthPixels > 0 ? metrics.widthPixels : sWidth;
            int heightPixels = metrics.heightPixels > 0 ? metrics.heightPixels : sHeight;
            if (widthPixels > 0) {
                config.screenWidthDp = Math.round(widthPixels / density);
            }
            if (heightPixels > 0) {
                config.screenHeightDp = Math.round(heightPixels / density);
            }
            if (config.screenWidthDp > 0 && config.screenHeightDp > 0) {
                config.smallestScreenWidthDp = Math.min(config.screenWidthDp, config.screenHeightDp);
            }
            if (sDpi > 0) {
                config.densityDpi = sDpi;
            }
            applyScreenLayout(config);
        } catch (Throwable t) {
            Slog.w(TAG, "apply display configuration failed", t);
        }
    }

    /** emu 档案的 screenSize / screenFormat / orientation 一并落到 Configuration。 */
    private static void applyScreenLayout(Configuration config) {
        String size = text("screenSize");
        if (size != null) {
            int value = config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
            switch (size) {
                case "small":
                    value = Configuration.SCREENLAYOUT_SIZE_SMALL;
                    break;
                case "normal":
                    value = Configuration.SCREENLAYOUT_SIZE_NORMAL;
                    break;
                case "large":
                    value = Configuration.SCREENLAYOUT_SIZE_LARGE;
                    break;
                case "xlarge":
                    value = Configuration.SCREENLAYOUT_SIZE_XLARGE;
                    break;
                default:
                    break;
            }
            config.screenLayout = (config.screenLayout & ~Configuration.SCREENLAYOUT_SIZE_MASK) | value;
        }
        String format = text("screenFormat");
        if (format != null) {
            int value = "long".equals(format)
                    ? Configuration.SCREENLAYOUT_LONG_YES : Configuration.SCREENLAYOUT_LONG_NO;
            config.screenLayout = (config.screenLayout & ~Configuration.SCREENLAYOUT_LONG_MASK) | value;
        }
        String orientation = text("orientation");
        if ("landscape".equals(orientation)) {
            config.orientation = Configuration.ORIENTATION_LANDSCAPE;
        } else if ("portrait".equals(orientation)) {
            config.orientation = Configuration.ORIENTATION_PORTRAIT;
        }
    }

    public static boolean hasDisplayOverride() {
        return sWidth > 0 || sHeight > 0 || sDpi > 0;
    }

    public static boolean hasProfile() {
        return sHasProfile;
    }

    // ==================== 对 hook 暴露的字段读取 ====================

    public static String getString(String... keys) {
        return text(keys);
    }

    public static int getInt(int defValue, String... keys) {
        int value = number(keys);
        return value > 0 ? value : defValue;
    }

    /** Settings.Secure 里会被替换的键（android_id …）。 */
    public static String getSecureSetting(String name) {
        if (name == null) {
            return null;
        }
        if ("android_id".equals(name)) {
            return text("androidId", "android_id");
        }
        if ("bluetooth_name".equals(name)) {
            return text("bluetoothName");
        }
        return null;
    }

    public static String androidId() {
        return text("androidId", "android_id", "aid");
    }

    public static String imeiForSlot(int slot) {
        String primary = imei();
        if (slot <= 0) {
            return primary;
        }
        return has("imei2") ? raw("imei2") : primary;
    }

    /** 档案里显式给空串 = 该设备没有 IMEI（例如 wifi-only 平板）。 */
    public static String imei() {
        if (has("imei1")) {
            return raw("imei1");
        }
        if (has("imei")) {
            return raw("imei");
        }
        return text("deviceId");
    }

    public static String meid() {
        return text("meid");
    }

    public static String imsi() {
        return text("imsi", "subscriberId");
    }

    public static String iccid() {
        return text("iccid", "simSerialNumber");
    }

    public static String phoneNumber() {
        return text("phoneNumber", "line1Number", "msisdn");
    }

    /**
     * getNetworkOperatorName()。
     *
     * <p>emu 档案在 wifi-only / 无 SIM 时把 carrier 明确写成空串，这种情况下必须返回
     * 空串，不能回落到真机运营商名（否则一条「无 SIM」档案发出去带着 DITO/CMCC 就更可疑）。</p>
     */
    public static String carrierName() {
        if (has("carrier")) {
            return raw("carrier");
        }
        if (has("operator")) {
            return raw("operator");
        }
        return text("networkOperatorName", "simOperatorName");
    }

    public static String simOperatorName() {
        if (has("operator")) {
            return raw("operator");
        }
        if (has("carrier")) {
            return raw("carrier");
        }
        return text("simOperatorName");
    }

    /** getNetworkOperator() / getSimOperator()：MCC+MNC，MNC 补零到 2~3 位。 */
    public static String networkOperator() {
        int mcc = number("mcc");
        int mnc = number("mnc");
        if (has("mcc") && mcc <= 0) {
            // 档案明确标注 mcc=0：无 SIM，运营商串必须为空而不是真机值
            return "";
        }
        if (mcc <= 0) {
            return null;
        }
        String mccText = pad(mcc, 3);
        String mncText = mnc > 0 ? (mnc < 100 ? pad(mnc, 2) : pad(mnc, 3)) : "";
        return mccText + mncText;
    }

    /** getSimCountryIso() / getNetworkCountryIso()：小写两位国家码。 */
    public static String countryIso() {
        if (has("countryCode")) {
            String code = raw("countryCode");
            return code.isEmpty() ? "" : code.toLowerCase(Locale.US);
        }
        String code = text("countryIso", "country");
        return code != null ? code.toLowerCase(Locale.US) : null;
    }

    public static String macAddress() {
        if (has("mac")) {
            return raw("mac");
        }
        return text("wifiMac", "macAddress");
    }

    public static String wifiSsid() {
        return text("ssid", "wifiSsid");
    }

    public static String wifiBssid() {
        return text("bssid", "wifiBssid");
    }

    /** 电量百分比，档案没给返回 -1。 */
    public static int batteryLevel() {
        if (!has("batteryLevel")) {
            return -1;
        }
        return number("batteryLevel");
    }

    /** 是否在充电；档案没给返回 null。 */
    public static Boolean isCharging() {
        if (!has("isOnCharging")) {
            return null;
        }
        String value = raw("isOnCharging").trim().toLowerCase(Locale.US);
        if (value.isEmpty()) {
            return null;
        }
        return "yes".equals(value) || "true".equals(value) || "1".equals(value)
                || "charging".equals(value);
    }

    /** AudioManager.getStreamVolume 用（emu 的 systemVolume）。 */
    public static int systemVolume() {
        return number("systemVolume");
    }

    /** WifiInfo.getIpAddress() 用的网络序 int；无法解析返回 0。 */
    public static int ipAddressInt() {
        String ip = text("ipAddress", "ip");
        if (ip == null) {
            return 0;
        }
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return 0;
        }
        try {
            int value = 0;
            for (int i = 0; i < 4; i++) {
                value |= (Integer.parseInt(parts[i]) & 0xff) << (8 * i);
            }
            return value;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static long totalMemory() {
        return longNumber("totalMemory");
    }

    public static long availableMemory() {
        return longNumber("availableMemory");
    }

    public static long totalStorage() {
        return longNumber("totalInternalStorage", "totalStorage");
    }

    public static long availableStorage() {
        return longNumber("availableInternalStorage", "availableStorage");
    }

    public static String modelName() {
        return sModel;
    }

    public static String brandName() {
        return sBrand;
    }

    // ==================== 内部工具 ====================

    private static boolean has(String key) {
        JSONObject json = sProfile;
        return json != null && json.has(key) && json.opt(key) != JSONObject.NULL;
    }

    /** 原样返回（可能是空串），用于"显式给了空值"的语义。 */
    private static String raw(String key) {
        JSONObject json = sProfile;
        if (json == null) {
            return "";
        }
        return json.optString(key, "");
    }

    private static String firstNonNull(String... values) {
        for (String value : values) {
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    private static String text(String... keys) {
        JSONObject json = sProfile;
        if (json == null || keys == null) {
            return null;
        }
        for (String key : keys) {
            if (key == null) {
                continue;
            }
            String value = json.optString(key, "");
            if (!value.isEmpty() && !"null".equalsIgnoreCase(value)) {
                return value;
            }
        }
        return null;
    }

    private static int number(String... keys) {
        JSONObject json = sProfile;
        if (json == null || keys == null) {
            return 0;
        }
        for (String key : keys) {
            if (key == null) {
                continue;
            }
            Object value = json.opt(key);
            if (value == null || value == JSONObject.NULL) {
                continue;
            }
            if (value instanceof Number) {
                return ((Number) value).intValue();
            }
            String textValue = String.valueOf(value).trim();
            if (textValue.isEmpty()) {
                continue;
            }
            try {
                return (int) Double.parseDouble(textValue);
            } catch (NumberFormatException ignored) {
                // try next key
            }
        }
        return 0;
    }

    private static long longNumber(String... keys) {
        JSONObject json = sProfile;
        if (json == null || keys == null) {
            return 0L;
        }
        for (String key : keys) {
            if (key == null) {
                continue;
            }
            Object value = json.opt(key);
            if (value == null || value == JSONObject.NULL) {
                continue;
            }
            if (value instanceof Number) {
                return ((Number) value).longValue();
            }
            String textValue = String.valueOf(value).trim();
            if (textValue.isEmpty()) {
                continue;
            }
            try {
                return (long) Double.parseDouble(textValue);
            } catch (NumberFormatException ignored) {
                // try next key
            }
        }
        return 0L;
    }

    private static String pad(int value, int length) {
        StringBuilder builder = new StringBuilder(String.valueOf(value));
        while (builder.length() < length) {
            builder.insert(0, "0");
        }
        return builder.toString();
    }

    private static String[] splitLocale(String localeTag, String language, String country) {
        String lang = language != null ? language : "en";
        String region = country != null ? country : "";
        if (localeTag != null) {
            String[] parts = localeTag.replace('-', '_').split("_");
            if (parts.length > 0 && !parts[0].isEmpty()) {
                lang = parts[0];
            }
            if (parts.length > 1 && !parts[1].isEmpty()) {
                region = parts[1];
            }
        }
        return new String[]{lang, region};
    }

    private static void setStaticField(String name, String value) {
        if (value == null) {
            return;
        }
        setStaticField("android.os.Build", name, value);
    }

    private static void setStaticField(String className, String name, String value) {
        if (value == null) {
            return;
        }
        try {
            Class<?> clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(null, value);
        } catch (Throwable t) {
            Slog.w(TAG, "unable to override " + className + "." + name + ": " + t.getMessage());
        }
    }

    private static void setStaticIntField(String className, String name, int value) {
        try {
            Class<?> clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.setInt(null, value);
        } catch (Throwable t) {
            Slog.w(TAG, "unable to override " + className + "." + name + ": " + t.getMessage());
        }
    }

    private static void setStaticObjectField(String name, Object value) {
        try {
            Field field = Build.class.getDeclaredField(name);
            field.setAccessible(true);
            field.set(null, value);
        } catch (Throwable t) {
            Slog.w(TAG, "unable to override Build." + name + ": " + t.getMessage());
        }
    }

    private static String readText(File file) {
        FileInputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toString("UTF-8");
        } catch (Throwable t) {
            Slog.w(TAG, "read device config failed", t);
            return null;
        } finally {
            CloseUtils.close(is, bos);
        }
    }
}
