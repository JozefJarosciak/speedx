package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.MessageInputFragment;
import io.rong.imkit.widget.InputView;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.TypingMessage.TypingMessageManager;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

public abstract class InputProvider {
    private static final String TAG = "InputProvider";
    int index;
    RongContext mContext;
    Conversation mCurrentConversation;
    InputView mCurrentView;
    MessageInputFragment mFragment;

    public static abstract class ExtendProvider extends InputProvider {
        public abstract Drawable obtainPluginDrawable(Context context);

        public abstract CharSequence obtainPluginTitle(Context context);

        public abstract void onPluginClick(View view);

        public ExtendProvider(RongContext rongContext) {
            super(rongContext);
        }
    }

    /* renamed from: io.rong.imkit.widget.provider.InputProvider$1 */
    class C51831 implements ISendMessageCallback {
        C51831() {
        }

        public void onAttached(Message message) {
        }

        public void onSuccess(Message message) {
        }

        public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    public static abstract class MainInputProvider extends InputProvider {
        public abstract Drawable obtainSwitchDrawable(Context context);

        public abstract void onActive(Context context);

        public abstract View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, InputView inputView);

        public abstract void onInactive(Context context);

        public abstract void onSwitch(Context context);

        public MainInputProvider(RongContext rongContext) {
            super(rongContext);
        }

        public void onInputResume(InputView inputView, Conversation conversation) {
        }

        public void onInputPause() {
        }
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public RongContext getContext() {
        return this.mContext;
    }

    public InputProvider(RongContext rongContext) {
        this.mContext = rongContext;
    }

    public void setCurrentConversation(Conversation conversation) {
        this.mCurrentConversation = conversation;
    }

    public Conversation getCurrentConversation() {
        return this.mCurrentConversation;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void startActivityForResult(Intent intent, int i) {
        if (this.mFragment != null) {
            this.mFragment.startActivityFromProvider(this, intent, i);
        }
    }

    public MessageInputFragment getCurrentFragment() {
        return this.mFragment;
    }

    public void publish(MessageContent messageContent) {
        publish(messageContent, null);
    }

    public void publish(MessageContent messageContent, ResultCallback<Message> resultCallback) {
        if (messageContent == null) {
            RLog.m19424w(TAG, "publish content is null");
        } else if (this.mCurrentConversation == null || TextUtils.isEmpty(this.mCurrentConversation.getTargetId()) || this.mCurrentConversation.getConversationType() == null) {
            Log.e(TAG, "the conversation hasn't been created yet!!!");
        } else {
            RongIM.getInstance().sendMessage(Message.obtain(this.mCurrentConversation.getTargetId(), this.mCurrentConversation.getConversationType(), messageContent), null, null, new C51831());
        }
    }

    public void onTypingMessage(String str) {
        if (TypingMessageManager.getInstance().isShowMessageTyping() && RongIMClient.getInstance() != null) {
            RongIMClient.getInstance().sendTypingStatus(this.mCurrentConversation.getConversationType(), this.mCurrentConversation.getTargetId(), str);
        }
    }

    public void onAttached(MessageInputFragment messageInputFragment, InputView inputView) {
        this.mFragment = messageInputFragment;
        this.mCurrentView = inputView;
    }

    public void onDetached() {
        this.mFragment = null;
        this.mCurrentView = null;
    }

    public InputView getInputView() {
        return this.mCurrentView;
    }
}
