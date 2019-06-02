package io.rong.imkit.widget.provider;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import io.rong.imkit.RongKitIntent;
import io.rong.message.RichContentItem;
import java.util.ArrayList;

class PublicServiceMultiRichContentMessageProvider$1 implements OnItemClickListener {
    final /* synthetic */ PublicServiceMultiRichContentMessageProvider this$0;
    final /* synthetic */ ArrayList val$msgList;

    PublicServiceMultiRichContentMessageProvider$1(PublicServiceMultiRichContentMessageProvider publicServiceMultiRichContentMessageProvider, ArrayList arrayList) {
        this.this$0 = publicServiceMultiRichContentMessageProvider;
        this.val$msgList = arrayList;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String url = ((RichContentItem) this.val$msgList.get(i + 1)).getUrl();
        Intent intent = new Intent(RongKitIntent.RONG_INTENT_ACTION_WEBVIEW);
        intent.setPackage(PublicServiceMultiRichContentMessageProvider.access$000(this.this$0).getPackageName());
        intent.putExtra("url", url);
        PublicServiceMultiRichContentMessageProvider.access$000(this.this$0).startActivity(intent);
    }
}
