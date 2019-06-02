package io.rong.imkit.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import io.rong.common.RLog;

public abstract class UriFragment extends BaseFragment {
    public static final String RONG_URI = "RONG_URI";
    private static final String TAG = "UriFragment";
    IActionBarHandler mBarHandler;
    private Uri mUri;

    protected interface IActionBarHandler {
        void onTitleChanged(CharSequence charSequence);

        void onUnreadCountChanged(int i);
    }

    protected abstract void initFragment(Uri uri);

    protected Bundle obtainUriBundle(Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RONG_URI, uri);
        return bundle;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mUri != null) {
            return;
        }
        if (bundle == null) {
            this.mUri = getActivity().getIntent().getData();
        } else {
            this.mUri = (Uri) bundle.getParcelable(RONG_URI);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getUri() != null) {
            initFragment(getUri());
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onViewStateRestored(Bundle bundle) {
        RLog.m19419d(TAG, "onViewStateRestored");
        super.onViewStateRestored(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(RONG_URI, getUri());
        super.onSaveInstanceState(bundle);
    }

    public void onRestoreUI() {
        if (getUri() != null) {
            initFragment(getUri());
        }
    }

    public void setActionBarHandler(IActionBarHandler iActionBarHandler) {
        this.mBarHandler = iActionBarHandler;
    }

    protected IActionBarHandler getActionBarHandler() {
        return this.mBarHandler;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public void setUri(Uri uri) {
        this.mUri = uri;
        if (this.mUri != null && isInLayout()) {
            initFragment(this.mUri);
        }
    }

    public boolean onBackPressed() {
        return false;
    }
}
