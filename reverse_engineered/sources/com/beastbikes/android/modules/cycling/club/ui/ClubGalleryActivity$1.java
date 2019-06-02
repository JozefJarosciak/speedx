package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubGalleryActivity$1 extends AsyncTask<String, Void, List<ClubPhotoDTO>> {
    /* renamed from: a */
    final /* synthetic */ boolean f9666a;
    /* renamed from: b */
    final /* synthetic */ String f9667b;
    /* renamed from: c */
    final /* synthetic */ String f9668c;
    /* renamed from: d */
    final /* synthetic */ String f9669d;
    /* renamed from: e */
    final /* synthetic */ ClubGalleryActivity f9670e;

    ClubGalleryActivity$1(ClubGalleryActivity clubGalleryActivity, boolean z, String str, String str2, String str3) {
        this.f9670e = clubGalleryActivity;
        this.f9666a = z;
        this.f9667b = str;
        this.f9668c = str2;
        this.f9669d = str3;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10802a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10803a((List) obj);
    }

    protected void onPreExecute() {
        if (ClubGalleryActivity.a(this.f9670e) == null || (ClubGalleryActivity.a(this.f9670e).size() <= 0 && ClubGalleryActivity.b(this.f9670e))) {
            ClubGalleryActivity.a(this.f9670e, new C1802i(this.f9670e, this.f9670e.getString(C1373R.string.loading_msg), true));
            ClubGalleryActivity.c(this.f9670e).show();
            ClubGalleryActivity.a(this.f9670e, false);
        }
        if (!this.f9666a) {
            ClubGalleryActivity.d(this.f9670e).setVisibility(0);
            ClubGalleryActivity.d(this.f9670e).m13554a();
        }
    }

    /* renamed from: a */
    protected List<ClubPhotoDTO> m10802a(String... strArr) {
        try {
            String str = this.f9667b;
            if (!(this.f9666a || ClubGalleryActivity.a(this.f9670e) == null || ClubGalleryActivity.a(this.f9670e).size() <= 0)) {
                str = ((ClubPhotoDTO) ClubGalleryActivity.a(this.f9670e).get(ClubGalleryActivity.a(this.f9670e).size() - 1)).getCreateDate();
            }
            return ClubGalleryActivity.e(this.f9670e).m10576a(this.f9668c, str, this.f9669d, 30, this.f9670e, this.f9666a);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10803a(List<ClubPhotoDTO> list) {
        if (ClubGalleryActivity.c(this.f9670e) != null) {
            ClubGalleryActivity.c(this.f9670e).dismiss();
        }
        if (!this.f9666a) {
            ClubGalleryActivity.d(this.f9670e).m13555b();
            ClubGalleryActivity.d(this.f9670e).setVisibility(8);
        }
        ClubGalleryActivity.f(this.f9670e).setLoading(false);
        ClubGalleryActivity.f(this.f9670e).setRefreshing(false);
        if (ClubGalleryActivity.c(this.f9670e) != null) {
            ClubGalleryActivity.c(this.f9670e).dismiss();
        }
        if (list == null || list.isEmpty()) {
            ClubGalleryActivity.f(this.f9670e).setCanLoad(false);
        } else if (this.f9666a) {
            ClubGalleryActivity.a(this.f9670e).clear();
            ClubGalleryActivity.a(this.f9670e).addAll(list);
        } else {
            ClubGalleryActivity.a(this.f9670e).addAll(list);
        }
        ClubGalleryActivity.g(this.f9670e).notifyDataSetChanged();
        if (ClubGalleryActivity.a(this.f9670e) == null || ClubGalleryActivity.a(this.f9670e).isEmpty()) {
            ClubGalleryActivity.h(this.f9670e).setVisibility(0);
            ClubGalleryActivity.i(this.f9670e).setVisibility(8);
            ClubGalleryActivity.j(this.f9670e).setEnabled(false);
            return;
        }
        ClubGalleryActivity.h(this.f9670e).setVisibility(8);
        ClubGalleryActivity.i(this.f9670e).setVisibility(0);
        ClubGalleryActivity.j(this.f9670e).setEnabled(true);
    }
}
