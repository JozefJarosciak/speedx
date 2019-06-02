package io.rong.imkit.fragment;

import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.Event.ConversationNotificationEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;

public class SetConversationNotificationFragment extends BaseSettingFragment {
    private static final String TAG = "SetConversationNotificationFragment";

    /* renamed from: io.rong.imkit.fragment.SetConversationNotificationFragment$1 */
    class C50731 extends ResultCallback<ConversationNotificationStatus> {
        C50731() {
        }

        public void onSuccess(ConversationNotificationStatus conversationNotificationStatus) {
            if (conversationNotificationStatus != null) {
                SetConversationNotificationFragment.this.setSwitchBtnStatus(conversationNotificationStatus != ConversationNotificationStatus.DO_NOT_DISTURB);
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            SetConversationNotificationFragment.this.setSwitchBtnStatus(!SetConversationNotificationFragment.this.getSwitchBtnStatus());
        }
    }

    /* renamed from: io.rong.imkit.fragment.SetConversationNotificationFragment$2 */
    class C50742 extends ResultCallback<ConversationNotificationStatus> {
        C50742() {
        }

        public void onSuccess(ConversationNotificationStatus conversationNotificationStatus) {
            RLog.m19422i(SetConversationNotificationFragment.TAG, "SetConversationNotificationFragment onSuccess--");
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            SetConversationNotificationFragment.this.setSwitchBtnStatus(!SetConversationNotificationFragment.this.getSwitchBtnStatus());
        }
    }

    public static SetConversationNotificationFragment newInstance() {
        return new SetConversationNotificationFragment();
    }

    protected void initData() {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().getEventBus().register(this);
        }
        RongIM.getInstance().getConversationNotificationStatus(getConversationType(), getTargetId(), new C50731());
    }

    protected boolean setSwitchButtonEnabled() {
        return true;
    }

    protected String setTitle() {
        return getString(C4974R.string.rc_setting_conversation_notify);
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    protected void onSettingItemClick(View view) {
        RLog.m19422i(TAG, "onSettingItemClick, " + view.toString());
    }

    protected int setSwitchBtnVisibility() {
        return 0;
    }

    protected void toggleSwitch(boolean z) {
        ConversationNotificationStatus conversationNotificationStatus;
        if (z) {
            conversationNotificationStatus = ConversationNotificationStatus.NOTIFY;
        } else {
            conversationNotificationStatus = ConversationNotificationStatus.DO_NOT_DISTURB;
        }
        if (getConversationType() == null || TextUtils.isEmpty(getTargetId())) {
            RLog.m19420e(TAG, "SetConversationNotificationFragment Arguments is null");
        } else {
            RongIM.getInstance().setConversationNotificationStatus(getConversationType(), getTargetId(), conversationNotificationStatus, new C50742());
        }
    }

    public void onEventMainThread(ConversationNotificationEvent conversationNotificationEvent) {
        if (conversationNotificationEvent != null && conversationNotificationEvent.getTargetId().equals(getTargetId()) && conversationNotificationEvent.getConversationType().getValue() == getConversationType().getValue()) {
            setSwitchBtnStatus(conversationNotificationEvent.getStatus() == ConversationNotificationStatus.NOTIFY);
        }
    }

    public void onDestroy() {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().getEventBus().unregister(this);
        }
        super.onDestroy();
    }
}
