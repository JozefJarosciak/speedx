package io.rong.imkit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.adapter.ConversationAddMemberAdapter;
import io.rong.imkit.widget.adapter.ConversationAddMemberAdapter.OnDeleteIconListener;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.UserInfo;
import java.util.ArrayList;
import java.util.List;

public class ConversationAddMemberFragment extends BaseFragment implements OnItemClickListener, OnDeleteIconListener {
    static final int PREPARE_LIST = 1;
    static final int REMOVE_ITEM = 2;
    static final int SHOW_TOAST = 3;
    private ConversationAddMemberAdapter mAdapter;
    private ConversationType mConversationType;
    private GridView mGridList;
    private List<String> mIdList = new ArrayList();
    private ArrayList<UserInfo> mMembers = new ArrayList();
    private String mTargetId;

    /* renamed from: io.rong.imkit.fragment.ConversationAddMemberFragment$1 */
    class C50151 implements OnTouchListener {
        C50151() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (1 != motionEvent.getAction() || !ConversationAddMemberFragment.this.mAdapter.isDeleteState()) {
                return false;
            }
            ConversationAddMemberFragment.this.mAdapter.add(new UserInfo("RongAddBtn", null, null));
            String currentUserId = RongIM.getInstance().getCurrentUserId();
            if (ConversationAddMemberFragment.this.mAdapter.getCreatorId() != null && ConversationAddMemberFragment.this.mConversationType.equals(ConversationType.DISCUSSION) && currentUserId.equals(ConversationAddMemberFragment.this.mAdapter.getCreatorId())) {
                ConversationAddMemberFragment.this.mAdapter.add(new UserInfo("RongDelBtn", null, null));
            }
            ConversationAddMemberFragment.this.mAdapter.setDeleteState(false);
            ConversationAddMemberFragment.this.mAdapter.notifyDataSetChanged();
            return true;
        }
    }

    /* renamed from: io.rong.imkit.fragment.ConversationAddMemberFragment$2 */
    class C50162 extends ResultCallback<Discussion> {
        C50162() {
        }

        public void onSuccess(Discussion discussion) {
            ConversationAddMemberFragment.this.mIdList = discussion.getMemberIdList();
            ConversationAddMemberFragment.this.mAdapter.setCreatorId(discussion.getCreatorId());
            Message message = new Message();
            message.what = 1;
            message.obj = ConversationAddMemberFragment.this.mIdList;
            ConversationAddMemberFragment.this.getHandler().sendMessage(message);
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            ConversationAddMemberFragment.this.getHandler().sendEmptyMessage(3);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RongContext.getInstance().getEventBus().register(this);
        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            if (intent.getData() != null) {
                this.mConversationType = ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase());
                this.mTargetId = intent.getData().getQueryParameter("targetId");
            }
        }
        this.mAdapter = new ConversationAddMemberAdapter(getActivity());
        this.mAdapter.setDeleteIconListener(this);
        initData();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_fr_conversation_member_list, null);
        this.mGridList = (GridView) findViewById(inflate, C4974R.id.rc_list);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mGridList.setAdapter(this.mAdapter);
        this.mGridList.setOnItemClickListener(this);
        this.mGridList.setOnTouchListener(new C50151());
    }

    private void initData() {
        if (this.mConversationType.equals(ConversationType.DISCUSSION)) {
            RongIM.getInstance().getDiscussion(this.mTargetId, new C50162());
        } else if (this.mConversationType.equals(ConversationType.PRIVATE)) {
            this.mIdList.add(this.mTargetId);
            Message message = new Message();
            message.what = 1;
            message.obj = this.mIdList;
            getHandler().sendMessage(message);
        }
    }

    public void onEventMainThread(UserInfo userInfo) {
        int count = this.mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            UserInfo userInfo2 = (UserInfo) this.mAdapter.getItem(i);
            if (userInfo.getUserId().equals(userInfo2.getUserId())) {
                userInfo2.setName(userInfo.getName());
                userInfo2.setPortraitUri(userInfo.getPortraitUri());
                this.mAdapter.getView(i, this.mGridList.getChildAt(i - this.mGridList.getFirstVisiblePosition()), this.mGridList);
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        UserInfo userInfo = (UserInfo) this.mAdapter.getItem(i);
        if (userInfo.getUserId().equals("RongDelBtn")) {
            this.mAdapter.setDeleteState(true);
            int count = this.mAdapter.getCount();
            this.mAdapter.remove(count - 1);
            this.mAdapter.remove(count - 2);
            this.mAdapter.notifyDataSetChanged();
        } else if (!userInfo.getUserId().equals("RongAddBtn")) {
        } else {
            if (RongContext.getInstance().getMemberSelectListener() == null) {
                throw new ExceptionInInitializerError("The OnMemberSelectListener hasn't been set!");
            }
            RongContext.getInstance().getMemberSelectListener().startSelectMember(getActivity(), this.mConversationType, this.mTargetId);
        }
    }

    public void onDeleteIconClick(View view, final int i) {
        RongIM.getInstance().removeMemberFromDiscussion(this.mTargetId, ((UserInfo) this.mAdapter.getItem(i)).getUserId(), new RongIMClient$OperationCallback() {
            public void onSuccess() {
                Message message = new Message();
                message.what = 2;
                message.obj = Integer.valueOf(i);
                ConversationAddMemberFragment.this.getHandler().sendMessage(message);
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                ConversationAddMemberFragment.this.getHandler().sendEmptyMessage(3);
            }
        });
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                String currentUserId;
                int i = 0;
                for (String currentUserId2 : (List) message.obj) {
                    if (i >= 50) {
                        this.mMembers.add(new UserInfo("RongAddBtn", null, null));
                        currentUserId2 = RongIM.getInstance().getCurrentUserId();
                        if (this.mAdapter.getCreatorId() != null && this.mConversationType.equals(ConversationType.DISCUSSION) && currentUserId2.equals(this.mAdapter.getCreatorId())) {
                            this.mMembers.add(new UserInfo("RongDelBtn", null, null));
                        }
                        this.mAdapter.addCollection(this.mMembers);
                        this.mAdapter.notifyDataSetChanged();
                        break;
                    }
                    UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(currentUserId2);
                    if (userInfo == null) {
                        this.mMembers.add(new UserInfo(currentUserId2, null, null));
                    } else {
                        this.mMembers.add(userInfo);
                    }
                    i++;
                }
                this.mMembers.add(new UserInfo("RongAddBtn", null, null));
                currentUserId2 = RongIM.getInstance().getCurrentUserId();
                this.mMembers.add(new UserInfo("RongDelBtn", null, null));
                this.mAdapter.addCollection(this.mMembers);
                this.mAdapter.notifyDataSetChanged();
                break;
            case 2:
                this.mAdapter.remove(((Integer) message.obj).intValue());
                this.mAdapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onRestoreUI() {
        initData();
    }
}
