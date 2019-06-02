package com.mapbox.services.android.geocoder.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import com.mapbox.services.android.C4202R;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.geocoding.v5.models.CarmenFeature;

public class GeocoderAutoCompleteView extends AutoCompleteTextView {
    private static final int DEFAULT_NUMBER_OF_LINES = 1;
    private GeocoderAdapter adapter;
    private OnFeatureListener onFeatureListener = null;

    /* renamed from: com.mapbox.services.android.geocoder.ui.GeocoderAutoCompleteView$1 */
    class C42031 implements OnItemClickListener {
        C42031() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            CarmenFeature item = GeocoderAutoCompleteView.this.adapter.getItem(i);
            GeocoderAutoCompleteView.this.setText(item.toString());
            if (GeocoderAutoCompleteView.this.onFeatureListener != null) {
                GeocoderAutoCompleteView.this.onFeatureListener.OnFeatureClick(item);
            }
        }
    }

    public interface OnFeatureListener {
        void OnFeatureClick(CarmenFeature carmenFeature);
    }

    public GeocoderAutoCompleteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.adapter = new GeocoderAdapter(context);
        setAdapter(this.adapter);
        setLines(1);
        setOnItemClickListener(new C42031());
        final Drawable drawable = ContextCompat.getDrawable(context, C4202R.drawable.ic_clear_black_24dp);
        setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                GeocoderAutoCompleteView geocoderAutoCompleteView = (GeocoderAutoCompleteView) view;
                if (geocoderAutoCompleteView.getCompoundDrawables()[2] != null && motionEvent.getAction() == 1 && motionEvent.getX() > ((float) ((geocoderAutoCompleteView.getWidth() - geocoderAutoCompleteView.getPaddingRight()) - drawable.getIntrinsicWidth()))) {
                    GeocoderAutoCompleteView.this.setText("");
                }
                return false;
            }
        });
    }

    public void setAccessToken(String str) {
        this.adapter.setAccessToken(str);
    }

    public void setType(String str) {
        this.adapter.setType(str);
    }

    public void setBbox(Position position, Position position2) {
        this.adapter.setBbox(position2.getLongitude(), position2.getLatitude(), position.getLongitude(), position.getLatitude());
    }

    public void setBbox(double d, double d2, double d3, double d4) {
        this.adapter.setBbox(d, d2, d3, d4);
    }

    public void setProximity(Position position) {
        this.adapter.setProximity(position);
    }

    public void setOnFeatureListener(OnFeatureListener onFeatureListener) {
        this.onFeatureListener = onFeatureListener;
    }
}
