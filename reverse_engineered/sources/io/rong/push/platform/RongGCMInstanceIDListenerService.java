package io.rong.push.platform;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;
import io.rong.push.core.PushRegistrationService;

public class RongGCMInstanceIDListenerService extends InstanceIDListenerService {
    public void onTokenRefresh() {
        startService(new Intent(this, PushRegistrationService.class));
    }
}
