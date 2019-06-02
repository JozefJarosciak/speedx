package com.j256.ormlite.dao;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.support.DatabaseResults;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EagerForeignCollection<T, ID> extends BaseForeignCollection<T, ID> implements CloseableWrappedIterable<T>, ForeignCollection<T>, Serializable {
    private static final long serialVersionUID = -2523335606983317721L;
    private List<T> results;

    /* renamed from: com.j256.ormlite.dao.EagerForeignCollection$1 */
    class C41061 implements CloseableIterator<T> {
        private int offset = -1;

        C41061() {
        }

        public boolean hasNext() {
            return this.offset + 1 < EagerForeignCollection.this.results.size();
        }

        public T first() {
            this.offset = 0;
            if (this.offset >= EagerForeignCollection.this.results.size()) {
                return null;
            }
            return EagerForeignCollection.this.results.get(0);
        }

        public T next() {
            this.offset++;
            return EagerForeignCollection.this.results.get(this.offset);
        }

        public T nextThrow() {
            this.offset++;
            if (this.offset >= EagerForeignCollection.this.results.size()) {
                return null;
            }
            return EagerForeignCollection.this.results.get(this.offset);
        }

        public T current() {
            if (this.offset < 0) {
                this.offset = 0;
            }
            if (this.offset >= EagerForeignCollection.this.results.size()) {
                return null;
            }
            return EagerForeignCollection.this.results.get(this.offset);
        }

        public T previous() {
            this.offset--;
            if (this.offset < 0 || this.offset >= EagerForeignCollection.this.results.size()) {
                return null;
            }
            return EagerForeignCollection.this.results.get(this.offset);
        }

        public T moveRelative(int i) {
            this.offset += i;
            if (this.offset < 0 || this.offset >= EagerForeignCollection.this.results.size()) {
                return null;
            }
            return EagerForeignCollection.this.results.get(this.offset);
        }

        public void remove() {
            if (this.offset < 0) {
                throw new IllegalStateException("next() must be called before remove()");
            } else if (this.offset >= EagerForeignCollection.this.results.size()) {
                throw new IllegalStateException("current results position (" + this.offset + ") is out of bounds");
            } else {
                Object remove = EagerForeignCollection.this.results.remove(this.offset);
                this.offset--;
                if (EagerForeignCollection.this.dao != null) {
                    try {
                        EagerForeignCollection.this.dao.delete(remove);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        public void close() {
        }

        public void closeQuietly() {
        }

        public DatabaseResults getRawResults() {
            return null;
        }

        public void moveToNext() {
            this.offset++;
        }
    }

    public EagerForeignCollection(Dao<T, ID> dao, Object obj, Object obj2, FieldType fieldType, String str, boolean z) throws SQLException {
        super(dao, obj, obj2, fieldType, str, z);
        if (obj2 == null) {
            this.results = new ArrayList();
        } else {
            this.results = dao.query(getPreparedQuery());
        }
    }

    public CloseableIterator<T> iterator() {
        return iteratorThrow(-1);
    }

    public CloseableIterator<T> iterator(int i) {
        return iteratorThrow(i);
    }

    public CloseableIterator<T> closeableIterator() {
        return iteratorThrow(-1);
    }

    public CloseableIterator<T> closeableIterator(int i) {
        return iteratorThrow(-1);
    }

    public CloseableIterator<T> iteratorThrow() {
        return iteratorThrow(-1);
    }

    public CloseableIterator<T> iteratorThrow(int i) {
        return new C41061();
    }

    public CloseableWrappedIterable<T> getWrappedIterable() {
        return this;
    }

    public CloseableWrappedIterable<T> getWrappedIterable(int i) {
        return this;
    }

    public void close() {
    }

    public void closeLastIterator() {
    }

    public boolean isEager() {
        return true;
    }

    public int size() {
        return this.results.size();
    }

    public boolean isEmpty() {
        return this.results.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.results.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.results.containsAll(collection);
    }

    public Object[] toArray() {
        return this.results.toArray();
    }

    public <E> E[] toArray(E[] eArr) {
        return this.results.toArray(eArr);
    }

    public boolean add(T t) {
        if (this.results.add(t)) {
            return super.add(t);
        }
        return false;
    }

    public boolean addAll(Collection<? extends T> collection) {
        if (this.results.addAll(collection)) {
            return super.addAll(collection);
        }
        return false;
    }

    public boolean remove(Object obj) {
        if (!this.results.remove(obj) || this.dao == null) {
            return false;
        }
        try {
            if (this.dao.delete(obj) != 1) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            throw new IllegalStateException("Could not delete data element from dao", e);
        }
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        for (Object remove : collection) {
            if (remove(remove)) {
                z = true;
            }
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        return super.retainAll(collection);
    }

    public int updateAll() throws SQLException {
        int i = 0;
        for (Object update : this.results) {
            i += this.dao.update(update);
        }
        return i;
    }

    public int refreshAll() throws SQLException {
        int i = 0;
        for (Object refresh : this.results) {
            i += this.dao.refresh(refresh);
        }
        return i;
    }

    public int refreshCollection() throws SQLException {
        this.results = this.dao.query(getPreparedQuery());
        return this.results.size();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EagerForeignCollection)) {
            return false;
        }
        return this.results.equals(((EagerForeignCollection) obj).results);
    }

    public int hashCode() {
        return this.results.hashCode();
    }
}
