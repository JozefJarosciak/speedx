package com.beastbikes.framework.persistence.android.ormlite;

import android.text.TextUtils;
import com.beastbikes.framework.persistence.C1663b;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.table.TableInfo;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: ORMLiteAccessObject */
/* renamed from: com.beastbikes.framework.persistence.android.ormlite.a */
public class C1664a<T extends PersistentObject> implements C1663b<T>, RawRowMapper<T> {
    /* renamed from: a */
    private static final Logger f7529a = LoggerFactory.getLogger("ORMLiteAccessObject");
    /* renamed from: b */
    private final C1380c f7530b;
    /* renamed from: c */
    private final BaseDaoImpl<T, String> f7531c;
    /* renamed from: d */
    private final TableInfo<T, String> f7532d;

    public /* synthetic */ Object mapRow(String[] strArr, String[] strArr2) throws SQLException {
        return m9019a(strArr, strArr2);
    }

    public C1664a(C1380c c1380c, Class<T> cls) {
        this.f7530b = c1380c;
        try {
            this.f7531c = (BaseDaoImpl) c1380c.getDao(cls);
            this.f7532d = new TableInfo(c1380c.getConnectionSource(), this.f7531c, (Class) cls);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: c */
    public T mo3189c(String str) throws PersistenceException {
        try {
            return (PersistentObject) this.f7531c.queryForId(str);
        } catch (Throwable e) {
            throw new PersistenceException(e);
        }
    }

    /* renamed from: c */
    public void m9030c(final T... tArr) throws PersistenceException {
        if (tArr != null && tArr.length >= 0) {
            m9020a(new Callable<Void>(this) {
                /* renamed from: b */
                final /* synthetic */ C1664a f13026b;

                public /* synthetic */ Object call() throws Exception {
                    return m13765a();
                }

                /* renamed from: a */
                public Void m13765a() throws Exception {
                    for (Object obj : tArr) {
                        C1664a.f7529a.trace("Creating object: " + String.valueOf(obj));
                        this.f13026b.f7531c.create(obj);
                    }
                    return null;
                }
            });
        }
    }

    /* renamed from: d */
    public void m9031d(final T... tArr) throws PersistenceException {
        if (tArr != null && tArr.length >= 0) {
            m9020a(new Callable<Void>(this) {
                /* renamed from: b */
                final /* synthetic */ C1664a f13028b;

                public /* synthetic */ Object call() throws Exception {
                    return m13766a();
                }

                /* renamed from: a */
                public Void m13766a() throws Exception {
                    for (Object obj : tArr) {
                        C1664a.f7529a.trace("Updating " + String.valueOf(obj));
                        this.f13028b.f7531c.update(obj);
                    }
                    return null;
                }
            });
        }
    }

    /* renamed from: a */
    public void m9022a(final List<T> list) throws PersistenceException {
        if (list != null && !list.isEmpty()) {
            m9020a(new Callable<Void>(this) {
                /* renamed from: b */
                final /* synthetic */ C1664a f13030b;

                public /* synthetic */ Object call() throws Exception {
                    return m13767a();
                }

                /* renamed from: a */
                public Void m13767a() throws Exception {
                    for (Object obj : list) {
                        C1664a.f7529a.trace("Updating " + String.valueOf(obj));
                        this.f13030b.f7531c.update(obj);
                    }
                    return null;
                }
            });
        }
    }

    /* renamed from: a */
    public void mo3187a(final T... tArr) throws PersistenceException {
        if (tArr != null && tArr.length >= 0) {
            m9020a(new Callable<Void>(this) {
                /* renamed from: b */
                final /* synthetic */ C1664a f13032b;

                public /* synthetic */ Object call() throws Exception {
                    return m13768a();
                }

                /* renamed from: a */
                public Void m13768a() throws Exception {
                    for (Object obj : tArr) {
                        C1664a.f7529a.trace("Creating/Updating " + String.valueOf(obj));
                        this.f13032b.f7531c.createOrUpdate(obj);
                    }
                    return null;
                }
            });
        }
    }

    /* renamed from: b */
    public void m9026b(final List<T> list) throws PersistenceException {
        if (list != null && !list.isEmpty()) {
            m9020a(new Callable<Void>(this) {
                /* renamed from: b */
                final /* synthetic */ C1664a f13034b;

                public /* synthetic */ Object call() throws Exception {
                    return m13769a();
                }

                /* renamed from: a */
                public Void m13769a() throws Exception {
                    for (PersistentObject persistentObject : list) {
                        C1664a.f7529a.trace("Creating/Updating " + String.valueOf(persistentObject));
                        this.f13034b.f7531c.createOrUpdate(persistentObject);
                    }
                    return null;
                }
            });
        }
    }

    /* renamed from: b */
    public void mo3188b(final T... tArr) throws PersistenceException {
        if (tArr != null && tArr.length >= 0) {
            m9020a(new Callable<Void>(this) {
                /* renamed from: b */
                final /* synthetic */ C1664a f13036b;

                public /* synthetic */ Object call() throws Exception {
                    return m13770a();
                }

                /* renamed from: a */
                public Void m13770a() throws Exception {
                    for (Object obj : tArr) {
                        C1664a.f7529a.trace("Deleting " + String.valueOf(obj));
                        this.f13036b.f7531c.delete(obj);
                    }
                    return null;
                }
            });
        }
    }

    /* renamed from: c */
    public void m9029c(final List<T> list) throws PersistenceException {
        if (list != null && !list.isEmpty()) {
            m9020a(new Callable<Void>(this) {
                /* renamed from: b */
                final /* synthetic */ C1664a f13038b;

                public /* synthetic */ Object call() throws Exception {
                    return m13771a();
                }

                /* renamed from: a */
                public Void m13771a() throws Exception {
                    for (Object obj : list) {
                        C1664a.f7529a.trace("Deleting " + String.valueOf(obj));
                        this.f13038b.f7531c.delete(obj);
                    }
                    return null;
                }
            });
        }
    }

    /* renamed from: a */
    public void m9024a(String... strArr) throws PersistenceException {
        f7529a.trace("Deleting " + Arrays.toString(strArr));
        try {
            this.f7531c.deleteIds(Arrays.asList(strArr));
        } catch (Throwable e) {
            throw new PersistenceException(e);
        }
    }

    /* renamed from: a */
    public void m9021a(String str, String... strArr) throws PersistenceException {
        f7529a.trace("Executing " + str + " : " + Arrays.toString(strArr));
        try {
            this.f7531c.executeRaw(str, strArr);
        } catch (Throwable e) {
            throw new PersistenceException(e);
        }
    }

    /* renamed from: a */
    public T m9019a(String[] strArr, String[] strArr2) throws SQLException {
        int i;
        FieldType[] fieldTypes = this.f7532d.getFieldTypes();
        PersistentObject persistentObject = (PersistentObject) this.f7532d.createObject();
        int min = Math.min(strArr.length, strArr2.length);
        Map hashMap = new HashMap();
        for (i = 0; i < min; i++) {
            hashMap.put(strArr[i], strArr2[i]);
        }
        for (i = 0; i < fieldTypes.length; i++) {
            FieldType fieldType = fieldTypes[i];
            Field field = fieldType.getField();
            String str = (String) hashMap.get(fieldType.getColumnName());
            if (str != null) {
                try {
                    field.setAccessible(true);
                    field.set(persistentObject, fieldType.convertStringToJavaField(str, i));
                } catch (Throwable e) {
                    throw new SQLException(e);
                }
            }
        }
        return persistentObject;
    }

    /* renamed from: a */
    public <V> V m9020a(Callable<V> callable) throws PersistenceException {
        try {
            return TransactionManager.callInTransaction(this.f7530b.getConnectionSource(), callable);
        } catch (Throwable e) {
            throw new PersistenceException(e);
        }
    }

    /* renamed from: b */
    public List<T> m9025b(String str, String... strArr) throws PersistenceException {
        CloseableIterator closeableIterator;
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(this.f7532d.getTableName());
        if (!TextUtils.isEmpty(str)) {
            if (!str.startsWith(" ")) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(str);
        }
        try {
            GenericRawResults queryRaw = this.f7531c.queryRaw(stringBuilder.toString(), (RawRowMapper) this, strArr);
            if (queryRaw == null) {
                return null;
            }
            List<T> arrayList = new ArrayList();
            closeableIterator = queryRaw.closeableIterator();
            while (closeableIterator.hasNext()) {
                arrayList.add(closeableIterator.nextThrow());
            }
            closeableIterator.close();
            return arrayList;
        } catch (Throwable e) {
            throw new PersistenceException(e);
        } catch (Throwable th) {
            closeableIterator.close();
        }
    }
}
