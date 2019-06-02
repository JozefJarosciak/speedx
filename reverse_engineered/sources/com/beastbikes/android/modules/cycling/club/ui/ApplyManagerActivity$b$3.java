package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ApplyDTO;
import com.beastbikes.android.modules.cycling.club.ui.ApplyManagerActivity.C1405b;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ApplyManagerActivity$b$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ApplyDTO f9416a;
    /* renamed from: b */
    final /* synthetic */ C1405b f9417b;

    ApplyManagerActivity$b$3(C1405b c1405b, ApplyDTO applyDTO) {
        this.f9417b = c1405b;
        this.f9416a = applyDTO;
    }

    public void onClick(View view) {
        final ApplyManagerActivity applyManagerActivity = (ApplyManagerActivity) this.f9417b.getContext();
        applyManagerActivity.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, Boolean>(this) {
            /* renamed from: b */
            final /* synthetic */ ApplyManagerActivity$b$3 f9415b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m10659a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m10660a((Boolean) obj);
            }

            /* renamed from: a */
            protected Boolean m10659a(Void... voidArr) {
                try {
                    return Boolean.valueOf(C1405b.b(this.f9415b.f9417b).m10542a(this.f9415b.f9416a.getObjectId(), 1));
                } catch (BusinessException e) {
                    return Boolean.valueOf(false);
                }
            }

            /* renamed from: a */
            protected void m10660a(Boolean bool) {
                if (bool.booleanValue()) {
                    C1405b.c(this.f9415b.f9417b).setVisibility(0);
                    Toasts.show(this.f9415b.f9417b.getContext(), (int) C1373R.string.activity_club_apply_item_status_refused);
                    C1405b.d(this.f9415b.f9417b).setVisibility(8);
                    C1405b.e(this.f9415b.f9417b).setVisibility(8);
                    C1405b.c(this.f9415b.f9417b).setText(C1373R.string.activity_club_apply_item_status_refused);
                    this.f9415b.f9416a.setStatus(3);
                    ApplyManagerActivity.f(applyManagerActivity).notifyDataSetChanged();
                    return;
                }
                Toasts.show(this.f9415b.f9417b.getContext(), (int) C1373R.string.activity_club_apply_item_error);
            }
        }, new Void[0]);
    }
}
