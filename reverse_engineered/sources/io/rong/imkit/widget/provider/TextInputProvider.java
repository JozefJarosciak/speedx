package io.rong.imkit.widget.provider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.fragment.MessageInputFragment;
import io.rong.imkit.mention.ITextInputListener;
import io.rong.imkit.model.Draft;
import io.rong.imkit.utils.AndroidEmoji;
import io.rong.imkit.widget.InputView;
import io.rong.imkit.widget.InputView.Event;
import io.rong.imkit.widget.RongEmojiPager;
import io.rong.imkit.widget.RongEmojiPager.OnEmojiClickListener;
import io.rong.imkit.widget.provider.InputProvider.MainInputProvider;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.TypingMessage.TypingMessageManager;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;

public class TextInputProvider extends MainInputProvider implements TextWatcher, OnClickListener, OnFocusChangeListener, OnKeyListener, OnLongClickListener {
    private static final String TAG = "TextInputProvider";
    private boolean flag = true;
    volatile InputView mInputView;
    private ITextInputListener mTextInputListener;
    private TextWatcher mTextWatcher;

    /* renamed from: io.rong.imkit.widget.provider.TextInputProvider$3 */
    class C51913 implements OnEditorActionListener {
        C51913() {
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 6) {
                return false;
            }
            ((InputMethodManager) textView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(textView.getWindowToken(), 0);
            return true;
        }
    }

    /* renamed from: io.rong.imkit.widget.provider.TextInputProvider$4 */
    class C51924 extends ResultCallback<String> {
        C51924() {
        }

