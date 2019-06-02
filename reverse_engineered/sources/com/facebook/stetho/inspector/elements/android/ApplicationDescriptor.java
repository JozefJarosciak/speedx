package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.elements.android.ActivityTracker.Listener;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

final class ApplicationDescriptor extends AbstractChainedDescriptor<Application> {
    private final ActivityTracker mActivityTracker = ActivityTracker.get();
    private final Map<Application, ElementContext> mElementToContextMap = Collections.synchronizedMap(new IdentityHashMap());

    private class ElementContext {
        private Application mElement;
        private final Listener mListener = new C31621();

        /* renamed from: com.facebook.stetho.inspector.elements.android.ApplicationDescriptor$ElementContext$1 */
        class C31621 implements Listener {
            C31621() {
            }

            public void onActivityAdded(Activity activity) {
            }

            public void onActivityRemoved(Activity activity) {
            }
        }

        public void hook(Application application) {
            this.mElement = application;
            ApplicationDescriptor.this.mActivityTracker.registerListener(this.mListener);
        }

        public void unhook() {
            ApplicationDescriptor.this.mActivityTracker.unregisterListener(this.mListener);
            this.mElement = null;
        }

        public List<Activity> getActivitiesList() {
            return ApplicationDescriptor.this.mActivityTracker.getActivitiesView();
        }
    }

    ApplicationDescriptor() {
    }

    private ElementContext getContext(Application application) {
        return (ElementContext) this.mElementToContextMap.get(application);
    }

    protected void onHook(Application application) {
        ElementContext elementContext = new ElementContext();
        elementContext.hook(application);
        this.mElementToContextMap.put(application, elementContext);
    }

    protected void onUnhook(Application application) {
        ((ElementContext) this.mElementToContextMap.remove(application)).unhook();
    }

    protected NodeType onGetNodeType(Application application) {
        return NodeType.ELEMENT_NODE;
    }

    protected void onGetChildren(Application application, Accumulator<Object> accumulator) {
        List activitiesList = getContext(application).getActivitiesList();
        for (int size = activitiesList.size() - 1; size >= 0; size--) {
            accumulator.store(activitiesList.get(size));
        }
    }
}
