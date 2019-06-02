package com.beastbikes.framework.ui.android.lib.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView.RecyclerListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.Groupable;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.OnGroupListener;
import java.util.ArrayList;

public abstract class BaseSectionListAdapter<G extends Groupable<C>, C> extends BaseExpandableListAdapter implements OnScrollListener, RecyclerListener {
    private OnAdapterChangeListener changedListener;
    protected Context context;
    protected int firstVisiblePos = -1;
    private OnGroupListener groupListener;
    protected ArrayList<G> groups;
    protected LayoutInflater inflater;
    protected int lastVisiblePos = -1;
    private ExpandableListView listview;
    protected int scrollState;

    public abstract View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup);

    public abstract View getGroupView(int i, boolean z, View view, ViewGroup viewGroup);

    public abstract void recycleView(View view);

    public BaseSectionListAdapter(Context context, ExpandableListView expandableListView, ArrayList<G> arrayList) {
        this.context = context;
        this.groups = arrayList;
        this.inflater = LayoutInflater.from(context);
        this.listview = expandableListView;
        this.listview.setRecyclerListener(this);
        this.listview.setOnScrollListener(this);
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        expandAll();
        if (this.changedListener != null) {
            this.changedListener.onDataChanged(this.groups == null ? 0 : this.groups.size());
        }
    }

    public void setDataChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        this.changedListener = onAdapterChangeListener;
    }

    public void onGroupCollapsed(int i) {
        if (this.groupListener != null) {
            this.groupListener.onGroupCollapsed(i);
        }
    }

    public void onGroupExpanded(int i) {
        if (this.groupListener != null) {
            this.groupListener.onGroupExpanded(i);
        }
    }

    public G getGroup(int i) {
        if (this.groups == null || this.groups.size() <= i) {
            return null;
        }
        return (Groupable) this.groups.get(i);
    }

    public int getGroupCount() {
        return this.groups != null ? this.groups.size() : 0;
    }

    public ArrayList<G> getGroups() {
        return this.groups;
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public C getChild(int i, int i2) {
        Groupable group = getGroup(i);
        if (group != null) {
            ArrayList children = group.getChildren();
            if (children != null && children.size() > i2) {
                return children.get(i2);
            }
        }
        return null;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public int getChildrenCount(int i) {
        Groupable group = getGroup(i);
        if (group != null) {
            ArrayList children = group.getChildren();
            if (children != null) {
                return children.size();
            }
        }
        return 0;
    }

    public void onMovedToScrapHeap(View view) {
        if (view != null) {
            recycleView(view);
        }
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    public void add(ArrayList<G> arrayList) {
        if (arrayList == null || arrayList.size() < 1) {
            notifyDataSetChanged();
            return;
        }
        if (this.groups == null) {
            this.groups = new ArrayList();
        }
        if (this.groups.size() < 1) {
            this.groups.addAll(arrayList);
            notifyDataSetChanged();
            return;
        }
        Groupable group = getGroup(this.groups.size() - 1);
        Groupable groupable = (Groupable) arrayList.get(0);
        if (isSameSection(group, groupable)) {
            arrayList.remove(groupable);
            group.addChildren(groupable.getChildren());
        }
        this.groups.addAll(arrayList);
        notifyDataSetChanged();
    }

    protected boolean isSameSection(G g, G g2) {
        return false;
    }

    public void clear() {
        if (this.groups != null) {
            this.groups.clear();
        }
    }

    public void expandAll() {
        int groupCount = getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            if (this.listview.getExpandableListAdapter() != null) {
                this.listview.expandGroup(i);
            }
        }
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
