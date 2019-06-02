package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2057d;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeed;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedBase;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedImageTxtRecord;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedPost;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.modules.cycling.club.dto.ImageInfo;
import com.beastbikes.android.modules.cycling.club.dto.RecordInfo;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2154e;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2154e.C2153a;
import com.beastbikes.android.modules.user.p153c.C2408a;
import com.beastbikes.android.modules.user.ui.CyclingRecordActivity;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@C1457a(a = "俱乐部发布feed")
@C1459b(a = 2130903100)
public class ClubFeedPostActivity extends SessionFragmentActivity implements OnClickListener, C2153a {
    /* renamed from: a */
    protected int f5063a = 9;
    @C1458a(a = 2131755537)
    /* renamed from: b */
    protected LinearLayout f5064b;
    @C1458a(a = 2131755538)
    /* renamed from: c */
    ImageView f5065c;
    @C1458a(a = 2131755539)
    /* renamed from: d */
    TextView f5066d;
    @C1458a(a = 2131755540)
    /* renamed from: e */
    ImageView f5067e;
    @C1458a(a = 2131755535)
    /* renamed from: f */
    protected EditText f5068f;
    @C1458a(a = 2131755541)
    /* renamed from: g */
    protected CheckBox f5069g;
    /* renamed from: h */
    protected ArrayList<String> f5070h = new ArrayList();
    /* renamed from: i */
    protected MenuItem f5071i;
    /* renamed from: j */
    protected C2154e f5072j;
    /* renamed from: k */
    private RecordInfo f5073k;
    /* renamed from: l */
    private String f5074l;
    /* renamed from: m */
    private boolean f5075m;
    /* renamed from: n */
    private boolean f5076n;
    /* renamed from: o */
    private boolean f5077o;
    /* renamed from: p */
    private SharedPreferences f5078p;
    /* renamed from: q */
    private boolean f5079q;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (getIntent() != null) {
            this.f5074l = getIntent().getStringExtra("club_extra_id");
        }
        m6402f();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C1373R.menu.clubfeed_post_menu, menu);
        this.f5071i = menu.getItem(0);
        this.f5071i.setEnabled(false);
        return super.onCreateOptionsMenu(menu);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.f5068f.getWindowToken(), 0);
    }

    /* renamed from: a */
    protected int mo2771a() {
        return 0;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (this.f5075m || this.f5076n || this.f5077o) {
                    C2621c c2621c = new C2621c(this);
                    c2621c.b(C1373R.string.club_post_feed_back_warming);
                    c2621c.a(C1373R.string.str_ok, new ClubFeedPostActivity$1(this, c2621c));
                    c2621c.b(C1373R.string.cancel, new ClubFeedPostActivity$2(this, c2621c));
                    c2621c.a();
                    return true;
                }
            case C1373R.id.menu_clubfeed_post:
                C2057d.a().a(m6404b(), this.f5074l);
                setResult(-1);
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: b */
    protected ClubFeed m6404b() {
        String obj = this.f5068f.getText().toString();
        String sportIdentify = this.f5073k == null ? "" : this.f5073k.getSportIdentify();
        List c = m6405c();
        ClubFeed clubFeed = new ClubFeed();
        clubFeed.setStamp(System.currentTimeMillis());
        clubFeed.setFeedType(1);
        clubFeed.setFid((int) System.currentTimeMillis());
        clubFeed.setState(1);
        ClubFeedPost clubFeedPost = new ClubFeedPost(this.f5079q ? 1 : 0, this.f5074l, obj, sportIdentify, mo2771a());
        clubFeed.setPost(clubFeedPost);
        ClubFeedBase clubFeedImageTxtRecord = new ClubFeedImageTxtRecord();
        clubFeedImageTxtRecord.setRecordInfo(this.f5073k);
        clubFeedImageTxtRecord.setText(clubFeedPost.getContent());
        clubFeedImageTxtRecord.setFeedType(clubFeed.getFeedType());
        clubFeedImageTxtRecord.setFid(clubFeed.getFid());
        clubFeedImageTxtRecord.setClubId(this.f5074l);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        List arrayList;
        clubFeedImageTxtRecord.setUserId(currentUser.getObjectId());
        clubFeedImageTxtRecord.setUser(new ClubUser(currentUser.getObjectId(), currentUser.getDisplayName(), currentUser.getAvatar()));
        clubFeedImageTxtRecord.setDate(new Date(clubFeed.getStamp()));
        if (c == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = c;
        }
        clubFeedImageTxtRecord.setImageList(arrayList);
        clubFeedImageTxtRecord.setStatus(1);
        clubFeedImageTxtRecord.setClubId(this.f5074l);
        clubFeedImageTxtRecord.setText(clubFeedPost.getContent());
        clubFeed.setImageTxt(clubFeedImageTxtRecord);
        return clubFeed;
    }

    /* renamed from: e */
    private void m6401e() {
        if (this.f5071i != null) {
            if (this.f5076n || this.f5075m || this.f5077o) {
                this.f5071i.setEnabled(true);
            } else {
                this.f5071i.setEnabled(false);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i2) {
            case -1:
                switch (i) {
                    case 90:
                        this.f5073k = (RecordInfo) intent.getSerializableExtra("record_info");
                        if (this.f5073k != null) {
                            this.f5077o = true;
                            this.f5067e.setImageResource(C1373R.drawable.ic_delete);
                            CharSequence title = this.f5073k.getTitle();
                            if (TextUtils.isEmpty(title) || title.equals("null")) {
                                String format = new SimpleDateFormat("MM-dd").format(this.f5073k.getStartDate());
                                this.f5066d.setText(format + C2408a.a(this.f5073k.getStartDate().getTime()));
                            } else {
                                this.f5066d.setText(title);
                            }
                            if (TextUtils.isEmpty(this.f5073k.getCyclingImage())) {
                                this.f5065c.setImageResource(C1373R.drawable.ic_feed_cycling);
                            } else {
                                Picasso.with(this).load(this.f5073k.getCyclingImage()).fit().error((int) C1373R.drawable.ic_feed_cycling).placeholder((int) C1373R.drawable.ic_feed_cycling).centerCrop().into(this.f5065c);
                            }
                        }
                        m6401e();
                        break;
                    case 1009:
                        if (this.f5072j != null) {
                            ArrayList stringArrayListExtra = intent.getStringArrayListExtra("select_result");
                            if (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) {
                                this.f5076n = false;
                            } else {
                                this.f5076n = true;
                            }
                            m6401e();
                            this.f5072j.a(i, i2, intent);
                            break;
                        }
                        break;
                    default:
                        break;
                }
        }
        super.onActivityResult(i, i2, intent);
    }

    /* renamed from: f */
    private void m6402f() {
        LinearLayout linearLayout = (LinearLayout) findViewById(C1373R.id.add_pic_lay);
        this.f5072j = new C2154e(this, this);
        linearLayout.addView(this.f5072j);
        this.f5064b = (LinearLayout) findViewById(C1373R.id.clubfeed_add_record);
        this.f5064b.setOnClickListener(this);
        this.f5068f = (EditText) findViewById(C1373R.id.edit);
        this.f5078p = getSharedPreferences(m5331p(), 0);
        this.f5079q = this.f5078p.getBoolean("NEEDSYNC", true);
        this.f5069g = (CheckBox) findViewById(C1373R.id.clubfeed_save_cb);
        if (this.f5079q) {
            this.f5069g.setChecked(true);
        } else {
            this.f5069g.setChecked(false);
        }
        this.f5067e = (ImageView) findViewById(C1373R.id.iv_right_icon);
        this.f5067e.setOnClickListener(this);
        if (this.f5067e != null) {
            this.f5067e.setOnClickListener(this);
        }
        this.f5069g.setOnCheckedChangeListener(new ClubFeedPostActivity$3(this));
        this.f5068f.addTextChangedListener(new ClubFeedPostActivity$4(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.clubfeed_add_record:
                Intent intent = new Intent(this, CyclingRecordActivity.class);
                intent.putExtra("from_club", true);
                startActivityForResult(intent, 90);
                return;
            case C1373R.id.iv_right_icon:
                if (this.f5073k != null) {
                    this.f5073k = null;
                    this.f5065c.setImageResource(C1373R.drawable.ic_feed_cycling);
                    this.f5066d.setText(C1373R.string.club_feed_add_cycling_record);
                    this.f5067e.setImageResource(C1373R.drawable.ic_arrow_right_icon);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: c */
    protected List<ImageInfo> m6405c() {
        List<ImageInfo> arrayList = new ArrayList();
        int size = this.f5072j.getSelectedFiles().size();
        for (int i = 0; i < size; i++) {
            ImageInfo imageInfo = new ImageInfo();
            String str = (String) this.f5072j.getSelectedFiles().get(i);
            imageInfo.setUrl(str);
            imageInfo.setId(System.currentTimeMillis() + "");
            Options options = new Options();
            options.inJustDecodeBounds = true;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            imageInfo.setHeight(options.outHeight);
            imageInfo.setWidth(options.outWidth);
            imageInfo.setMine(options.outMimeType);
            arrayList.add(imageInfo);
            if (!(decodeFile == null || decodeFile.isRecycled())) {
                decodeFile.recycle();
            }
        }
        return arrayList;
    }

    /* renamed from: d */
    public int m6406d() {
        return this.f5063a;
    }
}
