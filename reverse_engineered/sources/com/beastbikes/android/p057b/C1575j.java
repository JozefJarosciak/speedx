package com.beastbikes.android.p057b;

import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BeastUpgradeHandler10 */
/* renamed from: com.beastbikes.android.b.j */
public class C1575j extends C1567d {
    /* renamed from: a */
    private static Logger f7312a = LoggerFactory.getLogger(C1575j.class);

    public C1575j(C1377c c1377c) {
        super(c1377c, 265);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, BleDevice.class);
        } catch (SQLException e) {
            f7312a.error("Create table BleDevice error, e = " + e);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN source");
        } catch (android.database.SQLException e2) {
            f7312a.error("Alter table activity add column source error, e = " + e2);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN device_id");
        } catch (android.database.SQLException e22) {
            f7312a.error("Alter table activity add column device_id error, e = " + e22);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN sample_count");
        } catch (android.database.SQLException e222) {
            f7312a.error("Alter table activity add column sample_count error, e = " + e222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN sample_rate");
        } catch (android.database.SQLException e2222) {
            f7312a.error("Alter table activity add column sample_rate error, e = " + e2222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN ble_data_type");
        } catch (android.database.SQLException e22222) {
            f7312a.error("Alter table activity add column ble_data_type error, e = " + e22222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity_sample ADD COLUMN max_speed");
        } catch (android.database.SQLException e222222) {
            f7312a.error("Alter table activity_sample add column max_speed error, e = " + e222222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity_sample ADD COLUMN cadence");
        } catch (android.database.SQLException e2222222) {
            f7312a.error("Alter table activity_sample add column cadence error, e = " + e2222222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity_sample ADD COLUMN max_cadence");
        } catch (android.database.SQLException e22222222) {
            f7312a.error("Alter table activity_sample add column max_cadence error, e = " + e22222222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity_sample ADD COLUMN max_cardiac_rate");
        } catch (android.database.SQLException e222222222) {
            f7312a.error("Alter table activity_sample add column max_cardiac_rate error, e = " + e222222222);
        }
    }
}
