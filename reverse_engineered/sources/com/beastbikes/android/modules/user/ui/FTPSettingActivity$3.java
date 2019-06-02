package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.train.dto.FtpDTO;
import com.beastbikes.framework.ui.android.utils.Toasts;

class FTPSettingActivity$3 extends AsyncTask<Object, Void, FtpDTO> {
    /* renamed from: a */
    final /* synthetic */ C1802i f11584a;
    /* renamed from: b */
    final /* synthetic */ FTPSettingActivity f11585b;

    FTPSettingActivity$3(FTPSettingActivity fTPSettingActivity, C1802i c1802i) {
        this.f11585b = fTPSettingActivity;
        this.f11584a = c1802i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12430a(objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12431a((FtpDTO) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (!this.f11584a.isShowing()) {
            this.f11584a.show();
        }
    }

    /* renamed from: a */
    protected FtpDTO m12430a(Object... objArr) {
        return FTPSettingActivity.g(this.f11585b).m12008a(FTPSettingActivity.d(this.f11585b), FTPSettingActivity.e(this.f11585b), FTPSettingActivity.f(this.f11585b));
    }

    /* renamed from: a */
    protected void m12431a(FtpDTO ftpDTO) {
        if (this.f11584a.isShowing()) {
            this.f11584a.dismiss();
        }
        if (ftpDTO != null) {
            Intent intent = this.f11585b.getIntent();
            intent.putExtra("FTP", ftpDTO);
            this.f11585b.setResult(-1, intent);
            this.f11585b.finish();
            return;
        }
        Toasts.show(this.f11585b, (int) C1373R.string.network_not_awesome);
    }
}
