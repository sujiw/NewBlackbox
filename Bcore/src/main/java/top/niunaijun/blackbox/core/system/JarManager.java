package top.niunaijun.blackbox.core.system;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.utils.JarUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/JarManager.class */
public class JarManager {
    private static final String TAG = "JarManager";
    private static final JarConfig.JarDefinition[] REQUIRED_JARS = JarConfig.getRequiredJars();
    private static final int BUFFER_SIZE = JarUtils.getOptimalBufferSize();
    private static final long MAX_RETRY_DELAY = 5000;
    private final ConcurrentHashMap<String, File> mJarCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> mJarHashes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, JarConfig.JarDefinition> mJarDefinitions = new ConcurrentHashMap<>();
    private final AtomicBoolean mIsInitialized = new AtomicBoolean(false);
    private final AtomicBoolean mIsInitializing = new AtomicBoolean(false);
    private static volatile JarManager sInstance;

    public static JarManager getInstance() {
        if (sInstance == null) {
            synchronized (JarManager.class) {
                if (sInstance == null) {
                    sInstance = new JarManager();
                }
            }
        }
        return sInstance;
    }

    private JarManager() {
    }

    public void initializeAsync() {
        if (this.mIsInitialized.get() || this.mIsInitializing.getAndSet(true)) {
            return;
        }
        new Thread(() -> {
            try {
                initializeJarEnvironment();
                this.mIsInitialized.set(true);
                Log.i(TAG, "JAR environment initialized successfully");
            } catch (Exception e) {
                Log.e(TAG, "Failed to initialize JAR environment", e);
            } finally {
                this.mIsInitializing.set(false);
            }
        }, "JarManager-Init").start();
    }

    public void initializeSync() {
        if (this.mIsInitialized.get()) {
            return;
        }
        try {
            initializeJarEnvironment();
            this.mIsInitialized.set(true);
            Log.i(TAG, "JAR environment initialized successfully");
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize JAR environment", e);
            throw new RuntimeException("JAR environment initialization failed", e);
        }
    }

    public boolean isReady() {
        return this.mIsInitialized.get();
    }

    public File getJarFile(String jarName) {
        if (!this.mIsInitialized.get()) {
            Log.w(TAG, "JAR environment not initialized, attempting sync initialization");
            initializeSync();
        }
        File jarFile = this.mJarCache.get(jarName);
        if (jarFile != null && jarFile.exists()) {
            return jarFile;
        }
        Log.w(TAG, "JAR file not found in cache: " + jarName);
        return null;
    }

    public File getEmptyJar() {
        return getJarFile("empty.jar");
    }

    public File getJunitJar() {
        return getJarFile("junit.jar");
    }

    private void initializeJarEnvironment() {
        Context context = BlackBoxCore.getContext();
        if (context == null) {
            throw new IllegalStateException("BlackBoxCore context is null");
        }
        Log.d(TAG, "Starting JAR environment initialization");
        for (JarConfig.JarDefinition jarDef : REQUIRED_JARS) {
            try {
                processJarFile(context, jarDef);
            } catch (Exception e) {
                Log.e(TAG, "Failed to process JAR file: " + jarDef.getAssetName(), e);
            }
        }
        verifyJarEnvironment();
    }

    private void processJarFile(Context context, JarConfig.JarDefinition jarDef) throws IOException {
        String jarName = jarDef.getAssetName();
        Log.d(TAG, "Processing JAR file: " + jarName + " (" + jarDef.getDescription() + ")");
        this.mJarDefinitions.put(jarName, jarDef);
        File targetFile = getTargetFile(jarDef);
        if (targetFile == null) {
            throw new IOException("Invalid JAR definition: " + jarName);
        }
        if (targetFile.exists() && isFileValid(targetFile, jarDef)) {
            Log.d(TAG, "JAR file already exists and is valid: " + jarName);
            this.mJarCache.put(jarName, targetFile);
            return;
        }
        copyJarFromAssets(context, jarDef, targetFile);
        if (!isFileValid(targetFile, jarDef)) {
            throw new IOException("JAR file verification failed: " + jarName);
        }
        this.mJarCache.put(jarName, targetFile);
        Log.d(TAG, "Successfully processed JAR file: " + jarName);
    }

