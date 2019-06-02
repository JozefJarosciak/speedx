package io.rong.imkit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imkit.widget.provider.InputProvider.MainInputProvider;
import io.rong.imkit.widget.provider.VoiceInputProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.CustomServiceMode;
import java.util.ArrayList;
import java.util.List;

public class InputView extends LinearLayout {
    private static final String TAG = "InputView";
    int center;
    boolean collapsed = true;
    private CustomServiceMode currentType;
    IInputBoardListener inputBoardListener;
    int left;
    FrameLayout mCustomLayout;
    LinearLayout mCustomMenuLayout;
    FrameLayout mExtendLayout;
    ImageView mIcon1;
    ImageView mIcon2;
    RelativeLayout mInputLayout;
    LinearLayout mInputMenuLayout;
    LinearLayout mInputMenuSwitchLayout;
    volatile MainInputProvider mMainProvider;
    volatile MainInputProvider mMenuProvider;
    ImageView mMenuSwitcher1;
    ImageView mMenuSwitcher2;
    LinearLayout mPluginsLayout;
    List<InputProvider> mProviderList;
    volatile MainInputProvider mSlaveProvider;
    int mStyle;
    LinearLayout mSwitcherLayout;
    FrameLayout mToggleLayout;
    View mView;
    FrameLayout mWidgetLayout;
    private OnInfoButtonClick onInfoButtonClick;
    int originalBottom = 0;
    int originalTop = 0;
    int right;
    private OnClickListener switcherListener;

    /* renamed from: io.rong.imkit.widget.InputView$1 */
    class C51461 implements OnClickListener {
        C51461() {
        }

        public void onClick(View view) {
            if (InputView.this.switcherListener != null) {
                InputView.this.switcherListener.onClick(view);
            }
        }
    }

    /* renamed from: io.rong.imkit.widget.InputView$2 */
    class C51472 implements OnClickListener {
        C51472() {
        }

        public void onClick(View view) {
            InputView.this.changeMainProvider(view, InputView.this.mSlaveProvider, InputView.this.mMainProvider);
        }
    }

    /* renamed from: io.rong.imkit.widget.InputView$3 */
    class C51483 implements OnClickListener {
        C51483() {
        }

        public void onClick(View view) {
            InputView.this.changeMainProvider(view, InputView.this.mSlaveProvider, InputView.this.mMainProvider);
        }
    }

    /* renamed from: io.rong.imkit.widget.InputView$4 */
    class C51494 implements OnClickListener {
        C51494() {
        }

        public void onClick(View view) {
            if (InputView.this.currentType == null || !InputView.this.currentType.equals(CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT_FIRST)) {
                if (InputView.this.onInfoButtonClick != null) {
                    InputView.this.onInfoButtonClick.onClick(view);
                } else {
                    InputView.this.changeMainProvider(view, InputView.this.mSlaveProvider, InputView.this.mMainProvider);
                }
            } else if (InputView.this.switcherListener != null) {
                InputView.this.switcherListener.onClick(view);
            }
        }
    }

    /* renamed from: io.rong.imkit.widget.InputView$5 */
    class C51505 implements OnClickListener {
        C51505() {
        }

        public void onClick(View view) {
            if (InputView.this.currentType == null || !InputView.this.currentType.equals(CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT_FIRST)) {
                if (InputView.this.onInfoButtonClick != null) {
                    InputView.this.onInfoButtonClick.onClick(view);
                } else {
                    InputView.this.changeMainProvider(view, InputView.this.mSlaveProvider, InputView.this.mMainProvider);
                }
            } else if (InputView.this.switcherListener != null) {
                InputView.this.switcherListener.onClick(view);
            }
        }
    }

    public enum Event {
        ACTION,
        INACTION,
        DESTROY,
        CLICK
    }

    class ExtendClickListener implements OnClickListener {
        ExtendClickListener() {
        }

        public void onClick(View view) {
            if (InputView.this.mPluginsLayout.getVisibility() == 8 || InputView.this.mExtendLayout.getVisibility() == 0) {
                InputView.this.onExtendProviderActive(view.getContext());
                InputView.this.mPluginsLayout.setVisibility(0);
                if (InputView.this.mMainProvider instanceof VoiceInputProvider) {
                    InputView.this.setInputProvider(InputView.this.mSlaveProvider, InputView.this.mMainProvider);
                }
            } else if (InputView.this.mPluginsLayout.getVisibility() == 0) {
                InputView.this.onProviderInactive(view.getContext());
            }
            RongContext.getInstance().getEventBus().post(Event.CLICK);
        }
    }

