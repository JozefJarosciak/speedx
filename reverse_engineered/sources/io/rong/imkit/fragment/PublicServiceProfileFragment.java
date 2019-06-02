package io.rong.imkit.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM$PublicServiceBehaviorListener;
import io.rong.imkit.model.Event.PublicServiceFollowableEvent;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.LoadingDialogFragment;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.PublicServiceProfile;

public class PublicServiceProfileFragment extends DispatchResultFragment {
    public static final String AGS_PUBLIC_ACCOUNT_INFO = "arg_public_account_info";
    private TextView mAccountTV;
    private ConversationType mConversationType;
    private TextView mDescriptionTV;
    private Button mEnterBtn;
    private Button mFollowBtn;
    private LoadingDialogFragment mLoadingDialogFragment;
    private TextView mNameTV;
    private AsyncImageView mPortraitIV;
    PublicServiceProfile mPublicAccountInfo;
    private String mTargetId;
    private Button mUnfollowBtn;
    private String name;

    /* renamed from: io.rong.imkit.fragment.PublicServiceProfileFragment$1 */
    class C50591 extends ResultCallback<PublicServiceProfile> {
        C50591() {
        }

        public void onSuccess(PublicServiceProfile publicServiceProfile) {
            if (publicServiceProfile != null) {
                PublicServiceProfileFragment.this.initData(publicServiceProfile);
                RongUserInfoManager.getInstance().setPublicServiceProfile(publicServiceProfile);
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            RLog.m19420e("PublicServiceProfileFragment", "Failure to get data!!!");
        }
    }

    protected void initFragment(Uri uri) {
        if (getActivity().getIntent() != null) {
            this.mPublicAccountInfo = (PublicServiceProfile) getActivity().getIntent().getParcelableExtra(AGS_PUBLIC_ACCOUNT_INFO);
        }
        if (uri == null) {
            return;
        }
        if (this.mPublicAccountInfo == null) {
            this.mConversationType = ConversationType.valueOf(!TextUtils.isEmpty(uri.getLastPathSegment()) ? uri.getLastPathSegment().toUpperCase() : "");
            this.mTargetId = uri.getQueryParameter("targetId");
            this.name = uri.getQueryParameter("name");
            return;
        }
        this.mConversationType = this.mPublicAccountInfo.getConversationType();
        this.mTargetId = this.mPublicAccountInfo.getTargetId();
        this.name = this.mPublicAccountInfo.getName();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_fr_public_service_inf, viewGroup, false);
        this.mPortraitIV = (AsyncImageView) inflate.findViewById(C4974R.id.portrait);
        this.mNameTV = (TextView) inflate.findViewById(C4974R.id.name);
        this.mAccountTV = (TextView) inflate.findViewById(C4974R.id.account);
        this.mDescriptionTV = (TextView) inflate.findViewById(C4974R.id.description);
        this.mEnterBtn = (Button) inflate.findViewById(C4974R.id.enter);
        this.mFollowBtn = (Button) inflate.findViewById(C4974R.id.follow);
        this.mUnfollowBtn = (Button) inflate.findViewById(C4974R.id.unfollow);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mLoadingDialogFragment = LoadingDialogFragment.newInstance("", getResources().getString(C4974R.string.rc_notice_data_is_loading));
        if (this.mPublicAccountInfo != null) {
            initData(this.mPublicAccountInfo);
        } else if (!TextUtils.isEmpty(this.mTargetId)) {
            PublicServiceType publicServiceType = null;
            if (this.mConversationType == ConversationType.APP_PUBLIC_SERVICE) {
                publicServiceType = PublicServiceType.APP_PUBLIC_SERVICE;
            } else if (this.mConversationType == ConversationType.PUBLIC_SERVICE) {
                publicServiceType = PublicServiceType.PUBLIC_SERVICE;
            } else {
                System.err.print("the public service type is error!!");
            }
            RongIM.getInstance().getPublicServiceProfile(publicServiceType, this.mTargetId, new C50591());
        }
    }

