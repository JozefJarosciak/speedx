package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2159g;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Iterator;

@C1459b(a = 2130903099)
@C1460c(a = 2131820563)
@C1457a(a = "俱乐部发布动态查看照片详情")
public class ClubFeedImageDetailsActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755528)
    /* renamed from: a */
    CircleImageView f5019a;
    @C1458a(a = 2131755525)
    /* renamed from: b */
    private LinearLayout f5020b;
    @C1458a(a = 2131755529)
    /* renamed from: c */
    private TextView f5021c;
    @C1458a(a = 2131755531)
    /* renamed from: d */
    private ImageButton f5022d;
    @C1458a(a = 2131755533)
    /* renamed from: e */
    private TextView f5023e;
    @C1458a(a = 2131755530)
    /* renamed from: f */
    private TextView f5024f;
    @C1458a(a = 2131755526)
    /* renamed from: g */
    private TextView f5025g;
    /* renamed from: h */
    private ArrayList<String> f5026h = null;
    /* renamed from: i */
    private ArrayList<ClubPhotoDTO> f5027i = null;
    /* renamed from: j */
    private ViewPager f5028j;
    /* renamed from: k */
    private int f5029k = 0;
    /* renamed from: l */
    private ClubFeedImageDetailsActivity$a f5030l;
    /* renamed from: m */
    private C2052c f5031m;
    /* renamed from: n */
    private Menu f5032n;
    /* renamed from: o */
    private String f5033o;
    /* renamed from: p */
    private boolean f5034p;
    /* renamed from: q */
    private boolean f5035q = true;
    /* renamed from: r */
    private boolean f5036r = true;
    /* renamed from: s */
    private C2159g f5037s;
    /* renamed from: t */
    private String f5038t;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f5038t = currentUser.getObjectId();
            this.f5028j = (ViewPager) findViewById(C1373R.id.pager);
            this.f5031m = new C2052c(this);
            this.f5022d.setOnClickListener(this);
            this.f5024f.setOnClickListener(this);
            this.f5020b.setOnClickListener(this);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                this.f5035q = extras.getBoolean("canDel", true);
                this.f5036r = extras.getBoolean("compress", true);
                this.f5026h = extras.getStringArrayList("images");
                if (this.f5026h == null || this.f5026h.size() <= 0) {
                    this.f5026h = new ArrayList();
                    this.f5027i = (ArrayList) extras.getSerializable("gallery_photos");
                    this.f5033o = extras.getString("club_manager_id");
                    this.f5034p = true;
                    Iterator it = this.f5027i.iterator();
                    while (it.hasNext()) {
                        this.f5026h.add(((ClubPhotoDTO) it.next()).getImageUrl());
                    }
                }
                if (this.f5027i == null || this.f5027i.size() <= 0) {
                    this.f5020b.setVisibility(8);
                } else {
                    this.f5020b.setVisibility(0);
                }
                this.f5029k = extras.getInt("position", 0);
                ViewPager viewPager = this.f5028j;
                PagerAdapter clubFeedImageDetailsActivity$a = new ClubFeedImageDetailsActivity$a(this, this, this.f5026h);
                this.f5030l = clubFeedImageDetailsActivity$a;
                viewPager.setAdapter(clubFeedImageDetailsActivity$a);
                this.f5028j.setCurrentItem(this.f5029k);
                m6353b(this.f5029k);
                setTitle((this.f5029k + 1) + "/" + this.f5026h.size());
                this.f5028j.setOnPageChangeListener(new ClubFeedImageDetailsActivity$1(this));
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.f5032n = menu;
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu.findItem(C1373R.id.menu_clubfeed_image_del) == null) {
            return super.onPrepareOptionsMenu(menu);
        }
        if (this.f5035q) {
            m6352b();
        } else {
            m6347a();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_clubfeed_image_del:
                if (!this.f5034p) {
                    this.f5030l.f9622a.remove(this.f5029k);
                    this.f5030l.notifyDataSetChanged();
                    int i = this.f5029k;
                    this.f5029k = i - 1;
                    this.f5029k = i;
                    if (this.f5030l.f9622a.size() == 0) {
                        finish();
                    } else {
                        setTitle((this.f5029k + 1) + "/" + this.f5026h.size());
                    }
                    if (this.f5029k >= 0 && this.f5030l.f9622a.size() > 0) {
                        this.f5028j.setCurrentItem(this.f5029k, true);
                        break;
                    }
                }
                C2621c c2621c = new C2621c(this);
                c2621c.b(C1373R.string.dialog_sure_or_delete);
                c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new ClubFeedImageDetailsActivity$3(this, c2621c)).b(C1373R.string.cancel, new ClubFeedImageDetailsActivity$2(this, c2621c)).a();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        finish();
        return super.onTouchEvent(motionEvent);
    }

    public void finish() {
        Intent intent = new Intent();
        if (this.f5034p) {
            intent.putExtra("gallery_photos", this.f5027i);
        } else {
            intent.putStringArrayListExtra("select_result", this.f5026h);
        }
        setResult(-1, intent);
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.club_feed_image_commend_count:
                m6359d(this.f5029k);
                return;
            case C1373R.id.club_feed_image_praise:
                m6356c(this.f5029k);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m6348a(int i) {
        if (this.f5026h != null && this.f5026h.size() > 0 && this.f5027i != null && !this.f5027i.isEmpty()) {
            getAsyncTaskQueue().a(new ClubFeedImageDetailsActivity$4(this, ((ClubPhotoDTO) this.f5027i.get(i)).getPhotoId()), new String[0]);
        }
    }

    /* renamed from: a */
    private void m6347a() {
        if (this.f5032n != null) {
            for (int i = 0; i < this.f5032n.size(); i++) {
                this.f5032n.getItem(i).setVisible(false);
                this.f5032n.getItem(i).setEnabled(false);
            }
        }
    }

    /* renamed from: b */
    private void m6352b() {
        if (this.f5032n != null) {
            for (int i = 0; i < this.f5032n.size(); i++) {
                this.f5032n.getItem(i).setVisible(true);
                this.f5032n.getItem(i).setEnabled(true);
            }
        }
    }

    /* renamed from: b */
    private void m6353b(int i) {
        if (this.f5034p) {
            this.f5020b.setVisibility(0);
            if (i < this.f5027i.size()) {
                ClubPhotoDTO clubPhotoDTO = (ClubPhotoDTO) this.f5027i.get(i);
                if (clubPhotoDTO != null) {
                    if (this.f5038t.equals(clubPhotoDTO.getUserId()) || this.f5033o.equals(this.f5038t)) {
                        m6352b();
                    } else {
                        m6347a();
                    }
                    CharSequence content = clubPhotoDTO.getContent();
                    if (TextUtils.isEmpty(content)) {
                        this.f5025g.setVisibility(8);
                    } else {
                        this.f5025g.setVisibility(0);
                        this.f5025g.setText(content);
                    }
                    this.f5021c.setText(clubPhotoDTO.getNickName());
                    if (clubPhotoDTO.getLikeNum() <= 0) {
                        this.f5023e.setText("");
                    } else {
                        this.f5023e.setText(String.valueOf(clubPhotoDTO.getLikeNum()));
                    }
                    if (clubPhotoDTO.isHasLiked()) {
                        this.f5022d.setSelected(true);
                    } else {
                        this.f5022d.setSelected(false);
                    }
                    if (clubPhotoDTO.getCommentNum() <= 0) {
                        this.f5024f.setText("");
                    } else {
                        this.f5024f.setText(String.valueOf(clubPhotoDTO.getCommentNum()));
                    }
                    if (TextUtils.isEmpty(clubPhotoDTO.getAvatar())) {
                        this.f5019a.setImageResource(C1373R.drawable.ic_avatar);
                        return;
                    } else {
                        Picasso.with(this).load(clubPhotoDTO.getAvatar()).fit().placeholder((int) C1373R.drawable.ic_avatar).error((int) C1373R.drawable.ic_avatar).into(this.f5019a);
                        return;
                    }
                }
                return;
            }
            return;
        }
        this.f5020b.setVisibility(8);
    }

    /* renamed from: c */
    private void m6356c(int i) {
        getAsyncTaskQueue().a(new ClubFeedImageDetailsActivity$5(this, i), new Integer[]{Integer.valueOf(i)});
    }

    /* renamed from: d */
    private void m6359d(int i) {
        if (i >= 0 && i < this.f5027i.size()) {
            ClubPhotoDTO clubPhotoDTO = (ClubPhotoDTO) this.f5027i.get(i);
            if (clubPhotoDTO != null) {
                Intent intent = new Intent(this, ClubImageDetailsActivity.class);
                intent.putExtra("photo", clubPhotoDTO);
                startActivity(intent);
            }
        }
    }

    /* renamed from: a */
    private void m6350a(String str) {
        if (this != null && !isFinishing()) {
            if (this.f5037s == null) {
                this.f5037s = new C2159g(this, str);
            }
            this.f5037s.a(str);
            this.f5037s.showAtLocation(findViewById(C1373R.id.activity_clubfeed_image_view), 17, 0, 0);
        }
    }
}
