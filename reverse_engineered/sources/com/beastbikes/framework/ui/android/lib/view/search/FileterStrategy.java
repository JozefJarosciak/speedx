package com.beastbikes.framework.ui.android.lib.view.search;

import java.util.List;

interface FileterStrategy<T> {
    CharSequence getCharSequence(Object obj);

    boolean isMatched(T t, CharSequence charSequence);

    void onDataChanged(List<T> list);
}
