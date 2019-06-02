package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import java.io.Serializable;

public interface Pageable<K> {
    void cacheData(String str, Serializable serializable, long j, K k);

    K chooseMaxId(K k, K k2);

    K defValue();

    boolean initIsLastPage(K k);

    K initMaxId(String str);
}
