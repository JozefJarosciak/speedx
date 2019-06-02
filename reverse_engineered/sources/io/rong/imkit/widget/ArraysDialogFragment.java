package io.rong.imkit.widget;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ArraysDialogFragment extends BaseDialogFragment {
    private static final String ARGS_ARRAYS = "args_arrays";
    private static final String ARGS_TITLE = "args_title";
    private int count;
    private OnArraysDialogItemListener mItemListener;

    public interface OnArraysDialogItemListener {
        void OnArraysDialogItemClick(DialogInterface dialogInterface, int i);
    }

    /* renamed from: io.rong.imkit.widget.ArraysDialogFragment$1 */
    class C51431 implements OnClickListener {
        C51431() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (ArraysDialogFragment.this.mItemListener != null) {
                ArraysDialogFragment.this.mItemListener.OnArraysDialogItemClick(dialogInterface, i);
            }
        }
    }

    public static ArraysDialogFragment newInstance(String str, String[] strArr) {
        ArraysDialogFragment arraysDialogFragment = new ArraysDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_TITLE, str);
        bundle.putStringArray(ARGS_ARRAYS, strArr);
        arraysDialogFragment.setArguments(bundle);
        return arraysDialogFragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        CharSequence string = getArguments().getString(ARGS_TITLE);
        CharSequence[] stringArray = getArguments().getStringArray(ARGS_ARRAYS);
        Builder builder = new Builder(getActivity());
        builder.setTitle(string);
        if (stringArray.length > 0 && stringArray[0] != null) {
            setCount(stringArray.length);
            builder.setItems(stringArray, new C51431());
        }
        return builder.create();
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public ArraysDialogFragment setArraysDialogItemListener(OnArraysDialogItemListener onArraysDialogItemListener) {
        this.mItemListener = onArraysDialogItemListener;
        return this;
    }

    public void show(FragmentManager fragmentManager) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(this, "ArraysDialogFragment");
        beginTransaction.commitAllowingStateLoss();
    }
}
