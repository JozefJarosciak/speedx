package io.rong.imkit.widget;

import android.support.v4.app.DialogFragment;
import android.view.View;

public class BaseDialogFragment extends DialogFragment {
    protected <T extends View> T getView(View view, int i) {
        return view.findViewById(i);
    }
}
