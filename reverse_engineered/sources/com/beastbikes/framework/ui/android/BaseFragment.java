package com.beastbikes.framework.ui.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.beastbikes.framework.android.p056e.C1376d;
import com.beastbikes.framework.android.p056e.C2794a;
import com.beastbikes.framework.android.p056e.C2795b;
import com.beastbikes.framework.android.p056e.C2796c;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.ViewIntrospector;

public abstract class BaseFragment extends Fragment implements C1376d {
    private C2794a asyncTaskQueue;
    private C2796c requestQueueFactory;

    public C2796c getRequestQueueFactory() {
        return this.requestQueueFactory;
    }

    public C2794a getAsyncTaskQueue() {
        return this.asyncTaskQueue;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        Class cls = getClass();
        C1459b c1459b = (C1459b) cls.getAnnotation(C1459b.class);
        C1460c c1460c = (C1460c) cls.getAnnotation(C1460c.class);
        if (c1459b != null) {
            inflate = layoutInflater.inflate(c1459b.a(), null);
        } else {
            inflate = super.onCreateView(layoutInflater, viewGroup, bundle);
        }
        ViewIntrospector.introspect(inflate, (Object) this);
        setHasOptionsMenu(c1460c != null);
        return inflate;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        C1460c c1460c = (C1460c) getClass().getAnnotation(C1460c.class);
        if (c1460c != null) {
            menuInflater.inflate(c1460c.a(), menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (getActivity() != null) {
                    getActivity().finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onAttach(Activity activity) {
        this.requestQueueFactory = C2796c.m13744a((Context) activity);
        this.asyncTaskQueue = C2795b.m13743a(activity);
        super.onAttach(activity);
    }

    public void onStop() {
        this.asyncTaskQueue.m13742a(getActivity());
        super.onStop();
    }

    public void onDetach() {
        this.asyncTaskQueue.m13742a(getActivity());
        this.asyncTaskQueue.m13741a();
        super.onDetach();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.requestQueueFactory.m13746a(getActivity());
        this.asyncTaskQueue.m13742a(getActivity());
        this.asyncTaskQueue.m13741a();
    }
}
