package io.rong.imkit.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.AudioPlayManager;
import io.rong.imkit.manager.AudioRecordManager;
import io.rong.imkit.manager.SendImageManager;
import io.rong.imkit.mention.RongMentionManager;
import io.rong.imkit.model.ConversationInfo;
import io.rong.imkit.widget.InputView.IInputBoardListener;
import io.rong.imkit.widget.InputView.OnInfoButtonClick;
import io.rong.imkit.widget.SingleChoiceDialog;
import io.rong.imlib.CustomServiceConfig;
import io.rong.imlib.ICustomServiceListener;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.CSGroupItem;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.CustomServiceMode;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceMenu.PublicServiceMenuItemType;
import io.rong.message.PublicServiceCommandMessage;
import io.rong.push.RongPushClient;
import java.util.ArrayList;
import java.util.List;

public class ConversationFragment extends DispatchResultFragment implements OnScrollListener {
    private static final String TAG = "ConversationFragment";
    private boolean committing = false;
    ICustomServiceListener customServiceListener = new C50236();
    private long enterTime;
    private boolean evaluate = true;
    private IInputBoardListener inputBoardListener;
    ConversationType mConversationType;
    ConversationInfo mCurrentConversationInfo;
    private CSCustomServiceInfo mCustomUserInfo;
    MessageInputFragment mInputFragment;
    MessageListFragment mListFragment;
    String mTargetId;
    private OnInfoButtonClick onInfoButtonClick;
    private boolean resolved = true;
    private boolean robotType = true;
    private int source = 0;

    /* renamed from: io.rong.imkit.fragment.ConversationFragment$1 */
    class C50181 extends RongIMClient$OperationCallback {
        C50181() {
        }

