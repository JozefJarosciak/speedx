package io.rong.imlib.ipc;

import android.content.Context;
import io.rong.common.RLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;

public class RongExceptionHandler implements UncaughtExceptionHandler {
    Context mContext;

    public RongExceptionHandler(Context context) {
        this.mContext = context;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        File externalFilesDir = this.mContext.getExternalFilesDir("Crash");
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        File file = new File(externalFilesDir, System.currentTimeMillis() + ".log");
        try {
            file.createNewFile();
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            th.printStackTrace(printStream);
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (SecurityException e3) {
            e3.printStackTrace();
        }
        RLog.m19421e("RongExceptionHandler", "uncaughtException", th);
        System.exit(2);
    }
}
