package com.beastbikes.android.p057b;

import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.android.modules.cycling.club.dao.entity.Club;
import com.beastbikes.framework.persistence.android.C1378a;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/* compiled from: BeastUpgradeHandler3 */
/* renamed from: com.beastbikes.android.b.u */
public class C1586u extends C1567d {
    public C1586u(C1378a c1378a) {
        super(c1378a, 258);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Club.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
