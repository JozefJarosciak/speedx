package io.rong.imkit.fragment;

import android.os.Message;
import android.view.View;
import android.widget.Toast;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.AlterDialogFragment;
import io.rong.imkit.widget.AlterDialogFragment.AlterDialogBtnListener;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;

public class ClearConversationMsgFragment extends BaseSettingFragment implements AlterDialogBtnListener {
    private Conversation conversation;

    /* renamed from: io.rong.imkit.fragment.ClearConversationMsgFragment$1 */
    class C50141 extends ResultCallback<Boolean> {
        C50141() {
        }

        public void onSuccess(Boolean bool) {
            Toast.makeText(ClearConversationMsgFragment.this.getActivity(), ClearConversationMsgFragment.this.getString(C4974R.string.rc_setting_clear_msg_success), 0).show();
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            Toast.makeText(ClearConversationMsgFragment.this.getActivity(), ClearConversationMsgFragment.this.getString(C4974R.string.rc_setting_clear_msg_fail), 0).show();
        }
    }

    protected void initData() {
    }

    protected String setTitle() {
        return getString(C4974R.string.rc_setting_clear_msg_name);
    }

    protected boolean setSwitchButtonEnabled() {
        return false;
    }

    protected int setSwitchBtnVisibility() {
        return 8;
    }

    protected void onSettingItemClick(View view) {
        this.conversation = new Conversation();
        this.conversation.setConversationType(getConversationType());
        this.conversation.setTargetId(getTargetId());
        AlterDialogFragment newInstance = AlterDialogFragment.newInstance(getString(C4974R.string.rc_setting_name), getString(C4974R.string.rc_setting_clear_msg_prompt), getString(C4974R.string.rc_dialog_cancel), getString(C4974R.string.rc_dialog_ok));
        newInstance.setOnAlterDialogBtnListener(this);
        newInstance.show(getFragmentManager());
    }

    public void onDialogNegativeClick(AlterDialogFragment alterDialogFragment) {
        alterDialogFragment.dismiss();
    }

    public void onDialogPositiveClick(AlterDialogFragment alterDialogFragment) {
        if (this.conversation != null) {
            RongIM.getInstance().clearMessages(this.conversation.getConversationType(), this.conversation.getTargetId(), new C50141());
            RongIM.getInstance().clearTextMessageDraft(this.conversation.getConversationType(), this.conversation.getTargetId(), null);
        }
    }

    protected void toggleSwitch(boolean z) {
    }

    public boolean handleMessage(Message message) {
        return false;
    }
}
