package io.rong.imkit.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import io.rong.imkit.C4974R;
import io.rong.imkit.model.Emoji;
import io.rong.imkit.utils.AndroidEmoji;

public class EmojiAdapter extends BaseAdapter {
    private static final int EMOJI_PER_PAGE = 20;
    private Context mContext;
    private int mStartIndex;

    public EmojiAdapter(Context context, int i) {
        this.mContext = context;
        this.mStartIndex = i;
    }

    public int getCount() {
        return Math.min((AndroidEmoji.getEmojiList().size() - this.mStartIndex) + 1, 21);
    }

    public Object getItem(int i) {
        return Integer.valueOf(((Emoji) AndroidEmoji.getEmojiList().get(this.mStartIndex + i)).getRes());
    }

    public long getItemId(int i) {
        return (long) (this.mStartIndex + i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(C4974R.layout.rc_emoji_item, null);
        }
        ImageView imageView = (ImageView) view.findViewById(C4974R.id.rc_emoji_item);
        int size = AndroidEmoji.getEmojiList().size();
        int i2 = this.mStartIndex + i;
        if (i == 20 || i2 == size) {
            imageView.setImageResource(C4974R.drawable.rc_ic_delete);
        } else if (i2 < size) {
            imageView.setImageResource(((Emoji) AndroidEmoji.getEmojiList().get(i2)).getRes());
        }
        return view;
    }
}
