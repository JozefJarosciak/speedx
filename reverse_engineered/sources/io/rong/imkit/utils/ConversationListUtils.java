package io.rong.imkit.utils;

import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.BaseAdapter;

public class ConversationListUtils {
    public static int findPositionForNewConversation(UIConversation uIConversation, BaseAdapter<UIConversation> baseAdapter) {
        int count = baseAdapter.getCount();
        int i = 0;
        int i2 = 0;
        while (i2 < count) {
            int i3;
            if (!uIConversation.isTop()) {
                if (!((UIConversation) baseAdapter.getItem(i2)).isTop() && ((UIConversation) baseAdapter.getItem(i2)).getUIConversationTime() <= uIConversation.getUIConversationTime()) {
                    break;
                }
                i3 = i + 1;
            } else if (!((UIConversation) baseAdapter.getItem(i2)).isTop() || ((UIConversation) baseAdapter.getItem(i2)).getUIConversationTime() <= uIConversation.getUIConversationTime()) {
                break;
            } else {
                i3 = i + 1;
            }
            i2++;
            i = i3;
        }
        return i;
    }

    public static int findPositionForSetTop(UIConversation uIConversation, BaseAdapter<UIConversation> baseAdapter) {
        int count = baseAdapter.getCount();
        int i = 0;
        int i2 = 0;
        while (i2 < count && ((UIConversation) baseAdapter.getItem(i2)).isTop() && ((UIConversation) baseAdapter.getItem(i2)).getUIConversationTime() > uIConversation.getUIConversationTime()) {
            i2++;
            i++;
        }
        return i;
    }

    public static int findPositionForCancleTop(int i, BaseAdapter<UIConversation> baseAdapter) {
        int count = baseAdapter.getCount();
        if (i > count) {
            throw new IllegalArgumentException("the index for the position is error!");
        }
        int i2 = 0;
        int i3 = i + 1;
        while (i3 < count && (((UIConversation) baseAdapter.getItem(i3)).isTop() || ((UIConversation) baseAdapter.getItem(i)).getUIConversationTime() < ((UIConversation) baseAdapter.getItem(i3)).getUIConversationTime())) {
            i2++;
            i3++;
        }
        return i + i2;
    }
}
