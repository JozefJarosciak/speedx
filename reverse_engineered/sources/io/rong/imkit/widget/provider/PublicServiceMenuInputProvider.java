package io.rong.imkit.widget.provider;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.IPublicServiceMenuClickListener;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongKitIntent;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.widget.InputView;
import io.rong.imkit.widget.provider.InputProvider.MainInputProvider;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.PublicServiceMenu.PublicServiceMenuItemType;
import io.rong.imlib.model.PublicServiceMenuItem;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.message.PublicServiceCommandMessage;
import java.util.ArrayList;
import java.util.Iterator;

public class PublicServiceMenuInputProvider extends MainInputProvider implements OnTouchListener {
    Conversation conversation;
    Context mContext;

    /* renamed from: io.rong.imkit.widget.provider.PublicServiceMenuInputProvider$2 */
    class C51862 implements ISendMessageCallback {
        C51862() {
        }

        public void onAttached(Message message) {
        }

        public void onSuccess(Message message) {
        }

        public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    private class PopupMenu {
        View container;
        ArrayList<PublicServiceMenuItem> list;
        PopupWindow popupWindow = new PopupWindow(this.container, -2, -2);

        /* renamed from: io.rong.imkit.widget.provider.PublicServiceMenuInputProvider$PopupMenu$1 */
        class C51871 implements OnClickListener {
            C51871() {
            }

            public void onClick(View view) {
                PublicServiceMenuInputProvider.this.onMenuItemSelect((PublicServiceMenuItem) view.getTag());
                PopupMenu.this.dismiss();
            }
        }

        public PopupMenu(Context context, ArrayList<PublicServiceMenuItem> arrayList) {
            this.list = arrayList;
            this.container = LayoutInflater.from(context).inflate(C4974R.layout.rc_item_public_service_input_menus, null);
            LinearLayout linearLayout = (LinearLayout) this.container.findViewById(C4974R.id.rc_layout);
            this.container.setLayoutParams(new LayoutParams(-1, -1, 1.0f));
            linearLayout.setClickable(true);
            linearLayout.setFocusable(true);
            setupMenu(linearLayout);
        }

        public void showAtLocation(View view) {
            this.popupWindow.setBackgroundDrawable(new ColorDrawable());
            this.container.measure(0, 0);
            int[] iArr = new int[2];
            int measuredWidth = this.container.getMeasuredWidth();
            view.getLocationOnScreen(iArr);
            this.popupWindow.showAtLocation(view, 83, iArr[0] + ((view.getWidth() - measuredWidth) / 2), view.getHeight() + 10);
            this.popupWindow.setOutsideTouchable(true);
            this.popupWindow.setFocusable(true);
            this.popupWindow.update();
        }

        public void dismiss() {
            this.popupWindow.dismiss();
        }

        void setupMenu(LinearLayout linearLayout) {
            linearLayout.removeAllViews();
            for (int i = 0; i < this.list.size(); i++) {
                LinearLayout linearLayout2 = (LinearLayout) ((LayoutInflater) PublicServiceMenuInputProvider.this.mContext.getSystemService("layout_inflater")).inflate(C4974R.layout.rc_item_public_service_input_menu_item, null);
                this.container.setLayoutParams(new LayoutParams(-2, -2, 1.0f));
                linearLayout2.setFocusable(true);
                linearLayout2.setTag(this.list.get(i));
                TextView textView = (TextView) linearLayout2.findViewById(C4974R.id.rc_menu_item_text);
                View findViewById = linearLayout2.findViewById(C4974R.id.rc_menu_line);
                if (i + 1 == this.list.size()) {
                    findViewById.setVisibility(8);
                }
                textView.setText(((PublicServiceMenuItem) this.list.get(i)).getName());
                linearLayout2.setOnClickListener(new C51871());
                linearLayout.addView(linearLayout2);
            }
            linearLayout.setVisibility(0);
        }
    }

    public PublicServiceMenuInputProvider(RongContext rongContext) {
        super(rongContext);
        this.mContext = rongContext;
    }

    public Drawable obtainSwitchDrawable(Context context) {
        return context.getResources().getDrawable(C4974R.drawable.rc_ic_voice);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, InputView inputView) {
        this.conversation = getCurrentConversation();
        PublicServiceProfile publicServiceInfoFromCache = RongContext.getInstance().getPublicServiceInfoFromCache(ConversationKey.obtain(this.conversation.getTargetId(), this.conversation.getConversationType()).getKey());
        if (publicServiceInfoFromCache != null) {
            ArrayList menuItems = publicServiceInfoFromCache.getMenu().getMenuItems();
            if (!(menuItems == null || menuItems.size() == 0)) {
                viewGroup.removeAllViews();
                Iterator it = menuItems.iterator();
                while (it.hasNext()) {
                    final PublicServiceMenuItem publicServiceMenuItem = (PublicServiceMenuItem) it.next();
                    LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(C4974R.layout.rc_item_public_service_input_menu, null);
                    linearLayout.setLayoutParams(new LayoutParams(-1, -1, 1.0f));
                    ((TextView) linearLayout.findViewById(C4974R.id.rc_title)).setText(publicServiceMenuItem.getName());
                    ImageView imageView = (ImageView) linearLayout.findViewById(C4974R.id.rc_icon);
                    if (publicServiceMenuItem.getSubMenuItems().size() > 0) {
                        imageView.setImageDrawable(this.mContext.getResources().getDrawable(C4974R.drawable.rc_ic_trangle));
                    }
                    linearLayout.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (publicServiceMenuItem.getSubMenuItems() == null || publicServiceMenuItem.getSubMenuItems().size() == 0) {
                                PublicServiceMenuInputProvider.this.onMenuItemSelect(publicServiceMenuItem);
                            } else {
                                new PopupMenu(PublicServiceMenuInputProvider.this.mContext, publicServiceMenuItem.getSubMenuItems()).showAtLocation(view);
                            }
                        }
                    });
                    viewGroup.addView(linearLayout);
                }
            }
        }
        return viewGroup;
    }

    public void onActive(Context context) {
    }

    public void onInactive(Context context) {
    }

    private void onMenuItemSelect(PublicServiceMenuItem publicServiceMenuItem) {
        if (publicServiceMenuItem.getType().equals(PublicServiceMenuItemType.View)) {
            IPublicServiceMenuClickListener publicServiceMenuClickListener = RongContext.getInstance().getPublicServiceMenuClickListener();
            if (publicServiceMenuClickListener == null || !publicServiceMenuClickListener.onClick(this.conversation.getConversationType(), this.conversation.getTargetId(), publicServiceMenuItem)) {
                Intent intent = new Intent(RongKitIntent.RONG_INTENT_ACTION_WEBVIEW);
                intent.setPackage(this.mContext.getPackageName());
                intent.addFlags(268435456);
                intent.putExtra("url", publicServiceMenuItem.getUrl());
                this.mContext.startActivity(intent);
            }
        }
        RongIMClient.getInstance().sendMessage(this.conversation.getConversationType(), this.conversation.getTargetId(), PublicServiceCommandMessage.obtain(publicServiceMenuItem), null, null, new C51862());
    }

    public void onSwitch(Context context) {
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
