package io.rong.imkit.widget;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

public class AlterDialogFragment extends BaseDialogFragment {
    private static final String ARGS_CANCEL_BTN_TXT = "args_cancel_button_text";
    private static final String ARGS_MESSAGE = "args_message";
    private static final String ARGS_OK_BTN_TXT = "args_ok_button_text";
    private static final String ARGS_TITLE = "args_title";
    private AlterDialogBtnListener mAlterDialogBtnListener;

    public interface AlterDialogBtnListener {
        void onDialogNegativeClick(AlterDialogFragment alterDialogFragment);

        void onDialogPositiveClick(AlterDialogFragment alterDialogFragment);
    }

    /* renamed from: io.rong.imkit.widget.AlterDialogFragment$1 */
    class C51411 implements OnClickListener {
        C51411() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (AlterDialogFragment.this.mAlterDialogBtnListener != null) {
                AlterDialogFragment.this.mAlterDialogBtnListener.onDialogPositiveClick(AlterDialogFragment.this);
            }
        }
    }

    /* renamed from: io.rong.imkit.widget.AlterDialogFragment$2 */
    class C51422 implements OnClickListener {
        C51422() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (AlterDialogFragment.this.mAlterDialogBtnListener != null) {
                AlterDialogFragment.this.mAlterDialogBtnListener.onDialogNegativeClick(AlterDialogFragment.this);
            }
        }
    }

    public static AlterDialogFragment newInstance(String str, String str2, String str3, String str4) {
        AlterDialogFragment alterDialogFragment = new AlterDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_TITLE, str);
        bundle.putString(ARGS_MESSAGE, str2);
        bundle.putString(ARGS_CANCEL_BTN_TXT, str3);
        bundle.putString(ARGS_OK_BTN_TXT, str4);
        alterDialogFragment.setArguments(bundle);
        return alterDialogFragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        CharSequence string = getArguments().getString(ARGS_TITLE);
        CharSequence string2 = getArguments().getString(ARGS_MESSAGE);
        CharSequence string3 = getArguments().getString(ARGS_CANCEL_BTN_TXT);
        CharSequence string4 = getArguments().getString(ARGS_OK_BTN_TXT);
        Builder builder = new Builder(getActivity());
        if (!TextUtils.isEmpty(string)) {
            builder.setTitle(string);
        }
        if (!TextUtils.isEmpty(string2)) {
            builder.setMessage(string2);
        }
        if (!TextUtils.isEmpty(string4)) {
            builder.setPositiveButton(string4, new C51411());
        }
        if (!TextUtils.isEmpty(string3)) {
            builder.setNegativeButton(string3, new C51422());
        }
        return builder.create();
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "AlterDialogFragment");
    }

    public void setOnAlterDialogBtnListener(AlterDialogBtnListener alterDialogBtnListener) {
        this.mAlterDialogBtnListener = alterDialogBtnListener;
    }
}
