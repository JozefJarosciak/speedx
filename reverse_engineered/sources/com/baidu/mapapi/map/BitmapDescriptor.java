package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class BitmapDescriptor {
    /* renamed from: a */
    Bitmap f2880a;
    /* renamed from: b */
    private Bundle f2881b;

    BitmapDescriptor(Bitmap bitmap) {
        if (bitmap != null) {
            this.f2880a = m4091a(bitmap, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    /* renamed from: a */
    private Bitmap m4091a(Bitmap bitmap, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    /* renamed from: a */
    byte[] m4092a() {
        Buffer allocate = ByteBuffer.allocate((this.f2880a.getWidth() * this.f2880a.getHeight()) * 4);
        this.f2880a.copyPixelsToBuffer(allocate);
        return allocate.array();
    }

    /* renamed from: b */
    Bundle m4093b() {
        if (this.f2880a == null) {
            throw new IllegalStateException("the bitmap has been recycled! you can not use it again");
        }
        if (this.f2881b == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("image_width", this.f2880a.getWidth());
            bundle.putInt("image_height", this.f2880a.getHeight());
            byte[] a = m4092a();
            bundle.putByteArray("image_data", a);
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            messageDigest.update(a, 0, a.length);
            byte[] digest = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder("");
            for (byte b : digest) {
                stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            bundle.putString("image_hashcode", stringBuilder.toString());
            this.f2881b = bundle;
        }
        return this.f2881b;
    }

    public Bitmap getBitmap() {
        return this.f2880a;
    }

    public void recycle() {
        if (this.f2880a != null && !this.f2880a.isRecycled()) {
            this.f2880a.recycle();
            this.f2880a = null;
        }
    }
}
