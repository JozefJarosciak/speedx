package com.j256.ormlite.dao;

import com.j256.ormlite.field.FieldType;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LazyForeignCollection<T, ID> extends BaseForeignCollection<T, ID> implements ForeignCollection<T>, Serializable {
    private static final long serialVersionUID = -5460708106909626233L;
    private transient CloseableIterator<T> lastIterator;

    public LazyForeignCollection(Dao<T, ID> dao, Object obj, Object obj2, FieldType fieldType, String str, boolean z) {
        super(dao, obj, obj2, fieldType, str, z);
    }

    public CloseableIterator<T> iterator() {
        return closeableIterator(-1);
    }

    public CloseableIterator<T> iterator(int i) {
        return closeableIterator(i);
    }

    public CloseableIterator<T> closeableIterator() {
        return closeableIterator(-1);
    }

    public CloseableIterator<T> closeableIterator(int i) {
        try {
            return iteratorThrow(i);
        } catch (Throwable e) {
            throw new IllegalStateException("Could not build lazy iterator for " + this.dao.getDataClass(), e);
        }
    }

    public CloseableIterator<T> iteratorThrow() throws SQLException {
        return iteratorThrow(-1);
    }

    public CloseableIterator<T> iteratorThrow(int i) throws SQLException {
        this.lastIterator = seperateIteratorThrow(i);
        return this.lastIterator;
    }

    public CloseableWrappedIterable<T> getWrappedIterable() {
        return getWrappedIterable(-1);
    }

    public CloseableWrappedIterable<T> getWrappedIterable(final int i) {
        return new CloseableWrappedIterableImpl(new CloseableIterable<T>() {
            public CloseableIterator<T> iterator() {
                return closeableIterator();
            }

            public CloseableIterator<T> closeableIterator() {
                try {
                    return LazyForeignCollection.this.seperateIteratorThrow(i);
                } catch (Throwable e) {
                    throw new IllegalStateException("Could not build lazy iterator for " + LazyForeignCollection.this.dao.getDataClass(), e);
                }
            }
        });
    }

    public void closeLastIterator() throws SQLException {
        if (this.lastIterator != null) {
            this.lastIterator.close();
            this.lastIterator = null;
        }
    }

    public boolean isEager() {
        return false;
    }

    public int size() {
        CloseableIterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            try {
                it.moveToNext();
                i++;
            } finally {
                try {
                    it.close();
                } catch (SQLException e) {
                }
            }
        }
        return i;
    }

    public boolean isEmpty() {
        CloseableIterator it = iterator();
        try {
            boolean z = !it.hasNext();
            return z;
        } finally {
            try {
                it.close();
            } catch (SQLException e) {
            }
        }
    }

    public boolean contains(Object obj) {
        boolean z;
        CloseableIterator it = iterator();
        while (it.hasNext()) {
            try {
                if (it.next().equals(obj)) {
                    z = true;
                    break;
                }
            } finally {
                try {
                    it.close();
                } catch (SQLException e) {
                }
            }
        }
        z = false;
        try {
            it.close();
        } catch (SQLException e2) {
        }
        return z;
    }

    public boolean containsAll(Collection<?> collection) {
        Set hashSet = new HashSet(collection);
        CloseableIterator it = iterator();
        while (it.hasNext()) {
            try {
                hashSet.remove(it.next());
            } finally {
                try {
                    it.close();
                } catch (SQLException e) {
                }
            }
        }
        boolean isEmpty = hashSet.isEmpty();
        return isEmpty;
    }

    public boolean remove(Object obj) {
        boolean z;
        CloseableIterator it = iterator();
        while (it.hasNext()) {
            try {
                if (it.next().equals(obj)) {
                    it.remove();
                    z = true;
                    break;
                }
            } finally {
                try {
                    it.close();
                } catch (SQLException e) {
                }
            }
        }
        z = false;
        try {
            it.close();
        } catch (SQLException e2) {
        }
        return z;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        CloseableIterator it = iterator();
        while (it.hasNext()) {
            try {
                if (collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            } finally {
                try {
                    it.close();
                } catch (SQLException e) {
                }
            }
        }
        return z;
    }

    public Object[] toArray() {
        List arrayList = new ArrayList();
        CloseableIterator it = iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(it.next());
            } finally {
                try {
                    it.close();
                } catch (SQLException e) {
                }
            }
        }
        Object[] toArray = arrayList.toArray();
        return toArray;
    }

    public <E> E[] toArray(E[] eArr) {
        CloseableIterator it = iterator();
        int i = 0;
        List list = null;
        while (it.hasNext()) {
            try {
                Object next = it.next();
                if (i >= eArr.length) {
                    if (list == null) {
                        list = new ArrayList();
                        for (Object add : eArr) {
                            list.add(add);
                        }
                    }
                    list.add(next);
                } else {
                    eArr[i] = next;
                }
                i++;
            } finally {
                try {
                    it.close();
                } catch (SQLException e) {
                    i = e;
                }
            }
        }
        if (list != null) {
            return list.toArray(eArr);
        }
        if (i >= eArr.length - 1) {
            return eArr;
        }
        eArr[i] = null;
        return eArr;
    }

    public int updateAll() {
        throw new UnsupportedOperationException("Cannot call updateAll() on a lazy collection.");
    }

    public int refreshAll() {
        throw new UnsupportedOperationException("Cannot call updateAll() on a lazy collection.");
    }

    public int refreshCollection() {
        return 0;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    private CloseableIterator<T> seperateIteratorThrow(int i) throws SQLException {
        if (this.dao != null) {
            return this.dao.iterator(getPreparedQuery(), i);
        }
        throw new IllegalStateException("Internal DAO object is null.  Lazy collections cannot be used if they have been deserialized.");
    }
}
