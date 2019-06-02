package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BeastUpgradeHandler14 */
/* renamed from: com.beastbikes.android.b.n */
public class C1579n extends C1567d {
    /* renamed from: a */
    private static Logger f7316a = LoggerFactory.getLogger(C1579n.class);

    public C1579n(C1377c c1377c) {
        super(c1377c, 269);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN repair_distance");
        } catch (SQLException e) {
            f7316a.error("Alter table activity add column repair_distance error, e = " + e);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN show_status");
        } catch (SQLException e2) {
            f7316a.error("Alter table activity add column show_status error, e = " + e2);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN is_repair");
        } catch (SQLException e22) {
            f7316a.error("Alter table activity add column is_repair error, e = " + e22);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN is_new_samples");
        } catch (SQLException e222) {
            f7316a.error("Alter table activity add column is_new_samples error, e = " + e222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN version");
        } catch (SQLException e2222) {
            f7316a.error("Alter table activity add column version error, e = " + e2222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity_sample ADD COLUMN cycling_status");
        } catch (SQLException e22222) {
            f7316a.error("Alter table activity_sample add column cycling_status error, e = " + e22222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity_sample ADD COLUMN has_repair");
        } catch (SQLException e222222) {
            f7316a.error("Alter table activity_sample add column has_repair error, e = " + e222222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN max_heart_rate");
        } catch (SQLException e2222222) {
            f7316a.error("Alter table user add column max_heart_rate error, e = " + e2222222);
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN mobile");
        } catch (SQLException e22222222) {
            f7316a.error("Alter table user add column mobile error, e = " + e22222222);
        }
    }
}
