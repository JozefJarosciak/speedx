package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;

/* compiled from: BeastUpgradeHandler17 */
/* renamed from: com.beastbikes.android.b.q */
public class C1582q extends C1567d {
    public C1582q(C1377c c1377c) {
        super(c1377c, 272);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE ble_device ADD COLUMN object_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE ble_device ADD COLUMN mileage");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE ble_device ADD COLUMN cycle_time");
        } catch (SQLException e22) {
            e22.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE ble_device ADD COLUMN cycle_times");
        } catch (SQLException e222) {
            e222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN max_20_minutes_power");
        } catch (SQLException e2222) {
            e2222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN standard_power");
        } catch (SQLException e22222) {
            e22222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN power_if");
        } catch (SQLException e222222) {
            e222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN power_tss");
        } catch (SQLException e2222222) {
            e2222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN power_ftp");
        } catch (SQLException e22222222) {
            e22222222.printStackTrace();
        }
    }
}
