package com.digits.sdk.android;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C1500b;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;
import retrofit.client.Header;

/* compiled from: DigitsSession */
public class ap extends C1469k<C1500b> {
    /* renamed from: a */
    public static final au f6876a = new au("", false);
    @SerializedName("phone_number")
    /* renamed from: b */
    private final String f6877b;
    @SerializedName("email")
    /* renamed from: c */
    private final au f6878c;

    public ap(C1500b c1500b, long j, String str, au auVar) {
        super(c1500b, j);
        this.f6877b = str;
        this.f6878c = auVar;
    }

    public ap(OAuth2Token oAuth2Token) {
        this(oAuth2Token, 0, "", f6876a);
    }

    /* renamed from: a */
    public boolean m8109a() {
        return m8101e() == 0;
    }

    /* renamed from: b */
    public boolean m8110b() {
        return m8106a(m8101e()) && m8107a(m8100d());
    }

    /* renamed from: a */
    private boolean m8106a(long j) {
        return (m8109a() || j == -1) ? false : true;
    }

    /* renamed from: a */
    private boolean m8107a(C1500b c1500b) {
        return (!(c1500b instanceof TwitterAuthToken) || ((TwitterAuthToken) c1500b).f7052c == null || ((TwitterAuthToken) c1500b).f7051b == null) ? false : true;
    }

    /* renamed from: a */
    static ap m8104a(C4645j<as> c4645j, String str) {
        if (c4645j == null) {
            throw new NullPointerException("result must not be null");
        } else if (c4645j.f16364a == null) {
            throw new NullPointerException("result.data must not be null");
        } else if (c4645j.f16365b == null) {
            throw new NullPointerException("result.response must not be null");
        } else if (str == null) {
            throw new NullPointerException("phoneNumber must not be null");
        } else {
            String str2;
            String str3 = "";
            String str4 = "";
            for (Header header : c4645j.f16365b.getHeaders()) {
                CharSequence value;
                CharSequence charSequence;
                if ("x-twitter-new-account-oauth-access-token".equals(header.getName())) {
                    String str5 = str4;
                    value = header.getValue();
                    charSequence = str5;
                } else if ("x-twitter-new-account-oauth-secret".equals(header.getName())) {
                    charSequence = header.getValue();
                    r1 = str3;
                } else {
                    Object obj = str4;
                    r1 = str3;
                }
                if (!TextUtils.isEmpty(value) && !TextUtils.isEmpty(charSequence)) {
                    str3 = charSequence;
                    str2 = value;
                    break;
                }
                CharSequence charSequence2 = value;
                value = charSequence;
            }
            str2 = str3;
            str3 = str4;
            return new ap(new TwitterAuthToken(str2, str3), ((as) c4645j.f16364a).f6883a, str, f6876a);
        }
    }

    /* renamed from: a */
    static ap m8102a(aq aqVar, String str) {
        if (aqVar == null) {
            throw new NullPointerException("result must not be null");
        } else if (str == null) {
            throw new NullPointerException("phoneNumber must not be null");
        } else {
            return new ap(new TwitterAuthToken(aqVar.f6879a, aqVar.f6880b), aqVar.f6882d, str, f6876a);
        }
    }

    /* renamed from: a */
    public static ap m8103a(bz bzVar) {
        if (bzVar == null) {
            throw new NullPointerException("verifyAccountResponse must not be null");
        }
        return new ap(bzVar.f6887a, bzVar.f6888b, bzVar.f6889c, bzVar.f6890d != null ? bzVar.f6890d : f6876a);
    }

    /* renamed from: c */
    public au m8111c() {
        return this.f6878c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r5 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r2 = super.equals(r5);
        if (r2 == 0) goto L_0x0005;
    L_0x0018:
        r5 = (com.digits.sdk.android.ap) r5;
        r2 = r4.f6877b;
        if (r2 == 0) goto L_0x0039;
    L_0x001e:
        r2 = r4.f6877b;
        r3 = r5.f6877b;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0028:
        r2 = r4.f6878c;
        if (r2 == 0) goto L_0x003e;
    L_0x002c:
        r2 = r4.f6878c;
        r3 = r5.f6878c;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0037;
    L_0x0036:
        r0 = r1;
    L_0x0037:
        r1 = r0;
        goto L_0x0005;
    L_0x0039:
        r2 = r5.f6877b;
        if (r2 == 0) goto L_0x0028;
    L_0x003d:
        goto L_0x0005;
    L_0x003e:
        r2 = r5.f6878c;
        if (r2 != 0) goto L_0x0036;
    L_0x0042:
        goto L_0x0037;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digits.sdk.android.ap.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = super.hashCode() * 31;
        if (this.f6877b != null) {
            hashCode = this.f6877b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.f6878c != null) {
            i = this.f6878c.hashCode();
        }
        return hashCode + i;
    }
}
