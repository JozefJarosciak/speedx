package com.squareup.picasso;

public enum MemoryPolicy {
    NO_CACHE(1),
    NO_STORE(2);
    
    final int index;

    static boolean shouldReadFromMemoryCache(int i) {
        return (NO_CACHE.index & i) == 0;
    }

    static boolean shouldWriteToMemoryCache(int i) {
        return (NO_STORE.index & i) == 0;
    }

    private MemoryPolicy(int i) {
        this.index = i;
    }
}
