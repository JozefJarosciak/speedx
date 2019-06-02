package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ApplyDTO;
import com.beastbikes.android.modules.cycling.club.ui.ApplyManagerActivity.C1405b;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ApplyManagerActivity$b$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ApplyDTO f9412a;
    /* renamed from: b */
    final /* synthetic */ C1405b f9413b;

    ApplyManagerActivity$b$2(C1405b c1405b, ApplyDTO applyDTO) {
        this.f9413b = c1405b;
        this.f9412a = applyDTO;
    }

    public void onClick(View view) {
        final ApplyManagerActivity applyManagerActivity = (ApplyManagerActivity) this.f9413b.getContext();
        C2580w.m12905a(applyManagerActivity, "同意入队申请", null);
        if (ApplyManagerActivity.c(applyManagerActivity) >= ApplyManagerActivity.d(applyManagerActivity)) {
            C1405b.a(this.f9413b);
        } else {
            applyManagerActivity.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, Boolean>(this) {
                /* renamed from: b */
                final /* synthetic */ ApplyManagerActivity$b$2 f9411b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m10657a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m10658a((Boolean) obj);
                }

                /* renamed from: a */
                protected Boolean m10657a(Void... voidArr) {
                    try {
                        return Boolean.valueOf(C1405b.b(this.f9411b.f9413b).m10542a(this.f9411b.f9412a.getObjectId(), 0));
                    } catch (BusinessException e) {
                        return Boolean.valueOf(false);
                    }
                }

                /* renamed from: a */
                protected void m10658a(Boolean bool) {
                    if (bool.booleanValue()) {
                        C1405b.c(this.f9411b.f9413b).setVisibility(0);
                        Toasts.show(this.f9411b.f9413b.getContext(), (int) C1373R.string.activity_club_apply_item_status_agreed);
                        C1405b.d(this.f9411b.f9413b).setVisibility(8);
                        C1405b.e(this.f9411b.f9413b).setVisibility(8);
                        C1405b.c(this.f9411b.f9413b).setText(C1373R.string.activity_club_apply_item_status_agreed);
                        this.f9411b.f9412a.setStatus(1);
                        ApplyManagerActivity.f(applyManagerActivity).notifyDataSetChanged();
                        ApplyManagerActivity.h(applyManagerActivity);
                        return;
                    }
                    Toasts.show(this.f9411b.f9413b.getContext(), (int) C1373R.string.activity_club_apply_item_error);
                }
            }, new Void[0]);
        }
    }
}
