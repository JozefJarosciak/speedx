package com.beastbikes.android.p057b;

import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.android.modules.cycling.grid.dao.entity.Grid;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/* compiled from: BeastUpgradeHandler6 */
/* renamed from: com.beastbikes.android.b.x */
public class C1589x extends C1567d {
    public C1589x(C1377c c1377c) {
        super(c1377c, 261);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Grid.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity_sample ADD COLUMN curr_time");
        } catch (android.database.SQLException e2) {
            e2.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN userId");
        } catch (android.database.SQLException e22) {
            e22.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN userIntId BIGINT DEFAULT 0;");
        } catch (android.database.SQLException e222) {
            e222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN updatedAt");
        } catch (android.database.SQLException e2222) {
            e2222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN createdAt");
        } catch (android.database.SQLException e22222) {
            e22222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN clubId");
        } catch (android.database.SQLException e222222) {
            e222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN objectId");
        } catch (android.database.SQLException e2222222) {
            e2222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN isOk");
        } catch (android.database.SQLException e22222222) {
            e22222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN gridNum");
        } catch (android.database.SQLException e222222222) {
            e222222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN edited");
        } catch (android.database.SQLException e2222222222) {
            e2222222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN weeklyDistance");
        } catch (android.database.SQLException e22222222222) {
            e22222222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN sameGrid");
        } catch (android.database.SQLException e222222222222) {
            e222222222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN monthlyDistance");
        } catch (android.database.SQLException e2222222222222) {
            e2222222222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN clubName");
        } catch (android.database.SQLException e22222222222222) {
            e22222222222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN avatar");
        } catch (android.database.SQLException e222222222222222) {
            e222222222222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE user ADD COLUMN birthday");
        } catch (android.database.SQLException e2222222222222222) {
            e2222222222222222.printStackTrace();
        }
    }
}
