package io.rong.imkit.widget;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

public class LoadingDialogFragment extends BaseDialogFragment {
    private static final String ARGS_MESSAGE = "args_message";
    private static final String ARGS_TITLE = "args_title";

    public static LoadingDialogFragment newInstance(String str, String str2) {
        LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_TITLE, str);
        bundle.putString(ARGS_MESSAGE, str2);
        loadingDialogFragment.setArguments(bundle);
        return loadingDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog progressDialog = new ProgressDialog(getActivity());
        CharSequence string = getArguments().getString(ARGS_TITLE);
        CharSequence string2 = getArguments().getString(ARGS_MESSAGE);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(0);
        if (!TextUtils.isEmpty(string)) {
            progressDialog.setTitle(string);
        }
        if (!TextUtils.isEmpty(string2)) {
            progressDialog.setMessage(string2);
        }
        return progressDialog;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "LoadingDialogFragment");
    }
}
