package com.beastbikes.android.modules.social.im.ui.conversation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.message.dto.MessageDTO;
import com.beastbikes.android.modules.message.p071a.C2310a;
import com.beastbikes.android.modules.message.ui.MessageActivity;
import com.beastbikes.android.utils.C2556e;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.business.BusinessException;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM$ConversationListBehaviorListener;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.model.Conversation.ConversationType;
import java.util.List;

public class ConversationListStaticActivity extends SessionFragmentActivity implements OnSharedPreferenceChangeListener, OnClickListener, RongIM$ConversationListBehaviorListener {
    /* renamed from: a */
    private TextView f11169a;
    /* renamed from: b */
    private TextView f11170b;
    /* renamed from: c */
    private TextView f11171c;
    /* renamed from: d */
    private C2310a f11172d;
    /* renamed from: e */
    private SharedPreferences f11173e;

    /* renamed from: com.beastbikes.android.modules.social.im.ui.conversation.ConversationListStaticActivity$1 */
    class C23431 extends AsyncTask<Void, Void, List<MessageDTO>> {
        /* renamed from: a */
        final /* synthetic */ ConversationListStaticActivity f11168a;

        C23431(ConversationListStaticActivity conversationListStaticActivity) {
            this.f11168a = conversationListStaticActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m11975a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m11976a((List) obj);
        }

        /* renamed from: a */
        protected List<MessageDTO> m11975a(Void... voidArr) {
            try {
                return this.f11168a.f11172d.m11806a();
            } catch (BusinessException e) {
                return null;
            }
        }

        /* renamed from: a */
        protected void m11976a(List<MessageDTO> list) {
            if (list != null && !list.isEmpty()) {
                MessageDTO messageDTO = (MessageDTO) list.get(list.size() - 1);
                if (messageDTO != null) {
                    this.f11168a.f11170b.setText(messageDTO.getMessage());
                    this.f11168a.f11171c.setText(C2556e.m12823a(messageDTO.getAvailableTime()));
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        C2580w.m12905a(this, "查看私信列表", null);
        setContentView(C1373R.layout.rc_conversation_list);
        this.f11173e = getSharedPreferences(p(), 0);
        this.f11173e.registerOnSharedPreferenceChangeListener(this);
        findViewById(C1373R.id.conversation_list_header).setOnClickListener(this);
        this.f11169a = (TextView) findViewById(C1373R.id.rc_unread_message1);
        this.f11170b = (TextView) findViewById(C1373R.id.rc_body);
        this.f11171c = (TextView) findViewById(C1373R.id.rc_time);
        Fragment conversationListFragment = new ConversationListFragment();
        conversationListFragment.setUri(Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon().appendPath("conversationlist").appendQueryParameter(ConversationType.PRIVATE.getName(), "false").appendQueryParameter(ConversationType.GROUP.getName(), "false").appendQueryParameter(ConversationType.PUBLIC_SERVICE.getName(), "false").build());
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(C1373R.id.frag_container, conversationListFragment);
        beginTransaction.commit();
        this.f11172d = new C2310a(this);
        RongIM.setConversationListBehaviorListener(this);
        m11978a();
    }

    protected void onResume() {
        super.onResume();
        m11979a("beast.friend.new.message.count");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.conversation_list_header:
                startActivity(new Intent(this, MessageActivity.class));
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m11979a(String str) {
        int i = this.f11173e.getInt(str, 0);
        if (i <= 0) {
            this.f11169a.setVisibility(8);
            return;
        }
        if (i > 99) {
            this.f11169a.setText("...");
        } else {
            this.f11169a.setText(String.valueOf(i));
        }
        this.f11169a.setVisibility(0);
    }

    /* renamed from: a */
    private void m11978a() {
        getAsyncTaskQueue().m13740a(new C23431(this), new Void[0]);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.equals("beast.friend.new.message.count")) {
            m11979a(str);
        }
    }

    public boolean onConversationPortraitClick(Context context, ConversationType conversationType, String str) {
        return false;
    }

    public boolean onConversationPortraitLongClick(Context context, ConversationType conversationType, String str) {
        return false;
    }

    public boolean onConversationLongClick(Context context, View view, UIConversation uIConversation) {
        return false;
    }

    public boolean onConversationClick(Context context, View view, UIConversation uIConversation) {
        if (uIConversation.getUnReadMessageCount() > 0) {
            if (uIConversation.getConversationType() == ConversationType.GROUP) {
                C2580w.m12905a(this, "消息列表点击俱乐部未读消息", "click_get_into_club_session_page");
            } else if (uIConversation.getConversationType() == ConversationType.PRIVATE) {
                C2580w.m12905a(this, "消息列表点击个人未读消息", "click_get_into_session_page");
            }
        }
        return false;
    }
}
