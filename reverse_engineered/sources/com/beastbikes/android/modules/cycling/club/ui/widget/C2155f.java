package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityInfo;
import com.beastbikes.android.utils.C2555d;
import com.wdullaer.materialdatetimepicker.date.C4789b;
import com.wdullaer.materialdatetimepicker.date.C4789b.C4788b;
import com.wdullaer.materialdatetimepicker.time.C4830f;
import com.wdullaer.materialdatetimepicker.time.C4830f.C4829c;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: PickerDialogManange */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.f */
public class C2155f implements C4788b, C4829c {
    /* renamed from: a */
    private C4789b f10112a;
    /* renamed from: b */
    private String f10113b;
    /* renamed from: c */
    private final TextView f10114c;
    /* renamed from: d */
    private final TextView f10115d;
    /* renamed from: e */
    private final TextView f10116e;
    /* renamed from: f */
    private Activity f10117f;
    /* renamed from: g */
    private Calendar f10118g = Calendar.getInstance();
    /* renamed from: h */
    private int f10119h;
    /* renamed from: i */
    private int f10120i;
    /* renamed from: j */
    private int f10121j;
    /* renamed from: k */
    private C4830f f10122k;

    public C2155f(Activity activity, TextView textView, TextView textView2, TextView textView3) {
        this.f10117f = activity;
        this.f10114c = textView;
        this.f10115d = textView2;
        this.f10116e = textView3;
        int i = this.f10118g.get(1);
        int i2 = this.f10118g.get(2);
        int i3 = this.f10118g.get(5);
        this.f10112a = new C4789b();
        this.f10112a.b(activity.getResources().getColor(C1373R.color.bg_theme_black_color));
        this.f10112a.b(this, i, i2, i3);
        this.f10112a.a(this.f10118g);
    }

    /* renamed from: a */
    public void m11071a(ClubActivityInfo clubActivityInfo) {
        if (TextUtils.isEmpty(clubActivityInfo.getStartDate()) || clubActivityInfo.getStartDate().equals("0")) {
            this.f10114c.setText(m11061a(new Date().getTime() + 3600000));
        } else {
            this.f10114c.setText(m11064c(clubActivityInfo.getStartDate()));
            if (m11062a(clubActivityInfo.getStartDate())) {
                this.f10114c.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            } else {
                this.f10114c.setTextColor(this.f10117f.getResources().getColor(C1373R.color.design_color_c7));
            }
        }
        if (TextUtils.isEmpty(clubActivityInfo.getEndDate()) || clubActivityInfo.getEndDate().equals("0")) {
            this.f10115d.setText(m11061a(new Date().getTime() + 14400000));
        } else {
            this.f10115d.setText(m11064c(clubActivityInfo.getEndDate()));
            if (this.f10114c.getCurrentTextColor() != this.f10117f.getResources().getColor(C1373R.color.design_color_c7)) {
                this.f10115d.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            } else {
                this.f10115d.setTextColor(this.f10117f.getResources().getColor(C1373R.color.design_color_c7));
            }
        }
        if (TextUtils.isEmpty(clubActivityInfo.getApplyEndDate()) || clubActivityInfo.getApplyEndDate().equals("0")) {
            this.f10116e.setTextColor(this.f10117f.getResources().getColor(C1373R.color.design_color_c7));
            this.f10116e.setText(this.f10117f.getString(C1373R.string.club_act_time_str));
            return;
        }
        this.f10116e.setText(m11064c(clubActivityInfo.getApplyEndDate()));
        if (this.f10114c.getCurrentTextColor() != this.f10117f.getResources().getColor(C1373R.color.design_color_c7)) {
            this.f10116e.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.f10116e.setTextColor(this.f10117f.getResources().getColor(C1373R.color.design_color_c7));
        }
    }

    /* renamed from: a */
    public void m11070a() {
        if (!this.f10112a.isAdded()) {
            this.f10112a.show(this.f10117f.getFragmentManager(), "startTimeTv");
        }
    }

    /* renamed from: b */
    public void m11075b() {
        if (!this.f10112a.isAdded()) {
            this.f10112a.show(this.f10117f.getFragmentManager(), "endTimeTv");
        }
    }

    /* renamed from: c */
    public void m11076c() {
        if (!this.f10112a.isAdded()) {
            this.f10112a.b(this.f10117f.getResources().getColor(C1373R.color.bg_theme_black_color));
            Calendar h = m11069h();
            this.f10112a.b(this, h.get(1), h.get(2), h.get(5));
            this.f10112a.a(this.f10118g);
            this.f10112a.b(h);
            this.f10112a.show(this.f10117f.getFragmentManager(), "deadLineTv");
        }
    }

    /* renamed from: d */
    private void m11065d() {
        this.f10122k = C4830f.a(this, this.f10118g.get(11), this.f10118g.get(12), 0, true);
        this.f10122k.b(this.f10117f.getResources().getColor(C1373R.color.bg_theme_black_color));
        if (m11074a(this.f10118g)) {
            this.f10122k.a(this.f10118g.get(11), this.f10118g.get(12), 0);
        }
    }

    /* renamed from: e */
    private void m11066e() {
        m11065d();
        this.f10113b = "startTimeTv";
        this.f10122k.show(this.f10117f.getFragmentManager(), "startTimeTv");
    }

