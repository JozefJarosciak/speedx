package io.rong.imkit.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.Event.PublicServiceFollowableEvent;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.adapter.BaseAdapter;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.PublicServiceProfileList;
import java.util.Iterator;

public class PublicServiceSubscribeListFragment extends DispatchResultFragment {
    private PublicServiceListAdapter mAdapter;
    private ListView mListView;

    /* renamed from: io.rong.imkit.fragment.PublicServiceSubscribeListFragment$1 */
    class C50681 implements OnItemClickListener {
        C50681() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            PublicServiceProfile item = PublicServiceSubscribeListFragment.this.mAdapter.getItem(i);
            RongIM.getInstance().startConversation(PublicServiceSubscribeListFragment.this.getActivity(), item.getConversationType(), item.getTargetId(), item.getName());
        }
    }

    /* renamed from: io.rong.imkit.fragment.PublicServiceSubscribeListFragment$2 */
    class C50712 implements OnItemLongClickListener {
        C50712() {
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
            String[] strArr = new String[1];
            final PublicServiceProfile item = PublicServiceSubscribeListFragment.this.mAdapter.getItem(i);
            if (item.getConversationType() == ConversationType.PUBLIC_SERVICE) {
                strArr[0] = PublicServiceSubscribeListFragment.this.getActivity().getString(C4974R.string.rc_pub_service_info_unfollow);
            }
            ArraysDialogFragment.newInstance(item.getName(), strArr).setArraysDialogItemListener(new OnArraysDialogItemListener() {

                /* renamed from: io.rong.imkit.fragment.PublicServiceSubscribeListFragment$2$1$1 */
                class C50691 extends RongIMClient$OperationCallback {
                    C50691() {
                    }

                    public void onSuccess() {
                        PublicServiceSubscribeListFragment.this.mAdapter.remove(i);
                        PublicServiceSubscribeListFragment.this.mAdapter.notifyDataSetChanged();
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                }

                public void OnArraysDialogItemClick(DialogInterface dialogInterface, int i) {
                    PublicServiceType publicServiceType = null;
                    if (item.getConversationType() == ConversationType.APP_PUBLIC_SERVICE) {
                        publicServiceType = PublicServiceType.APP_PUBLIC_SERVICE;
                    } else if (item.getConversationType() == ConversationType.PUBLIC_SERVICE) {
                        publicServiceType = PublicServiceType.PUBLIC_SERVICE;
                    } else {
                        System.err.print("the public service type is error!!");
                    }
                    RongIMClient.getInstance().unsubscribePublicService(publicServiceType, item.getTargetId(), new C50691());
                }
            }).show(PublicServiceSubscribeListFragment.this.getFragmentManager());
            return true;
        }
    }

    /* renamed from: io.rong.imkit.fragment.PublicServiceSubscribeListFragment$3 */
    class C50723 extends ResultCallback<PublicServiceProfileList> {
        C50723() {
        }

        public void onSuccess(PublicServiceProfileList publicServiceProfileList) {
            Iterator it = publicServiceProfileList.getPublicServiceData().iterator();
            while (it.hasNext()) {
                RongUserInfoManager.getInstance().setPublicServiceProfile((PublicServiceProfile) it.next());
            }
            PublicServiceSubscribeListFragment.this.mAdapter.clear();
            PublicServiceSubscribeListFragment.this.mAdapter.addCollection(publicServiceProfileList.getPublicServiceData());
            PublicServiceSubscribeListFragment.this.mAdapter.notifyDataSetChanged();
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    private class PublicServiceListAdapter extends BaseAdapter<PublicServiceProfile> {
        LayoutInflater mInflater;

        class ViewHolder {
            TextView introduction;
            TextView name;
            AsyncImageView portrait;

            ViewHolder() {
            }
        }

        public PublicServiceListAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        protected View newView(Context context, int i, ViewGroup viewGroup) {
            View inflate = this.mInflater.inflate(C4974R.layout.rc_item_public_service_list, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.portrait = (AsyncImageView) inflate.findViewById(C4974R.id.portrait);
            viewHolder.name = (TextView) inflate.findViewById(C4974R.id.name);
            viewHolder.introduction = (TextView) inflate.findViewById(C4974R.id.introduction);
            inflate.setTag(viewHolder);
            return inflate;
        }

        protected void bindView(View view, int i, PublicServiceProfile publicServiceProfile) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (publicServiceProfile != null) {
                viewHolder.portrait.setResource(publicServiceProfile.getPortraitUri());
                viewHolder.name.setText(publicServiceProfile.getName());
                viewHolder.introduction.setText(publicServiceProfile.getIntroduction());
            }
        }

        public int getCount() {
            return super.getCount();
        }

        public PublicServiceProfile getItem(int i) {
            return (PublicServiceProfile) super.getItem(i);
        }

        public long getItemId(int i) {
            return 0;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C4974R.layout.rc_fr_public_service_sub_list, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mListView = (ListView) view.findViewById(C4974R.id.rc_list);
        this.mListView.setOnItemClickListener(new C50681());
        this.mListView.setOnItemLongClickListener(new C50712());
        this.mAdapter = new PublicServiceListAdapter(getActivity());
        this.mListView.setAdapter(this.mAdapter);
        getDBData();
        RongContext.getInstance().getEventBus().register(this);
    }

    private void getDBData() {
        RongIM.getInstance().getPublicServiceList(new C50723());
    }

    protected void initFragment(Uri uri) {
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    public void onEvent(PublicServiceFollowableEvent publicServiceFollowableEvent) {
        if (publicServiceFollowableEvent != null) {
            getDBData();
        }
    }

    public void onDestroyView() {
        RongContext.getInstance().getEventBus().unregister(this);
        super.onDestroyView();
    }
}
