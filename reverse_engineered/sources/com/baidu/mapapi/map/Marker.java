package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import com.alipay.sdk.authjs.C0840a;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1252h;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

public final class Marker extends Overlay {
    /* renamed from: a */
    LatLng f3035a;
    /* renamed from: b */
    BitmapDescriptor f3036b;
    /* renamed from: c */
    float f3037c;
    /* renamed from: d */
    float f3038d;
    /* renamed from: e */
    boolean f3039e;
    /* renamed from: f */
    boolean f3040f;
    /* renamed from: g */
    float f3041g;
    /* renamed from: h */
    String f3042h;
    /* renamed from: i */
    int f3043i;
    /* renamed from: j */
    boolean f3044j;
    /* renamed from: k */
    boolean f3045k;
    /* renamed from: l */
    float f3046l;
    /* renamed from: m */
    int f3047m;
    /* renamed from: n */
    ArrayList<BitmapDescriptor> f3048n;
    /* renamed from: o */
    int f3049o;

    Marker() {
        this.f3044j = false;
        this.f3045k = false;
        this.f3049o = 20;
        this.type = C1252h.marker;
    }

    /* renamed from: a */
    private void m4154a(ArrayList<BitmapDescriptor> arrayList, Bundle bundle) {
        int i = 0;
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) it.next();
            ParcelItem parcelItem = new ParcelItem();
            Bundle bundle2 = new Bundle();
            Bitmap bitmap = bitmapDescriptor.f2880a;
            Buffer allocate = ByteBuffer.allocate((bitmap.getWidth() * bitmap.getHeight()) * 4);
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            bundle2.putByteArray("image_data", array);
            bundle2.putInt("image_width", bitmap.getWidth());
            bundle2.putInt("image_height", bitmap.getHeight());
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            messageDigest.update(array, 0, array.length);
            byte[] digest = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder("");
            for (byte b : digest) {
                stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            bundle2.putString("image_hashcode", stringBuilder.toString());
            parcelItem.setBundle(bundle2);
            arrayList2.add(parcelItem);
        }
        if (arrayList2.size() > 0) {
            Parcelable[] parcelableArr = new ParcelItem[arrayList2.size()];
            while (i < arrayList2.size()) {
                parcelableArr[i] = (ParcelItem) arrayList2.get(i);
                i++;
            }
            bundle.putParcelableArray("icons", parcelableArr);
        }
    }

    /* renamed from: a */
    Bundle mo2613a(Bundle bundle) {
        int i = 1;
        super.mo2613a(bundle);
        Bundle bundle2 = new Bundle();
        if (this.f3036b != null) {
            bundle.putBundle("image_info", this.f3036b.m4093b());
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(this.f3035a);
        bundle.putInt("animatetype", this.f3047m);
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt("perspective", this.f3039e ? 1 : 0);
        bundle.putFloat("anchor_x", this.f3037c);
        bundle.putFloat("anchor_y", this.f3038d);
        bundle.putFloat("rotate", this.f3041g);
        bundle.putInt("y_offset", this.f3043i);
        bundle.putInt("isflat", this.f3044j ? 1 : 0);
        String str = "istop";
        if (!this.f3045k) {
            i = 0;
        }
        bundle.putInt(str, i);
        bundle.putInt("period", this.f3049o);
        bundle.putFloat("alpha", this.f3046l);
        if (this.f3048n != null && this.f3048n.size() > 0) {
            m4154a(this.f3048n, bundle);
        }
        bundle2.putBundle(C0840a.f2030f, bundle);
        return bundle;
    }

    public float getAlpha() {
        return this.f3046l;
    }

    public float getAnchorX() {
        return this.f3037c;
    }

    public float getAnchorY() {
        return this.f3038d;
    }

    public BitmapDescriptor getIcon() {
        return this.f3036b;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.f3048n;
    }

    public int getPeriod() {
        return this.f3049o;
    }

    public LatLng getPosition() {
        return this.f3035a;
    }

    public float getRotate() {
        return this.f3041g;
    }

    public String getTitle() {
        return this.f3042h;
    }

    public boolean isDraggable() {
        return this.f3040f;
    }

    public boolean isFlat() {
        return this.f3044j;
    }

    public boolean isPerspective() {
        return this.f3039e;
    }

    public void setAlpha(float f) {
        if (f < 0.0f || ((double) f) > 1.0d) {
            this.f3046l = 1.0f;
            return;
        }
        this.f3046l = f;
        this.listener.mo2624b(this);
    }

    public void setAnchor(float f, float f2) {
        if (f >= 0.0f && f <= 1.0f && f2 >= 0.0f && f2 <= 1.0f) {
            this.f3037c = f;
            this.f3038d = f2;
            this.listener.mo2624b(this);
        }
    }

    public void setDraggable(boolean z) {
        this.f3040f = z;
        this.listener.mo2624b(this);
    }

    public void setFlat(boolean z) {
        this.f3044j = z;
        this.listener.mo2624b(this);
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("marker's icon can not be null");
        }
        this.f3036b = bitmapDescriptor;
        this.listener.mo2624b(this);
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList == null) {
            throw new IllegalArgumentException("marker's icons can not be null");
        } else if (arrayList.size() != 0) {
            int i = 0;
            while (i < arrayList.size()) {
                if (arrayList.get(i) != null && ((BitmapDescriptor) arrayList.get(i)).f2880a != null) {
                    i++;
                } else {
                    return;
                }
            }
            this.f3048n = arrayList;
            this.listener.mo2624b(this);
        }
    }

    public void setPeriod(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("marker's period must be greater than zero ");
        }
        this.f3049o = i;
        this.listener.mo2624b(this);
    }

    public void setPerspective(boolean z) {
        this.f3039e = z;
        this.listener.mo2624b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("marker's position can not be null");
        }
        this.f3035a = latLng;
        this.listener.mo2624b(this);
    }

    public void setRotate(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        this.f3041g = f % 360.0f;
        this.listener.mo2624b(this);
    }

    public void setTitle(String str) {
        this.f3042h = str;
    }

    public void setToTop() {
        this.f3045k = true;
        this.listener.mo2624b(this);
    }
}
