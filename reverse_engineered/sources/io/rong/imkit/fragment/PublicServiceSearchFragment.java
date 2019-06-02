package io.rong.imkit.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.Event.PublicServiceFollowableEvent;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.LoadingDialogFragment;
import io.rong.imkit.widget.adapter.BaseAdapter;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$SearchType;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.PublicServiceProfileList;

public class PublicServiceSearchFragment extends DispatchResultFragment {
    private static final String TAG = "PublicServiceSearchFragment";
    private PublicServiceListAdapter mAdapter;
    private EditText mEditText;
    private ListView mListView;
    LoadingDialogFragment mLoadingDialogFragment;
    private Button mSearchBtn;

    /* renamed from: io.rong.imkit.fragment.PublicServiceSearchFragment$1 */
    class C50661 implements OnClickListener {

        /* renamed from: io.rong.imkit.fragment.PublicServiceSearchFragment$1$1 */
        class C50651 extends ResultCallback<PublicServiceProfileList> {
            C50651() {
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                PublicServiceSearchFragment.this.mLoadingDialogFragment.dismiss();
            }

            public void onSuccess(PublicServiceProfileList publicServiceProfileList) {
                PublicServiceSearchFragment.this.mAdapter.clear();
                PublicServiceSearchFragment.this.mAdapter.addCollection(publicServiceProfileList.getPublicServiceData());
                PublicServiceSearchFragment.this.mAdapter.notifyDataSetChanged();
                PublicServiceSearchFragment.this.mLoadingDialogFragment.dismiss();
            }
        }

        C50661() {
        }

        public void onClick(View view) {
            PublicServiceSearchFragment.this.mLoadingDialogFragment.show(PublicServiceSearchFragment.this.getFragmentManager());
            RongIM.getInstance().searchPublicService(RongIMClient$SearchType.EXACT, PublicServiceSearchFragment.this.mEditText.getText().toString(), new C50651());
        }
    }

    /* renamed from: io.rong.imkit.fragment.PublicServiceSearchFragment$2 */
    class C50672 implements OnItemClickListener {
        C50672() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Parcelable item = PublicServiceSearchFragment.this.mAdapter.getItem(i);
            if (item.isFollow()) {
                RongIM.getInstance().startConversation(PublicServiceSearchFragment.this.getActivity(), item.getConversationType(), item.getTargetId(), item.getName());
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("rong://" + view.getContext().getApplicationInfo().packageName).buildUpon().appendPath("publicServiceProfile").appendPath(item.getConversationType().getName().toLowerCase()).appendQueryParameter("targetId", item.getTargetId()).build());
            intent.putExtra(PublicServiceProfileFragment.AGS_PUBLIC_ACCOUNT_INFO, item);
            PublicServiceSearchFragment.this.startActivity(intent);
        }
    }

    private class PublicServiceListAdapter extends BaseAdapter<PublicServiceProfile> {
        LayoutInflater mInflater;

        class ViewHolder {
            TextView name;
            AsyncImageView portrait;

            ViewHolder() {
            }
        }

        public PublicServiceListAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        protected View newView(Context context, int i, ViewGroup viewGroup) {
            View inflate = this.mInflater.inflate(C4974R.layout.rc_item_public_service_search, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.portrait = (AsyncImageView) inflate.findViewById(C4974R.id.portrait);
            viewHolder.name = (TextView) inflate.findViewById(C4974R.id.name);
            inflate.setTag(viewHolder);
            return inflate;
        }

        protected void bindView(View view, int i, PublicServiceProfile publicServiceProfile) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (publicServiceProfile != null) {
                viewHolder.portrait.setResource(publicServiceProfile.getPortraitUri());
                viewHolder.name.setText(publicServiceProfile.getName());
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

    protected void initFragment(Uri uri) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C4974R.layout.rc_fr_public_service_search, viewGroup, false);
        this.mEditText = (EditText) inflate.findViewById(C4974R.id.rc_search_ed);
        this.mSearchBtn = (Button) inflate.findViewById(C4974R.id.rc_search_btn);
        this.mListView = (ListView) inflate.findViewById(C4974R.id.rc_search_list);
        RongContext.getInstance().getEventBus().register(this);
        return inflate;
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mLoadingDialogFragment = LoadingDialogFragment.newInstance("", getResources().getString(C4974R.string.rc_notice_data_is_loading));
        this.mSearchBtn.setOnClickListener(new C50661());
        this.mAdapter = new PublicServiceListAdapter(getActivity());
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new C50672());
    }

    public void onDestroy() {
        RongContext.getInstance().getEventBus().unregister(this);
        super.onDestroy();
    }

    public void onEventMainThread(PublicServiceFollowableEvent publicServiceFollowableEvent) {
        RLog.m19419d(TAG, "onEventMainThread PublicAccountIsFollowEvent, follow=" + publicServiceFollowableEvent.isFollow());
        if (publicServiceFollowableEvent != null) {
            getActivity().finish();
        }
    }
}
