package top.niunaijun.blackbox.utils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * Per-virtual-app network proxy.
 *
 * <p>The host UI writes every configuration into {@code files/app_proxy.json}
 * (a map of {@code <userId>_<packageName>} to a small json object). The server
 * process reads that file once per guest process start and hands the raw json
 * to the guest through {@code AppConfig}, so the guest never performs any file
 * IO while it is being bound (that would deadlock on the filesystem hooks).</p>
 */
public final class AppProxyManager {

    private static final String TAG = "AppProxyManager";

    /** Must match the constant used by the host UI. */
    public static final String CONFIG_FILE = "app_proxy.json";
    public static final String TYPE_HTTP = "http";
    public static final String TYPE_SOCKS5 = "socks5";

    private AppProxyManager() {
    }

    /** The container is single user, the package name is the whole key. */
    public static String key(String packageName) {
        return packageName;
    }

    /**
     * Runs in the server process, which is allowed to touch the host files
     * before the guest is bound.
     */
    public static String readStoredConfig(File hostFilesDir, String packageName) {
        if (hostFilesDir == null || packageName == null) {
            return null;
        }
        File file = new File(hostFilesDir, CONFIG_FILE);
        if (!file.exists()) {
            return null;
        }
        String raw = readText(file);
        if (raw == null || raw.isEmpty()) {
            return null;
        }
        try {
            JSONObject all = new JSONObject(raw);
            String value = all.optString(key(packageName), null);
            if (value != null) {
                return value;
            }
            // Legacy per-user keys ("<userId>_<packageName>") written by older builds.
            String suffix = "_" + packageName;
            java.util.Iterator<String> keys = all.keys();
            while (keys.hasNext()) {
                String legacy = keys.next();
                if (legacy.endsWith(suffix)) {
                    return all.optString(legacy, null);
                }
            }
            return null;
        } catch (Throwable t) {
            Slog.w(TAG, "parse proxy config file failed", t);
            return null;
        }
    }

    /**
     * Runs inside the guest process. Pure memory work, no IO.
     */
    public static void installFromJson(String raw) {
        try {
            if (raw == null || raw.isEmpty()) {
                return;
            }
            JSONObject json = new JSONObject(raw);
            if (!json.optBoolean("enabled", false)) {
                return;
            }
            String type = json.optString("type", TYPE_HTTP);
            String host = json.optString("host", "");
            int port = json.optInt("port", 0);
            if (host.isEmpty() || port <= 0 || port > 65535) {
                Slog.w(TAG, "invalid proxy config: " + host + ":" + port);
                return;
            }
            String username = json.optString("username", "");
            String password = json.optString("password", "");
            String bypass = json.optString("bypass", "localhost,127.0.0.1");
            apply(type, host, port, username, password, bypass);
            Slog.d(TAG, "proxy installed: " + type + "://" + host + ":" + port);
        } catch (Throwable t) {
            Slog.w(TAG, "install proxy failed", t);
        }
    }

    private static String readText(File file) {
        FileInputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toString("UTF-8");
        } catch (Throwable t) {
            Slog.w(TAG, "read proxy config failed: " + t);
            return null;
        } finally {
            CloseUtils.close(is);
            CloseUtils.close(bos);
        }
    }

    private static void apply(String type, String host, int port, String username,
                              String password, String bypass) {
        boolean socks = TYPE_SOCKS5.equalsIgnoreCase(type);
        if (socks) {
            System.setProperty("socksProxyHost", host);
            System.setProperty("socksProxyPort", String.valueOf(port));
            System.setProperty("socksProxyVersion", "5");
        } else {
            System.setProperty("http.proxyHost", host);
            System.setProperty("http.proxyPort", String.valueOf(port));
            System.setProperty("https.proxyHost", host);
            System.setProperty("https.proxyPort", String.valueOf(port));
        }
        if (bypass != null && !bypass.isEmpty()) {
            String nonProxyHosts = bypass.replace(',', '|');
            System.setProperty("http.nonProxyHosts", nonProxyHosts);
            System.setProperty("https.nonProxyHosts", nonProxyHosts);
        }

        final Proxy proxy = new Proxy(
                socks ? Proxy.Type.SOCKS : Proxy.Type.HTTP,
                InetSocketAddress.createUnresolved(host, port));
        ProxySelector.setDefault(new ProxySelector() {
            @Override
            public List<Proxy> select(URI uri) {
                return Collections.singletonList(proxy);
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                Slog.w(TAG, "proxy connect failed: " + uri + " -> " + sa);
            }
        });

        if (!username.isEmpty()) {
            final PasswordAuthentication auth =
                    new PasswordAuthentication(username, password.toCharArray());
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return auth;
                }
            });
        }
    }
}
