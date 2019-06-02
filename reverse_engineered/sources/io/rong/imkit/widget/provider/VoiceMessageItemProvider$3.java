package io.rong.imkit.widget.provider;

import android.content.DialogInterface;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.AudioPlayManager;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;

class VoiceMessageItemProvider$3 implements OnArraysDialogItemListener {
    final /* synthetic */ VoiceMessageItemProvider this$0;
    final /* synthetic */ UIMessage val$message;

    VoiceMessageItemProvider$3(VoiceMessageItemProvider voiceMessageItemProvider, UIMessage uIMessage) {
        this.this$0 = voiceMessageItemProvider;
        this.val$message = uIMessage;
    }

    public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            AudioPlayManager.getInstance().stopPlay();
            RongIM.getInstance().deleteMessages(new int[]{this.val$message.getMessageId()}, null);
        } else if (i == 1) {
            if (AudioPlayManager.getInstance().getPlayingUri() != null) {
                AudioPlayManager.getInstance().stopPlay();
            }
            RongIM.getInstance().recallMessage(this.val$message);
        }
    }
}
