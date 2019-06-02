package android.support.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback.Stub;
import android.text.TextUtils;

public class CustomTabsClient {
    private final ICustomTabsService mService;
    private final ComponentName mServiceComponentName;

    CustomTabsClient(ICustomTabsService iCustomTabsService, ComponentName componentName) {
        this.mService = iCustomTabsService;
        this.mServiceComponentName = componentName;
    }

    public static boolean bindCustomTabsService(Context context, String str, CustomTabsServiceConnection customTabsServiceConnection) {
        Intent intent = new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
        if (!TextUtils.isEmpty(str)) {
            intent.setPackage(str);
        }
        return context.bindService(intent, customTabsServiceConnection, 33);
    }

    public boolean warmup(long j) {
        try {
            return this.mService.warmup(j);
        } catch (RemoteException e) {
            return false;
        }
    }

    public CustomTabsSession newSession(final CustomTabsCallback customTabsCallback) {
        ICustomTabsCallback c00031 = new Stub() {
            public void onNavigationEvent(int i, Bundle bundle) {
                if (customTabsCallback != null) {
                    customTabsCallback.onNavigationEvent(i, bundle);
                }
            }

            public void extraCallback(String str, Bundle bundle) throws RemoteException {
                if (customTabsCallback != null) {
                    customTabsCallback.extraCallback(str, bundle);
                }
            }
        };
        try {
            if (this.mService.newSession(c00031)) {
                return new CustomTabsSession(this.mService, c00031, this.mServiceComponentName);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        }
    }

    public Bundle extraCommand(String str, Bundle bundle) {
        try {
            return this.mService.extraCommand(str, bundle);
        } catch (RemoteException e) {
            return null;
        }
    }
}
