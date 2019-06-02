package com.beastbikes.framework.ui.android.lib.frag;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.beastbikes.framework.ui.android.C2824R;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;
import com.beastbikes.framework.ui.android.lib.list.PageData;
import com.beastbikes.framework.ui.android.lib.list.PageRefreshData;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.AbsListProxable;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullProxyFactory;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefrehGridStringProxy;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshAbsListViewProxy;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshAdapterViewBase;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshGridView;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshListViewProxy;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.PullToRefreshProxy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class FragPullAbsList<K, D, V extends AbsListView> extends FragBasePull<K, V> implements OnItemClickListener, OnItemLongClickListener {
    public static final int COUNT = 20;
    private static final String KEY_CLS_ITEM = "cls_item";
    private static final String KEY_CLS_KEY = "cls_key";
    private static final String KEY_CLS_VIEW = "cls_view";
    public AbsListProxable<K, D> absProxy = null;
    protected Class<?> clsItem;
    protected Class<?> clsKey;
    protected Class<?> clsView;
    private CharSequence emptyText = "暂时还没有相关的数据";
    private int emptyTxtColor = 855638016;
    private TextView emptyView;
    protected LinearLayout footerContainer;
    protected LinearLayout headerContainer;

    protected abstract BaseListAdapter<D> adapterToDisplay(AbsListView absListView);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.clsKey = (Class) bundle.getSerializable(KEY_CLS_KEY);
            this.clsItem = (Class) bundle.getSerializable(KEY_CLS_ITEM);
            this.clsView = (Class) bundle.getSerializable(KEY_CLS_VIEW);
            return;
        }
        initClasses();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putSerializable(KEY_CLS_KEY, this.clsKey);
        bundle.putSerializable(KEY_CLS_ITEM, this.clsItem);
        bundle.putSerializable(KEY_CLS_VIEW, this.clsView);
        super.onSaveInstanceState(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.emptyView = new TextView(getActivity());
        this.emptyView.setLineSpacing(0.0f, 1.2f);
        this.emptyView.setGravity(1);
        int dip2px = DensityUtil.dip2px(getActivity(), 30.0f);
        this.emptyView.setPadding(dip2px, dip2px, dip2px, dip2px);
        this.emptyView.setTextSize(20.0f);
        this.emptyView.setText(this.emptyText);
        this.emptyView.setTextColor(getResources().getColor(17170443));
        this.emptyView.setMovementMethod(LinkMovementMethod.getInstance());
        this.pullView.setEmptyView(this.emptyView);
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.absProxy.onStart();
    }

    public void onDestroy() {
        if (this.absProxy != null) {
            this.absProxy.onStop();
        }
        super.onDestroy();
    }

    protected final PullToRefreshProxy<K, V> getPullProxy() {
        Object absPullProxy = getAbsPullProxy();
        if (absPullProxy instanceof AbsListProxable) {
            this.absProxy = absPullProxy;
            return absPullProxy;
        }
        throw new UnsupportedClassVersionError("proxy must be implements AbsListProxable");
    }

    protected PullToRefreshAbsListViewProxy<K, D, V> getAbsPullProxy() {
        if (this.clsKey == Long.class && this.clsView == ListView.class) {
            initHeaderAndFooter();
            PullToRefreshAbsListViewProxy pullToRefreshListViewProxy = new PullToRefreshListViewProxy(adapterToDisplay((AbsListView) this.internalView), (PullToRefreshAdapterViewBase) this.pullView, cacheKey(), this, this.headerContainer, this.footerContainer, PullProxyFactory.getDefaultLongPageable());
            pullToRefreshListViewProxy.setPullHeader(getPullHeader());
            pullToRefreshListViewProxy.setOnItemClickListener(this);
            pullToRefreshListViewProxy.setOnItemLongClickListener(this);
            ((ListView) pullToRefreshListViewProxy.getInternalView()).setFooterDividersEnabled(false);
            return pullToRefreshListViewProxy;
        } else if (this.clsKey == String.class && this.clsView == ListView.class) {
            initHeaderAndFooter();
            r0 = new PullToRefreshListViewProxy(adapterToDisplay((AbsListView) this.internalView), (PullToRefreshAdapterViewBase) this.pullView, cacheKey(), this, this.headerContainer, this.footerContainer, PullProxyFactory.getDefaultStringPageable());
            r0.setPullHeader(getPullHeader());
            r0.setOnItemClickListener(this);
            r0.setOnItemLongClickListener(this);
            ((ListView) r0.getInternalView()).setFooterDividersEnabled(false);
            return r0;
        } else if (this.clsView != GridView.class) {
            return null;
        } else {
            r0 = new PullToRefrehGridStringProxy(adapterToDisplay((AbsListView) this.internalView), (PullToRefreshGridView) this.pullView, cacheKey(), this, PullProxyFactory.getDefaultStringPageable());
            r0.setPullHeader(getPullHeader());
            r0.setOnItemClickListener(this);
            r0.setOnItemLongClickListener(this);
            return r0;
        }
    }

    private void initHeaderAndFooter() {
        this.headerContainer = new LinearLayout(getActivity());
        this.footerContainer = new LinearLayout(getActivity());
        this.headerContainer.setOrientation(1);
        this.footerContainer.setOrientation(1);
        this.footerContainer.setPadding(0, 1, 0, 0);
        View headerView = getHeaderView();
        if (headerView != null) {
            this.headerContainer.addView(headerView);
        }
        headerView = getFooterView();
        if (headerView != null) {
            this.footerContainer.addView(headerView);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.internalView instanceof ListView) {
            i -= ((ListView) this.internalView).getHeaderViewsCount();
        }
        if (i >= 0 && i < this.absProxy.getAdapter().getCount()) {
            Object item = this.absProxy.getAdapter().getItem(i);
            if (item != null) {
                onItemClick(item);
            }
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.internalView instanceof ListView) {
            i -= ((ListView) this.internalView).getHeaderViewsCount();
        }
        if (i >= 0 && i < this.absProxy.getAdapter().getCount()) {
            Object item = this.absProxy.getAdapter().getItem(i);
            if (item != null) {
                onItemLongClick(item);
            }
        }
        return true;
    }

    public void onItemLongClick(D d) {
    }

    protected void onItemClick(D d) {
    }

    public void loadRefreshMore(K k, long j) {
    }

    public void loadMore(K k) {
    }

    protected void showListContextMenu() {
        this.pullProxy.showContextMenu();
    }

    public void refreshList() {
        this.absProxy.refreshList();
    }

    protected void onLoadFailed(String str) {
        if (!(getActivity() == null || TextUtils.isEmpty(str))) {
            Toast.makeText(getActivity(), str, 1).show();
        }
        this.pullProxy.onRefreshFinished();
    }

    protected void onLoadSucessfully(List<D> list) {
        this.absProxy.onLoadSucessfully(list);
    }

    protected void onLoadSucessAddfully(List<D> list) {
        if (list == null || list.size() == 0) {
            this.pullProxy.hidePullUp();
        } else {
            this.absProxy.onLoadSucessAddfully(list);
        }
    }

    protected void onLoadSucessfully(PageRefreshData<K, D> pageRefreshData) {
        this.absProxy.onLoadSucessfully(pageRefreshData);
    }

    protected void onLoadSucessfully(PageData<K, D> pageData) {
        this.absProxy.onLoadSucessfully(pageData);
    }

    protected void onPullDownRefresh() {
        this.pullProxy.onPullDownRefresh();
    }

    @Deprecated
    protected View getHeaderView() {
        return null;
    }

    @Deprecated
    protected View getFooterView() {
        return null;
    }

    protected void addHeaderView(View view) {
        this.headerContainer.addView(view);
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

    public void setEmptyText(CharSequence charSequence) {
        this.emptyText = charSequence;
        if (this.emptyView != null) {
            this.emptyView.setText(charSequence);
        }
    }

    public void setEmptyText(CharSequence charSequence, int i) {
        this.emptyText = charSequence;
        this.emptyTxtColor = i;
        if (this.emptyView != null) {
            this.emptyView.setTextColor(i);
            this.emptyView.setText(charSequence);
        }
    }

    protected int layoutResource() {
        if (this.clsView == ListView.class) {
            return C2824R.layout.pull_to_refresh_list;
        }
        if (this.clsView == GridView.class) {
            return C2824R.layout.pull_to_refresh_grid;
        }
        return C2824R.id.invalidResId;
    }

    private void initClasses() {
        Type[] actualTypeArguments;
        Class cls = getClass();
        cls.getGenericInterfaces();
        Type genericSuperclass = cls.getGenericSuperclass();
        Class cls2 = cls;
        while (!(genericSuperclass instanceof ParameterizedType)) {
            cls = cls2.getSuperclass();
            genericSuperclass = cls.getGenericSuperclass();
            cls2 = cls;
        }
        Type[] actualTypeArguments2 = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        this.clsKey = (Class) actualTypeArguments2[0];
        this.clsItem = (Class) actualTypeArguments2[1];
        if (actualTypeArguments2.length < 3) {
            while (true) {
                if ((genericSuperclass instanceof ParameterizedType) && ((ParameterizedType) genericSuperclass).getActualTypeArguments().length >= 3) {
                    break;
                }
                cls2 = cls2.getSuperclass();
                genericSuperclass = cls2.getGenericSuperclass();
            }
            actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        } else {
            actualTypeArguments = actualTypeArguments2;
        }
        if (this.clsView == null) {
            this.clsView = (Class) actualTypeArguments[2];
        }
    }

    public void scrollToTop(boolean z) {
        if (this.clsView == ListView.class) {
            ((ListView) this.internalView).setSelection(0);
            if (z) {
                this.pullProxy.pullDownToRefresh();
            }
        } else if (this.clsView == GridView.class) {
            ((GridView) this.internalView).setSelection(0);
            if (z) {
                this.pullProxy.pullDownToRefresh();
            }
        }
    }
}
