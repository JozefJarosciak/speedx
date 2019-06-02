package ch.qos.logback.classic.turbo;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

class LRUMessageCache extends LinkedHashMap<String, Integer> {
    private static final long serialVersionUID = 1;
    final int cacheSize;

    LRUMessageCache(int i) {
        super((int) (((float) i) * 1.3333334f), 0.75f, true);
        if (i < 1) {
            throw new IllegalArgumentException("Cache size cannot be smaller than 1");
        }
        this.cacheSize = i;
    }

    public synchronized void clear() {
        super.clear();
    }

    int getMessageCountAndThenIncrement(String str) {
        if (str == null) {
            return 0;
        }
        Integer num;
        synchronized (this) {
            num = (Integer) super.get(str);
            num = num == null ? Integer.valueOf(0) : Integer.valueOf(num.intValue() + 1);
            super.put(str, num);
        }
        return num.intValue();
    }

    protected boolean removeEldestEntry(Entry entry) {
        return size() > this.cacheSize;
    }
}
