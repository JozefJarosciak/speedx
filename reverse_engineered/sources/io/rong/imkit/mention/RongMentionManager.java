package io.rong.imkit.mention;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM$IGroupMembersProvider;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.adapter.BaseAdapter;
import io.rong.imkit.widget.provider.TextInputProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MentionedInfo.MentionedType;
import io.rong.imlib.model.UserInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RongMentionManager implements IMemberMentionedListener, ITextInputListener {
    private static String TAG = "RongMentionManager";
    private RongIM$IGroupMembersProvider mGroupMembersProvider;
    private IMentionedInputListener mMentionedInputListener;
    private Stack<MentionInstance> stack = new Stack();

    private static class SingletonHolder {
        static RongMentionManager sInstance = new RongMentionManager();

        private SingletonHolder() {
        }
    }

    RongMentionManager() {
    }

    public static RongMentionManager getInstance() {
        return SingletonHolder.sInstance;
    }

    public void createInstance(ConversationType conversationType, String str, BaseAdapter baseAdapter, TextInputProvider textInputProvider) {
        RLog.m19422i(TAG, "createInstance");
        String str2 = conversationType.getName() + str;
        if (this.stack.size() <= 0 || !((MentionInstance) this.stack.peek()).key.equals(str2)) {
            MentionInstance mentionInstance = new MentionInstance();
            mentionInstance.key = str2;
            mentionInstance.mentionBlocks = new ArrayList();
            mentionInstance.mentionInputProvider = textInputProvider;
            this.stack.add(mentionInstance);
            baseAdapter.setOnItemClickListener(this);
            textInputProvider.setTextInputListener(this);
        }
    }

    public void destroyInstance() {
        RLog.m19422i(TAG, "destroyInstance");
        if (this.stack.size() > 0) {
            this.stack.pop();
        } else {
            RLog.m19420e(TAG, "destroyInstance error.");
        }
    }

    public void onMemberMentioned(String str) {
        RLog.m19419d(TAG, "onMemberMentioned " + str);
        if (TextUtils.isEmpty(str)) {
            RLog.m19420e(TAG, "onMemberMentioned userId is null");
        } else {
            addMentionedMember(RongUserInfoManager.getInstance().getUserInfo(str), 0);
        }
    }

    public void mentionMember(UserInfo userInfo) {
        if (userInfo == null || TextUtils.isEmpty(userInfo.getUserId())) {
            RLog.m19420e(TAG, "onMemberMentioned userId is null");
        } else {
            addMentionedMember(userInfo, 1);
        }
    }

    private void addMentionedMember(UserInfo userInfo, int i) {
        if (this.stack.size() > 0) {
            MentionInstance mentionInstance = (MentionInstance) this.stack.peek();
            EditText editText = mentionInstance.mentionInputProvider.getEditText();
            if (userInfo != null && editText != null) {
                CharSequence charSequence;
                if (i == 0) {
                    charSequence = "@" + userInfo.getName() + " ";
                } else {
                    charSequence = userInfo.getName() + " ";
                }
                int length = charSequence.length();
                int selectionStart = editText.getSelectionStart();
                MentionBlock brokenMentionedBlock = getBrokenMentionedBlock(selectionStart, mentionInstance.mentionBlocks);
                if (brokenMentionedBlock != null) {
                    mentionInstance.mentionBlocks.remove(brokenMentionedBlock);
                }
                brokenMentionedBlock = new MentionBlock();
                brokenMentionedBlock.userId = userInfo.getUserId();
                brokenMentionedBlock.offset = false;
                brokenMentionedBlock.name = userInfo.getName();
                if (i == 1) {
                    brokenMentionedBlock.start = selectionStart - 1;
                } else {
                    brokenMentionedBlock.start = selectionStart;
                }
                brokenMentionedBlock.end = selectionStart + length;
                mentionInstance.mentionBlocks.add(brokenMentionedBlock);
                editText.getEditableText().insert(selectionStart, charSequence);
                editText.setSelection(selectionStart + length);
                brokenMentionedBlock.offset = true;
            }
        }
    }

    private MentionBlock getBrokenMentionedBlock(int i, List<MentionBlock> list) {
        for (MentionBlock mentionBlock : list) {
            if (mentionBlock.offset && i < mentionBlock.end && i > mentionBlock.start) {
                return mentionBlock;
            }
        }
        return null;
    }

    private void offsetMentionedBlocks(int i, int i2, List<MentionBlock> list) {
        for (MentionBlock mentionBlock : list) {
            if (i <= mentionBlock.start && mentionBlock.offset) {
                mentionBlock.start += i2;
                mentionBlock.end += i2;
            }
            mentionBlock.offset = true;
        }
    }

    private MentionBlock getDeleteMentionedBlock(int i, List<MentionBlock> list) {
        for (MentionBlock mentionBlock : list) {
            if (i == mentionBlock.end) {
                return mentionBlock;
            }
        }
        return null;
    }

    public void onTextEdit(ConversationType conversationType, String str, int i, int i2, String str2) {
        boolean z = true;
        RLog.m19419d(TAG, "onTextEdit " + i + ", " + str2);
        if (this.stack == null || this.stack.size() == 0) {
            RLog.m19424w(TAG, "onTextEdit ignore.");
            return;
        }
        MentionInstance mentionInstance = (MentionInstance) this.stack.peek();
        if (mentionInstance.key.equals(conversationType.getName() + str)) {
            if (i2 == 1 && !TextUtils.isEmpty(str2)) {
                if (i == 0) {
                    z = str2.substring(0, 1).equals("@");
                } else {
                    String substring = str2.substring(i - 1, i);
                    if (!str2.substring(i, i + 1).equals("@") || substring.matches("^[a-zA-Z]*") || substring.matches("^\\d+$")) {
                        z = false;
                    }
                }
                if (z && (this.mMentionedInputListener == null || !this.mMentionedInputListener.onMentionedInput(conversationType, str))) {
                    Intent intent = new Intent(RongContext.getInstance(), MemberMentionedActivity.class);
                    intent.putExtra("conversationType", conversationType.getValue());
                    intent.putExtra("targetId", str);
                    intent.setFlags(268435456);
                    RongContext.getInstance().startActivity(intent);
                }
            }
            MentionBlock brokenMentionedBlock = getBrokenMentionedBlock(i, mentionInstance.mentionBlocks);
            if (brokenMentionedBlock != null) {
                mentionInstance.mentionBlocks.remove(brokenMentionedBlock);
            }
            offsetMentionedBlocks(i, i2, mentionInstance.mentionBlocks);
            return;
        }
        RLog.m19424w(TAG, "onTextEdit ignore conversation.");
    }

    public MentionedInfo onSendButtonClick() {
        if (this.stack.size() > 0) {
            List arrayList = new ArrayList();
            MentionInstance mentionInstance = (MentionInstance) this.stack.peek();
            for (MentionBlock mentionBlock : mentionInstance.mentionBlocks) {
                if (!arrayList.contains(mentionBlock.userId)) {
                    arrayList.add(mentionBlock.userId);
                }
            }
            if (arrayList.size() > 0) {
                mentionInstance.mentionBlocks.clear();
                return new MentionedInfo(MentionedType.PART, arrayList, null);
            }
        }
        return null;
    }

    public void onDeleteClick(ConversationType conversationType, String str, EditText editText, int i) {
        RLog.m19419d(TAG, "onTextEdit " + i);
        if (this.stack.size() > 0 && i > 0) {
            MentionInstance mentionInstance = (MentionInstance) this.stack.peek();
            if (mentionInstance.key.equals(conversationType.getName() + str)) {
                MentionBlock deleteMentionedBlock = getDeleteMentionedBlock(i, mentionInstance.mentionBlocks);
                if (deleteMentionedBlock != null) {
                    mentionInstance.mentionBlocks.remove(deleteMentionedBlock);
                    int length = (i - deleteMentionedBlock.name.length()) - 1;
                    editText.getEditableText().delete(length, i);
                    editText.setSelection(length);
                }
            }
        }
    }

    public void setGroupMembersProvider(RongIM$IGroupMembersProvider rongIM$IGroupMembersProvider) {
        this.mGroupMembersProvider = rongIM$IGroupMembersProvider;
    }

    public RongIM$IGroupMembersProvider getGroupMembersProvider() {
        return this.mGroupMembersProvider;
    }

    public void setMentionedInputListener(IMentionedInputListener iMentionedInputListener) {
        this.mMentionedInputListener = iMentionedInputListener;
    }
}
