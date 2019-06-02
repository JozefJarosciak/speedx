package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;

/* compiled from: BeastUpgradeHandler8 */
/* renamed from: com.beastbikes.android.b.z */
public class C1591z extends C1567d {
    public C1591z(C1377c c1377c) {
        super(c1377c, 263);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE friend ADD COLUMN friend_remarks");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN is_private");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
}
