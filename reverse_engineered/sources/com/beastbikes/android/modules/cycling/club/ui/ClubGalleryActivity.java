package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.dialog.Wheelview.C1783c;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.biz.C2052c.C2051b;
import com.beastbikes.android.modules.cycling.club.biz.C2057d;
import com.beastbikes.android.modules.cycling.club.biz.C2057d.C2054a;
import com.beastbikes.android.modules.cycling.club.biz.C2057d.C2056c;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2147a;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.SwipeRefreshAndLoadLayout;
import com.beastbikes.android.widget.SwipeRefreshAndLoadLayout.C2602a;
import com.beastbikes.android.widget.smoothprogressbar.C2742b;
import com.beastbikes.android.widget.smoothprogressbar.C2746c;
import com.beastbikes.android.widget.smoothprogressbar.SmoothProgressBar;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.squareup.picasso.Picasso;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@C1459b(a = 2130903253)
public class ClubGalleryActivity extends SessionFragmentActivity implements OnRefreshListener, OnClickListener, OnItemClickListener, C1783c, C2051b, C2054a, C2056c, C2602a {
    @C1458a(a = 2131756296)
    /* renamed from: a */
    private ImageView f5093a;
    @C1458a(a = 2131756297)
    /* renamed from: b */
    private TextView f5094b;
    @C1458a(a = 2131756299)
    /* renamed from: c */
    private TextView f5095c;
    @C1458a(a = 2131756300)
    /* renamed from: d */
    private TextView f5096d;
    @C1458a(a = 2131756306)
    /* renamed from: e */
    private FloatingActionButton f5097e;
    @C1458a(a = 2131756303)
    /* renamed from: f */
    private TextView f5098f;
    @C1458a(a = 2131756305)
    /* renamed from: g */
    private GridView f5099g;
    @C1458a(a = 2131756304)
    /* renamed from: h */
    private SwipeRefreshAndLoadLayout f5100h;
    @C1458a(a = 2131756301)
    /* renamed from: i */
    private RelativeLayout f5101i;
    @C1458a(a = 2131756302)
    /* renamed from: j */
    private TextView f5102j;
    @C1458a(a = 2131756307)
    /* renamed from: k */
    private SmoothProgressBar f5103k;
    /* renamed from: l */
    private ClubGalleryActivity$a f5104l;
    /* renamed from: m */
    private List<ClubPhotoDTO> f5105m = new LinkedList();
    /* renamed from: n */
    private String f5106n;
    /* renamed from: o */
    private C2147a f5107o;
    /* renamed from: p */
    private C2052c f5108p;
    /* renamed from: q */
    private String f5109q;
    /* renamed from: r */
    private int f5110r;
    /* renamed from: s */
    private int f5111s;
    /* renamed from: t */
    private boolean f5112t = true;
    /* renamed from: u */
    private int f5113u;
    /* renamed from: v */
    private boolean f5114v;
    /* renamed from: w */
    private String f5115w;
    /* renamed from: x */
    private String f5116x = "";
    /* renamed from: y */
    private C1802i f5117y;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubGalleryActivity$c */
    private class C1407c extends ViewHolder<ClubPhotoDTO> implements OnClickListener, OnCheckedChangeListener {
        /* renamed from: a */
        final /* synthetic */ ClubGalleryActivity f5089a;
        @C1458a(a = 2131756313)
        /* renamed from: b */
        private ImageView f5090b;
        @C1458a(a = 2131756314)
        /* renamed from: c */
        private CheckBox f5091c;
        /* renamed from: d */
        private ClubPhotoDTO f5092d;

        public /* synthetic */ void bind(Object obj) {
            m6417a((ClubPhotoDTO) obj);
        }

        public C1407c(ClubGalleryActivity clubGalleryActivity, View view) {
            this.f5089a = clubGalleryActivity;
            super(view);
        }

        /* renamed from: a */
        public void m6417a(ClubPhotoDTO clubPhotoDTO) {
            if (clubPhotoDTO != null) {
                this.f5092d = clubPhotoDTO;
                LayoutParams layoutParams = (LayoutParams) this.f5090b.getLayoutParams();
                layoutParams.width = this.f5089a.f5113u;
                layoutParams.height = this.f5089a.f5113u;
                this.f5090b.setLayoutParams(layoutParams);
                if (!TextUtils.isEmpty(clubPhotoDTO.getImageUrl())) {
                    String str;
                    if (clubPhotoDTO.getImageUrl().startsWith("http://") || clubPhotoDTO.getImageUrl().startsWith("https://")) {
                        str = clubPhotoDTO.getImageUrl() + "?imageView2/2/w/" + this.f5089a.f5113u;
                    } else {
                        str = "file://" + clubPhotoDTO.getImageUrl();
                    }
                    Picasso.with(getContext()).load(str).placeholder((int) C1373R.drawable.bg_222222).error((int) C1373R.drawable.bg_222222).resize(this.f5089a.f5113u, this.f5089a.f5113u).centerCrop().into(this.f5090b);
                }
                this.f5090b.setOnClickListener(this);
                if (this.f5089a.f5114v && (this.f5089a.m5331p().equals(clubPhotoDTO.getUserId()) || this.f5089a.m5331p().equals(this.f5089a.f5109q))) {
                    this.f5091c.setVisibility(0);
                    this.f5091c.setOnCheckedChangeListener(this);
                    this.f5091c.setChecked(clubPhotoDTO.isEdit());
                    return;
                }
                this.f5091c.setVisibility(8);
            }
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.f5092d != null) {
                this.f5092d.setEdit(z);
            }
        }

