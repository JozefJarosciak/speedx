package io.rong.imkit;

import android.content.Context;
import io.rong.imlib.model.PublicServiceProfile;

public interface RongIM$PublicServiceBehaviorListener {
    boolean onEnterConversationClick(Context context, PublicServiceProfile publicServiceProfile);

    boolean onFollowClick(Context context, PublicServiceProfile publicServiceProfile);

    boolean onUnFollowClick(Context context, PublicServiceProfile publicServiceProfile);
}
