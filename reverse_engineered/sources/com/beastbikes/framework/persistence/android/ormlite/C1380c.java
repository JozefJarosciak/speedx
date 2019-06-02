package com.beastbikes.framework.persistence.android.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ORMLitePersistenceSupport */
/* renamed from: com.beastbikes.framework.persistence.android.ormlite.c */
public abstract class C1380c extends OrmLiteSqliteOpenHelper implements C1379b, Comparator<C1567d> {
    /* renamed from: a */
    private static final Map<Class<? extends PersistentObject>, C1664a<? extends PersistentObject>> f4112a = new HashMap();

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m5405a((C1567d) obj, (C1567d) obj2);
    }

    public C1380c(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    /* renamed from: a */
    public C1567d[] mo2744a() {
        return new C1567d[0];
    }

    /* renamed from: a */
    public int m5405a(C1567d c1567d, C1567d c1567d2) {
        return c1567d.a(c1567d2);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        C1567d[] a = mo2744a();
        if (a != null && a.length > 0) {
            Arrays.sort(a, this);
            for (C1567d c1567d : a) {
                if (i < c1567d.a()) {
                    c1567d.a(sQLiteDatabase, connectionSource, i, i2);
                }
            }
        }
    }

    /* renamed from: a */
    public <T extends PersistentObject> C1664a<T> m5406a(Class<T> cls) {
        C1664a<T> c1664a = (C1664a) f4112a.get(cls);
        if (c1664a == null) {
            c1664a = new C1664a(this, cls);
            synchronized (f4112a) {
                f4112a.put(cls, c1664a);
            }
        }
        return c1664a;
    }
}