        public void onClick(View view) {
            if (this.f5089a.f5114v) {
                boolean z;
                CheckBox checkBox = this.f5091c;
                if (this.f5091c.isChecked()) {
                    z = false;
                } else {
                    z = true;
                }
                checkBox.setChecked(z);
            } else if (this.f5092d != null && !this.f5089a.f5105m.isEmpty()) {
                Intent intent = new Intent(this.f5089a, ClubFeedImageDetailsActivity.class);
                intent.putExtra("gallery_photos", (Serializable) this.f5089a.f5105m);
                intent.putExtra("position", this.f5089a.f5105m.indexOf(this.f5092d));
                intent.putExtra("club_manager_id", this.f5089a.f5109q);
                if (this.f5089a.m5331p().equals(this.f5089a.f5109q) || this.f5092d.getUserId().equals(this.f5089a.m5331p())) {
                    intent.putExtra("canDel", true);
                } else {
                    intent.putExtra("canDel", false);
                }
                this.f5089a.startActivityForResult(intent, 88);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.f5113u = displayMetrics.widthPixels / 2;
        this.f5107o = new C2147a(this, this);
        this.f5093a.setOnClickListener(this);
        this.f5096d.setOnClickListener(this);
        this.f5094b.setOnClickListener(this);
        this.f5097e.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.f5106n = intent.getStringExtra("club_id");
            this.f5110r = intent.getIntExtra("photo_max_count", 500);
            this.f5111s = intent.getIntExtra("photo_count", 0);
            int intExtra = intent.getIntExtra("club_status", 0);
            this.f5109q = intent.getStringExtra("club_manager_id");
            if (bundle != null) {
                this.f5110r = bundle.getInt("photo_max_count", 500);
                this.f5111s = bundle.getInt("photo_count", 0);
            }
            if (this.f5109q != null) {
                Log.e("clubManagerId", this.f5109q);
            } else {
                Log.e("clubManagerId", "null");
            }
            if (intExtra == 1 || intExtra == 4) {
                this.f5096d.setVisibility(0);
            } else {
                this.f5096d.setVisibility(8);
            }
            this.f5095c.setText(String.format("(%d/%d)", new Object[]{Integer.valueOf(this.f5111s), Integer.valueOf(this.f5110r)}));
            this.f5108p = new C2052c(this);
            onRefresh();
            this.f5104l = new ClubGalleryActivity$a(this, null);
            this.f5099g.setNumColumns(2);
            this.f5103k.setSmoothProgressDrawableBackgroundDrawable(C2742b.a(getResources().getIntArray(C1373R.array.pocket_background_colors), ((C2746c) this.f5103k.getIndeterminateDrawable()).a()));
            this.f5103k.a();
            this.f5099g.setAdapter(this.f5104l);
            this.f5100h.setChildGridView(this.f5099g);
            this.f5100h.setOnRefreshListener(this);
            this.f5100h.setOnLoadListener(this);
            this.f5099g.setOnItemClickListener(this);
            C2057d.a().a(this);
            C2057d.a().a(this);
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("photo_max_count", this.f5110r);
        bundle.putInt("photo_count", this.f5111s);
        super.onSaveInstanceState(bundle);
    }

    public void finish() {
        setResult(-1);
        C2057d.a().a(null);
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.club_gallery_go_back:
                finish();
                return;
            case C1373R.id.club_gallery_title_left_tv:
                this.f5114v = false;
                m6424b();
                return;
            case C1373R.id.club_gallery_title_right_tv:
                this.f5114v = true;
                if (this.f5094b.getVisibility() == 0) {
                    m6433e();
                }
                m6424b();
                return;
            case C1373R.id.club_gallery_floating_action_button:
                m6431d();
                return;
            default:
                return;
        }
    }

    public void onRefresh() {
        this.f5100h.setCanLoad(true);
        m6421a(this.f5106n, "", "", true);
    }

    /* renamed from: a */
    public void m6454a() {
        m6421a(this.f5106n, this.f5115w, this.f5116x, false);
    }

