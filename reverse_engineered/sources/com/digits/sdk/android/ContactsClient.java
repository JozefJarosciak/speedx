package com.digits.sdk.android;

import com.twitter.sdk.android.core.C4579d;
import com.twitter.sdk.android.core.C4655n;
import retrofit.RestAdapter.Builder;

public class ContactsClient {
    /* renamed from: a */
    private final C4655n f13074a;
    /* renamed from: b */
    private final C2933u f13075b;
    /* renamed from: c */
    private ContactsClient$ContactsService f13076c;
    /* renamed from: d */
    private C2898b f13077d;

    ContactsClient() {
        this(C4655n.a(), new C2933u(), new C2898b(), null);
    }

    ContactsClient(C4655n c4655n, C2933u c2933u, C2898b c2898b, ContactsClient$ContactsService contactsClient$ContactsService) {
        if (c4655n == null) {
            throw new IllegalArgumentException("twitter must not be null");
        } else if (c2933u == null) {
            throw new IllegalArgumentException("preference manager must not be null");
        } else if (c2898b == null) {
            throw new IllegalArgumentException("activityClassManagerFactory must not be null");
        } else {
            this.f13074a = c4655n;
            this.f13075b = c2933u;
            this.f13077d = c2898b;
            this.f13076c = contactsClient$ContactsService;
        }
    }

    /* renamed from: a */
    private ContactsClient$ContactsService m13842a() {
        if (this.f13076c != null) {
            return this.f13076c;
        }
        this.f13076c = (ContactsClient$ContactsService) new Builder().setEndpoint(new ad().a()).setClient(new C4579d(this.f13074a.b(), aa.b().b(), this.f13074a.e())).build().create(ContactsClient$ContactsService.class);
        return this.f13076c;
    }

    /* renamed from: a */
    bx m13843a(by byVar) {
        return m13842a().upload(byVar);
    }
}
