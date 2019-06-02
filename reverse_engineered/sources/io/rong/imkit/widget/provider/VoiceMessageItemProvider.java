package io.rong.imkit.widget.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.alibaba.fastjson.asm.Opcodes;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.AudioPlayManager;
import io.rong.imkit.manager.AudioRecordManager;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.VoiceMessage;

@ProviderTag(messageContent = VoiceMessage.class)
public class VoiceMessageItemProvider extends MessageProvider<VoiceMessage> {
    private static final String TAG = "VoiceMessageItemProvider";

    public VoiceMessageItemProvider(Context context) {
    }

    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_voice_message, null);
        VoiceMessageItemProvider$ViewHolder voiceMessageItemProvider$ViewHolder = new VoiceMessageItemProvider$ViewHolder(this);
        voiceMessageItemProvider$ViewHolder.left = (TextView) inflate.findViewById(C4974R.id.rc_left);
        voiceMessageItemProvider$ViewHolder.right = (TextView) inflate.findViewById(C4974R.id.rc_right);
        voiceMessageItemProvider$ViewHolder.img = (ImageView) inflate.findViewById(C4974R.id.rc_img);
        voiceMessageItemProvider$ViewHolder.unread = (ImageView) inflate.findViewById(C4974R.id.rc_voice_unread);
        inflate.setTag(voiceMessageItemProvider$ViewHolder);
        return inflate;
    }

    public void bindView(View view, int i, VoiceMessage voiceMessage, UIMessage uIMessage) {
        VoiceMessageItemProvider$ViewHolder voiceMessageItemProvider$ViewHolder = (VoiceMessageItemProvider$ViewHolder) view.getTag();
        if (uIMessage.continuePalyAudio) {
            Uri playingUri = AudioPlayManager.getInstance().getPlayingUri();
            if (playingUri == null || !playingUri.equals(voiceMessage.getUri())) {
                AudioPlayManager.getInstance().startPlay(view.getContext(), voiceMessage.getUri(), new VoiceMessageItemProvider$1(this, uIMessage, view, voiceMessageItemProvider$ViewHolder, voiceMessage, uIMessage.getMessage().getReceivedStatus().isListened()));
                return;
            }
            return;
        }
        setLayout(view.getContext(), voiceMessageItemProvider$ViewHolder, uIMessage, false);
    }

    public void onItemClick(View view, int i, VoiceMessage voiceMessage, UIMessage uIMessage) {
        RLog.d(TAG, "Item index:" + i);
        VoiceMessageItemProvider$ViewHolder voiceMessageItemProvider$ViewHolder = (VoiceMessageItemProvider$ViewHolder) view.getTag();
        voiceMessageItemProvider$ViewHolder.unread.setVisibility(8);
        Uri playingUri = AudioPlayManager.getInstance().getPlayingUri();
        if (playingUri == null || !playingUri.equals(voiceMessage.getUri())) {
            AudioPlayManager.getInstance().startPlay(view.getContext(), voiceMessage.getUri(), new VoiceMessageItemProvider$2(this, uIMessage, view, voiceMessageItemProvider$ViewHolder, voiceMessage, uIMessage.getMessage().getReceivedStatus().isListened()));
            return;
        }
        AudioPlayManager.getInstance().stopPlay();
    }

    public void onItemLongClick(View view, int i, VoiceMessage voiceMessage, UIMessage uIMessage) {
        int i2;
        boolean z;
        int integer;
        NotFoundException e;
        String[] strArr;
        String str = null;
        if (!uIMessage.getConversationType().getName().equals(ConversationType.APP_PUBLIC_SERVICE.getName()) && !uIMessage.getConversationType().getName().equals(ConversationType.PUBLIC_SERVICE.getName())) {
            UserInfo userInfo = uIMessage.getUserInfo();
            if (userInfo == null) {
                userInfo = RongUserInfoManager.getInstance().getUserInfo(uIMessage.getSenderUserId());
            }
            if (userInfo != null) {
                str = userInfo.getName();
            }
        } else if (uIMessage.getUserInfo() != null) {
            str = uIMessage.getUserInfo().getName();
        } else {
            PublicServiceProfile publicServiceProfile = RongUserInfoManager.getInstance().getPublicServiceProfile(PublicServiceType.setValue(uIMessage.getConversationType().getValue()), uIMessage.getTargetId());
            if (publicServiceProfile != null) {
                str = publicServiceProfile.getName();
            }
        }
        long currentTimeMillis = System.currentTimeMillis() - RongIM.getInstance().getDeltaTime();
        if (uIMessage.getSentStatus().equals(Message$SentStatus.SENDING) || uIMessage.getSentStatus().equals(Message$SentStatus.FAILED)) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        try {
            z = RongContext.getInstance().getResources().getBoolean(C4974R.bool.rc_enable_message_recall);
            try {
                integer = RongContext.getInstance().getResources().getInteger(C4974R.integer.rc_message_recall_interval);
            } catch (NotFoundException e2) {
                e = e2;
                RLog.e(TAG, "rc_message_recall_interval not configure in rc_config.xml");
                e.printStackTrace();
                integer = -1;
                if (i2 == 0) {
                }
                ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new VoiceMessageItemProvider$3(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
            }
        } catch (NotFoundException e3) {
            e = e3;
            z = false;
            RLog.e(TAG, "rc_message_recall_interval not configure in rc_config.xml");
            e.printStackTrace();
            integer = -1;
            if (i2 == 0) {
            }
            ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new VoiceMessageItemProvider$3(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
        }
        strArr = (i2 == 0 && z && currentTimeMillis - uIMessage.getSentTime() <= ((long) (integer * 1000)) && uIMessage.getSenderUserId().equals(RongIM.getInstance().getCurrentUserId())) ? new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete), view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_recall)} : new String[]{view.getContext().getResources().getString(C4974R.string.rc_dialog_item_message_delete)};
        ArraysDialogFragment.newInstance(str, strArr).setArraysDialogItemListener(new VoiceMessageItemProvider$3(this, uIMessage)).show(((FragmentActivity) view.getContext()).getSupportFragmentManager());
    }

    private void setLayout(Context context, VoiceMessageItemProvider$ViewHolder voiceMessageItemProvider$ViewHolder, UIMessage uIMessage, boolean z) {
        VoiceMessage voiceMessage = (VoiceMessage) uIMessage.getContent();
        int maxVoiceDuration = AudioRecordManager.getInstance().getMaxVoiceDuration();
        voiceMessageItemProvider$ViewHolder.img.getLayoutParams().width = (int) (((float) (57 + ((Opcodes.GETFIELD / maxVoiceDuration) * voiceMessage.getDuration()))) * context.getResources().getDisplayMetrics().density);
        AnimationDrawable animationDrawable;
        if (uIMessage.getMessageDirection() == Message$MessageDirection.SEND) {
            voiceMessageItemProvider$ViewHolder.left.setText(String.format("%s\"", new Object[]{Integer.valueOf(voiceMessage.getDuration())}));
            voiceMessageItemProvider$ViewHolder.left.setVisibility(0);
            voiceMessageItemProvider$ViewHolder.right.setVisibility(8);
            voiceMessageItemProvider$ViewHolder.unread.setVisibility(8);
            voiceMessageItemProvider$ViewHolder.img.setScaleType(ScaleType.FIT_END);
            voiceMessageItemProvider$ViewHolder.img.setBackgroundResource(C4974R.drawable.rc_ic_bubble_right);
            animationDrawable = (AnimationDrawable) context.getResources().getDrawable(C4974R.drawable.rc_an_voice_sent);
            if (z) {
                voiceMessageItemProvider$ViewHolder.img.setImageDrawable(animationDrawable);
                if (animationDrawable != null) {
                    animationDrawable.start();
                    return;
                }
                return;
            }
            voiceMessageItemProvider$ViewHolder.img.setImageDrawable(voiceMessageItemProvider$ViewHolder.img.getResources().getDrawable(C4974R.drawable.rc_ic_voice_sent));
            if (animationDrawable != null) {
                animationDrawable.stop();
                return;
            }
            return;
        }
        voiceMessageItemProvider$ViewHolder.right.setText(String.format("%s\"", new Object[]{Integer.valueOf(voiceMessage.getDuration())}));
        voiceMessageItemProvider$ViewHolder.right.setVisibility(0);
        voiceMessageItemProvider$ViewHolder.left.setVisibility(8);
        if (uIMessage.getReceivedStatus().isListened()) {
            voiceMessageItemProvider$ViewHolder.unread.setVisibility(8);
        } else {
            voiceMessageItemProvider$ViewHolder.unread.setVisibility(0);
        }
        voiceMessageItemProvider$ViewHolder.img.setBackgroundResource(C4974R.drawable.rc_ic_bubble_left);
        animationDrawable = (AnimationDrawable) context.getResources().getDrawable(C4974R.drawable.rc_an_voice_receive);
        if (z) {
            voiceMessageItemProvider$ViewHolder.img.setImageDrawable(animationDrawable);
            if (animationDrawable != null) {
                animationDrawable.start();
            }
        } else {
            voiceMessageItemProvider$ViewHolder.img.setImageDrawable(voiceMessageItemProvider$ViewHolder.img.getResources().getDrawable(C4974R.drawable.rc_ic_voice_receive));
            if (animationDrawable != null) {
                animationDrawable.stop();
            }
        }
        voiceMessageItemProvider$ViewHolder.img.setScaleType(ScaleType.FIT_START);
    }

    public Spannable getContentSummary(VoiceMessage voiceMessage) {
        return new SpannableString(RongContext.getInstance().getString(C4974R.string.rc_message_content_voice));
    }

    @TargetApi(8)
    private boolean muteAudioFocus(Context context, boolean z) {
        boolean z2 = true;
        if (context == null) {
            RLog.d(TAG, "muteAudioFocus context is null.");
            return false;
        } else if (VERSION.SDK_INT < 8) {
            RLog.d(TAG, "muteAudioFocus Android 2.1 and below can not stop music");
            return false;
        } else {
            boolean z3;
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (z) {
                z3 = audioManager.requestAudioFocus(null, 3, 2) == 1;
            } else {
                if (audioManager.abandonAudioFocus(null) != 1) {
                    z2 = false;
                }
                z3 = z2;
            }
            RLog.d(TAG, "muteAudioFocus pauseMusic bMute=" + z + " result=" + z3);
            return z3;
        }
    }
}
