package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;

/* compiled from: BeastUpgradeHandler9 */
/* renamed from: com.beastbikes.android.b.aa */
public class aa extends C1567d {
    public aa(C1377c c1377c) {
        super(c1377c, 264);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE club ADD COLUMN type");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE club ADD COLUMN linkTo");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN fans_num");
        } catch (SQLException e22) {
            e22.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN follower_num");
        } catch (SQLException e222) {
            e222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN follow_status");
        } catch (SQLException e2222) {
            e2222.printStackTrace();
        }
    }
}
