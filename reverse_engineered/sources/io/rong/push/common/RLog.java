package io.rong.push.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.os.Process;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RLog {
    static final boolean DEBUG = true;
    private static String FILE_NAME = "PushLog.txt";
    private static Boolean IS_WRITE_TO_FILE = Boolean.valueOf(true);
    private static int LOG_FILE_SAVE_DAYS = 1;
    private static String LOG_PATH = "/sdcard/";
    private static String RongLog = "RongLog-Push";
    private static SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat logFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* renamed from: i */
    public static void m8382i(String str, String str2) {
        log(str, str2, 'i');
    }

    /* renamed from: v */
    public static void m8383v(String str, String str2) {
        log(str, str2, 'v');
    }

    /* renamed from: d */
    public static void m8380d(String str, String str2) {
        log(str, str2, 'd');
    }

    /* renamed from: e */
    public static void m8381e(String str, String str2) {
        log(str, str2, 'e');
    }

    private static void log(String str, String str2, char c) {
        String str3 = RongLog + "[" + str + "]";
        if ('e' == c) {
            Log.e(str3, str2);
        } else if ('w' == c) {
            Log.w(str3, str2);
        } else if ('d' == c) {
            Log.d(str3, str2);
        } else if ('i' == c) {
            Log.i(str3, str2);
        } else {
            Log.v(str3, str2);
        }
    }

    private static void writeLogtoFile(String str, String str2, String str3) {
        Date date = new Date();
        String format = fileNameFormat.format(date);
        String str4 = logFormat.format(date) + "  " + str + "  " + Process.myPid() + "    " + str2 + "    " + str3;
        File file = new File(LOG_PATH, format + FILE_NAME);
        delFile();
        try {
            Writer fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str4);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delFile() {
        File file = new File(LOG_PATH, fileNameFormat.format(getDateBefore()) + FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    private static Date getDateBefore() {
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(5, instance.get(5) - LOG_FILE_SAVE_DAYS);
        return instance.getTime();
    }

    public static void sendLog(Context context, List<String> list) {
        Parcelable fromFile;
        try {
            fromFile = Uri.fromFile(new File(LOG_PATH, fileNameFormat.format(new Date()) + FILE_NAME));
        } catch (Exception e) {
            e.printStackTrace();
            fromFile = null;
        }
        if (fromFile != null) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("message/rfc822");
            intent.putExtra("android.intent.extra.EMAIL", list.toArray());
            intent.putExtra("android.intent.extra.SUBJECT", "RongCloud log");
            intent.putExtra("android.intent.extra.STREAM", fromFile);
            context.startActivity(intent);
        }
    }
}
