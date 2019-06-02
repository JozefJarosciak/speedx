package io.rong.imkit.fragment;

import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.Event.ConversationTopEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;

public class SetConversationToTopFragment extends BaseSettingFragment {
    private static String TAG = SetConversationToTopFragment.class.getSimpleName();

    /* renamed from: io.rong.imkit.fragment.SetConversationToTopFragment$1 */
    class C50751 extends ResultCallback<Conversation> {
        C50751() {
        }

        public void onSuccess(Conversation conversation) {
            if (conversation != null) {
                SetConversationToTopFragment.this.setSwitchBtnStatus(conversation.isTop());
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    protected void initData() {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().getEventBus().register(this);
        }
        RongIM.getInstance().getConversation(getConversationType(), getTargetId(), new C50751());
    }

    protected boolean setSwitchButtonEnabled() {
        return true;
    }

    protected String setTitle() {
        return getString(C4974R.string.rc_setting_set_top);
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
        if (getConversationType() == null || TextUtils.isEmpty(getTargetId())) {
            RLog.m19420e(TAG, "toggleSwitch() args is null");
        } else {
            RongIM.getInstance().setConversationToTop(getConversationType(), getTargetId(), z, null);
        }
    }

    public void onEventMainThread(ConversationTopEvent conversationTopEvent) {
        if (conversationTopEvent != null && conversationTopEvent.getTargetId().equals(getTargetId()) && conversationTopEvent.getConversationType().getValue() == getConversationType().getValue()) {
            setSwitchBtnStatus(conversationTopEvent.isTop());
        }
    }

    public void onDestroy() {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().getEventBus().unregister(this);
        }
        super.onDestroy();
    }
}
