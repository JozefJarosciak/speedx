package com.mapbox.mapboxsdk.annotations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mapbox.mapboxsdk.C1485R;
import com.mapbox.mapboxsdk.maps.MapboxMap$MarkerViewAdapter;

public class MarkerViewManager$ImageMarkerViewAdapter extends MapboxMap$MarkerViewAdapter<MarkerView> {
    private LayoutInflater inflater;

    /* renamed from: com.mapbox.mapboxsdk.annotations.MarkerViewManager$ImageMarkerViewAdapter$ViewHolder */
    private static class ViewHolder {
        ImageView imageView;

        private ViewHolder() {
        }
    }

    public MarkerViewManager$ImageMarkerViewAdapter(Context context) {
        super(context);
        this.inflater = LayoutInflater.from(context);
    }

    @Nullable
    public View getView(@NonNull MarkerView markerView, @Nullable View view, @NonNull ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            ViewHolder viewHolder2 = new ViewHolder();
            view = this.inflater.inflate(C1485R.layout.view_image_marker, viewGroup, false);
            viewHolder2.imageView = (ImageView) view.findViewById(C1485R.id.image);
            view.setTag(viewHolder2);
            viewHolder = viewHolder2;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageBitmap(markerView.getIcon().getBitmap());
        return view;
    }
}