        public void onSuccess() {
            RLog.m19422i(ConversationFragment.TAG, "joinChatRoom onSuccess : " + ConversationFragment.this.mTargetId);
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            RLog.m19420e(ConversationFragment.TAG, "joinChatRoom onError : " + rongIMClient$ErrorCode);
            if (ConversationFragment.this.getActivity() != null) {
                ConversationFragment.this.csWarning(ConversationFragment.this.getString(C4974R.string.rc_join_chatroom_failure));
            }
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationFragment$2 */
    class C50192 extends RongIMClient$OperationCallback {
        C50192() {
        }

        public void onSuccess() {
            RLog.m19422i(ConversationFragment.TAG, "joinExistChatRoom onSuccess : " + ConversationFragment.this.mTargetId);
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            RLog.m19420e(ConversationFragment.TAG, "joinExistChatRoom onError : " + rongIMClient$ErrorCode);
            if (ConversationFragment.this.getActivity() != null) {
                ConversationFragment.this.csWarning(ConversationFragment.this.getString(C4974R.string.rc_join_chatroom_failure));
            }
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationFragment$3 */
    class C50203 implements ISendMessageCallback {
        C50203() {
        }

        public void onAttached(Message message) {
        }

        public void onSuccess(Message message) {
        }

        public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationFragment$4 */
    class C50214 implements OnClickListener {
        C50214() {
        }

        public void onClick(View view) {
            RongIMClient.getInstance().switchToHumanMode(ConversationFragment.this.mTargetId);
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationFragment$6 */
    class C50236 implements ICustomServiceListener {
        C50236() {
        }

        public void onSuccess(CustomServiceConfig customServiceConfig) {
            if (customServiceConfig.isBlack) {
                ConversationFragment.this.onCustomServiceWarning(ConversationFragment.this.getString(C4974R.string.rc_blacklist_prompt), false);
            }
            if (customServiceConfig.robotSessionNoEva) {
                ConversationFragment.this.evaluate = false;
                if (ConversationFragment.this.mListFragment != null) {
                    ConversationFragment.this.mListFragment.setNeedEvaluateForRobot(true);
                }
            }
        }

        public void onError(int i, String str) {
            ConversationFragment.this.onCustomServiceWarning(str, false);
        }

        public void onModeChanged(CustomServiceMode customServiceMode) {
            ConversationFragment.this.mInputFragment.setInputProviderType(customServiceMode);
            if (customServiceMode.equals(CustomServiceMode.CUSTOM_SERVICE_MODE_HUMAN) || customServiceMode.equals(CustomServiceMode.CUSTOM_SERVICE_MODE_HUMAN_FIRST)) {
                ConversationFragment.this.robotType = false;
                ConversationFragment.this.evaluate = true;
            } else if (customServiceMode.equals(CustomServiceMode.CUSTOM_SERVICE_MODE_NO_SERVICE)) {
                ConversationFragment.this.evaluate = false;
            }
            if (ConversationFragment.this.mListFragment != null) {
                ConversationFragment.this.mListFragment.setRobotMode(ConversationFragment.this.robotType);
            }
        }

        public void onQuit(String str) {
            if (!ConversationFragment.this.committing) {
                ConversationFragment.this.onCustomServiceWarning(str, true);
            }
        }

        public void onPullEvaluation(String str) {
            if (!ConversationFragment.this.committing) {
                ConversationFragment.this.onCustomServiceEvaluation(true, str, ConversationFragment.this.robotType, ConversationFragment.this.evaluate);
            }
        }

        public void onSelectGroup(List<CSGroupItem> list) {
            ConversationFragment.this.showSingleSelectDialog(list);
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationFragment$7 */
    class C50257 implements Runnable {

        /* renamed from: io.rong.imkit.fragment.ConversationFragment$7$1 */
        class C50241 extends RongIMClient$OperationCallback {
            C50241() {
            }

            public void onSuccess() {
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            }
        }

        C50257() {
        }

        public void run() {
            RongIM.getInstance().quitChatRoom(ConversationFragment.this.mTargetId, new C50241());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RongPushClient.clearAllPushNotifications(RongContext.getInstance());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C4974R.layout.rc_fr_conversation, viewGroup, false);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onResume() {
        RongPushClient.clearAllPushNotifications(RongContext.getInstance());
        super.onResume();
    }

    public void setOnInfoButtonClick(OnInfoButtonClick onInfoButtonClick) {
        this.onInfoButtonClick = onInfoButtonClick;
        if (this.mInputFragment != null) {
            this.mInputFragment.setOnInfoButtonClick(onInfoButtonClick);
        }
    }

    public void setInputBoardListener(IInputBoardListener iInputBoardListener) {
        this.inputBoardListener = iInputBoardListener;
        if (this.mInputFragment != null) {
            this.mInputFragment.setInputBoardListener(iInputBoardListener);
        }
    }

    protected void initFragment(Uri uri) {
        RLog.m19419d(TAG, "initFragment : " + uri + ",this=" + this);
        if (uri != null) {
            this.mConversationType = ConversationType.valueOf(uri.getLastPathSegment().toUpperCase());
            this.mTargetId = uri.getQueryParameter("targetId");
            if (!(!this.mConversationType.equals(ConversationType.CUSTOMER_SERVICE) || getActivity() == null || getActivity().getIntent() == null || getActivity().getIntent().getData() == null)) {
                this.mCustomUserInfo = (CSCustomServiceInfo) getActivity().getIntent().getParcelableExtra("customServiceInfo");
            }
            this.mCurrentConversationInfo = ConversationInfo.obtain(this.mConversationType, this.mTargetId);
            RongContext.getInstance().registerConversationInfo(this.mCurrentConversationInfo);
            this.mListFragment = (MessageListFragment) getChildFragmentManager().findFragmentById(16908298);
            this.mInputFragment = (MessageInputFragment) getChildFragmentManager().findFragmentById(16908311);
            if (this.mListFragment == null) {
                this.mListFragment = new MessageListFragment();
            }
            if (this.mInputFragment == null) {
                this.mInputFragment = new MessageInputFragment();
            }
            if (this.mListFragment.getUri() == null) {
                this.mListFragment.setUri(uri);
            }
            if (this.mInputFragment.getUri() == null) {
                this.mInputFragment.setUri(uri);
            }
            this.mListFragment.setOnScrollListener(this);
            if (this.mConversationType.equals(ConversationType.CHATROOM)) {
                boolean z = getActivity() != null && getActivity().getIntent().getBooleanExtra("createIfNotExist", true);
                int integer = getResources().getInteger(C4974R.integer.rc_chatroom_first_pull_message_count);
                if (z) {
                    RongIMClient.getInstance().joinChatRoom(this.mTargetId, integer, new C50181());
                } else {
                    RongIMClient.getInstance().joinExistChatRoom(this.mTargetId, integer, new C50192());
                }
            } else if (this.mConversationType == ConversationType.APP_PUBLIC_SERVICE || this.mConversationType == ConversationType.PUBLIC_SERVICE) {
                MessageContent publicServiceCommandMessage = new PublicServiceCommandMessage();
                publicServiceCommandMessage.setCommand(PublicServiceMenuItemType.Entry.getMessage());
                RongIMClient.getInstance().sendMessage(Message.obtain(this.mTargetId, this.mConversationType, publicServiceCommandMessage), null, null, new C50203());
            } else if (this.mConversationType.equals(ConversationType.CUSTOMER_SERVICE)) {
                this.enterTime = System.currentTimeMillis();
                this.mInputFragment.setOnRobotSwitcherListener(new C50214());
                RongIMClient.getInstance().startCustomService(this.mTargetId, this.customServiceListener, this.mCustomUserInfo);
            }
            try {
                if (!RongContext.getInstance().getResources().getBoolean(C4974R.bool.rc_enable_mentioned_message)) {
                    return;
                }
                if (this.mConversationType.equals(ConversationType.DISCUSSION) || this.mConversationType.equals(ConversationType.GROUP)) {
                    RongMentionManager.getInstance().createInstance(this.mConversationType, this.mTargetId, this.mListFragment.getAdapter(), this.mInputFragment.getMentionInputProvider());
                }
            } catch (NotFoundException e) {
                RLog.m19420e(TAG, "Resource not found!");
            }
        }
    }

    private void showSingleSelectDialog(final List<CSGroupItem> list) {
        if (getActivity() != null) {
            List arrayList = new ArrayList();
            arrayList.clear();
            for (int i = 0; i < list.size(); i++) {
                if (((CSGroupItem) list.get(i)).getOnline()) {
                    arrayList.add(((CSGroupItem) list.get(i)).getName());
                }
            }
            if (arrayList.size() == 0) {
                RongIMClient.getInstance().selectCustomServiceGroup(this.mTargetId, null);
                return;
            }
            final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity(), arrayList);
            singleChoiceDialog.setTitle(getActivity().getResources().getString(C4974R.string.rc_cs_select_group));
            singleChoiceDialog.setOnOKButtonListener(new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    RongIMClient.getInstance().selectCustomServiceGroup(ConversationFragment.this.mTargetId, ((CSGroupItem) list.get(singleChoiceDialog.getSelectItem())).getId());
                }
            });
            singleChoiceDialog.show();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mInputFragment = (MessageInputFragment) getChildFragmentManager().findFragmentById(16908311);
        if (this.mInputFragment != null) {
            this.mInputFragment.setOnInfoButtonClick(this.onInfoButtonClick);
            this.mInputFragment.setInputBoardListener(this.inputBoardListener);
        }
    }

    public void onDestroyView() {
        RongContext.getInstance().unregisterConversationInfo(this.mCurrentConversationInfo);
        super.onDestroyView();
    }

    public void onDestroy() {
        RongContext.getInstance().getEventBus().unregister(this);
        if (this.mConversationType != null) {
            if (this.mConversationType.equals(ConversationType.CHATROOM)) {
                SendImageManager.getInstance().cancelSendingImages(this.mConversationType, this.mTargetId);
            }
            RongContext.getInstance().executorBackground(new C50257());
            if (this.mConversationType.equals(ConversationType.CUSTOMER_SERVICE)) {
                boolean z;
                try {
                    z = RongContext.getInstance().getResources().getBoolean(C4974R.bool.rc_stop_custom_service_when_quit);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    z = true;
                }
                if (z) {
                    RongIMClient.getInstance().stopCustomService(this.mTargetId);
                }
            }
        }
        AudioPlayManager.getInstance().stopPlay();
        AudioRecordManager.getInstance().stopRecord();
        try {
            if (RongContext.getInstance().getResources().getBoolean(C4974R.bool.rc_enable_mentioned_message) && (this.mConversationType.equals(ConversationType.DISCUSSION) || this.mConversationType.equals(ConversationType.GROUP))) {
                RongMentionManager.getInstance().destroyInstance();
            }
        } catch (NotFoundException e2) {
        }
        super.onDestroy();
    }

    public boolean onBackPressed() {
        if (this.mConversationType == null || !this.mConversationType.equals(ConversationType.CUSTOMER_SERVICE)) {
            return false;
        }
        return onCustomServiceEvaluation(false, "", this.robotType, this.evaluate);
    }

    public boolean handleMessage(android.os.Message message) {
        return false;
    }

    private void csWarning(String str) {
        if (getActivity() != null) {
            Builder builder = new Builder(getActivity());
            builder.setCancelable(false);
            final AlertDialog create = builder.create();
            create.show();
            Window window = create.getWindow();
            window.setContentView(C4974R.layout.rc_cs_alert_warning);
            ((TextView) window.findViewById(C4974R.id.rc_cs_msg)).setText(str);
            window.findViewById(C4974R.id.rc_btn_ok).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    create.dismiss();
                    FragmentManager childFragmentManager = ConversationFragment.this.getChildFragmentManager();
                    if (childFragmentManager.getBackStackEntryCount() > 0) {
                        childFragmentManager.popBackStack();
                    } else {
                        ConversationFragment.this.getActivity().finish();
                    }
                }
            });
        }
    }

    public void onCustomServiceWarning(String str, final boolean z) {
        if (getActivity() != null) {
            Builder builder = new Builder(getActivity());
            builder.setCancelable(false);
            final AlertDialog create = builder.create();
            create.show();
            Window window = create.getWindow();
            window.setContentView(C4974R.layout.rc_cs_alert_warning);
            ((TextView) window.findViewById(C4974R.id.rc_cs_msg)).setText(str);
            window.findViewById(C4974R.id.rc_btn_ok).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    create.dismiss();
                    if (z) {
                        ConversationFragment.this.onCustomServiceEvaluation(false, "", ConversationFragment.this.robotType, z);
                        return;
                    }
                    FragmentManager childFragmentManager = ConversationFragment.this.getChildFragmentManager();
                    if (childFragmentManager.getBackStackEntryCount() > 0) {
                        childFragmentManager.popBackStack();
                    } else {
                        ConversationFragment.this.getActivity().finish();
                    }
                }
            });
        }
    }

    public boolean onCustomServiceEvaluation(boolean z, final String str, final boolean z2, boolean z3) {
        int i = 0;
        if (!z3 || getActivity() == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 60;
        try {
            i2 = RongContext.getInstance().getResources().getInteger(C4974R.integer.rc_custom_service_evaluation_interval);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (currentTimeMillis - this.enterTime >= ((long) (i2 * 1000)) || z) {
            this.committing = true;
            Builder builder = new Builder(getActivity());
            builder.setCancelable(false);
            final AlertDialog create = builder.create();
            create.show();
            Window window = create.getWindow();
            final LinearLayout linearLayout;
            if (z2) {
                window.setContentView(C4974R.layout.rc_cs_alert_robot_evaluation);
                linearLayout = (LinearLayout) window.findViewById(C4974R.id.rc_cs_yes_no);
                if (this.resolved) {
                    linearLayout.getChildAt(0).setSelected(true);
                    linearLayout.getChildAt(1).setSelected(false);
                } else {
                    linearLayout.getChildAt(0).setSelected(false);
                    linearLayout.getChildAt(1).setSelected(true);
                }
                while (i < linearLayout.getChildCount()) {
                    linearLayout.getChildAt(i).setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            view.setSelected(true);
                            if (linearLayout.indexOfChild(view) == 0) {
                                linearLayout.getChildAt(1).setSelected(false);
                                ConversationFragment.this.resolved = true;
                                return;
                            }
                            ConversationFragment.this.resolved = false;
                            linearLayout.getChildAt(0).setSelected(false);
                        }
                    });
                    i++;
                }
            } else {
                window.setContentView(C4974R.layout.rc_cs_alert_human_evaluation);
                linearLayout = (LinearLayout) window.findViewById(C4974R.id.rc_cs_stars);
                while (i < linearLayout.getChildCount()) {
                    View childAt = linearLayout.getChildAt(i);
                    if (i < this.source) {
                        childAt.setSelected(true);
                    }
                    childAt.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            int indexOfChild = linearLayout.indexOfChild(view);
                            int childCount = linearLayout.getChildCount();
                            ConversationFragment.this.source = indexOfChild + 1;
                            if (view.isSelected()) {
                                for (indexOfChild++; indexOfChild < childCount; indexOfChild++) {
                                    linearLayout.getChildAt(indexOfChild).setSelected(false);
                                }
                                return;
                            }
                            while (indexOfChild >= 0) {
                                linearLayout.getChildAt(indexOfChild).setSelected(true);
                                indexOfChild--;
                            }
                        }
                    });
                    i++;
                }
            }
            window.findViewById(C4974R.id.rc_btn_cancel).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ConversationFragment.this.committing = false;
                    create.dismiss();
                    FragmentManager childFragmentManager = ConversationFragment.this.getChildFragmentManager();
                    if (childFragmentManager.getBackStackEntryCount() > 0) {
                        childFragmentManager.popBackStack();
                    } else {
                        ConversationFragment.this.getActivity().finish();
                    }
                }
            });
            window.findViewById(C4974R.id.rc_btn_ok).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (z2) {
                        RongIMClient.getInstance().evaluateCustomService(ConversationFragment.this.mTargetId, ConversationFragment.this.resolved, "");
                    } else if (ConversationFragment.this.source > 0) {
                        RongIMClient.getInstance().evaluateCustomService(ConversationFragment.this.mTargetId, ConversationFragment.this.source, null, str);
                    }
                    create.dismiss();
                    ConversationFragment.this.committing = false;
                    FragmentManager childFragmentManager = ConversationFragment.this.getChildFragmentManager();
                    if (childFragmentManager.getBackStackEntryCount() > 0) {
                        childFragmentManager.popBackStack();
                    } else {
                        ConversationFragment.this.getActivity().finish();
                    }
                }
            });
            return true;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        if (!(inputMethodManager == null || !inputMethodManager.isActive() || getActivity().getCurrentFocus() == null || getActivity().getCurrentFocus().getWindowToken() == null)) {
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (childFragmentManager.getBackStackEntryCount() > 0) {
            childFragmentManager.popBackStack();
            return false;
        }
        getActivity().finish();
        return false;
    }
}