    /* renamed from: f */
    private void m11067f() {
        m11065d();
        this.f10113b = "endTimeTv";
        this.f10122k.show(this.f10117f.getFragmentManager(), "endTimeTv");
    }

    /* renamed from: g */
    private void m11068g() {
        C4830f a = C4830f.a(this, this.f10118g.get(11), this.f10118g.get(12), 0, true);
        a.b(this.f10117f.getResources().getColor(C1373R.color.bg_theme_black_color));
        if (m11074a(this.f10118g)) {
            a.a(this.f10118g.get(11), this.f10118g.get(12), 0);
        }
        Calendar h = m11069h();
        if (m11074a(h)) {
            a.b(h.get(11), h.get(12), 0);
        }
        this.f10113b = "deadLineTv";
        a.show(this.f10117f.getFragmentManager(), "deadLineTv");
    }

    /* renamed from: h */
    private Calendar m11069h() {
        Calendar instance = Calendar.getInstance();
        Object charSequence = this.f10114c.getText().toString();
        if (!TextUtils.isEmpty(charSequence)) {
            Date date = new Date();
            date.setTime(m11063b(charSequence));
            instance.setTime(date);
        }
        return instance;
    }

    /* renamed from: a */
    private String m11060a(int i, int i2, int i3, int i4, int i5) {
        String str = "";
        String str2 = (i2 + 1) + "";
        String str3 = i3 + "";
        String str4 = i4 + "";
        str = i5 + "";
        if (i2 < 10) {
            str2 = 0 + str2;
        }
        if (i3 < 10) {
            str3 = 0 + str3;
        }
        if (i4 < 10) {
            str4 = 0 + str4;
        }
        if (i5 < 10) {
            str = 0 + str;
        }
        return i + HelpFormatter.DEFAULT_OPT_PREFIX + str2 + HelpFormatter.DEFAULT_OPT_PREFIX + str3 + " " + str4 + ":" + str;
    }

    /* renamed from: a */
    private boolean m11062a(String str) {
        return new Date().getTime() - C2555d.m12800b(str) < 0;
    }

    /* renamed from: b */
    private long m11063b(String str) {
        long j = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                j = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return j;
    }

    /* renamed from: a */
    private String m11061a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(j));
    }

    /* renamed from: a */
    public void m11072a(C4789b c4789b, int i, int i2, int i3) {
        this.f10119h = i;
        this.f10120i = i2;
        this.f10121j = i3;
        String tag = c4789b.getTag();
        Object obj = -1;
        switch (tag.hashCode()) {
            case -1847837391:
                if (tag.equals("startTimeTv")) {
                    obj = null;
                    break;
                }
                break;
            case 1627521770:
                if (tag.equals("endTimeTv")) {
                    obj = 1;
                    break;
                }
                break;
            case 2040306458:
                if (tag.equals("deadLineTv")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                m11066e();
                return;
            case 1:
                m11067f();
                return;
            case 2:
                m11068g();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m11073a(RadialPickerLayout radialPickerLayout, int i, int i2, int i3) {
        String str = this.f10113b;
        Object obj = -1;
        switch (str.hashCode()) {
            case -1847837391:
                if (str.equals("startTimeTv")) {
                    obj = null;
                    break;
                }
                break;
            case 1627521770:
                if (str.equals("endTimeTv")) {
                    obj = 1;
                    break;
                }
                break;
            case 2040306458:
                if (str.equals("deadLineTv")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.f10114c.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.f10114c.setText(m11060a(this.f10119h, this.f10120i, this.f10121j, i, i2));
                long b = m11063b(m11060a(this.f10119h, this.f10120i, this.f10121j, i, i2));
                if (b > m11063b(this.f10115d.getText().toString())) {
                    this.f10115d.setTextColor(this.f10117f.getResources().getColor(C1373R.color.design_color_c7));
                } else {
                    this.f10115d.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }
                if (this.f10116e.getText().toString().equals(this.f10117f.getString(C1373R.string.club_act_time_str)) || b >= m11063b(this.f10116e.getText().toString())) {
                    this.f10116e.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    return;
                } else {
                    this.f10116e.setTextColor(this.f10117f.getResources().getColor(C1373R.color.design_color_c7));
                    return;
                }
            case 1:
                this.f10115d.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.f10115d.setText(m11060a(this.f10119h, this.f10120i, this.f10121j, i, i2));
                if (m11063b(m11060a(this.f10119h, this.f10120i, this.f10121j, i, i2)) < m11063b(this.f10114c.getText().toString())) {
                    this.f10114c.setTextColor(this.f10117f.getResources().getColor(C1373R.color.design_color_c7));
                    this.f10116e.setTextColor(this.f10117f.getResources().getColor(C1373R.color.design_color_c7));
                    return;
                }
                this.f10114c.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.f10116e.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                return;
            case 2:
                this.f10116e.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.f10116e.setText(m11060a(this.f10119h, this.f10120i, this.f10121j, i, i2));
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public boolean m11074a(Calendar calendar) {
        if (this.f10119h == calendar.get(1) && this.f10120i == calendar.get(2) && this.f10121j == calendar.get(5)) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    private String m11064c(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(Long.valueOf(C2555d.m12800b(str)).longValue()));
    }
}
