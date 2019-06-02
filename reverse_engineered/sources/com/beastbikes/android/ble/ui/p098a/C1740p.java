package com.beastbikes.android.ble.ui.p098a;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.C1602a;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p096a.C1615b;
import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.ble.biz.p097b.C1624h;
import com.beastbikes.android.ble.biz.p097b.C1625i;
import com.beastbikes.android.ble.ui.p098a.C1740p;
import com.beastbikes.android.ble.ui.painter.progress.ArcProgress;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: SpeedXDialogFragment */
/* renamed from: com.beastbikes.android.ble.ui.a.p */
public class C1740p extends DialogFragment implements OnClickListener, C1624h, C1625i {
    /* renamed from: a */
    private final Logger f7902a = LoggerFactory.getLogger(C1740p.class);
    /* renamed from: b */
    private TextView f7903b;
    /* renamed from: c */
    private TextView f7904c;
    /* renamed from: d */
    private ArcProgress f7905d;
    /* renamed from: e */
    private TextView f7906e;
    /* renamed from: f */
    private TextView f7907f;
    /* renamed from: g */
    private C1651c f7908g;
    /* renamed from: h */
    private C1602a f7909h;
    /* renamed from: i */
    private List<LocalActivity> f7910i;
    /* renamed from: j */
    private int f7911j = 0;
    /* renamed from: k */
    private int f7912k = 0;
    /* renamed from: l */
    private int f7913l;
    /* renamed from: m */
    private C1614a f7914m;
    /* renamed from: n */
    private Timer f7915n;
    /* renamed from: o */
    private long f7916o;
    /* renamed from: p */
    private int f7917p = -1;
    /* renamed from: q */
    private int f7918q;
    /* renamed from: r */
    private boolean f7919r;

    /* compiled from: SpeedXDialogFragment */
    /* renamed from: com.beastbikes.android.ble.ui.a.p$3 */
    class C17333 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1740p f7891a;

        C17333(C1740p c1740p) {
            this.f7891a = c1740p;
        }

