package com.mob.commons.appcollector;

import android.content.Context;
import android.os.Build.VERSION;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mob.commons.C4226a;
import com.mob.commons.C4240c;
import com.mob.commons.C4241d;
import com.mob.commons.C4245e;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.ReflectHelper;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RuntimeCollector {
    /* renamed from: a */
    private static final String f14889a = (VERSION.SDK_INT >= 16 ? "^u\\d+_a\\d+" : "^app_\\d+");
    /* renamed from: b */
    private static RuntimeCollector f14890b;
    /* renamed from: c */
    private Context f14891c;

    /* renamed from: com.mob.commons.appcollector.RuntimeCollector$1 */
    class C42321 extends Thread {
        /* renamed from: a */
        final /* synthetic */ RuntimeCollector f14888a;

        /* renamed from: com.mob.commons.appcollector.RuntimeCollector$1$1 */
        class C42311 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C42321 f14887a;

            C42311(C42321 c42321) {
                this.f14887a = c42321;
            }

            public void run() {
                if (!C4241d.m16854a(this.f14887a.f14888a.f14891c, "comm/tags/.rcTag")) {
                    this.f14887a.f14888a.m16817b();
                }
            }
        }

        C42321(RuntimeCollector runtimeCollector) {
            this.f14888a = runtimeCollector;
        }

        public void run() {
            C4245e.m16870a(new File(C4275R.getCacheRoot(this.f14888a.f14891c), "comm/locks/.rc_lock"), new C42311(this));
        }
    }

    public static synchronized void startCollector(Context context, String str) {
        synchronized (RuntimeCollector.class) {
            startCollector(context);
        }
    }

    public static synchronized void startCollector(Context context) {
        synchronized (RuntimeCollector.class) {
            if (f14890b == null) {
                f14890b = new RuntimeCollector(context);
                f14890b.m16811a();
            }
        }
    }

    private RuntimeCollector(Context context) {
        this.f14891c = context.getApplicationContext();
    }

    /* renamed from: a */
    private void m16811a() {
        Thread c42321 = new C42321(this);
        c42321.setPriority(1);
        c42321.start();
    }

    /* renamed from: b */
    private void m16817b() {
        Process exec;
        Process process;
        Throwable th;
        Object obj;
        OutputStream outputStream;
        long j;
        String str;
        Object obj2;
        long d;
        OutputStream outputStream2;
        Object obj3;
        OutputStream outputStream3 = null;
        File file = new File(C4275R.getCacheRoot(this.f14891c), "comm/dbs/.plst");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        String absolutePath = file.getAbsolutePath();
        long c = m16820c();
        C4241d.m16855b(this.f14891c, "comm/tags/.rcTag");
        try {
            exec = Runtime.getRuntime().exec("sh");
            try {
                outputStream3 = exec.getOutputStream();
                process = exec;
            } catch (Throwable th2) {
                th = th2;
                try {
                    MobLog.getInstance().m16946w(th);
                    process = exec;
                    C4241d.m16856c(this.f14891c, "comm/tags/.rcTag");
                    obj = 1;
                    outputStream = outputStream3;
                    j = c;
                    while (true) {
                        try {
                            if (C4226a.m16770b(this.f14891c)) {
                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                                c = C4226a.m16764a(this.f14891c);
                                outputStream.write(("top -d 0 -n 1 | grep -E -v 'root|shell|system' >> " + absolutePath + " && echo \"" + "======================" + "\" >> " + absolutePath + "\n").getBytes("ascii"));
                                if (obj != null) {
                                    str = "echo \"" + c + "_" + C4226a.m16771c(this.f14891c) + "\" >> " + absolutePath + "\n";
                                } else {
                                    str = "echo \"" + c + "_0\" >> " + absolutePath + "\n";
                                    obj = null;
                                }
                                outputStream.write(str.getBytes("ascii"));
                                outputStream.flush();
                                if (c >= j) {
                                    outputStream.write("exit\n".getBytes("ascii"));
                                    outputStream.flush();
                                    outputStream.close();
                                    process.waitFor();
                                    process.destroy();
                                    if (m16815a(absolutePath)) {
                                        c = j;
                                        obj2 = obj;
                                    } else {
                                        d = m16821d();
                                        if (d <= 0) {
                                            d = j;
                                        }
                                        obj2 = 1;
                                        c = d;
                                    }
                                    try {
                                        C4241d.m16855b(this.f14891c, "comm/tags/.rcTag");
                                        try {
                                            exec = Runtime.getRuntime().exec("sh");
                                            try {
                                                process = exec;
                                                outputStream2 = exec.getOutputStream();
                                            } catch (Throwable th3) {
                                                th = th3;
                                                try {
                                                    MobLog.getInstance().m16946w(th);
                                                    process = exec;
                                                    outputStream2 = outputStream;
                                                    C4241d.m16856c(this.f14891c, "comm/tags/.rcTag");
                                                    obj3 = obj2;
                                                    outputStream3 = outputStream2;
                                                    obj = obj3;
                                                    Thread.sleep((long) (C4226a.m16771c(this.f14891c) * 1000));
                                                    outputStream = outputStream3;
                                                    j = c;
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    process = exec;
                                                    obj = obj2;
                                                    outputStream3 = outputStream;
                                                    MobLog.getInstance().m16934d(th);
                                                    outputStream = outputStream3;
                                                    j = c;
                                                }
                                            }
                                        } catch (Throwable th5) {
                                            th = th5;
                                            exec = process;
                                            MobLog.getInstance().m16946w(th);
                                            process = exec;
                                            outputStream2 = outputStream;
                                            C4241d.m16856c(this.f14891c, "comm/tags/.rcTag");
                                            obj3 = obj2;
                                            outputStream3 = outputStream2;
                                            obj = obj3;
                                            Thread.sleep((long) (C4226a.m16771c(this.f14891c) * 1000));
                                            outputStream = outputStream3;
                                            j = c;
                                        }
                                        try {
                                            C4241d.m16856c(this.f14891c, "comm/tags/.rcTag");
                                            obj3 = obj2;
                                            outputStream3 = outputStream2;
                                            obj = obj3;
                                            Thread.sleep((long) (C4226a.m16771c(this.f14891c) * 1000));
                                            outputStream = outputStream3;
                                            j = c;
                                        } catch (Throwable th6) {
                                            th = th6;
                                            obj3 = obj2;
                                            outputStream3 = outputStream2;
                                            obj = obj3;
                                            MobLog.getInstance().m16934d(th);
                                            outputStream = outputStream3;
                                            j = c;
                                        }
                                    } catch (Throwable th7) {
                                        th = th7;
                                        obj = obj2;
                                        outputStream3 = outputStream;
                                        MobLog.getInstance().m16934d(th);
                                        outputStream = outputStream3;
                                        j = c;
                                    }
                                }
                            }
                            c = j;
                            outputStream3 = outputStream;
                            try {
                                Thread.sleep((long) (C4226a.m16771c(this.f14891c) * 1000));
                                outputStream = outputStream3;
                                j = c;
                            } catch (Throwable th8) {
                                th = th8;
                                MobLog.getInstance().m16934d(th);
                                outputStream = outputStream3;
                                j = c;
                            }
                        } catch (Throwable th9) {
                            th = th9;
                            c = j;
                            outputStream3 = outputStream;
                            MobLog.getInstance().m16934d(th);
                            outputStream = outputStream3;
                            j = c;
                        }
                    }
                } catch (Throwable th10) {
                    return;
                }
            }
        } catch (Throwable th11) {
            th = th11;
            exec = outputStream3;
            MobLog.getInstance().m16946w(th);
            process = exec;
            C4241d.m16856c(this.f14891c, "comm/tags/.rcTag");
            obj = 1;
            outputStream = outputStream3;
            j = c;
            while (true) {
                if (C4226a.m16770b(this.f14891c)) {
                    if (file.exists()) {
                        file.createNewFile();
                    }
                    c = C4226a.m16764a(this.f14891c);
                    outputStream.write(("top -d 0 -n 1 | grep -E -v 'root|shell|system' >> " + absolutePath + " && echo \"" + "======================" + "\" >> " + absolutePath + "\n").getBytes("ascii"));
                    if (obj != null) {
                        str = "echo \"" + c + "_0\" >> " + absolutePath + "\n";
                        obj = null;
                    } else {
                        str = "echo \"" + c + "_" + C4226a.m16771c(this.f14891c) + "\" >> " + absolutePath + "\n";
                    }
                    outputStream.write(str.getBytes("ascii"));
                    outputStream.flush();
                    if (c >= j) {
                        outputStream.write("exit\n".getBytes("ascii"));
                        outputStream.flush();
                        outputStream.close();
                        process.waitFor();
                        process.destroy();
                        if (m16815a(absolutePath)) {
                            d = m16821d();
                            if (d <= 0) {
                                d = j;
                            }
                            obj2 = 1;
                            c = d;
                        } else {
                            c = j;
                            obj2 = obj;
                        }
                        C4241d.m16855b(this.f14891c, "comm/tags/.rcTag");
                        exec = Runtime.getRuntime().exec("sh");
                        process = exec;
                        outputStream2 = exec.getOutputStream();
                        C4241d.m16856c(this.f14891c, "comm/tags/.rcTag");
                        obj3 = obj2;
                        outputStream3 = outputStream2;
                        obj = obj3;
                        Thread.sleep((long) (C4226a.m16771c(this.f14891c) * 1000));
                        outputStream = outputStream3;
                        j = c;
                    }
                }
                c = j;
                outputStream3 = outputStream;
                Thread.sleep((long) (C4226a.m16771c(this.f14891c) * 1000));
                outputStream = outputStream3;
                j = c;
            }
        }
        C4241d.m16856c(this.f14891c, "comm/tags/.rcTag");
        obj = 1;
        outputStream = outputStream3;
        j = c;
        while (true) {
            if (C4226a.m16770b(this.f14891c)) {
                if (file.exists()) {
                    file.createNewFile();
                }
                c = C4226a.m16764a(this.f14891c);
                outputStream.write(("top -d 0 -n 1 | grep -E -v 'root|shell|system' >> " + absolutePath + " && echo \"" + "======================" + "\" >> " + absolutePath + "\n").getBytes("ascii"));
                if (obj != null) {
                    str = "echo \"" + c + "_0\" >> " + absolutePath + "\n";
                    obj = null;
                } else {
                    str = "echo \"" + c + "_" + C4226a.m16771c(this.f14891c) + "\" >> " + absolutePath + "\n";
                }
                outputStream.write(str.getBytes("ascii"));
                outputStream.flush();
                if (c >= j) {
                    outputStream.write("exit\n".getBytes("ascii"));
                    outputStream.flush();
                    outputStream.close();
                    process.waitFor();
                    process.destroy();
                    if (m16815a(absolutePath)) {
                        d = m16821d();
                        if (d <= 0) {
                            d = j;
                        }
                        obj2 = 1;
                        c = d;
                    } else {
                        c = j;
                        obj2 = obj;
                    }
                    C4241d.m16855b(this.f14891c, "comm/tags/.rcTag");
                    exec = Runtime.getRuntime().exec("sh");
                    process = exec;
                    outputStream2 = exec.getOutputStream();
                    C4241d.m16856c(this.f14891c, "comm/tags/.rcTag");
                    obj3 = obj2;
                    outputStream3 = outputStream2;
                    obj = obj3;
                    Thread.sleep((long) (C4226a.m16771c(this.f14891c) * 1000));
                    outputStream = outputStream3;
                    j = c;
                }
            }
            c = j;
            outputStream3 = outputStream;
            Thread.sleep((long) (C4226a.m16771c(this.f14891c) * 1000));
            outputStream = outputStream3;
            j = c;
        }
    }

    /* renamed from: c */
    private long m16820c() {
        long a = C4226a.m16764a(this.f14891c);
        try {
            File file = new File(C4275R.getCacheRoot(this.f14891c), "comm/dbs/.nulplt");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                return readLong;
            }
            file.createNewFile();
            m16821d();
            return a + 86400000;
        } catch (Throwable th) {
            MobLog.getInstance().m16934d(th);
            return a + 86400000;
        }
    }

    /* renamed from: d */
    private long m16821d() {
        long a = C4226a.m16764a(this.f14891c) + 86400000;
        try {
            File file = new File(C4275R.getCacheRoot(this.f14891c), "comm/dbs/.nulplt");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeLong(a);
            dataOutputStream.flush();
            dataOutputStream.close();
            return a;
        } catch (Throwable th) {
            MobLog.getInstance().m16934d(th);
            return 0;
        }
    }

    /* renamed from: a */
    private boolean m16815a(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        m16812a(str, arrayList, arrayList2);
        m16814a(m16809a(m16816a(m16810a(arrayList), arrayList), arrayList2), arrayList2);
        return m16819b(str);
    }

    /* renamed from: a */
    private void m16812a(String str, ArrayList<ArrayList<HashMap<String, String>>> arrayList, ArrayList<long[]> arrayList2) {
        try {
            HashMap e = m16822e();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str), "utf-8"));
            String readLine = bufferedReader.readLine();
            for (int i = 0; i < 7; i++) {
                readLine = bufferedReader.readLine();
            }
            ArrayList arrayList3 = new ArrayList();
            for (readLine = 
/*
Method generation error in method: com.mob.commons.appcollector.RuntimeCollector.a(java.lang.String, java.util.ArrayList, java.util.ArrayList):void, dex: classes2.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r1_2 'readLine' java.lang.String) = (r1_1 'readLine' java.lang.String), (r1_3 'readLine' java.lang.String) binds: {(r1_1 'readLine' java.lang.String)=B:2:?, (r1_3 'readLine' java.lang.String)=B:4:0x001e} in method: com.mob.commons.appcollector.RuntimeCollector.a(java.lang.String, java.util.ArrayList, java.util.ArrayList):void, dex: classes2.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:279)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 21 more

*/

            /* renamed from: a */
            private void m16813a(String str, HashMap<String, String[]> hashMap, ArrayList<HashMap<String, String>> arrayList) {
                String[] split = str.replaceAll(" +", " ").split(" ");
                if (split != null && split.length >= 10) {
                    Object obj = split[split.length - 1];
                    if (split[split.length - 2].matches(f14889a) && !Property.TEXT_ANCHOR_TOP.equals(obj)) {
                        String[] strArr = (String[]) hashMap.get(obj);
                        if (strArr != null) {
                            HashMap hashMap2 = new HashMap();
                            hashMap2.put("pkg", obj);
                            hashMap2.put("name", strArr[0]);
                            hashMap2.put(MapboxEvent.ATTRIBUTE_VERSION, strArr[1]);
                            hashMap2.put("pcy", split[split.length - 3]);
                            arrayList.add(hashMap2);
                        }
                    }
                }
            }

            /* renamed from: e */
            private HashMap<String, String[]> m16822e() {
                ArrayList arrayList;
                try {
                    Object[] objArr = new Object[]{Boolean.valueOf(false)};
                    arrayList = (ArrayList) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14891c), "getInstalledApp", objArr);
                } catch (Throwable th) {
                    MobLog.getInstance().m16946w(th);
                    arrayList = new ArrayList();
                }
                HashMap<String, String[]> hashMap = new HashMap();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    hashMap.put((String) ((HashMap) it.next()).get("pkg"), new String[]{(String) r0.get("name"), (String) ((HashMap) it.next()).get(MapboxEvent.ATTRIBUTE_VERSION)});
                }
                return hashMap;
            }

            /* renamed from: a */
            private HashMap<String, Integer> m16810a(ArrayList<ArrayList<HashMap<String, String>>> arrayList) {
                HashMap<String, Integer> hashMap = new HashMap();
                Iterator it = arrayList.iterator();
                int i = 0;
                while (it.hasNext()) {
                    Iterator it2 = ((ArrayList) it.next()).iterator();
                    int i2 = i;
                    while (it2.hasNext()) {
                        HashMap hashMap2 = (HashMap) it2.next();
                        String str = ((String) hashMap2.get("pkg")) + ":" + ((String) hashMap2.get(MapboxEvent.ATTRIBUTE_VERSION));
                        if (!hashMap.containsKey(str)) {
                            hashMap.put(str, Integer.valueOf(i2));
                            i2++;
                        }
                    }
                    i = i2;
                }
                return hashMap;
            }

            /* renamed from: a */
            private HashMap<String, String>[][] m16816a(HashMap<String, Integer> hashMap, ArrayList<ArrayList<HashMap<String, String>>> arrayList) {
                HashMap[][] hashMapArr = (HashMap[][]) Array.newInstance(HashMap.class, new int[]{hashMap.size(), arrayList.size()});
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ArrayList arrayList2 = (ArrayList) arrayList.get(i);
                    int size2 = arrayList2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        HashMap hashMap2 = (HashMap) arrayList2.get(i2);
                        hashMapArr[((Integer) hashMap.get(((String) hashMap2.get("pkg")) + ":" + ((String) hashMap2.get(MapboxEvent.ATTRIBUTE_VERSION)))).intValue()][i] = hashMap2;
                    }
                }
                return hashMapArr;
            }

            /* renamed from: a */
            private ArrayList<HashMap<String, Object>> m16809a(HashMap<String, String>[][] hashMapArr, ArrayList<long[]> arrayList) {
                ArrayList<HashMap<String, Object>> arrayList2 = new ArrayList(hashMapArr.length);
                for (HashMap<String, String>[] hashMapArr2 : hashMapArr) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("runtimes", Long.valueOf(0));
                    hashMap.put("fg", Integer.valueOf(0));
                    hashMap.put("bg", Integer.valueOf(0));
                    hashMap.put("empty", Integer.valueOf(0));
                    arrayList2.add(hashMap);
                    int length = hashMapArr2.length - 1;
                    while (length >= 0) {
                        if (hashMapArr2[length] != null) {
                            hashMap.put("runtimes", Long.valueOf((length == 0 ? 0 : ((long[]) arrayList.get(length))[1]) + ((Long) C4275R.forceCast(hashMap.get("runtimes"), Long.valueOf(0))).longValue()));
                            if ("fg".equals(hashMapArr2[length].get("pcy"))) {
                                hashMap.put("fg", Integer.valueOf(((Integer) C4275R.forceCast(hashMap.get("fg"), Integer.valueOf(0))).intValue() + 1));
                            } else if ("bg".equals(hashMapArr2[length].get("pcy"))) {
                                hashMap.put("bg", Integer.valueOf(((Integer) C4275R.forceCast(hashMap.get("bg"), Integer.valueOf(0))).intValue() + 1));
                            } else {
                                hashMap.put("empty", Integer.valueOf(((Integer) C4275R.forceCast(hashMap.get("empty"), Integer.valueOf(0))).intValue() + 1));
                            }
                            hashMap.put("pkg", hashMapArr2[length].get("pkg"));
                            hashMap.put("name", hashMapArr2[length].get("name"));
                            hashMap.put(MapboxEvent.ATTRIBUTE_VERSION, hashMapArr2[length].get(MapboxEvent.ATTRIBUTE_VERSION));
                        }
                        length--;
                    }
                }
                return arrayList2;
            }

            /* renamed from: a */
            private void m16814a(ArrayList<HashMap<String, Object>> arrayList, ArrayList<long[]> arrayList2) {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "APP_RUNTIMES");
                hashMap.put("list", arrayList);
                hashMap.put("datatime", Long.valueOf(C4226a.m16764a(this.f14891c)));
                hashMap.put("recordat", Long.valueOf(((long[]) arrayList2.get(0))[0]));
                long j = 0;
                for (int i = 1; i < arrayList2.size(); i++) {
                    j += ((long[]) arrayList2.get(i))[1];
                }
                hashMap.put("sdk_runtime_len", Long.valueOf(j));
                hashMap.put("top_count", Integer.valueOf(arrayList2.size()));
                C4240c.m16840a(this.f14891c).m16853a(C4226a.m16764a(this.f14891c), hashMap);
            }

            /* renamed from: b */
            private boolean m16819b(String str) {
                try {
                    File file = new File(str);
                    file.delete();
                    file.createNewFile();
                    return true;
                } catch (Throwable th) {
                    MobLog.getInstance().m16934d(th);
                    return false;
                }
            }
        }
