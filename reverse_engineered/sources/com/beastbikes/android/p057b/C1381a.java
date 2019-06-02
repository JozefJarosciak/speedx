package com.beastbikes.android.p057b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.android.modules.cycling.club.dao.entity.Club;
import com.beastbikes.android.modules.cycling.grid.dao.entity.Grid;
import com.beastbikes.android.modules.social.im.dao.entity.Friend;
import com.beastbikes.android.modules.user.dao.entity.LocalAccounts;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import com.beastbikes.framework.persistence.android.ormlite.C1380c;
import com.beastbikes.framework.persistence.android.ormlite.C1567d;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BeastPersistenceManager */
/* renamed from: com.beastbikes.android.b.a */
public class C1381a extends C1380c {
    /* renamed from: a */
    private static Logger f4113a = LoggerFactory.getLogger(C1381a.class);
    /* renamed from: b */
    private final C1567d[] f4114b = new C1567d[]{new C1574i(this), new C1585t(this), new C1586u(this), new C1587v(this), new C1588w(this), new C1589x(this), new C1590y(this), new C1591z(this), new aa(this), new C1575j(this), new C1576k(this), new C1577l(this), new C1578m(this), new C1579n(this), new C1580o(this), new C1581p(this), new C1582q(this), new C1583r(this), new C1584s(this)};

    public C1381a(Context context) {
        super(context, "beast.sqlite", null, 274);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, LocalUser.class);
        } catch (SQLException e) {
            f4113a.error("Create LocalUser table error, e = " + e);
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, LocalActivity.class);
        } catch (SQLException e2) {
            f4113a.error("Create LocalActivity table error, e = " + e2);
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, LocalActivitySample.class);
        } catch (SQLException e22) {
            f4113a.error("Create LocalActivitySample table error, e = " + e22);
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, Club.class);
        } catch (SQLException e222) {
            f4113a.error("Create Club table error, e = " + e222);
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, Friend.class);
        } catch (SQLException e2222) {
            f4113a.error("Create Friend table error, e = " + e2222);
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, Grid.class);
        } catch (SQLException e22222) {
            f4113a.error("Create Grid table error, e = " + e22222);
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, LocalAccounts.class);
        } catch (SQLException e222222) {
            f4113a.error("Create LocalAccounts table error, e = " + e222222);
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, BleDevice.class);
        } catch (SQLException e2222222) {
            f4113a.error("Create BleDevice table error, e = " + e2222222);
        }
    }

    /* renamed from: a */
    public C1567d[] mo2744a() {
        return this.f4114b;
    }
}
