package com.journeyapps.barcodescanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.zxing.PlanarYUVLuminanceSource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/* compiled from: SourceData */
/* renamed from: com.journeyapps.barcodescanner.m */
public class C4169m {
    /* renamed from: a */
    private byte[] f14830a;
    /* renamed from: b */
    private int f14831b;
    /* renamed from: c */
    private int f14832c;
    /* renamed from: d */
    private int f14833d;
    /* renamed from: e */
    private int f14834e;
    /* renamed from: f */
    private Rect f14835f;

    public C4169m(byte[] bArr, int i, int i2, int i3, int i4) {
        this.f14830a = bArr;
        this.f14831b = i;
        this.f14832c = i2;
        this.f14834e = i4;
        this.f14833d = i3;
    }

    /* renamed from: a */
    public void m16703a(Rect rect) {
        this.f14835f = rect;
    }

    /* renamed from: a */
    public boolean m16704a() {
        return this.f14834e % Opcodes.GETFIELD != 0;
    }

    /* renamed from: b */
    public PlanarYUVLuminanceSource m16705b() {
        byte[] a = C4169m.m16698a(this.f14834e, this.f14830a, this.f14831b, this.f14832c);
        if (m16704a()) {
            return new PlanarYUVLuminanceSource(a, this.f14832c, this.f14831b, this.f14835f.left, this.f14835f.top, this.f14835f.width(), this.f14835f.height(), false);
        }
        return new PlanarYUVLuminanceSource(a, this.f14831b, this.f14832c, this.f14835f.left, this.f14835f.top, this.f14835f.width(), this.f14835f.height(), false);
    }

    /* renamed from: a */
    public Bitmap m16702a(int i) {
        return m16697a(this.f14835f, i);
    }

    /* renamed from: a */
    private Bitmap m16697a(Rect rect, int i) {
        if (m16704a()) {
            rect = new Rect(rect.top, rect.left, rect.bottom, rect.right);
        }
        YuvImage yuvImage = new YuvImage(this.f14830a, this.f14833d, this.f14831b, this.f14832c, null);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(rect, 90, byteArrayOutputStream);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        Options options = new Options();
        options.inSampleSize = i;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, options);
        if (this.f14834e == 0) {
            return decodeByteArray;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) this.f14834e);
        return Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, false);
    }

    /* renamed from: a */
    public static byte[] m16698a(int i, byte[] bArr, int i2, int i3) {
        switch (i) {
            case 90:
                return C4169m.m16699a(bArr, i2, i3);
            case Opcodes.GETFIELD /*180*/:
                return C4169m.m16700b(bArr, i2, i3);
            case 270:
                return C4169m.m16701c(bArr, i2, i3);
            default:
                return bArr;
        }
    }

    /* renamed from: a */
    public static byte[] m16699a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[(i * i2)];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = i2 - 1; i5 >= 0; i5--) {
                bArr2[i3] = bArr[(i5 * i) + i4];
                i3++;
            }
        }
        return bArr2;
    }

    /* renamed from: b */
    public static byte[] m16700b(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[i3];
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i4] = bArr[i5];
            i4--;
        }
        return bArr2;
    }

    /* renamed from: c */
    public static byte[] m16701c(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[i3];
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < i; i5++) {
            for (i3 = i2 - 1; i3 >= 0; i3--) {
                bArr2[i4] = bArr[(i3 * i) + i5];
                i4--;
            }
        }
        return bArr2;
    }
}
