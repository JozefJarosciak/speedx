package com.beastbikes.android.p057b;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;

/* compiled from: BeastUpgradeHandler18 */
/* renamed from: com.beastbikes.android.b.r */
public class C1583r extends C1567d {
    public C1583r(C1377c c1377c) {
        super(c1377c, 273);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN course_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN course_if");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN course_name");
        } catch (SQLException e22) {
            e22.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN course_en_name");
        } catch (SQLException e222) {
            e222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN course_time");
        } catch (SQLException e2222) {
            e2222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN course_tss");
        } catch (SQLException e22222) {
            e22222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN is_virtual_watts");
        } catch (SQLException e222222) {
            e222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN train_course_date");
        } catch (SQLException e2222222) {
            e2222222.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN train_id");
        } catch (SQLException e22222222) {
            e22222222.printStackTrace();
        }
    }
}
