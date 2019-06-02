package io.rong.imkit.cache;

import io.rong.imkit.RongContext;

public abstract class RongCacheWrap<K, V> extends RongCache<K, V> {
    RongContext mContext;
    boolean mIsSync = false;

    public abstract V obtainValue(K k);

    public RongCacheWrap(RongContext rongContext, int i) {
        super(i);
        this.mContext = rongContext;
    }

    public boolean isIsSync() {
        return this.mIsSync;
    }

    public void setIsSync(boolean z) {
        this.mIsSync = z;
    }

    protected V create(K k) {
        if (k == null) {
            return null;
        }
        if (this.mIsSync) {
            return obtainValue(k);
        }
        executeCacheProvider(k);
        return super.create(k);
    }

    protected RongContext getContext() {
        return this.mContext;
    }

    public void executeCacheProvider(final K k) {
        this.mContext.executorBackground(new Runnable() {
            public void run() {
                RongCacheWrap.this.obtainValue(k);
            }
        });
    }
}
