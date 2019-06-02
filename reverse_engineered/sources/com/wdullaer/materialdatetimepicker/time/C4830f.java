package com.wdullaer.materialdatetimepicker.time;

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
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wdullaer.materialdatetimepicker.C4779R;
import com.wdullaer.materialdatetimepicker.C4781a;
import com.wdullaer.materialdatetimepicker.C4782b;
import com.wdullaer.materialdatetimepicker.C4783c;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout.C4810a;
import com.wdullaer.materialdatetimepicker.time.Timepoint.TYPE;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: TimePickerDialog */
/* renamed from: com.wdullaer.materialdatetimepicker.time.f */
public class C4830f extends DialogFragment implements C4810a, C4820e {
    /* renamed from: A */
    private int f17015A = -1;
    /* renamed from: B */
    private boolean f17016B;
    /* renamed from: C */
    private Timepoint[] f17017C;
    /* renamed from: D */
    private Timepoint f17018D;
    /* renamed from: E */
    private Timepoint f17019E;
    /* renamed from: F */
    private boolean f17020F;
    /* renamed from: G */
    private int f17021G;
    /* renamed from: H */
    private String f17022H;
    /* renamed from: I */
    private int f17023I;
    /* renamed from: J */
    private String f17024J;
    /* renamed from: K */
    private char f17025K;
    /* renamed from: L */
    private String f17026L;
    /* renamed from: M */
    private String f17027M;
    /* renamed from: N */
    private boolean f17028N;
    /* renamed from: O */
    private ArrayList<Integer> f17029O;
    /* renamed from: P */
    private C4828b f17030P;
    /* renamed from: Q */
    private int f17031Q;
    /* renamed from: R */
    private int f17032R;
    /* renamed from: S */
    private String f17033S;
    /* renamed from: T */
    private String f17034T;
    /* renamed from: U */
    private String f17035U;
    /* renamed from: V */
    private String f17036V;
    /* renamed from: W */
    private String f17037W;
    /* renamed from: X */
    private String f17038X;
    /* renamed from: a */
    private C4829c f17039a;
    /* renamed from: b */
    private OnCancelListener f17040b;
    /* renamed from: c */
    private OnDismissListener f17041c;
    /* renamed from: d */
    private C4781a f17042d;
    /* renamed from: e */
    private Button f17043e;
    /* renamed from: f */
    private Button f17044f;
    /* renamed from: g */
    private TextView f17045g;
    /* renamed from: h */
    private TextView f17046h;
    /* renamed from: i */
    private TextView f17047i;
    /* renamed from: j */
    private TextView f17048j;
    /* renamed from: k */
    private TextView f17049k;
    /* renamed from: l */
    private TextView f17050l;
    /* renamed from: m */
    private TextView f17051m;
    /* renamed from: n */
    private View f17052n;
    /* renamed from: o */
    private RadialPickerLayout f17053o;
    /* renamed from: p */
    private int f17054p;
    /* renamed from: q */
    private int f17055q;
    /* renamed from: r */
    private String f17056r;
    /* renamed from: s */
    private String f17057s;
    /* renamed from: t */
    private boolean f17058t;
    /* renamed from: u */
    private Timepoint f17059u;
    /* renamed from: v */
    private boolean f17060v;
    /* renamed from: w */
    private String f17061w;
    /* renamed from: x */
    private boolean f17062x;
    /* renamed from: y */
    private boolean f17063y;
    /* renamed from: z */
    private boolean f17064z;

    /* compiled from: TimePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.f$1 */
    class C48211 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4830f f17006a;

        C48211(C4830f c4830f) {
            this.f17006a = c4830f;
        }

