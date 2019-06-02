package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.InputDeviceCompat;
import com.beastbikes.framework.persistence.android.C1378a;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;

/* compiled from: BeastUpgradeHandler2 */
/* renamed from: com.beastbikes.android.b.t */
public class C1585t extends C1567d {
    public C1585t(C1378a c1378a) {
        super(c1378a, InputDeviceCompat.SOURCE_KEYBOARD);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN activity_url");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN fake BIGINT DEFAULT 0;");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN speed BIGINT DEFAULT 0;");
        } catch (SQLException e22) {
            e22.printStackTrace();
        }
    }
}
