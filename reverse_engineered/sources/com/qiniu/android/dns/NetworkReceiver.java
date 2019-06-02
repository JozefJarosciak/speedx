package com.qiniu.android.dns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.qiniu.android.dns.NetworkInfo.NetSatus;
import java.util.Locale;

public final class NetworkReceiver extends BroadcastReceiver {
    /* renamed from: a */
    private static final Uri f15107a = Uri.parse("content://telephony/carriers/preferapn");
    /* renamed from: b */
    private static C4345a f15108b;

    /* renamed from: a */
    public static NetworkInfo m17132a(NetworkInfo networkInfo, Context context) {
        if (networkInfo == null) {
            return NetworkInfo.f15103a;
        }
        NetSatus netSatus;
        int i;
        if (networkInfo.getType() == 1) {
            netSatus = NetSatus.WIFI;
            i = 0;
        } else {
            String extraInfo;
            NetSatus netSatus2 = NetSatus.MOBILE;
            Cursor query = context.getContentResolver().query(f15107a, null, null, null, null);
            if (query != null) {
                query.moveToFirst();
                String string = query.getString(query.getColumnIndex("user"));
                if (!TextUtils.isEmpty(string) && (string.startsWith("ctwap") || string.startsWith("ctnet"))) {
                    i = 1;
                    query.close();
                    if (i != 1) {
                        extraInfo = networkInfo.getExtraInfo();
                        if (extraInfo != null) {
                            extraInfo = extraInfo.toLowerCase(Locale.getDefault());
                            if (!extraInfo.equals("cmwap") || extraInfo.equals("cmnet")) {
                                i = 3;
                                netSatus = netSatus2;
                            } else if (extraInfo.equals("3gnet") || extraInfo.equals("uninet") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                                i = 2;
                                netSatus = netSatus2;
                            }
                        }
                    }
                    netSatus = netSatus2;
                }
            }
            i = 0;
            query.close();
            if (i != 1) {
                extraInfo = networkInfo.getExtraInfo();
                if (extraInfo != null) {
                    extraInfo = extraInfo.toLowerCase(Locale.getDefault());
                    if (extraInfo.equals("cmwap")) {
                    }
                    i = 3;
                    netSatus = netSatus2;
                }
            }
            netSatus = netSatus2;
        }
        return new NetworkInfo(netSatus, i);
    }

    public void onReceive(Context context, Intent intent) {
        if (f15108b != null) {
            f15108b.m17146a(m17132a(((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo(), context));
        }
    }
}
