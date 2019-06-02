package cn.jpush.android.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.util.C0490b;
import java.util.List;

/* renamed from: cn.jpush.android.ui.d */
final class C0485d implements OnItemClickListener {
    /* renamed from: a */
    final /* synthetic */ List f919a;
    /* renamed from: b */
    final /* synthetic */ ListViewActivity f920b;

    C0485d(ListViewActivity listViewActivity, List list) {
        this.f920b = listViewActivity;
        this.f919a = list;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C0429c c0429c = (C0429c) this.f919a.get(i);
        c0429c.f626p = false;
        this.f920b.startActivity(C0490b.m1667a(this.f920b, c0429c, false));
    }
}
