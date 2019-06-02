package com.beastbikes.framework.ui.android.lib.frag;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.beastbikes.framework.ui.android.C2824R;
import com.beastbikes.framework.ui.android.lib.list.BaseSectionListAdapter;
import com.beastbikes.framework.ui.android.lib.list.PageData;
import com.beastbikes.framework.ui.android.lib.list.PageRefreshData;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.ExpandableSectionList;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.Groupable;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullProxyFactory;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullRefeshListener;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshSectionListProxy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class FragPullSectionList<K, G extends Groupable<C>, C> extends FragBasePull<K, ExpandableSectionList> implements OnChildClickListener, PullRefeshListener<K> {
    protected Class<?> clsKey;
    protected LinearLayout footerContainer;
    protected LinearLayout headerContainer;
    protected BaseSectionListAdapter<G, C> sectionAdapter;
    protected PullToRefreshSectionListProxy<K, G, C> sectionProxy;

    /* renamed from: com.beastbikes.framework.ui.android.lib.frag.FragPullSectionList$1 */
    class C28271 implements OnTouchListener {
        C28271() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.lib.frag.FragPullSectionList$2 */
    class C28282 implements OnGroupCollapseListener {
        C28282() {
        }

        public void onGroupCollapse(int i) {
            ((ExpandableSectionList) FragPullSectionList.this.internalView).expandGroup(i);
        }
    }

    protected abstract BaseSectionListAdapter<G, C> adapterToDisplay(AbsListView absListView);

    protected int sectionResource() {
        return C2824R.id.invalidResId;
    }

    protected View sectionView() {
        return null;
    }

    protected final PullToRefreshSectionListProxy<K, G, C> getPullProxy() {
        View view;
        initHeaderAndFooter();
        ((ExpandableSectionList) this.internalView).addHeaderView(this.headerContainer);
        ((ExpandableSectionList) this.internalView).addFooterView(this.footerContainer);
        this.sectionProxy = getSectionPullProxy();
        this.sectionAdapter = this.sectionProxy.getAdapter();
        View sectionView = sectionView();
        int sectionResource = sectionResource();
        if (sectionView != null || sectionResource == C2824R.id.invalidResId) {
            if (sectionView != null) {
                sectionView.setLayoutParams(new LayoutParams(-1, -2));
            }
            view = sectionView;
        } else {
            view = getLayoutInflater(null).inflate(sectionResource, (ViewGroup) this.internalView, false);
        }
        if (view != null) {
            view.setOnTouchListener(new C28271());
        }
        ((ExpandableSectionList) this.internalView).setPinnedHeaderView(view);
        ((ExpandableSectionList) this.internalView).setGroupIndicator(null);
        ((ExpandableSectionList) this.internalView).setOnChildClickListener(this);
        this.sectionProxy.setOnCreateContextMenuListener(this);
        ((ExpandableSectionList) this.internalView).setOnGroupCollapseListener(new C28282());
        return this.sectionProxy;
    }

    protected PullToRefreshSectionListProxy<K, G, C> getSectionPullProxy() {
        if (this.clsKey == Long.class) {
            return new PullToRefreshSectionListProxy(adapterToDisplay((AbsListView) this.internalView), this.pullView, cacheKey(), this, PullProxyFactory.getDefaultLongPageable());
        }
        return new PullToRefreshSectionListProxy(adapterToDisplay((AbsListView) this.internalView), this.pullView, cacheKey(), this, PullProxyFactory.getDefaultStringPageable());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initClasses();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.sectionProxy.onStart();
        this.sectionProxy.disablePull();
    }

    private void initClasses() {
        Class cls = getClass();
        Type genericSuperclass = cls.getGenericSuperclass();
        while (!(genericSuperclass instanceof ParameterizedType)) {
            cls = cls.getSuperclass();
            genericSuperclass = cls.getGenericSuperclass();
        }
        this.clsKey = (Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

    private void initHeaderAndFooter() {
        this.headerContainer = new LinearLayout(getActivity());
        this.footerContainer = new LinearLayout(getActivity());
        this.headerContainer.setOrientation(1);
        this.footerContainer.setOrientation(1);
        this.footerContainer.setPadding(0, 1, 0, 0);
    }

    public void onDestroy() {
        if (this.sectionProxy != null) {
            this.sectionProxy.onStop();
        }
        super.onDestroy();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        Object child = this.sectionAdapter.getChild(i, i2);
        if (child != null) {
            onItemClick(child);
        }
        return true;
    }

    public void onItemClick(C c) {
    }

    protected int layoutResource() {
        return C2824R.layout.pull_to_refresh_section_list;
    }

    protected String cacheKey() {
        return getClass().getName();
    }

    protected void setListHeader(View view) {
        this.headerContainer.removeAllViews();
        if (view != null) {
            this.headerContainer.addView(view);
        }
    }

    protected void addHeaderView(View view) {
        this.headerContainer.addView(view);
    }

    protected void addHeaderView(View view, int i) {
        this.headerContainer.addView(view, i);
    }

    protected void addFooterView(View view) {
        this.footerContainer.addView(view);
    }

    protected void removeHeaderView(View view) {
        this.headerContainer.removeView(view);
    }

    protected void removeFooterView(View view) {
        this.footerContainer.removeView(view);
    }

    public void loadMore(K k) {
    }

    public void loadRefreshMore(K k, long j) {
    }

    protected void onLoadFailed(String str) {
        if (!(getActivity() == null || TextUtils.isEmpty(str))) {
            Toast.makeText(getActivity(), str, 1).show();
        }
        this.pullProxy.onRefreshFinished();
    }

    public void onLoadSucessfully(ArrayList<G> arrayList) {
        this.sectionProxy.onLoadSucessfully((ArrayList) arrayList);
    }

    public void onLoadSucessfully(PageRefreshData<K, G> pageRefreshData) {
        this.sectionProxy.onLoadSucessfully((PageRefreshData) pageRefreshData);
    }

    public void onLoadSucessfully(PageData<K, G> pageData) {
        this.sectionProxy.onLoadSucessfully((PageData) pageData);
    }
}
