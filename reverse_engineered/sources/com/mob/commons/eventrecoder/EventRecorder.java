package com.mob.commons.eventrecoder;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.cons.C0844a;
import com.mob.commons.C4245e;
import com.mob.tools.MobLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class EventRecorder {
    /* renamed from: a */
    private static Context f14925a;
    /* renamed from: b */
    private static File f14926b;
    /* renamed from: c */
    private static FileOutputStream f14927c;

    /* renamed from: com.mob.commons.eventrecoder.EventRecorder$1 */
    static class C42461 implements Runnable {
        C42461() {
        }

        public void run() {
            try {
                EventRecorder.f14926b = new File(EventRecorder.f14925a.getFilesDir(), ".mrecord");
                if (!EventRecorder.f14926b.exists()) {
                    EventRecorder.f14926b.createNewFile();
                }
                EventRecorder.f14927c = new FileOutputStream(EventRecorder.f14926b, true);
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
            }
        }
    }

    /* renamed from: com.mob.commons.eventrecoder.EventRecorder$4 */
    static class C42494 implements Runnable {
        C42494() {
        }

        public void run() {
            try {
                EventRecorder.f14927c.close();
                EventRecorder.f14926b.delete();
                EventRecorder.f14926b = new File(EventRecorder.f14925a.getFilesDir(), ".mrecord");
                EventRecorder.f14926b.createNewFile();
                EventRecorder.f14927c = new FileOutputStream(EventRecorder.f14926b, true);
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
            }
        }
    }

    public static final synchronized void prepare(Context context) {
        synchronized (EventRecorder.class) {
            f14925a = context.getApplicationContext();
            m16875a(new C42461());
        }
    }

    public static final synchronized void addBegin(String str, String str2) {
        synchronized (EventRecorder.class) {
            m16876a(str + " " + str2 + " 0\n");
        }
    }

    /* renamed from: a */
    private static final void m16875a(Runnable runnable) {
        C4245e.m16870a(new File(f14925a.getFilesDir(), "comm/locks/.mrlock"), runnable);
    }

    public static final synchronized void addEnd(String str, String str2) {
        synchronized (EventRecorder.class) {
            m16876a(str + " " + str2 + " 1\n");
        }
    }

    /* renamed from: a */
    private static final void m16876a(final String str) {
        m16875a(new Runnable() {
            public void run() {
                try {
                    EventRecorder.f14927c.write(str.getBytes("utf-8"));
                    EventRecorder.f14927c.flush();
                } catch (Throwable th) {
                    MobLog.getInstance().m16946w(th);
                }
            }
        });
    }

    public static final synchronized String checkRecord(final String str) {
        String str2;
        synchronized (EventRecorder.class) {
            final LinkedList linkedList = new LinkedList();
            m16875a(new Runnable() {
                public void run() {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(EventRecorder.f14926b), "utf-8"));
                        for (Object readLine = bufferedReader.readLine(); !TextUtils.isEmpty(readLine); readLine = bufferedReader.readLine()) {
                            String[] split = readLine.split(" ");
                            if (str.equals(split[0])) {
                                if ("0".equals(split[2])) {
                                    linkedList.add(split[1]);
                                } else if (C0844a.f2048d.equals(split[2])) {
                                    int indexOf = linkedList.indexOf(split[1]);
                                    if (indexOf != -1) {
                                        linkedList.remove(indexOf);
                                    }
                                }
                            }
                        }
                        bufferedReader.close();
                    } catch (Throwable th) {
                        MobLog.getInstance().m16934d(th);
                    }
                }
            });
            if (linkedList.size() > 0) {
                str2 = (String) linkedList.get(0);
            } else {
                str2 = null;
            }
        }
        return str2;
    }

    public static final synchronized void clear() {
        synchronized (EventRecorder.class) {
            m16875a(new C42494());
        }
    }
}
