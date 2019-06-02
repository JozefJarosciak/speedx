package com.beastbikes.android.modules.user.ui;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.C5421v;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

class WatermarkGalleryActivity$5 extends AsyncTask<Void, Void, List<Bitmap>> {
    /* renamed from: a */
    final /* synthetic */ List f11817a;
    /* renamed from: b */
    final /* synthetic */ GPUImageView f11818b;
    /* renamed from: c */
    final /* synthetic */ WatermarkGalleryActivity f11819c;

    WatermarkGalleryActivity$5(WatermarkGalleryActivity watermarkGalleryActivity, List list, GPUImageView gPUImageView) {
        this.f11819c = watermarkGalleryActivity;
        this.f11817a = list;
        this.f11818b = gPUImageView;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12553a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12554a((List) obj);
    }

    /* renamed from: a */
    protected List<Bitmap> m12553a(Void... voidArr) {
        List<Bitmap> arrayList = new ArrayList(5);
        for (C5421v filter : this.f11817a) {
            this.f11818b.setFilter(filter);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrayList.add(this.f11818b.getBitmapWithFilterApplied());
        }
        return arrayList;
    }

    /* renamed from: a */
    protected void m12554a(List<Bitmap> list) {
        ArrayList arrayList = (ArrayList) list;
        WatermarkGalleryActivity.f(this.f11819c).setImageBitmap((Bitmap) arrayList.get(0));
        WatermarkGalleryActivity.g(this.f11819c).setImageBitmap((Bitmap) arrayList.get(1));
        WatermarkGalleryActivity.h(this.f11819c).setImageBitmap((Bitmap) arrayList.get(2));
        WatermarkGalleryActivity.i(this.f11819c).setImageBitmap((Bitmap) arrayList.get(3));
        WatermarkGalleryActivity.j(this.f11819c).setImageBitmap((Bitmap) arrayList.get(4));
        this.f11818b.setVisibility(8);
    }
}
