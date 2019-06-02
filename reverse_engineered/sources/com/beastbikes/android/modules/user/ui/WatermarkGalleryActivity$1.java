package com.beastbikes.android.modules.user.ui;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.C5421v;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

class WatermarkGalleryActivity$1 extends AsyncTask<Void, Void, List<Bitmap>> {
    /* renamed from: a */
    final /* synthetic */ List f11805a;
    /* renamed from: b */
    final /* synthetic */ GPUImageView f11806b;
    /* renamed from: c */
    final /* synthetic */ WatermarkGalleryActivity f11807c;

    WatermarkGalleryActivity$1(WatermarkGalleryActivity watermarkGalleryActivity, List list, GPUImageView gPUImageView) {
        this.f11807c = watermarkGalleryActivity;
        this.f11805a = list;
        this.f11806b = gPUImageView;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12551a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12552a((List) obj);
    }

    /* renamed from: a */
    protected List<Bitmap> m12551a(Void... voidArr) {
        List<Bitmap> arrayList = new ArrayList(5);
        for (C5421v filter : this.f11805a) {
            this.f11806b.setFilter(filter);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrayList.add(this.f11806b.getBitmapWithFilterApplied());
        }
        return arrayList;
    }

    /* renamed from: a */
    protected void m12552a(List<Bitmap> list) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = (ArrayList) list;
            WatermarkGalleryActivity.a(this.f11807c).setImageBitmap((Bitmap) arrayList.get(0));
            WatermarkGalleryActivity.b(this.f11807c).setImageBitmap((Bitmap) arrayList.get(1));
            WatermarkGalleryActivity.c(this.f11807c).setImageBitmap((Bitmap) arrayList.get(2));
            WatermarkGalleryActivity.d(this.f11807c).setImageBitmap((Bitmap) arrayList.get(3));
            WatermarkGalleryActivity.e(this.f11807c).setImageBitmap((Bitmap) arrayList.get(4));
            this.f11806b.setVisibility(8);
        }
    }
}
