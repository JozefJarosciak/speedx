package com.beastbikes.framework.ui.android.lib.view.search;

import java.util.List;

public interface MySearchListener {
    void clearHistory(String str);

    CharSequence getHistoryCharSequence(Object obj);

    CharSequence getIntelligenceCharSequence(Object obj);

    String getSearchKey();

    void goSearch(String str, String str2);

    List<?> loadHistoryData(String str);

    List<?> loadIntelligenceData(String str, String str2);

    void onHistoryItemClicked(Object obj);

    void onIntelligenceItemClicked(Object obj);

    void recordHistory(String str, String str2);
}
