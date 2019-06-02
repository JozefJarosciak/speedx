package com.wdullaer.materialdatetimepicker.date;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.framework.ui.android.WebActivity;
import com.wdullaer.materialdatetimepicker.C4779R;
import com.wdullaer.materialdatetimepicker.C4781a;
import com.wdullaer.materialdatetimepicker.C4782b;
import com.wdullaer.materialdatetimepicker.C4783c;
import com.wdullaer.materialdatetimepicker.date.C4795d.C4793a;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: DatePickerDialog */
/* renamed from: com.wdullaer.materialdatetimepicker.date.b */
public class C4789b extends DialogFragment implements OnClickListener, C4784a {
    /* renamed from: a */
    private static SimpleDateFormat f16744a = new SimpleDateFormat("yyyy", Locale.getDefault());
    /* renamed from: b */
    private static SimpleDateFormat f16745b = new SimpleDateFormat("dd", Locale.getDefault());
    /* renamed from: A */
    private int f16746A = -1;
    /* renamed from: B */
    private boolean f16747B = true;
    /* renamed from: C */
    private boolean f16748C = false;
    /* renamed from: D */
    private int f16749D = 0;
    /* renamed from: E */
    private int f16750E = C4779R.string.mdtp_ok;
    /* renamed from: F */
    private String f16751F;
    /* renamed from: G */
    private int f16752G = C4779R.string.mdtp_cancel;
    /* renamed from: H */
    private String f16753H;
    /* renamed from: I */
    private C4781a f16754I;
    /* renamed from: J */
    private boolean f16755J = true;
    /* renamed from: K */
    private String f16756K;
    /* renamed from: L */
    private String f16757L;
    /* renamed from: M */
    private String f16758M;
    /* renamed from: N */
    private String f16759N;
    /* renamed from: c */
    private final Calendar f16760c = Calendar.getInstance();
    /* renamed from: d */
    private C4788b f16761d;
    /* renamed from: e */
    private HashSet<C4787a> f16762e = new HashSet();
    /* renamed from: f */
    private OnCancelListener f16763f;
    /* renamed from: g */
    private OnDismissListener f16764g;
    /* renamed from: h */
    private AccessibleDateAnimator f16765h;
    /* renamed from: i */
    private TextView f16766i;
    /* renamed from: j */
    private LinearLayout f16767j;
    /* renamed from: k */
    private TextView f16768k;
    /* renamed from: l */
    private TextView f16769l;
    /* renamed from: m */
    private TextView f16770m;
    /* renamed from: n */
    private C4792c f16771n;
    /* renamed from: o */
    private C4803i f16772o;
    /* renamed from: p */
    private int f16773p = -1;
    /* renamed from: q */
    private int f16774q = this.f16760c.getFirstDayOfWeek();
    /* renamed from: r */
    private int f16775r = 1900;
    /* renamed from: s */
    private int f16776s = 2100;
    /* renamed from: t */
    private String f16777t;
    /* renamed from: u */
    private Calendar f16778u;
    /* renamed from: v */
    private Calendar f16779v;
    /* renamed from: w */
    private Calendar[] f16780w;
    /* renamed from: x */
    private Calendar[] f16781x;
    /* renamed from: y */
    private boolean f16782y = false;
    /* renamed from: z */
    private boolean f16783z = false;

    /* compiled from: DatePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.date.b$1 */
    class C47851 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4789b f16742a;

        C47851(C4789b c4789b) {
            this.f16742a = c4789b;
        }

