package com.beastbikes.android.p057b;

import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.android.modules.social.im.dao.entity.Friend;
import com.beastbikes.framework.persistence.C1377c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/* compiled from: BeastUpgradeHandler4 */
/* renamed from: com.beastbikes.android.b.v */
public class C1587v extends C1567d {
    public C1587v(C1377c c1377c) {
        super(c1377c, 259);
    }

    /* renamed from: a */
    public void mo3126a(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Friend.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
