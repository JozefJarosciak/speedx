package com.baidu.mapapi.cloud;

import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class BaseCloudSearchInfo extends BaseSearchInfo {
    public String filter;
    public int pageIndex;
    public int pageSize = 10;
    /* renamed from: q */
    public String f2768q;
    public String sortby;
    public String tags;

    /* renamed from: a */
    String mo2610a() {
        StringBuilder stringBuilder = new StringBuilder();
        if (super.mo2610a() == null) {
            return null;
        }
        stringBuilder.append(super.mo2610a());
        if (!(this.f2768q == null || this.f2768q.equals("") || this.f2768q.length() > 45)) {
            stringBuilder.append(C0869a.f2161b);
            stringBuilder.append("q");
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            try {
                stringBuilder.append(URLEncoder.encode(this.f2768q, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (!(this.tags == null || this.tags.equals("") || this.tags.length() > 45)) {
            stringBuilder.append(C0869a.f2161b);
            stringBuilder.append("tags");
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            try {
                stringBuilder.append(URLEncoder.encode(this.tags, "UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        if (!(this.sortby == null || this.sortby.equals(""))) {
            stringBuilder.append(C0869a.f2161b);
            stringBuilder.append("sortby");
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append(this.sortby);
        }
        if (!(this.filter == null || this.filter.equals(""))) {
            stringBuilder.append(C0869a.f2161b);
            stringBuilder.append("filter");
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append(this.filter);
        }
        if (this.pageIndex >= 0) {
            stringBuilder.append(C0869a.f2161b);
            stringBuilder.append("page_index");
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append(this.pageIndex);
        }
        if (this.pageSize >= 0 && this.pageSize <= 50) {
            stringBuilder.append(C0869a.f2161b);
            stringBuilder.append("page_size");
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append(this.pageSize);
        }
        return stringBuilder.toString();
    }
}
