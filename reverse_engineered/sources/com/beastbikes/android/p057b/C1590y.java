package com.beastbikes.android.p057b;

import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.android.modules.user.dao.entity.LocalAccounts;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/* compiled from: BeastUpgradeHandler7 */
/* renamed from: com.beastbikes.android.b.y */
public class C1590y extends C1567d {
    public C1590y(C1377c c1377c) {
        super(c1377c, 262);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, LocalAccounts.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