    private void initData(final PublicServiceProfile publicServiceProfile) {
        if (publicServiceProfile != null) {
            this.mPortraitIV.setResource(publicServiceProfile.getPortraitUri());
            this.mNameTV.setText(publicServiceProfile.getName());
            this.mAccountTV.setText(String.format(getResources().getString(C4974R.string.rc_pub_service_info_account), new Object[]{publicServiceProfile.getTargetId()}));
            this.mDescriptionTV.setText(publicServiceProfile.getIntroduction());
            boolean isFollow = publicServiceProfile.isFollow();
            FragmentTransaction beginTransaction;
            if (publicServiceProfile.isGlobal()) {
                beginTransaction = getFragmentManager().beginTransaction();
                beginTransaction.add(C4974R.id.rc_layout, SetConversationNotificationFragment.newInstance());
                beginTransaction.commitAllowingStateLoss();
                this.mFollowBtn.setVisibility(8);
                this.mEnterBtn.setVisibility(0);
                this.mUnfollowBtn.setVisibility(8);
            } else if (isFollow) {
                beginTransaction = getFragmentManager().beginTransaction();
                beginTransaction.add(C4974R.id.rc_layout, SetConversationNotificationFragment.newInstance());
                beginTransaction.commitAllowingStateLoss();
                this.mFollowBtn.setVisibility(8);
                this.mEnterBtn.setVisibility(0);
                this.mUnfollowBtn.setVisibility(0);
            } else {
                this.mFollowBtn.setVisibility(0);
                this.mEnterBtn.setVisibility(8);
                this.mUnfollowBtn.setVisibility(8);
            }
            this.mEnterBtn.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    RongIM$PublicServiceBehaviorListener publicServiceBehaviorListener = RongContext.getInstance().getPublicServiceBehaviorListener();
                    if (publicServiceBehaviorListener == null || !publicServiceBehaviorListener.onEnterConversationClick(view.getContext(), publicServiceProfile)) {
                        PublicServiceProfileFragment.this.getActivity().finish();
                        RongIM.getInstance().startConversation(PublicServiceProfileFragment.this.getActivity(), publicServiceProfile.getConversationType(), publicServiceProfile.getTargetId(), publicServiceProfile.getName());
                    }
                }
            });
            this.mFollowBtn.setOnClickListener(new OnClickListener() {
                public void onClick(final View view) {
                    PublicServiceType publicServiceType = null;
                    if (PublicServiceProfileFragment.this.mConversationType == ConversationType.APP_PUBLIC_SERVICE) {
                        publicServiceType = PublicServiceType.APP_PUBLIC_SERVICE;
                    } else if (PublicServiceProfileFragment.this.mConversationType == ConversationType.PUBLIC_SERVICE) {
                        publicServiceType = PublicServiceType.PUBLIC_SERVICE;
                    } else {
                        System.err.print("the public service type is error!!");
                    }
                    RongIM.getInstance().subscribePublicService(publicServiceType, publicServiceProfile.getTargetId(), new RongIMClient$OperationCallback() {
                        public void onSuccess() {
                            PublicServiceProfileFragment.this.mLoadingDialogFragment.dismiss();
                            PublicServiceProfileFragment.this.mFollowBtn.setVisibility(8);
                            PublicServiceProfileFragment.this.mEnterBtn.setVisibility(0);
                            PublicServiceProfileFragment.this.mUnfollowBtn.setVisibility(0);
                            RongUserInfoManager.getInstance().setPublicServiceProfile(publicServiceProfile);
                            RongContext.getInstance().getEventBus().post(PublicServiceFollowableEvent.obtain(publicServiceProfile.getTargetId(), publicServiceProfile.getConversationType(), true));
                            RongIM$PublicServiceBehaviorListener publicServiceBehaviorListener = RongContext.getInstance().getPublicServiceBehaviorListener();
                            if (publicServiceBehaviorListener == null || !publicServiceBehaviorListener.onFollowClick(view.getContext(), publicServiceProfile)) {
                                PublicServiceProfileFragment.this.getActivity().finish();
                                RongIM.getInstance().startConversation(PublicServiceProfileFragment.this.getActivity(), publicServiceProfile.getConversationType(), publicServiceProfile.getTargetId(), publicServiceProfile.getName());
                            }
                        }

                        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                            PublicServiceProfileFragment.this.mLoadingDialogFragment.dismiss();
                        }
                    });
                    PublicServiceProfileFragment.this.mLoadingDialogFragment.show(PublicServiceProfileFragment.this.getFragmentManager());
                }
            });
            this.mUnfollowBtn.setOnClickListener(new OnClickListener() {
                public void onClick(final View view) {
                    PublicServiceType publicServiceType = null;
                    if (PublicServiceProfileFragment.this.mConversationType == ConversationType.APP_PUBLIC_SERVICE) {
                        publicServiceType = PublicServiceType.APP_PUBLIC_SERVICE;
                    } else if (PublicServiceProfileFragment.this.mConversationType == ConversationType.PUBLIC_SERVICE) {
                        publicServiceType = PublicServiceType.PUBLIC_SERVICE;
                    } else {
                        System.err.print("the public service type is error!!");
                    }
                    RongIM.getInstance().unsubscribePublicService(publicServiceType, publicServiceProfile.getTargetId(), new RongIMClient$OperationCallback() {
                        public void onSuccess() {
                            PublicServiceProfileFragment.this.mFollowBtn.setVisibility(0);
                            PublicServiceProfileFragment.this.mEnterBtn.setVisibility(8);
                            PublicServiceProfileFragment.this.mUnfollowBtn.setVisibility(8);
                            RongContext.getInstance().getEventBus().post(PublicServiceFollowableEvent.obtain(publicServiceProfile.getTargetId(), publicServiceProfile.getConversationType(), false));
                            RongIM$PublicServiceBehaviorListener publicServiceBehaviorListener = RongContext.getInstance().getPublicServiceBehaviorListener();
                            if (publicServiceBehaviorListener == null || !publicServiceBehaviorListener.onUnFollowClick(view.getContext(), publicServiceProfile)) {
                                PublicServiceProfileFragment.this.getActivity().finish();
                            }
                        }

                        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                        }
                    });
                }
            });
        }
    }

    public boolean handleMessage(Message message) {
        return false;
    }
}
