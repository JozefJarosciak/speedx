package com.twitter.sdk.android.tweetcomposer;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import com.squareup.picasso.Transformation;
import java.util.Arrays;

/* compiled from: RoundedCornerTransformation */
/* renamed from: com.twitter.sdk.android.tweetcomposer.i */
class C4685i implements Transformation {
    /* renamed from: a */
    final float[] f16473a;

    /* compiled from: RoundedCornerTransformation */
    /* renamed from: com.twitter.sdk.android.tweetcomposer.i$a */
    public static class C4684a {
        /* renamed from: a */
        int f16469a;
        /* renamed from: b */
        int f16470b;
        /* renamed from: c */
        int f16471c;
        /* renamed from: d */
        int f16472d;

        /* renamed from: a */
        public C4684a m18468a(int i, int i2, int i3, int i4) {
            this.f16469a = i;
            this.f16470b = i2;
            this.f16471c = i3;
            this.f16472d = i4;
            return this;
        }

        /* renamed from: a */
        C4685i m18469a() {
            if (this.f16469a < 0 || this.f16470b < 0 || this.f16471c < 0 || this.f16472d < 0) {
                throw new IllegalStateException("Radius must not be negative");
            }
            return new C4685i(new float[]{(float) this.f16469a, (float) this.f16469a, (float) this.f16470b, (float) this.f16470b, (float) this.f16471c, (float) this.f16471c, (float) this.f16472d, (float) this.f16472d});
        }
    }

    C4685i(float[] fArr) {
        this.f16473a = fArr;
    }

    public Bitmap transform(Bitmap bitmap) {
        RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Shader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        Path path = new Path();
        path.addRoundRect(rectF, this.f16473a, Direction.CCW);
        new Canvas(createBitmap).drawPath(path, paint);
        bitmap.recycle();
        return createBitmap;
    }

    public String key() {
        return "RoundedCornerTransformation(" + Arrays.toString(this.f16473a) + ")";
    }
}
