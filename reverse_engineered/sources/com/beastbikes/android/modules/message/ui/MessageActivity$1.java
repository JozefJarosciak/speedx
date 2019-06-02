package com.beastbikes.android.modules.message.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.message.dto.MessageDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class MessageActivity$1 extends AsyncTask<Void, Void, List<MessageDTO>> {
    /* renamed from: a */
    final /* synthetic */ MessageActivity f10919a;

    MessageActivity$1(MessageActivity messageActivity) {
        this.f10919a = messageActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11807a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11808a((List) obj);
    }

    /* renamed from: a */
    protected List<MessageDTO> m11807a(Void... voidArr) {
        try {
            return MessageActivity.a(this.f10919a).m11806a();
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11808a(List<MessageDTO> list) {
        if (list != null && !list.isEmpty()) {
            MessageActivity.b(this.f10919a).clear();
            MessageActivity.b(this.f10919a).addAll(list);
            MessageActivity.c(this.f10919a).notifyDataSetChanged();
            MessageActivity.d(this.f10919a).setSelection(MessageActivity.b(this.f10919a).size() - 1);
            MessageActivity.e(this.f10919a).edit().putLong("message.lastdate", System.currentTimeMillis()).apply();
        }
    }
}
