package com.beastbikes.android.modules.cycling.activity.biz;

import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.android.modules.user.dto.C2411a;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* compiled from: ActivityManager */
class a$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f8561a;
    /* renamed from: b */
    final /* synthetic */ List f8562b;
    /* renamed from: c */
    final /* synthetic */ String f8563c;
    /* renamed from: d */
    final /* synthetic */ String f8564d;
    /* renamed from: e */
    final /* synthetic */ C1398a f8565e;

    a$1(C1398a c1398a, String str, List list, String str2, String str3) {
        this.f8565e = c1398a;
        this.f8561a = str;
        this.f8562b = list;
        this.f8563c = str2;
        this.f8564d = str3;
    }

    public void run() {
        try {
            this.f8565e.e(this.f8561a);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        List arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < this.f8562b.size(); i2++) {
            C2411a c2411a = (C2411a) this.f8562b.get(i2);
            LocalActivitySample localActivitySample = new LocalActivitySample();
            localActivitySample.setActivityId(this.f8561a);
            localActivitySample.setRemoteId(this.f8563c);
            if (TextUtils.isEmpty(this.f8563c)) {
                localActivitySample.setRemoteId(this.f8561a);
            }
            localActivitySample.setUserId(this.f8564d);
            localActivitySample.setSynced(true);
            localActivitySample.setSyncTime(System.currentTimeMillis());
            localActivitySample.setId(UUID.randomUUID().toString());
            localActivitySample.setAltitude(String.valueOf(c2411a.m12231g()));
            localActivitySample.setLatitude0(String.valueOf(c2411a.m12225c()));
            localActivitySample.setLatitude1(String.valueOf(c2411a.m12229e()));
            localActivitySample.setLongitude0(String.valueOf(c2411a.m12227d()));
            localActivitySample.setLongitude1(String.valueOf(c2411a.m12230f()));
            localActivitySample.setElapsedTime((long) Math.round((float) c2411a.m12232h()));
            localActivitySample.setDistance(c2411a.m12233i());
            localActivitySample.setVelocity(c2411a.m12234j());
            localActivitySample.setCalorie(c2411a.m12235k());
            localActivitySample.setCardiacRate(c2411a.m12236l());
            localActivitySample.setCurrTime(c2411a.m12232h());
            localActivitySample.setCadence(c2411a.m12237m());
            localActivitySample.setCyclingStatus(c2411a.m12238n());
            localActivitySample.setIsRepair(c2411a.m12239o() ? 1 : 0);
            localActivitySample.setPower(c2411a.m12240p());
            localActivitySample.setOrdinal(i);
            i++;
            arrayList.add(localActivitySample);
        }
        try {
            C1398a.a(this.f8565e).m9026b(arrayList);
            LocalActivity b = this.f8565e.b(this.f8561a);
            if (b != null && b.getIsRepair() == 1) {
                b.setNewSamples(true);
                C1398a.b(this.f8565e).mo3187a(b);
                C1398a.d().info("Update activity is newSamples, activityId = " + this.f8561a);
            }
        } catch (PersistenceException e2) {
            C1398a.d().error("Save activity sample to local error");
        } catch (BusinessException e3) {
            e3.printStackTrace();
        }
    }
}
