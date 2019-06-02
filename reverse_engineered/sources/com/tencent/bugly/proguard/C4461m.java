package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.C4417a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.m */
public final class C4461m {
    /* renamed from: a */
    public static final long f15685a = System.currentTimeMillis();
    /* renamed from: b */
    private static C4461m f15686b = null;
    /* renamed from: c */
    private Context f15687c;
    /* renamed from: d */
    private String f15688d = C4417a.m17304b().f15289d;
    /* renamed from: e */
    private Map<Integer, Map<String, C4458l>> f15689e = new HashMap();
    /* renamed from: f */
    private SharedPreferences f15690f;

    private C4461m(Context context) {
        this.f15687c = context;
        this.f15690f = context.getSharedPreferences("crashrecord", 0);
    }

    /* renamed from: a */
    public static synchronized C4461m m17649a(Context context) {
        C4461m c4461m;
        synchronized (C4461m.class) {
            if (f15686b == null) {
                f15686b = new C4461m(context);
            }
            c4461m = f15686b;
        }
        return c4461m;
    }

    /* renamed from: a */
    public static synchronized C4461m m17648a() {
        C4461m c4461m;
        synchronized (C4461m.class) {
            c4461m = f15686b;
        }
        return c4461m;
    }

    /* renamed from: b */
    private synchronized boolean m17655b(int i) {
        boolean z;
        try {
            List<C4458l> c = m17658c(i);
            if (c == null) {
                z = false;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                List arrayList = new ArrayList();
                Collection arrayList2 = new ArrayList();
                for (C4458l c4458l : c) {
                    if (c4458l.f15674b != null && c4458l.f15674b.equalsIgnoreCase(this.f15688d) && c4458l.f15676d > 0) {
                        arrayList.add(c4458l);
                    }
                    if (c4458l.f15675c + 86400000 < currentTimeMillis) {
                        arrayList2.add(c4458l);
                    }
                }
                Collections.sort(arrayList);
                if (arrayList.size() < 2) {
                    c.removeAll(arrayList2);
                    m17652a(i, (List) c);
                    z = false;
                } else if (arrayList.size() <= 0 || ((C4458l) arrayList.get(arrayList.size() - 1)).f15675c + 86400000 >= currentTimeMillis) {
                    z = true;
                } else {
                    c.clear();
                    m17652a(i, (List) c);
                    z = false;
                }
            }
        } catch (Exception e) {
            C4475w.m17753e("isFrequentCrash failed", new Object[0]);
            z = false;
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized void m17659a(int i, final int i2) {
        C4474v.m17740a().m17741a(new Runnable(this, 1004) {
            /* renamed from: c */
            private /* synthetic */ C4461m f15682c;

            public final void run() {
                try {
                    if (!TextUtils.isEmpty(this.f15682c.f15688d)) {
                        C4458l c4458l;
                        C4458l c4458l2;
                        List a = this.f15682c.m17658c(1004);
                        List arrayList;
                        if (a == null) {
                            arrayList = new ArrayList();
                        } else {
                            arrayList = a;
                        }
                        if (this.f15682c.f15689e.get(Integer.valueOf(1004)) == null) {
                            this.f15682c.f15689e.put(Integer.valueOf(1004), new HashMap());
                        }
                        if (((Map) this.f15682c.f15689e.get(Integer.valueOf(1004))).get(this.f15682c.f15688d) == null) {
                            C4458l c4458l3 = new C4458l();
                            c4458l3.f15673a = (long) 1004;
                            c4458l3.f15679g = C4461m.f15685a;
                            c4458l3.f15674b = this.f15682c.f15688d;
                            c4458l3.f15678f = C4417a.m17304b().f15295j;
                            C4417a.m17304b().getClass();
                            c4458l3.f15677e = "2.4.0";
                            c4458l3.f15675c = System.currentTimeMillis();
                            c4458l3.f15676d = i2;
                            ((Map) this.f15682c.f15689e.get(Integer.valueOf(1004))).put(this.f15682c.f15688d, c4458l3);
                            c4458l = c4458l3;
                        } else {
                            c4458l2 = (C4458l) ((Map) this.f15682c.f15689e.get(Integer.valueOf(1004))).get(this.f15682c.f15688d);
                            c4458l2.f15676d = i2;
                            c4458l = c4458l2;
                        }
                        Collection arrayList2 = new ArrayList();
                        int i = 0;
                        for (C4458l c4458l22 : r4) {
                            if (c4458l22.f15679g == c4458l.f15679g && c4458l22.f15674b != null && c4458l22.f15674b.equalsIgnoreCase(c4458l.f15674b)) {
                                i = 1;
                                c4458l22.f15676d = c4458l.f15676d;
                            }
                            if ((c4458l22.f15677e != null && !c4458l22.f15677e.equalsIgnoreCase(c4458l.f15677e)) || ((c4458l22.f15678f != null && !c4458l22.f15678f.equalsIgnoreCase(c4458l.f15678f)) || c4458l22.f15676d <= 0)) {
                                arrayList2.add(c4458l22);
                            }
                        }
                        r4.removeAll(arrayList2);
                        if (i == 0) {
                            r4.add(c4458l);
                        }
                        this.f15682c.m17652a(1004, (List) r4);
                    }
                } catch (Exception e) {
                    C4475w.m17753e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    /* renamed from: c */
    private synchronized <T extends List<?>> T m17658c(int i) {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2;
        T t;
        Throwable th;
        try {
            File file = new File(this.f15687c.getDir("crashrecord", 0), i);
            if (file.exists()) {
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(file));
                } catch (IOException e) {
                    objectInputStream2 = null;
                    try {
                        C4475w.m17747a("open record file error", new Object[0]);
                        if (objectInputStream2 != null) {
                            objectInputStream2.close();
                        }
                        t = null;
                        return t;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        objectInputStream = objectInputStream2;
                        th = th3;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        throw th;
                    }
                } catch (ClassNotFoundException e2) {
                    objectInputStream = null;
                    try {
                        C4475w.m17747a("get object error", new Object[0]);
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        t = null;
                        return t;
                    } catch (Throwable th4) {
                        th = th4;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    objectInputStream = null;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
                try {
                    List list = (List) objectInputStream.readObject();
                    objectInputStream.close();
                } catch (IOException e3) {
                    objectInputStream2 = objectInputStream;
                    C4475w.m17747a("open record file error", new Object[0]);
                    if (objectInputStream2 != null) {
                        objectInputStream2.close();
                    }
                    t = null;
                    return t;
                } catch (ClassNotFoundException e4) {
                    C4475w.m17747a("get object error", new Object[0]);
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    t = null;
                    return t;
                }
            }
            t = null;
        } catch (Exception e5) {
            C4475w.m17753e("readCrashRecord error", new Object[0]);
        }
        return t;
    }

    /* renamed from: a */
    private synchronized <T extends List<?>> void m17652a(int i, T t) {
        ObjectOutputStream objectOutputStream;
        IOException e;
        Throwable th;
        if (t != null) {
            try {
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(this.f15687c.getDir("crashrecord", 0), i)));
                    try {
                        objectOutputStream.writeObject(t);
                        objectOutputStream.close();
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            C4475w.m17747a("open record file error", new Object[0]);
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            throw th;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    objectOutputStream = null;
                    e.printStackTrace();
                    C4475w.m17747a("open record file error", new Object[0]);
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectOutputStream = null;
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                C4475w.m17753e("writeCrashRecord error", new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public final synchronized boolean m17660a(final int i) {
        boolean z = true;
        synchronized (this) {
            try {
                z = this.f15690f.getBoolean(i + "_" + this.f15688d, true);
                C4474v.m17740a().m17741a(new Runnable(this) {
                    /* renamed from: b */
                    private /* synthetic */ C4461m f15684b;

                    public final void run() {
                        this.f15684b.f15690f.edit().putBoolean(i + "_" + this.f15684b.f15688d, !this.f15684b.m17655b(i)).commit();
                    }
                });
            } catch (Exception e) {
                C4475w.m17753e("canInit error", new Object[0]);
            }
        }
        return z;
    }
}
