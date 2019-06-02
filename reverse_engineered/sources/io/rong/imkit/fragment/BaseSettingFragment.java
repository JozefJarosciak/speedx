package io.rong.imkit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imlib.model.Conversation.ConversationType;

public abstract class BaseSettingFragment extends BaseFragment implements OnClickListener {
    CheckBox mCheckBox;
    ConversationType mConversationType;
    RelativeLayout mSettingItem;
    String mTargetId;
    TextView mTextView;

    protected abstract void initData();

    protected abstract void onSettingItemClick(View view);

    protected abstract int setSwitchBtnVisibility();

    protected abstract boolean setSwitchButtonEnabled();

    protected abstract String setTitle();

    protected abstract void toggleSwitch(boolean z);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            if (intent.getData() != null) {
                this.mConversationType = ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase());
                this.mTargetId = intent.getData().getQueryParameter("targetId");
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_fragment_base_setting, viewGroup, false);
        this.mTextView = (TextView) inflate.findViewById(C4974R.id.rc_title);
        this.mCheckBox = (CheckBox) inflate.findViewById(C4974R.id.rc_checkbox);
        this.mSettingItem = (RelativeLayout) inflate.findViewById(C4974R.id.rc_setting_item);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        this.mTextView.setText(setTitle());
        this.mCheckBox.setEnabled(setSwitchButtonEnabled());
        if (8 == setSwitchBtnVisibility()) {
            this.mCheckBox.setVisibility(8);
        } else if (setSwitchBtnVisibility() == 0) {
            this.mCheckBox.setVisibility(0);
        }
        this.mCheckBox.setOnClickListener(this);
        this.mSettingItem.setOnClickListener(this);
        initData();
        super.onActivityCreated(bundle);
    }

    public void onClick(View view) {
        if (view == this.mSettingItem) {
            onSettingItemClick(view);
        } else if (view == this.mCheckBox) {
            toggleSwitch(this.mCheckBox.isChecked());
        }
    }

    public void onDestroy() {
        super.onDestroy();
        RongContext.getInstance().getEventBus().unregister(this);
    }

    protected ConversationType getConversationType() {
        return this.mConversationType;
    }

    protected String getTargetId() {
        return this.mTargetId;
    }

    protected void setSwitchBtnStatus(boolean z) {
        this.mCheckBox.setChecked(z);
    }

    protected boolean getSwitchBtnStatus() {
        return this.mCheckBox.isChecked();
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onRestoreUI() {
        initData();
    }
}
