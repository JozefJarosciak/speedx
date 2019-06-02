package ch.qos.logback.core.spi;

import ch.qos.logback.core.filter.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class FilterAttachableImpl<E> implements FilterAttachable<E> {
    CopyOnWriteArrayList<Filter<E>> filterList = new CopyOnWriteArrayList();

    public void addFilter(Filter<E> filter) {
        this.filterList.add(filter);
    }

    public void clearAllFilters() {
        this.filterList.clear();
    }

    public List<Filter<E>> getCopyOfAttachedFiltersList() {
        return new ArrayList(this.filterList);
    }

    public FilterReply getFilterChainDecision(E e) {
        Iterator it = this.filterList.iterator();
        while (it.hasNext()) {
            FilterReply decide = ((Filter) it.next()).decide(e);
            if (decide == FilterReply.DENY) {
                return decide;
            }
            if (decide == FilterReply.ACCEPT) {
                return decide;
            }
        }
        return FilterReply.NEUTRAL;
    }
}
