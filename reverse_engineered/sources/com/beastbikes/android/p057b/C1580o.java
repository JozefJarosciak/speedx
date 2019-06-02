package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;

/* compiled from: BeastUpgradeHandler15 */
/* renamed from: com.beastbikes.android.b.o */
public class C1580o extends C1567d {
    public C1580o(C1377c c1377c) {
        super(c1377c, 270);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN achievement_num");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN is_join_leg");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN achievement");
        } catch (SQLException e22) {
            e22.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN leg_name");
        } catch (SQLException e222) {
            e222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN other_leg_count");
        } catch (SQLException e2222) {
            e2222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN achievement_name");
        } catch (SQLException e22222) {
            e22222.printStackTrace();
        }
    }
}
