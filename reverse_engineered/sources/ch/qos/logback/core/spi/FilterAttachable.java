package ch.qos.logback.core.spi;

import ch.qos.logback.core.filter.Filter;
import java.util.List;

public interface FilterAttachable<E> {
    void addFilter(Filter<E> filter);

    void clearAllFilters();

    List<Filter<E>> getCopyOfAttachedFiltersList();

    FilterReply getFilterChainDecision(E e);
}
