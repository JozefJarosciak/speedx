package com.beastbikes.android.modules.user.ui;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.C5421v;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

class WatermarkGalleryActivity$7 extends AsyncTask<Void, Void, List<Bitmap>> {
    /* renamed from: a */
    final /* synthetic */ List f11823a;
    /* renamed from: b */
    final /* synthetic */ GPUImageView f11824b;
    /* renamed from: c */
    final /* synthetic */ WatermarkGalleryActivity f11825c;

    WatermarkGalleryActivity$7(WatermarkGalleryActivity watermarkGalleryActivity, List list, GPUImageView gPUImageView) {
        this.f11825c = watermarkGalleryActivity;
        this.f11823a = list;
        this.f11824b = gPUImageView;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12557a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12558a((List) obj);
    }

    /* renamed from: a */
    protected List<Bitmap> m12557a(Void... voidArr) {
        List<Bitmap> arrayList = new ArrayList(5);
        for (C5421v filter : this.f11823a) {
            this.f11824b.setFilter(filter);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrayList.add(this.f11824b.getBitmapWithFilterApplied());
        }
        return arrayList;
    }

    /* renamed from: a */
    protected void m12558a(List<Bitmap> list) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = (ArrayList) list;
            WatermarkGalleryActivity.p(this.f11825c).setImageBitmap((Bitmap) arrayList.get(0));
            WatermarkGalleryActivity.q(this.f11825c).setImageBitmap((Bitmap) arrayList.get(1));
            WatermarkGalleryActivity.r(this.f11825c).setImageBitmap((Bitmap) arrayList.get(2));
            WatermarkGalleryActivity.s(this.f11825c).setImageBitmap((Bitmap) arrayList.get(3));
            WatermarkGalleryActivity.t(this.f11825c).setImageBitmap((Bitmap) arrayList.get(4));
            this.f11824b.setVisibility(8);
        }
    }
}