        public void onSuccess(String str) {
            if (TextInputProvider.this.mInputView == null) {
                RLog.m19420e(TextInputProvider.TAG, "inputView is null!");
                return;
            }
            ViewHolder viewHolder = (ViewHolder) TextInputProvider.this.mInputView.getTag();
            if (str != null && viewHolder != null && viewHolder.mEdit != null) {
                TextInputProvider.this.flag = false;
                viewHolder.mEdit.setText(str);
                viewHolder.mEdit.setSelection(str.length());
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    public class ViewHolder {
        public FrameLayout mBack;
        public Button mButton;
        public EditText mEdit;
        public RongEmojiPager mEmojiPager;
        public TextWatcher mExtraTextWatcher;
        public ImageView mSmile;
    }

    public void onInputResume(InputView inputView, Conversation conversation) {
        this.mInputView = inputView;
        setCurrentConversation(conversation);
    }

    public void onInputPause() {
    }

    public TextInputProvider(RongContext rongContext) {
        super(rongContext);
        RLog.m19419d(TAG, TAG);
    }

    public void onAttached(MessageInputFragment messageInputFragment, InputView inputView) {
        RLog.m19419d(TAG, "onAttached");
        super.onAttached(messageInputFragment, inputView);
    }

    public void onDetached() {
        RLog.m19419d(TAG, "Detached");
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        final ConversationType conversationType = getCurrentConversation().getConversationType();
        final String targetId = getCurrentConversation().getTargetId();
        if (viewHolder == null || viewHolder.mEdit == null || TextUtils.isEmpty(viewHolder.mEdit.getText())) {
            RongIMClient.getInstance().clearTextMessageDraft(conversationType, targetId, new ResultCallback<Boolean>() {
                public void onSuccess(Boolean bool) {
                    TextInputProvider.this.getContext().getEventBus().post(new Draft(targetId, Integer.valueOf(conversationType.getValue()), null, null));
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            });
        } else {
            final String obj = viewHolder.mEdit.getText().toString();
            RongIMClient.getInstance().saveTextMessageDraft(conversationType, targetId, obj, new ResultCallback<Boolean>() {
                public void onSuccess(Boolean bool) {
                    TextInputProvider.this.getContext().getEventBus().post(new Draft(targetId, Integer.valueOf(conversationType.getValue()), obj, null));
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            });
        }
        if (viewHolder != null) {
            viewHolder.mEmojiPager = null;
        }
        RongContext.getInstance().getEventBus().unregister(this);
        super.onDetached();
    }

    public Drawable obtainSwitchDrawable(Context context) {
        return context.getResources().getDrawable(C4974R.drawable.rc_ic_keyboard);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, InputView inputView) {
        ViewHolder viewHolder;
        RLog.m19419d(TAG, "onCreateView");
        View inflate = layoutInflater.inflate(C4974R.layout.rc_wi_txt_provider, viewGroup);
        ViewHolder viewHolder2 = (ViewHolder) inputView.getTag();
        if (viewHolder2 == null) {
            viewHolder = new ViewHolder();
        } else {
            viewHolder = viewHolder2;
        }
        viewHolder.mSmile = (ImageView) inflate.findViewById(16908294);
        viewHolder.mEdit = (EditText) inflate.findViewById(16908291);
        viewHolder.mBack = (FrameLayout) inflate.findViewById(C4974R.id.rc_frame);
        inputView.setTag(viewHolder);
        if (inputView.getToggleLayout().getVisibility() == 0) {
            viewHolder.mButton = (Button) layoutInflater.inflate(C4974R.layout.rc_wi_text_btn, inputView.getToggleLayout(), false);
            inputView.getToggleLayout().addView(viewHolder.mButton);
        }
        if (inputView.getToggleLayout().getVisibility() != 0 || viewHolder.mButton == null) {
            viewHolder.mButton = (Button) inflate.findViewById(16908313);
        }
        viewHolder.mEdit.addTextChangedListener(this);
        viewHolder.mEdit.setOnFocusChangeListener(this);
        viewHolder.mSmile.setOnClickListener(this);
        viewHolder.mEdit.setOnClickListener(this);
        viewHolder.mEdit.setOnLongClickListener(this);
        viewHolder.mEdit.setOnEditorActionListener(new C51913());
        viewHolder.mEdit.setOnKeyListener(this);
        viewHolder.mBack.setOnClickListener(this);
        if (this.mTextWatcher != null) {
            viewHolder.mExtraTextWatcher = this.mTextWatcher;
        }
        this.mInputView = inputView;
        viewHolder.mButton.setOnClickListener(this);
        if (!RongContext.getInstance().getEventBus().isRegistered(this)) {
            RongContext.getInstance().getEventBus().register(this);
        }
        RongIMClient.getInstance().getTextMessageDraft(getCurrentConversation().getConversationType(), getCurrentConversation().getTargetId(), new C51924());
        return inflate;
    }

    public void onClick(View view) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        final ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        if (viewHolder == null) {
            RLog.m19420e(TAG, "holder is null!");
        } else if (viewHolder.mSmile.equals(view)) {
            if (viewHolder.mEmojiPager == null) {
                viewHolder.mEmojiPager = new RongEmojiPager(this.mInputView.getExtendLayout());
                viewHolder.mEmojiPager.setOnEmojiClickListener(new OnEmojiClickListener() {
                    public void onEmojiClick(String str) {
                        if (str.equals("/DEL")) {
                            viewHolder.mEdit.dispatchKeyEvent(new KeyEvent(0, 67));
                            return;
                        }
                        viewHolder.mEdit.getText().insert(viewHolder.mEdit.getSelectionStart(), str);
                    }
                });
                if (viewHolder.mEdit != null) {
                    viewHolder.mEdit.requestFocus();
                }
                this.mInputView.onEmojiProviderActive(getContext());
                this.mInputView.setExtendLayoutVisibility(0);
                if (this.mInputView.getExtendLayout().getVisibility() == 0) {
                    viewHolder.mSmile.setImageResource(C4974R.drawable.rc_ic_smiley_selected);
                    viewHolder.mBack.setBackgroundResource(C4974R.drawable.rc_bg_text_hover);
                }
            } else if (this.mInputView.getExtendLayout().getVisibility() == 8) {
                this.mInputView.onEmojiProviderActive(getContext());
                this.mInputView.setExtendLayoutVisibility(0);
                if (this.mInputView.getExtendLayout().getVisibility() == 0) {
                    viewHolder.mSmile.setImageResource(C4974R.drawable.rc_ic_smiley_selected);
                    viewHolder.mBack.setBackgroundResource(C4974R.drawable.rc_bg_text_hover);
                }
            } else if (this.mInputView.getExtendLayout().getVisibility() == 0) {
                viewHolder.mBack.setBackgroundResource(C4974R.drawable.rc_bg_text_hover);
                viewHolder.mSmile.setImageResource(C4974R.drawable.rc_ic_smiley_selected);
            } else {
                this.mInputView.onProviderInactive(getContext());
            }
        } else if (view.equals(viewHolder.mButton)) {
            if (TextUtils.isEmpty(viewHolder.mEdit.getText().toString().trim())) {
                viewHolder.mEdit.getText().clear();
                viewHolder.mEdit.setText("");
                return;
            }
            MessageContent obtain = TextMessage.obtain(viewHolder.mEdit.getText().toString());
            if (this.mTextInputListener != null) {
                MentionedInfo onSendButtonClick = this.mTextInputListener.onSendButtonClick();
                if (onSendButtonClick != null) {
                    obtain.setMentionedInfo(onSendButtonClick);
                }
            }
            publish(obtain);
            viewHolder.mEdit.getText().clear();
            viewHolder.mEdit.setText("");
        } else if (viewHolder.mEdit.equals(view)) {
            viewHolder.mBack.setBackgroundResource(C4974R.drawable.rc_bg_text_hover);
            viewHolder.mSmile.setImageResource(C4974R.drawable.rc_ic_smiley);
            this.mInputView.onProviderActive(getContext());
        } else if (view.equals(viewHolder.mBack)) {
            viewHolder.mBack.setBackgroundResource(C4974R.drawable.rc_bg_text_hover);
            viewHolder.mSmile.setImageResource(C4974R.drawable.rc_ic_smiley);
            this.mInputView.onProviderActive(getContext());
        }
    }

    public boolean onLongClick(View view) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
        } else if (this.mInputView != null && this.mInputView.getExtendLayout().getVisibility() == 0) {
            this.mInputView.onProviderInactive(getContext());
            this.mInputView.setExtendLayoutVisibility(8);
        }
        return false;
    }

    public void onActive(Context context) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        if (viewHolder != null && viewHolder.mEdit != null) {
            viewHolder.mEdit.requestFocus();
            ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(viewHolder.mEdit, 0);
        }
    }

