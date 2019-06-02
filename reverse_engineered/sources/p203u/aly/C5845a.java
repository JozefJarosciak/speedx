package p203u.aly;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import ch.qos.logback.core.joran.action.Action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p203u.aly.av.C5858e;
import p203u.aly.av.C5859f;

/* compiled from: CCSQLManager */
/* renamed from: u.aly.a */
public class C5845a {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static void m21071a(android.database.sqlite.SQLiteDatabase r12, java.util.Map<java.lang.String, p203u.aly.C5949k> r13, p203u.aly.bo r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0013 in list [B:5:0x0010]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r6 = 0;
        r4 = 0;
        r1 = 0;
        r0 = "__ag_of";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = r13.get(r0);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = (p203u.aly.C5949k) r0;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r0 != 0) goto L_0x0014;
    L_0x000e:
        if (r1 == 0) goto L_0x0013;
    L_0x0010:
        r1.close();
    L_0x0013:
        return;
    L_0x0014:
        r5 = "system where key=\"__ag_of\"";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2.<init>();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r3 = "select * from ";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r2.append(r3);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r2.append(r5);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r2.toString();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r3 = 0;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r1 = r12.rawQuery(r2, r3);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r1.moveToFirst();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r6;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x0032:
        r8 = r1.isAfterLast();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r8 != 0) goto L_0x008d;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x0038:
        r8 = r1.getCount();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r8 <= 0) goto L_0x0068;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x003e:
        r2 = "count";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r1.getColumnIndex(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r4 = r1.getInt(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = "timeStamp";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r1.getColumnIndex(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r1.getLong(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.<init>();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r9 = "delete from ";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8 = r8.append(r9);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8 = r8.append(r5);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8 = r8.toString();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r12.execSQL(r8);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x0068:
        r1.moveToNext();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        goto L_0x0032;
    L_0x006c:
        r0 = move-exception;
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2.<init>();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r3 = "save to system table error ";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r2.append(r3);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = r0.toString();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = r2.append(r0);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = r0.toString();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        p203u.aly.ah.m21165d(r0);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r1 == 0) goto L_0x0013;
    L_0x0089:
        r1.close();
        goto L_0x0013;
    L_0x008d:
        r8 = new android.content.ContentValues;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.<init>();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r5 = "key";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r9 = r0.m21982c();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.put(r5, r9);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r9 = "count";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r4 != 0) goto L_0x00ce;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x009f:
        r4 = r0.m21984e();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x00a3:
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.put(r9, r4);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r4 = "timeStamp";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r5 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r5 != 0) goto L_0x00b4;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x00b0:
        r2 = r0.m21983d();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x00b4:
        r0 = java.lang.Long.valueOf(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.put(r4, r0);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = "system";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = 0;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r12.insert(r0, r2, r8);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = "success";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = 0;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r14.mo6179a(r0, r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r1 == 0) goto L_0x0013;
    L_0x00c9:
        r1.close();
        goto L_0x0013;
    L_0x00ce:
        r4 = (long) r4;
        r10 = r0.m21984e();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r4 = r4 + r10;
        goto L_0x00a3;
    L_0x00d5:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00db;
    L_0x00d8:
        r1.close();
    L_0x00db:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.a.a(android.database.sqlite.SQLiteDatabase, java.util.Map, u.aly.bo):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: c */
    private static void m21082c(android.database.sqlite.SQLiteDatabase r2, java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0027 in list [B:3:0x0024]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r2.beginTransaction();	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0.<init>();	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r1 = "update system set count=count+1 where key like '";	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = r0.append(r1);	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = r0.append(r3);	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r1 = "'";	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = r0.append(r1);	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = r0.toString();	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r2.execSQL(r0);	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r2.setTransactionSuccessful();	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        if (r2 == 0) goto L_0x0027;
    L_0x0024:
        r2.endTransaction();
    L_0x0027:
        return;
    L_0x0028:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0027;
    L_0x002b:
        r2.endTransaction();
        goto L_0x0027;
    L_0x002f:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0035;
    L_0x0032:
        r2.endTransaction();
    L_0x0035:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.a.c(android.database.sqlite.SQLiteDatabase, java.lang.String):void");
    }

    /* renamed from: a */
    public static boolean m21074a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            if (C5845a.m21078b(sQLiteDatabase, str) >= 0) {
                sQLiteDatabase.execSQL("delete from " + str);
            }
            return true;
        } catch (SQLException e) {
            ah.m21165d("cleanTableData faild!" + e.toString());
            return false;
        }
    }

    /* renamed from: b */
    public static int m21078b(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursor = null;
        int i = 0;
        try {
            cursor = sQLiteDatabase.rawQuery("select * from " + str, null);
            i = cursor.getCount();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            ah.m21165d("count error " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i;
    }

    /* renamed from: a */
    public static boolean m21075a(SQLiteDatabase sQLiteDatabase, Collection<C5947i> collection) {
        try {
            sQLiteDatabase.beginTransaction();
            if (C5845a.m21078b(sQLiteDatabase, "aggregated_cache") > 0) {
                C5845a.m21074a(sQLiteDatabase, "aggregated_cache");
            }
            for (C5947i a : collection) {
                sQLiteDatabase.insert("aggregated_cache", null, C5845a.m21067a(a));
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            return true;
        } catch (SQLException e) {
            ah.m21165d("insert to Aggregated cache table faild!");
            sQLiteDatabase.endTransaction();
            return false;
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m21077a(bo boVar, SQLiteDatabase sQLiteDatabase, Collection<C5947i> collection) {
        try {
            sQLiteDatabase.beginTransaction();
            for (C5947i a : collection) {
                sQLiteDatabase.insert("aggregated", null, C5845a.m21067a(a));
            }
            sQLiteDatabase.setTransactionSuccessful();
            C5845a.m21074a(sQLiteDatabase, "aggregated_cache");
            boVar.mo6179a("success", false);
            return true;
        } catch (SQLException e) {
            ah.m21165d("insert to Aggregated cache table faild!");
            return false;
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* renamed from: a */
    public static boolean m21076a(SQLiteDatabase sQLiteDatabase, bo boVar) {
        try {
            sQLiteDatabase.beginTransaction();
            if (C5845a.m21078b(sQLiteDatabase, "aggregated_cache") <= 0) {
                boVar.mo6179a("faild", false);
                return false;
            }
            sQLiteDatabase.execSQL("insert into aggregated(key, count, value, totalTimestamp, timeWindowNum, label) select key, count, value, totalTimestamp, timeWindowNum, label from aggregated_cache");
            sQLiteDatabase.setTransactionSuccessful();
            C5845a.m21074a(sQLiteDatabase, "aggregated_cache");
            boVar.mo6179a("success", false);
            sQLiteDatabase.endTransaction();
            return true;
        } catch (SQLException e) {
            boVar.mo6179a(Boolean.valueOf(false), false);
            ah.m21165d("cacheToAggregatedTable happen " + e.toString());
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* renamed from: a */
    private static ContentValues m21067a(C5947i c5947i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Action.KEY_ATTRIBUTE, c5947i.m21957a());
        contentValues.put("label", c5947i.m21967c());
        contentValues.put("count", Long.valueOf(c5947i.m21972g()));
        contentValues.put("value", Long.valueOf(c5947i.m21971f()));
        contentValues.put("totalTimestamp", Long.valueOf(c5947i.m21970e()));
        contentValues.put("timeWindowNum", c5947i.m21973h());
        return contentValues;
    }

    /* renamed from: b */
    public static boolean m21080b(SQLiteDatabase sQLiteDatabase, bo boVar) {
        Cursor cursor = null;
        try {
            Map hashMap = new HashMap();
            cursor = sQLiteDatabase.rawQuery("select * from aggregated_cache", null);
            while (cursor.moveToNext()) {
                C5947i c5947i = new C5947i();
                c5947i.m21960a(bj.m21700a(cursor.getString(cursor.getColumnIndex(Action.KEY_ATTRIBUTE))));
                c5947i.m21966b(bj.m21700a(cursor.getString(cursor.getColumnIndex("label"))));
                c5947i.m21968c((long) cursor.getInt(cursor.getColumnIndex("count")));
                c5947i.m21964b((long) cursor.getInt(cursor.getColumnIndex("value")));
                c5947i.m21965b(cursor.getString(cursor.getColumnIndex("timeWindowNum")));
                c5947i.m21958a(Long.parseLong(cursor.getString(cursor.getColumnIndex("totalTimestamp"))));
                hashMap.put(bj.m21700a(cursor.getString(cursor.getColumnIndex(Action.KEY_ATTRIBUTE))), c5947i);
            }
            if (hashMap.size() > 0) {
                boVar.mo6179a(hashMap, false);
            } else {
                boVar.mo6179a("faild", false);
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException e) {
            boVar.mo6179a(Boolean.valueOf(false), false);
            ah.m21165d("cacheToMemory happen " + e.toString());
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return false;
    }

    /* renamed from: a */
    public static void m21072a(SQLiteDatabase sQLiteDatabase, boolean z, bo boVar) {
        C5845a.m21074a(sQLiteDatabase, "system");
        C5845a.m21074a(sQLiteDatabase, "aggregated");
        if (!z) {
            C5845a.m21074a(sQLiteDatabase, "limitedck");
            boVar.mo6179a("success", false);
        }
    }

    /* renamed from: a */
    public static void m21070a(SQLiteDatabase sQLiteDatabase, String str, long j, long j2) {
        try {
            int b = C5845a.m21078b(sQLiteDatabase, "system");
            int c = br.m21760a().m21762c();
            ContentValues contentValues;
            if (b < c) {
                contentValues = new ContentValues();
                contentValues.put(Action.KEY_ATTRIBUTE, str);
                contentValues.put("timeStamp", Long.valueOf(j2));
                contentValues.put("count", Long.valueOf(j));
                sQLiteDatabase.insert("system", null, contentValues);
            } else if (b == c) {
                contentValues = new ContentValues();
                contentValues.put(Action.KEY_ATTRIBUTE, "__meta_ve_of");
                contentValues.put("timeStamp", Long.valueOf(System.currentTimeMillis()));
                contentValues.put("count", Integer.valueOf(1));
                sQLiteDatabase.insert("system", null, contentValues);
            } else {
                C5845a.m21082c(sQLiteDatabase, "__meta_ve_of");
            }
        } catch (SQLException e) {
        }
    }

    /* renamed from: a */
    public static void m21073a(bo boVar, SQLiteDatabase sQLiteDatabase, List<String> list) {
        try {
            sQLiteDatabase.beginTransaction();
            if (C5845a.m21078b(sQLiteDatabase, "limitedck") > 0) {
                C5845a.m21074a(sQLiteDatabase, "limitedck");
            }
            for (String str : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("ck", str);
                sQLiteDatabase.insert("limitedck", null, contentValues);
            }
            sQLiteDatabase.setTransactionSuccessful();
            boVar.mo6179a("success", false);
        } catch (SQLException e) {
            ah.m21165d("insertToLimitCKTable error " + e.toString());
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* renamed from: a */
    public static String m21068a(SQLiteDatabase sQLiteDatabase) {
        String valueOf;
        SQLException sQLException;
        SQLException sQLException2;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            sQLiteDatabase.beginTransaction();
            if (C5845a.m21078b(sQLiteDatabase, "aggregated_cache") <= 0) {
                valueOf = String.valueOf(0);
                if (cursor != null) {
                    cursor.close();
                }
                sQLiteDatabase.endTransaction();
            } else {
                rawQuery = sQLiteDatabase.rawQuery("select * from aggregated_cache", null);
                Object obj;
                try {
                    if (rawQuery.moveToLast()) {
                        valueOf = rawQuery.getString(rawQuery.getColumnIndex("timeWindowNum"));
                    } else {
                        obj = cursor;
                    }
                } catch (SQLException e) {
                    sQLException = e;
                    obj = cursor;
                    sQLException2 = sQLException;
                    try {
                        ah.m21165d("queryLastTimeWindowNumFromCache error " + sQLException2.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.endTransaction();
                        return valueOf;
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.endTransaction();
                        throw th;
                    }
                }
                try {
                    sQLiteDatabase.setTransactionSuccessful();
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    sQLiteDatabase.endTransaction();
                } catch (SQLException e2) {
                    sQLException2 = e2;
                    ah.m21165d("queryLastTimeWindowNumFromCache error " + sQLException2.toString());
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    sQLiteDatabase.endTransaction();
                    return valueOf;
                }
            }
        } catch (SQLException e3) {
            rawQuery = cursor;
            sQLException = e3;
            valueOf = cursor;
            sQLException2 = sQLException;
            ah.m21165d("queryLastTimeWindowNumFromCache error " + sQLException2.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.endTransaction();
            return valueOf;
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.endTransaction();
            throw th;
        }
        return valueOf;
    }

    /* renamed from: b */
    public static Map<String, List<C5858e>> m21079b(SQLiteDatabase sQLiteDatabase) {
        SQLException e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        Cursor rawQuery;
        try {
            if (C5845a.m21078b(sQLiteDatabase, "aggregated") > 0) {
                rawQuery = sQLiteDatabase.rawQuery("select * from aggregated", null);
                try {
                    Map<String, List<C5858e>> hashMap = new HashMap();
                    while (rawQuery.moveToNext()) {
                        List list;
                        String string = rawQuery.getString(rawQuery.getColumnIndex(Action.KEY_ATTRIBUTE));
                        if (hashMap.containsKey(string)) {
                            list = (List) hashMap.get(string);
                            hashMap.remove(string);
                        } else {
                            list = new ArrayList();
                        }
                        C5858e c5858e = new C5858e();
                        c5858e.f18619e = bj.m21700a(rawQuery.getString(rawQuery.getColumnIndex("label")));
                        c5858e.f18615a = rawQuery.getLong(rawQuery.getColumnIndex("value"));
                        c5858e.f18616b = rawQuery.getLong(rawQuery.getColumnIndex("totalTimestamp"));
                        c5858e.f18617c = Integer.parseInt(rawQuery.getString(rawQuery.getColumnIndex("timeWindowNum")));
                        c5858e.f18618d = (int) rawQuery.getLong(rawQuery.getColumnIndex("count"));
                        list.add(c5858e);
                        hashMap.put(string, list);
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return hashMap;
                } catch (SQLException e2) {
                    e = e2;
                    cursor = rawQuery;
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                if (cursor2 != null) {
                    cursor2.close();
                }
                return cursor2;
            }
        } catch (SQLException e3) {
            e = e3;
            cursor = cursor2;
            try {
                ah.m21165d("readAllAggregatedDataForUpload error " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
                return cursor2;
            } catch (Throwable th3) {
                th = th3;
                rawQuery = cursor;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            rawQuery = cursor2;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static Map<String, List<C5859f>> m21069a(bo boVar, SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        SQLException e;
        Throwable th;
        try {
            Cursor cursor;
            Map<String, List<C5859f>> hashMap = new HashMap();
            if (C5845a.m21078b(sQLiteDatabase, "system") > 0) {
                rawQuery = sQLiteDatabase.rawQuery("select * from system", null);
                while (rawQuery.moveToNext()) {
                    List list;
                    String string = rawQuery.getString(rawQuery.getColumnIndex(Action.KEY_ATTRIBUTE));
                    if (hashMap.containsKey(string)) {
                        list = (List) hashMap.get(string);
                        hashMap.remove(string);
                    } else {
                        try {
                            list = new ArrayList();
                        } catch (SQLException e2) {
                            e = e2;
                        }
                    }
                    C5859f c5859f = new C5859f();
                    c5859f.f18622b = rawQuery.getLong(rawQuery.getColumnIndex("timeStamp"));
                    c5859f.f18621a = (int) rawQuery.getLong(rawQuery.getColumnIndex("count"));
                    list.add(c5859f);
                    hashMap.put(string, list);
                }
                cursor = rawQuery;
            } else {
                cursor = null;
            }
            if (cursor != null) {
                cursor.close();
            }
            return hashMap;
        } catch (SQLException e3) {
            e = e3;
            rawQuery = null;
            try {
                boVar.mo6179a("faild", false);
                ah.m21165d("readAllSystemDataForUpload error " + e.toString());
                if (rawQuery == null) {
                    return null;
                }
                rawQuery.close();
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    /* renamed from: c */
    public static List<String> m21081c(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        SQLException e;
        Throwable th;
        Cursor cursor = null;
        try {
            if (C5845a.m21078b(sQLiteDatabase, "limitedck") > 0) {
                rawQuery = sQLiteDatabase.rawQuery("select * from limitedck", null);
                try {
                    List<String> arrayList = new ArrayList();
                    while (rawQuery.moveToNext()) {
                        arrayList.add(rawQuery.getString(rawQuery.getColumnIndex("ck")));
                    }
                    if (rawQuery == null) {
                        return arrayList;
                    }
                    rawQuery.close();
                    return arrayList;
                } catch (SQLException e2) {
                    e = e2;
                    try {
                        ah.m21165d("loadLimitCKFromDB error " + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        return cursor;
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return cursor;
        } catch (SQLException e3) {
            e = e3;
            rawQuery = cursor;
            ah.m21165d("loadLimitCKFromDB error " + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            return cursor;
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }
}
