package io.rong.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import io.rong.imlib.NativeClient;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.cli.HelpFormatter;

public class RFLog {
    private static final int BUFF_SIZE = 1048576;
    private static String LOG_FILE = null;
    private static final String TAG = "RFLog";
    private static String ZIP_FILE;
    private static int mode;

    private static class UploadFile extends Thread {
        private UploadFile() {
        }

        public void run() {
            File file;
            DataOutputStream dataOutputStream;
            File file2;
            Exception e;
            InputStream inputStream;
            DataOutputStream dataOutputStream2;
            Throwable th;
            Object obj;
            Object obj2;
            File file3 = null;
            DataOutputStream dataOutputStream3 = null;
            InputStream inputStream2 = null;
            InputStream fileInputStream;
            try {
                file = new File(RFLog.LOG_FILE);
                try {
                    if (!file.exists()) {
                        if (file3 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (file3 != null) {
                            try {
                                dataOutputStream3.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (file3 != null) {
                            file3.delete();
                        }
                        if (file != null) {
                            file.delete();
                        }
                    } else if (zipLogFile()) {
                        String uuid = UUID.randomUUID().toString();
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://feedback.cn.ronghub.com").openConnection();
                        httpURLConnection.setReadTimeout(5000);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setRequestProperty("RC-App-Key", NativeClient.getInstance().getAppKey());
                        httpURLConnection.setRequestProperty("RC-User-Token", NativeClient.getInstance().getToken());
                        httpURLConnection.setRequestProperty("Connection", "keep-alive");
                        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + uuid);
                        dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        try {
                            dataOutputStream.writeBytes(HelpFormatter.DEFAULT_LONG_OPT_PREFIX + uuid + "\r\n");
                            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"fileLog\"; filename=\"" + NativeClient.getInstance().getDeviceId() + "_" + Long.toString(System.currentTimeMillis()) + ".zip\"\r\n");
                            dataOutputStream.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
                            file2 = new File(RFLog.ZIP_FILE);
                            try {
                                fileInputStream = new FileInputStream(file2);
                                try {
                                    byte[] bArr = new byte[1024];
                                    while (true) {
                                        int read = fileInputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        dataOutputStream.write(bArr, 0, read);
                                    }
                                    dataOutputStream.writeBytes("\r\n--" + uuid + "--\r\n");
                                    dataOutputStream.flush();
                                    Log.d(RFLog.TAG, "UploadFile log end. code = " + httpURLConnection.getResponseCode());
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e32) {
                                            e32.printStackTrace();
                                        }
                                    }
                                    if (dataOutputStream != null) {
                                        try {
                                            dataOutputStream.close();
                                        } catch (IOException e322) {
                                            e322.printStackTrace();
                                        }
                                    }
                                    if (file2 != null) {
                                        file2.delete();
                                    }
                                    if (file != null) {
                                        file.delete();
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                    file3 = file2;
                                    file2 = file;
                                    inputStream = fileInputStream;
                                    dataOutputStream2 = dataOutputStream;
                                    try {
                                        e.printStackTrace();
                                        Log.e(RFLog.TAG, "UploadFile log failed.");
                                        if (inputStream != null) {
                                            try {
                                                inputStream.close();
                                            } catch (IOException e3222) {
                                                e3222.printStackTrace();
                                            }
                                        }
                                        if (dataOutputStream2 != null) {
                                            try {
                                                dataOutputStream2.close();
                                            } catch (IOException e32222) {
                                                e32222.printStackTrace();
                                            }
                                        }
                                        if (file3 != null) {
                                            file3.delete();
                                        }
                                        if (file2 != null) {
                                            file2.delete();
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        dataOutputStream = dataOutputStream2;
                                        fileInputStream = inputStream;
                                        file = file2;
                                        if (fileInputStream != null) {
                                            try {
                                                fileInputStream.close();
                                            } catch (IOException e22) {
                                                e22.printStackTrace();
                                            }
                                        }
                                        if (dataOutputStream != null) {
                                            try {
                                                dataOutputStream.close();
                                            } catch (IOException e222) {
                                                e222.printStackTrace();
                                            }
                                        }
                                        if (file3 != null) {
                                            file3.delete();
                                        }
                                        if (file != null) {
                                            file.delete();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    file3 = file2;
                                    if (fileInputStream != null) {
                                        fileInputStream.close();
                                    }
                                    if (dataOutputStream != null) {
                                        dataOutputStream.close();
                                    }
                                    if (file3 != null) {
                                        file3.delete();
                                    }
                                    if (file != null) {
                                        file.delete();
                                    }
                                    throw th;
                                }
                            } catch (Exception e5) {
                                e = e5;
                                dataOutputStream2 = dataOutputStream;
                                File file4 = file;
                                obj = file3;
                                file3 = file2;
                                file2 = file4;
                                e.printStackTrace();
                                Log.e(RFLog.TAG, "UploadFile log failed.");
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (dataOutputStream2 != null) {
                                    dataOutputStream2.close();
                                }
                                if (file3 != null) {
                                    file3.delete();
                                }
                                if (file2 != null) {
                                    file2.delete();
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                obj2 = file3;
                                file3 = file2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (dataOutputStream != null) {
                                    dataOutputStream.close();
                                }
                                if (file3 != null) {
                                    file3.delete();
                                }
                                if (file != null) {
                                    file.delete();
                                }
                                throw th;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            file2 = file;
                            dataOutputStream2 = dataOutputStream;
                            obj = file3;
                            e.printStackTrace();
                            Log.e(RFLog.TAG, "UploadFile log failed.");
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (dataOutputStream2 != null) {
                                dataOutputStream2.close();
                            }
                            if (file3 != null) {
                                file3.delete();
                            }
                            if (file2 != null) {
                                file2.delete();
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            obj2 = file3;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (file3 != null) {
                                file3.delete();
                            }
                            if (file != null) {
                                file.delete();
                            }
                            throw th;
                        }
                    } else {
                        Log.d(RFLog.TAG, "UploadFile log file == null.");
                        if (file3 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        if (file3 != null) {
                            try {
                                dataOutputStream3.close();
                            } catch (IOException e322222) {
                                e322222.printStackTrace();
                            }
                        }
                        if (file3 != null) {
                            file3.delete();
                        }
                        if (file != null) {
                            file.delete();
                        }
                    }
                } catch (Exception e7) {
                    e = e7;
                    file2 = file;
                    obj2 = file3;
                    obj = file3;
                    e.printStackTrace();
                    Log.e(RFLog.TAG, "UploadFile log failed.");
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (dataOutputStream2 != null) {
                        dataOutputStream2.close();
                    }
                    if (file3 != null) {
                        file3.delete();
                    }
                    if (file2 != null) {
                        file2.delete();
                    }
                } catch (Throwable th6) {
                    th = th6;
                    obj2 = file3;
                    Object obj3 = file3;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (file3 != null) {
                        file3.delete();
                    }
                    if (file != null) {
                        file.delete();
                    }
                    throw th;
                }
            } catch (Exception e8) {
                e = e8;
                file2 = file3;
                obj = file3;
                obj2 = file3;
                e.printStackTrace();
                Log.e(RFLog.TAG, "UploadFile log failed.");
                if (inputStream != null) {
                    inputStream.close();
                }
                if (dataOutputStream2 != null) {
                    dataOutputStream2.close();
                }
                if (file3 != null) {
                    file3.delete();
                }
                if (file2 != null) {
                    file2.delete();
                }
            } catch (Throwable th7) {
                th = th7;
                file = file3;
                fileInputStream = file3;
                dataOutputStream = file3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (file3 != null) {
                    file3.delete();
                }
                if (file != null) {
                    file.delete();
                }
                throw th;
            }
        }

        private static boolean zipLogFile() throws IOException {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(RFLog.LOG_FILE), 1048576);
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(RFLog.ZIP_FILE)));
            try {
                byte[] bArr = new byte[1048576];
                zipOutputStream.putNextEntry(new ZipEntry(RFLog.LOG_FILE.substring(RFLog.LOG_FILE.lastIndexOf("/") + 1)));
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 1048576);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                bufferedInputStream.close();
                zipOutputStream.close();
            }
        }
    }

    public static void uploadIfNeed(Context context) {
        Context applicationContext = context.getApplicationContext();
        LOG_FILE = getCachePath(applicationContext, "ronglog") + "/RongLog.log";
        ZIP_FILE = getCachePath(applicationContext, "ronglog") + "/RongLog.zip";
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("Log2File", 0);
        if (sharedPreferences != null) {
            mode = sharedPreferences.getInt("LOG_MODE", 0);
            long j = sharedPreferences.getLong("LOG_START", 0);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - j > 259200000) {
                RLog.m19419d(TAG, "Stop write log");
                mode = 0;
                NativeClient.getInstance().setLogStatus(false);
            } else if (mode > 0) {
                RLog.m19419d(TAG, "Start write log");
                NativeClient.getInstance().setLogStatus(true);
                Editor edit = sharedPreferences.edit();
                j = sharedPreferences.getLong("LOG_TIME", 0);
                if (j == 0) {
                    edit.putLong("LOG_TIME", currentTimeMillis).apply();
                } else if (currentTimeMillis - j > 7200000) {
                    edit.putLong("LOG_TIME", currentTimeMillis).apply();
                    if (new File(LOG_FILE).exists()) {
                        new UploadFile().start();
                    }
                }
            } else {
                RLog.m19419d(TAG, "Stop write log");
                NativeClient.getInstance().setLogStatus(false);
            }
        }
    }

    public static void setMode(Context context, int i) {
        RLog.m19419d(TAG, "setMode = " + i);
        SharedPreferences sharedPreferences = context.getSharedPreferences("Log2File", 0);
        if (sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            if (i != sharedPreferences.getInt("LOG_MODE", -1)) {
                edit.putLong("LOG_START", System.currentTimeMillis());
                edit.putInt("LOG_MODE", i);
                edit.apply();
            }
        }
    }

    public static void write(String str, String str2) {
        if (mode != 0) {
            File file = new File(LOG_FILE);
            String str3 = new SimpleDateFormat("MM-dd HH:mm:ss.SS", Locale.getDefault()).format(new Date()) + " " + Process.myPid() + " " + str + ": " + str2;
            try {
                Writer fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(str3);
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void write(String str, Exception exception) {
        if (mode != 0) {
            String str2 = null;
            try {
                Writer stringWriter = new StringWriter();
                exception.printStackTrace(new PrintWriter(stringWriter));
                str2 = "\r\n" + stringWriter.toString() + "\r\n";
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(str2)) {
                write(str, str2);
            }
        }
    }

    public static void write(String str, String str2, int i) {
        if (mode == 0) {
            return;
        }
        if (mode != 1 || i >= 6) {
            write(str, str2);
        }
    }

    private static String getCachePath(Context context, String str) {
        boolean equals = Environment.getExternalStorageState().equals("mounted");
        File externalCacheDir = context.getExternalCacheDir();
        if (!equals || externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        if (TextUtils.isEmpty(str)) {
            return externalCacheDir.getPath();
        }
        File file = new File(externalCacheDir.getPath() + "/" + str);
        if (file.exists() || file.mkdir()) {
            return file.getPath();
        }
        return context.getCacheDir().getPath();
    }
}
