package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;
import com.beastbikes.framework.ui.android.lib.list.PageData;
import com.beastbikes.framework.ui.android.lib.list.PageRefreshData;
import java.util.List;

public interface AbsListProxable<K, D> {
    BaseListAdapter<D> getAdapter();

    boolean isLastPage();

    void onLoadFailed(String str);

    void onLoadSucessAddfully(List<D> list);

    void onLoadSucessfully(PageData<K, D> pageData);

    void onLoadSucessfully(PageRefreshData<K, D> pageRefreshData);

    void onLoadSucessfully(List<D> list);

    void onPullUpRefresh();

    void onStart();

    void onStop();

    void refreshList();

    void setOnItemClickListener(OnItemClickListener onItemClickListener);

    void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener);
}
