package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.framework.persistence.android.C1378a;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BeastUpgradeHandler1 */
/* renamed from: com.beastbikes.android.b.i */
public class C1574i extends C1567d {
    /* renamed from: a */
    private static Logger f7311a = LoggerFactory.getLogger(C1574i.class);

    public C1574i(C1378a c1378a) {
        super(c1378a, 256);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity_sample RENAME TO activity_sample_old;");
        } catch (SQLException e) {
            f7311a.error("alter table activity_sample rename to activity_sample_old error, e = " + e);
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, LocalActivitySample.class);
        } catch (java.sql.SQLException e2) {
            f7311a.error("Create table LocalActivitySample error, e = " + e2);
        }
        try {
            sQLiteDatabase.execSQL("INSERT INTO activity_sample SELECT * FROM activity_sample_old;");
        } catch (SQLException e3) {
            f7311a.error("Insert into activity_sample select * from activity_sample_old error, e = " + e3);
        }
        try {
            sQLiteDatabase.execSQL("DROP TABLE activity_sample_old;");
        } catch (SQLException e32) {
            f7311a.error("Drop table activity_sample_old error, e = " + e32);
        }
    }
}
