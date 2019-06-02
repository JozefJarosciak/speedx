package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1786a.C1784a;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.ui.ClubMemberManagerActivity.C1409b;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubMemberManagerActivity$b$2 implements C1784a {
    /* renamed from: a */
    final /* synthetic */ RankDTO f9737a;
    /* renamed from: b */
    final /* synthetic */ C1409b f9738b;

    ClubMemberManagerActivity$b$2(C1409b c1409b, RankDTO rankDTO) {
        this.f9738b = c1409b;
        this.f9737a = rankDTO;
    }

    /* renamed from: a */
    public void mo3403a(int i) {
        final ClubMemberManagerActivity clubMemberManagerActivity = (ClubMemberManagerActivity) this.f9738b.getContext();
        clubMemberManagerActivity.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, Boolean>(this) {
            /* renamed from: b */
            final /* synthetic */ ClubMemberManagerActivity$b$2 f9736b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10836a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m10837a((Boolean) obj);
            }

            /* renamed from: a */
            protected Boolean m10836a(Void... voidArr) {
                try {
                    if (C1409b.a(this.f9736b.f9738b) == null) {
                        C1409b.a(this.f9736b.f9738b, new ClubManager(clubMemberManagerActivity));
                    }
                    return Boolean.valueOf(C1409b.a(this.f9736b.f9738b).m10546b(this.f9736b.f9737a.getUserId(), 0));
                } catch (BusinessException e) {
                    return Boolean.valueOf(false);
                }
            }

            /* renamed from: a */
            protected void m10837a(Boolean bool) {
                if (bool.booleanValue()) {
                    Toasts.show(clubMemberManagerActivity, (int) C1373R.string.activity_member_manager_delete_success);
                    ClubMemberManagerActivity.d(clubMemberManagerActivity).remove(this.f9736b.f9737a);
                    ClubMemberManagerActivity.e(clubMemberManagerActivity).notifyDataSetChanged();
                    ClubMemberManagerActivity.n(this.f9736b.f9738b.f5162c);
                    ClubMemberManagerActivity.l(this.f9736b.f9738b.f5162c).setText((ClubMemberManagerActivity.d(this.f9736b.f9738b.f5162c).size() + 1) + "/" + ClubMemberManagerActivity.k(this.f9736b.f9738b.f5162c));
                    return;
                }
                Toasts.show(clubMemberManagerActivity, (int) C1373R.string.activity_member_manager_delete_failed);
            }
        }, new Void[0]);
    }

    /* renamed from: b */
    public void mo3404b(int i) {
    }
}