    private File getTargetFile(JarConfig.JarDefinition jarDef) {
        return new File(BEnvironment.getCacheDir(), jarDef.getFileName());
    }

    private void copyJarFromAssets(Context context, JarConfig.JarDefinition jarDef, File targetFile) throws IOException {
        String jarName = jarDef.getAssetName();
        InputStream inputStream = null;
        try {
            InputStream inputStream2 = context.getAssets().open(jarName);
            if (inputStream2 == null) {
                throw new IOException("Asset not found: " + jarName);
            }
            File targetDir = targetFile.getParentFile();
            if (!targetDir.exists() && !targetDir.mkdirs()) {
                throw new IOException("Failed to create target directory: " + targetDir);
            }
            JarUtils.copyFileWithProgress(inputStream2, targetFile, jarName);
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (IOException e) {
                    Log.w(TAG, "Failed to close input stream for: " + jarName, e);
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    Log.w(TAG, "Failed to close input stream for: " + jarName, e2);
                }
            }
            throw th;
        }
    }

    private boolean isFileValid(File file, JarConfig.JarDefinition jarDef) {
        if (!file.exists()) {
            Log.w(TAG, "File does not exist: " + file);
            return false;
        }
        if (file.length() == 0) {
            Log.w(TAG, "File is empty: " + file);
            return false;
        }
        if (!JarConfig.validateFile(file, jarDef)) {
            Log.w(TAG, "File validation failed for " + jarDef.getAssetName() + " (size: " + file.length() + ", min: " + jarDef.getMinSize() + ")");
            return false;
        }
        String fileHash = JarUtils.calculateFileHash(file);
        if (fileHash != null) {
            this.mJarHashes.put(jarDef.getAssetName(), fileHash);
            return true;
        }
        return true;
    }

    private void verifyJarEnvironment() {
        boolean allValid = true;
        for (JarConfig.JarDefinition jarDef : REQUIRED_JARS) {
            String jarName = jarDef.getAssetName();
            File jarFile = this.mJarCache.get(jarName);
            if (jarFile == null || !jarFile.exists()) {
                if (jarDef.isRequired()) {
                    Log.e(TAG, "Required JAR file missing: " + jarName);
                    allValid = false;
                } else {
                    Log.w(TAG, "Optional JAR file missing: " + jarName);
                }
            }
        }
        if (!allValid) {
            Log.w(TAG, "JAR environment verification failed - some required files are missing");
        } else {
            Log.i(TAG, "JAR environment verification passed");
        }
    }

    public void clearCache() {
        this.mJarCache.clear();
        this.mJarHashes.clear();
        this.mJarDefinitions.clear();
        Log.d(TAG, "JAR cache cleared");
    }

    public String getCacheStats() {
        return String.format("JAR Cache Stats - Files: %d, Hashes: %d, Definitions: %d, Initialized: %s", Integer.valueOf(this.mJarCache.size()), Integer.valueOf(this.mJarHashes.size()), Integer.valueOf(this.mJarDefinitions.size()), Boolean.valueOf(this.mIsInitialized.get()));
    }

    public String getJarInfo(String jarName) {
        File jarFile = this.mJarCache.get(jarName);
        JarConfig.JarDefinition jarDef = this.mJarDefinitions.get(jarName);
        String hash = this.mJarHashes.get(jarName);
        if (jarFile == null || jarDef == null) {
            return "JAR not found: " + jarName;
        }
        Object[] objArr = new Object[4];
        objArr[0] = jarName;
        objArr[1] = Long.valueOf(jarFile.length());
        objArr[2] = hash != null ? hash.substring(0, Math.min(8, hash.length())) + "..." : "N/A";
        objArr[3] = Boolean.valueOf(jarDef.isRequired());
        return String.format("JAR: %s, Size: %d bytes, Hash: %s, Required: %s", objArr);
    }
}
