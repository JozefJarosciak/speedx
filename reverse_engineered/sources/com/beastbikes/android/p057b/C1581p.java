package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;

/* compiled from: BeastUpgradeHandler16 */
/* renamed from: com.beastbikes.android.b.p */
public class C1581p extends C1567d {
    public C1581p(C1377c c1377c) {
        super(c1377c, 271);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN max_power");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN avg_power");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN power_output");
        } catch (SQLException e22) {
            e22.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity_sample ADD COLUMN power");
        } catch (SQLException e222) {
            e222.printStackTrace();
        }
    }
}
