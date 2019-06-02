package com.beastbikes.android.modules.cycling.club.ui;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.C2065d;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import java.util.List;

class ClubLevelActivity$a extends BaseListAdapter<C2065d> {
    /* renamed from: a */
    final /* synthetic */ ClubLevelActivity f9722a;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubLevelActivity$a$a */
    class C2096a extends ViewHolder<C2065d> {
        /* renamed from: a */
        TextView f9718a;
        /* renamed from: b */
        TextView f9719b;
        /* renamed from: c */
        ProgressBar f9720c;
        /* renamed from: d */
        final /* synthetic */ ClubLevelActivity$a f9721d;

        public /* synthetic */ void bind(Object obj) {
            m10833a((C2065d) obj);
        }

        protected C2096a(ClubLevelActivity$a clubLevelActivity$a, View view) {
            this.f9721d = clubLevelActivity$a;
            super(view);
            this.f9718a = (TextView) view.findViewById(C1373R.id.tv_param1);
            this.f9719b = (TextView) view.findViewById(C1373R.id.tv_param2);
            this.f9720c = (ProgressBar) view.findViewById(C1373R.id.pb_level);
        }

        /* renamed from: a */
        public void m10833a(C2065d c2065d) {
            this.f9718a.setText(c2065d.m10641c());
            this.f9719b.setText(c2065d.m10639a() + "/" + c2065d.m10640b());
            this.f9720c.setMax(c2065d.m10640b());
            this.f9720c.setProgress(c2065d.m10639a());
        }
    }

    public ClubLevelActivity$a(ClubLevelActivity clubLevelActivity, Handler handler, AbsListView absListView, List<C2065d> list) {
        this.f9722a = clubLevelActivity;
        super(handler, absListView, list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C2096a c2096a;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.clubfeed_level_item, null);
            c2096a = new C2096a(this, view);
        } else {
            c2096a = (C2096a) view.getTag();
        }
        C2065d c2065d = (C2065d) getItem(i);
        if (c2065d != null) {
            c2096a.m10833a(c2065d);
        }
        return view;
    }

    protected void recycleView(View view) {
    }
}
