package io.rong.imkit.widget.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import io.rong.imkit.mention.IMemberMentionedListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private Context mContext;
    private List<T> mList = new ArrayList();
    protected IMemberMentionedListener mMentionMemberSelectListener;

    protected abstract void bindView(View view, int i, T t);

    protected abstract View newView(Context context, int i, ViewGroup viewGroup);

    public BaseAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(IMemberMentionedListener iMemberMentionedListener) {
        this.mMentionMemberSelectListener = iMemberMentionedListener;
    }

    protected <T extends View> T findViewById(View view, int i) {
        return view.findViewById(i);
    }

    public int findPosition(T t) {
        int count = getCount();
        while (true) {
            int i = count - 1;
            if (count <= 0) {
                return -1;
            }
            if (t.equals(getItem(i))) {
                return i;
            }
            count = i;
        }
    }

    public int findPosition(long j) {
        int count = getCount();
        while (true) {
            int i = count - 1;
            if (count <= 0) {
                return -1;
            }
            if (getItemId(i) == j) {
                return i;
            }
            count = i;
        }
    }

    public void addCollection(Collection<T> collection) {
        this.mList.addAll(collection);
    }

    public void addCollection(T... tArr) {
        for (Object add : tArr) {
            this.mList.add(add);
        }
    }

    public void add(T t) {
        this.mList.add(t);
    }

    public void add(T t, int i) {
        this.mList.add(i, t);
    }

    public void remove(int i) {
        this.mList.remove(i);
    }

    public void removeAll() {
        this.mList.clear();
    }

    public void clear() {
        this.mList.clear();
    }

    public int getCount() {
        if (this.mList == null) {
            return 0;
        }
        return this.mList.size();
    }

    public T getItem(int i) {
        if (this.mList != null && i < this.mList.size()) {
            return this.mList.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = newView(this.mContext, i, viewGroup);
        }
        bindView(view, i, getItem(i));
        return view;
    }
}
