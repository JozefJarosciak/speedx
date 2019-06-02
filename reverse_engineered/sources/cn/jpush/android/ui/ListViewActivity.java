package cn.jpush.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.util.ac;
import java.util.List;

public class ListViewActivity extends Activity {
    /* renamed from: z */
    private static final String f903z;
    /* renamed from: a */
    private C0429c f904a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r6 = 53;
        r0 = "WZI=";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0029;
    L_0x000d:
        r3 = r0;
        r4 = r2;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x0012:
        r7 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0037;
            case 1: goto L_0x0039;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0019;
        };
    L_0x0019:
        r5 = 47;
    L_0x001b:
        r5 = r5 ^ r7;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0027;
    L_0x0023:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0012;
    L_0x0027:
        r1 = r0;
        r0 = r3;
    L_0x0029:
        if (r1 > r2) goto L_0x000d;
    L_0x002b:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f903z = r0;
        return;
    L_0x0037:
        r5 = r6;
        goto L_0x001b;
    L_0x0039:
        r5 = r6;
        goto L_0x001b;
    L_0x003b:
        r5 = 45;
        goto L_0x001b;
    L_0x003e:
        r5 = 68;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.ListViewActivity.<clinit>():void");
    }

    protected void onCreate(Bundle bundle) {
        ac.m1584c();
        super.onCreate(bundle);
        if (getIntent() != null) {
            this.f904a = (C0429c) getIntent().getSerializableExtra(f903z);
            C0429c c0429c = this.f904a;
            ac.m1584c();
            View gridView = new GridView(getApplicationContext());
            gridView.setNumColumns(2);
            List list = c0429c.f631u;
            gridView.setAdapter(new C0486e(this, Integer.MIN_VALUE, list));
            gridView.setOnItemClickListener(new C0485d(this, list));
            setContentView(gridView);
            return;
        }
        ac.m1586d();
    }
}
