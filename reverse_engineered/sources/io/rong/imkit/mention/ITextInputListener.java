package io.rong.imkit.mention;

import android.widget.EditText;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.MentionedInfo;

public interface ITextInputListener {
    void onDeleteClick(ConversationType conversationType, String str, EditText editText, int i);

    MentionedInfo onSendButtonClick();

    void onTextEdit(ConversationType conversationType, String str, int i, int i2, String str2);
}
