package ch.qos.logback.core.rolling.helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsynchronousCompressor {
    Compressor compressor;

    public AsynchronousCompressor(Compressor compressor) {
        this.compressor = compressor;
    }

    public Future<?> compressAsynchronously(String str, String str2, String str3) {
        ExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
        Future<?> submit = newScheduledThreadPool.submit(new CompressionRunnable(this.compressor, str, str2, str3));
        newScheduledThreadPool.shutdown();
        return submit;
    }
}
