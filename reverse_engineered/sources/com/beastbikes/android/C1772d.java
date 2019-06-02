package com.beastbikes.android;

import android.content.Context;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.authentication.AuthenticationException;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.authentication.p055a.C1536a.C1535a;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.C1770e;
import com.beastbikes.android.sphere.restful.C1771b;
import com.beastbikes.framework.android.p088g.C1465f;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.Collections;
import java.util.Map;

/* compiled from: RestfulAPIFactory */
/* renamed from: com.beastbikes.android.d */
public class C1772d extends C1770e implements C1771b {
    public C1772d(Context context) {
        super(context);
    }

    /* renamed from: a */
    public <T extends C1600d> T m9398a(Class<T> cls, String str) {
        return super.m9396a(cls, str, Collections.emptyMap(), this);
    }

    /* renamed from: a */
    public <T extends C1600d> T m9399a(Class<T> cls, String str, Map<String, String> map) {
        return super.m9396a(cls, str, map, this);
    }

    /* renamed from: a */
    public void mo3241a() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setPassword(AVUser.getPwd());
            accountDTO.setUsername(currentUser.getUsername());
            accountDTO.setAuthType(currentUser.getSignType());
            final C1536a c1536a = new C1536a(this.a);
            c1536a.m8465a(accountDTO, new C1535a(this) {
                /* renamed from: b */
                final /* synthetic */ C1772d f8079b;

                /* renamed from: a */
                public void mo3121a(AuthenticationException authenticationException, AccountDTO accountDTO) {
                    if (authenticationException != null) {
                        authenticationException.printStackTrace();
                        Toasts.show(this.f8079b.a, authenticationException.getMessage());
                        c1536a.m8464a();
                    }
                    C1465f.e(this.f8079b.a);
                }

                /* renamed from: b */
                public void mo3122b(AuthenticationException authenticationException, AccountDTO accountDTO) {
                }
            });
        }
    }
}
