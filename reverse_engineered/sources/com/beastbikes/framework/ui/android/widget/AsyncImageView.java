package com.beastbikes.framework.ui.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.beastbikes.framework.android.p176b.C2790a;
import java.io.File;

public class AsyncImageView extends NetworkImageView {
    private Bitmap local;

    public AsyncImageView(Context context) {
        this(context, null);
    }

    public AsyncImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AsyncImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageBitmap(File file) {
        setImageBitmap(file.getAbsolutePath());
    }

    public void setImageBitmap(String str) {
        C2790a a = C2790a.m13728a();
        this.local = a.getBitmap(str);
        if (this.local == null) {
            this.local = a.m13733b(str);
        }
        requestLayout();
    }

    public void setImageUrl(String str, ImageLoader imageLoader) {
        this.local = null;
        super.setImageUrl(str, imageLoader);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.local != null) {
            super.setImageBitmap(this.local);
        }
    }
}
