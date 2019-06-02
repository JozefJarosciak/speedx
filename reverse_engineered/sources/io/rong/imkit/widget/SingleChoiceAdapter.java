package io.rong.imkit.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import java.util.ArrayList;
import java.util.List;

public class SingleChoiceAdapter<T> extends BaseAdapter {
    private int mCheckBoxResourceID = 0;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mObjects = new ArrayList();
    private int mSelectItem = -1;

    public static class ViewHolder {
        public CheckBox mCheckBox;
        public TextView mTextView;
    }

    public SingleChoiceAdapter(Context context, int i) {
        init(context, i);
    }

    public SingleChoiceAdapter(Context context, List<T> list, int i) {
        init(context, i);
        if (list != null) {
            this.mObjects = list;
        }
    }

    private void init(Context context, int i) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mCheckBoxResourceID = i;
    }

    public void refreshData(List<T> list) {
        if (list != null) {
            this.mObjects = list;
            setSelectItem(0);
        }
    }

    public void setSelectItem(int i) {
        if (i >= 0 && i < this.mObjects.size()) {
            this.mSelectItem = i;
            notifyDataSetChanged();
        }
    }

    public int getSelectItem() {
        return this.mSelectItem;
    }

    public void clear() {
        this.mObjects.clear();
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mObjects.size();
    }

    public T getItem(int i) {
        return this.mObjects.get(i);
    }

    public int getPosition(T t) {
        return this.mObjects.indexOf(t);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.mInflater.inflate(C4974R.layout.rc_cs_item_single_choice, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) view.findViewById(C4974R.id.rc_cs_tv_group_name);
            viewHolder.mCheckBox = (CheckBox) view.findViewById(C4974R.id.rc_cs_group_checkBox);
            view.setTag(viewHolder);
            if (this.mCheckBoxResourceID != 0) {
                viewHolder.mCheckBox.setButtonDrawable(this.mCheckBoxResourceID);
            }
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mCheckBox.setChecked(this.mSelectItem == i);
        Object item = getItem(i);
        if (item instanceof CharSequence) {
            viewHolder.mTextView.setText((CharSequence) item);
        } else {
            viewHolder.mTextView.setText(item.toString());
        }
        return view;
    }
}
