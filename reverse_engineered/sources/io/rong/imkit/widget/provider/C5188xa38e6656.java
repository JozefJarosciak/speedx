package io.rong.imkit.widget.provider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.message.RichContentItem;
import java.util.ArrayList;

/* renamed from: io.rong.imkit.widget.provider.PublicServiceMultiRichContentMessageProvider$PublicAccountMsgAdapter */
class C5188xa38e6656 extends BaseAdapter {
    LayoutInflater inflater;
    int itemCount;
    ArrayList<RichContentItem> itemList = new ArrayList();
    final /* synthetic */ PublicServiceMultiRichContentMessageProvider this$0;

    public C5188xa38e6656(PublicServiceMultiRichContentMessageProvider publicServiceMultiRichContentMessageProvider, Context context, ArrayList<RichContentItem> arrayList) {
        this.this$0 = publicServiceMultiRichContentMessageProvider;
        this.inflater = LayoutInflater.from(context);
        this.itemList.addAll(arrayList);
        this.itemCount = arrayList.size() - 1;
    }

    public int getCount() {
        return this.itemCount;
    }

    public RichContentItem getItem(int i) {
        if (this.itemList.size() == 0) {
            return null;
        }
        return (RichContentItem) this.itemList.get(i + 1);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(C4974R.layout.rc_item_public_service_message, null);
        AsyncImageView asyncImageView = (AsyncImageView) inflate.findViewById(C4974R.id.rc_img);
        TextView textView = (TextView) inflate.findViewById(C4974R.id.rc_txt);
        if (this.itemList.size() == 0) {
            return null;
        }
        CharSequence title = ((RichContentItem) this.itemList.get(i + 1)).getTitle();
        if (title != null) {
            textView.setText(title);
        }
        asyncImageView.setResource(((RichContentItem) this.itemList.get(i + 1)).getImageUrl(), 0);
        return inflate;
    }
}
