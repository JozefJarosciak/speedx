package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.PersonalRecordResponseDTO;

class PersonalRecordActivity$2 extends AsyncTask<Void, Void, PersonalRecordResponseDTO> {
    /* renamed from: a */
    final /* synthetic */ PersonalRecordActivity f11744a;

    PersonalRecordActivity$2(PersonalRecordActivity personalRecordActivity) {
        this.f11744a = personalRecordActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12509a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12510a((PersonalRecordResponseDTO) obj);
    }

    protected void onPreExecute() {
        PersonalRecordActivity.a(this.f11744a);
    }

    /* renamed from: a */
    protected PersonalRecordResponseDTO m12509a(Void... voidArr) {
        return PersonalRecordActivity.d(this.f11744a).m12120a(PersonalRecordActivity.b(this.f11744a), PersonalRecordActivity.c(this.f11744a));
    }

    /* renamed from: a */
    protected void m12510a(PersonalRecordResponseDTO personalRecordResponseDTO) {
        PersonalRecordActivity.e(this.f11744a);
        if (personalRecordResponseDTO == null) {
            PersonalRecordActivity.a(this.f11744a, null);
            PersonalRecordActivity.a(this.f11744a, -1, null);
            PersonalRecordActivity.f(this.f11744a).error("get personal record data information error with dataDTO is null!");
            return;
        }
        PersonalRecordActivity.a(this.f11744a, personalRecordResponseDTO);
        int a = PersonalRecordActivity.a(this.f11744a, 0, null);
        if (a > 0) {
            PersonalRecordActivity.g(this.f11744a).getXAxis().a(a, true);
            if (PersonalRecordActivity.c(this.f11744a) != 0) {
                PersonalRecordActivity.g(this.f11744a).a((float) (a - 1), 0);
            } else {
                PersonalRecordActivity.g(this.f11744a).a(null);
            }
            PersonalRecordActivity.a(this.f11744a, a - 1);
        }
    }
}
