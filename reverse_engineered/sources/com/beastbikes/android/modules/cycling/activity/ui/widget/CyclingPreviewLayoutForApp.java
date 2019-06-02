package com.beastbikes.android.modules.cycling.activity.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.utils.C2555d;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CyclingPreviewLayoutForApp extends LinearLayout {
    /* renamed from: a */
    private String[] f9243a;
    /* renamed from: b */
    private String[] f9244b;
    /* renamed from: c */
    private String[] f9245c;
    /* renamed from: d */
    private float f9246d = 1.0f;
    /* renamed from: e */
    private float f9247e = 11.2f;
    /* renamed from: f */
    private float f9248f = 8.8f;
    /* renamed from: g */
    private float f9249g = 100.0f;
    /* renamed from: h */
    private float f9250h = 68.0f;
    /* renamed from: i */
    private float f9251i = 54.0f;
    /* renamed from: j */
    private String[] f9252j;
    /* renamed from: k */
    private DecimalFormat f9253k;
    /* renamed from: l */
    private boolean f9254l;
    /* renamed from: m */
    private ArrayList<C2038b> f9255m;
    /* renamed from: n */
    private ArrayList<Integer> f9256n;
    /* renamed from: o */
    private LocalActivity f9257o;
    /* renamed from: p */
    private SimpleDateFormat f9258p;
    /* renamed from: q */
    private AVUser f9259q;

    public CyclingPreviewLayoutForApp(Context context) {
        super(context);
        m10493a(context);
    }

    public CyclingPreviewLayoutForApp(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m10493a(context);
    }

    public CyclingPreviewLayoutForApp(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10493a(context);
    }

    /* renamed from: a */
    private void m10493a(Context context) {
        this.f9258p = new SimpleDateFormat("HH:mm", Locale.getDefault());
        this.f9259q = AVUser.getCurrentUser();
        setOrientation(1);
        setPadding(0, getResources().getDimensionPixelSize(C1373R.dimen.dimen_10), 0, getResources().getDimensionPixelSize(C1373R.dimen.dimen_10));
        this.f9255m = new ArrayList();
        this.f9253k = new DecimalFormat("#.#");
        this.f9252j = getResources().getStringArray(C1373R.array.cycling_data_settings_desc);
        this.f9243a = getResources().getStringArray(C1373R.array.cycling_data_settings_desc);
        this.f9244b = getResources().getStringArray(C1373R.array.cycling_data_settings_format);
        this.f9254l = C1849a.m9645b(context);
        if (this.f9254l) {
            this.f9245c = getResources().getStringArray(C1373R.array.cycling_data_settings_desc_unit_km);
        } else {
            this.f9245c = getResources().getStringArray(C1373R.array.cycling_data_settings_desc_unit_mi);
        }
    }

    /* renamed from: a */
    public void m10494a(LocalActivity localActivity) {
        if (localActivity != null && this.f9255m != null) {
            this.f9257o = localActivity;
            int size = this.f9255m.size();
            for (int i = 0; i < size; i++) {
                C2038b c2038b = (C2038b) this.f9255m.get(i);
                c2038b.m10503a(m10492a(m10491a(c2038b.getDesc(), this.f9252j), localActivity));
            }
        }
    }

    /* renamed from: a */
    private String m10492a(int i, LocalActivity localActivity) {
        String str = "N/A";
        switch (i) {
            case 0:
                if (localActivity.getRealTimeCadence() > 0) {
                    return String.valueOf(localActivity.getRealTimeCadence());
                }
                return str;
            case 1:
                if (localActivity.getCadence() <= 0.0d) {
                    return str;
                }
                return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(localActivity.getCadence())});
            case 2:
                if (this.f9254l) {
                    str = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(localActivity.getTotalDistance() / 1000.0d)});
                } else {
                    str = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.m9638a(localActivity.getTotalDistance()) / 1000.0d)});
                }
                if (localActivity.getTotalDistance() == 0.0d) {
                    return String.valueOf(0.0d);
                }
                return str;
            case 3:
                if (this.f9259q == null) {
                    return str;
                }
                if (this.f9254l) {
                    return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(this.f9259q.getTotalMileage() / 1000.0d)});
                }
                return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.m9638a(this.f9259q.getTotalMileage()) / 1000.0d)});
            case 4:
                if (this.f9254l) {
                    return this.f9253k.format(localActivity.getAltitude());
                }
                return this.f9253k.format(C1849a.m9646c(localActivity.getAltitude()));
            case 5:
                return this.f9253k.format(localActivity.getCurrentSlope() * 100.0d) + "%";
            case 6:
                if (this.f9254l) {
                    return this.f9253k.format(localActivity.getTotalRisenAltitude());
                }
                return this.f9253k.format(C1849a.m9646c(localActivity.getTotalRisenAltitude()));
            case 7:
                if (this.f9254l) {
                    return this.f9253k.format(localActivity.getTotalDescent());
                }
                return this.f9253k.format(C1849a.m9646c(localActivity.getTotalDescent()));
            case 8:
                if (this.f9254l) {
                    str = this.f9253k.format(localActivity.getVerticalSpeed());
                } else {
                    str = this.f9253k.format(C1849a.m9650e(localActivity.getVerticalSpeed()));
                }
                if (localActivity.getVerticalSpeed() == 0.0d) {
                    return String.valueOf("0.0");
                }
                return str;
            case 9:
                if (this.f9254l) {
                    return this.f9253k.format(localActivity.getVerticalSpeedPer30s());
                }
                return this.f9253k.format(C1849a.m9650e(localActivity.getVerticalSpeedPer30s()));
            case 10:
                return this.f9258p.format(new Date());
            case 11:
                if (localActivity.getRealTimeHeartRate() > 0) {
                    return String.valueOf(localActivity.getRealTimeHeartRate());
                }
                return str;
            case 12:
                if (localActivity.getReserveHeartRate() <= 0.0d) {
                    return str;
                }
                return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(localActivity.getReserveHeartRate() * 100.0d)}) + "%";
            case 13:
                if (localActivity.getMaxCardiacRate() > 0.0d) {
                    return String.valueOf((int) localActivity.getMaxCardiacRate());
                }
                return str;
            case 14:
                if (localActivity.getCardiacRate() <= 0.0d) {
                    return str;
                }
                return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(localActivity.getCardiacRate())});
            case 15:
                if (localActivity.getAvgReserveHeartRate() <= 0.0d) {
                    return str;
                }
                return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(localActivity.getAvgReserveHeartRate() * 100.0d)}) + "%";
            case 16:
                if (localActivity.getMaxHeartRatePercent() <= 0.0d) {
                    return str;
                }
                return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(localActivity.getMaxHeartRatePercent() * 100.0d)}) + "%";
            case 17:
                if (localActivity.getHeartRateZone() <= 0) {
                    return str;
                }
                return String.format(Locale.getDefault(), getContext().getString(C1373R.string.str_number_zone), new Object[]{Integer.valueOf(localActivity.getHeartRateZone())});
            case 18:
                return String.valueOf(localActivity.getRealTimePower());
            case 19:
                return String.valueOf(localActivity.getPowerFTP() + "%");
            case 20:
                return String.valueOf(localActivity.getAvgPowerPer3s());
            case 21:
                return String.valueOf(localActivity.getAvgPowerPer10s());
            case 22:
                return String.valueOf(localActivity.getAvgPowerPer30s());
            case 23:
                return String.valueOf(localActivity.getAvgPower());
            case 24:
                if (localActivity.getPowerIF() > 0.0d) {
                    return String.valueOf(localActivity.getPowerIF());
                }
                return str;
            case 25:
                return String.valueOf(localActivity.getMaxPower());
            case 26:
                if (localActivity.getStandardPower() > 0) {
                    return String.valueOf(localActivity.getStandardPower());
                }
                return str;
            case 27:
                if (localActivity.getPowerTSS() > 0) {
                    return String.valueOf(localActivity.getPowerTSS());
                }
                return str;
            case 28:
                return String.valueOf(localActivity.getPowerWattsPerKG());
            case 29:
                return String.format(Locale.getDefault(), getContext().getString(C1373R.string.str_number_zone), new Object[]{Integer.valueOf(localActivity.getPowerZone())});
            case 30:
                if (this.f9254l) {
                    return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(localActivity.getInstantaneousVelocity())});
                }
                return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.m9648d(localActivity.getInstantaneousVelocity()))});
            case 31:
                if (this.f9254l) {
                    return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(localActivity.getSpeed())});
                }
                return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.m9648d(localActivity.getSpeed()))});
            case 32:
                if (this.f9254l) {
                    return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(localActivity.getMaxVelocity())});
                }
                return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.m9648d(localActivity.getMaxVelocity()))});
            case 33:
                return C2555d.m12802b((long) localActivity.getTotalElapsedTime());
            case 34:
                return C2555d.m12802b((System.currentTimeMillis() - localActivity.getStartTime()) / 1000);
            default:
                return str;
        }
    }

    /* renamed from: a */
    private int m10491a(String str, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (TextUtils.equals(str, strArr[i])) {
                return i;
            }
        }
        return -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public void m10495a(java.util.ArrayList<java.lang.Integer> r15) {
        /*
        r14 = this;
        r1 = -1;
        r13 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = 1;
        r12 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r8 = 0;
        r14.removeAllViews();
        if (r15 == 0) goto L_0x0012;
    L_0x000c:
        r0 = r15.size();
        if (r0 > 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        r14.f9256n = r15;
        r9 = new android.widget.LinearLayout$LayoutParams;
        r9.<init>(r1, r8, r12);
        r10 = new android.widget.LinearLayout$LayoutParams;
        r10.<init>(r8, r1, r12);
        r11 = r15.size();
        switch(r11) {
            case 1: goto L_0x0027;
            case 2: goto L_0x0027;
            case 3: goto L_0x005f;
            case 4: goto L_0x00a7;
            case 5: goto L_0x011e;
            case 6: goto L_0x0178;
            case 7: goto L_0x01ee;
            case 8: goto L_0x0297;
            case 9: goto L_0x01ee;
            case 10: goto L_0x0297;
            default: goto L_0x0026;
        };
    L_0x0026:
        goto L_0x0012;
    L_0x0027:
        if (r8 >= r11) goto L_0x0012;
    L_0x0029:
        r0 = r15.get(r8);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = r14.f9247e;
        r2 = r14.f9250h;
        r3 = r14.f9247e;
        r0.m10502a(r1, r2, r3);
        r0.setLayoutParams(r9);
        r14.addView(r0);
        r1 = r14.f9255m;
        r1.add(r0);
        r8 = r8 + 1;
        goto L_0x0027;
    L_0x005f:
        if (r8 >= r11) goto L_0x0012;
    L_0x0061:
        r0 = r15.get(r8);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = r14.f9247e;
        r2 = r14.f9250h;
        r3 = r14.f9247e;
        r0.m10502a(r1, r2, r3);
        if (r8 != r7) goto L_0x00a4;
    L_0x008b:
        r9.weight = r13;
        r1 = r14.f9247e;
        r2 = r14.f9249g;
        r3 = r14.f9247e;
        r0.m10502a(r1, r2, r3);
    L_0x0096:
        r0.setLayoutParams(r9);
        r14.addView(r0);
        r1 = r14.f9255m;
        r1.add(r0);
        r8 = r8 + 1;
        goto L_0x005f;
    L_0x00a4:
        r9.weight = r12;
        goto L_0x0096;
    L_0x00a7:
        r6 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r6.<init>(r0);
        r6.setOrientation(r8);
        r6.setLayoutParams(r9);
    L_0x00b6:
        if (r8 >= r11) goto L_0x0119;
    L_0x00b8:
        r0 = r15.get(r8);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        if (r8 == 0) goto L_0x00db;
    L_0x00d9:
        if (r8 != r7) goto L_0x0102;
    L_0x00db:
        if (r8 != 0) goto L_0x00f6;
    L_0x00dd:
        r1 = r14.f9247e;
        r2 = r14.f9250h;
        r3 = r14.f9247e;
        r0.m10502a(r1, r2, r3);
        r9.weight = r12;
    L_0x00e8:
        r0.setLayoutParams(r9);
        r14.addView(r0);
        r1 = r14.f9255m;
        r1.add(r0);
    L_0x00f3:
        r8 = r8 + 1;
        goto L_0x00b6;
    L_0x00f6:
        r1 = r14.f9247e;
        r2 = r14.f9249g;
        r3 = r14.f9247e;
        r0.m10502a(r1, r2, r3);
        r9.weight = r13;
        goto L_0x00e8;
    L_0x0102:
        r9.weight = r12;
        r1 = r14.f9247e;
        r2 = r14.f9250h;
        r3 = r14.f9247e;
        r0.m10502a(r1, r2, r3);
        r0.setLayoutParams(r10);
        r6.addView(r0);
        r1 = r14.f9255m;
        r1.add(r0);
        goto L_0x00f3;
    L_0x0119:
        r14.addView(r6);
        goto L_0x0012;
    L_0x011e:
        r6 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r6.<init>(r0);
        r6.setOrientation(r8);
        r6.setLayoutParams(r9);
    L_0x012d:
        if (r8 >= r11) goto L_0x0173;
    L_0x012f:
        r0 = r15.get(r8);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = r14.f9247e;
        r2 = r14.f9250h;
        r3 = r14.f9247e;
        r0.m10502a(r1, r2, r3);
        if (r8 == 0) goto L_0x015e;
    L_0x0159:
        if (r8 == r7) goto L_0x015e;
    L_0x015b:
        r1 = 2;
        if (r8 != r1) goto L_0x016c;
    L_0x015e:
        r0.setLayoutParams(r9);
        r14.addView(r0);
    L_0x0164:
        r1 = r14.f9255m;
        r1.add(r0);
        r8 = r8 + 1;
        goto L_0x012d;
    L_0x016c:
        r0.setLayoutParams(r10);
        r6.addView(r0);
        goto L_0x0164;
    L_0x0173:
        r14.addView(r6);
        goto L_0x0012;
    L_0x0178:
        r6 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r6.<init>(r0);
        r6.setOrientation(r8);
        r6.setLayoutParams(r9);
        r12 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r12.<init>(r0);
        r12.setOrientation(r8);
        r12.setLayoutParams(r9);
    L_0x0196:
        if (r8 >= r11) goto L_0x01e6;
    L_0x0198:
        r0 = r15.get(r8);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = r14.f9247e;
        r2 = r14.f9250h;
        r3 = r14.f9247e;
        r0.m10502a(r1, r2, r3);
        if (r8 == 0) goto L_0x01c4;
    L_0x01c2:
        if (r8 != r7) goto L_0x01d2;
    L_0x01c4:
        r0.setLayoutParams(r9);
        r14.addView(r0);
    L_0x01ca:
        r1 = r14.f9255m;
        r1.add(r0);
        r8 = r8 + 1;
        goto L_0x0196;
    L_0x01d2:
        r1 = 2;
        if (r8 == r1) goto L_0x01d8;
    L_0x01d5:
        r1 = 3;
        if (r8 != r1) goto L_0x01df;
    L_0x01d8:
        r0.setLayoutParams(r10);
        r6.addView(r0);
        goto L_0x01ca;
    L_0x01df:
        r0.setLayoutParams(r10);
        r12.addView(r0);
        goto L_0x01ca;
    L_0x01e6:
        r14.addView(r6);
        r14.addView(r12);
        goto L_0x0012;
    L_0x01ee:
        r0 = 7;
        if (r11 != r0) goto L_0x0292;
    L_0x01f1:
        r0 = r14.f9247e;
        r6 = r0;
    L_0x01f4:
        r0 = r15.get(r8);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = r14.f9251i;
        r0.m10502a(r6, r1, r6);
        r14.addView(r0);
        r1 = r14.f9255m;
        r1.add(r0);
    L_0x0220:
        if (r7 >= r11) goto L_0x0012;
    L_0x0222:
        r12 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r12.<init>(r0);
        r12.setOrientation(r8);
        r12.setLayoutParams(r9);
        r0 = r15.get(r7);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = r14.f9251i;
        r0.m10502a(r6, r1, r6);
        r12.addView(r0, r10);
        r1 = r14.f9255m;
        r1.add(r0);
        r0 = r7 + 1;
        r0 = r15.get(r0);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = r14.f9251i;
        r0.m10502a(r6, r1, r6);
        r12.addView(r0, r10);
        r1 = r14.f9255m;
        r1.add(r0);
        r14.addView(r12);
        r0 = r7 + 2;
        r7 = r0;
        goto L_0x0220;
    L_0x0292:
        r0 = r14.f9248f;
        r6 = r0;
        goto L_0x01f4;
    L_0x0297:
        r0 = 8;
        if (r11 != r0) goto L_0x0311;
    L_0x029b:
        r0 = r14.f9247e;
        r6 = r0;
    L_0x029e:
        r7 = r8;
    L_0x029f:
        if (r7 >= r11) goto L_0x0012;
    L_0x02a1:
        r12 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r12.<init>(r0);
        r12.setOrientation(r8);
        r12.setLayoutParams(r9);
        r0 = r15.get(r7);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = r14.f9251i;
        r0.m10502a(r6, r1, r6);
        r12.addView(r0, r10);
        r1 = r14.f9255m;
        r1.add(r0);
        r0 = r7 + 1;
        r0 = r15.get(r0);
        r0 = (java.lang.Integer) r0;
        r2 = r0.intValue();
        r0 = new com.beastbikes.android.modules.cycling.activity.ui.widget.b;
        r1 = r14.getContext();
        r3 = r14.f9244b;
        r3 = r3[r2];
        r4 = r14.f9243a;
        r4 = r4[r2];
        r5 = r14.f9245c;
        r5 = r5[r2];
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = r14.f9251i;
        r0.m10502a(r6, r1, r6);
        r12.addView(r0, r10);
        r1 = r14.f9255m;
        r1.add(r0);
        r14.addView(r12);
        r0 = r7 + 2;
        r7 = r0;
        goto L_0x029f;
    L_0x0311:
        r0 = r14.f9248f;
        r6 = r0;
        goto L_0x029e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.beastbikes.android.modules.cycling.activity.ui.widget.CyclingPreviewLayoutForApp.a(java.util.ArrayList):void");
    }

    public void setDisplayKM(boolean z) {
        this.f9254l = z;
        if (z) {
            this.f9245c = getResources().getStringArray(C1373R.array.cycling_data_settings_desc_unit_km);
        } else {
            this.f9245c = getResources().getStringArray(C1373R.array.cycling_data_settings_desc_unit_mi);
        }
        m10495a(this.f9256n);
        m10494a(this.f9257o);
    }
}