        public void onClick(View view) {
            this.f16742a.mo6196h();
            this.f16742a.m18817i();
            this.f16742a.dismiss();
        }
    }

    /* compiled from: DatePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.date.b$2 */
    class C47862 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4789b f16743a;

        C47862(C4789b c4789b) {
            this.f16743a = c4789b;
        }

        public void onClick(View view) {
            this.f16743a.mo6196h();
            if (this.f16743a.getDialog() != null) {
                this.f16743a.getDialog().cancel();
            }
        }
    }

    /* compiled from: DatePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.date.b$a */
    public interface C4787a {
        /* renamed from: a */
        void mo6197a();
    }

    /* compiled from: DatePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.date.b$b */
    public interface C4788b {
        /* renamed from: a */
        void m18789a(C4789b c4789b, int i, int i2, int i3);
    }

    /* renamed from: a */
    public static C4789b m18790a(C4788b c4788b, int i, int i2, int i3) {
        C4789b c4789b = new C4789b();
        c4789b.m18807b(c4788b, i, i2, i3);
        return c4789b;
    }

    /* renamed from: b */
    public void m18807b(C4788b c4788b, int i, int i2, int i3) {
        this.f16761d = c4788b;
        this.f16760c.set(1, i);
        this.f16760c.set(2, i2);
        this.f16760c.set(5, i3);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().getWindow().setSoftInputMode(3);
        this.f16773p = -1;
        if (bundle != null) {
            this.f16760c.set(1, bundle.getInt("year"));
            this.f16760c.set(2, bundle.getInt("month"));
            this.f16760c.set(5, bundle.getInt("day"));
            this.f16749D = bundle.getInt("default_view");
        }
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("year", this.f16760c.get(1));
        bundle.putInt("month", this.f16760c.get(2));
        bundle.putInt("day", this.f16760c.get(5));
        bundle.putInt("week_start", this.f16774q);
        bundle.putInt("year_start", this.f16775r);
        bundle.putInt("year_end", this.f16776s);
        bundle.putInt("current_view", this.f16773p);
        int i = -1;
        if (this.f16773p == 0) {
            i = this.f16771n.getMostVisiblePosition();
        } else if (this.f16773p == 1) {
            i = this.f16772o.getFirstVisiblePosition();
            bundle.putInt("list_position_offset", this.f16772o.getFirstPositionOffset());
        }
        bundle.putInt("list_position", i);
        bundle.putSerializable("min_date", this.f16778u);
        bundle.putSerializable("max_date", this.f16779v);
        bundle.putSerializable("highlighted_days", this.f16780w);
        bundle.putSerializable("selectable_days", this.f16781x);
        bundle.putBoolean("theme_dark", this.f16782y);
        bundle.putBoolean("theme_dark_changed", this.f16783z);
        bundle.putInt("accent", this.f16746A);
        bundle.putBoolean("vibrate", this.f16747B);
        bundle.putBoolean("dismiss", this.f16748C);
        bundle.putInt("default_view", this.f16749D);
        bundle.putString(WebActivity.EXTRA_TITLE, this.f16777t);
        bundle.putInt("ok_resid", this.f16750E);
        bundle.putString("ok_string", this.f16751F);
        bundle.putInt("cancel_resid", this.f16752G);
        bundle.putString("cancel_string", this.f16753H);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        int i3;
        Log.d("DatePickerDialog", "onCreateView: ");
        View inflate = layoutInflater.inflate(C4779R.layout.mdtp_date_picker_dialog, viewGroup, false);
        this.f16766i = (TextView) inflate.findViewById(C4779R.id.date_picker_header);
        this.f16767j = (LinearLayout) inflate.findViewById(C4779R.id.date_picker_month_and_day);
        this.f16767j.setOnClickListener(this);
        this.f16768k = (TextView) inflate.findViewById(C4779R.id.date_picker_month);
        this.f16769l = (TextView) inflate.findViewById(C4779R.id.date_picker_day);
        this.f16770m = (TextView) inflate.findViewById(C4779R.id.date_picker_year);
        this.f16770m.setOnClickListener(this);
        int i4 = this.f16749D;
        if (bundle != null) {
            this.f16774q = bundle.getInt("week_start");
            this.f16775r = bundle.getInt("year_start");
            this.f16776s = bundle.getInt("year_end");
            int i5 = bundle.getInt("current_view");
            i = bundle.getInt("list_position");
            i2 = bundle.getInt("list_position_offset");
            this.f16778u = (Calendar) bundle.getSerializable("min_date");
            this.f16779v = (Calendar) bundle.getSerializable("max_date");
            this.f16780w = (Calendar[]) bundle.getSerializable("highlighted_days");
            this.f16781x = (Calendar[]) bundle.getSerializable("selectable_days");
            this.f16782y = bundle.getBoolean("theme_dark");
            this.f16783z = bundle.getBoolean("theme_dark_changed");
            this.f16746A = bundle.getInt("accent");
            this.f16747B = bundle.getBoolean("vibrate");
            this.f16748C = bundle.getBoolean("dismiss");
            this.f16777t = bundle.getString(WebActivity.EXTRA_TITLE);
            this.f16750E = bundle.getInt("ok_resid");
            this.f16751F = bundle.getString("ok_string");
            this.f16752G = bundle.getInt("cancel_resid");
            this.f16753H = bundle.getString("cancel_string");
            i3 = i;
            i = i2;
            i2 = i5;
        } else {
            i = 0;
            i3 = -1;
            i2 = i4;
        }
        Context activity = getActivity();
        this.f16771n = new C4798f(activity, (C4784a) this);
        this.f16772o = new C4803i(activity, this);
        if (!this.f16783z) {
            this.f16782y = C4783c.m18771a(activity, this.f16782y);
        }
        Resources resources = getResources();
        this.f16756K = resources.getString(C4779R.string.mdtp_day_picker_description);
        this.f16757L = resources.getString(C4779R.string.mdtp_select_day);
        this.f16758M = resources.getString(C4779R.string.mdtp_year_picker_description);
        this.f16759N = resources.getString(C4779R.string.mdtp_select_year);
        inflate.setBackgroundColor(ContextCompat.getColor(activity, this.f16782y ? C4779R.color.mdtp_date_picker_view_animator_dark_theme : C4779R.color.mdtp_date_picker_view_animator));
        this.f16765h = (AccessibleDateAnimator) inflate.findViewById(C4779R.id.animator);
        this.f16765h.addView(this.f16771n);
        this.f16765h.addView(this.f16772o);
        this.f16765h.setDateMillis(this.f16760c.getTimeInMillis());
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        this.f16765h.setInAnimation(alphaAnimation);
        alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        this.f16765h.setOutAnimation(alphaAnimation);
        Button button = (Button) inflate.findViewById(C4779R.id.ok);
        button.setOnClickListener(new C47851(this));
        button.setTypeface(C4782b.m18764a(activity, "Roboto-Medium"));
        if (this.f16751F != null) {
            button.setText(this.f16751F);
        } else {
            button.setText(this.f16750E);
        }
        Button button2 = (Button) inflate.findViewById(C4779R.id.cancel);
        button2.setOnClickListener(new C47862(this));
        button2.setTypeface(C4782b.m18764a(activity, "Roboto-Medium"));
        if (this.f16753H != null) {
            button2.setText(this.f16753H);
        } else {
            button2.setText(this.f16752G);
        }
        button2.setVisibility(isCancelable() ? 0 : 8);
        if (this.f16746A == -1) {
            this.f16746A = C4783c.m18766a(getActivity());
        }
        if (this.f16766i != null) {
            this.f16766i.setBackgroundColor(C4783c.m18765a(this.f16746A));
        }
        inflate.findViewById(C4779R.id.day_picker_selected_date_layout).setBackgroundColor(this.f16746A);
        button.setTextColor(this.f16746A);
        button2.setTextColor(this.f16746A);
        if (getDialog() == null) {
            inflate.findViewById(C4779R.id.done_background).setVisibility(8);
        }
        m18791a(false);
        m18792c(i2);
        if (i3 != -1) {
            if (i2 == 0) {
                this.f16771n.m18824a(i3);
            } else if (i2 == 1) {
                this.f16772o.m18871a(i3, i);
            }
        }
        this.f16754I = new C4781a(activity);
        return inflate;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.requestWindowFeature(1);
        return onCreateDialog;
    }

    public void onResume() {
        super.onResume();
        this.f16754I.m18761a();
    }

    public void onPause() {
        super.onPause();
        this.f16754I.m18762b();
        if (this.f16748C) {
            dismiss();
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        if (this.f16763f != null) {
            this.f16763f.onCancel(dialogInterface);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.f16764g != null) {
            this.f16764g.onDismiss(dialogInterface);
        }
    }

    /* renamed from: c */
    private void m18792c(int i) {
        long timeInMillis = this.f16760c.getTimeInMillis();
        ObjectAnimator a;
        switch (i) {
            case 0:
                a = C4783c.m18767a(this.f16767j, 0.9f, 1.05f);
                if (this.f16755J) {
                    a.setStartDelay(500);
                    this.f16755J = false;
                }
                this.f16771n.mo6197a();
                if (this.f16773p != i) {
                    this.f16767j.setSelected(true);
                    this.f16770m.setSelected(false);
                    this.f16765h.setDisplayedChild(0);
                    this.f16773p = i;
                }
                a.start();
                this.f16765h.setContentDescription(this.f16756K + ": " + DateUtils.formatDateTime(getActivity(), timeInMillis, 16));
                C4783c.m18768a(this.f16765h, this.f16757L);
                return;
            case 1:
                a = C4783c.m18767a(this.f16770m, 0.85f, 1.1f);
                if (this.f16755J) {
                    a.setStartDelay(500);
                    this.f16755J = false;
                }
                this.f16772o.mo6197a();
                if (this.f16773p != i) {
                    this.f16767j.setSelected(false);
                    this.f16770m.setSelected(true);
                    this.f16765h.setDisplayedChild(1);
                    this.f16773p = i;
                }
                a.start();
                this.f16765h.setContentDescription(this.f16758M + ": " + f16744a.format(Long.valueOf(timeInMillis)));
                C4783c.m18768a(this.f16765h, this.f16759N);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m18791a(boolean z) {
        if (this.f16766i != null) {
            if (this.f16777t != null) {
                this.f16766i.setText(this.f16777t.toUpperCase(Locale.getDefault()));
            } else {
                this.f16766i.setText(this.f16760c.getDisplayName(7, 2, Locale.getDefault()).toUpperCase(Locale.getDefault()));
            }
        }
        this.f16768k.setText(this.f16760c.getDisplayName(2, 1, Locale.getDefault()).toUpperCase(Locale.getDefault()));
        this.f16769l.setText(f16745b.format(this.f16760c.getTime()));
        this.f16770m.setText(f16744a.format(this.f16760c.getTime()));
        long timeInMillis = this.f16760c.getTimeInMillis();
        this.f16765h.setDateMillis(timeInMillis);
        this.f16767j.setContentDescription(DateUtils.formatDateTime(getActivity(), timeInMillis, 24));
        if (z) {
            C4783c.m18768a(this.f16765h, DateUtils.formatDateTime(getActivity(), timeInMillis, 20));
        }
    }

    /* renamed from: b */
    public boolean mo6189b() {
        return this.f16782y;
    }

    /* renamed from: b */
    public void m18806b(int i) {
        this.f16746A = i;
    }

    /* renamed from: c */
    public int mo6191c() {
        return this.f16746A;
    }

    /* renamed from: a */
    public void m18805a(Calendar calendar) {
        this.f16778u = calendar;
        if (this.f16771n != null) {
            this.f16771n.m18827b();
        }
    }

    /* renamed from: b */
    public void m18808b(Calendar calendar) {
        this.f16779v = calendar;
        if (this.f16771n != null) {
            this.f16771n.m18827b();
        }
    }

    /* renamed from: d */
    public Calendar[] mo6192d() {
        return this.f16780w;
    }

    /* renamed from: c */
    private void m18793c(Calendar calendar) {
        int i = calendar.get(5);
        int actualMaximum = calendar.getActualMaximum(5);
        if (i > actualMaximum) {
            calendar.set(5, actualMaximum);
        }
        m18799f(calendar);
    }

    public void onClick(View view) {
        mo6196h();
        if (view.getId() == C4779R.id.date_picker_year) {
            m18792c(1);
        } else if (view.getId() == C4779R.id.date_picker_month_and_day) {
            m18792c(0);
        }
    }

    /* renamed from: a */
    public void mo6186a(int i) {
        this.f16760c.set(1, i);
        m18793c(this.f16760c);
        m18800j();
        m18792c(0);
        m18791a(true);
    }

    /* renamed from: a */
    public void mo6187a(int i, int i2, int i3) {
        this.f16760c.set(1, i);
        this.f16760c.set(2, i2);
        this.f16760c.set(5, i3);
        m18800j();
        m18791a(true);
    }

    /* renamed from: j */
    private void m18800j() {
        Iterator it = this.f16762e.iterator();
        while (it.hasNext()) {
            ((C4787a) it.next()).mo6197a();
        }
    }

    /* renamed from: a */
    public C4793a mo6185a() {
        return new C4793a(this.f16760c);
    }

    /* renamed from: f */
    public int mo6194f() {
        if (this.f16781x != null) {
            return this.f16781x[0].get(1);
        }
        return (this.f16778u == null || this.f16778u.get(1) <= this.f16775r) ? this.f16775r : this.f16778u.get(1);
    }

    /* renamed from: g */
    public int mo6195g() {
        if (this.f16781x != null) {
            return this.f16781x[this.f16781x.length - 1].get(1);
        }
        return (this.f16779v == null || this.f16779v.get(1) >= this.f16776s) ? this.f16776s : this.f16779v.get(1);
    }

    /* renamed from: b */
    public boolean mo6190b(int i, int i2, int i3) {
        if (this.f16781x != null) {
            if (m18794c(i, i2, i3)) {
                return false;
            }
            return true;
        } else if (m18795d(i, i2, i3) || m18797e(i, i2, i3)) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: c */
    private boolean m18794c(int i, int i2, int i3) {
        for (Calendar calendar : this.f16781x) {
            if (i < calendar.get(1)) {
                break;
            }
            if (i <= calendar.get(1)) {
                if (i2 >= calendar.get(2)) {
                    if (i2 <= calendar.get(2)) {
                        if (i3 < calendar.get(5)) {
                            break;
                        } else if (i3 <= calendar.get(5)) {
                            return true;
                        }
                    } else {
                        continue;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }

    /* renamed from: d */
    private boolean m18795d(int i, int i2, int i3) {
        if (this.f16778u == null) {
            return false;
        }
        if (i < this.f16778u.get(1)) {
            return true;
        }
        if (i > this.f16778u.get(1)) {
            return false;
        }
        if (i2 < this.f16778u.get(2)) {
            return true;
        }
        if (i2 > this.f16778u.get(2) || i3 >= this.f16778u.get(5)) {
            return false;
        }
        return true;
    }

    /* renamed from: d */
    private boolean m18796d(Calendar calendar) {
        return m18795d(calendar.get(1), calendar.get(2), calendar.get(5));
    }

    /* renamed from: e */
    private boolean m18797e(int i, int i2, int i3) {
        if (this.f16779v == null) {
            return false;
        }
        if (i > this.f16779v.get(1)) {
            return true;
        }
        if (i < this.f16779v.get(1)) {
            return false;
        }
        if (i2 > this.f16779v.get(2)) {
            return true;
        }
        if (i2 < this.f16779v.get(2) || i3 <= this.f16779v.get(5)) {
            return false;
        }
        return true;
    }

    /* renamed from: e */
    private boolean m18798e(Calendar calendar) {
        return m18797e(calendar.get(1), calendar.get(2), calendar.get(5));
    }

    /* renamed from: f */
    private void m18799f(Calendar calendar) {
        if (this.f16781x != null) {
            int i = Integer.MAX_VALUE;
            Calendar[] calendarArr = this.f16781x;
            int length = calendarArr.length;
            int i2 = 0;
            while (i2 < length) {
                Calendar calendar2 = calendarArr[i2];
                int abs = Math.abs(calendar.compareTo(calendar2));
                if (abs < i) {
                    i2++;
                    i = abs;
                } else {
                    calendar.setTimeInMillis(calendar2.getTimeInMillis());
                    return;
                }
            }
        } else if (m18796d(calendar)) {
            calendar.setTimeInMillis(this.f16778u.getTimeInMillis());
        } else if (m18798e(calendar)) {
            calendar.setTimeInMillis(this.f16779v.getTimeInMillis());
        }
    }

    /* renamed from: e */
    public int mo6193e() {
        return this.f16774q;
    }

    /* renamed from: a */
    public void mo6188a(C4787a c4787a) {
        this.f16762e.add(c4787a);
    }

    /* renamed from: h */
    public void mo6196h() {
        if (this.f16747B) {
            this.f16754I.m18763c();
        }
    }

    /* renamed from: i */
    public void m18817i() {
        if (this.f16761d != null) {
            this.f16761d.m18789a(this, this.f16760c.get(1), this.f16760c.get(2), this.f16760c.get(5));
        }
    }
}