        public void onClick(View view) {
            this.f17006a.m18939a(0, true, false, true);
            this.f17006a.mo6211e();
        }
    }

    /* compiled from: TimePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.f$2 */
    class C48222 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4830f f17007a;

        C48222(C4830f c4830f) {
            this.f17007a = c4830f;
        }

        public void onClick(View view) {
            this.f17007a.m18939a(1, true, false, true);
            this.f17007a.mo6211e();
        }
    }

    /* compiled from: TimePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.f$3 */
    class C48233 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4830f f17008a;

        C48233(C4830f c4830f) {
            this.f17008a = c4830f;
        }

        public void onClick(View view) {
            this.f17008a.m18939a(2, true, false, true);
            this.f17008a.mo6211e();
        }
    }

    /* compiled from: TimePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.f$4 */
    class C48244 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4830f f17009a;

        C48244(C4830f c4830f) {
            this.f17009a = c4830f;
        }

        public void onClick(View view) {
            if (this.f17009a.f17028N && this.f17009a.m18959j()) {
                this.f17009a.m18942a(false);
            } else {
                this.f17009a.mo6211e();
            }
            this.f17009a.m18980h();
            this.f17009a.dismiss();
        }
    }

    /* compiled from: TimePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.f$5 */
    class C48255 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4830f f17010a;

        C48255(C4830f c4830f) {
            this.f17010a = c4830f;
        }

        public void onClick(View view) {
            this.f17010a.mo6211e();
            if (this.f17010a.getDialog() != null) {
                this.f17010a.getDialog().cancel();
            }
        }
    }

    /* compiled from: TimePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.f$6 */
    class C48266 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C4830f f17011a;

        C48266(C4830f c4830f) {
            this.f17011a = c4830f;
        }

        public void onClick(View view) {
            int i = 1;
            if (!this.f17011a.mo6212f() && !this.f17011a.mo6213g()) {
                this.f17011a.mo6211e();
                int isCurrentlyAmOrPm = this.f17011a.f17053o.getIsCurrentlyAmOrPm();
                if (isCurrentlyAmOrPm != 0) {
                    i = isCurrentlyAmOrPm == 1 ? 0 : isCurrentlyAmOrPm;
                }
                this.f17011a.f17053o.setAmOrPm(i);
            }
        }
    }

    /* compiled from: TimePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.f$a */
    private class C4827a implements OnKeyListener {
        /* renamed from: a */
        final /* synthetic */ C4830f f17012a;

        private C4827a(C4830f c4830f) {
            this.f17012a = c4830f;
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 1) {
                return this.f17012a.m18953f(i);
            }
            return false;
        }
    }

    /* compiled from: TimePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.f$b */
    private static class C4828b {
        /* renamed from: a */
        private int[] f17013a;
        /* renamed from: b */
        private ArrayList<C4828b> f17014b = new ArrayList();

        public C4828b(int... iArr) {
            this.f17013a = iArr;
        }

        /* renamed from: a */
        public void m18933a(C4828b c4828b) {
            this.f17014b.add(c4828b);
        }

        /* renamed from: a */
        public boolean m18934a(int i) {
            for (int i2 : this.f17013a) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: b */
        public C4828b m18935b(int i) {
            if (this.f17014b == null) {
                return null;
            }
            Iterator it = this.f17014b.iterator();
            while (it.hasNext()) {
                C4828b c4828b = (C4828b) it.next();
                if (c4828b.m18934a(i)) {
                    return c4828b;
                }
            }
            return null;
        }
    }

    /* compiled from: TimePickerDialog */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.f$c */
    public interface C4829c {
        /* renamed from: a */
        void m18936a(RadialPickerLayout radialPickerLayout, int i, int i2, int i3);
    }

    /* renamed from: a */
    public static C4830f m18937a(C4829c c4829c, int i, int i2, int i3, boolean z) {
        C4830f c4830f = new C4830f();
        c4830f.m18971b(c4829c, i, i2, i3, z);
        return c4830f;
    }

    /* renamed from: b */
    public void m18971b(C4829c c4829c, int i, int i2, int i3, boolean z) {
        this.f17039a = c4829c;
        this.f17059u = new Timepoint(i, i2, i3);
        this.f17060v = z;
        this.f17028N = false;
        this.f17061w = "";
        this.f17062x = false;
        this.f17063y = false;
        this.f17015A = -1;
        this.f17064z = true;
        this.f17016B = false;
        this.f17020F = false;
        this.f17021G = C4779R.string.mdtp_ok;
        this.f17023I = C4779R.string.mdtp_cancel;
    }

    /* renamed from: b */
    public void m18968b(int i) {
        this.f17015A = i;
    }

    /* renamed from: b */
    public boolean mo6208b() {
        return this.f17062x;
    }

    /* renamed from: c */
    public boolean mo6209c() {
        return this.f17060v;
    }

    /* renamed from: d */
    public int mo6210d() {
        return this.f17015A;
    }

    /* renamed from: a */
    public void m18965a(int i, int i2, int i3) {
        m18970b(new Timepoint(i, i2, i3));
    }

    /* renamed from: b */
    public void m18970b(Timepoint timepoint) {
        if (this.f17019E == null || timepoint.m18907a(this.f17019E) <= 0) {
            this.f17018D = timepoint;
            return;
        }
        throw new IllegalArgumentException("Minimum time must be smaller than the maximum time");
    }

    /* renamed from: b */
    public void m18969b(int i, int i2, int i3) {
        m18973c(new Timepoint(i, i2, i3));
    }

    /* renamed from: c */
    public void m18973c(Timepoint timepoint) {
        if (this.f17018D == null || timepoint.m18907a(this.f17018D) >= 0) {
            this.f17019E = timepoint;
            return;
        }
        throw new IllegalArgumentException("Maximum time must be greater than the minimum time");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && bundle.containsKey("initial_time") && bundle.containsKey("is_24_hour_view")) {
            this.f17059u = (Timepoint) bundle.getParcelable("initial_time");
            this.f17060v = bundle.getBoolean("is_24_hour_view");
            this.f17028N = bundle.getBoolean("in_kb_mode");
            this.f17061w = bundle.getString("dialog_title");
            this.f17062x = bundle.getBoolean("theme_dark");
            this.f17063y = bundle.getBoolean("theme_dark_changed");
            this.f17015A = bundle.getInt("accent");
            this.f17064z = bundle.getBoolean("vibrate");
            this.f17016B = bundle.getBoolean("dismiss");
            this.f17017C = (Timepoint[]) bundle.getParcelableArray("selectable_times");
            this.f17018D = (Timepoint) bundle.getParcelable("min_time");
            this.f17019E = (Timepoint) bundle.getParcelable("max_time");
            this.f17020F = bundle.getBoolean("enable_seconds");
            this.f17021G = bundle.getInt("ok_resid");
            this.f17022H = bundle.getString("ok_string");
            this.f17023I = bundle.getInt("cancel_resid");
            this.f17024J = bundle.getString("cancel_string");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4779R.layout.mdtp_time_picker_dialog, viewGroup, false);
        OnKeyListener c4827a = new C4827a();
        inflate.findViewById(C4779R.id.time_picker_dialog).setOnKeyListener(c4827a);
        if (this.f17015A == -1) {
            this.f17015A = C4783c.m18766a(getActivity());
        }
        if (!this.f17063y) {
            this.f17062x = C4783c.m18771a(getActivity(), this.f17062x);
        }
        Resources resources = getResources();
        Context activity = getActivity();
        this.f17033S = resources.getString(C4779R.string.mdtp_hour_picker_description);
        this.f17034T = resources.getString(C4779R.string.mdtp_select_hours);
        this.f17035U = resources.getString(C4779R.string.mdtp_minute_picker_description);
        this.f17036V = resources.getString(C4779R.string.mdtp_select_minutes);
        this.f17037W = resources.getString(C4779R.string.mdtp_second_picker_description);
        this.f17038X = resources.getString(C4779R.string.mdtp_select_seconds);
        this.f17054p = ContextCompat.getColor(activity, C4779R.color.mdtp_white);
        this.f17055q = ContextCompat.getColor(activity, C4779R.color.mdtp_accent_color_focused);
        this.f17045g = (TextView) inflate.findViewById(C4779R.id.hours);
        this.f17045g.setOnKeyListener(c4827a);
        this.f17046h = (TextView) inflate.findViewById(C4779R.id.hour_space);
        this.f17048j = (TextView) inflate.findViewById(C4779R.id.minutes_space);
        this.f17047i = (TextView) inflate.findViewById(C4779R.id.minutes);
        this.f17047i.setOnKeyListener(c4827a);
        this.f17050l = (TextView) inflate.findViewById(C4779R.id.seconds_space);
        this.f17049k = (TextView) inflate.findViewById(C4779R.id.seconds);
        this.f17049k.setOnKeyListener(c4827a);
        this.f17051m = (TextView) inflate.findViewById(C4779R.id.ampm_label);
        this.f17051m.setOnKeyListener(c4827a);
        String[] amPmStrings = new DateFormatSymbols().getAmPmStrings();
        this.f17056r = amPmStrings[0];
        this.f17057s = amPmStrings[1];
        this.f17042d = new C4781a(getActivity());
        this.f17059u = m18951e(this.f17059u);
        this.f17053o = (RadialPickerLayout) inflate.findViewById(C4779R.id.time_picker);
        this.f17053o.setOnValueSelectedListener(this);
        this.f17053o.setOnKeyListener(c4827a);
        this.f17053o.m18902a(getActivity(), this, this.f17059u, this.f17060v);
        int i = 0;
        if (bundle != null && bundle.containsKey("current_item_showing")) {
            i = bundle.getInt("current_item_showing");
        }
        m18939a(i, false, true, true);
        this.f17053o.invalidate();
        this.f17045g.setOnClickListener(new C48211(this));
        this.f17047i.setOnClickListener(new C48222(this));
        this.f17049k.setOnClickListener(new C48233(this));
        this.f17044f = (Button) inflate.findViewById(C4779R.id.ok);
        this.f17044f.setOnClickListener(new C48244(this));
        this.f17044f.setOnKeyListener(c4827a);
        this.f17044f.setTypeface(C4782b.m18764a(activity, "Roboto-Medium"));
        if (this.f17022H != null) {
            this.f17044f.setText(this.f17022H);
        } else {
            this.f17044f.setText(this.f17021G);
        }
        this.f17043e = (Button) inflate.findViewById(C4779R.id.cancel);
        this.f17043e.setOnClickListener(new C48255(this));
        this.f17043e.setTypeface(C4782b.m18764a(activity, "Roboto-Medium"));
        if (this.f17024J != null) {
            this.f17043e.setText(this.f17024J);
        } else {
            this.f17043e.setText(this.f17023I);
        }
        this.f17043e.setVisibility(isCancelable() ? 0 : 8);
        this.f17052n = inflate.findViewById(C4779R.id.ampm_hitspace);
        if (this.f17060v) {
            this.f17051m.setVisibility(8);
        } else {
            this.f17051m.setVisibility(0);
            m18949c(this.f17059u.m18910d() ? 0 : 1);
            this.f17052n.setOnClickListener(new C48266(this));
        }
        if (!this.f17020F) {
            this.f17050l.setVisibility(8);
            inflate.findViewById(C4779R.id.separator_seconds).setVisibility(8);
        }
        LayoutParams layoutParams;
        if (this.f17060v && !this.f17020F) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            ((TextView) inflate.findViewById(C4779R.id.separator)).setLayoutParams(layoutParams);
        } else if (this.f17020F) {
            View findViewById = inflate.findViewById(C4779R.id.separator);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(0, C4779R.id.minutes_space);
            layoutParams.addRule(15, -1);
            findViewById.setLayoutParams(layoutParams);
            LayoutParams layoutParams2;
            if (this.f17060v) {
                layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(1, C4779R.id.center_view);
                this.f17048j.setLayoutParams(layoutParams2);
            } else {
                layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(13);
                this.f17048j.setLayoutParams(layoutParams2);
            }
        }
        this.f17058t = true;
        m18938a(this.f17059u.m18906a(), true);
        m18950d(this.f17059u.m18908b());
        m18952e(this.f17059u.m18909c());
        this.f17026L = resources.getString(C4779R.string.mdtp_time_placeholder);
        this.f17027M = resources.getString(C4779R.string.mdtp_deleted_key);
        this.f17025K = this.f17026L.charAt(0);
        this.f17032R = -1;
        this.f17031Q = -1;
        m18961l();
        if (this.f17028N) {
            this.f17029O = bundle.getIntegerArrayList("typed_times");
            m18954g(-1);
            this.f17045g.invalidate();
        } else if (this.f17029O == null) {
            this.f17029O = new ArrayList();
        }
        TextView textView = (TextView) inflate.findViewById(C4779R.id.time_picker_header);
        if (!this.f17061w.isEmpty()) {
            textView.setVisibility(0);
            textView.setText(this.f17061w);
        }
        this.f17044f.setTextColor(this.f17015A);
        this.f17043e.setTextColor(this.f17015A);
        textView.setBackgroundColor(C4783c.m18765a(this.f17015A));
        inflate.findViewById(C4779R.id.time_display_background).setBackgroundColor(this.f17015A);
        inflate.findViewById(C4779R.id.time_display).setBackgroundColor(this.f17015A);
        if (getDialog() == null) {
            inflate.findViewById(C4779R.id.done_background).setVisibility(8);
        }
        int color = ContextCompat.getColor(activity, C4779R.color.mdtp_circle_background);
        int color2 = ContextCompat.getColor(activity, C4779R.color.mdtp_background_color);
        int color3 = ContextCompat.getColor(activity, C4779R.color.mdtp_light_gray);
        i = ContextCompat.getColor(activity, C4779R.color.mdtp_light_gray);
        RadialPickerLayout radialPickerLayout = this.f17053o;
        if (!this.f17062x) {
            i = color;
        }
        radialPickerLayout.setBackgroundColor(i);
        View findViewById2 = inflate.findViewById(C4779R.id.time_picker_dialog);
        if (this.f17062x) {
            i = color3;
        } else {
            i = color2;
        }
        findViewById2.setBackgroundColor(i);
        return inflate;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.requestWindowFeature(1);
        return onCreateDialog;
    }

    public void onResume() {
        super.onResume();
        this.f17042d.m18761a();
    }

    public void onPause() {
        super.onPause();
        this.f17042d.m18762b();
        if (this.f17016B) {
            dismiss();
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        if (this.f17040b != null) {
            this.f17040b.onCancel(dialogInterface);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.f17041c != null) {
            this.f17041c.onDismiss(dialogInterface);
        }
    }

    /* renamed from: e */
    public void mo6211e() {
        if (this.f17064z) {
            this.f17042d.m18763c();
        }
    }

    /* renamed from: c */
    private void m18949c(int i) {
        if (i == 0) {
            this.f17051m.setText(this.f17056r);
            C4783c.m18768a(this.f17053o, this.f17056r);
            this.f17052n.setContentDescription(this.f17056r);
        } else if (i == 1) {
            this.f17051m.setText(this.f17057s);
            C4783c.m18768a(this.f17053o, this.f17057s);
            this.f17052n.setContentDescription(this.f17057s);
        } else {
            this.f17051m.setText(this.f17026L);
        }
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        if (this.f17053o != null) {
            bundle.putParcelable("initial_time", this.f17053o.getTime());
            bundle.putBoolean("is_24_hour_view", this.f17060v);
            bundle.putInt("current_item_showing", this.f17053o.getCurrentItemShowing());
            bundle.putBoolean("in_kb_mode", this.f17028N);
            if (this.f17028N) {
                bundle.putIntegerArrayList("typed_times", this.f17029O);
            }
            bundle.putString("dialog_title", this.f17061w);
            bundle.putBoolean("theme_dark", this.f17062x);
            bundle.putBoolean("theme_dark_changed", this.f17063y);
            bundle.putInt("accent", this.f17015A);
            bundle.putBoolean("vibrate", this.f17064z);
            bundle.putBoolean("dismiss", this.f17016B);
            bundle.putParcelableArray("selectable_times", this.f17017C);
            bundle.putParcelable("min_time", this.f17018D);
            bundle.putParcelable("max_time", this.f17019E);
            bundle.putBoolean("enable_seconds", this.f17020F);
            bundle.putInt("ok_resid", this.f17021G);
            bundle.putString("ok_string", this.f17022H);
            bundle.putInt("cancel_resid", this.f17023I);
            bundle.putString("cancel_string", this.f17024J);
        }
    }

    /* renamed from: a */
    public void mo6206a(Timepoint timepoint) {
        int i = 0;
        m18938a(timepoint.m18906a(), false);
        this.f17053o.setContentDescription(this.f17033S + ": " + timepoint.m18906a());
        m18950d(timepoint.m18908b());
        this.f17053o.setContentDescription(this.f17035U + ": " + timepoint.m18908b());
        m18952e(timepoint.m18909c());
        this.f17053o.setContentDescription(this.f17037W + ": " + timepoint.m18909c());
        if (!this.f17060v) {
            if (!timepoint.m18910d()) {
                i = 1;
            }
            m18949c(i);
        }
    }

    /* renamed from: a */
    public void mo6205a(int i) {
        if (!this.f17058t) {
            return;
        }
        if (i == 0) {
            m18939a(1, true, true, false);
            C4783c.m18768a(this.f17053o, this.f17034T + ". " + this.f17053o.getMinutes());
        } else if (i == 1 && this.f17020F) {
            m18939a(2, true, true, false);
            C4783c.m18768a(this.f17053o, this.f17036V + ". " + this.f17053o.getSeconds());
        }
    }

    /* renamed from: a */
    public void mo6204a() {
        if (!m18959j()) {
            this.f17029O.clear();
        }
        m18942a(true);
    }

    /* renamed from: d */
    public boolean m18976d(Timepoint timepoint) {
        if (this.f17017C != null) {
            return !Arrays.asList(this.f17017C).contains(timepoint);
        } else {
            if (this.f17018D != null && this.f17018D.m18907a(timepoint) > 0) {
                return true;
            }
            if (this.f17019E == null || this.f17019E.m18907a(timepoint) >= 0) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: a */
    public boolean mo6207a(Timepoint timepoint, int i) {
        if (timepoint == null) {
            return false;
        }
        if (i == 0) {
            if (this.f17017C != null) {
                for (Timepoint a : this.f17017C) {
                    if (a.m18906a() == timepoint.m18906a()) {
                        return false;
                    }
                }
                return true;
            } else if (this.f17018D != null && this.f17018D.m18906a() > timepoint.m18906a()) {
                return true;
            } else {
                if (this.f17019E == null || this.f17019E.m18906a() + 1 > timepoint.m18906a()) {
                    return false;
                }
                return true;
            }
        } else if (i != 1) {
            return m18976d(timepoint);
        } else {
            if (this.f17017C != null) {
                for (Timepoint a2 : this.f17017C) {
                    if (a2.m18906a() == timepoint.m18906a() && a2.m18908b() == timepoint.m18908b()) {
                        return false;
                    }
                }
                return true;
            } else if (this.f17018D != null && new Timepoint(this.f17018D.m18906a(), this.f17018D.m18908b()).m18907a(timepoint) > 0) {
                return true;
            } else {
                if (this.f17019E == null || new Timepoint(this.f17019E.m18906a(), this.f17019E.m18908b(), 59).m18907a(timepoint) >= 0) {
                    return false;
                }
                return true;
            }
        }
    }

    /* renamed from: f */
    public boolean mo6212f() {
        Timepoint timepoint = new Timepoint(12);
        if (this.f17017C != null) {
            for (Timepoint a : this.f17017C) {
                if (a.m18907a(timepoint) < 0) {
                    return false;
                }
            }
            return true;
        } else if (this.f17018D == null || this.f17018D.m18907a(timepoint) <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: g */
    public boolean mo6213g() {
        Timepoint timepoint = new Timepoint(12);
        if (this.f17017C != null) {
            for (Timepoint a : this.f17017C) {
                if (a.m18907a(timepoint) >= 0) {
                    return false;
                }
            }
            return true;
        } else if (this.f17019E == null || this.f17019E.m18907a(timepoint) >= 0) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: e */
    private Timepoint m18951e(Timepoint timepoint) {
        return mo6203a(timepoint, TYPE.HOUR);
    }

    /* renamed from: a */
    public Timepoint mo6203a(Timepoint timepoint, TYPE type) {
        if (this.f17017C != null) {
            int i = Integer.MAX_VALUE;
            Timepoint[] timepointArr = this.f17017C;
            int length = timepointArr.length;
            int i2 = 0;
            Timepoint timepoint2 = timepoint;
            while (i2 < length) {
                int i3;
                Timepoint timepoint3 = timepointArr[i2];
                if (type == TYPE.MINUTE && timepoint3.m18906a() != timepoint.m18906a()) {
                    i3 = i;
                } else if (type != TYPE.SECOND || timepoint3.m18906a() == timepoint.m18906a() || timepoint3.m18908b() == timepoint.m18908b()) {
                    int abs = Math.abs(timepoint3.m18907a(timepoint));
                    if (abs >= i) {
                        return timepoint2;
                    }
                    timepoint2 = timepoint3;
                    i3 = abs;
                } else {
                    i3 = i;
                }
                i2++;
                i = i3;
            }
            return timepoint2;
        } else if (this.f17018D == null || this.f17018D.m18907a(timepoint) <= 0) {
            return (this.f17019E == null || this.f17019E.m18907a(timepoint) >= 0) ? timepoint : this.f17019E;
        } else {
            return this.f17018D;
        }
    }

    /* renamed from: a */
    private void m18938a(int i, boolean z) {
        String str;
        if (this.f17060v) {
            str = "%02d";
        } else {
            str = "%d";
            i %= 12;
            if (i == 0) {
                i = 12;
            }
        }
        CharSequence format = String.format(str, new Object[]{Integer.valueOf(i)});
        this.f17045g.setText(format);
        this.f17046h.setText(format);
        if (z) {
            C4783c.m18768a(this.f17053o, format);
        }
    }

    /* renamed from: d */
    private void m18950d(int i) {
        if (i == 60) {
            i = 0;
        }
        CharSequence format = String.format(Locale.getDefault(), "%02d", new Object[]{Integer.valueOf(i)});
        C4783c.m18768a(this.f17053o, format);
        this.f17047i.setText(format);
        this.f17048j.setText(format);
    }

    /* renamed from: e */
    private void m18952e(int i) {
        if (i == 60) {
            i = 0;
        }
        CharSequence format = String.format(Locale.getDefault(), "%02d", new Object[]{Integer.valueOf(i)});
        C4783c.m18768a(this.f17053o, format);
        this.f17049k.setText(format);
        this.f17050l.setText(format);
    }

    /* renamed from: a */
    private void m18939a(int i, boolean z, boolean z2, boolean z3) {
        int hours;
        View view;
        this.f17053o.m18901a(i, z);
        switch (i) {
            case 0:
                hours = this.f17053o.getHours();
                if (!this.f17060v) {
                    hours %= 12;
                }
                this.f17053o.setContentDescription(this.f17033S + ": " + hours);
                if (z3) {
                    C4783c.m18768a(this.f17053o, this.f17034T);
                }
                view = this.f17045g;
                break;
            case 1:
                this.f17053o.setContentDescription(this.f17035U + ": " + this.f17053o.getMinutes());
                if (z3) {
                    C4783c.m18768a(this.f17053o, this.f17036V);
                }
                view = this.f17047i;
                break;
            default:
                this.f17053o.setContentDescription(this.f17037W + ": " + this.f17053o.getSeconds());
                if (z3) {
                    C4783c.m18768a(this.f17053o, this.f17038X);
                }
                view = this.f17049k;
                break;
        }
        int i2 = i == 0 ? this.f17054p : this.f17055q;
        int i3 = i == 1 ? this.f17054p : this.f17055q;
        hours = i == 2 ? this.f17054p : this.f17055q;
        this.f17045g.setTextColor(i2);
        this.f17047i.setTextColor(i3);
        this.f17049k.setTextColor(hours);
        ObjectAnimator a = C4783c.m18767a(view, 0.85f, 1.1f);
        if (z2) {
            a.setStartDelay(300);
        }
        a.start();
    }

    /* renamed from: f */
    private boolean m18953f(int i) {
        if (i == 111 || i == 4) {
            if (isCancelable()) {
                dismiss();
            }
            return true;
        }
        if (i == 61) {
            if (this.f17028N) {
                if (m18959j()) {
                    m18942a(true);
                }
                return true;
            }
        } else if (i == 66) {
            if (this.f17028N) {
                if (!m18959j()) {
                    return true;
                }
                m18942a(false);
            }
            if (this.f17039a != null) {
                this.f17039a.m18936a(this.f17053o, this.f17053o.getHours(), this.f17053o.getMinutes(), this.f17053o.getSeconds());
            }
            dismiss();
            return true;
        } else if (i == 67) {
            if (this.f17028N && !this.f17029O.isEmpty()) {
                String str;
                int k = m18960k();
                if (k == m18958j(0)) {
                    str = this.f17056r;
                } else if (k == m18958j(1)) {
                    str = this.f17057s;
                } else {
                    str = String.format("%d", new Object[]{Integer.valueOf(C4830f.m18956i(k))});
                }
                C4783c.m18768a(this.f17053o, String.format(this.f17027M, new Object[]{str}));
                m18946b(true);
            }
        } else if (i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || (!this.f17060v && (i == m18958j(0) || i == m18958j(1)))) {
            if (this.f17028N) {
                if (m18955h(i)) {
                    m18946b(false);
                }
                return true;
            } else if (this.f17053o == null) {
                Log.e("TimePickerDialog", "Unable to initiate keyboard mode, TimePicker was null.");
                return true;
            } else {
                this.f17029O.clear();
                m18954g(i);
                return true;
            }
        }
        return false;
    }

    /* renamed from: g */
    private void m18954g(int i) {
        if (!this.f17053o.m18903a(false)) {
            return;
        }
        if (i == -1 || m18955h(i)) {
            this.f17028N = true;
            this.f17044f.setEnabled(false);
            m18946b(false);
        }
    }

    /* renamed from: h */
    private boolean m18955h(int i) {
        int i2;
        if (this.f17060v) {
            int size = this.f17029O.size();
            if (this.f17020F) {
                i2 = 6;
            } else {
                i2 = 4;
            }
            if (size == i2) {
                return false;
            }
        }
        if (!this.f17060v && m18959j()) {
            return false;
        }
        this.f17029O.add(Integer.valueOf(i));
        if (m18957i()) {
            i2 = C4830f.m18956i(i);
            C4783c.m18768a(this.f17053o, String.format("%d", new Object[]{Integer.valueOf(i2)}));
            if (m18959j()) {
                if (!this.f17060v) {
                    if (this.f17029O.size() <= (this.f17020F ? 5 : 3)) {
                        this.f17029O.add(this.f17029O.size() - 1, Integer.valueOf(7));
                        this.f17029O.add(this.f17029O.size() - 1, Integer.valueOf(7));
                    }
                }
                this.f17044f.setEnabled(true);
            }
            return true;
        }
        m18960k();
        return false;
    }

    /* renamed from: i */
    private boolean m18957i() {
        C4828b c4828b = this.f17030P;
        Iterator it = this.f17029O.iterator();
        C4828b c4828b2 = c4828b;
        while (it.hasNext()) {
            c4828b = c4828b2.m18935b(((Integer) it.next()).intValue());
            if (c4828b == null) {
                return false;
            }
            c4828b2 = c4828b;
        }
        return true;
    }

    /* renamed from: j */
    private boolean m18959j() {
        boolean z = false;
        if (this.f17060v) {
            int[] a = m18945a(null);
            if (a[0] < 0 || a[1] < 0 || a[1] >= 60 || a[2] < 0 || a[2] >= 60) {
                return false;
            }
            return true;
        }
        if (this.f17029O.contains(Integer.valueOf(m18958j(0))) || this.f17029O.contains(Integer.valueOf(m18958j(1)))) {
            z = true;
        }
        return z;
    }

    /* renamed from: k */
    private int m18960k() {
        int intValue = ((Integer) this.f17029O.remove(this.f17029O.size() - 1)).intValue();
        if (!m18959j()) {
            this.f17044f.setEnabled(false);
        }
        return intValue;
    }

    /* renamed from: a */
    private void m18942a(boolean z) {
        this.f17028N = false;
        if (!this.f17029O.isEmpty()) {
            int[] a = m18945a(null);
            this.f17053o.setTime(new Timepoint(a[0], a[1], a[2]));
            if (!this.f17060v) {
                this.f17053o.setAmOrPm(a[3]);
            }
            this.f17029O.clear();
        }
        if (z) {
            m18946b(false);
            this.f17053o.m18903a(true);
        }
    }

    /* renamed from: b */
    private void m18946b(boolean z) {
        int i = 0;
        if (z || !this.f17029O.isEmpty()) {
            Boolean[] boolArr = new Boolean[]{Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)};
            int[] a = m18945a(boolArr);
            String str = boolArr[0].booleanValue() ? "%02d" : "%2d";
            String str2 = boolArr[1].booleanValue() ? "%02d" : "%2d";
            String str3 = boolArr[1].booleanValue() ? "%02d" : "%2d";
            CharSequence replace = a[0] == -1 ? this.f17026L : String.format(str, new Object[]{Integer.valueOf(a[0])}).replace(' ', this.f17025K);
            CharSequence replace2 = a[1] == -1 ? this.f17026L : String.format(str2, new Object[]{Integer.valueOf(a[1])}).replace(' ', this.f17025K);
            CharSequence replace3 = a[2] == -1 ? this.f17026L : String.format(str3, new Object[]{Integer.valueOf(a[1])}).replace(' ', this.f17025K);
            this.f17045g.setText(replace);
            this.f17046h.setText(replace);
            this.f17045g.setTextColor(this.f17055q);
            this.f17047i.setText(replace2);
            this.f17048j.setText(replace2);
            this.f17047i.setTextColor(this.f17055q);
            this.f17049k.setText(replace3);
            this.f17050l.setText(replace3);
            this.f17049k.setTextColor(this.f17055q);
            if (!this.f17060v) {
                m18949c(a[3]);
                return;
            }
            return;
        }
        int hours = this.f17053o.getHours();
        int minutes = this.f17053o.getMinutes();
        int seconds = this.f17053o.getSeconds();
        m18938a(hours, true);
        m18950d(minutes);
        m18952e(seconds);
        if (!this.f17060v) {
            if (hours >= 12) {
                i = 1;
            }
            m18949c(i);
        }
        m18939a(this.f17053o.getCurrentItemShowing(), true, true, true);
        this.f17044f.setEnabled(true);
    }

    /* renamed from: i */
    private static int m18956i(int i) {
        switch (i) {
            case 7:
                return 0;
            case 8:
                return 1;
            case 9:
                return 2;
            case 10:
                return 3;
            case 11:
                return 4;
            case 12:
                return 5;
            case 13:
                return 6;
            case 14:
                return 7;
            case 15:
                return 8;
            case 16:
                return 9;
            default:
                return -1;
        }
    }

    /* renamed from: a */
    private int[] m18945a(Boolean[] boolArr) {
        int i;
        int i2;
        int intValue;
        int i3;
        if (this.f17060v || !m18959j()) {
            i = 1;
            i2 = -1;
        } else {
            intValue = ((Integer) this.f17029O.get(this.f17029O.size() - 1)).intValue();
            if (intValue == m18958j(0)) {
                intValue = 0;
            } else if (intValue == m18958j(1)) {
                boolean z = true;
            } else {
                intValue = -1;
            }
            i = 2;
            i2 = intValue;
        }
        if (this.f17020F) {
            i3 = 2;
        } else {
            i3 = 0;
        }
        int i4 = 0;
        int i5 = -1;
        int i6 = -1;
        for (int i7 = i; i7 <= this.f17029O.size(); i7++) {
            intValue = C4830f.m18956i(((Integer) this.f17029O.get(this.f17029O.size() - i7)).intValue());
            if (this.f17020F) {
                if (i7 == i) {
                    i4 = intValue;
                } else if (i7 == i + 1) {
                    i4 += intValue * 10;
                    if (boolArr != null && intValue == 0) {
                        boolArr[2] = Boolean.valueOf(true);
                    }
                }
            }
            if (i7 == i + i3) {
                i6 = intValue;
            } else if (i7 == (i + i3) + 1) {
                i6 += intValue * 10;
                if (boolArr != null && intValue == 0) {
                    boolArr[1] = Boolean.valueOf(true);
                }
            } else if (i7 == (i + i3) + 2) {
                i5 = intValue;
            } else if (i7 == (i + i3) + 3) {
                i5 += intValue * 10;
                if (boolArr != null && intValue == 0) {
                    boolArr[0] = Boolean.valueOf(true);
                }
            }
        }
        return new int[]{i5, i6, i4, i2};
    }

    /* renamed from: j */
    private int m18958j(int i) {
        if (this.f17031Q == -1 || this.f17032R == -1) {
            KeyCharacterMap load = KeyCharacterMap.load(-1);
            int i2 = 0;
            while (i2 < Math.max(this.f17056r.length(), this.f17057s.length())) {
                if (this.f17056r.toLowerCase(Locale.getDefault()).charAt(i2) != this.f17057s.toLowerCase(Locale.getDefault()).charAt(i2)) {
                    KeyEvent[] events = load.getEvents(new char[]{this.f17056r.toLowerCase(Locale.getDefault()).charAt(i2), this.f17057s.toLowerCase(Locale.getDefault()).charAt(i2)});
                    if (events == null || events.length != 4) {
                        Log.e("TimePickerDialog", "Unable to find keycodes for AM and PM.");
                    } else {
                        this.f17031Q = events[0].getKeyCode();
                        this.f17032R = events[2].getKeyCode();
                    }
                } else {
                    i2++;
                }
            }
        }
        if (i == 0) {
            return this.f17031Q;
        }
        return i == 1 ? this.f17032R : -1;
    }

    /* renamed from: l */
    private void m18961l() {
        this.f17030P = new C4828b(new int[0]);
        C4828b c4828b;
        C4828b c4828b2;
        if (this.f17060v) {
            C4828b c4828b3 = new C4828b(7, 8, 9, 10, 11, 12);
            c4828b = new C4828b(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
            c4828b3.m18933a(c4828b);
            if (this.f17020F) {
                c4828b2 = new C4828b(7, 8, 9, 10, 11, 12);
                c4828b2.m18933a(new C4828b(7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
                c4828b.m18933a(c4828b2);
            }
            c4828b2 = new C4828b(7, 8);
            this.f17030P.m18933a(c4828b2);
            C4828b c4828b4 = new C4828b(7, 8, 9, 10, 11, 12);
            c4828b2.m18933a(c4828b4);
            c4828b4.m18933a(c4828b3);
            c4828b4.m18933a(new C4828b(13, 14, 15, 16));
            c4828b4 = new C4828b(13, 14, 15, 16);
            c4828b2.m18933a(c4828b4);
            c4828b4.m18933a(c4828b3);
            c4828b2 = new C4828b(9);
            this.f17030P.m18933a(c4828b2);
            c4828b4 = new C4828b(7, 8, 9, 10);
            c4828b2.m18933a(c4828b4);
            c4828b4.m18933a(c4828b3);
            C4828b c4828b5 = new C4828b(11, 12);
            c4828b2.m18933a(c4828b5);
            c4828b5.m18933a(c4828b);
            c4828b5 = new C4828b(10, 11, 12, 13, 14, 15, 16);
            this.f17030P.m18933a(c4828b5);
            c4828b5.m18933a(c4828b3);
            return;
        }
        c4828b3 = new C4828b(m18958j(0), m18958j(1));
        c4828b = new C4828b(7, 8, 9, 10, 11, 12);
        c4828b2 = new C4828b(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        c4828b2.m18933a(c4828b3);
        c4828b.m18933a(c4828b2);
        c4828b2 = new C4828b(8);
        this.f17030P.m18933a(c4828b2);
        c4828b2.m18933a(c4828b3);
        c4828b4 = new C4828b(7, 8, 9);
        c4828b2.m18933a(c4828b4);
        c4828b4.m18933a(c4828b3);
        C4828b c4828b6 = new C4828b(7, 8, 9, 10, 11, 12);
        c4828b4.m18933a(c4828b6);
        c4828b6.m18933a(c4828b3);
        C4828b c4828b7 = new C4828b(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        c4828b6.m18933a(c4828b7);
        c4828b7.m18933a(c4828b3);
        if (this.f17020F) {
            c4828b7.m18933a(c4828b);
        }
        c4828b6 = new C4828b(13, 14, 15, 16);
        c4828b4.m18933a(c4828b6);
        c4828b6.m18933a(c4828b3);
        if (this.f17020F) {
            c4828b6.m18933a(c4828b);
        }
        c4828b4 = new C4828b(10, 11, 12);
        c4828b2.m18933a(c4828b4);
        c4828b2 = new C4828b(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        c4828b4.m18933a(c4828b2);
        c4828b2.m18933a(c4828b3);
        if (this.f17020F) {
            c4828b2.m18933a(c4828b);
        }
        c4828b2 = new C4828b(9, 10, 11, 12, 13, 14, 15, 16);
        this.f17030P.m18933a(c4828b2);
        c4828b2.m18933a(c4828b3);
        c4828b4 = new C4828b(7, 8, 9, 10, 11, 12);
        c4828b2.m18933a(c4828b4);
        c4828b2 = new C4828b(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        c4828b4.m18933a(c4828b2);
        c4828b2.m18933a(c4828b3);
        if (this.f17020F) {
            c4828b2.m18933a(c4828b);
        }
    }

    /* renamed from: h */
    public void m18980h() {
        if (this.f17039a != null) {
            this.f17039a.m18936a(this.f17053o, this.f17053o.getHours(), this.f17053o.getMinutes(), this.f17053o.getSeconds());
        }
    }
}