        public void run() {
            this.f7891a.m9284d(0);
            this.f7891a.f7905d.setProgress(0);
            this.f7891a.f7906e.setText("0%");
        }
    }

    /* compiled from: SpeedXDialogFragment */
    /* renamed from: com.beastbikes.android.ble.ui.a.p$6 */
    class C17366 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1740p f7897a;

        C17366(C1740p c1740p) {
            this.f7897a = c1740p;
        }

        public void run() {
            C1623g c = this.f7897a.f7909h.mo3167c();
            if (c != null) {
                c.mo3197b(1);
            }
            this.f7897a.getActivity().getSharedPreferences(this.f7897a.getActivity().getPackageName(), 0).edit().remove("beast.ble.gps").apply();
            this.f7897a.dismissAllowingStateLoss();
        }
    }

    /* compiled from: SpeedXDialogFragment */
    /* renamed from: com.beastbikes.android.ble.ui.a.p$7 */
    class C17377 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1740p f7898a;

        C17377(C1740p c1740p) {
            this.f7898a = c1740p;
        }

        public void run() {
            this.f7898a.dismissAllowingStateLoss();
        }
    }

    /* compiled from: SpeedXDialogFragment */
    /* renamed from: com.beastbikes.android.ble.ui.a.p$8 */
    class C17388 extends TimerTask {
        /* renamed from: a */
        final /* synthetic */ C1740p f7899a;

        C17388(C1740p c1740p) {
            this.f7899a = c1740p;
        }

        public void run() {
            if (this.f7899a.f7914m != null) {
                C1615b i = this.f7899a.f7914m.m8744i();
                if (i != null && i.m8775o()) {
                    return;
                }
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f7899a.f7916o <= 2000) {
                return;
            }
            if (this.f7899a.f7918q > 15) {
                this.f7899a.dismissAllowingStateLoss();
                this.f7899a.f7902a.error("超过2秒未响应，restartCount > 2");
                return;
            }
            if (this.f7899a.f7917p >= 0) {
                if (this.f7899a.f7909h != null) {
                    this.f7899a.f7909h.mo3136a((byte) 1, (byte) 2, this.f7899a.f7917p);
                }
                this.f7899a.f7902a.error("超过2秒未响应，currentIndex >= 0");
            }
            this.f7899a.f7918q = this.f7899a.f7918q + 1;
            this.f7899a.f7916o = currentTimeMillis;
        }
    }

    /* renamed from: a */
    public void m9298a(C1602a c1602a) {
        this.f7909h = c1602a;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f7908g = new C1651c(getActivity());
        Bundle arguments = getArguments();
        this.f7913l = arguments.getInt("sync_type", 0);
        this.f7914m = C1661h.m8999a().m9005b(arguments.getString("central_id"));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().requestWindowFeature(1);
        setCancelable(false);
        View inflate = LayoutInflater.from(getActivity()).inflate(C1373R.layout.sync_data_dialog_view, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels - C2801d.m13756a(getActivity(), 80.0f), -2);
        int a = C2801d.m13756a(getActivity(), 15.0f);
        layoutParams.setMargins(0, a, 0, a);
        this.f7903b = (TextView) inflate.findViewById(C1373R.id.sync_data_dialog_title);
        this.f7903b.setLayoutParams(layoutParams);
        this.f7904c = (TextView) inflate.findViewById(C1373R.id.sync_data_cancel_sync);
        this.f7904c.setOnClickListener(this);
        this.f7905d = (ArcProgress) inflate.findViewById(C1373R.id.sync_data_progress_view);
        this.f7906e = (TextView) inflate.findViewById(C1373R.id.sync_data_progress_value);
        this.f7906e.setText("0%");
        this.f7907f = (TextView) inflate.findViewById(C1373R.id.sync_data_progress_message);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.f7914m != null) {
            this.f7914m.m8744i().m8750a(false);
            this.f7914m.m8744i().m8767h(false);
        }
        switch (this.f7913l) {
            case 0:
                this.f7903b.setText(C1373R.string.label_syncing_activity);
                if (this.f7909h != null) {
                    this.f7909h.mo3146a((C1624h) this);
                }
                this.f7904c.setText(C1373R.string.sync_data_cancel_sync);
                this.f7907f.setText(getString(C1373R.string.sync_data_message) + "(" + String.valueOf(this.f7912k + 1) + "/" + String.valueOf(this.f7911j) + ")");
                m9283d();
                return;
            case 1:
                this.f7903b.setText(C1373R.string.label_speedx_agps_update_msg_1);
                this.f7907f.setText(C1373R.string.label_agps_update_desc);
                this.f7904c.setText(C1373R.string.label_cancel_update);
                if (this.f7909h != null) {
                    this.f7909h.mo3147a((C1625i) this);
                    return;
                }
                return;
            case 3:
                this.f7903b.setText(C1373R.string.str_navigation_route_sync_ing);
                this.f7907f.setText("");
                this.f7904c.setText(C1373R.string.cancel);
                if (this.f7909h != null) {
                    this.f7909h.mo3147a((C1625i) this);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.sync_data_cancel_sync:
                C1623g c;
                switch (this.f7913l) {
                    case 0:
                        if (this.f7914m != null) {
                            this.f7914m.m8744i().m8750a(true);
                            this.f7914m.m8744i().m8767h(true);
                        }
                        c = this.f7909h.mo3167c();
                        if (c != null) {
                            c.mo3195a(0);
                        }
                        if (this.f7910i == null || this.f7910i.size() <= 0 || this.f7912k >= this.f7910i.size()) {
                            dismissAllowingStateLoss();
                            return;
                        }
                        LocalActivity localActivity = (LocalActivity) this.f7910i.get(this.f7912k);
                        if (localActivity == null) {
                            dismissAllowingStateLoss();
                            return;
                        } else {
                            m9277a(localActivity.getId());
                            return;
                        }
                    case 1:
                        C2580w.m12905a(getActivity(), "", "click_my_page_my_device_stop_transfer_agps");
                        m9286e();
                        return;
                    case 3:
                        C2580w.m12905a(getActivity(), "", "click_my_page_my_device_stop_transfer_route");
                        if (this.f7914m != null) {
                            this.f7914m.m8744i().m8750a(true);
                        }
                        c = this.f7909h.mo3167c();
                        if (c != null) {
                            c.mo3195a(3);
                        }
                        dismissAllowingStateLoss();
                        Toasts.show(getActivity(), getString(C1373R.string.str_ble_sync_has_stoped));
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    /* renamed from: a */
    public void mo3222a() {
    }

    /* renamed from: a */
    public void mo3224a(final int i, final int i2) {
        if (getActivity() != null && isAdded()) {
            this.f7917p = i;
            this.f7918q = 0;
            this.f7916o = System.currentTimeMillis();
            getActivity().runOnUiThread(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C1740p f7888c;

                public void run() {
                    this.f7888c.f7905d.setProgress(Integer.valueOf(String.format("%.0f", new Object[]{Double.valueOf(((((double) i) + 1.0d) / ((double) i2)) * 100.0d)})).intValue());
                    this.f7888c.f7906e.setText(String.format("%d%%", new Object[]{Integer.valueOf(r0)}));
                    this.f7888c.f7907f.setText(this.f7888c.getString(C1373R.string.sync_data_message) + "(" + String.valueOf(this.f7888c.f7912k + 1) + "/" + String.valueOf(this.f7888c.f7911j) + ")");
                }
            });
        }
    }

    /* renamed from: b */
    public void mo3225b() {
        this.f7912k++;
        this.f7919r = false;
        this.f7916o = System.currentTimeMillis();
        this.f7918q = 0;
        this.f7917p = -1;
        if (getActivity() != null) {
            getActivity().runOnUiThread(new C17333(this));
        }
    }

    /* renamed from: a */
    public void mo3223a(final int i) {
        if (i == 16 || i == 21) {
            this.f7902a.error("Sync errorCode = " + i + ", (错误描述: errorCode 16 = MCU_ERR_CODE_SYNC_RIDE_BUSY, errorCode 21 = MCU_ERR_CODE_SYNC_TIMEOUT");
            return;
        }
        this.f7918q = 0;
        this.f7917p = -1;
        this.f7916o = System.currentTimeMillis();
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C1740p f7893b;

                public void run() {
                    if (i != 17 || this.f7893b.f7919r) {
                        this.f7893b.f7919r = false;
                        this.f7893b.f7912k = this.f7893b.f7912k + 1;
                        this.f7893b.m9284d(0);
                    } else {
                        this.f7893b.f7919r = true;
                        this.f7893b.m9284d(i);
                    }
                    this.f7893b.f7905d.setProgress(0);
                    this.f7893b.f7906e.setText("0%");
                }
            });
        }
    }

    /* renamed from: b */
    public void mo3226b(int i) {
        if (getActivity() != null && this.f7914m != null) {
            double d = (double) ((i + 1) * 190);
            final double d2 = this.f7914m.m8744i().m8757d();
            if (d > d2) {
                d = d2;
            }
            getActivity().runOnUiThread(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C1740p f7896c;

                public void run() {
                    double d = 0.0d;
                    if (d2 > 0.0d) {
                        d = (d / d2) * 100.0d;
                    }
                    this.f7896c.f7905d.setProgress((int) d);
                    this.f7896c.f7906e.setText(String.format("%.0f%%", new Object[]{Double.valueOf(d)}));
                }
            });
        }
    }

    @TargetApi(17)
    /* renamed from: c */
    public void mo3228c(int i) {
        if (getActivity() != null && !getActivity().isFinishing() && !getActivity().isDestroyed()) {
            if (i == 4) {
                new Handler(Looper.getMainLooper()).post(new C17366(this));
            } else if (i == 16) {
                C1623g c = this.f7909h.mo3167c();
                if (c != null) {
                    c.mo3197b(3);
                }
                dismissAllowingStateLoss();
            }
        }
    }

    /* renamed from: c */
    public void mo3227c() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new C17377(this));
        }
    }

    @TargetApi(18)
    /* renamed from: d */
    private void m9283d() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            dismissAllowingStateLoss();
        } else if (this.f7914m == null) {
            dismissAllowingStateLoss();
        } else {
            this.f7910i = this.f7908g.m8884a(currentUser.getObjectId(), this.f7914m.m8728a());
            if (this.f7910i == null || this.f7910i.isEmpty()) {
                dismissAllowingStateLoss();
                return;
            }
            this.f7911j = this.f7910i.size();
            this.f7912k = 0;
            this.f7907f.setText(getString(C1373R.string.sync_data_message) + "(" + String.valueOf(this.f7912k + 1) + "/" + String.valueOf(this.f7911j) + ")");
            m9284d(0);
            this.f7916o = System.currentTimeMillis();
            this.f7915n = new Timer();
            this.f7915n.schedule(new C17388(this), 1000, 1000);
        }
    }

    /* renamed from: d */
    private void m9284d(int i) {
        if (this.f7909h != null) {
            if (this.f7911j <= 0 || this.f7912k >= this.f7911j) {
                C1623g c = this.f7909h.mo3167c();
                if (c != null) {
                    c.mo3197b(0);
                }
                dismissAllowingStateLoss();
                return;
            }
            LocalActivity localActivity = (LocalActivity) this.f7910i.get(this.f7912k);
            if (localActivity == null) {
                dismissAllowingStateLoss();
                return;
            }
            try {
                new C1398a(getActivity()).e(localActivity.getId());
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            this.f7902a.info("同步数据的记录: " + localActivity.getId());
            this.f7909h.mo3161a(localActivity.getFinishTime(), i, localActivity.getId());
        }
    }

    /* renamed from: e */
    private void m9286e() {
        final C2621c c2621c = new C2621c(getActivity());
        c2621c.m13065b((int) C1373R.string.msg_cancel_update_agps);
        c2621c.m13059a((int) C1373R.string.activity_alert_dialog_text_ok, new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1740p f7901b;

            public void onClick(View view) {
                c2621c.m13069b();
                if (this.f7901b.f7914m != null) {
                    this.f7901b.f7914m.m8744i().m8750a(true);
                }
                C1623g c = this.f7901b.f7909h.mo3167c();
                if (c != null) {
                    c.mo3195a(1);
                }
                this.f7901b.getActivity().getSharedPreferences(this.f7901b.getActivity().getPackageName(), 0).edit().remove("beast.ble.gps").apply();
                this.f7901b.dismissAllowingStateLoss();
            }
        });
        c2621c.m13066b((int) C1373R.string.cancel, new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1740p f7885b;

            public void onClick(View view) {
                c2621c.m13069b();
            }
        }).m13063a();
    }

    /* renamed from: a */
    private void m9277a(String str) {
        if (!TextUtils.isEmpty(str)) {
            final C1802i c1802i = new C1802i(getActivity(), "", true);
            c1802i.show();
            try {
                new C1398a(getActivity()).e(str);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C1740p f7890b;

                public void run() {
                    c1802i.dismiss();
                    this.f7890b.dismissAllowingStateLoss();
                }
            }, 5000);
        }
    }

    public void dismissAllowingStateLoss() {
        if (this.f7915n != null) {
            this.f7915n.cancel();
        }
        super.dismissAllowingStateLoss();
    }
}
