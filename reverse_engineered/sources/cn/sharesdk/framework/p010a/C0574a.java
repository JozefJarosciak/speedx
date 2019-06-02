package cn.sharesdk.framework.p010a;

import android.text.TextUtils;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.network.HTTPPart;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.network.RawNetworkCallback;
import java.util.ArrayList;

/* compiled from: SSDKNetworkHelper */
/* renamed from: cn.sharesdk.framework.a.a */
public class C0574a extends NetworkHelper {
    /* renamed from: a */
    private static C0574a f1235a = null;

    private C0574a() {
    }

    /* renamed from: a */
    public static C0574a m1988a() {
        if (f1235a == null) {
            f1235a = new C0574a();
        }
        return f1235a;
    }

    /* renamed from: a */
    public String m1993a(String str, ArrayList<KVPair<String>> arrayList, String str2, int i) throws Throwable {
        return m1994a(str, (ArrayList) arrayList, null, null, str2, i);
    }

    /* renamed from: a */
    public String m1994a(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        m1989a(str2, i);
        return super.httpGet(str, arrayList, arrayList2, networkTimeOut);
    }

    /* renamed from: b */
    public String m1997b(String str, ArrayList<KVPair<String>> arrayList, String str2, int i) throws Throwable {
        return m1990a(str, arrayList, null, str2, i);
    }

    /* renamed from: a */
    public String m1990a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, String str2, int i) throws Throwable {
        return m1992a(str, (ArrayList) arrayList, (KVPair) kVPair, null, str2, i);
    }

    /* renamed from: a */
    public String m1992a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, String str2, int i) throws Throwable {
        return m1991a(str, arrayList, kVPair, arrayList2, null, str2, i);
    }

    /* renamed from: a */
    public String m1991a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        m1989a(str2, i);
        return super.httpPost(str, arrayList, kVPair, arrayList2, networkTimeOut);
    }

    /* renamed from: b */
    public String m1996b(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        m1989a(str2, i);
        return super.httpPut(str, arrayList, kVPair, arrayList2, networkTimeOut);
    }

    /* renamed from: a */
    public void m1995a(String str, ArrayList<KVPair<String>> arrayList, HTTPPart hTTPPart, RawNetworkCallback rawNetworkCallback, String str2, int i) throws Throwable {
        m1989a(str2, i);
        super.rawPost(str, arrayList, hTTPPart, rawNetworkCallback, null);
    }

    /* renamed from: b */
    public String m1998b(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        m1989a(str2, i);
        return super.jsonPost(str, arrayList, arrayList2, networkTimeOut);
    }

    /* renamed from: a */
    private void m1989a(String str, int i) {
        if (!TextUtils.isEmpty(str) && i > 0) {
            ShareSDK.logApiEvent(str, i);
        }
    }
}
