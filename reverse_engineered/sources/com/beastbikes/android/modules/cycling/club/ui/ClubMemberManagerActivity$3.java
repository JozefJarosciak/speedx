package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.framework.business.BusinessException;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class ClubMemberManagerActivity$3 extends AsyncTask<Void, Void, List<RankDTO>> {
    /* renamed from: a */
    final /* synthetic */ ClubMemberManagerActivity f9729a;

    ClubMemberManagerActivity$3(ClubMemberManagerActivity clubMemberManagerActivity) {
        this.f9729a = clubMemberManagerActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10834a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10835a((List) obj);
    }

    /* renamed from: a */
    protected List<RankDTO> m10834a(Void... voidArr) {
        List<RankDTO> list = null;
        if (!(ClubMemberManagerActivity.b(this.f9729a) == null || TextUtils.isEmpty(ClubMemberManagerActivity.c(this.f9729a)))) {
            try {
                list = ClubMemberManagerActivity.b(this.f9729a).m10540a(ClubMemberManagerActivity.c(this.f9729a), 1, 1, 1000);
            } catch (BusinessException e) {
            }
        }
        return list;
    }

    /* renamed from: a */
    protected void m10835a(List<RankDTO> list) {
        if (list != null && !list.isEmpty()) {
            double milestone;
            Collection arrayList = new ArrayList();
            for (RankDTO rankDTO : list) {
                if (rankDTO.isManager()) {
                    ClubMemberManagerActivity.a(this.f9729a, rankDTO);
                } else {
                    arrayList.add(rankDTO);
                }
            }
            ClubMemberManagerActivity.d(this.f9729a).clear();
            ClubMemberManagerActivity.d(this.f9729a).addAll(arrayList);
            ClubMemberManagerActivity.e(this.f9729a).notifyDataSetChanged();
            ClubMemberManagerActivity.g(this.f9729a).setText(ClubMemberManagerActivity.f(this.f9729a).getNickname());
            ClubMemberManagerActivity.h(this.f9729a).setText(this.f9729a.getResources().getString(C1373R.string.jointime) + C2555d.m12808c(C2555d.m12820h(ClubMemberManagerActivity.f(this.f9729a).getJoined())));
            if (ClubMemberManagerActivity.f(this.f9729a).getMilestone() > 0.0d) {
                milestone = ClubMemberManagerActivity.f(this.f9729a).getMilestone() / 1000.0d;
            } else {
                milestone = 0.0d;
            }
            if (C1849a.m9645b(this.f9729a)) {
                ClubMemberManagerActivity.i(this.f9729a).setText(String.format("%.1f", new Object[]{Double.valueOf(milestone)}) + " " + this.f9729a.getResources().getString(C1373R.string.str_mileage_unit_km));
            } else {
                ClubMemberManagerActivity.i(this.f9729a).setText(String.format("%.1f", new Object[]{Double.valueOf(C1849a.m9638a(milestone))}) + " " + this.f9729a.getResources().getString(C1373R.string.str_mileage_unit_mile));
            }
            if (TextUtils.isEmpty(ClubMemberManagerActivity.f(this.f9729a).getAvatarUrl())) {
                ClubMemberManagerActivity.j(this.f9729a).setImageResource(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(this.f9729a).load(ClubMemberManagerActivity.f(this.f9729a).getAvatarUrl()).fit().centerCrop().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).into(ClubMemberManagerActivity.j(this.f9729a));
            }
            ClubMemberManagerActivity.l(this.f9729a).setText((ClubMemberManagerActivity.d(this.f9729a).size() + 1) + "/" + ClubMemberManagerActivity.k(this.f9729a));
        }
    }
}
