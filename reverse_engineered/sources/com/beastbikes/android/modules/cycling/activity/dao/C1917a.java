package com.beastbikes.android.modules.cycling.activity.dao;

import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.p057b.C1568c;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.persistence.android.ormlite.C1380c;
import com.beastbikes.framework.persistence.android.ormlite.C1664a;
import com.beastbikes.framework.ui.android.WebActivity;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: LocalActivityDao */
/* renamed from: com.beastbikes.android.modules.cycling.activity.dao.a */
public class C1917a extends C1664a<LocalActivity> implements C1568c {
    /* renamed from: a */
    private static final Logger f8573a = LoggerFactory.getLogger(C1917a.class);

    public C1917a(C1380c c1380c) {
        super(c1380c, LocalActivity.class);
    }

    /* renamed from: a */
    public void m9906a(String str, String str2) throws PersistenceException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ").append("activity");
        stringBuilder.append(" SET ").append(WebActivity.EXTRA_TITLE).append("=?");
        stringBuilder.append(" WHERE ").append(FieldType.FOREIGN_ID_FIELD_SUFFIX).append("=?");
        m9021a(stringBuilder.toString(), str2, str);
    }

    /* renamed from: a */
    public void m9905a(String str, int i) throws PersistenceException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ").append("activity");
        stringBuilder.append(" SET ").append("is_private").append(SimpleComparison.EQUAL_TO_OPERATION).append(i);
        stringBuilder.append(" WHERE ").append(FieldType.FOREIGN_ID_FIELD_SUFFIX).append("=?");
        m9021a(stringBuilder.toString(), str);
    }

    /* renamed from: b */
    public void m9909b(String str, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ").append("activity");
        stringBuilder.append(" SET ").append("show_status").append(SimpleComparison.EQUAL_TO_OPERATION).append(i);
        stringBuilder.append(" WHERE ").append(FieldType.FOREIGN_ID_FIELD_SUFFIX).append("=?");
        try {
            m9021a(stringBuilder.toString(), str);
            f8573a.info("Update local cycling showStatus is success by activityId = " + str);
        } catch (PersistenceException e) {
            f8573a.error("Update local cycling showStatus is error, e = " + e);
        }
    }

    /* renamed from: a */
    public void m9904a(LocalActivity localActivity) throws PersistenceException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ").append("activity");
        stringBuilder.append(" SET ").append("state").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getState());
        stringBuilder.append(" , ").append("finish_time").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getFinishTime());
        stringBuilder.append(" WHERE ").append(FieldType.FOREIGN_ID_FIELD_SUFFIX).append("=?");
        m9021a(stringBuilder.toString(), localActivity.getId());
    }

    /* renamed from: b */
    public void m9908b(LocalActivity localActivity) {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ").append("activity");
        StringBuilder append = stringBuilder.append(" SET ").append("synced=");
        if (!localActivity.isSynced()) {
            i = 0;
        }
        append.append(i);
        stringBuilder.append(" , ").append("fake=").append(localActivity.getFake());
        stringBuilder.append(" , ").append("speed=").append(localActivity.getSpeed());
        stringBuilder.append(" , ").append("total_uphill_distance=").append(localActivity.getTotalUphillDistance());
        stringBuilder.append(" , ").append("total_risen_altitude=").append(localActivity.getTotalRisenAltitude());
        stringBuilder.append(" , ").append("max_velocity=").append(localActivity.getMaxVelocity());
        stringBuilder.append(" , ").append("total_calorie=").append(localActivity.getTotalCalorie());
        stringBuilder.append(" , ").append("sync_time=").append(localActivity.getSyncTime());
        stringBuilder.append(" , ").append("title=?");
        stringBuilder.append(" , ").append("activity_url=?");
        stringBuilder.append(" , ").append("remote_id=?");
        stringBuilder.append(" , ").append("is_repair").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getIsRepair());
        stringBuilder.append(" WHERE _id").append("=?");
        try {
            m9021a(stringBuilder.toString(), localActivity.getTitle(), localActivity.getActivityUrl(), localActivity.getRemoteId(), localActivity.getId());
            f8573a.info("UPDATE localActivity " + localActivity.getId() + " success, SQL: " + stringBuilder.toString());
        } catch (Throwable e) {
            f8573a.info("UPDATE localActivity " + localActivity.getId() + "error", e);
        }
    }

    /* renamed from: c */
    public void m9911c(LocalActivity localActivity) throws PersistenceException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ").append("activity");
        stringBuilder.append(" SET ").append("instantaneous_velocity").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getInstantaneousVelocity());
        stringBuilder.append(" , ").append("total_distance").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getTotalDistance());
        stringBuilder.append(" , ").append("total_elapsed_time").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getTotalElapsedTime());
        stringBuilder.append(" , ").append("total_calorie").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getTotalCalorie());
        stringBuilder.append(" , ").append("total_distance").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getTotalDistance());
        stringBuilder.append(" , ").append("total_elapsed_time").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getTotalElapsedTime());
        stringBuilder.append(" , ").append("max_altitude").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getMaxAltitude());
        stringBuilder.append(" , ").append("max_cardiac_rate").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getMaxCardiacRate());
        stringBuilder.append(" , ").append("max_velocity").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getMaxVelocity());
        stringBuilder.append(" , ").append("total_uphill_distance").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getTotalUphillDistance());
        stringBuilder.append(" , ").append("total_risen_altitude").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getTotalRisenAltitude());
        stringBuilder.append(" , ").append(MapboxEvent.KEY_SPEED).append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getSpeed());
        stringBuilder.append(" , ").append("max_power").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getMaxPower());
        stringBuilder.append(" , ").append("avg_power").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getAvgPower());
        stringBuilder.append(" , ").append("standard_power").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getStandardPower());
        stringBuilder.append(" , ").append("total_decline").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getTotalDescent());
        stringBuilder.append(" , ").append("total_avg_power_30s").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getTotalAvgPower30s());
        stringBuilder.append(" , ").append("is_virtual_watts").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.isVirtualPower() ? 1 : 0);
        stringBuilder.append(" , ").append("sample_count").append(SimpleComparison.EQUAL_TO_OPERATION).append(localActivity.getSampleCount());
        stringBuilder.append(" WHERE _id").append("=?");
        m9021a(stringBuilder.toString(), localActivity.getId());
    }

    /* renamed from: b */
    public List<LocalActivity> m9907b(String str, String str2) {
        String str3 = "WHERE user_id=? AND central_id=? AND ble_data_type!=2 AND total_distance>0 AND length(trim(ifnull(remote_id, ''))) = 0 ORDER BY finish_time ASC";
        try {
            f8573a.info("Query unsync ble localActivity by userId = " + str + " and centralId = " + str2);
            return super.m9025b("WHERE user_id=? AND central_id=? AND ble_data_type!=2 AND total_distance>0 AND length(trim(ifnull(remote_id, ''))) = 0 ORDER BY finish_time ASC", str, str2);
        } catch (PersistenceException e) {
            f8573a.error("Query unsync ble local activity by userId and deviceId error, " + e);
            return null;
        }
    }

    /* renamed from: c */
    public List<LocalActivity> m9910c(String str, String str2) {
        String str3 = "WHERE user_id=? AND central_id=? AND ble_data_type=2 AND state=4 AND total_distance>0 AND length(trim(ifnull(remote_id, ''))) = 0 ORDER BY finish_time ASC";
        try {
            f8573a.info("Query unsync ble localActivity by userId = " + str + " and centralId = " + str2);
            return super.m9025b("WHERE user_id=? AND central_id=? AND ble_data_type=2 AND state=4 AND total_distance>0 AND length(trim(ifnull(remote_id, ''))) = 0 ORDER BY finish_time ASC", str, str2);
        } catch (PersistenceException e) {
            f8573a.error("Query unsync ble local activity by userId and deviceId error, " + e);
            return null;
        }
    }

    /* renamed from: a */
    public LocalActivity m9903a(long j, String str) {
        try {
            List b = super.m9025b("WHERE finish_time=" + j + " AND " + "device_id" + "=?", str);
            if (b == null || b.size() <= 0) {
                return null;
            }
            return (LocalActivity) b.get(0);
        } catch (PersistenceException e) {
            f8573a.error("Query ble local activity finish time = " + j + ", device id = " + str);
            return null;
        }
    }
}