    /* renamed from: a */
    public void m6457a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.f5115w = C2555d.b(Integer.parseInt(str), Integer.parseInt(str2));
            m6421a(this.f5106n, this.f5115w, this.f5116x, true);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i < this.f5105m.size()) {
            ClubPhotoDTO clubPhotoDTO = (ClubPhotoDTO) this.f5105m.get(i);
            if (clubPhotoDTO != null) {
                Intent intent = new Intent(this, ClubFeedImageDetailsActivity.class);
                intent.putExtra("gallery_photos", (Serializable) this.f5105m);
                intent.putExtra("position", i);
                intent.putExtra("club_manager_id", this.f5109q);
                if (m5331p().equals(this.f5109q) || clubPhotoDTO.getUserId().equals(m5331p())) {
                    intent.putExtra("canDel", true);
                } else {
                    intent.putExtra("canDel", false);
                }
                startActivityForResult(intent, 88);
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i2) {
            case -1:
                if (i == 88) {
                    List list = (List) intent.getSerializableExtra("gallery_photos");
                    if (list != null && !list.isEmpty()) {
                        this.f5105m = list;
                        this.f5104l.notifyDataSetChanged();
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m6424b() {
        if (this.f5114v) {
            this.f5093a.setVisibility(8);
            this.f5094b.setVisibility(0);
            this.f5096d.setText(C1373R.string.delete);
        } else {
            this.f5093a.setVisibility(0);
            this.f5094b.setVisibility(8);
            this.f5096d.setText(C1373R.string.str_edit);
            for (ClubPhotoDTO edit : this.f5105m) {
                edit.setEdit(false);
            }
        }
        this.f5104l.notifyDataSetChanged();
    }

    /* renamed from: c */
    private void m6429c() {
        if (this.f5107o == null) {
            this.f5107o = new C2147a(this, this);
        }
        this.f5107o.showAtLocation(findViewById(C1373R.id.club_gallery_view), 80, 0, 0);
    }

    /* renamed from: a */
    private void m6421a(String str, String str2, String str3, boolean z) {
        m6425b(str);
        getAsyncTaskQueue().a(new ClubGalleryActivity$1(this, z, str2, str, str3), new String[0]);
    }

    /* renamed from: b */
    private void m6425b(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new ClubGalleryActivity$2(this), new String[]{str});
        }
    }

    /* renamed from: d */
    private void m6431d() {
        if (this.f5111s >= this.f5110r) {
            C2621c c2621c = new C2621c(this);
            c2621c.b(C1373R.string.club_gallery_image_count_msg);
            c2621c.a(C1373R.string.label_i_know, new ClubGalleryActivity$3(this, c2621c)).a();
            return;
        }
        Intent intent = new Intent(this, ClubFeedPicUploadActivity.class);
        intent.putExtra("club_extra_id", this.f5106n);
        if (this.f5110r > this.f5111s) {
            intent.putExtra("count", this.f5110r - this.f5111s);
        }
        startActivity(intent);
    }

    /* renamed from: e */
    private void m6433e() {
        if (this.f5105m != null && this.f5105m.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            for (ClubPhotoDTO clubPhotoDTO : this.f5105m) {
                int i2;
                if (clubPhotoDTO.isEdit()) {
                    stringBuilder.append(clubPhotoDTO.getPhotoId()).append(",");
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            if (i <= 0) {
                Toasts.show(this, getString(C1373R.string.club_gallery_select_delete_image));
                return;
            }
            CharSequence format = String.format(getString(C1373R.string.club_gallery_delete_image_count), new Object[]{Integer.valueOf(i)});
            C2621c c2621c = new C2621c(this);
            c2621c.b(format);
            c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new ClubGalleryActivity$5(this, c2621c)).b(C1373R.string.cancel, new ClubGalleryActivity$4(this, c2621c)).a();
        }
    }

    /* renamed from: f */
    private void m6435f() {
        if (this.f5105m != null && this.f5105m.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ClubPhotoDTO clubPhotoDTO : this.f5105m) {
                if (clubPhotoDTO.isEdit()) {
                    stringBuilder.append(clubPhotoDTO.getPhotoId()).append(",");
                }
            }
            if (stringBuilder.length() <= 0) {
                Toasts.show(this, getString(C1373R.string.club_gallery_select_delete_image));
                return;
            }
            String stringBuilder2 = stringBuilder.toString();
            getAsyncTaskQueue().a(new ClubGalleryActivity$6(this), new String[]{stringBuilder2});
        }
    }

    /* renamed from: a */
    public void m6455a(int i, int i2, String str) {
        if (this.f5106n.equals(str)) {
            runOnUiThread(new ClubGalleryActivity$7(this, i, i2));
        }
    }

    /* renamed from: a */
    public void m6456a(String str) {
        runOnUiThread(new ClubGalleryActivity$8(this));
        if (this.f5106n.equals(str)) {
            onRefresh();
        }
    }

    /* renamed from: a */
    public void m6458a(List<ClubPhotoDTO> list) {
        runOnUiThread(new ClubGalleryActivity$9(this, list));
    }
}
