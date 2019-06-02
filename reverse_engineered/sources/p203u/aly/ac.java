package p203u.aly;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: DatabaseManager */
/* renamed from: u.aly.ac */
public class ac {
    /* renamed from: c */
    private static ac f18568c;
    /* renamed from: d */
    private static SQLiteOpenHelper f18569d;
    /* renamed from: a */
    private AtomicInteger f18570a = new AtomicInteger();
    /* renamed from: b */
    private AtomicInteger f18571b = new AtomicInteger();
    /* renamed from: e */
    private SQLiteDatabase f18572e;

    /* renamed from: b */
    private static synchronized void m21096b(Context context) {
        synchronized (ac.class) {
            if (f18568c == null) {
                f18568c = new ac();
                f18569d = ao.m21195a(context);
            }
        }
    }

    /* renamed from: a */
    public static synchronized ac m21095a(Context context) {
        ac acVar;
        synchronized (ac.class) {
            if (f18568c == null) {
                ac.m21096b(context);
            }
            acVar = f18568c;
        }
        return acVar;
    }

    /* renamed from: a */
    public synchronized SQLiteDatabase m21097a() {
        if (this.f18570a.incrementAndGet() == 1) {
            this.f18572e = f18569d.getReadableDatabase();
        }
        return this.f18572e;
    }

    /* renamed from: b */
    public synchronized SQLiteDatabase m21098b() {
        if (this.f18570a.incrementAndGet() == 1) {
            this.f18572e = f18569d.getWritableDatabase();
        }
        return this.f18572e;
    }

    /* renamed from: c */
    public synchronized void m21099c() {
        if (this.f18570a.decrementAndGet() == 0) {
            this.f18572e.close();
        }
        if (this.f18571b.decrementAndGet() == 0) {
            this.f18572e.close();
        }
    }
}
