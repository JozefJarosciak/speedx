package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;

final class ImmutableMultiset$EntrySet extends ImmutableSet<Entry<E>> {
    private static final long serialVersionUID = 0;
    final /* synthetic */ ImmutableMultiset this$0;

    /* renamed from: com.google.common.collect.ImmutableMultiset$EntrySet$1 */
    class C35961 extends ImmutableAsList<Entry<E>> {
        C35961() {
        }

        public Entry<E> get(int i) {
            return ImmutableMultiset$EntrySet.this.this$0.getEntry(i);
        }

        ImmutableCollection<Entry<E>> delegateCollection() {
            return ImmutableMultiset$EntrySet.this;
        }
    }

    private ImmutableMultiset$EntrySet(ImmutableMultiset immutableMultiset) {
        this.this$0 = immutableMultiset;
    }

    boolean isPartialView() {
        return this.this$0.isPartialView();
    }

    public UnmodifiableIterator<Entry<E>> iterator() {
        return asList().iterator();
    }

    ImmutableList<Entry<E>> createAsList() {
        return new C35961();
    }

    public int size() {
        return this.this$0.elementSet().size();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        if (entry.getCount() > 0 && this.this$0.count(entry.getElement()) == entry.getCount()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.this$0.hashCode();
    }

    Object writeReplace() {
        return new ImmutableMultiset$EntrySetSerializedForm(this.this$0);
    }
}
