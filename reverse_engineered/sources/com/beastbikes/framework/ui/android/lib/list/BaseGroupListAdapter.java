package com.beastbikes.framework.ui.android.lib.list;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.RecyclerListener;
import com.beastbikes.framework.ui.android.C2824R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BaseGroupListAdapter extends BaseListAdapter<Object> {
    private ArrayList<BaseListAdapter<?>> adapters;
    private BaseListAdapter<?> curAdapter;
    protected RecyclerListener recycleListener = new C28291();
    private HashMap<BaseListAdapter<?>, Integer> viewTypeMaps = new HashMap();

    /* renamed from: com.beastbikes.framework.ui.android.lib.list.BaseGroupListAdapter$1 */
    class C28291 extends CatchableRecyclerListener {
        C28291() {
        }

        public void intlOnMovedToScrapHeap(View view) {
            if (view != null) {
                view.destroyDrawingCache();
            }
            BaseGroupListAdapter.this.recycleView(view);
        }
    }

    public BaseGroupListAdapter(Handler handler, AbsListView absListView, ArrayList<BaseListAdapter<?>> arrayList) {
        super(handler, absListView, null);
        if (arrayList == null || absListView == null) {
            throw new UnsupportedOperationException();
        }
        absListView.setRecyclerListener(this.recycleListener);
        this.adapters = arrayList;
        if (arrayList.size() < 1) {
            this.curAdapter = null;
        } else {
            this.curAdapter = (BaseListAdapter) arrayList.get(0);
        }
    }

    protected void recycleView(View view) {
        BaseListAdapter baseListAdapter = (BaseListAdapter) view.getTag(C2824R.id.group_list_view_type);
        if (baseListAdapter != null) {
            baseListAdapter.recycleView(view);
        }
    }

    public boolean show(BaseListAdapter<?> baseListAdapter) {
        if (!this.adapters.contains(baseListAdapter)) {
            return false;
        }
        this.curAdapter = baseListAdapter;
        notifyDataSetChanged();
        return true;
    }

    public void addAdapter(BaseListAdapter<?> baseListAdapter) {
        if (!this.adapters.contains(baseListAdapter)) {
            this.adapters.add(baseListAdapter);
        }
    }

    public int getItemViewType(int i) {
        if (this.viewTypeMaps == null || this.curAdapter == null || this.viewTypeMaps.get(this.curAdapter) == null) {
            return 0;
        }
        return ((Integer) this.viewTypeMaps.get(this.curAdapter)).intValue() + this.curAdapter.getItemViewType(i);
    }

    public int getViewTypeCount() {
        Iterator it = this.adapters.iterator();
        int i = 0;
        while (it.hasNext()) {
            BaseListAdapter baseListAdapter = (BaseListAdapter) it.next();
            this.viewTypeMaps.put(baseListAdapter, Integer.valueOf(i));
            i = baseListAdapter.getViewTypeCount() + i;
        }
        return i;
    }

    public int getCount() {
        return this.curAdapter == null ? 0 : this.curAdapter.getCount();
    }

    public Object getItem(int i) {
        return this.curAdapter == null ? null : this.curAdapter.getItem(i);
    }

    public long getItemId(int i) {
        return this.curAdapter == null ? 0 : this.curAdapter.getItemId(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = this.curAdapter.getView(i, view, viewGroup);
        view2.setTag(C2824R.id.group_list_view_type, this.curAdapter);
        return view2;
    }

    public BaseListAdapter<?> getCurAdapter() {
        return this.curAdapter;
    }
}
