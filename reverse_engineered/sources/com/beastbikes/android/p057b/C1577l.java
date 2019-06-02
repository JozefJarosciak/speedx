package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BeastUpgradeHandler12 */
/* renamed from: com.beastbikes.android.b.l */
public class C1577l extends C1567d {
    /* renamed from: a */
    private static Logger f7314a = LoggerFactory.getLogger(C1577l.class);

    public C1577l(C1377c c1377c) {
        super(c1377c, 267);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE ble_device ADD COLUMN mac_address");
        } catch (SQLException e) {
            f7314a.error("Alter table ble_device add column mac_address error, e = " + e);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE ble_device ADD COLUMN guarantee_time");
        } catch (SQLException e2) {
            f7314a.error("alter table ble_device add column guarantee_time error, e = " + e2);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN cadence");
        } catch (SQLException e22) {
            f7314a.error("alter table activity add column cadence error, e = " + e22);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN cadence_max");
        } catch (SQLException e222) {
            f7314a.error("Alter table activity add column cadence_max error, e = " + e222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN cardiac_rate");
        } catch (SQLException e2222) {
            f7314a.error("alter table activity add column cardiac_rate error, e = " + e2222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN central_id");
        } catch (SQLException e22222) {
            f7314a.error("Alter table activity add column center_id error, e = " + e22222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN speedx_id");
        } catch (SQLException e222222) {
            f7314a.error("Alter table user add column speed_id error, e = " + e222222);
        }
    }
}
