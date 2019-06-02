package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.tencent.wxop.stat.StatGameUser;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4545q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.g */
public class C4519g extends C4513e {
    /* renamed from: a */
    private StatGameUser f15928a = null;

    public C4519g(Context context, int i, StatGameUser statGameUser, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f15928a = statGameUser.clone();
    }

    /* renamed from: a */
    public C4518f mo6117a() {
        return C4518f.MTA_GAME_USER;
    }

    /* renamed from: a */
    public boolean mo6118a(JSONObject jSONObject) {
        if (this.f15928a == null) {
            return false;
        }
        C4545q.m18100a(jSONObject, "wod", this.f15928a.getWorldName());
        C4545q.m18100a(jSONObject, "gid", this.f15928a.getAccount());
        C4545q.m18100a(jSONObject, "lev", this.f15928a.getLevel());
        return true;
    }
}
