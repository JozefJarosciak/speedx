package com.beastbikes.android.modules.user.ui;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.C5421v;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

class WatermarkGalleryActivity$6 extends AsyncTask<Void, Void, List<Bitmap>> {
    /* renamed from: a */
    final /* synthetic */ List f11820a;
    /* renamed from: b */
    final /* synthetic */ GPUImageView f11821b;
    /* renamed from: c */
    final /* synthetic */ WatermarkGalleryActivity f11822c;

    WatermarkGalleryActivity$6(WatermarkGalleryActivity watermarkGalleryActivity, List list, GPUImageView gPUImageView) {
        this.f11822c = watermarkGalleryActivity;
        this.f11820a = list;
        this.f11821b = gPUImageView;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12555a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12556a((List) obj);
    }

    /* renamed from: a */
    protected List<Bitmap> m12555a(Void... voidArr) {
        List<Bitmap> arrayList = new ArrayList(5);
        for (C5421v filter : this.f11820a) {
            this.f11821b.setFilter(filter);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrayList.add(this.f11821b.getBitmapWithFilterApplied());
        }
        return arrayList;
    }

    /* renamed from: a */
    protected void m12556a(List<Bitmap> list) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = (ArrayList) list;
            WatermarkGalleryActivity.k(this.f11822c).setImageBitmap((Bitmap) arrayList.get(0));
            WatermarkGalleryActivity.l(this.f11822c).setImageBitmap((Bitmap) arrayList.get(1));
            WatermarkGalleryActivity.m(this.f11822c).setImageBitmap((Bitmap) arrayList.get(2));
            WatermarkGalleryActivity.n(this.f11822c).setImageBitmap((Bitmap) arrayList.get(3));
            WatermarkGalleryActivity.o(this.f11822c).setImageBitmap((Bitmap) arrayList.get(4));
            this.f11821b.setVisibility(8);
        }
    }
}
