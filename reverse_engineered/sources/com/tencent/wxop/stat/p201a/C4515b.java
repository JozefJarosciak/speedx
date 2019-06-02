package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.tencent.wxop.stat.StatServiceImpl;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.b */
public class C4515b extends C4513e {
    /* renamed from: a */
    protected C4516c f15908a = new C4516c();
    /* renamed from: m */
    private long f15909m = -1;

    public C4515b(Context context, int i, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f15908a.f15910a = str;
    }

    /* renamed from: h */
    private void m17912h() {
        if (this.f15908a.f15910a != null) {
            Map commonKeyValueForKVEvent = StatServiceImpl.getCommonKeyValueForKVEvent(this.f15908a.f15910a);
            if (commonKeyValueForKVEvent != null && commonKeyValueForKVEvent.size() > 0) {
                if (this.f15908a.f15912c == null || this.f15908a.f15912c.length() == 0) {
                    this.f15908a.f15912c = new JSONObject(commonKeyValueForKVEvent);
                    return;
                }
                for (Entry entry : commonKeyValueForKVEvent.entrySet()) {
                    try {
                        this.f15908a.f15912c.put(entry.getKey().toString(), entry.getValue());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public C4518f mo6117a() {
        return C4518f.CUSTOM;
    }

    /* renamed from: a */
    public void m17914a(long j) {
        this.f15909m = j;
    }

    /* renamed from: a */
    public boolean mo6118a(JSONObject jSONObject) {
        jSONObject.put("ei", this.f15908a.f15910a);
        if (this.f15909m > 0) {
            jSONObject.put("du", this.f15909m);
        }
        if (this.f15908a.f15911b == null) {
            m17912h();
            jSONObject.put("kv", this.f15908a.f15912c);
        } else {
            jSONObject.put("ar", this.f15908a.f15911b);
        }
        return true;
    }

    /* renamed from: b */
    public C4516c m17916b() {
        return this.f15908a;
    }
}
