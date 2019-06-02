package io.rong.imkit.mention;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongIM$IGroupMemberCallback;
import io.rong.imkit.RongIM$IGroupMembersProvider;
import io.rong.imkit.mention.SideBar.OnTouchingLetterChangedListener;
import io.rong.imkit.tools.CharacterParser;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.UserInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MemberMentionedActivity extends Activity {
    private MembersAdapter mAdapter;
    private List<MemberInfo> mAllMemberList;
    private ListView mListView;

    /* renamed from: io.rong.imkit.mention.MemberMentionedActivity$1 */
    class C50981 implements RongIM$IGroupMemberCallback {
        C50981() {
        }

        public void onGetGroupMembersResult(final List<UserInfo> list) {
            MemberMentionedActivity.this.mListView.getHandler().post(new Runnable() {
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        UserInfo userInfo = (UserInfo) list.get(i);
                        if (!(userInfo == null || userInfo.getUserId().equals(RongIMClient.getInstance().getCurrentUserId()))) {
                            MemberInfo memberInfo = new MemberInfo(userInfo);
                            String str = "#";
                            String selling = CharacterParser.getInstance().getSelling(userInfo.getName());
                            if (selling.length() > 0) {
                                selling = selling.substring(0, 1).toUpperCase();
                            } else {
                                selling = str;
                            }
                            if (selling.matches("[A-Z]")) {
                                memberInfo.setLetter(selling.toUpperCase());
                            } else {
                                memberInfo.setLetter("#");
                            }
                            MemberMentionedActivity.this.mAllMemberList.add(memberInfo);
                        }
                    }
                    Collections.sort(MemberMentionedActivity.this.mAllMemberList, PinyinComparator.getInstance());
                    MemberMentionedActivity.this.mAdapter.setData(MemberMentionedActivity.this.mAllMemberList);
                    MemberMentionedActivity.this.mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    /* renamed from: io.rong.imkit.mention.MemberMentionedActivity$2 */
    class C50992 extends ResultCallback<Discussion> {
        C50992() {
        }

        public void onSuccess(Discussion discussion) {
            for (String userInfo : discussion.getMemberIdList()) {
                String userInfo2;
                UserInfo userInfo3 = RongUserInfoManager.getInstance().getUserInfo(userInfo2);
                if (!(userInfo3 == null || userInfo3.getUserId().equals(RongIMClient.getInstance().getCurrentUserId()))) {
                    MemberInfo memberInfo = new MemberInfo(userInfo3);
                    userInfo2 = CharacterParser.getInstance().getSelling(userInfo3.getName()).substring(0, 1).toUpperCase();
                    if (userInfo2.matches("[A-Z]")) {
                        memberInfo.setLetter(userInfo2.toUpperCase());
                    } else {
                        memberInfo.setLetter("#");
                    }
                    MemberMentionedActivity.this.mAllMemberList.add(memberInfo);
                }
            }
            Collections.sort(MemberMentionedActivity.this.mAllMemberList, PinyinComparator.getInstance());
            MemberMentionedActivity.this.mAdapter.setData(MemberMentionedActivity.this.mAllMemberList);
            MemberMentionedActivity.this.mAdapter.notifyDataSetChanged();
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    /* renamed from: io.rong.imkit.mention.MemberMentionedActivity$3 */
    class C51003 implements OnItemClickListener {
        C51003() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            MemberMentionedActivity.this.finish();
            MemberInfo item = MemberMentionedActivity.this.mAdapter.getItem(i);
            if (item != null && item.userInfo != null) {
                RongMentionManager.getInstance().mentionMember(item.userInfo);
            }
        }
    }

    /* renamed from: io.rong.imkit.mention.MemberMentionedActivity$4 */
    class C51014 implements OnTouchingLetterChangedListener {
        C51014() {
        }

        public void onTouchingLetterChanged(String str) {
            int positionForSection = MemberMentionedActivity.this.mAdapter.getPositionForSection(str.charAt(0));
            if (positionForSection != -1) {
                MemberMentionedActivity.this.mListView.setSelection(positionForSection);
            }
        }
    }

    /* renamed from: io.rong.imkit.mention.MemberMentionedActivity$5 */
    class C51025 implements TextWatcher {
        C51025() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            List access$000;
            List arrayList = new ArrayList();
            if (TextUtils.isEmpty(charSequence.toString())) {
                access$000 = MemberMentionedActivity.this.mAllMemberList;
            } else {
                arrayList.clear();
                for (MemberInfo memberInfo : MemberMentionedActivity.this.mAllMemberList) {
                    String name = memberInfo.userInfo.getName();
                    if (name.contains(charSequence) || CharacterParser.getInstance().getSelling(name).startsWith(charSequence.toString())) {
                        arrayList.add(memberInfo);
                    }
                }
                access$000 = arrayList;
            }
            Collections.sort(access$000, PinyinComparator.getInstance());
            MemberMentionedActivity.this.mAdapter.setData(access$000);
            MemberMentionedActivity.this.mAdapter.notifyDataSetChanged();
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: io.rong.imkit.mention.MemberMentionedActivity$6 */
    class C51036 implements OnClickListener {
        C51036() {
        }

        public void onClick(View view) {
            MemberMentionedActivity.this.finish();
        }
    }

    private class MemberInfo {
        String letter;
        UserInfo userInfo;

        MemberInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public void setLetter(String str) {
            this.letter = str;
        }

        public String getLetter() {
            return this.letter;
        }
    }

    class MembersAdapter extends BaseAdapter implements SectionIndexer {
        private List<MemberInfo> mList = new ArrayList();

        MembersAdapter() {
        }

        public void setData(List<MemberInfo> list) {
            this.mList = list;
        }

        public int getCount() {
            return this.mList.size();
        }

        public MemberInfo getItem(int i) {
            return (MemberInfo) this.mList.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(viewGroup.getContext()).inflate(C4974R.layout.rc_mention_list_item, null);
                viewHolder.name = (TextView) view.findViewById(C4974R.id.rc_user_name);
                viewHolder.portrait = (AsyncImageView) view.findViewById(C4974R.id.rc_user_portrait);
                viewHolder.letter = (TextView) view.findViewById(C4974R.id.letter);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            UserInfo userInfo = ((MemberInfo) this.mList.get(i)).userInfo;
            if (userInfo != null) {
                viewHolder.name.setText(userInfo.getName());
                viewHolder.portrait.setAvatar(userInfo.getPortraitUri());
            }
            if (i == getPositionForSection(getSectionForPosition(i))) {
                viewHolder.letter.setVisibility(0);
                viewHolder.letter.setText(((MemberInfo) this.mList.get(i)).getLetter());
            } else {
                viewHolder.letter.setVisibility(8);
            }
            return view;
        }

        public Object[] getSections() {
            return new Object[0];
        }

        public int getPositionForSection(int i) {
            for (int i2 = 0; i2 < getCount(); i2++) {
                if (((MemberInfo) this.mList.get(i2)).getLetter().toUpperCase().charAt(0) == i) {
                    return i2;
                }
            }
            return -1;
        }

        public int getSectionForPosition(int i) {
            return ((MemberInfo) this.mList.get(i)).getLetter().charAt(0);
        }
    }

    public static class PinyinComparator implements Comparator<MemberInfo> {
        public static PinyinComparator instance = null;

        public static PinyinComparator getInstance() {
            if (instance == null) {
                instance = new PinyinComparator();
            }
            return instance;
        }

        public int compare(MemberInfo memberInfo, MemberInfo memberInfo2) {
            if (memberInfo.getLetter().equals("@") || memberInfo2.getLetter().equals("#")) {
                return -1;
            }
            if (memberInfo.getLetter().equals("#") || memberInfo2.getLetter().equals("@")) {
                return 1;
            }
            return memberInfo.getLetter().compareTo(memberInfo2.getLetter());
        }
    }

    class ViewHolder {
        TextView letter;
        TextView name;
        AsyncImageView portrait;

        ViewHolder() {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C4974R.layout.rc_mention_members);
        EditText editText = (EditText) findViewById(C4974R.id.rc_edit_text);
        this.mListView = (ListView) findViewById(C4974R.id.rc_list);
        SideBar sideBar = (SideBar) findViewById(C4974R.id.rc_sidebar);
        sideBar.setTextView((TextView) findViewById(C4974R.id.rc_popup_bg));
        this.mAdapter = new MembersAdapter();
        this.mListView.setAdapter(this.mAdapter);
        this.mAllMemberList = new ArrayList();
        String stringExtra = getIntent().getStringExtra("targetId");
        ConversationType value = ConversationType.setValue(getIntent().getIntExtra("conversationType", 0));
        RongIM$IGroupMembersProvider groupMembersProvider = RongMentionManager.getInstance().getGroupMembersProvider();
        if (value.equals(ConversationType.GROUP) && groupMembersProvider != null) {
            groupMembersProvider.getGroupMembers(stringExtra, new C50981());
        } else if (value.equals(ConversationType.DISCUSSION)) {
            RongIMClient.getInstance().getDiscussion(stringExtra, new C50992());
        }
        this.mListView.setOnItemClickListener(new C51003());
        sideBar.setOnTouchingLetterChangedListener(new C51014());
        editText.addTextChangedListener(new C51025());
        findViewById(C4974R.id.rc_btn_cancel).setOnClickListener(new C51036());
    }
}
