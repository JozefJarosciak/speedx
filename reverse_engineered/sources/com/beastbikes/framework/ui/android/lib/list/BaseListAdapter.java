package com.beastbikes.framework.ui.android.lib.list;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView.RecyclerListener;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter implements OnScrollListener {
    protected OnAdapterChangeListener changedListener;
    protected OnHierarchyChangeListener childViewRemovedListener;
    protected List<T> data;
    protected int firstVisiblePos;
    protected String gaString;
    protected Handler handler;
    protected LayoutInflater inflater;
    protected int lastVisiblePos;
    protected AbsListView listView;
    protected RecyclerListener recycleListener;
    protected int scrollState;

    /* renamed from: com.beastbikes.framework.ui.android.lib.list.BaseListAdapter$1 */
    class C28301 extends CatchableRecyclerListener {
        C28301() {
        }

        public void intlOnMovedToScrapHeap(View view) {
            if (view != null) {
                view.destroyDrawingCache();
            }
            BaseListAdapter.this.recycleView(view);
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.lib.list.BaseListAdapter$2 */
    class C28312 extends CatchableOnHierarchyChangeListener {
        C28312() {
        }

        public void intlOnChildViewAdded(View view, View view2) {
        }

        public void intlOnChildViewRemoved(View view, View view2) {
            BaseListAdapter.this.recycleView(view2);
        }
    }

    public abstract View getView(int i, View view, ViewGroup viewGroup);

    protected abstract void recycleView(View view);

    public BaseListAdapter(Handler handler, AbsListView absListView) {
        this(handler, absListView, null);
    }

    public BaseListAdapter(Handler handler, AbsListView absListView, List<T> list) {
        this.data = null;
        this.inflater = null;
        this.listView = null;
        this.firstVisiblePos = -1;
        this.lastVisiblePos = -1;
        this.recycleListener = new C28301();
        this.childViewRemovedListener = new C28312();
        this.listView = absListView;
        this.data = list;
        this.handler = handler;
        this.listView.setRecyclerListener(this.recycleListener);
        this.listView.setOnScrollListener(this);
        this.listView.setOnHierarchyChangeListener(this.childViewRemovedListener);
        this.inflater = (LayoutInflater) absListView.getContext().getSystemService("layout_inflater");
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (this.changedListener != null) {
            this.changedListener.onDataChanged(this.data == null ? 0 : this.data.size());
        }
    }

    public void setDataChangedListener(OnAdapterChangeListener onAdapterChangeListener) {
        this.changedListener = onAdapterChangeListener;
    }

    public int getCount() {
        if (this.data != null) {
            return this.data.size();
        }
        return 0;
    }

    public T getItem(int i) {
        if (this.data != null) {
            return this.data.get(i);
        }
        return null;
    }

    public int getItemPosition(T t) {
        if (this.data == null) {
            return -1;
        }
        return this.data.indexOf(t);
    }

    public void add(List<T> list) {
        if (list == null) {
            notifyDataSetChanged();
            return;
        }
        if (this.data == null) {
            this.data = new ArrayList();
        }
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    public void tryAdd(List<?> list) {
        if (list != null) {
            if (this.data == null) {
                this.data = new ArrayList();
            }
            for (Object add : list) {
                this.data.add(add);
            }
        }
    }

    public void add(T t) {
        if (t != null) {
            if (this.data == null) {
                this.data = new ArrayList();
            }
            this.data.add(t);
            notifyDataSetChanged();
        }
    }

    public void insert(List<T> list) {
        if (list != null) {
            if (this.data == null) {
                this.data = new ArrayList();
            }
            this.data.addAll(0, list);
            notifyDataSetChanged();
        }
    }

    public void insert(T t) {
        if (t != null) {
            if (this.data == null) {
                this.data = new ArrayList();
            }
            this.data.add(0, t);
            notifyDataSetChanged();
        }
    }

    public void clearItems() {
        if (this.data != null) {
            this.data.clear();
            notifyDataSetChanged();
        }
    }

    public void removeItems(ArrayList<T> arrayList) {
        if (this.data != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.data.remove(it.next());
            }
            notifyDataSetChanged();
        }
    }

    public void removeItem(T t) {
        if (this.data != null) {
            this.data.remove(t);
            notifyDataSetChanged();
        }
    }

    public void replaceItem(int i, T t) {
        if (this.data == null) {
            this.data = new ArrayList();
            this.data.add(t);
        } else if (this.data.size() > i) {
            this.data.remove(i);
            this.data.add(i, t);
        } else {
            this.data.add(t);
        }
    }

    public void tryRemove(Object obj) {
        try {
            removeItem(obj);
        } catch (Exception e) {
            removeItems((ArrayList) obj);
        }
    }

    public long getItemId(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public void recycleAllView() {
        int childCount = this.listView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.listView.getChildAt(i);
            if (childAt != null) {
                childAt.destroyDrawingCache();
            }
            recycleView(childAt);
        }
    }

    public List<T> getData() {
        return this.data;
    }

    public void setGaString(String str) {
        this.gaString = str;
    }

    public boolean isScroll() {
        return this.scrollState == 2;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.scrollState = i;
        switch (i) {
            case 0:
                int abs = Math.abs(absListView.getFirstVisiblePosition() - this.firstVisiblePos);
                int abs2 = Math.abs(absListView.getLastVisiblePosition() - this.lastVisiblePos);
                if (abs > 0 || abs2 > 0) {
                    notifyDataSetChanged();
                    return;
                }
                return;
            case 1:
                this.firstVisiblePos = absListView.getFirstVisiblePosition();
                this.lastVisiblePos = absListView.getLastVisiblePosition();
                return;
            default:
                return;
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