    public interface IInputBoardListener {
        void onBoardCollapsed();

        void onBoardExpanded(int i);
    }

    class InputClickListener implements OnClickListener {
        InputClickListener() {
        }

        public void onClick(final View view) {
            RLog.m19419d(InputView.TAG, "InputClickListener change to input menu");
            InputView.this.mMainProvider.onSwitch(view.getContext());
            InputView.this.mPluginsLayout.setVisibility(8);
            InputView.this.mExtendLayout.setVisibility(8);
            InputView.this.mInputLayout.startAnimation(InputView.this.createPopupAnimOut(view.getContext()));
            InputView.this.mInputMenuLayout.postDelayed(new Runnable() {
                public void run() {
                    InputView.this.mInputLayout.clearAnimation();
                    InputView.this.mInputLayout.setVisibility(8);
                    InputView.this.mInputMenuLayout.startAnimation(InputView.this.createPopupAnimIn(view.getContext()));
                    InputView.this.mInputMenuLayout.setVisibility(0);
                }
            }, 310);
        }
    }

    class InputMenuClickListener implements OnClickListener {
        InputMenuClickListener() {
        }

        public void onClick(final View view) {
            RLog.m19419d(InputView.TAG, "InputMenuClickListener change to input");
            InputView.this.mInputMenuLayout.startAnimation(InputView.this.createPopupAnimOut(view.getContext()));
            InputView.this.mInputLayout.postDelayed(new Runnable() {
                public void run() {
                    InputView.this.mInputMenuLayout.clearAnimation();
                    InputView.this.mInputMenuLayout.setVisibility(8);
                    InputView.this.mInputLayout.startAnimation(InputView.this.createPopupAnimIn(view.getContext()));
                    InputView.this.mInputLayout.setVisibility(0);
                }
            }, 310);
        }
    }

    public interface OnInfoButtonClick {
        void onClick(View view);
    }

    enum Style {
        SCE(291),
        ECS(801),
        CES(561),
        CSE(531),
        SC(288),
        CS(33),
        EC(800),
        CE(35),
        C(32);
        
        private int value;

        private Style(int i) {
            this.value = 0;
            this.value = i;
        }
    }

