package com.github.mikephil.charting.p183g;

import android.graphics.Color;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.ViewCompat;
import ch.qos.logback.core.net.SyslogConstants;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;

/* compiled from: ColorTemplate */
/* renamed from: com.github.mikephil.charting.g.a */
public class C3272a {
    /* renamed from: a */
    public static final int[] f14167a = new int[]{Color.rgb(207, 248, 246), Color.rgb(Opcodes.LCMP, AVException.USER_MOBILEPHONE_MISSING, AVException.USER_MOBILEPHONE_MISSING), Color.rgb(SyslogConstants.LOG_LOCAL1, Opcodes.GETFIELD, Opcodes.NEW), Color.rgb(118, 174, 175), Color.rgb(42, 109, TransportMediator.KEYCODE_MEDIA_RECORD)};
    /* renamed from: b */
    public static final int[] f14168b = new int[]{Color.rgb(217, 80, 138), Color.rgb(254, Opcodes.FCMPL, 7), Color.rgb(254, 247, 120), Color.rgb(106, 167, 134), Color.rgb(53, 194, AVException.USER_ID_MISMATCH)};
    /* renamed from: c */
    public static final int[] f14169c = new int[]{Color.rgb(64, 89, 128), Color.rgb(Opcodes.FCMPL, Opcodes.IF_ACMPEQ, AVException.TIMEOUT), Color.rgb(217, 184, 162), Color.rgb(191, 134, 134), Color.rgb(Opcodes.PUTSTATIC, 48, 80)};
    /* renamed from: d */
    public static final int[] f14170d = new int[]{Color.rgb(Opcodes.INSTANCEOF, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, Opcodes.IFNONNULL, 0), Color.rgb(106, 150, 31), Color.rgb(Opcodes.PUTSTATIC, 100, 53)};
    /* renamed from: e */
    public static final int[] f14171e = new int[]{Color.rgb(Opcodes.CHECKCAST, 255, AVException.EXCEEDED_QUOTA), Color.rgb(255, 247, AVException.EXCEEDED_QUOTA), Color.rgb(255, AVException.ACCOUNT_ALREADY_LINKED, AVException.EXCEEDED_QUOTA), Color.rgb(AVException.EXCEEDED_QUOTA, 234, 255), Color.rgb(255, AVException.EXCEEDED_QUOTA, Opcodes.IFGT)};
    /* renamed from: f */
    public static final int[] f14172f = new int[]{C3272a.m15842a("#2ecc71"), C3272a.m15842a("#f1c40f"), C3272a.m15842a("#e74c3c"), C3272a.m15842a("#3498db")};

    /* renamed from: a */
    public static int m15842a(String str) {
        int parseLong = (int) Long.parseLong(str.replace("#", ""), 16);
        return Color.rgb((parseLong >> 16) & 255, (parseLong >> 8) & 255, (parseLong >> 0) & 255);
    }

    /* renamed from: a */
    public static int m15840a() {
        return Color.rgb(51, Opcodes.PUTFIELD, 229);
    }

    /* renamed from: a */
    public static int m15841a(int i, int i2) {
        return (ViewCompat.MEASURED_SIZE_MASK & i) | ((i2 & 255) << 24);
    }
}
