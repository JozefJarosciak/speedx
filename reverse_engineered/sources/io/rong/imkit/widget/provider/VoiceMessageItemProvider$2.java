package io.rong.imkit.widget.provider;

import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.view.View;
import io.rong.eventbus.EventBus;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.manager.IAudioPlayListener;
import io.rong.imkit.model.Event.AudioListenedEvent;
import io.rong.imkit.model.Event.PlayAudioEvent;
import io.rong.imkit.model.UIMessage;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.message.VoiceMessage;

class VoiceMessageItemProvider$2 implements IAudioPlayListener {
    final /* synthetic */ VoiceMessageItemProvider this$0;
    final /* synthetic */ VoiceMessage val$content;
    final /* synthetic */ VoiceMessageItemProvider$ViewHolder val$holder;
    final /* synthetic */ boolean val$listened;
    final /* synthetic */ UIMessage val$message;
    final /* synthetic */ View val$view;

    VoiceMessageItemProvider$2(VoiceMessageItemProvider voiceMessageItemProvider, UIMessage uIMessage, View view, VoiceMessageItemProvider$ViewHolder voiceMessageItemProvider$ViewHolder, VoiceMessage voiceMessage, boolean z) {
        this.this$0 = voiceMessageItemProvider;
        this.val$message = uIMessage;
        this.val$view = view;
        this.val$holder = voiceMessageItemProvider$ViewHolder;
        this.val$content = voiceMessage;
        this.val$listened = z;
    }

    public void onStart(Uri uri) {
        this.val$message.getReceivedStatus().setListened();
        this.val$message.continuePalyAudio = false;
        RongIMClient.getInstance().setMessageReceivedStatus(this.val$message.getMessageId(), this.val$message.getReceivedStatus(), null);
        VoiceMessageItemProvider.access$000(this.this$0, this.val$view.getContext(), this.val$holder, this.val$message, true);
        EventBus.getDefault().post(new AudioListenedEvent(this.val$message.getConversationType(), this.val$message.getTargetId(), this.val$message.getMessageId()));
    }

    public void onStop(Uri uri) {
        VoiceMessageItemProvider.access$000(this.this$0, this.val$view.getContext(), this.val$holder, this.val$message, false);
    }

    public void onComplete(Uri uri) {
        VoiceMessageItemProvider.access$000(this.this$0, this.val$view.getContext(), this.val$holder, this.val$message, false);
        PlayAudioEvent obtain = PlayAudioEvent.obtain();
        obtain.content = this.val$content;
        if (!this.val$listened && this.val$message.getMessageDirection().equals(Message$MessageDirection.RECEIVE)) {
            try {
                obtain.continuously = RongContext.getInstance().getResources().getBoolean(C4974R.bool.rc_play_audio_continuous);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        if (obtain.continuously) {
            EventBus.getDefault().post(obtain);
        }
    }
}
