package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BeastUpgradeHandler11 */
/* renamed from: com.beastbikes.android.b.k */
public class C1576k extends C1567d {
    /* renamed from: a */
    private static Logger f7313a = LoggerFactory.getLogger(C1576k.class);

    public C1576k(C1377c c1377c) {
        super(c1377c, 266);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN medal_num");
        } catch (SQLException e) {
            f7313a.error("Alter table user add column medal_num error, e = " + e);
        }
    }
}
