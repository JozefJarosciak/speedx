package com.mob.commons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.alipay.sdk.packet.C0861d;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.MobRSA;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.SQLiteHelper;
import com.mob.tools.utils.SQLiteHelper.SingleTableDB;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

/* compiled from: DataHeap */
/* renamed from: com.mob.commons.c */
public class C4240c implements Callback {
    /* renamed from: a */
    private static C4240c f14908a;
    /* renamed from: b */
    private Context f14909b;
    /* renamed from: c */
    private Handler f14910c;
    /* renamed from: d */
    private SingleTableDB f14911d;
    /* renamed from: e */
    private Hashon f14912e = new Hashon();
    /* renamed from: f */
    private Random f14913f = new Random();

    /* compiled from: DataHeap */
    /* renamed from: com.mob.commons.c$2 */
    class C42392 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4240c f14907a;

        C42392(C4240c c4240c) {
            this.f14907a = c4240c;
        }

        public void run() {
            ArrayList d = this.f14907a.m16847b();
            if (d.size() > 0 && this.f14907a.m16844a(d)) {
                this.f14907a.m16850b(d);
            }
        }
    }

    /* renamed from: a */
    public static synchronized C4240c m16840a(Context context) {
        C4240c c4240c;
        synchronized (C4240c.class) {
            if (f14908a == null) {
                f14908a = new C4240c(context);
            }
            c4240c = f14908a;
        }
        return c4240c;
    }

    private C4240c(Context context) {
        this.f14909b = context.getApplicationContext();
        MobHandlerThread mobHandlerThread = new MobHandlerThread();
        mobHandlerThread.start();
        this.f14910c = new Handler(mobHandlerThread.getLooper(), this);
        File file = new File(C4275R.getCacheRoot(context), "comm/dbs/.dh");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        this.f14911d = SQLiteHelper.getDatabase(file.getAbsolutePath(), "DataHeap_1");
        this.f14911d.addField("time", DirectionsCriteria.INSTRUCTIONS_TEXT, true);
        this.f14911d.addField(C0861d.f2139k, DirectionsCriteria.INSTRUCTIONS_TEXT, true);
        this.f14910c.sendEmptyMessage(1);
    }

    /* renamed from: a */
    public synchronized void m16853a(long j, HashMap<String, Object> hashMap) {
        Message message = new Message();
        message.what = 2;
        message.obj = new Object[]{Long.valueOf(j), hashMap};
        this.f14910c.sendMessage(message);
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                m16842a();
                this.f14910c.sendEmptyMessageDelayed(1, AbstractComponentTracker.LINGERING_TIMEOUT);
                break;
            case 2:
                Object[] objArr = (Object[]) message.obj;
                long longValue = ((Long) C4275R.forceCast(objArr[0], Long.valueOf(-1))).longValue();
                if (longValue > 0) {
                    m16848b(longValue, (HashMap) objArr[1]);
                    break;
                }
                break;
        }
        return false;
    }

    /* renamed from: b */
    private void m16848b(final long j, final HashMap<String, Object> hashMap) {
        C4245e.m16871a(new File(C4275R.getCacheRoot(this.f14909b), "comm/locks/.dhlock"), true, new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C4240c f14906c;

            public void run() {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("time", String.valueOf(j));
                    contentValues.put(C0861d.f2139k, Base64.encodeToString(Data.AES128Encode(Data.rawMD5(String.valueOf(ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14906c.f14909b), "getManufacturer", new Object[0]))), this.f14906c.f14912e.fromHashMap(hashMap).getBytes("utf-8")), 2));
                    SQLiteHelper.insert(this.f14906c.f14911d, contentValues);
                } catch (Throwable th) {
                    MobLog.getInstance().m16946w(th);
                }
            }
        });
    }

    /* renamed from: a */
    private void m16842a() {
        Object obj;
        try {
            obj = (String) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14909b), "getNetworkType", new Object[0]);
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
            obj = null;
        }
        if (obj != null && !"none".equals(obj)) {
            C4245e.m16871a(new File(C4275R.getCacheRoot(this.f14909b), "comm/locks/.dhlock"), true, new C42392(this));
        }
    }

    /* renamed from: b */
    private ArrayList<String[]> m16847b() {
        ArrayList<String[]> arrayList = new ArrayList();
        try {
            Cursor query = SQLiteHelper.query(this.f14911d, new String[]{"time", C0861d.f2139k}, null, null, null);
            if (query != null) {
                if (query.moveToFirst()) {
                    long a = C4226a.m16764a(this.f14909b);
                    do {
                        Object obj = new String[]{query.getString(0), query.getString(1)};
                        long j = -1;
                        try {
                            j = Long.parseLong(obj[0]);
                        } catch (Throwable th) {
                        }
                        if (j <= a) {
                            arrayList.add(obj);
                        }
                    } while (query.moveToNext());
                }
                query.close();
            }
        } catch (Throwable th2) {
            MobLog.getInstance().m16946w(th2);
        }
        return arrayList;
    }

    /* renamed from: a */
    private boolean m16844a(ArrayList<String[]> arrayList) {
        try {
            C4237b a = C4237b.m16835a(this.f14909b);
            ArrayList a2 = a.m16837a();
            if (a2.isEmpty()) {
                return false;
            }
            HashMap hashMap = new HashMap();
            Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14909b);
            hashMap.put("plat", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getPlatformCode", new Object[0]));
            hashMap.put(C0861d.f2142n, ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getDeviceKey", new Object[0]));
            hashMap.put("mac", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getMacAddress", new Object[0]));
            hashMap.put(MapboxEvent.ATTRIBUTE_MODEL, ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getModel", new Object[0]));
            hashMap.put("duid", DeviceAuthorizer.authorize(this.f14909b, null));
            hashMap.put("imei", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getIMEI", new Object[0]));
            hashMap.put("serialno", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getSerialno", new Object[0]));
            hashMap.put("networktype", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getDetailNetworkTypeForStatic", new Object[0]));
            ArrayList arrayList2 = new ArrayList();
            byte[] rawMD5 = Data.rawMD5(String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getManufacturer", new Object[0])));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(this.f14912e.fromJson(new String(Data.AES128Decode(rawMD5, Base64.decode(((String[]) it.next())[1], 2)), "utf-8").trim()));
            }
            hashMap.put("datas", arrayList2);
            arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("appkey", ((MobProduct) a2.get(0)).getProductAppkey()));
            arrayList2.add(new KVPair(ANSIConstants.ESC_END, m16841a(this.f14912e.fromHashMap(hashMap))));
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new KVPair("User-Identity", a.m16836a(a2)));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            boolean equals = "200".equals(String.valueOf(this.f14912e.fromJson(a.httpPost(C4240c.m16846b(this.f14909b), arrayList2, null, arrayList3, networkTimeOut)).get("status")));
            if (equals) {
                return equals;
            }
            C4250f.m16890e(this.f14909b, null);
            return equals;
        } catch (Throwable th) {
            C4250f.m16890e(this.f14909b, null);
            MobLog.getInstance().m16946w(th);
            return false;
        }
    }

    /* renamed from: a */
    private String m16841a(String str) throws Throwable {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeLong(this.f14913f.nextLong());
        dataOutputStream.writeLong(this.f14913f.nextLong());
        dataOutputStream.flush();
        dataOutputStream.close();
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream2);
        gZIPOutputStream.write(str.getBytes("utf-8"));
        gZIPOutputStream.flush();
        gZIPOutputStream.close();
        byte[] AES128Encode = Data.AES128Encode(toByteArray, byteArrayOutputStream2.toByteArray());
        toByteArray = new MobRSA(1024).encode(toByteArray, new BigInteger("ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", 16), new BigInteger("191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd", 16));
        OutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream3);
        dataOutputStream2.writeInt(toByteArray.length);
        dataOutputStream2.write(toByteArray);
        dataOutputStream2.writeInt(AES128Encode.length);
        dataOutputStream2.write(AES128Encode);
        dataOutputStream2.flush();
        dataOutputStream2.close();
        return Base64.encodeToString(byteArrayOutputStream3.toByteArray(), 2);
    }

    /* renamed from: b */
    private void m16850b(ArrayList<String[]> arrayList) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String[] strArr = (String[]) it.next();
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(CoreConstants.SINGLE_QUOTE_CHAR).append(strArr[0]).append(CoreConstants.SINGLE_QUOTE_CHAR);
            }
            SQLiteHelper.delete(this.f14911d, "time in (" + stringBuilder.toString() + ")", null);
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
        }
    }

    /* renamed from: b */
    private static String m16846b(Context context) {
        String str = null;
        try {
            str = C4250f.m16891f(context);
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
        }
        return TextUtils.isEmpty(str) ? "http://c.data.mob.com/v2/cdata" : str;
    }
}
