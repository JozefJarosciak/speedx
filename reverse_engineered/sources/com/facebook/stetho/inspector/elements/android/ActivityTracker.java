package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import com.facebook.stetho.common.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ActivityTracker {
    private static final ActivityTracker sInstance = new ActivityTracker();
    private final ArrayList<Activity> mActivities = new ArrayList();
    private final List<Activity> mActivitiesUnmodifiable = Collections.unmodifiableList(this.mActivities);
    private AutomaticTracker mAutomaticTracker;
    private final List<Listener> mListeners = new CopyOnWriteArrayList();

    private static abstract class AutomaticTracker {

        @TargetApi(14)
        private static class AutomaticTrackerICSAndBeyond extends AutomaticTracker {
            private final Application mApplication;
            private final ActivityLifecycleCallbacks mLifecycleCallbacks = new C31571();
            private final ActivityTracker mTracker;

            /* renamed from: com.facebook.stetho.inspector.elements.android.ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond$1 */
            class C31571 implements ActivityLifecycleCallbacks {
                C31571() {
                }

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    AutomaticTrackerICSAndBeyond.this.mTracker.add(activity);
                }

                public void onActivityStarted(Activity activity) {
                }

                public void onActivityResumed(Activity activity) {
                }

                public void onActivityPaused(Activity activity) {
                }

                public void onActivityStopped(Activity activity) {
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                public void onActivityDestroyed(Activity activity) {
                    AutomaticTrackerICSAndBeyond.this.mTracker.remove(activity);
                }
            }

            public AutomaticTrackerICSAndBeyond(Application application, ActivityTracker activityTracker) {
                super();
                this.mApplication = application;
                this.mTracker = activityTracker;
            }

            public void register() {
                this.mApplication.registerActivityLifecycleCallbacks(this.mLifecycleCallbacks);
            }

            public void unregister() {
                this.mApplication.unregisterActivityLifecycleCallbacks(this.mLifecycleCallbacks);
            }
        }

        public abstract void register();

        public abstract void unregister();

        private AutomaticTracker() {
        }

        public static AutomaticTracker newInstanceIfPossible(Application application, ActivityTracker activityTracker) {
            if (VERSION.SDK_INT >= 14) {
                return new AutomaticTrackerICSAndBeyond(application, activityTracker);
            }
            return null;
        }
    }

    public interface Listener {
        void onActivityAdded(Activity activity);

        void onActivityRemoved(Activity activity);
    }

    public static ActivityTracker get() {
        return sInstance;
    }

    public void registerListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public void unregisterListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    public boolean beginTrackingIfPossible(Application application) {
        if (this.mAutomaticTracker == null) {
            AutomaticTracker newInstanceIfPossible = AutomaticTracker.newInstanceIfPossible(application, this);
            if (newInstanceIfPossible != null) {
                newInstanceIfPossible.register();
                this.mAutomaticTracker = newInstanceIfPossible;
                return true;
            }
        }
        return false;
    }

    public boolean endTracking() {
        if (this.mAutomaticTracker == null) {
            return false;
        }
        this.mAutomaticTracker.unregister();
        this.mAutomaticTracker = null;
        return true;
    }

    public void add(Activity activity) {
        Util.throwIfNull(activity);
        Util.throwIfNot(Looper.myLooper() == Looper.getMainLooper());
        this.mActivities.add(activity);
        for (Listener onActivityAdded : this.mListeners) {
            onActivityAdded.onActivityAdded(activity);
        }
    }

    public void remove(Activity activity) {
        Util.throwIfNull(activity);
        Util.throwIfNot(Looper.myLooper() == Looper.getMainLooper());
        if (this.mActivities.remove(activity)) {
            for (Listener onActivityRemoved : this.mListeners) {
                onActivityRemoved.onActivityRemoved(activity);
            }
        }
    }

    public List<Activity> getActivitiesView() {
        return this.mActivitiesUnmodifiable;
    }

    public Activity tryGetTopActivity() {
        if (this.mActivitiesUnmodifiable.isEmpty()) {
            return null;
        }
        return (Activity) this.mActivitiesUnmodifiable.get(this.mActivitiesUnmodifiable.size() - 1);
    }
}
