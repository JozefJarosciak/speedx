package com.avos.avoscloud;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import java.util.Map;

public class LogUtil {

    public static class avlog {
        public static boolean showAVLog = true;

        /* renamed from: i */
        public static void m3509i(String str) {
            if (showAVLog) {
                log.m3523i(str);
            }
        }

        /* renamed from: i */
        public static void m3508i(Object obj) {
            if (showAVLog) {
                log.m3523i("" + obj);
            }
        }

        /* renamed from: d */
        public static void m3506d(String str) {
            if (showAVLog) {
                log.m3514d(str);
            }
        }

        /* renamed from: e */
        public static void m3507e(String str) {
            if (showAVLog) {
                log.m3519e(str);
            }
        }
    }

    public static class log {
        public static String Cname = "";
        public static String Mname = "";
        public static final String Tag = "===AVOS Cloud===";
        public static final boolean show = true;

        private static boolean shouldShow(int i) {
            return (AVOSCloud.getLogLevel() & i) > 0;
        }

        protected static void getTrace() {
            StackTraceElement stackTraceElement = new Throwable().fillInStackTrace().getStackTrace()[2];
            String str = stackTraceElement.getClassName();
            Cname = str.substring(str.lastIndexOf(".") + 1);
            Mname = (stackTraceElement.getMethodName() + "->" + stackTraceElement.getLineNumber() + ": ");
        }

        /* renamed from: v */
        public static void m3524v(String str) {
            if (shouldShow(2)) {
                getTrace();
                if (str == null) {
                    str = "null";
                }
                Log.v(Tag, Mname + str);
            }
        }

        /* renamed from: d */
        public static void m3514d(String str) {
            if (shouldShow(4)) {
                getTrace();
                if (str == null) {
                    str = "null";
                }
                Log.d(Tag, Cname + "->" + Mname + str);
            }
        }

        /* renamed from: d */
        public static void m3517d(Map<String, Object> map) {
            if (shouldShow(4)) {
                String str = "";
                getTrace();
                if (map == null) {
                    str = "null";
                } else {
                    try {
                        str = JSON.toJSONString(map);
                    } catch (Exception e) {
                    }
                }
                Log.d(Tag, Cname + "->" + Mname + str);
            }
        }

        /* renamed from: d */
        public static void m3513d(int i) {
            m3514d("" + i);
        }

        /* renamed from: d */
        public static void m3512d(float f) {
            m3514d("" + f);
        }

        /* renamed from: d */
        public static void m3511d(double d) {
            m3514d("" + d);
        }

        /* renamed from: d */
        public static void m3510d() {
            if (shouldShow(4)) {
                getTrace();
                Log.d(Tag, "===AVOS Cloud===->" + Mname + "");
            }
        }

        /* renamed from: d */
        public static void m3516d(String str, String str2) {
            if (shouldShow(4)) {
                getTrace();
                Log.d(str, Cname + "->" + Mname + str2);
            }
        }

        /* renamed from: d */
        public static void m3515d(String str, Exception exception) {
            if (shouldShow(32)) {
                Log.d(Tag, Cname + "->" + Mname + str + ":" + exception.toString());
                exception.printStackTrace();
            }
        }

        /* renamed from: i */
        public static void m3523i(String str) {
            if (shouldShow(8)) {
                getTrace();
                if (str == null) {
                    str = "null";
                }
                Log.i(Tag, Mname + str);
            }
        }

        /* renamed from: w */
        public static void m3525w(String str) {
            if (shouldShow(16)) {
                getTrace();
                if (str == null) {
                    str = "null";
                }
                Log.w(Tag, Mname + str);
            }
        }

        /* renamed from: e */
        public static void m3519e(String str) {
            if (shouldShow(32)) {
                getTrace();
                if (str == null) {
                    str = "null";
                }
                Log.e(Tag, Cname + "->" + Mname + str);
            }
        }

        /* renamed from: e */
        public static void m3518e() {
            if (shouldShow(32)) {
                getTrace();
                Log.e(Tag, Cname + "->" + Mname + "");
            }
        }

        /* renamed from: e */
        public static void m3520e(String str, Exception exception) {
            if (shouldShow(32)) {
                Log.e(Tag, str + Mname + "err:" + exception.toString());
                exception.printStackTrace();
            }
        }

        /* renamed from: e */
        public static void m3521e(String str, String str2) {
            if (shouldShow(32)) {
                getTrace();
                Log.e(str, Cname + "->" + Mname + str2);
            }
        }

        /* renamed from: e */
        public static void m3522e(String str, String str2, Exception exception) {
            if (shouldShow(32)) {
                getTrace();
                Log.e(str, Cname + "->" + Mname + str2 + " err:" + exception.toString());
            }
        }
    }
}
