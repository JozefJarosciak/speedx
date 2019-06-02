package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import ch.qos.logback.core.joran.action.Action;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$SendImageMessageCallback;
import io.rong.imlib.model.Message;
import io.rong.message.ImageMessage;
import java.util.Iterator;

public class CameraInputProvider extends ExtendProvider {

    /* renamed from: io.rong.imkit.widget.provider.CameraInputProvider$1 */
    class C51751 extends RongIMClient$SendImageMessageCallback {
        C51751() {
        }

        public void onAttached(Message message) {
        }

        public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }

        public void onSuccess(Message message) {
        }

        public void onProgress(Message message, int i) {
        }
    }

    class PublicLocationRunnable implements Runnable {
        Uri mUri;

        public PublicLocationRunnable(Uri uri) {
            this.mUri = uri;
        }

        public void run() {
            CameraInputProvider.this.sendImage(this.mUri);
        }
    }

    class PublishRunnable implements Runnable {
        Uri mUri;

        public PublishRunnable(Uri uri) {
            this.mUri = uri;
        }

        public void run() {
            Cursor query = CameraInputProvider.this.getContext().getContentResolver().query(this.mUri, new String[]{"_data"}, null, null, null);
            if (query == null || query.getCount() == 0) {
                query.close();
                return;
            }
            query.moveToFirst();
            Uri parse = Uri.parse("file://" + query.getString(0));
            query.close();
            CameraInputProvider.this.sendImage(parse);
        }
    }

    public CameraInputProvider(RongContext rongContext) {
        super(rongContext);
    }

    public Drawable obtainPluginDrawable(Context context) {
        return context.getResources().getDrawable(C4974R.drawable.rc_ic_camera);
    }

    public CharSequence obtainPluginTitle(Context context) {
        return context.getString(C4974R.string.rc_plugins_camera);
    }

    public void onPluginClick(View view) {
        Intent intent = new Intent();
        intent.setClass(view.getContext(), TakingPicturesActivity.class);
        startActivityForResult(intent, 24);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && intent != null) {
            if (intent.getData() != null && "content".equals(intent.getData().getScheme())) {
                getContext().executorBackground(new PublishRunnable(intent.getData()));
            } else if (intent.getData() != null && Action.FILE_ATTRIBUTE.equals(intent.getData().getScheme())) {
                getContext().executorBackground(new PublicLocationRunnable(intent.getData()));
            } else if (intent.hasExtra("android.intent.extra.RETURN_RESULT")) {
                Iterator it = intent.getParcelableArrayListExtra("android.intent.extra.RETURN_RESULT").iterator();
                while (it.hasNext()) {
                    getContext().executorBackground(new PublishRunnable((Uri) it.next()));
                }
            }
            super.onActivityResult(i, i2, intent);
        }
    }

    private void sendImage(Uri uri) {
        if (this.mCurrentConversation != null) {
            RongIM.getInstance().sendImageMessage(Message.obtain(this.mCurrentConversation.getTargetId(), this.mCurrentConversation.getConversationType(), ImageMessage.obtain(uri, uri)), null, null, new C51751());
        }
    }
}
