package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.activity.PictureSelectorActivity;
import io.rong.imkit.manager.SendImageManager;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imlib.model.Conversation;
import java.util.List;

public class ImageInputProvider extends ExtendProvider {
    private static final String TAG = "ImageInputProvider";

    public ImageInputProvider(RongContext rongContext) {
        super(rongContext);
    }

    public Drawable obtainPluginDrawable(Context context) {
        return context.getResources().getDrawable(C4974R.drawable.rc_ic_picture);
    }

    public CharSequence obtainPluginTitle(Context context) {
        return context.getString(C4974R.string.rc_plugins_image);
    }

    public void onPluginClick(View view) {
        Intent intent = new Intent();
        intent.setClass(view.getContext(), PictureSelectorActivity.class);
        startActivityForResult(intent, 23);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            boolean booleanExtra = intent.getBooleanExtra("sendOrigin", false);
            List parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.intent.extra.RETURN_RESULT");
            Conversation currentConversation = getCurrentConversation();
            SendImageManager.getInstance().sendImages(currentConversation.getConversationType(), currentConversation.getTargetId(), parcelableArrayListExtra, booleanExtra);
        }
    }
}
