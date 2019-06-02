package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.HistogramDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;

class ClubFeedInfoFrag$7 extends AsyncTask<String, Void, HistogramDTO> {
    /* renamed from: a */
    final /* synthetic */ C2389c f9646a;
    /* renamed from: b */
    final /* synthetic */ String f9647b;
    /* renamed from: c */
    final /* synthetic */ ClubFeedInfoFrag f9648c;

    ClubFeedInfoFrag$7(ClubFeedInfoFrag clubFeedInfoFrag, C2389c c2389c, String str) {
        this.f9648c = clubFeedInfoFrag;
        this.f9646a = c2389c;
        this.f9647b = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10796a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10797a((HistogramDTO) obj);
    }

    /* renamed from: a */
    protected HistogramDTO m10796a(String... strArr) {
        return this.f9646a.m12119a(1, this.f9647b, 30);
    }

    /* renamed from: a */
    protected void m10797a(HistogramDTO histogramDTO) {
        ClubFeedInfoFrag.l(this.f9648c).setHistogramDTO(histogramDTO);
    }
}
