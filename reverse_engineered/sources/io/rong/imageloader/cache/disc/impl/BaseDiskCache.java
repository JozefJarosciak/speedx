package io.rong.imageloader.cache.disc.impl;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import io.rong.imageloader.cache.disc.DiskCache;
import io.rong.imageloader.cache.disc.naming.FileNameGenerator;
import io.rong.imageloader.core.DefaultConfigurationFactory;
import io.rong.imageloader.utils.IoUtils;
import io.rong.imageloader.utils.IoUtils.CopyListener;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BaseDiskCache implements DiskCache {
    public static final int DEFAULT_BUFFER_SIZE = 32768;
    public static final CompressFormat DEFAULT_COMPRESS_FORMAT = CompressFormat.PNG;
    public static final int DEFAULT_COMPRESS_QUALITY = 100;
    private static final String ERROR_ARG_NULL = " argument must be not null";
    private static final String TEMP_IMAGE_POSTFIX = ".tmp";
    protected int bufferSize;
    protected final File cacheDir;
    protected CompressFormat compressFormat;
    protected int compressQuality;
    protected final FileNameGenerator fileNameGenerator;
    protected final File reserveCacheDir;

    public BaseDiskCache(File file) {
        this(file, null);
    }

    public BaseDiskCache(File file, File file2) {
        this(file, file2, DefaultConfigurationFactory.createFileNameGenerator());
    }

    public BaseDiskCache(File file, File file2, FileNameGenerator fileNameGenerator) {
        this.bufferSize = 32768;
        this.compressFormat = DEFAULT_COMPRESS_FORMAT;
        this.compressQuality = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (fileNameGenerator == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.cacheDir = file;
            this.reserveCacheDir = file2;
            this.fileNameGenerator = fileNameGenerator;
        }
    }

    public File getDirectory() {
        return this.cacheDir;
    }

    public File get(String str) {
        return getFile(str);
    }

    public boolean save(String str, InputStream inputStream, CopyListener copyListener) throws IOException {
        boolean copyStream;
        Throwable th;
        File file = getFile(str);
        File file2 = new File(file.getAbsolutePath() + TEMP_IMAGE_POSTFIX);
        Closeable bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2), this.bufferSize);
            copyStream = IoUtils.copyStream(inputStream, bufferedOutputStream, copyListener, this.bufferSize);
            try {
                IoUtils.closeSilently(bufferedOutputStream);
                if (copyStream && !file2.renameTo(file)) {
                    copyStream = false;
                }
                if (!copyStream) {
                    file2.delete();
                }
                return copyStream;
            } catch (Throwable th2) {
                th = th2;
                copyStream = false;
                if (!copyStream) {
                    file2.delete();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            copyStream = false;
            if (copyStream && !file2.renameTo(file)) {
                copyStream = false;
            }
            if (copyStream) {
                file2.delete();
            }
            throw th;
        }
    }

    public boolean save(String str, Bitmap bitmap) throws IOException {
        File file = getFile(str);
        File file2 = new File(file.getAbsolutePath() + TEMP_IMAGE_POSTFIX);
        Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2), this.bufferSize);
        try {
            boolean compress = bitmap.compress(this.compressFormat, this.compressQuality, bufferedOutputStream);
            IoUtils.closeSilently(bufferedOutputStream);
            if (compress && !file2.renameTo(file)) {
                compress = false;
            }
            if (!compress) {
                file2.delete();
            }
            bitmap.recycle();
            return compress;
        } catch (Throwable th) {
            IoUtils.closeSilently(bufferedOutputStream);
            file2.delete();
        }
    }

    public boolean remove(String str) {
        return getFile(str).delete();
    }

    public void close() {
    }

    public void clear() {
        File[] listFiles = this.cacheDir.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    protected File getFile(String str) {
        String generate = this.fileNameGenerator.generate(str);
        File file = this.cacheDir;
        if (!(this.cacheDir.exists() || this.cacheDir.mkdirs() || this.reserveCacheDir == null || (!this.reserveCacheDir.exists() && !this.reserveCacheDir.mkdirs()))) {
            file = this.reserveCacheDir;
        }
        return new File(file, generate);
    }

    public void setBufferSize(int i) {
        this.bufferSize = i;
    }

    public void setCompressFormat(CompressFormat compressFormat) {
        this.compressFormat = compressFormat;
    }

    public void setCompressQuality(int i) {
        this.compressQuality = i;
    }
}
