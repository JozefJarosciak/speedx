package com.tencent.wxop.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.tencent.wxop.stat.common.C4529a;
import com.tencent.wxop.stat.common.C4533e;
import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.C4545q;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.p201a.C4513e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public class au {
    /* renamed from: h */
    private static StatLogger f15994h = C4539k.m18052b();
    /* renamed from: i */
    private static Context f15995i = null;
    /* renamed from: j */
    private static au f15996j = null;
    /* renamed from: a */
    volatile int f15997a = 0;
    /* renamed from: b */
    C4529a f15998b = null;
    /* renamed from: c */
    private bc f15999c = null;
    /* renamed from: d */
    private bc f16000d = null;
    /* renamed from: e */
    private C4533e f16001e = null;
    /* renamed from: f */
    private String f16002f = "";
    /* renamed from: g */
    private String f16003g = "";
    /* renamed from: k */
    private int f16004k = 0;
    /* renamed from: l */
    private ConcurrentHashMap<C4513e, String> f16005l = null;
    /* renamed from: m */
    private boolean f16006m = false;
    /* renamed from: n */
    private HashMap<String, String> f16007n = new HashMap();

    private au(Context context) {
        try {
            this.f16001e = new C4533e();
            f15995i = context.getApplicationContext();
            this.f16005l = new ConcurrentHashMap();
            this.f16002f = C4539k.m18076r(context);
            this.f16003g = "pri_" + C4539k.m18076r(context);
            this.f15999c = new bc(f15995i, this.f16002f);
            this.f16000d = new bc(f15995i, this.f16003g);
            m17980a(true);
            m17980a(false);
            m17990f();
            m18001b(f15995i);
            m18003d();
            m17994j();
        } catch (Throwable th) {
            f15994h.m18011e(th);
        }
    }

    /* renamed from: a */
    public static au m17968a(Context context) {
        if (f15996j == null) {
            synchronized (au.class) {
                if (f15996j == null) {
                    f15996j = new au(context);
                }
            }
        }
        return f15996j;
    }

    /* renamed from: a */
    private String m17969a(List<bd> list) {
        StringBuilder stringBuilder = new StringBuilder(list.size() * 3);
        stringBuilder.append("event_id in (");
        int size = list.size();
        int i = 0;
        for (bd bdVar : list) {
            stringBuilder.append(bdVar.f16033a);
            if (i != size - 1) {
                stringBuilder.append(",");
            }
            i++;
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: a */
    private synchronized void m17970a(int i, boolean z) {
        try {
            if (this.f15997a > 0 && i > 0 && !StatServiceImpl.m17881a()) {
                if (StatConfig.isDebugEnable()) {
                    f15994h.m18012i("Load " + this.f15997a + " unsent events");
                }
                List arrayList = new ArrayList(i);
                m17986b(arrayList, i, z);
                if (arrayList.size() > 0) {
                    if (StatConfig.isDebugEnable()) {
                        f15994h.m18012i("Peek " + arrayList.size() + " unsent events.");
                    }
                    m17978a(arrayList, 2, z);
                    C4551i.m18122b(f15995i).m18125b(arrayList, new ba(this, arrayList, z));
                }
            }
        } catch (Throwable th) {
            f15994h.m18011e(th);
        }
    }

    /* renamed from: a */
    private void m17971a(C4513e c4513e, C4526h c4526h, boolean z) {
        long insert;
        long j;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = m17987c(z);
            sQLiteDatabase.beginTransaction();
            if (!z && this.f15997a > StatConfig.getMaxStoreEventCount()) {
                f15994h.warn("Too many events stored in db.");
                this.f15997a -= this.f15999c.getWritableDatabase().delete("events", "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)", null);
            }
            ContentValues contentValues = new ContentValues();
            String g = c4513e.m17909g();
            if (StatConfig.isDebugEnable()) {
                f15994h.m18012i("insert 1 event, content:" + g);
            }
            contentValues.put("content", C4545q.m18103b(g));
            contentValues.put("send_count", "0");
            contentValues.put("status", Integer.toString(1));
            contentValues.put("timestamp", Long.valueOf(c4513e.m17905c()));
            insert = sQLiteDatabase.insert("events", null, contentValues);
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                    j = insert;
                } catch (Throwable th) {
                    f15994h.m18011e(th);
                    j = insert;
                }
                if (j <= 0) {
                    this.f15997a++;
                    if (StatConfig.isDebugEnable()) {
                        f15994h.m18009d("directStoreEvent insert event to db, event:" + c4513e.m17909g());
                    }
                    if (c4526h != null) {
                        c4526h.mo6119a();
                    }
                }
                f15994h.error("Failed to store event:" + c4513e.m17909g());
                return;
            }
        } catch (Throwable th2) {
            f15994h.m18011e(th2);
            j = -1;
        }
        j = insert;
        if (j <= 0) {
            f15994h.error("Failed to store event:" + c4513e.m17909g());
            return;
        }
        this.f15997a++;
        if (StatConfig.isDebugEnable()) {
            f15994h.m18009d("directStoreEvent insert event to db, event:" + c4513e.m17909g());
        }
        if (c4526h != null) {
            c4526h.mo6119a();
        }
    }

    /* renamed from: a */
    private synchronized void m17978a(List<bd> list, int i, boolean z) {
        SQLiteDatabase c;
        Throwable th;
        String str = null;
        synchronized (this) {
            if (list.size() != 0) {
                int b = m17981b(z);
                try {
                    String str2;
                    c = m17987c(z);
                    if (i == 2) {
                        try {
                            str2 = "update events set status=" + i + ", send_count=send_count+1  where " + m17969a((List) list);
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                f15994h.m18011e(th);
                                if (c != null) {
                                    try {
                                        c.endTransaction();
                                    } catch (Throwable th3) {
                                        f15994h.m18011e(th3);
                                    }
                                }
                            } catch (Throwable th4) {
                                th3 = th4;
                                if (c != null) {
                                    try {
                                        c.endTransaction();
                                    } catch (Throwable th5) {
                                        f15994h.m18011e(th5);
                                    }
                                }
                                throw th3;
                            }
                        }
                    }
                    str2 = "update events set status=" + i + " where " + m17969a((List) list);
                    if (this.f16004k % 3 == 0) {
                        str = "delete from events where send_count>" + b;
                    }
                    this.f16004k++;
                    if (StatConfig.isDebugEnable()) {
                        f15994h.m18012i("update sql:" + str2);
                    }
                    c.beginTransaction();
                    c.execSQL(str2);
                    if (str != null) {
                        f15994h.m18012i("update for delete sql:" + str);
                        c.execSQL(str);
                        m17990f();
                    }
                    c.setTransactionSuccessful();
                    if (c != null) {
                        try {
                            c.endTransaction();
                        } catch (Throwable th32) {
                            f15994h.m18011e(th32);
                        }
                    }
                } catch (Throwable th6) {
                    th32 = th6;
                    c = null;
                    if (c != null) {
                        c.endTransaction();
                    }
                    throw th32;
                }
            }
        }
    }

    /* renamed from: a */
    private synchronized void m17979a(java.util.List<com.tencent.wxop.stat.bd> r9, boolean r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.tencent.wxop.stat.au.a(java.util.List, boolean):void. bs: [B:26:0x00c1, B:49:0x00e9]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r8 = this;
        r1 = 0;
        monitor-enter(r8);
        r0 = r9.size();	 Catch:{ all -> 0x00ce }
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r8);
        return;
    L_0x000a:
        r0 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ all -> 0x00ce }
        if (r0 == 0) goto L_0x0032;	 Catch:{ all -> 0x00ce }
    L_0x0010:
        r0 = f15994h;	 Catch:{ all -> 0x00ce }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ce }
        r3 = "Delete ";	 Catch:{ all -> 0x00ce }
        r2.<init>(r3);	 Catch:{ all -> 0x00ce }
        r3 = r9.size();	 Catch:{ all -> 0x00ce }
        r2 = r2.append(r3);	 Catch:{ all -> 0x00ce }
        r3 = " events, important:";	 Catch:{ all -> 0x00ce }
        r2 = r2.append(r3);	 Catch:{ all -> 0x00ce }
        r2 = r2.append(r10);	 Catch:{ all -> 0x00ce }
        r2 = r2.toString();	 Catch:{ all -> 0x00ce }
        r0.m18012i(r2);	 Catch:{ all -> 0x00ce }
    L_0x0032:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ce }
        r0 = r9.size();	 Catch:{ all -> 0x00ce }
        r0 = r0 * 3;	 Catch:{ all -> 0x00ce }
        r3.<init>(r0);	 Catch:{ all -> 0x00ce }
        r0 = "event_id in (";	 Catch:{ all -> 0x00ce }
        r3.append(r0);	 Catch:{ all -> 0x00ce }
        r0 = 0;	 Catch:{ all -> 0x00ce }
        r4 = r9.size();	 Catch:{ all -> 0x00ce }
        r5 = r9.iterator();	 Catch:{ all -> 0x00ce }
        r2 = r0;	 Catch:{ all -> 0x00ce }
    L_0x004c:
        r0 = r5.hasNext();	 Catch:{ all -> 0x00ce }
        if (r0 == 0) goto L_0x006a;	 Catch:{ all -> 0x00ce }
    L_0x0052:
        r0 = r5.next();	 Catch:{ all -> 0x00ce }
        r0 = (com.tencent.wxop.stat.bd) r0;	 Catch:{ all -> 0x00ce }
        r6 = r0.f16033a;	 Catch:{ all -> 0x00ce }
        r3.append(r6);	 Catch:{ all -> 0x00ce }
        r0 = r4 + -1;	 Catch:{ all -> 0x00ce }
        if (r2 == r0) goto L_0x0066;	 Catch:{ all -> 0x00ce }
    L_0x0061:
        r0 = ",";	 Catch:{ all -> 0x00ce }
        r3.append(r0);	 Catch:{ all -> 0x00ce }
    L_0x0066:
        r0 = r2 + 1;	 Catch:{ all -> 0x00ce }
        r2 = r0;	 Catch:{ all -> 0x00ce }
        goto L_0x004c;	 Catch:{ all -> 0x00ce }
    L_0x006a:
        r0 = ")";	 Catch:{ all -> 0x00ce }
        r3.append(r0);	 Catch:{ all -> 0x00ce }
        r1 = r8.m17987c(r10);	 Catch:{ Throwable -> 0x00d1 }
        r1.beginTransaction();	 Catch:{ Throwable -> 0x00d1 }
        r0 = "events";	 Catch:{ Throwable -> 0x00d1 }
        r2 = r3.toString();	 Catch:{ Throwable -> 0x00d1 }
        r5 = 0;	 Catch:{ Throwable -> 0x00d1 }
        r0 = r1.delete(r0, r2, r5);	 Catch:{ Throwable -> 0x00d1 }
        r2 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x00d1 }
        if (r2 == 0) goto L_0x00b3;	 Catch:{ Throwable -> 0x00d1 }
    L_0x0087:
        r2 = f15994h;	 Catch:{ Throwable -> 0x00d1 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d1 }
        r6 = "delete ";	 Catch:{ Throwable -> 0x00d1 }
        r5.<init>(r6);	 Catch:{ Throwable -> 0x00d1 }
        r4 = r5.append(r4);	 Catch:{ Throwable -> 0x00d1 }
        r5 = " event ";	 Catch:{ Throwable -> 0x00d1 }
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x00d1 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00d1 }
        r3 = r4.append(r3);	 Catch:{ Throwable -> 0x00d1 }
        r4 = ", success delete:";	 Catch:{ Throwable -> 0x00d1 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00d1 }
        r3 = r3.append(r0);	 Catch:{ Throwable -> 0x00d1 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00d1 }
        r2.m18012i(r3);	 Catch:{ Throwable -> 0x00d1 }
    L_0x00b3:
        r2 = r8.f15997a;	 Catch:{ Throwable -> 0x00d1 }
        r0 = r2 - r0;	 Catch:{ Throwable -> 0x00d1 }
        r8.f15997a = r0;	 Catch:{ Throwable -> 0x00d1 }
        r1.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00d1 }
        r8.m17990f();	 Catch:{ Throwable -> 0x00d1 }
        if (r1 == 0) goto L_0x0008;
    L_0x00c1:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00c6 }
        goto L_0x0008;
    L_0x00c6:
        r0 = move-exception;
        r1 = f15994h;	 Catch:{ all -> 0x00ce }
        r1.m18011e(r0);	 Catch:{ all -> 0x00ce }
        goto L_0x0008;
    L_0x00ce:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x00d1:
        r0 = move-exception;
        r2 = f15994h;	 Catch:{ all -> 0x00e6 }
        r2.m18011e(r0);	 Catch:{ all -> 0x00e6 }
        if (r1 == 0) goto L_0x0008;
    L_0x00d9:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00de }
        goto L_0x0008;
    L_0x00de:
        r0 = move-exception;
        r1 = f15994h;	 Catch:{ all -> 0x00ce }
        r1.m18011e(r0);	 Catch:{ all -> 0x00ce }
        goto L_0x0008;
    L_0x00e6:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00ec;
    L_0x00e9:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00ed }
    L_0x00ec:
        throw r0;	 Catch:{ all -> 0x00ce }
    L_0x00ed:
        r1 = move-exception;	 Catch:{ all -> 0x00ce }
        r2 = f15994h;	 Catch:{ all -> 0x00ce }
        r2.m18011e(r1);	 Catch:{ all -> 0x00ce }
        goto L_0x00ec;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.a(java.util.List, boolean):void");
    }

    /* renamed from: a */
    private void m17980a(boolean z) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = m17987c(z);
            sQLiteDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", Integer.valueOf(1));
            int update = sQLiteDatabase.update("events", contentValues, "status=?", new String[]{Long.toString(2)});
            if (StatConfig.isDebugEnable()) {
                f15994h.m18012i("update " + update + " unsent events.");
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th) {
                    f15994h.m18011e(th);
                }
            }
        } catch (Throwable th2) {
            f15994h.m18011e(th2);
        }
    }

    /* renamed from: b */
    private int m17981b(boolean z) {
        return !z ? StatConfig.getMaxSendRetryCount() : StatConfig.getMaxImportantDataSendRetryCount();
    }

    /* renamed from: b */
    public static au m17982b() {
        return f15996j;
    }

    /* renamed from: b */
    private void m17983b(int i, boolean z) {
        int g = i == -1 ? !z ? m17991g() : m17992h() : i;
        if (g > 0) {
            int sendPeriodMinutes = (StatConfig.getSendPeriodMinutes() * 60) * StatConfig.getNumEventsCommitPerSec();
            if (g > sendPeriodMinutes && sendPeriodMinutes > 0) {
                g = sendPeriodMinutes;
            }
            int a = StatConfig.m17855a();
            int i2 = g / a;
            int i3 = g % a;
            if (StatConfig.isDebugEnable()) {
                f15994h.m18012i("sentStoreEventsByDb sendNumbers=" + g + ",important=" + z + ",maxSendNumPerFor1Period=" + sendPeriodMinutes + ",maxCount=" + i2 + ",restNumbers=" + i3);
            }
            for (g = 0; g < i2; g++) {
                m17970a(a, z);
            }
            if (i3 > 0) {
                m17970a(i3, z);
            }
        }
    }

    /* renamed from: b */
    private synchronized void m17984b(C4513e c4513e, C4526h c4526h, boolean z, boolean z2) {
        if (StatConfig.getMaxStoreEventCount() > 0) {
            if (StatConfig.f15851m <= 0 || z || z2) {
                m17971a(c4513e, c4526h, z);
            } else if (StatConfig.f15851m > 0) {
                if (StatConfig.isDebugEnable()) {
                    f15994h.m18012i("cacheEventsInMemory.size():" + this.f16005l.size() + ",numEventsCachedInMemory:" + StatConfig.f15851m + ",numStoredEvents:" + this.f15997a);
                    f15994h.m18012i("cache event:" + c4513e.m17909g());
                }
                this.f16005l.put(c4513e, "");
                if (this.f16005l.size() >= StatConfig.f15851m) {
                    m17993i();
                }
                if (c4526h != null) {
                    if (this.f16005l.size() > 0) {
                        m17993i();
                    }
                    c4526h.mo6119a();
                }
            }
        }
    }

    /* renamed from: b */
    private synchronized void m17985b(C4549f c4549f) {
        Throwable th;
        Cursor query;
        try {
            Object obj;
            long update;
            String a = c4549f.m18113a();
            String a2 = C4539k.m18046a(a);
            ContentValues contentValues = new ContentValues();
            contentValues.put("content", c4549f.f16123b.toString());
            contentValues.put("md5sum", a2);
            c4549f.f16124c = a2;
            contentValues.put(MapboxEvent.ATTRIBUTE_VERSION, Integer.valueOf(c4549f.f16125d));
            query = this.f15999c.getReadableDatabase().query("config", null, null, null, null, null, null);
            do {
                try {
                    if (!query.moveToNext()) {
                        obj = null;
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } while (query.getInt(0) != c4549f.f16122a);
            obj = 1;
            this.f15999c.getWritableDatabase().beginTransaction();
            if (1 == obj) {
                update = (long) this.f15999c.getWritableDatabase().update("config", contentValues, "type=?", new String[]{Integer.toString(c4549f.f16122a)});
            } else {
                contentValues.put("type", Integer.valueOf(c4549f.f16122a));
                update = this.f15999c.getWritableDatabase().insert("config", null, contentValues);
            }
            if (update == -1) {
                f15994h.m18010e("Failed to store cfg:" + a);
            } else {
                f15994h.m18009d("Sucessed to store cfg:" + a);
            }
            this.f15999c.getWritableDatabase().setTransactionSuccessful();
            if (query != null) {
                query.close();
            }
            try {
                this.f15999c.getWritableDatabase().endTransaction();
            } catch (Exception e) {
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            this.f15999c.getWritableDatabase().endTransaction();
            throw th;
        }
        return;
    }

    /* renamed from: b */
    private void m17986b(List<bd> list, int i, boolean z) {
        Throwable th;
        Cursor cursor;
        Cursor query;
        try {
            query = m17988d(z).query("events", null, "status=?", new String[]{Integer.toString(1)}, null, null, null, Integer.toString(i));
            while (query.moveToNext()) {
                try {
                    long j = query.getLong(0);
                    String string = query.getString(1);
                    if (!StatConfig.f15845g) {
                        string = C4545q.m18098a(string);
                    }
                    int i2 = query.getInt(2);
                    int i3 = query.getInt(3);
                    bd bdVar = new bd(j, string, i2, i3);
                    if (StatConfig.isDebugEnable()) {
                        f15994h.m18012i("peek event, id=" + j + ",send_count=" + i3 + ",timestamp=" + query.getLong(4));
                    }
                    list.add(bdVar);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* renamed from: c */
    private SQLiteDatabase m17987c(boolean z) {
        return !z ? this.f15999c.getWritableDatabase() : this.f16000d.getWritableDatabase();
    }

    /* renamed from: d */
    private SQLiteDatabase m17988d(boolean z) {
        return !z ? this.f15999c.getReadableDatabase() : this.f16000d.getReadableDatabase();
    }

    /* renamed from: f */
    private void m17990f() {
        this.f15997a = m17991g() + m17992h();
    }

    /* renamed from: g */
    private int m17991g() {
        return (int) DatabaseUtils.queryNumEntries(this.f15999c.getReadableDatabase(), "events");
    }

    /* renamed from: h */
    private int m17992h() {
        return (int) DatabaseUtils.queryNumEntries(this.f16000d.getReadableDatabase(), "events");
    }

    /* renamed from: i */
    private void m17993i() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.tencent.wxop.stat.au.i():void. bs: [B:42:0x011b, B:53:0x0133]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r9 = this;
        r1 = 0;
        r0 = r9.f16006m;
        if (r0 == 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r2 = r9.f16005l;
        monitor-enter(r2);
        r0 = r9.f16005l;	 Catch:{ all -> 0x0013 }
        r0 = r0.size();	 Catch:{ all -> 0x0013 }
        if (r0 != 0) goto L_0x0016;	 Catch:{ all -> 0x0013 }
    L_0x0011:
        monitor-exit(r2);	 Catch:{ all -> 0x0013 }
        goto L_0x0005;	 Catch:{ all -> 0x0013 }
    L_0x0013:
        r0 = move-exception;	 Catch:{ all -> 0x0013 }
        monitor-exit(r2);	 Catch:{ all -> 0x0013 }
        throw r0;
    L_0x0016:
        r0 = 1;
        r9.f16006m = r0;	 Catch:{ all -> 0x0013 }
        r0 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ all -> 0x0013 }
        if (r0 == 0) goto L_0x0051;	 Catch:{ all -> 0x0013 }
    L_0x001f:
        r0 = f15994h;	 Catch:{ all -> 0x0013 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0013 }
        r4 = "insert ";	 Catch:{ all -> 0x0013 }
        r3.<init>(r4);	 Catch:{ all -> 0x0013 }
        r4 = r9.f16005l;	 Catch:{ all -> 0x0013 }
        r4 = r4.size();	 Catch:{ all -> 0x0013 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0013 }
        r4 = " events ,numEventsCachedInMemory:";	 Catch:{ all -> 0x0013 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0013 }
        r4 = com.tencent.wxop.stat.StatConfig.f15851m;	 Catch:{ all -> 0x0013 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0013 }
        r4 = ",numStoredEvents:";	 Catch:{ all -> 0x0013 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0013 }
        r4 = r9.f15997a;	 Catch:{ all -> 0x0013 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0013 }
        r3 = r3.toString();	 Catch:{ all -> 0x0013 }
        r0.m18012i(r3);	 Catch:{ all -> 0x0013 }
    L_0x0051:
        r0 = r9.f15999c;	 Catch:{ Throwable -> 0x00ca }
        r1 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x00ca }
        r1.beginTransaction();	 Catch:{ Throwable -> 0x00ca }
        r0 = r9.f16005l;	 Catch:{ Throwable -> 0x00ca }
        r0 = r0.entrySet();	 Catch:{ Throwable -> 0x00ca }
        r3 = r0.iterator();	 Catch:{ Throwable -> 0x00ca }
    L_0x0064:
        r0 = r3.hasNext();	 Catch:{ Throwable -> 0x00ca }
        if (r0 == 0) goto L_0x0116;	 Catch:{ Throwable -> 0x00ca }
    L_0x006a:
        r0 = r3.next();	 Catch:{ Throwable -> 0x00ca }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Throwable -> 0x00ca }
        r0 = r0.getKey();	 Catch:{ Throwable -> 0x00ca }
        r0 = (com.tencent.wxop.stat.p201a.C4513e) r0;	 Catch:{ Throwable -> 0x00ca }
        r4 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x00ca }
        r4.<init>();	 Catch:{ Throwable -> 0x00ca }
        r5 = r0.m17909g();	 Catch:{ Throwable -> 0x00ca }
        r6 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x00ca }
        if (r6 == 0) goto L_0x0099;	 Catch:{ Throwable -> 0x00ca }
    L_0x0085:
        r6 = f15994h;	 Catch:{ Throwable -> 0x00ca }
        r7 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ca }
        r8 = "insert content:";	 Catch:{ Throwable -> 0x00ca }
        r7.<init>(r8);	 Catch:{ Throwable -> 0x00ca }
        r7 = r7.append(r5);	 Catch:{ Throwable -> 0x00ca }
        r7 = r7.toString();	 Catch:{ Throwable -> 0x00ca }
        r6.m18012i(r7);	 Catch:{ Throwable -> 0x00ca }
    L_0x0099:
        r5 = com.tencent.wxop.stat.common.C4545q.m18103b(r5);	 Catch:{ Throwable -> 0x00ca }
        r6 = "content";	 Catch:{ Throwable -> 0x00ca }
        r4.put(r6, r5);	 Catch:{ Throwable -> 0x00ca }
        r5 = "send_count";	 Catch:{ Throwable -> 0x00ca }
        r6 = "0";	 Catch:{ Throwable -> 0x00ca }
        r4.put(r5, r6);	 Catch:{ Throwable -> 0x00ca }
        r5 = "status";	 Catch:{ Throwable -> 0x00ca }
        r6 = 1;	 Catch:{ Throwable -> 0x00ca }
        r6 = java.lang.Integer.toString(r6);	 Catch:{ Throwable -> 0x00ca }
        r4.put(r5, r6);	 Catch:{ Throwable -> 0x00ca }
        r5 = "timestamp";	 Catch:{ Throwable -> 0x00ca }
        r6 = r0.m17905c();	 Catch:{ Throwable -> 0x00ca }
        r0 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x00ca }
        r4.put(r5, r0);	 Catch:{ Throwable -> 0x00ca }
        r0 = "events";	 Catch:{ Throwable -> 0x00ca }
        r5 = 0;	 Catch:{ Throwable -> 0x00ca }
        r1.insert(r0, r5, r4);	 Catch:{ Throwable -> 0x00ca }
        r3.remove();	 Catch:{ Throwable -> 0x00ca }
        goto L_0x0064;
    L_0x00ca:
        r0 = move-exception;
        r3 = f15994h;	 Catch:{ all -> 0x0130 }
        r3.m18011e(r0);	 Catch:{ all -> 0x0130 }
        if (r1 == 0) goto L_0x00d8;
    L_0x00d2:
        r1.endTransaction();	 Catch:{ Throwable -> 0x0129 }
        r9.m17990f();	 Catch:{ Throwable -> 0x0129 }
    L_0x00d8:
        r0 = 0;
        r9.f16006m = r0;	 Catch:{ all -> 0x0013 }
        r0 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ all -> 0x0013 }
        if (r0 == 0) goto L_0x0113;	 Catch:{ all -> 0x0013 }
    L_0x00e1:
        r0 = f15994h;	 Catch:{ all -> 0x0013 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0013 }
        r3 = "after insert, cacheEventsInMemory.size():";	 Catch:{ all -> 0x0013 }
        r1.<init>(r3);	 Catch:{ all -> 0x0013 }
        r3 = r9.f16005l;	 Catch:{ all -> 0x0013 }
        r3 = r3.size();	 Catch:{ all -> 0x0013 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0013 }
        r3 = ",numEventsCachedInMemory:";	 Catch:{ all -> 0x0013 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0013 }
        r3 = com.tencent.wxop.stat.StatConfig.f15851m;	 Catch:{ all -> 0x0013 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0013 }
        r3 = ",numStoredEvents:";	 Catch:{ all -> 0x0013 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0013 }
        r3 = r9.f15997a;	 Catch:{ all -> 0x0013 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0013 }
        r1 = r1.toString();	 Catch:{ all -> 0x0013 }
        r0.m18012i(r1);	 Catch:{ all -> 0x0013 }
    L_0x0113:
        monitor-exit(r2);	 Catch:{ all -> 0x0013 }
        goto L_0x0005;
    L_0x0116:
        r1.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00ca }
        if (r1 == 0) goto L_0x00d8;
    L_0x011b:
        r1.endTransaction();	 Catch:{ Throwable -> 0x0122 }
        r9.m17990f();	 Catch:{ Throwable -> 0x0122 }
        goto L_0x00d8;
    L_0x0122:
        r0 = move-exception;
        r1 = f15994h;	 Catch:{ all -> 0x0013 }
        r1.m18011e(r0);	 Catch:{ all -> 0x0013 }
        goto L_0x00d8;	 Catch:{ all -> 0x0013 }
    L_0x0129:
        r0 = move-exception;	 Catch:{ all -> 0x0013 }
        r1 = f15994h;	 Catch:{ all -> 0x0013 }
        r1.m18011e(r0);	 Catch:{ all -> 0x0013 }
        goto L_0x00d8;
    L_0x0130:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0139;
    L_0x0133:
        r1.endTransaction();	 Catch:{ Throwable -> 0x013a }
        r9.m17990f();	 Catch:{ Throwable -> 0x013a }
    L_0x0139:
        throw r0;	 Catch:{ all -> 0x0013 }
    L_0x013a:
        r1 = move-exception;	 Catch:{ all -> 0x0013 }
        r3 = f15994h;	 Catch:{ all -> 0x0013 }
        r3.m18011e(r1);	 Catch:{ all -> 0x0013 }
        goto L_0x0139;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.au.i():void");
    }

    /* renamed from: j */
    private void m17994j() {
        Throwable th;
        Cursor query;
        try {
            query = this.f15999c.getReadableDatabase().query("keyvalues", null, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    this.f16007n.put(query.getString(0), query.getString(1));
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public int m17995a() {
        return this.f15997a;
    }

    /* renamed from: a */
    void m17996a(int i) {
        this.f16001e.m18024a(new bb(this, i));
    }

    /* renamed from: a */
    void m17997a(C4513e c4513e, C4526h c4526h, boolean z, boolean z2) {
        if (this.f16001e != null) {
            this.f16001e.m18024a(new ay(this, c4513e, c4526h, z, z2));
        }
    }

    /* renamed from: a */
    void m17998a(C4549f c4549f) {
        if (c4549f != null) {
            this.f16001e.m18024a(new az(this, c4549f));
        }
    }

    /* renamed from: a */
    void m17999a(List<bd> list, int i, boolean z, boolean z2) {
        if (this.f16001e != null) {
            this.f16001e.m18024a(new av(this, list, i, z, z2));
        }
    }

    /* renamed from: a */
    void m18000a(List<bd> list, boolean z, boolean z2) {
        if (this.f16001e != null) {
            this.f16001e.m18024a(new aw(this, list, z, z2));
        }
    }

    /* renamed from: b */
    public synchronized C4529a m18001b(Context context) {
        C4529a c4529a;
        Cursor query;
        Throwable th;
        Cursor cursor;
        if (this.f15998b != null) {
            c4529a = this.f15998b;
        } else {
            try {
                this.f15999c.getWritableDatabase().beginTransaction();
                if (StatConfig.isDebugEnable()) {
                    f15994h.m18012i("try to load user info from db.");
                }
                query = this.f15999c.getReadableDatabase().query("user", null, null, null, null, null, null, null);
                Object obj = null;
                try {
                    String string;
                    String b;
                    if (query.moveToNext()) {
                        String a = C4545q.m18098a(query.getString(0));
                        int i = query.getInt(1);
                        string = query.getString(2);
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        int i2 = (i == 1 || C4539k.m18044a(query.getLong(3) * 1000).equals(C4539k.m18044a(1000 * currentTimeMillis))) ? i : 1;
                        int i3 = !string.equals(C4539k.m18072n(context)) ? i2 | 2 : i2;
                        String[] split = a.split(",");
                        obj = null;
                        if (split == null || split.length <= 0) {
                            b = C4539k.m18053b(context);
                            obj = 1;
                            a = b;
                        } else {
                            b = split[0];
                            if (b == null || b.length() < 11) {
                                string = C4545q.m18097a(context);
                                if (string == null || string.length() <= 10) {
                                    string = b;
                                } else {
                                    obj = 1;
                                }
                                b = a;
                                a = string;
                            } else {
                                String str = b;
                                b = a;
                                a = str;
                            }
                        }
                        if (split == null || split.length < 2) {
                            string = C4539k.m18055c(context);
                            if (string != null && string.length() > 0) {
                                b = a + "," + string;
                                obj = 1;
                            }
                        } else {
                            string = split[1];
                            b = a + "," + string;
                        }
                        this.f15998b = new C4529a(a, string, i3);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("uid", C4545q.m18103b(b));
                        contentValues.put("user_type", Integer.valueOf(i3));
                        contentValues.put("app_ver", C4539k.m18072n(context));
                        contentValues.put("ts", Long.valueOf(currentTimeMillis));
                        if (obj != null) {
                            this.f15999c.getWritableDatabase().update("user", contentValues, "uid=?", new String[]{r10});
                        }
                        if (i3 != i) {
                            this.f15999c.getWritableDatabase().replace("user", null, contentValues);
                        }
                        obj = 1;
                    }
                    if (obj == null) {
                        string = C4539k.m18053b(context);
                        b = C4539k.m18055c(context);
                        String str2 = (b == null || b.length() <= 0) ? string : string + "," + b;
                        long currentTimeMillis2 = System.currentTimeMillis() / 1000;
                        String n = C4539k.m18072n(context);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("uid", C4545q.m18103b(str2));
                        contentValues2.put("user_type", Integer.valueOf(0));
                        contentValues2.put("app_ver", n);
                        contentValues2.put("ts", Long.valueOf(currentTimeMillis2));
                        this.f15999c.getWritableDatabase().insert("user", null, contentValues2);
                        this.f15998b = new C4529a(string, b, 0);
                    }
                    this.f15999c.getWritableDatabase().setTransactionSuccessful();
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th2) {
                            f15994h.m18011e(th2);
                        }
                    }
                    this.f15999c.getWritableDatabase().endTransaction();
                } catch (Throwable th3) {
                    th2 = th3;
                    if (query != null) {
                        query.close();
                    }
                    this.f15999c.getWritableDatabase().endTransaction();
                    throw th2;
                }
            } catch (Throwable th4) {
                th2 = th4;
                query = null;
                if (query != null) {
                    query.close();
                }
                this.f15999c.getWritableDatabase().endTransaction();
                throw th2;
            }
            c4529a = this.f15998b;
        }
        return c4529a;
    }

    /* renamed from: c */
    void m18002c() {
        if (StatConfig.isEnableStatService()) {
            try {
                this.f16001e.m18024a(new ax(this));
            } catch (Throwable th) {
                f15994h.m18011e(th);
            }
        }
    }

    /* renamed from: d */
    void m18003d() {
        Throwable th;
        Cursor query;
        try {
            query = this.f15999c.getReadableDatabase().query("config", null, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    int i = query.getInt(0);
                    String string = query.getString(1);
                    String string2 = query.getString(2);
                    int i2 = query.getInt(3);
                    C4549f c4549f = new C4549f(i);
                    c4549f.f16122a = i;
                    c4549f.f16123b = new JSONObject(string);
                    c4549f.f16124c = string2;
                    c4549f.f16125d = i2;
                    StatConfig.m17860a(f15995i, c4549f);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }
}
