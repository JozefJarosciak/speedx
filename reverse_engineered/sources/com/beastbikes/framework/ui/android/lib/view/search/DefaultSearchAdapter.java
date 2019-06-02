package com.beastbikes.framework.ui.android.lib.view.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.framework.ui.android.C2824R;

@SuppressLint({"InflateParams"})
public class DefaultSearchAdapter extends BaseSearchBarAdapter {
    private Context context;

    static class ViewHolder {
        TextView tv;

        ViewHolder() {
        }
    }

    public DefaultSearchAdapter(Context context, MySearchListener mySearchListener) {
        super(mySearchListener);
        this.context = context;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C2824R.layout.search_history_item, null);
            viewHolder.tv = (TextView) view.findViewById(C2824R.id.history_item_content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        CharSequence charSequence = getStringFromItem(getItem(i)).toString();
        LayoutParams layoutParams;
        if (isClear(i)) {
            layoutParams = (LayoutParams) viewHolder.tv.getLayoutParams();
            layoutParams.gravity = 17;
            viewHolder.tv.setGravity(17);
            viewHolder.tv.setLayoutParams(layoutParams);
        } else {
            layoutParams = (LayoutParams) viewHolder.tv.getLayoutParams();
            layoutParams.gravity = 16;
            viewHolder.tv.setLayoutParams(layoutParams);
            viewHolder.tv.setGravity(19);
        }
        viewHolder.tv.setText(charSequence);
        return view;
    }
}
