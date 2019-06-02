package com.beastbikes.android.modules.message.p071a;

import android.app.Activity;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.message.dto.MessageDTO;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MessageManager */
/* renamed from: com.beastbikes.android.modules.message.a.a */
public class C2310a extends C1397a {
    /* renamed from: a */
    private final C2309a f10916a = new C2309a();
    /* renamed from: b */
    private C1427b f10917b;
    /* renamed from: c */
    private Activity f10918c;

    /* compiled from: MessageManager */
    /* renamed from: com.beastbikes.android.modules.message.a.a$a */
    private final class C2309a implements Comparator<MessageDTO> {
        /* renamed from: a */
        final /* synthetic */ C2310a f10915a;

        private C2309a(C2310a c2310a) {
            this.f10915a = c2310a;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m11804a((MessageDTO) obj, (MessageDTO) obj2);
        }

        /* renamed from: a */
        public int m11804a(MessageDTO messageDTO, MessageDTO messageDTO2) {
            if (messageDTO.getAvailableTime().after(messageDTO2.getAvailableTime())) {
                return 1;
            }
            return -1;
        }
    }

    public C2310a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f10918c = activity;
        this.f10917b = (C1427b) new C1772d(activity).m9399a(C1427b.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public List<MessageDTO> m11806a() throws BusinessException {
        List<MessageDTO> list = null;
        try {
            JSONObject a = this.f10917b.a();
            if (a != null) {
                if (a.optInt("code") == 0) {
                    JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                    list = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        list.add(new MessageDTO(optJSONArray.optJSONObject(i)));
                    }
                    Collections.sort(list, this.f10916a);
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f10918c, optString);
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public int m11805a(long j) throws BusinessException {
        try {
            JSONObject a = this.f10917b.a((float) j);
            if (a == null) {
                return -1;
            }
            if (a.optInt("code") == 0) {
                return a.optJSONObject(C0882j.f2229c).optInt("count");
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return -1;
            }
            Toasts.showOnUiThread(this.f10918c, optString);
            return -1;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }
}
