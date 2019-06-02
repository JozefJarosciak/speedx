package com.j256.ormlite.dao;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

public abstract class BaseForeignCollection<T, ID> implements ForeignCollection<T>, Serializable {
    private static final long serialVersionUID = -5158840898186237589L;
    protected final transient Dao<T, ID> dao;
    private final transient FieldType foreignFieldType;
    private final transient boolean orderAscending;
    private final transient String orderColumn;
    private final transient Object parent;
    private final transient Object parentId;
    private transient PreparedQuery<T> preparedQuery;

    public abstract boolean remove(Object obj);

    public abstract boolean removeAll(Collection<?> collection);

    protected BaseForeignCollection(Dao<T, ID> dao, Object obj, Object obj2, FieldType fieldType, String str, boolean z) {
        this.dao = dao;
        this.foreignFieldType = fieldType;
        this.parentId = obj2;
        this.orderColumn = str;
        this.orderAscending = z;
        this.parent = obj;
    }

    public boolean add(T t) {
        try {
            return addElement(t);
        } catch (Throwable e) {
            throw new IllegalStateException("Could not create data element in dao", e);
        }
    }

    public boolean addAll(Collection<? extends T> collection) {
        boolean z = false;
        for (Object addElement : collection) {
            try {
                if (addElement(addElement)) {
                    z = true;
                }
            } catch (Throwable e) {
                throw new IllegalStateException("Could not create data elements in dao", e);
            }
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        if (this.dao != null) {
            CloseableIterator closeableIterator = closeableIterator();
            while (closeableIterator.hasNext()) {
                try {
                    if (!collection.contains(closeableIterator.next())) {
                        closeableIterator.remove();
                        z = true;
                    }
                } finally {
                    try {
                        closeableIterator.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
        return z;
    }

    public void clear() {
        if (this.dao != null) {
            CloseableIterator closeableIterator = closeableIterator();
            while (closeableIterator.hasNext()) {
                try {
                    closeableIterator.next();
                    closeableIterator.remove();
                } finally {
                    try {
                        closeableIterator.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
    }

    public int update(T t) throws SQLException {
        if (this.dao == null) {
            return 0;
        }
        return this.dao.update(t);
    }

    public int refresh(T t) throws SQLException {
        if (this.dao == null) {
            return 0;
        }
        return this.dao.refresh(t);
    }

    protected PreparedQuery<T> getPreparedQuery() throws SQLException {
        if (this.dao == null) {
            return null;
        }
        if (this.preparedQuery == null) {
            SelectArg selectArg = new SelectArg();
            selectArg.setValue(this.parentId);
            QueryBuilder queryBuilder = this.dao.queryBuilder();
            if (this.orderColumn != null) {
                queryBuilder.orderBy(this.orderColumn, this.orderAscending);
            }
            this.preparedQuery = queryBuilder.where().eq(this.foreignFieldType.getColumnName(), selectArg).prepare();
            if (this.preparedQuery instanceof MappedPreparedStmt) {
                ((MappedPreparedStmt) this.preparedQuery).setParentInformation(this.parent, this.parentId);
            }
        }
        return this.preparedQuery;
    }

    private boolean addElement(T t) throws SQLException {
        if (this.dao == null) {
            return false;
        }
        if (this.parent != null && this.foreignFieldType.getFieldValueIfNotDefault(t) == null) {
            this.foreignFieldType.assignField(t, this.parent, true, null);
        }
        this.dao.create(t);
        return true;
    }
}
