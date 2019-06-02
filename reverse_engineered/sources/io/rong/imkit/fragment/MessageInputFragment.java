package io.rong.imkit.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.alipay.sdk.cons.C0844a;
import com.beastbikes.framework.ui.android.WebActivity;
import io.rong.common.RLog;
import io.rong.eventbus.EventBus;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.model.Event.InputViewEvent;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.InputView;
import io.rong.imkit.widget.InputView.IInputBoardListener;
import io.rong.imkit.widget.InputView.OnInfoButtonClick;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imkit.widget.provider.TextInputProvider;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.CustomServiceMode;
import io.rong.imlib.model.PublicServiceMenu;
import io.rong.imlib.model.PublicServiceProfile;

public class MessageInputFragment extends UriFragment implements OnClickListener {
    private static final String IS_SHOW_EXTEND_INPUTS = "isShowExtendInputs";
    private static final String TAG = "MessageInputFragment";
    Conversation mConversation;
    InputView mInput;

    /* renamed from: io.rong.imkit.fragment.MessageInputFragment$1 */
    class C50391 implements Runnable {
        C50391() {
        }

        public void run() {
            MessageInputFragment.this.mInput.setExtendInputsVisibility(0);
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageInputFragment$2 */
    class C50402 implements Runnable {
        C50402() {
        }

        public void run() {
            MessageInputFragment.this.mInput.setExtendInputsVisibility(8);
        }
    }

    /* renamed from: io.rong.imkit.fragment.MessageInputFragment$3 */
    class C50413 extends ResultCallback<PublicServiceProfile> {
        C50413() {
        }

        public void onSuccess(PublicServiceProfile publicServiceProfile) {
            RongUserInfoManager.getInstance().setPublicServiceProfile(publicServiceProfile);
            PublicServiceMenu menu = publicServiceProfile.getMenu();
            if (menu == null || menu.getMenuItems() == null || menu.getMenuItems().size() <= 0) {
                MessageInputFragment.this.mInput.setInputProvider(RongContext.getInstance().getPrimaryInputProvider(), RongContext.getInstance().getSecondaryInputProvider());
            } else {
                MessageInputFragment.this.mInput.setInputProviderEx(RongContext.getInstance().getPrimaryInputProvider(), RongContext.getInstance().getSecondaryInputProvider(), RongContext.getInstance().getMenuInputProvider());
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            MessageInputFragment.this.mInput.setInputProvider(RongContext.getInstance().getPrimaryInputProvider(), RongContext.getInstance().getSecondaryInputProvider());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_fr_messageinput, viewGroup, false);
        this.mInput = (InputView) inflate.findViewById(C4974R.id.rc_input);
        EventBus.getDefault().register(this);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (RongContext.getInstance().getPrimaryInputProvider() == null) {
            throw new RuntimeException("MainInputProvider must not be null.");
        } else if (getUri() != null) {
            String queryParameter = getUri().getQueryParameter(IS_SHOW_EXTEND_INPUTS);
            if (queryParameter == null || !("true".equals(queryParameter) || C0844a.f2048d.equals(queryParameter))) {
                getHandler().postDelayed(new C50402(), 500);
            } else {
                getHandler().postDelayed(new C50391(), 500);
            }
        }
    }

    public void onPause() {
        super.onPause();
        if (RongContext.getInstance() != null && RongContext.getInstance().getPrimaryInputProvider() != null) {
            RongContext.getInstance().getPrimaryInputProvider().onInputPause();
        }
    }

    public void onResume() {
        super.onResume();
        if (RongContext.getInstance() != null) {
            if (RongContext.getInstance().getPrimaryInputProvider() != null) {
                RongContext.getInstance().getPrimaryInputProvider().onInputResume(this.mInput, this.mConversation);
            }
            if (RongContext.getInstance().getVoiceInputProvider() != null) {
                RongContext.getInstance().getVoiceInputProvider().onInputResume(this.mInput, this.mConversation);
            }
        }
    }

    public void setOnInfoButtonClick(OnInfoButtonClick onInfoButtonClick) {
        this.mInput.setOnInfoButtonClickListener(onInfoButtonClick);
    }

    public void setInputBoardListener(IInputBoardListener iInputBoardListener) {
        this.mInput.setInputBoardListener(iInputBoardListener);
    }

    private void setCurrentConversation(Conversation conversation) {
        RongContext.getInstance().getPrimaryInputProvider().setCurrentConversation(conversation);
        if (RongContext.getInstance().getSecondaryInputProvider() != null) {
            RongContext.getInstance().getSecondaryInputProvider().setCurrentConversation(conversation);
        }
        if (RongContext.getInstance().getMenuInputProvider() != null) {
            RongContext.getInstance().getMenuInputProvider().setCurrentConversation(conversation);
        }
        for (InputProvider currentConversation : RongContext.getInstance().getRegisteredExtendProviderList(this.mConversation.getConversationType())) {
            currentConversation.setCurrentConversation(conversation);
        }
        this.mInput.setExtendProvider(RongContext.getInstance().getRegisteredExtendProviderList(this.mConversation.getConversationType()), this.mConversation.getConversationType());
        for (InputProvider currentConversation2 : RongContext.getInstance().getRegisteredExtendProviderList(this.mConversation.getConversationType())) {
            currentConversation2.onAttached(this, this.mInput);
        }
        if (conversation.getConversationType().equals(ConversationType.APP_PUBLIC_SERVICE) || conversation.getConversationType().equals(ConversationType.PUBLIC_SERVICE)) {
            PublicServiceProfile publicServiceInfoFromCache = RongContext.getInstance().getPublicServiceInfoFromCache(ConversationKey.obtain(conversation.getTargetId(), conversation.getConversationType()).getKey());
            if (publicServiceInfoFromCache == null) {
                RongIM.getInstance().getPublicServiceProfile(PublicServiceType.setValue(conversation.getConversationType().getValue()), conversation.getTargetId(), new C50413());
            } else {
                PublicServiceMenu menu = publicServiceInfoFromCache.getMenu();
                if (menu == null || menu.getMenuItems() == null || menu.getMenuItems().size() <= 0) {
                    this.mInput.setInputProvider(RongContext.getInstance().getPrimaryInputProvider(), RongContext.getInstance().getSecondaryInputProvider());
                } else {
                    this.mInput.setInputProviderEx(RongContext.getInstance().getPrimaryInputProvider(), RongContext.getInstance().getSecondaryInputProvider(), RongContext.getInstance().getMenuInputProvider());
                }
            }
        } else if (conversation.getConversationType().equals(ConversationType.CUSTOMER_SERVICE)) {
            this.mInput.setInputProviderForCS(RongContext.getInstance().getPrimaryInputProvider(), RongContext.getInstance().getSecondaryInputProvider());
        } else {
            this.mInput.setInputProvider(RongContext.getInstance().getPrimaryInputProvider(), RongContext.getInstance().getSecondaryInputProvider());
        }
        RongContext.getInstance().getPrimaryInputProvider().onAttached(this, this.mInput);
        if (RongContext.getInstance().getSecondaryInputProvider() != null) {
            RongContext.getInstance().getSecondaryInputProvider().onAttached(this, this.mInput);
        }
    }

    public void setInputProviderType(CustomServiceMode customServiceMode) {
        switch (customServiceMode) {
            case CUSTOM_SERVICE_MODE_ROBOT:
                this.mInput.setOnlyRobotInputType();
                return;
            case CUSTOM_SERVICE_MODE_ROBOT_FIRST:
                this.mInput.setPriorRobotInputType();
                return;
            case CUSTOM_SERVICE_MODE_HUMAN_FIRST:
            case CUSTOM_SERVICE_MODE_HUMAN:
                this.mInput.setOnlyAdminInputType();
                return;
            case CUSTOM_SERVICE_MODE_NO_SERVICE:
                this.mInput.setNoServiceType();
                return;
            default:
                return;
        }
    }

    public void setOnRobotSwitcherListener(OnClickListener onClickListener) {
        this.mInput.setOnSwitcherListener(onClickListener);
    }

    protected void initFragment(Uri uri) {
        ConversationType valueOf = ConversationType.valueOf(uri.getLastPathSegment().toUpperCase());
        String queryParameter = uri.getQueryParameter("targetId");
        String queryParameter2 = uri.getQueryParameter(WebActivity.EXTRA_TITLE);
        if (valueOf != null) {
            this.mConversation = Conversation.obtain(valueOf, queryParameter, queryParameter2);
            if (this.mConversation != null) {
                setCurrentConversation(this.mConversation);
            }
        }
    }

    public TextInputProvider getMentionInputProvider() {
        return (TextInputProvider) RongContext.getInstance().getPrimaryInputProvider();
    }

    public void onClick(View view) {
    }

    public void onDestroyView() {
        RLog.m19419d(TAG, "onDestroyView the primary input provider is:" + RongContext.getInstance().getPrimaryInputProvider());
        RongContext.getInstance().getPrimaryInputProvider().onDetached();
        if (RongContext.getInstance().getSecondaryInputProvider() != null) {
            RongContext.getInstance().getSecondaryInputProvider().onDetached();
        }
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    public boolean onBackPressed() {
        return false;
    }

    private DispatchResultFragment getDispatchFragment(Fragment fragment) {
        if (fragment instanceof DispatchResultFragment) {
            return (DispatchResultFragment) fragment;
        }
        if (fragment.getParentFragment() != null) {
            return getDispatchFragment(fragment.getParentFragment());
        }
        throw new RuntimeException(fragment.getClass().getName() + " must has a parent fragment instance of DispatchFragment.");
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    public void startActivityFromProvider(InputProvider inputProvider, Intent intent, int i) {
        if (i == -1) {
            startActivityForResult(intent, -1);
        } else if ((i & -128) != 0) {
            throw new IllegalArgumentException("Can only use lower 7 bits for requestCode");
        } else {
            getDispatchFragment(this).startActivityForResult(this, intent, ((inputProvider.getIndex() + 1) << 7) + (i & 127));
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        int i3 = i >> 7;
        if (i3 != 0) {
            i3--;
            if (i3 > RongContext.getInstance().getRegisteredExtendProviderList(this.mConversation.getConversationType()).size() + 1) {
                RLog.m19424w(TAG, "onActivityResult Activity result provider index out of range: 0x" + Integer.toHexString(i));
                return;
            } else if (i3 == 0) {
                RongContext.getInstance().getPrimaryInputProvider().onActivityResult(i & 127, i2, intent);
                return;
            } else if (i3 == 1) {
                RongContext.getInstance().getSecondaryInputProvider().onActivityResult(i & 127, i2, intent);
                return;
            } else {
                ((ExtendProvider) RongContext.getInstance().getRegisteredExtendProviderList(this.mConversation.getConversationType()).get(i3 - 2)).onActivityResult(i & 127, i2, intent);
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onEventMainThread(InputViewEvent inputViewEvent) {
        if (inputViewEvent.isVisibility()) {
            this.mInput.setExtendInputsVisibility(0);
        } else {
            this.mInput.setExtendInputsVisibility(8);
        }
    }
}
