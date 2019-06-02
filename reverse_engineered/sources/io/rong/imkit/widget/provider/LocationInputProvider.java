package io.rong.imkit.widget.provider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM$LocationProvider.LocationCallback;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imlib.model.Message;
import io.rong.message.LocationMessage;

public class LocationInputProvider extends ExtendProvider {
    private static final String TAG = "LocationInputProvider";
    RongContext mContext;
    Message mCurrentMessage;

    /* renamed from: io.rong.imkit.widget.provider.LocationInputProvider$1 */
    class C51841 implements LocationCallback {
        C51841() {
        }

        public void onSuccess(LocationMessage locationMessage) {
            RongIM.getInstance().sendLocationMessage(Message.obtain(LocationInputProvider.this.mCurrentConversation.getTargetId(), LocationInputProvider.this.mCurrentConversation.getConversationType(), locationMessage), null, null, null);
        }

        public void onFailure(String str) {
        }
    }

    public LocationInputProvider(RongContext rongContext) {
        super(rongContext);
        this.mContext = rongContext;
    }

    public Drawable obtainPluginDrawable(Context context) {
        return context.getResources().getDrawable(C4974R.drawable.rc_ic_location);
    }

    public CharSequence obtainPluginTitle(Context context) {
        return context.getString(C4974R.string.rc_plugins_location);
    }

    public void onPluginClick(View view) {
        if (RongContext.getInstance() != null && RongContext.getInstance().getLocationProvider() != null) {
            RongContext.getInstance().getLocationProvider().onStartLocation(getContext(), new C51841());
        }
    }

    public void onDetached() {
        super.onDetached();
    }
}
