package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903360)
public class ClubFragment extends SessionFragment implements OnSharedPreferenceChangeListener {
    /* renamed from: a */
    public int f5082a;
    /* renamed from: b */
    public String f5083b;
    /* renamed from: c */
    private ClubManager f5084c;
    /* renamed from: d */
    private ClubInfoCompact f5085d;
    /* renamed from: e */
    private FragmentManager f5086e;
    /* renamed from: f */
    private SharedPreferences f5087f;
    /* renamed from: g */
    private C1802i f5088g;

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5084c = new ClubManager(getActivity());
        this.f5086e = getChildFragmentManager();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f5087f = getContext().getSharedPreferences(currentUser.getObjectId(), 0);
        }
        if (this.f5087f != null) {
            this.f5087f.registerOnSharedPreferenceChangeListener(this);
        }
        m6415a(false);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (TextUtils.isEmpty(currentUser.getClubId())) {
                menuInflater.inflate(C1373R.menu.club_activity_menu, menu);
                return;
            }
            menuInflater.inflate(C1373R.menu.clubfeed_info_menu, menu);
            if (this.f5084c.a() != 128) {
                menu.findItem(C1373R.id.menu_post_notice).setVisible(false);
            } else {
                menu.findItem(C1373R.id.menu_post_notice).setVisible(true);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_club_create:
                m6414a();
                break;
            case C1373R.id.menu_post_dynamic:
                intent = new Intent(getActivity(), ClubFeedPostActivity.class);
                if (this.f5085d != null) {
                    intent.putExtra("club_extra_id", this.f5085d.getObjectId());
                }
                startActivityForResult(intent, 4);
                break;
            case C1373R.id.menu_post_activity:
                startActivity(new Intent(getActivity(), ClubActivityReleaseActivity.class));
                break;
            case C1373R.id.menu_post_notice:
                intent = new Intent(getActivity(), ClubPostNoticeActivity.class);
                if (this.f5085d != null) {
                    intent.putExtra("club_info", this.f5085d);
                    intent.putExtra("notice", this.f5085d.getNotice());
                    intent.putExtra("club_id", this.f5085d.getObjectId());
                }
                startActivityForResult(intent, 4);
                break;
        }
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (Fragment onActivityResult : this.f5086e.getFragments()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f5087f != null) {
            this.f5087f.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        boolean z = false;
        if (str.contains("beast.club.status")) {
            int i = sharedPreferences.getInt(str, 0);
            if (i != 6 && i != 2) {
                if (i == 5 || i == 0) {
                    i = 0;
                    z = true;
                }
                m6410a(i, z);
            }
        }
    }

    /* renamed from: a */
    private void m6410a(int i, boolean z) {
        boolean z2 = false;
        if (i == 0) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                currentUser.setClubId("");
            }
            if (z) {
                this.f5087f.edit().putInt("beast.club.status", i).apply();
                z2 = true;
            }
        } else {
            z2 = true;
        }
        if (z2) {
            m6415a(true);
            getActivity().supportInvalidateOptionsMenu();
        }
    }

    /* renamed from: a */
    public void m6415a(boolean z) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (TextUtils.isEmpty(currentUser.getClubId())) {
                m6413d();
                return;
            }
            Bundle bundle = new Bundle();
            try {
                this.f5085d = this.f5084c.a(currentUser.getObjectId());
                if (this.f5085d == null || this.f5085d.getStatus() == 0) {
                    getActivity().setTitle(C1373R.string.club_info_title);
                    m6411a(bundle);
                }
                bundle.putSerializable("club_info", this.f5085d);
                bundle.putString("club_id", this.f5085d.getObjectId());
                bundle.putBoolean("is_statusChanged", z);
                getActivity().setTitle(this.f5085d.getName());
                m6411a(bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private void m6411a(Bundle bundle) {
        if (this.f5086e == null) {
            this.f5086e = getChildFragmentManager();
        }
        FragmentTransaction beginTransaction = this.f5086e.beginTransaction();
        Fragment fragment = (ClubFeedInfoFrag) this.f5086e.findFragmentByTag("tag_feed_info_frag");
        if (fragment == null) {
            fragment = (ClubFeedInfoFrag) Fragment.instantiate(getContext(), ClubFeedInfoFrag.class.getName(), bundle);
        }
        beginTransaction.replace(C1373R.id.fragment_club_linear_container, fragment);
        beginTransaction.addToBackStack("");
        beginTransaction.commitAllowingStateLoss();
    }

    /* renamed from: d */
    private void m6413d() {
        getActivity().setTitle(C1373R.string.club_info_title);
        if (this.f5086e == null) {
            this.f5086e = getChildFragmentManager();
        }
        FragmentTransaction beginTransaction = this.f5086e.beginTransaction();
        Fragment fragment = (ClubDiscoverFrag) this.f5086e.findFragmentByTag("tag_club_discover_frag");
        if (fragment == null) {
            fragment = (ClubDiscoverFrag) Fragment.instantiate(getContext(), ClubDiscoverFrag.class.getName(), null);
        }
        beginTransaction.replace(C1373R.id.fragment_club_linear_container, fragment);
        beginTransaction.addToBackStack("");
        beginTransaction.commitAllowingStateLoss();
    }

    /* renamed from: a */
    public void m6414a() {
        if (this.f5082a == 2) {
            C2621c c2621c = new C2621c(getActivity());
            c2621c.b(C1373R.string.club_dialog_joined_msg1);
            c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new ClubFragment$1(this, c2621c));
            c2621c.b(C1373R.string.cancel, new ClubFragment$2(this, c2621c));
            c2621c.a();
            return;
        }
        startActivityForResult(new Intent(getActivity(), ClubCreateActivity.class), 101);
    }

    /* renamed from: c */
    public void m6416c() {
        this.f5088g = new C1802i(getActivity(), getString(C1373R.string.club_info_waiting), false);
        this.f5088g.show();
        getAsyncTaskQueue().a(new ClubFragment$3(this), new String[0]);
    }
}
