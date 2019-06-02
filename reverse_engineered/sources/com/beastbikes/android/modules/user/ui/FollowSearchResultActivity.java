package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.user.dto.SeekFriendDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.C2638d;
import com.beastbikes.android.widget.C2638d.C2631b;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.WebActivity;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.j256.ormlite.field.FieldType;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903134)
public class FollowSearchResultActivity extends SessionFragmentActivity implements C2534b, C2631b {
    @C1458a(a = 2131755746)
    /* renamed from: a */
    private LinearLayout f6397a;
    /* renamed from: b */
    private C2638d f6398b;
    @C1458a(a = 2131755747)
    /* renamed from: c */
    private TextView f6399c;
    /* renamed from: d */
    private C2389c f6400d;
    /* renamed from: e */
    private FollowSearchResultActivity$a f6401e;
    /* renamed from: f */
    private int f6402f = 1;
    /* renamed from: g */
    private List<FriendDTO> f6403g = new ArrayList();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        CharSequence stringExtra = getIntent().getStringExtra(WebActivity.EXTRA_TITLE);
        if (!TextUtils.isEmpty(stringExtra)) {
            super.setTitle(stringExtra);
        }
        this.f6400d = new C2389c(this);
        this.f6398b = new C2638d(this, this.f6397a, this.f6403g, 2);
        this.f6398b.setRecyclerCallBack(this);
        this.f6401e = new FollowSearchResultActivity$a(this, this, this);
        this.f6398b.setAdapter(this.f6401e);
        m7626d();
    }

    /* renamed from: a */
    public void m7627a() {
        this.f6402f = 1;
        this.f6398b.setHasFooter(true);
        m7626d();
    }

    public void a_() {
        m7626d();
    }

    /* renamed from: a */
    public void m7628a(ViewHolder viewHolder, int i) {
        if (this.f6403g != null && this.f6403g.size() > 0) {
            FriendDTO friendDTO = (FriendDTO) this.f6403g.get(i);
            if (friendDTO != null) {
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("user_id", friendDTO.getFriendId());
                intent.putExtra("nick_name", friendDTO.getNickname());
                intent.putExtra("remarks", friendDTO.getRemarks());
                intent.putExtra("avatar", friendDTO.getAvatar());
                super.startActivity(intent);
            }
        }
    }

    /* renamed from: b */
    public void m7629b(ViewHolder viewHolder, int i) {
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: d */
    private void m7626d() {
        switch (getIntent().getIntExtra("follow_type", 0)) {
            case 0:
                String stringExtra = getIntent().getStringExtra("search_content");
                if (!TextUtils.isEmpty(stringExtra)) {
                    m7615a(stringExtra);
                    return;
                }
                return;
            case 1:
                try {
                    this.f6399c.setText(C1373R.string.follow_search_result_empty_contacts);
                    this.f6398b.setRefreshEnable(false);
                    getAsyncTaskQueue().a(new FollowSearchResultActivity$1(this, new C1802i(this, null, true)), new Void[0]);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 2:
                this.f6399c.setText(C1373R.string.follow_search_result_empty_weibo);
                this.f6398b.setRefreshEnable(false);
                Intent intent = getIntent();
                m7617a(intent.getStringExtra("open_id"), intent.getStringExtra("access_token"));
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m7617a(String str, String str2) {
        getAsyncTaskQueue().a(new FollowSearchResultActivity$2(this, new C1802i(this, null, true), str, str2), new Void[0]);
    }

    /* renamed from: a */
    private void m7618a(List<FriendDTO> list) {
        this.f6398b.a();
        if (list == null || list.size() <= 0) {
            this.f6398b.a(false);
            this.f6398b.setHasFooter(false);
            if (this.f6402f == 1) {
                this.f6399c.setVisibility(0);
                return;
            }
            return;
        }
        this.f6399c.setVisibility(8);
        if (list.size() < 20) {
            this.f6398b.a(false);
            this.f6398b.setHasFooter(false);
        }
        if (this.f6402f == 1) {
            this.f6403g.clear();
        }
        this.f6402f++;
        this.f6403g.addAll(list);
        this.f6398b.b();
    }

    /* renamed from: a */
    private void m7615a(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new FollowSearchResultActivity$3(this, new C1802i(this, null, true), str), new String[0]);
        }
    }

    /* renamed from: a */
    private void m7616a(String str, int i) {
        getAsyncTaskQueue().a(new FollowSearchResultActivity$4(this, str, i), new String[0]);
    }

    /* renamed from: b */
    private void m7621b(String str, int i) {
        getAsyncTaskQueue().a(new FollowSearchResultActivity$5(this, str, i), new String[0]);
    }

    /* renamed from: c */
    private void m7624c(String str, int i) {
        C2621c c2621c = new C2621c(this);
        c2621c.b(C1373R.string.msg_unfollow_prompt_dialog);
        c2621c.b(C1373R.string.cancel, new FollowSearchResultActivity$6(this, c2621c));
        c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new FollowSearchResultActivity$7(this, str, i, c2621c));
        c2621c.a();
    }

    /* renamed from: c */
    public List<FriendDTO> m7630c() throws Exception {
        Cursor query = getContentResolver().query(Phone.CONTENT_URI, new String[]{FieldType.FOREIGN_ID_FIELD_SUFFIX, "display_name", "data1"}, null, null, null);
        List arrayList = new ArrayList();
        if (query != null) {
            PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
            while (query.moveToNext()) {
                String string = query.getString(2);
                if (string != null) {
                    String string2 = query.getString(1);
                    SeekFriendDTO seekFriendDTO = new SeekFriendDTO();
                    seekFriendDTO.setNickName(string2);
                    try {
                        seekFriendDTO.setSeekValue(instance.format(instance.parse(string, "CN"), PhoneNumberFormat.E164));
                    } catch (NumberParseException e) {
                    }
                    arrayList.add(seekFriendDTO);
                }
            }
            query.close();
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        return this.f6400d.a(2, "", "", arrayList);
    }
}
