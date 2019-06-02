package com.j256.ormlite.dao;

import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.DatabaseTableConfig;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DaoManager {
    private static Map<ClassConnectionSource, Dao<?, ?>> classMap = null;
    private static Map<Class<?>, DatabaseTableConfig<?>> configMap = null;
    private static Logger logger = LoggerFactory.getLogger(DaoManager.class);
    private static Map<TableConfigConnectionSource, Dao<?, ?>> tableConfigMap = null;

    private static class ClassConnectionSource {
        Class<?> clazz;
        ConnectionSource connectionSource;

        public ClassConnectionSource(ConnectionSource connectionSource, Class<?> cls) {
            this.connectionSource = connectionSource;
            this.clazz = cls;
        }

        public int hashCode() {
            return ((this.clazz.hashCode() + 31) * 31) + this.connectionSource.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ClassConnectionSource classConnectionSource = (ClassConnectionSource) obj;
            if (this.clazz.equals(classConnectionSource.clazz) && this.connectionSource.equals(classConnectionSource.connectionSource)) {
                return true;
            }
            return false;
        }
    }

    private static class TableConfigConnectionSource {
        ConnectionSource connectionSource;
        DatabaseTableConfig<?> tableConfig;

        public TableConfigConnectionSource(ConnectionSource connectionSource, DatabaseTableConfig<?> databaseTableConfig) {
            this.connectionSource = connectionSource;
            this.tableConfig = databaseTableConfig;
        }

        public int hashCode() {
            return ((this.tableConfig.hashCode() + 31) * 31) + this.connectionSource.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TableConfigConnectionSource tableConfigConnectionSource = (TableConfigConnectionSource) obj;
            if (this.tableConfig.equals(tableConfigConnectionSource.tableConfig) && this.connectionSource.equals(tableConfigConnectionSource.connectionSource)) {
                return true;
            }
            return false;
        }
    }

    public static synchronized <D extends Dao<T, ?>, T> D createDao(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        D lookupDao;
        synchronized (DaoManager.class) {
            if (connectionSource == null) {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
            lookupDao = lookupDao(new ClassConnectionSource(connectionSource, cls));
            if (lookupDao == null) {
                Dao dao = (Dao) createDaoFromConfig(connectionSource, cls);
                if (dao == null) {
                    DatabaseTable databaseTable = (DatabaseTable) cls.getAnnotation(DatabaseTable.class);
                    if (databaseTable == null || databaseTable.daoClass() == Void.class || databaseTable.daoClass() == BaseDaoImpl.class) {
                        DatabaseTableConfig extractDatabaseTableConfig = connectionSource.getDatabaseType().extractDatabaseTableConfig(connectionSource, cls);
                        if (extractDatabaseTableConfig == null) {
                            lookupDao = BaseDaoImpl.createDao(connectionSource, (Class) cls);
                        } else {
                            lookupDao = BaseDaoImpl.createDao(connectionSource, extractDatabaseTableConfig);
                        }
                        logger.debug("created dao for class {} with reflection", (Object) cls);
                    } else {
                        Class daoClass = databaseTable.daoClass();
                        Object[] objArr = new Object[]{connectionSource, cls};
                        Constructor findConstructor = findConstructor(daoClass, objArr);
                        if (findConstructor == null) {
                            objArr = new Object[]{connectionSource};
                            findConstructor = findConstructor(daoClass, objArr);
                            if (findConstructor == null) {
                                throw new SQLException("Could not find public constructor with ConnectionSource and optional Class parameters " + daoClass + ".  Missing static on class?");
                            }
                        }
                        try {
                            dao = (Dao) findConstructor.newInstance(objArr);
                            logger.debug("created dao for class {} from constructor", (Object) cls);
                        } catch (Throwable e) {
                            throw SqlExceptionUtil.create("Could not call the constructor in class " + daoClass, e);
                        }
                    }
                    registerDao(connectionSource, lookupDao);
                }
            }
        }
        return lookupDao;
    }

    public static synchronized <D extends Dao<T, ?>, T> D lookupDao(ConnectionSource connectionSource, Class<T> cls) {
        D lookupDao;
        synchronized (DaoManager.class) {
            if (connectionSource == null) {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
            lookupDao = lookupDao(new ClassConnectionSource(connectionSource, cls));
        }
        return lookupDao;
    }

    public static synchronized <D extends Dao<T, ?>, T> D createDao(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        D doCreateDao;
        synchronized (DaoManager.class) {
            if (connectionSource == null) {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
            doCreateDao = doCreateDao(connectionSource, databaseTableConfig);
        }
        return doCreateDao;
    }

    public static synchronized <D extends Dao<T, ?>, T> D lookupDao(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) {
        D lookupDao;
        synchronized (DaoManager.class) {
            if (connectionSource == null) {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
            lookupDao = lookupDao(new TableConfigConnectionSource(connectionSource, databaseTableConfig));
            if (lookupDao == null) {
                lookupDao = null;
            }
        }
        return lookupDao;
    }

    public static synchronized void registerDao(ConnectionSource connectionSource, Dao<?, ?> dao) {
        synchronized (DaoManager.class) {
            if (connectionSource == null) {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
            addDaoToClassMap(new ClassConnectionSource(connectionSource, dao.getDataClass()), dao);
        }
    }

    public static synchronized void unregisterDao(ConnectionSource connectionSource, Dao<?, ?> dao) {
        synchronized (DaoManager.class) {
            if (connectionSource == null) {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
            removeDaoToClassMap(new ClassConnectionSource(connectionSource, dao.getDataClass()), dao);
        }
    }

    public static synchronized void registerDaoWithTableConfig(ConnectionSource connectionSource, Dao<?, ?> dao) {
        synchronized (DaoManager.class) {
            if (connectionSource == null) {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
            if (dao instanceof BaseDaoImpl) {
                DatabaseTableConfig tableConfig = ((BaseDaoImpl) dao).getTableConfig();
                if (tableConfig != null) {
                    addDaoToTableMap(new TableConfigConnectionSource(connectionSource, tableConfig), dao);
                }
            }
            addDaoToClassMap(new ClassConnectionSource(connectionSource, dao.getDataClass()), dao);
        }
    }

    public static synchronized void clearCache() {
        synchronized (DaoManager.class) {
            if (configMap != null) {
                configMap.clear();
                configMap = null;
            }
            clearDaoCache();
        }
    }

    public static synchronized void clearDaoCache() {
        synchronized (DaoManager.class) {
            if (classMap != null) {
                classMap.clear();
                classMap = null;
            }
            if (tableConfigMap != null) {
                tableConfigMap.clear();
                tableConfigMap = null;
            }
        }
    }

    public static synchronized void addCachedDatabaseConfigs(Collection<DatabaseTableConfig<?>> collection) {
        synchronized (DaoManager.class) {
            Map hashMap;
            if (configMap == null) {
                hashMap = new HashMap();
            } else {
                Object hashMap2 = new HashMap(configMap);
            }
            for (DatabaseTableConfig databaseTableConfig : collection) {
                hashMap.put(databaseTableConfig.getDataClass(), databaseTableConfig);
                logger.info("Loaded configuration for {}", databaseTableConfig.getDataClass());
            }
            configMap = hashMap;
        }
    }

    private static void addDaoToClassMap(ClassConnectionSource classConnectionSource, Dao<?, ?> dao) {
        if (classMap == null) {
            classMap = new HashMap();
        }
        classMap.put(classConnectionSource, dao);
    }

    private static void removeDaoToClassMap(ClassConnectionSource classConnectionSource, Dao<?, ?> dao) {
        if (classMap != null) {
            classMap.remove(classConnectionSource);
        }
    }

    private static void addDaoToTableMap(TableConfigConnectionSource tableConfigConnectionSource, Dao<?, ?> dao) {
        if (tableConfigMap == null) {
            tableConfigMap = new HashMap();
        }
        tableConfigMap.put(tableConfigConnectionSource, dao);
    }

    private static <T> Dao<?, ?> lookupDao(ClassConnectionSource classConnectionSource) {
        if (classMap == null) {
            classMap = new HashMap();
        }
        Dao<?, ?> dao = (Dao) classMap.get(classConnectionSource);
        if (dao == null) {
            return null;
        }
        return dao;
    }

    private static <T> Dao<?, ?> lookupDao(TableConfigConnectionSource tableConfigConnectionSource) {
        if (tableConfigMap == null) {
            tableConfigMap = new HashMap();
        }
        Dao<?, ?> dao = (Dao) tableConfigMap.get(tableConfigConnectionSource);
        if (dao == null) {
            return null;
        }
        return dao;
    }

    private static Constructor<?> findConstructor(Class<?> cls, Object[] objArr) {
        for (Constructor<?> constructor : cls.getConstructors()) {
            Class[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == objArr.length) {
                int i;
                Object obj;
                for (i = 0; i < parameterTypes.length; i++) {
                    if (!parameterTypes[i].isAssignableFrom(objArr[i].getClass())) {
                        obj = null;
                        break;
                    }
                }
                i = 1;
                if (obj != null) {
                    return constructor;
                }
            }
        }
        return null;
    }

    private static <D, T> D createDaoFromConfig(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        if (configMap == null) {
            return null;
        }
        DatabaseTableConfig databaseTableConfig = (DatabaseTableConfig) configMap.get(cls);
        if (databaseTableConfig == null) {
            return null;
        }
        return doCreateDao(connectionSource, databaseTableConfig);
    }

    private static <D extends Dao<T, ?>, T> D doCreateDao(ConnectionSource connectionSource, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        TableConfigConnectionSource tableConfigConnectionSource = new TableConfigConnectionSource(connectionSource, databaseTableConfig);
        D lookupDao = lookupDao(tableConfigConnectionSource);
        if (lookupDao == null) {
            Object dataClass = databaseTableConfig.getDataClass();
            ClassConnectionSource classConnectionSource = new ClassConnectionSource(connectionSource, dataClass);
            lookupDao = lookupDao(classConnectionSource);
            if (lookupDao != null) {
                addDaoToTableMap(tableConfigConnectionSource, lookupDao);
            } else {
                DatabaseTable databaseTable = (DatabaseTable) databaseTableConfig.getDataClass().getAnnotation(DatabaseTable.class);
                if (databaseTable == null || databaseTable.daoClass() == Void.class || databaseTable.daoClass() == BaseDaoImpl.class) {
                    lookupDao = BaseDaoImpl.createDao(connectionSource, (DatabaseTableConfig) databaseTableConfig);
                } else {
                    Class daoClass = databaseTable.daoClass();
                    Object[] objArr = new Object[]{connectionSource, databaseTableConfig};
                    Constructor findConstructor = findConstructor(daoClass, objArr);
                    if (findConstructor == null) {
                        throw new SQLException("Could not find public constructor with ConnectionSource, DatabaseTableConfig parameters in class " + daoClass);
                    }
                    try {
                        Dao dao = (Dao) findConstructor.newInstance(objArr);
                    } catch (Throwable e) {
                        throw SqlExceptionUtil.create("Could not call the constructor in class " + daoClass, e);
                    }
                }
                addDaoToTableMap(tableConfigConnectionSource, lookupDao);
                logger.debug("created dao for class {} from table config", dataClass);
                if (lookupDao(classConnectionSource) == null) {
                    addDaoToClassMap(classConnectionSource, lookupDao);
                }
            }
        }
        return lookupDao;
    }
}
