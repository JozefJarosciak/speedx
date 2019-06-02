package com.beastbikes.android.modules.cycling.activity.dao;

import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.android.p057b.C1569d;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.persistence.android.ormlite.C1380c;
import com.beastbikes.framework.persistence.android.ormlite.C1664a;
import java.util.List;

/* compiled from: LocalActivitySampleDao */
/* renamed from: com.beastbikes.android.modules.cycling.activity.dao.b */
public class C1918b extends C1664a<LocalActivitySample> implements C1569d {
    public C1918b(C1380c c1380c) {
        super(c1380c, LocalActivitySample.class);
    }

    /* renamed from: a */
    public List<LocalActivitySample> m9912a(String str, int i, int i2) throws PersistenceException {
        int max = Math.max(0, i);
        int max2 = Math.max(1, i2);
        return super.m9025b("WHERE activity_id=? ORDER BY distance ASC LIMIT " + max2 + " OFFSET " + (max * max2), str);
    }

    /* renamed from: b */
    public List<LocalActivitySample> m9913b(String str, int i, int i2) throws PersistenceException {
        int max = Math.max(0, i);
        int max2 = Math.max(1, i2);
        return super.m9025b("WHERE activity_id=? and length(trim(ifnull(remote_id, ''))) = 0 and velocity >= 0 and velocity < 1.79769313486231570e+308 ORDER BY time ASC LIMIT " + max2 + " OFFSET " + (max * max2), str);
    }
}
