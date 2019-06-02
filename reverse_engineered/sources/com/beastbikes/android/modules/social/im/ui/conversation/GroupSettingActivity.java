package com.beastbikes.android.modules.social.im.ui.conversation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.ui.ClubMemberManagerActivity;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.widget.LinearListView;
import com.beastbikes.android.widget.LinearListView.C2126b;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.business.BusinessException;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903138)
public class GroupSettingActivity extends SessionFragmentActivity implements OnSharedPreferenceChangeListener, OnClickListener, C2126b, C1706a {
    /* renamed from: a */
    public static String f6169a = "";
    @C1458a(a = 2131755768)
    /* renamed from: b */
    View f6170b;
    @C1458a(a = 2131755775)
    /* renamed from: c */
    Switch f6171c;
    @C1458a(a = 2131755777)
    /* renamed from: d */
    TextView f6172d;
    @C1458a(a = 2131755767)
    /* renamed from: e */
    EditText f6173e;
    @C1458a(a = 2131755773)
    /* renamed from: f */
    Switch f6174f;
    @C1458a(a = 2131755771)
    /* renamed from: g */
    LinearListView f6175g;
    /* renamed from: h */
    private ConversationType f6176h;
    /* renamed from: i */
    private String f6177i;
    /* renamed from: j */
    private ClubInfoCompact f6178j;
    /* renamed from: k */
    private List<RankDTO> f6179k = new ArrayList();
    /* renamed from: l */
    private GroupSettingActivity$a f6180l;
    /* renamed from: m */
    private SharedPreferences f6181m;
    /* renamed from: n */
    private SharedPreferences f6182n;
    /* renamed from: o */
    private int f6183o = ConversationNotificationStatus.NOTIFY.getValue();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setTitle(f6169a);
            }
            this.f6177i = intent.getStringExtra("TARGET_ID");
            int intExtra = intent.getIntExtra("CONVERSATION_TYPE", ConversationType.PRIVATE.ordinal());
            if (intExtra == ConversationType.PRIVATE.ordinal()) {
                this.f6176h = ConversationType.PRIVATE;
            } else if (intExtra == ConversationType.GROUP.ordinal()) {
                this.f6176h = ConversationType.GROUP;
            }
            try {
                this.f6178j = new ClubManager(this).a(m5331p());
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            this.f6170b.setOnClickListener(this);
            this.f6174f.setOnCheckedChangeListener(this);
            this.f6171c.setOnCheckedChangeListener(this);
            this.f6175g.setOnItemClickListener(this);
            m7356a();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_group_setting_see_all_lay:
                if (this.f6178j != null) {
                    Intent intent = new Intent(this, ClubMemberManagerActivity.class);
                    intent.putExtra("club_id", this.f6178j.getObjectId());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("club_info", this.f6178j);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void finish() {
        super.finish();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.f6173e.getWindowToken(), 0);
        }
    }

    protected void onPause() {
        super.onPause();
        Object obj = this.f6173e.getText().toString();
        if (!TextUtils.isEmpty(obj)) {
            new GroupSettingActivity$1(this, obj).execute(new String[0]);
        }
    }

    /* renamed from: a */
    public void m7363a(Switch switchR, boolean z) {
        if (this.f6174f.getTag() != null && switchR == this.f6174f) {
            ConversationNotificationStatus conversationNotificationStatus;
            if (this.f6183o == 1) {
                conversationNotificationStatus = ConversationNotificationStatus.DO_NOT_DISTURB;
            } else {
                conversationNotificationStatus = ConversationNotificationStatus.NOTIFY;
            }
            if (RongIM.getInstance() != null) {
                RongIM.getInstance().setConversationNotificationStatus(this.f6176h, this.f6177i, conversationNotificationStatus, new GroupSettingActivity$2(this, conversationNotificationStatus));
            }
        }
    }

    /* renamed from: a */
    public void m7362a(LinearListView linearListView, View view, int i, long j) {
        if (linearListView.getId() == C1373R.id.activity_group_setting_group_member) {
            RankDTO rankDTO = (RankDTO) this.f6180l.getItem(i);
            if (rankDTO != null) {
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.setClass(this, ProfileActivity.class);
                intent.putExtra("user_id", rankDTO.getUserId());
                intent.putExtra("avatar", rankDTO.getAvatarUrl());
                intent.putExtra("nick_name", rankDTO.getNickname());
                startActivity(intent);
            }
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
    }

    /* renamed from: a */
    private void m7356a() {
        if (this.f6178j != null) {
            if (AVUser.getCurrentUser() != null) {
                this.f6182n = getSharedPreferences(AVUser.getCurrentUser().getObjectId(), 0);
                CharSequence string = this.f6182n.getString(this.f6178j.getObjectId(), "");
                this.f6173e.setHint(AVUser.getCurrentUser().getDisplayName());
                if (!TextUtils.isEmpty(string)) {
                    this.f6173e.setText(string);
                }
                Spannable text = this.f6173e.getText();
                if (!TextUtils.isEmpty(text)) {
                    Selection.setSelection(text, text.length());
                }
            }
            this.f6174f.setEnabled(false);
            this.f6174f.setClickable(false);
            if (RongIM.getInstance() != null) {
                RongIM.getInstance().getConversationNotificationStatus(this.f6176h, this.f6177i, new GroupSettingActivity$3(this));
            }
            this.f6181m = PreferenceManager.getDefaultSharedPreferences(this);
            this.f6181m.registerOnSharedPreferenceChangeListener(this);
            this.f6172d.setText(this.f6178j.getNotice());
            this.f6180l = new GroupSettingActivity$a(this);
            this.f6175g.setAdapter(this.f6180l);
            m7357a(this.f6177i);
        }
    }

    /* renamed from: a */
    private void m7357a(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new GroupSettingActivity$4(this), new String[]{str});
        }
    }
}
