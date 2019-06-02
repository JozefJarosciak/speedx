package com.beastbikes.android.modules.preferences.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.Settings.System;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import com.avos.avoscloud.AVAnalytics;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

@C1457a(a = "离线地图")
@C1459b(a = 2130903545)
public class OfflineMapSettingActivity extends BaseFragmentActivity implements OnChildClickListener, OnGroupClickListener, MKOfflineMapListener {
    /* renamed from: a */
    private final MKOfflineMap f5899a = new MKOfflineMap();
    /* renamed from: b */
    private final DataSetObserver f5900b = new OfflineMapSettingActivity$1(this);
    @C1458a(a = 2131757229)
    /* renamed from: c */
    private ExpandableListView f5901c;
    /* renamed from: d */
    private OfflineMapSettingActivity$d f5902d;

    /* renamed from: com.beastbikes.android.modules.preferences.ui.OfflineMapSettingActivity$a */
    private static final class C1429a extends ViewHolder<OfflineMapSettingActivity$f> {
        @C1458a(a = 2131757231)
        /* renamed from: a */
        private TextView f5896a;
        @C1458a(a = 2131757232)
        /* renamed from: b */
        private TextView f5897b;

        public /* synthetic */ void bind(Object obj) {
            m7135a((OfflineMapSettingActivity$f) obj);
        }

        @SuppressLint({"DefaultLocale"})
        /* renamed from: a */
        static String m7134a(MKOLSearchRecord mKOLSearchRecord) {
            if (mKOLSearchRecord.size < 1048576) {
                return String.format("%s (%dK)", new Object[]{mKOLSearchRecord.cityName, Integer.valueOf(mKOLSearchRecord.size / 1024)});
            }
            return String.format("%s (%.1fM)", new Object[]{mKOLSearchRecord.cityName, Double.valueOf(((double) mKOLSearchRecord.size) / 1048576.0d)});
        }

        protected C1429a(View view) {
            super(view);
        }

        /* renamed from: a */
        public void m7135a(OfflineMapSettingActivity$f offlineMapSettingActivity$f) {
            this.f5896a.setText(C1429a.m7134a(OfflineMapSettingActivity$f.a(offlineMapSettingActivity$f)));
            this.f5897b.setText(offlineMapSettingActivity$f.f10946a);
        }
    }

    /* renamed from: com.beastbikes.android.modules.preferences.ui.OfflineMapSettingActivity$b */
    private static final class C1430b extends ViewHolder<OfflineMapSettingActivity$e> {
        @C1458a(a = 2131757230)
        /* renamed from: a */
        private TextView f5898a;

        public /* synthetic */ void bind(Object obj) {
            m7136a((OfflineMapSettingActivity$e) obj);
        }

        public C1430b(View view) {
            super(view);
        }

        /* renamed from: a */
        public void m7136a(OfflineMapSettingActivity$e offlineMapSettingActivity$e) {
            this.f5898a.setText(OfflineMapSettingActivity$e.b(offlineMapSettingActivity$e));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5899a.init(this);
        this.f5902d = new OfflineMapSettingActivity$d(this);
        this.f5902d.registerDataSetObserver(this.f5900b);
        this.f5901c.setOnChildClickListener(this);
        this.f5901c.setOnGroupClickListener(this);
        this.f5901c.setAdapter(this.f5902d);
    }

    protected void onResume() {
        super.onResume();
        this.f5902d.notifyDataSetChanged();
    }

    protected void onDestroy() {
        this.f5899a.destroy();
        this.f5902d.unregisterDataSetObserver(this.f5900b);
        super.onDestroy();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        if (m7140a((Context) this)) {
            return false;
        }
        ExpandableListAdapter expandableListAdapter = expandableListView.getExpandableListAdapter();
        OfflineMapSettingActivity$f offlineMapSettingActivity$f = (OfflineMapSettingActivity$f) expandableListAdapter.getChild(i, i2);
        if (offlineMapSettingActivity$f != null) {
            offlineMapSettingActivity$f.onClick(this.f5899a, expandableListAdapter);
        }
        AVAnalytics.onEvent(this, getString(C1373R.string.offline_map_setting_activity_event_download));
        return true;
    }

    public void onGetOfflineMapState(int i, int i2) {
        switch (i) {
            case 0:
            case 6:
                this.f5902d.a(this, i2);
                return;
            case 4:
                this.f5899a.remove(i2);
                this.f5902d.a(this, i2);
                return;
            default:
                return;
        }
    }

    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
        if (expandableListView.isGroupExpanded(i)) {
            expandableListView.collapseGroup(i);
        } else {
            expandableListView.expandGroup(i, false);
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m7140a(Context context) {
        if (System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private void m7138a(OfflineMapSettingActivity$f offlineMapSettingActivity$f, int[] iArr) {
        switch (C2799c.b(this)) {
            case 1:
            case 9:
                for (int start : iArr) {
                    this.f5899a.start(start);
                }
                Toasts.show(this, C1373R.string.offline_map_setting_activity_toast_start_downloading);
                offlineMapSettingActivity$f.f10946a = getString(C1373R.string.offline_map_setting_activity_downloading);
                return;
            default:
                C2621c c2621c = new C2621c(this);
                c2621c.b(C1373R.string.offline_map_setting_activity_wifi_mesage);
                c2621c.a(C1373R.string.offline_map_setting_activity_wifi_continue, new OfflineMapSettingActivity$3(this, c2621c, iArr, offlineMapSettingActivity$f)).b(C1373R.string.offline_map_setting_activity_wifi_cancle, new OfflineMapSettingActivity$2(this, c2621c)).a();
                return;
        }
    }
}
