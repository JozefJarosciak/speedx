package com.mapbox.mapboxsdk.maps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.mapbox.mapboxsdk.R$array;
import com.mapbox.mapboxsdk.maps.MapView.AttributionOnClickListener;

class MapView$AttributionOnClickListener$2 implements OnClickListener {
    final /* synthetic */ AttributionOnClickListener this$0;
    final /* synthetic */ Context val$context;

    MapView$AttributionOnClickListener$2(AttributionOnClickListener attributionOnClickListener, Context context) {
        this.this$0 = attributionOnClickListener;
        this.val$context = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = this.val$context.getResources().getStringArray(R$array.attribution_links)[3];
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.val$context.startActivity(intent);
        dialogInterface.cancel();
    }
}
