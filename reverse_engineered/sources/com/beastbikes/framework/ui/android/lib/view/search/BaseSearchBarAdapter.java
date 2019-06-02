package com.beastbikes.framework.ui.android.lib.view.search;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseSearchBarAdapter extends BaseAdapter implements Filterable {
    private static final String CLEAR_HISTORY = "清除历史纪录";
    private final List<Object> historyData;
    private List<Object> intelligenceData;
    private ArrayFilter mFilter;
    private final String searchKey;
    private final MySearchListener searchListener;
    private boolean showHistory = true;

    private class ArrayFilter extends Filter {
        private final List<Object> hDatas = new ArrayList();
        private final FilterResults historyResult = new FilterResults();
        private final List<Object> iDatas = new ArrayList();
        private final FilterResults intelligenceResult = new FilterResults();

        public CharSequence convertResultToString(Object obj) {
            if (obj.equals(BaseSearchBarAdapter.CLEAR_HISTORY)) {
                return "";
            }
            return BaseSearchBarAdapter.this.getStringFromItem(obj);
        }

        protected FilterResults performFiltering(CharSequence charSequence) {
            int i = 0;
            Collection loadHistoryData;
            if (charSequence == null || charSequence.length() < 1) {
                this.hDatas.clear();
                loadHistoryData = BaseSearchBarAdapter.this.searchListener.loadHistoryData(BaseSearchBarAdapter.this.searchKey);
                if (loadHistoryData != null && loadHistoryData.size() > 0) {
                    this.hDatas.addAll(loadHistoryData);
                    this.hDatas.add(BaseSearchBarAdapter.CLEAR_HISTORY);
                }
                this.historyResult.values = this.hDatas;
                FilterResults filterResults = this.historyResult;
                if (this.hDatas != null) {
                    i = this.hDatas.size();
                }
                filterResults.count = i;
                return this.historyResult;
            }
            this.iDatas.clear();
            this.intelligenceResult.count = 0;
            loadHistoryData = BaseSearchBarAdapter.this.searchListener.loadIntelligenceData(BaseSearchBarAdapter.this.searchKey, charSequence.toString());
            if (loadHistoryData != null && loadHistoryData.size() > 0) {
                this.iDatas.addAll(loadHistoryData);
            }
            this.intelligenceResult.values = this.iDatas;
            filterResults = this.intelligenceResult;
            if (this.iDatas != null) {
                i = this.iDatas.size();
            }
            filterResults.count = i;
            return this.intelligenceResult;
        }

        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults == this.historyResult) {
                BaseSearchBarAdapter.this.onDataChanged(true, (List) filterResults.values);
            } else {
                BaseSearchBarAdapter.this.onDataChanged(false, (List) filterResults.values);
            }
        }
    }

    public abstract View getView(int i, View view, ViewGroup viewGroup);

    public BaseSearchBarAdapter(MySearchListener mySearchListener) {
        this.searchListener = mySearchListener;
        this.searchKey = mySearchListener.getSearchKey();
        this.historyData = new ArrayList();
        this.intelligenceData = new ArrayList();
    }

    public boolean isShowHistory() {
        return this.showHistory;
    }

    public boolean isClear(int i) {
        Object item = getItem(i);
        if (item == null) {
            return false;
        }
        return item.equals(CLEAR_HISTORY);
    }

    private void onDataChanged(boolean z, List<Object> list) {
        this.showHistory = z;
        if (z) {
            this.historyData.clear();
            if (list != null && list.size() > 0) {
                this.historyData.addAll(list);
            }
        } else {
            this.intelligenceData.clear();
            if (list != null && list.size() > 0) {
                this.intelligenceData.addAll(list);
            }
        }
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new ArrayFilter();
        }
        return this.mFilter;
    }

    protected CharSequence getStringFromItem(Object obj) {
        if (this.historyData.contains(obj)) {
            return this.searchListener.getHistoryCharSequence(obj);
        }
        return this.searchListener.getIntelligenceCharSequence(obj);
    }

    public int getCount() {
        if (this.showHistory) {
            if (this.historyData != null) {
                return this.historyData.size();
            }
            return 0;
        } else if (this.intelligenceData == null) {
            return 0;
        } else {
            return this.intelligenceData.size();
        }
    }

    public Object getItem(int i) {
        if (this.showHistory) {
            return this.historyData.get(i);
        }
        return this.intelligenceData.get(i);
    }

    public void clearItems() {
        if (this.historyData != null) {
            this.historyData.clear();
            notifyDataSetChanged();
        }
        if (this.intelligenceData != null) {
            this.intelligenceData.clear();
            notifyDataSetChanged();
        }
    }

    public void addIntelligenceData(List<?> list) {
        if (list != null) {
            if (this.intelligenceData == null) {
                this.intelligenceData = new ArrayList();
            }
            this.intelligenceData.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void tryAdd(List<?> list) {
        if (list != null) {
            if (this.intelligenceData == null) {
                this.intelligenceData = new ArrayList();
            }
            for (Object add : list) {
                this.intelligenceData.add(add);
            }
            notifyDataSetChanged();
        }
    }

    public long getItemId(int i) {
        return 0;
    }
}
