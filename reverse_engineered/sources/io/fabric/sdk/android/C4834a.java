package io.fabric.sdk.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Set;

/* compiled from: ActivityLifecycleManager */
/* renamed from: io.fabric.sdk.android.a */
public class C4834a {
    /* renamed from: a */
    private final Application f17069a;
    /* renamed from: b */
    private C4833a f17070b;

    /* compiled from: ActivityLifecycleManager */
    /* renamed from: io.fabric.sdk.android.a$b */
    public static abstract class C4604b {
        /* renamed from: a */
        public void mo6223a(Activity activity, Bundle bundle) {
        }

        /* renamed from: a */
        public void mo6138a(Activity activity) {
        }

        /* renamed from: b */
        public void mo6224b(Activity activity) {
        }

        /* renamed from: c */
        public void m18234c(Activity activity) {
        }

        /* renamed from: d */
        public void m18235d(Activity activity) {
        }

        /* renamed from: b */
        public void m18233b(Activity activity, Bundle bundle) {
        }

        /* renamed from: e */
        public void m18236e(Activity activity) {
        }
    }

    /* compiled from: ActivityLifecycleManager */
    /* renamed from: io.fabric.sdk.android.a$a */
    private static class C4833a {
        /* renamed from: a */
        private final Set<ActivityLifecycleCallbacks> f17067a = new HashSet();
        /* renamed from: b */
        private final Application f17068b;

        C4833a(Application application) {
            this.f17068b = application;
        }

        @TargetApi(14)
        /* renamed from: a */
        private boolean m18982a(final C4604b c4604b) {
            if (this.f17068b == null) {
                return false;
            }
            ActivityLifecycleCallbacks c48321 = new ActivityLifecycleCallbacks(this) {
                /* renamed from: b */
                final /* synthetic */ C4833a f17066b;

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    c4604b.mo6223a(activity, bundle);
                }

                public void onActivityStarted(Activity activity) {
                    c4604b.mo6138a(activity);
                }

                public void onActivityResumed(Activity activity) {
                    c4604b.mo6224b(activity);
                }

                public void onActivityPaused(Activity activity) {
                    c4604b.m18234c(activity);
                }

                public void onActivityStopped(Activity activity) {
                    c4604b.m18235d(activity);
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    c4604b.m18233b(activity, bundle);
                }

                public void onActivityDestroyed(Activity activity) {
                    c4604b.m18236e(activity);
                }
            };
            this.f17068b.registerActivityLifecycleCallbacks(c48321);
            this.f17067a.add(c48321);
            return true;
        }
    }

    public C4834a(Context context) {
        this.f17069a = (Application) context.getApplicationContext();
        if (VERSION.SDK_INT >= 14) {
            this.f17070b = new C4833a(this.f17069a);
        }
    }

    /* renamed from: a */
    public boolean m18983a(C4604b c4604b) {
        return this.f17070b != null && this.f17070b.m18982a(c4604b);
    }
}
