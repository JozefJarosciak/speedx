package io.rong.imkit.widget.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.text.Spannable;
import android.view.View;
import android.view.ViewGroup;
import io.rong.imkit.model.UIMessage;
import io.rong.imlib.model.MessageContent;

public interface IContainerItemProvider<T extends Parcelable> {

    public static abstract class MessageProvider<K extends MessageContent> implements IContainerItemProvider<UIMessage> {
        public abstract void bindView(View view, int i, K k, UIMessage uIMessage);

        public abstract Spannable getContentSummary(K k);

        public abstract void onItemClick(View view, int i, K k, UIMessage uIMessage);

        public abstract void onItemLongClick(View view, int i, K k, UIMessage uIMessage);

        public final void bindView(View view, int i, UIMessage uIMessage) {
            bindView(view, i, uIMessage.getContent(), uIMessage);
        }

        public final Spannable getSummary(UIMessage uIMessage) {
            return getContentSummary(uIMessage.getContent());
        }
    }

    public interface ConversationProvider<T extends Parcelable> extends IContainerItemProvider<T> {
        Uri getPortraitUri(String str);

        String getTitle(String str);
    }

    void bindView(View view, int i, T t);

    View newView(Context context, ViewGroup viewGroup);
}
