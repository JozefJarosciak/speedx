package com.beastbikes.android.ble.dao;

import android.text.TextUtils;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.p057b.C1570e;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.persistence.android.ormlite.C1380c;
import com.beastbikes.framework.persistence.android.ormlite.C1664a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BleDeviceDao */
/* renamed from: com.beastbikes.android.ble.dao.a */
public class C1665a extends C1664a<BleDevice> implements C1570e {
    /* renamed from: a */
    private static final Logger f7533a = LoggerFactory.getLogger(C1665a.class);

    public C1665a(C1380c c1380c) {
        super(c1380c, BleDevice.class);
    }

    /* renamed from: a */
    public void m9035a(String str, String str2, int i, int i2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ").append("ble_device");
        stringBuilder.append(" SET ").append("hardware_type").append(SimpleComparison.EQUAL_TO_OPERATION).append(i);
        stringBuilder.append(" , ").append("device_id").append("=?");
        stringBuilder.append(" , ").append("brandType").append(SimpleComparison.EQUAL_TO_OPERATION).append(i2);
        stringBuilder.append(" , ").append("frame_id").append("=?");
        stringBuilder.append(" , ").append("device_url").append("=?");
        stringBuilder.append(" WHERE ").append("mac_address").append("=?");
        try {
            m9021a(stringBuilder.toString(), str2, str4, str3, str);
            f7533a.error("Update ble device info is success");
        } catch (PersistenceException e) {
            f7533a.error("Update ble device info error, " + e);
        }
    }

    /* renamed from: a */
    public BleDevice m9033a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            f7533a.error("Query BleDevice is error, deviceId = " + str + ", userId = " + str2);
            return null;
        }
        try {
            List b = super.m9025b("WHERE mac_address=? AND user_id=? ORDER BY last_bind_time DESC", str, str2);
            return (b == null || b.size() <= 0) ? null : (BleDevice) b.get(0);
        } catch (PersistenceException e) {
            f7533a.error("Query ble device by device id " + str);
            return null;
        }
    }

    /* renamed from: a */
    public BleDevice m9032a(String str) {
        try {
            List b = super.m9025b("WHERE mac_address=?", str);
            if (b == null || b.size() <= 0) {
                return null;
            }
            return (BleDevice) b.get(0);
        } catch (PersistenceException e) {
            f7533a.error("Query ble device by device id " + str);
            return null;
        }
    }

    /* renamed from: a */
    public List<BleDevice> m9034a(int i) {
        try {
            return super.m9025b("WHERE device_type=" + i, new String[0]);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public List<BleDevice> m9036b(String str) {
        try {
            return super.m9025b("WHERE user_id=? order by last_bind_time desc", str);
        } catch (PersistenceException e) {
            f7533a.error("Query ble device by device id " + str);
            return null;
        }
    }
}
