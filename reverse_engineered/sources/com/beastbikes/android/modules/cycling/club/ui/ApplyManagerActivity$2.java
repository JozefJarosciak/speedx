package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ApplyDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ApplyManagerActivity$2 extends AsyncTask<Void, Void, List<ApplyDTO>> {
    /* renamed from: a */
    final /* synthetic */ ApplyManagerActivity f9402a;

    ApplyManagerActivity$2(ApplyManagerActivity applyManagerActivity) {
        this.f9402a = applyManagerActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10653a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10654a((List) obj);
    }

    /* renamed from: a */
    protected List<ApplyDTO> m10653a(Void... voidArr) {
        List<ApplyDTO> list = null;
        if (ApplyManagerActivity.a(this.f9402a) != null) {
            try {
                list = ApplyManagerActivity.a(this.f9402a).m10535a(ApplyManagerActivity.b(this.f9402a), 1000);
            } catch (BusinessException e) {
            }
        }
        return list;
    }

    /* renamed from: a */
    protected void m10654a(List<ApplyDTO> list) {
        if (list == null || list.isEmpty()) {
            ApplyManagerActivity.g(this.f9402a).setVisibility(0);
            return;
        }
        if (ApplyManagerActivity.c(this.f9402a) != 0 && ApplyManagerActivity.c(this.f9402a) == ApplyManagerActivity.d(this.f9402a)) {
            for (ApplyDTO applyDTO : list) {
                if (applyDTO.getStatus() == 0) {
                    applyDTO.setStatus(3);
                }
            }
        }
        ApplyManagerActivity.e(this.f9402a).clear();
        ApplyManagerActivity.e(this.f9402a).addAll(list);
        ApplyManagerActivity.f(this.f9402a).notifyDataSetChanged();
    }
}
