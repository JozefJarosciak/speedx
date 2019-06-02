package io.rong.imkit.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import com.baidu.mapapi.UIMsg.m_AppUI;
import java.util.Iterator;

public abstract class DispatchResultFragment extends UriFragment {
    public void onActivityResult(int i, int i2, Intent intent) {
        int i3 = i >> 12;
        if (i3 != 0) {
            Fragment offsetFragment = getOffsetFragment(i3 - 1, this);
            if (offsetFragment != null) {
                offsetFragment.onActivityResult(i & 4095, i2, intent);
                return;
            }
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    public void startActivityForResult(Fragment fragment, Intent intent, int i) {
        int fragmentOffset = getFragmentOffset(0, fragment, this);
        if (fragmentOffset > 15) {
            throw new RuntimeException("DispatchFragment only support 16 fragments。");
        } else if (i == -1) {
            startActivityForResult(intent, -1);
        } else if ((i & m_AppUI.MSG_SENSOR) != 0) {
            throw new IllegalArgumentException("Can only use lower 12 bits for requestCode");
        } else {
            startActivityForResult(intent, ((fragmentOffset + 1) << 12) + (i & 4095));
        }
    }

    private int getFragmentOffset(int i, Fragment fragment, Fragment fragment2) {
        if (fragment2 == null || fragment2.getChildFragmentManager() == null || fragment2.getChildFragmentManager().getFragments() == null) {
            return 0;
        }
        Iterator it = fragment2.getChildFragmentManager().getFragments().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Fragment fragment3 = (Fragment) it.next();
        int i2 = i + 1;
        if (fragment == fragment3) {
            return i2;
        }
        return getFragmentOffset(i2, fragment, fragment3);
    }

    private Fragment getOffsetFragment(int i, Fragment fragment) {
        if (i == 0) {
            return fragment;
        }
        for (Fragment fragment2 : getChildFragmentManager().getFragments()) {
            i--;
            if (i == 0) {
                return fragment2;
            }
            if (fragment2.getChildFragmentManager().getFragments() != null && fragment2.getChildFragmentManager().getFragments().size() > 0) {
                return getOffsetFragment(i, fragment2);
            }
        }
        return null;
    }
}
