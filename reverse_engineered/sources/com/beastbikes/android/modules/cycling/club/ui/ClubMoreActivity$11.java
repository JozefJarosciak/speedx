package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;

class ClubMoreActivity$11 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubMoreActivity f9745a;
    /* renamed from: b */
    final /* synthetic */ int f9746b;
    /* renamed from: c */
    final /* synthetic */ ClubMoreActivity f9747c;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubMoreActivity$11$1 */
    class C20981 extends ResultCallback {
        /* renamed from: a */
        final /* synthetic */ ClubMoreActivity$11 f9744a;

        C20981(ClubMoreActivity$11 clubMoreActivity$11) {
            this.f9744a = clubMoreActivity$11;
        }

        public void onSuccess(Object obj) {
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    ClubMoreActivity$11(ClubMoreActivity clubMoreActivity, ClubMoreActivity clubMoreActivity2, int i) {
        this.f9747c = clubMoreActivity;
        this.f9745a = clubMoreActivity2;
        this.f9746b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10844a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10845a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        ClubMoreActivity.a(this.f9747c, new C1802i(this.f9745a, this.f9747c.getString(this.f9746b), true));
        ClubMoreActivity.o(this.f9747c).show();
    }

    /* renamed from: a */
    protected Boolean m10844a(Void... voidArr) {
        try {
            return Boolean.valueOf(ClubMoreActivity.a(this.f9747c).m10541a(1, ClubMoreActivity.p(this.f9747c), null, null));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10845a(Boolean bool) {
        if (ClubMoreActivity.o(this.f9747c) != null && ClubMoreActivity.o(this.f9747c).isShowing()) {
            ClubMoreActivity.o(this.f9747c).dismiss();
        }
        if (bool.booleanValue()) {
            RongIMClient.getInstance().clearConversations(new C20981(this), new ConversationType[]{ConversationType.GROUP});
            Toasts.show(this.f9745a, (int) C1373R.string.activity_club_manager_quit_club_success);
            this.f9747c.setResult(-1);
            this.f9747c.finish();
            return;
        }
        Toasts.show(this.f9745a, (int) C1373R.string.activity_club_manager_quit_club_failed);
    }
}
