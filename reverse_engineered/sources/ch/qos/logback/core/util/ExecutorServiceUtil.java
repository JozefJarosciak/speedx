package ch.qos.logback.core.util;

import ch.qos.logback.core.CoreConstants;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceUtil {
    public static ExecutorService newExecutorService() {
        return new ThreadPoolExecutor(CoreConstants.CORE_POOL_SIZE, 32, 0, TimeUnit.MILLISECONDS, new SynchronousQueue());
    }

    public static void shutdown(ExecutorService executorService) {
        executorService.shutdownNow();
    }
}
