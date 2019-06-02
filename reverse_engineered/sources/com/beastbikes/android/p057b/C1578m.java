package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BeastUpgradeHandler13 */
/* renamed from: com.beastbikes.android.b.m */
public class C1578m extends C1567d {
    /* renamed from: a */
    private static Logger f7315a = LoggerFactory.getLogger(C1578m.class);

    public C1578m(C1377c c1377c) {
        super(c1377c, 268);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN central_name");
        } catch (SQLException e) {
            f7315a.error("Alter table activity add column central_name error, e = " + e);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE ble_device ADD COLUMN frame_id");
        } catch (SQLException e2) {
            f7315a.error("Alter table ble_device add column frame_id error, e = " + e2);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE ble_device ADD COLUMN device_url");
        } catch (SQLException e22) {
            f7315a.error("Alter table ble_device add column device_url error, e = " + e22);
        }
    }
}
