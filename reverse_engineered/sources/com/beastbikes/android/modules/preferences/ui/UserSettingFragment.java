package com.beastbikes.android.modules.preferences.ui;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.alipay.sdk.sys.C0869a;
import com.android.volley.VolleyError;
import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.Wheelview;
import com.beastbikes.android.dialog.Wheelview.C1707d;
import com.beastbikes.android.dialog.Wheelview.C1708e;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.googlemaputils.C1821c;
import com.beastbikes.android.locale.googlemaputils.C1855a;
import com.beastbikes.android.locale.googlemaputils.C1856b;
import com.beastbikes.android.locale.p104a.C1823a;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.android.utils.aa;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.C4789b;
import com.wdullaer.materialdatetimepicker.date.C4789b.C4788b;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "个人设置页")
@C1459b(a = 2130903726)
public class UserSettingFragment extends SessionFragment implements OnClickListener, C1823a, C1821c, C4788b {
    /* renamed from: b */
    private static final Logger f5931b = LoggerFactory.getLogger(UserSettingActivity.class);
    /* renamed from: u */
    private static String f5932u = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "_Beast" + System.currentTimeMillis() + ".jpg");
    /* renamed from: A */
    private double f5933A = 65.0d;
    /* renamed from: B */
    private boolean f5934B = true;
    /* renamed from: C */
    private int f5935C = 1990;
    /* renamed from: D */
    private int f5936D = 1;
    /* renamed from: E */
    private int f5937E = 1;
    @C1458a(a = 2131757747)
    /* renamed from: a */
    TextView f5938a;
    @C1458a(a = 2131757733)
    /* renamed from: c */
    private ViewGroup f5939c;
    @C1458a(a = 2131757734)
    /* renamed from: d */
    private CircleImageView f5940d;
    @C1458a(a = 2131757735)
    /* renamed from: e */
    private ViewGroup f5941e;
    @C1458a(a = 2131757736)
    /* renamed from: f */
    private TextView f5942f;
    @C1458a(a = 2131757737)
    /* renamed from: g */
    private ViewGroup f5943g;
    @C1458a(a = 2131757738)
    /* renamed from: h */
    private TextView f5944h;
    @C1458a(a = 2131757739)
    /* renamed from: i */
    private ViewGroup f5945i;
    @C1458a(a = 2131757741)
    /* renamed from: j */
    private TextView f5946j;
    @C1458a(a = 2131757742)
    /* renamed from: k */
    private ViewGroup f5947k;
    @C1458a(a = 2131757743)
    /* renamed from: l */
    private TextView f5948l;
    @C1458a(a = 2131757744)
    /* renamed from: m */
    private ViewGroup f5949m;
    @C1458a(a = 2131757745)
    /* renamed from: n */
    private TextView f5950n;
    @C1458a(a = 2131757749)
    /* renamed from: o */
    private ViewGroup f5951o;
    @C1458a(a = 2131757751)
    /* renamed from: p */
    private TextView f5952p;
    @C1458a(a = 2131757746)
    /* renamed from: q */
    private ViewGroup f5953q;
    @C1458a(a = 2131757753)
    /* renamed from: r */
    private TextView f5954r;
    /* renamed from: s */
    private C2389c f5955s;
    /* renamed from: t */
    private File f5956t = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), m7186h());
    /* renamed from: v */
    private String f5957v = "";
    /* renamed from: w */
    private String f5958w;
    /* renamed from: x */
    private String f5959x;
    /* renamed from: y */
    private String f5960y;
    /* renamed from: z */
    private double f5961z = 170.0d;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        C1848b.a().a(getActivity(), this);
        if (!C1849a.b(getActivity())) {
            this.f5934B = false;
            this.f5961z = 180.34d;
            this.f5933A = 77.1107029d;
        }
        Intent intent = getActivity().getIntent();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            getActivity().finish();
            return;
        }
        if (!(intent.hasExtra("from_setting") && intent.getBooleanExtra("from_setting", false))) {
            if (intent.hasExtra("from_auth") && 2 == intent.getIntExtra("from_auth", 0)) {
                this.f5952p.setText(getString(C1373R.string.user_setting_activity_value_email));
                getView().findViewById(C1373R.id.user_setting_fragment_line1).setVisibility(8);
                getView().findViewById(C1373R.id.user_setting_fragment_line2).setVisibility(8);
                LayoutParams layoutParams = (LayoutParams) this.f5954r.getLayoutParams();
                layoutParams.leftMargin = DensityUtil.dip2px(15.0f, getActivity());
                layoutParams.rightMargin = DensityUtil.dip2px(15.0f, getActivity());
                layoutParams.topMargin = DensityUtil.dip2px(15.0f, getActivity());
                layoutParams.bottomMargin = DensityUtil.dip2px(15.0f, getActivity());
                this.f5954r.setBackgroundResource(C1373R.drawable.border_1px_solid_b9b9b9_width_radius_5dp);
                this.f5954r.setTextColor(getResources().getColor(C1373R.color.text_number_color));
            } else {
                this.f5954r.setVisibility(8);
            }
        }
        this.f5955s = new C2389c(getActivity());
        this.f5939c.setOnClickListener(this);
        this.f5943g.setOnClickListener(this);
        this.f5947k.setOnClickListener(this);
        this.f5945i.setOnClickListener(this);
        this.f5941e.setOnClickListener(this);
        this.f5949m.setOnClickListener(this);
        this.f5954r.setOnClickListener(this);
        this.f5953q.setOnClickListener(this);
        this.f5944h.setText(C1373R.string.str_gender_male);
        this.f5952p.setText(currentUser.getEmail());
        m7180e();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intent intent = getActivity().getIntent();
        super.onCreateOptionsMenu(menu, menuInflater);
        if (intent.hasExtra("from_auth") && menu != null) {
            menu.getItem(0).setVisible(false);
        }
    }

    /* renamed from: c */
    private void m7177c() {
        Object charSequence = this.f5942f.getText().toString();
        if (TextUtils.isEmpty(charSequence)) {
            Toasts.show(getActivity(), C1373R.string.user_setting_activity_save_error);
            return;
        }
        new Thread(new UserSettingFragment$1(this, getActivity(), charSequence)).start();
        if (getActivity() != null) {
            getActivity().setResult(-1, getActivity().getIntent());
            getActivity().finish();
        }
    }

    /* renamed from: e */
    private void m7180e() {
        String b = b();
        getAsyncTaskQueue().a(new UserSettingFragment$8(this), new String[]{b});
    }

    /* renamed from: a */
    private void m7168a(ProfileDTO profileDTO) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null || TextUtils.isEmpty(currentUser.getAvatar())) {
            this.f5940d.setImageResource(C1373R.drawable.ic_avatar);
        } else {
            Picasso.with(getActivity()).load(currentUser.getAvatar()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f5940d);
        }
        CharSequence nickname = profileDTO.getNickname();
        if (TextUtils.isEmpty(nickname)) {
            this.f5942f.setText(this.f5942f.getText().toString());
        } else {
            this.f5942f.setText(nickname);
        }
        switch (profileDTO.getGender()) {
            case 0:
                this.f5944h.setText(C1373R.string.str_gender_female);
                break;
            default:
                this.f5944h.setText(C1373R.string.str_gender_male);
                break;
        }
        this.f5946j.setText(profileDTO.getLocation());
        if (TextUtils.isEmpty(profileDTO.getEmail()) || profileDTO.getEmail().endsWith("@beastbikes.default.com")) {
            this.f5951o.setVisibility(8);
            getView().findViewById(C1373R.id.user_setting_fragment_line3).setVisibility(8);
        } else {
            this.f5951o.setVisibility(0);
            this.f5952p.setText(profileDTO.getEmail());
        }
        f5931b.trace("height=" + profileDTO.getHeight() + "weight=" + profileDTO.getWeight());
        if (this.f5934B) {
            this.f5948l.setText(((int) profileDTO.getHeight()) + "cm");
            this.f5950n.setText(((int) profileDTO.getWeight()) + "kg");
        } else {
            this.f5948l.setText(C1849a.g(profileDTO.getHeight()) + "'" + C1849a.f(profileDTO.getHeight()) + "\"");
            this.f5950n.setText(((int) Math.round(C1849a.h(profileDTO.getWeight()))) + "lb");
        }
        this.f5961z = profileDTO.getHeight();
        this.f5933A = profileDTO.getWeight();
        if (!TextUtils.isEmpty(profileDTO.getBirthday())) {
            String[] split = profileDTO.getBirthday().split(HelpFormatter.DEFAULT_OPT_PREFIX);
            this.f5935C = aa.a(split[0]);
            this.f5936D = aa.a(split[1]);
            this.f5937E = aa.a(split[2]);
        }
        this.f5938a.setText(this.f5935C + "." + this.f5936D + "." + this.f5937E + "");
    }

    /* renamed from: f */
    private void m7182f() {
        CharSequence charSequence = this.f5938a.getText().toString();
        String[] split = charSequence.split("\\.");
        if (TextUtils.isEmpty(charSequence)) {
            this.f5935C = 1990;
            this.f5936D = 1;
            this.f5937E = 1;
        } else {
            this.f5935C = Integer.valueOf(aa.a(split[0])).intValue();
            this.f5936D = Integer.valueOf(aa.a(split[1])).intValue() - 1;
            this.f5937E = Integer.valueOf(aa.a(split[2])).intValue();
        }
        if (this.f5936D <= 0) {
            this.f5936D = 1;
        }
        C4789b a = C4789b.a(this, this.f5935C, this.f5936D, this.f5937E);
        a.b(Calendar.getInstance());
        a.a(aa.a(1900, 1, 1));
        a.b(getResources().getColor(C1373R.color.bg_theme_black_color));
        a.show(getActivity().getFragmentManager(), "DatePickerOfBirth");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.user_setting_activity_avatar:
                m7184g();
                return;
            case C1373R.id.user_setting_activity_nickname:
                startActivityForResult(new Intent(getActivity(), EditTextActivity.class).putExtra("value", this.f5942f.getText().toString().trim()).putExtra(C0869a.f2169j, true), 1);
                return;
            case C1373R.id.user_setting_activity_gender:
                if (this.f5944h.getText().toString().equals(getString(C1373R.string.str_gender_male))) {
                    this.f5944h.setText(C1373R.string.str_gender_female);
                    return;
                } else {
                    this.f5944h.setText(C1373R.string.str_gender_male);
                    return;
                }
            case C1373R.id.user_setting_activity_location:
                this.f5946j.setText(C1373R.string.str_locating);
                C1848b.a().a(getActivity(), this);
                return;
            case C1373R.id.user_setting_activity_height:
                if (this.f5934B) {
                    m7170a("cm", (int) AVException.EXCEEDED_QUOTA, 200, (int) this.f5961z, new UserSettingFragment$9(this));
                    return;
                } else {
                    m7171a("'", "\"", C1849a.g(this.f5961z), C1849a.f(this.f5961z), new UserSettingFragment$10(this));
                    return;
                }
            case C1373R.id.user_setting_activity_weight:
                if (this.f5934B) {
                    m7170a("kg", 40, 150, Math.round((float) this.f5933A), new UserSettingFragment$11(this));
                    return;
                } else {
                    m7170a("lb", 88, 330, Math.round((float) C1849a.h(this.f5933A)), new UserSettingFragment$12(this));
                    return;
                }
            case C1373R.id.user_setting_activity_birth:
                m7182f();
                return;
            case C1373R.id.user_setting_activity_button_sign_out:
                m7177c();
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Cursor query;
        String string;
        Intent intent2;
        Throwable e;
        Cursor cursor;
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 1:
                if (-1 == i2) {
                    this.f5942f.setText(intent.getExtras().getString("value"));
                    return;
                }
                return;
            case 2:
                if (i2 == -1 && intent != null) {
                    Uri data = intent.getData();
                    String[] strArr = new String[]{"_data"};
                    try {
                        query = getActivity().getContentResolver().query(data, strArr, null, null, null);
                        if (query != null) {
                            try {
                                if (query.moveToFirst()) {
                                    string = query.getString(query.getColumnIndex(strArr[0]));
                                    if (!(query == null || query.isClosed())) {
                                        query.close();
                                    }
                                    if (TextUtils.isEmpty(string)) {
                                        string = data.getPath();
                                    }
                                    intent2 = new Intent(getActivity(), CutAvatarActivity.class);
                                    intent2.putExtra("path", string);
                                    startActivityForResult(intent2, 4);
                                    return;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                cursor = query;
                                try {
                                    AVAnalytics.onError(getActivity(), Log.getStackTraceString(e));
                                    f5931b.error("Get photo path from gallery failed", e);
                                    cursor.close();
                                    string = TextUtils.isEmpty(null) ? null : data.getPath();
                                    intent2 = new Intent(getActivity(), CutAvatarActivity.class);
                                    intent2.putExtra("path", string);
                                    startActivityForResult(intent2, 4);
                                    return;
                                } catch (Throwable th) {
                                    e = th;
                                    query = cursor;
                                    if (!(query == null || query.isClosed())) {
                                        query.close();
                                    }
                                    if (TextUtils.isEmpty(null)) {
                                        data.getPath();
                                    }
                                    throw e;
                                }
                            } catch (Throwable th2) {
                                e = th2;
                                query.close();
                                if (TextUtils.isEmpty(null)) {
                                    data.getPath();
                                }
                                throw e;
                            }
                        }
                        AVAnalytics.onError(getActivity(), "get photo path failed:" + data.toString());
                        string = null;
                        query.close();
                        if (TextUtils.isEmpty(string)) {
                            string = data.getPath();
                        }
                        break;
                    } catch (Exception e3) {
                        e = e3;
                        cursor = null;
                        AVAnalytics.onError(getActivity(), Log.getStackTraceString(e));
                        f5931b.error("Get photo path from gallery failed", e);
                        if (!(cursor == null || cursor.isClosed())) {
                            cursor.close();
                        }
                        if (TextUtils.isEmpty(null)) {
                        }
                        intent2 = new Intent(getActivity(), CutAvatarActivity.class);
                        intent2.putExtra("path", string);
                        startActivityForResult(intent2, 4);
                        return;
                    } catch (Throwable th3) {
                        e = th3;
                        query = null;
                        query.close();
                        if (TextUtils.isEmpty(null)) {
                            data.getPath();
                        }
                        throw e;
                    }
                    intent2 = new Intent(getActivity(), CutAvatarActivity.class);
                    intent2.putExtra("path", string);
                    startActivityForResult(intent2, 4);
                    return;
                }
                return;
            case 3:
                if (i2 == -1) {
                    Intent intent3 = new Intent(getActivity(), CutAvatarActivity.class);
                    intent3.putExtra("path", this.f5956t.getAbsolutePath());
                    startActivityForResult(intent3, 4);
                    return;
                }
                return;
            case 4:
                if (i2 == 4) {
                    Object stringExtra = intent.getStringExtra("path");
                    this.f5957v = stringExtra;
                    if (TextUtils.isEmpty(stringExtra)) {
                        this.f5940d.setImageResource(C1373R.drawable.ic_avatar);
                        return;
                    } else {
                        Picasso.with(getActivity()).load("file://" + stringExtra).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f5940d);
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* renamed from: g */
    private void m7184g() {
        new Builder(getActivity()).setTitle(getString(C1373R.string.user_setting_activity_setting_avatar_title)).setNegativeButton(getString(C1373R.string.str_album), new UserSettingFragment$14(this)).setPositiveButton(getString(C1373R.string.str_camera), new UserSettingFragment$13(this)).show();
    }

    /* renamed from: h */
    private String m7186h() {
        return new SimpleDateFormat("'BEAST'_yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
    }

    /* renamed from: a */
    private void m7169a(LatLng latLng) {
        AsyncTask userSettingFragment$15 = new UserSettingFragment$15(this);
        getAsyncTaskQueue().a(userSettingFragment$15, new String[]{latLng.getLatitude() + "," + latLng.getLongitude()});
    }

    /* renamed from: a */
    public void m7192a(Location location) {
        if (C1849a.a()) {
            m7169a(new LatLng(location.getLatitude(), location.getLongitude()));
        } else {
            new C1855a().a(getActivity(), getRequestQueueFactory(), location.getLatitude(), location.getLongitude(), this);
        }
    }

    public void e_() {
    }

    /* renamed from: a */
    public void m7194a(C1856b c1856b) {
        String[] split = c1856b.a().split(",");
        switch (split.length) {
            case 1:
                this.f5960y = split[0];
                break;
            case 2:
                this.f5958w = split[0];
                this.f5960y = split[1];
                break;
            case 3:
                this.f5958w = split[0];
                this.f5959x = split[1];
                this.f5960y = split[2];
                break;
        }
        this.f5946j.setText(C2558g.a(this.f5958w, this.f5959x, this.f5960y));
    }

    /* renamed from: a */
    public void m7193a(VolleyError volleyError) {
    }

    /* renamed from: a */
    private void m7171a(String str, String str2, int i, int i2, C1708e c1708e) {
        int i3;
        View inflate = getActivity().getLayoutInflater().inflate(C1373R.layout.popwindowpickheight, null, false);
        PopupWindow popupWindow = new PopupWindow(inflate, -1, -2, true);
        popupWindow.setOnDismissListener(new UserSettingFragment$2(this));
        ArrayList arrayList = new ArrayList();
        for (i3 = 4; i3 < 7; i3++) {
            arrayList.add(i3 + str);
        }
        ArrayList arrayList2 = new ArrayList();
        for (i3 = 0; i3 < 12; i3++) {
            arrayList2.add(i3 + str2);
        }
        Wheelview wheelview = (Wheelview) inflate.findViewById(C1373R.id.one);
        wheelview.setData(arrayList);
        Wheelview wheelview2 = (Wheelview) inflate.findViewById(C1373R.id.two);
        wheelview2.setData(arrayList2);
        if (i - 4 < 0) {
            wheelview.setDefault(0);
        } else if (i - 4 > 2) {
            wheelview.setDefault(2);
        } else {
            wheelview.setDefault(i - 4);
        }
        if (i2 < 0) {
            wheelview2.setDefault(0);
        } else if (i2 > 11) {
            wheelview2.setDefault(11);
        } else {
            wheelview2.setDefault(i2);
        }
        ((TextView) inflate.findViewById(C1373R.id.savebtn)).setOnClickListener(new UserSettingFragment$3(this, c1708e, wheelview, str, wheelview2, str2, popupWindow));
        ((TextView) inflate.findViewById(C1373R.id.cancelbtn)).setOnClickListener(new UserSettingFragment$4(this, popupWindow));
        m7191a(0.5d);
        popupWindow.showAtLocation(getView().findViewById(C1373R.id.main), 80, 0, 0);
    }

    /* renamed from: a */
    private void m7170a(String str, int i, int i2, int i3, C1707d c1707d) {
        View inflate = getActivity().getLayoutInflater().inflate(C1373R.layout.popwindowpickcommon, null, false);
        PopupWindow popupWindow = new PopupWindow(inflate, -1, -2, true);
        popupWindow.setOnDismissListener(new UserSettingFragment$5(this));
        ArrayList arrayList = new ArrayList();
        for (int i4 = i; i4 <= i2; i4++) {
            arrayList.add(i4 + str);
        }
        Wheelview wheelview = (Wheelview) inflate.findViewById(C1373R.id.one);
        wheelview.setData(arrayList);
        if (i3 - i < 0) {
            wheelview.setDefault(0);
        } else if (i3 - i > i2) {
            wheelview.setDefault(i2);
        } else if (i3 - i < wheelview.getListSize()) {
            wheelview.setDefault(i3 - i);
        }
        ((TextView) inflate.findViewById(C1373R.id.savebtn)).setOnClickListener(new UserSettingFragment$6(this, c1707d, wheelview, str, popupWindow));
        ((TextView) inflate.findViewById(C1373R.id.cancelbtn)).setOnClickListener(new UserSettingFragment$7(this, popupWindow));
        m7191a(0.5d);
        popupWindow.showAtLocation(getView().findViewById(C1373R.id.main), 80, 0, 0);
    }

    /* renamed from: a */
    public void m7191a(double d) {
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = (float) d;
        getActivity().getWindow().setAttributes(attributes);
    }

    /* renamed from: a */
    public void m7195a(C4789b c4789b, int i, int i2, int i3) {
        this.f5938a.setText(i + "." + (i2 + 1) + "." + i3 + "");
    }
}
