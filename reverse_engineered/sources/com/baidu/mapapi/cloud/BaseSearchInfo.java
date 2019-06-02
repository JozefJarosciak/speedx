package com.baidu.mapapi.cloud;

import ch.qos.logback.classic.spi.CallerData;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;

public abstract class BaseSearchInfo {
    /* renamed from: a */
    String f2767a;
    public String ak;
    public int geoTableId;
    public String sn;

    /* renamed from: a */
    String mo2610a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f2767a);
        stringBuilder.append(CallerData.NA);
        if (this.ak == null || this.ak.equals("") || this.ak.length() > 50) {
            return null;
        }
        stringBuilder.append("ak");
        stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
        stringBuilder.append(this.ak);
        if (this.geoTableId == 0) {
            return null;
        }
        stringBuilder.append(C0869a.f2161b);
        stringBuilder.append("geotable_id");
        stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
        stringBuilder.append(this.geoTableId);
        if (!(this.sn == null || this.sn.equals("") || this.sn.length() > 50)) {
            stringBuilder.append(C0869a.f2161b);
            stringBuilder.append("sn");
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append(this.sn);
        }
        return stringBuilder.toString();
    }
}
