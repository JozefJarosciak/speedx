package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;

/* compiled from: BeastUpgradeHandler5 */
/* renamed from: com.beastbikes.android.b.w */
public class C1588w extends C1567d {
    public C1588w(C1377c c1377c) {
        super(c1377c, 260);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE club ADD COLUMN isPrivate BIGINT DEFAULT 0;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE club ADD COLUMN clubLevel BIGINT DEFAULT 0;");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
}
