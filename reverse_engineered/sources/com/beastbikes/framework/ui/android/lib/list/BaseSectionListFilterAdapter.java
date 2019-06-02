package com.beastbikes.framework.ui.android.lib.list;

import android.content.Context;
import android.widget.AbsListView.RecyclerListener;
import android.widget.ExpandableListView;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.Groupable;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseSectionListFilterAdapter<G extends Groupable<C>, C> extends BaseSectionListAdapter<G, C> implements RecyclerListener {
    private ArrayList<ArrayList<Integer>> childIndexes = new ArrayList();
    private ArrayList<Integer> groupIndexes = new ArrayList();
    protected String keyword = null;

    public BaseSectionListFilterAdapter(Context context, ExpandableListView expandableListView, ArrayList<G> arrayList) {
        super(context, expandableListView, arrayList);
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    private void filter() {
        System.currentTimeMillis();
        this.groupIndexes.clear();
        this.childIndexes.clear();
        if (this.groups != null) {
            Iterator it = this.groups.iterator();
            int i = 0;
            while (it.hasNext()) {
                ArrayList children = ((Groupable) it.next()).getChildren();
                if (children != null) {
                    Object obj = null;
                    Iterator it2 = children.iterator();
                    int i2 = 0;
                    while (it2.hasNext()) {
                        if (isChildMatched(it2.next(), this.keyword)) {
                            if (obj == null) {
                                obj = new ArrayList();
                            }
                            obj.add(Integer.valueOf(i2));
                        }
                        i2++;
                    }
                    if (obj != null) {
                        this.groupIndexes.add(Integer.valueOf(i));
                        this.childIndexes.add(obj);
                    }
                }
                i++;
            }
        }
    }

    protected boolean isChildMatched(C c, String str) {
        return true;
    }

    public void notifyDataSetChanged() {
        filter();
        super.notifyDataSetChanged();
    }

    public int getGroupCount() {
        return this.groupIndexes.size();
    }

    public G getGroup(int i) {
        if (this.groups == null || this.groupIndexes.size() <= i) {
            return null;
        }
        return (Groupable) this.groups.get(((Integer) this.groupIndexes.get(i)).intValue());
    }

    public C getChild(int i, int i2) {
        Groupable group = getGroup(i);
        if (group != null) {
            ArrayList children = group.getChildren();
            ArrayList arrayList = (ArrayList) this.childIndexes.get(i);
            if (children != null && arrayList.size() > i2) {
                return children.get(((Integer) arrayList.get(i2)).intValue());
            }
        }
        return null;
    }

    public int getChildrenCount(int i) {
        if (getGroup(i) != null) {
            return ((ArrayList) this.childIndexes.get(i)).size();
        }
        return 0;
    }
}