    public InputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        View inflate = inflate(context, C4974R.layout.rc_wi_input, this);
        this.mView = inflate;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4974R.styleable.InputView);
        this.mStyle = obtainStyledAttributes.getInt(C4974R.styleable.InputView_RCStyle, 291);
        obtainStyledAttributes.recycle();
        this.mProviderList = new ArrayList();
        this.mSwitcherLayout = (LinearLayout) inflate.findViewById(C4974R.id.rc_switcher);
        this.mInputMenuSwitchLayout = (LinearLayout) inflate.findViewById(C4974R.id.rc_menu_switch);
        this.mMenuSwitcher1 = (ImageView) inflate.findViewById(C4974R.id.rc_switcher1);
        this.mMenuSwitcher2 = (ImageView) inflate.findViewById(C4974R.id.rc_switcher2);
        this.mInputMenuLayout = (LinearLayout) inflate.findViewById(C4974R.id.rc_input_menu);
        this.mInputLayout = (RelativeLayout) inflate.findViewById(16908297);
        this.mCustomLayout = (FrameLayout) inflate.findViewById(16908331);
        this.mWidgetLayout = (FrameLayout) inflate.findViewById(16908312);
        this.mExtendLayout = (FrameLayout) inflate.findViewById(C4974R.id.rc_ext);
        this.mToggleLayout = (FrameLayout) inflate.findViewById(16908311);
        this.mCustomMenuLayout = (LinearLayout) inflate.findViewById(C4974R.id.rc_input_custom_menu);
        this.mIcon1 = (ImageView) inflate.findViewById(16908295);
        this.mIcon2 = (ImageView) inflate.findViewById(16908296);
        this.mPluginsLayout = (LinearLayout) inflate.findViewById(C4974R.id.rc_plugins);
        this.left = (this.mStyle >> 8) % 16;
        this.center = (this.mStyle >> 4) % 16;
        this.right = this.mStyle % 16;
        this.mIcon2.setImageDrawable(getResources().getDrawable(C4974R.drawable.rc_ic_extend));
        this.mIcon2.setOnClickListener(new ExtendClickListener());
    }

    public void setOnInfoButtonClickListener(OnInfoButtonClick onInfoButtonClick) {
        this.onInfoButtonClick = onInfoButtonClick;
    }

    public void setExtendInputsVisibility(int i) {
        onProviderInactive(getContext());
        setPluginsLayoutVisibility(i);
    }

    public void setPluginsLayoutVisibility(int i) {
        this.mPluginsLayout.setVisibility(i);
    }

    public void setExtendLayoutVisibility(int i) {
        this.mExtendLayout.setVisibility(i);
    }

    public void setWidgetLayoutVisibility(int i) {
        this.mWidgetLayout.setVisibility(i);
    }

    private final void changeMainProvider(View view, MainInputProvider mainInputProvider, MainInputProvider mainInputProvider2) {
        this.mMainProvider.onSwitch(view.getContext());
        this.mPluginsLayout.setVisibility(8);
        this.mExtendLayout.setVisibility(8);
        setInputProvider(this.mSlaveProvider, this.mMainProvider);
    }

    private void setSCE() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(9, -1);
        this.mSwitcherLayout.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(11, -1);
        this.mToggleLayout.setLayoutParams(layoutParams);
        this.mToggleLayout.setVisibility(0);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(0, this.mToggleLayout.getId());
        layoutParams.addRule(1, this.mSwitcherLayout.getId());
        this.mCustomLayout.setLayoutParams(layoutParams);
    }

    private void setECS() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(9, -1);
        this.mToggleLayout.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(11, -1);
        this.mSwitcherLayout.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(0, this.mSwitcherLayout.getId());
        layoutParams.addRule(1, this.mToggleLayout.getId());
        this.mCustomLayout.setLayoutParams(layoutParams);
    }

    private void setCES() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(11, -1);
        this.mSwitcherLayout.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(0, this.mSwitcherLayout.getId());
        this.mToggleLayout.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(0, this.mToggleLayout.getId());
        this.mCustomLayout.setLayoutParams(layoutParams);
    }

    private void setCSE() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(11, -1);
        this.mToggleLayout.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(0, this.mToggleLayout.getId());
        this.mSwitcherLayout.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(0, this.mSwitcherLayout.getId());
        this.mCustomLayout.setLayoutParams(layoutParams);
    }

    private void setSC() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(9, -1);
        this.mSwitcherLayout.setLayoutParams(layoutParams);
        this.mToggleLayout.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(0, this.mToggleLayout.getId());
        layoutParams.addRule(1, this.mSwitcherLayout.getId());
        this.mCustomLayout.setLayoutParams(layoutParams);
    }

    private void setCS() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(11, -1);
        this.mSwitcherLayout.setLayoutParams(layoutParams);
        this.mToggleLayout.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(1, this.mToggleLayout.getId());
        layoutParams.addRule(0, this.mSwitcherLayout.getId());
        this.mCustomLayout.setLayoutParams(layoutParams);
    }

    private void setEC() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(9, -1);
        this.mToggleLayout.setLayoutParams(layoutParams);
        this.mSwitcherLayout.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(1, this.mToggleLayout.getId());
        this.mCustomLayout.setLayoutParams(layoutParams);
    }

    private void setCE() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(11, -1);
        this.mToggleLayout.setVisibility(0);
        this.mToggleLayout.setLayoutParams(layoutParams);
        this.mSwitcherLayout.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(0, this.mToggleLayout.getId());
        this.mCustomLayout.setLayoutParams(layoutParams);
    }

    public void setOnlyRobotInputType() {
        this.currentType = CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT;
        setC();
    }

    public void setPriorRobotInputType() {
        if (this.currentType == null || this.currentType != CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT_FIRST) {
            this.currentType = CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT_FIRST;
            this.mIcon1.setImageResource(C4974R.drawable.rc_ic_admin_selector);
            this.mIcon1.setOnClickListener(new C51461());
            this.mIcon1.setVisibility(8);
            setSC();
        } else if (this.currentType == CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT_FIRST) {
            this.mIcon1.setVisibility(0);
        }
    }

    public void setOnlyAdminInputType() {
        this.currentType = CustomServiceMode.CUSTOM_SERVICE_MODE_HUMAN;
        if (this.mSlaveProvider != null) {
            this.mIcon1.setVisibility(0);
            this.mIcon1.setImageDrawable(this.mSlaveProvider.obtainSwitchDrawable(getContext()));
            this.mIcon1.setOnClickListener(new C51472());
        }
        setSCE();
    }

    public void setNoServiceType() {
        this.currentType = CustomServiceMode.CUSTOM_SERVICE_MODE_NO_SERVICE;
        if (this.mSlaveProvider != null) {
            this.mIcon1.setVisibility(0);
            this.mIcon1.setImageDrawable(this.mSlaveProvider.obtainSwitchDrawable(getContext()));
            this.mIcon1.setOnClickListener(new C51483());
        }
        setSCE();
    }

    public void setOnSwitcherListener(OnClickListener onClickListener) {
        this.switcherListener = onClickListener;
    }

    private void setC() {
        this.mSwitcherLayout.setVisibility(8);
        this.mToggleLayout.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        this.mCustomLayout.setLayoutParams(layoutParams);
    }

    public void setInputProvider(MainInputProvider mainInputProvider, MainInputProvider mainInputProvider2) {
        this.mMainProvider = mainInputProvider;
        this.mSlaveProvider = mainInputProvider2;
        if (this.mMenuProvider == null) {
            this.mInputMenuSwitchLayout.setVisibility(8);
        }
        this.mCustomLayout.removeAllViews();
        switch (this.mStyle) {
            case 32:
                setC();
                break;
            case 33:
                setCS();
                break;
            case 35:
                setCE();
                break;
            case 288:
                setSC();
                break;
            case 291:
                setSCE();
                break;
            case 531:
                setCSE();
                break;
            case 561:
                setCES();
                break;
            case 800:
                setEC();
                break;
            case 801:
                setECS();
                break;
        }
        if (this.mSlaveProvider != null) {
            this.mIcon1.setImageDrawable(this.mSlaveProvider.obtainSwitchDrawable(getContext()));
            this.mIcon1.setOnClickListener(new C51494());
        }
        this.mMainProvider.onCreateView(LayoutInflater.from(getContext()), this.mCustomLayout, this);
    }

    public void setInputProviderForCS(MainInputProvider mainInputProvider, MainInputProvider mainInputProvider2) {
        this.mMainProvider = mainInputProvider;
        this.mSlaveProvider = mainInputProvider2;
        if (this.mMenuProvider == null) {
            this.mInputMenuSwitchLayout.setVisibility(8);
        }
        this.mCustomLayout.removeAllViews();
        setPriorRobotInputType();
        if (this.mSlaveProvider != null) {
            this.mIcon1.setImageResource(C4974R.drawable.rc_ic_admin_selector);
            this.mIcon1.setOnClickListener(new C51505());
        }
        this.mMainProvider.onCreateView(LayoutInflater.from(getContext()), this.mCustomLayout, this);
    }

    private Animation createPopupAnimIn(Context context) {
        Animation animationSet = new AnimationSet(context, null);
        animationSet.setFillAfter(true);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 150.0f, 0.0f);
        translateAnimation.setDuration(300);
        animationSet.addAnimation(translateAnimation);
        return animationSet;
    }

    private Animation createPopupAnimOut(Context context) {
        Animation animationSet = new AnimationSet(context, null);
        animationSet.setFillAfter(true);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 150.0f);
        translateAnimation.setDuration(300);
        animationSet.addAnimation(translateAnimation);
        return animationSet;
    }

    public void setInputProviderEx(MainInputProvider mainInputProvider, MainInputProvider mainInputProvider2, MainInputProvider mainInputProvider3) {
        this.mMenuProvider = mainInputProvider3;
        setInputProvider(mainInputProvider, mainInputProvider2);
        if (mainInputProvider3 != null && this.mMenuSwitcher1 != null) {
            this.mInputMenuSwitchLayout.setVisibility(0);
            mainInputProvider3.onCreateView(LayoutInflater.from(getContext()), this.mCustomMenuLayout, this);
            this.mInputMenuSwitchLayout.setOnClickListener(new InputClickListener());
            this.mMenuSwitcher2.setOnClickListener(new InputMenuClickListener());
            this.mMainProvider.onSwitch(getContext());
            this.mPluginsLayout.setVisibility(8);
            this.mExtendLayout.setVisibility(8);
            this.mInputLayout.setVisibility(8);
            this.mInputMenuLayout.setVisibility(0);
        }
    }

    public void setExtendProvider(List<ExtendProvider> list, ConversationType conversationType) {
        this.mProviderList.clear();
        for (ExtendProvider add : list) {
            this.mProviderList.add(add);
        }
        int i = 1;
        for (ExtendProvider add2 : list) {
            i++;
            add2.setIndex(i);
        }
        RongPluginPager rongPluginPager = new RongPluginPager(conversationType, this.mPluginsLayout);
    }

    public ViewGroup getExtendLayout() {
        return this.mExtendLayout;
    }

    public FrameLayout getToggleLayout() {
        return this.mToggleLayout;
    }

    public ImageView getIcon1() {
        return this.mIcon1;
    }

    public void onProviderActive(Context context) {
        if (this.mMainProvider != null) {
            this.mMainProvider.onActive(context);
        }
        if (this.mSlaveProvider != null) {
            this.mSlaveProvider.onActive(context);
        }
        if (this.mPluginsLayout.getVisibility() == 0) {
            this.mPluginsLayout.setVisibility(8);
        }
        if (this.mExtendLayout.getVisibility() == 0) {
            this.mExtendLayout.setVisibility(8);
        }
        RongContext.getInstance().getEventBus().post(Event.ACTION);
    }

    public void onProviderInactive(Context context) {
        if (this.mMainProvider != null) {
            this.mMainProvider.onInactive(context);
        }
        if (this.mSlaveProvider != null) {
            this.mSlaveProvider.onInactive(context);
        }
        if (this.mPluginsLayout.getVisibility() == 0) {
            this.mPluginsLayout.setVisibility(8);
        }
        if (this.mExtendLayout.getVisibility() == 0) {
            this.mExtendLayout.setVisibility(8);
        }
        RongContext.getInstance().getEventBus().post(Event.INACTION);
    }

    public void onExtendProviderActive(Context context) {
        if (this.mMainProvider != null) {
            this.mMainProvider.onInactive(context);
        }
        if (this.mSlaveProvider != null) {
            this.mSlaveProvider.onInactive(context);
        }
        if (this.mPluginsLayout.getVisibility() == 0) {
            this.mPluginsLayout.setVisibility(8);
        }
        if (this.mExtendLayout.getVisibility() == 0) {
            this.mExtendLayout.setVisibility(8);
        }
        RongContext.getInstance().getEventBus().post(Event.ACTION);
    }

    public void onEmojiProviderActive(Context context) {
        if (this.mMainProvider != null) {
            this.mMainProvider.onInactive(context);
        }
        if (this.mSlaveProvider != null) {
            this.mSlaveProvider.onInactive(context);
        }
        if (this.mPluginsLayout.getVisibility() == 0) {
            this.mPluginsLayout.setVisibility(8);
        }
        if (this.mExtendLayout.getVisibility() == 0) {
            this.mExtendLayout.setVisibility(8);
        }
        RongContext.getInstance().getEventBus().post(Event.ACTION);
    }

    public void setInputBoardListener(IInputBoardListener iInputBoardListener) {
        this.inputBoardListener = iInputBoardListener;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.originalTop != 0) {
            if (this.originalTop > i2) {
                if (this.originalBottom > i4 && this.inputBoardListener != null && this.collapsed) {
                    this.collapsed = false;
                    this.inputBoardListener.onBoardExpanded(this.originalBottom - i2);
                } else if (this.collapsed && this.inputBoardListener != null) {
                    this.collapsed = false;
                    this.inputBoardListener.onBoardExpanded(i4 - i2);
                }
            } else if (!(this.collapsed || this.inputBoardListener == null)) {
                this.collapsed = true;
                this.inputBoardListener.onBoardCollapsed();
            }
        }
        if (this.originalTop == 0) {
            this.originalTop = i2;
            this.originalBottom = i4;
        }
    }
}
