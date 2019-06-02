package com.beastbikes.android.modules.train.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.framework.ui.android.utils.Toasts;

class CreateTrainLongTimePlanActivity$4 extends AsyncTask<Integer, Void, TrainResultDTO> {
    /* renamed from: a */
    final /* synthetic */ C1802i f11249a;
    /* renamed from: b */
    final /* synthetic */ CreateTrainLongTimePlanActivity f11250b;

    CreateTrainLongTimePlanActivity$4(CreateTrainLongTimePlanActivity createTrainLongTimePlanActivity, C1802i c1802i) {
        this.f11250b = createTrainLongTimePlanActivity;
        this.f11249a = c1802i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12070a((Integer[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12071a((TrainResultDTO) obj);
    }

    protected void onPreExecute() {
        this.f11249a.show();
    }

    /* renamed from: a */
    protected TrainResultDTO m12070a(Integer... numArr) {
        return CreateTrainLongTimePlanActivity.e(this.f11250b).m12004b(CreateTrainLongTimePlanActivity.b(this.f11250b), CreateTrainLongTimePlanActivity.c(this.f11250b), CreateTrainLongTimePlanActivity.d(this.f11250b));
    }

    /* renamed from: a */
    protected void m12071a(TrainResultDTO trainResultDTO) {
        this.f11249a.dismiss();
        if (trainResultDTO == null) {
            Toasts.show(this.f11250b, (int) C1373R.string.route_activity_comment_followed_err);
            return;
        }
        C2574s.m12893a().m12895a((Object) trainResultDTO);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            currentUser.setTrainingId(trainResultDTO.getTrainId());
            currentUser.setTrainingType(2);
            AVUser.saveCurrentUser(currentUser);
        }
        CreateTrainLongTimePlanActivity.a(this.f11250b, trainResultDTO);
    }
}