    public void onInactive(Context context) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        if (viewHolder != null && viewHolder.mEdit != null) {
            if (this.mInputView.getExtendLayout().getVisibility() == 8) {
                viewHolder.mSmile.setImageResource(C4974R.drawable.rc_ic_smiley);
                if (viewHolder.mEdit.hasFocus()) {
                    viewHolder.mBack.setBackgroundResource(C4974R.drawable.rc_bg_text_hover);
                } else {
                    viewHolder.mBack.setBackgroundResource(C4974R.drawable.rc_bg_text_normal);
                }
            }
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(viewHolder.mEdit.getWindowToken(), 0);
        }
    }

    public void onSwitch(Context context) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        if (viewHolder != null) {
            viewHolder.mButton.setVisibility(8);
            onInactive(context);
        }
    }

    public void onFocusChange(View view, boolean z) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
        } else if (this.mInputView != null && z) {
            this.mInputView.setExtendInputsVisibility(8);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        if (viewHolder != null && viewHolder.mExtraTextWatcher != null) {
            viewHolder.mExtraTextWatcher.beforeTextChanged(charSequence, i, i2, i3);
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        if (viewHolder != null) {
            if (viewHolder.mExtraTextWatcher != null) {
                viewHolder.mExtraTextWatcher.onTextChanged(charSequence, i, i2, i3);
            }
            ConversationType conversationType = getCurrentConversation().getConversationType();
            String targetId = getCurrentConversation().getTargetId();
            if (this.mTextInputListener != null && this.flag && (conversationType.equals(ConversationType.GROUP) || conversationType.equals(ConversationType.DISCUSSION))) {
                if (i3 == 0) {
                    this.mTextInputListener.onTextEdit(conversationType, targetId, i + i2, -i2, charSequence.toString());
                } else {
                    this.mTextInputListener.onTextEdit(conversationType, targetId, i, i3, charSequence.toString());
                }
            }
            this.flag = true;
            if (viewHolder.mButton == null) {
                return;
            }
            if (TextUtils.isEmpty(charSequence)) {
                viewHolder.mButton.setVisibility(8);
            } else if (this.mInputView.getToggleLayout().getVisibility() == 0) {
                if (this.mInputView.getToggleLayout().findViewById(16908313) == null) {
                    if (viewHolder.mButton != null) {
                        viewHolder.mButton.setVisibility(8);
                    }
                    viewHolder.mButton = (Button) LayoutInflater.from(getContext()).inflate(C4974R.layout.rc_wi_text_btn, this.mInputView.getToggleLayout(), false);
                    this.mInputView.getToggleLayout().addView(viewHolder.mButton);
                    viewHolder.mButton.setOnClickListener(this);
                }
                viewHolder.mButton.setVisibility(0);
            } else {
                viewHolder.mButton.setVisibility(0);
            }
        }
    }

    public void afterTextChanged(Editable editable) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        if (viewHolder != null) {
            if (editable.toString().length() > 0 && TypingMessageManager.getInstance().isShowMessageTyping()) {
                onTypingMessage(((MessageTag) TextMessage.class.getAnnotation(MessageTag.class)).value());
            }
            if (AndroidEmoji.isEmoji(editable.toString())) {
                int selectionStart = viewHolder.mEdit.getSelectionStart();
                int selectionEnd = viewHolder.mEdit.getSelectionEnd();
                viewHolder.mEdit.removeTextChangedListener(this);
                viewHolder.mEdit.setText(AndroidEmoji.ensure(editable.toString()));
                viewHolder.mEdit.addTextChangedListener(this);
                viewHolder.mEdit.setSelection(selectionStart, selectionEnd);
            }
            if (viewHolder.mExtraTextWatcher != null) {
                viewHolder.mExtraTextWatcher.afterTextChanged(editable);
            }
            RLog.m19419d(TAG, "afterTextChanged " + editable.toString());
        }
    }

    public void setEditTextContent(CharSequence charSequence) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        if (viewHolder != null && viewHolder.mEdit != null && charSequence != null) {
            viewHolder.mEdit.setText(charSequence);
            viewHolder.mEdit.setSelection(charSequence.length());
        }
    }

    public void setEditTextChangedListener(TextWatcher textWatcher) {
        this.mTextWatcher = textWatcher;
    }

    public void onEventMainThread(Event event) {
        if (this.mInputView == null) {
            RLog.m19420e(TAG, "inputView is null!");
            return;
        }
        ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
        if (viewHolder != null && event == Event.CLICK && this.mInputView.getExtendLayout().getVisibility() == 8) {
            viewHolder.mSmile.setImageResource(C4974R.drawable.rc_ic_smiley);
            viewHolder.mBack.setBackgroundResource(C4974R.drawable.rc_bg_text_normal);
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 67 && keyEvent.getAction() == 0) {
            ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
            if (!(viewHolder == null || viewHolder.mEdit == null || this.mTextInputListener == null)) {
                this.mTextInputListener.onDeleteClick(getCurrentConversation().getConversationType(), getCurrentConversation().getTargetId(), viewHolder.mEdit, viewHolder.mEdit.getSelectionStart());
            }
        }
        return false;
    }

    public EditText getEditText() {
        if (this.mInputView != null) {
            ViewHolder viewHolder = (ViewHolder) this.mInputView.getTag();
            if (viewHolder != null) {
                return viewHolder.mEdit;
            }
        }
        return null;
    }

    public void setTextInputListener(ITextInputListener iTextInputListener) {
        this.mTextInputListener = iTextInputListener;
    }
}
