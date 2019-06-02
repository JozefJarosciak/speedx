package com.tencent.wxop.stat.p201a;

import android.content.Context;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4530b;
import com.tencent.wxop.stat.common.C4545q;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.d */
public class C4517d extends C4513e {
    /* renamed from: a */
    private String f15913a;
    /* renamed from: m */
    private int f15914m;
    /* renamed from: n */
    private int f15915n = 100;
    /* renamed from: o */
    private Thread f15916o = null;

    public C4517d(Context context, int i, int i2, Throwable th, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        m17917a(i2, th);
    }

    public C4517d(Context context, int i, int i2, Throwable th, Thread thread, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        m17917a(i2, th);
        this.f15916o = thread;
    }

    public C4517d(Context context, int i, String str, int i2, int i3, Thread thread, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        if (str != null) {
            if (i3 <= 0) {
                i3 = StatConfig.getMaxReportEventLength();
            }
            if (str.length() <= i3) {
                this.f15913a = str;
            } else {
                this.f15913a = str.substring(0, i3);
            }
        }
        this.f15916o = thread;
        this.f15914m = i2;
    }

    /* renamed from: a */
    private void m17917a(int i, Throwable th) {
        if (th != null) {
            Writer stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            this.f15913a = stringWriter.toString();
            this.f15914m = i;
            printWriter.close();
        }
    }

    /* renamed from: a */
    public C4518f mo6117a() {
        return C4518f.ERROR;
    }

    /* renamed from: a */
    public boolean mo6118a(JSONObject jSONObject) {
        C4545q.m18100a(jSONObject, "er", this.f15913a);
        jSONObject.put("ea", this.f15914m);
        if (this.f15914m == 2 || this.f15914m == 3) {
            new C4530b(this.l).m18022a(jSONObject, this.f15916o);
        }
        return true;
    }
}
