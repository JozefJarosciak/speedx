package com.j256.ormlite.field;

import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.BaseForeignCollection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.EagerForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.LazyForeignCollection;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.types.VoidType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.mapped.MappedQueryForId;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableInfo;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public class FieldType {
    private static boolean DEFAULT_VALUE_BOOLEAN = false;
    private static byte DEFAULT_VALUE_BYTE = (byte) 0;
    private static char DEFAULT_VALUE_CHAR = '\u0000';
    private static double DEFAULT_VALUE_DOUBLE = 0.0d;
    private static float DEFAULT_VALUE_FLOAT = 0.0f;
    private static int DEFAULT_VALUE_INT = 0;
    private static long DEFAULT_VALUE_LONG = 0;
    private static short DEFAULT_VALUE_SHORT = (short) 0;
    public static final String FOREIGN_ID_FIELD_SUFFIX = "_id";
    private static final ThreadLocal<FieldType$LevelCounters> threadLevelCounters = new FieldType$1();
    private final String columnName;
    private final ConnectionSource connectionSource;
    private DataPersister dataPersister;
    private Object dataTypeConfigObj;
    private Object defaultValue;
    private final Field field;
    private final DatabaseFieldConfig fieldConfig;
    private FieldConverter fieldConverter;
    private final Method fieldGetMethod;
    private final Method fieldSetMethod;
    private BaseDaoImpl<?, ?> foreignDao;
    private FieldType foreignFieldType;
    private FieldType foreignIdField;
    private TableInfo<?, ?> foreignTableInfo;
    private final String generatedIdSequence;
    private final boolean isGeneratedId;
    private final boolean isId;
    private MappedQueryForId<Object, Object> mappedQueryForId;
    private final Class<?> parentClass;
    private final String tableName;

    public FieldType(ConnectionSource connectionSource, String str, Field field, DatabaseFieldConfig databaseFieldConfig, Class<?> cls) throws SQLException {
        DataPersister dataPersister;
        this.connectionSource = connectionSource;
        this.tableName = str;
        DatabaseType databaseType = connectionSource.getDatabaseType();
        this.field = field;
        this.parentClass = cls;
        databaseFieldConfig.postProcess();
        Class type = field.getType();
        DataPersister lookupForField;
        if (databaseFieldConfig.getDataPersister() == null) {
            Class persisterClass = databaseFieldConfig.getPersisterClass();
            if (persisterClass == null || persisterClass == VoidType.class) {
                lookupForField = DataPersisterManager.lookupForField(field);
            } else {
                try {
                    try {
                        Object invoke = persisterClass.getDeclaredMethod("getSingleton", new Class[0]).invoke(null, new Object[0]);
                        if (invoke == null) {
                            throw new SQLException("Static getSingleton method should not return null on class " + persisterClass);
                        }
                        try {
                            lookupForField = (DataPersister) invoke;
                        } catch (Throwable e) {
                            throw SqlExceptionUtil.create("Could not cast result of static getSingleton method to DataPersister from class " + persisterClass, e);
                        }
                    } catch (InvocationTargetException e2) {
                        throw SqlExceptionUtil.create("Could not run getSingleton method on class " + persisterClass, e2.getTargetException());
                    } catch (Throwable e3) {
                        throw SqlExceptionUtil.create("Could not run getSingleton method on class " + persisterClass, e3);
                    }
                } catch (Throwable e32) {
                    throw SqlExceptionUtil.create("Could not find getSingleton static method on class " + persisterClass, e32);
                }
            }
            dataPersister = lookupForField;
        } else {
            lookupForField = databaseFieldConfig.getDataPersister();
            if (lookupForField.isValidForField(field)) {
                dataPersister = lookupForField;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Field class ").append(type.getName());
                stringBuilder.append(" for field ").append(this);
                stringBuilder.append(" is not valid for type ").append(lookupForField);
                Class primaryClass = lookupForField.getPrimaryClass();
                if (primaryClass != null) {
                    stringBuilder.append(", maybe should be " + primaryClass);
                }
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        String foreignColumnName = databaseFieldConfig.getForeignColumnName();
        String name = field.getName();
        if (databaseFieldConfig.isForeign() || databaseFieldConfig.isForeignAutoRefresh() || foreignColumnName != null) {
            if (dataPersister == null || !dataPersister.isPrimitive()) {
                if (foreignColumnName == null) {
                    foreignColumnName = name + FOREIGN_ID_FIELD_SUFFIX;
                } else {
                    foreignColumnName = name + "_" + foreignColumnName;
                }
                if (ForeignCollection.class.isAssignableFrom(type)) {
                    throw new SQLException("Field '" + field.getName() + "' in class " + type + "' should use the @" + ForeignCollectionField.class.getSimpleName() + " annotation not foreign=true");
                }
            }
            throw new IllegalArgumentException("Field " + this + " is a primitive class " + type + " but marked as foreign");
        } else if (databaseFieldConfig.isForeignCollection()) {
            if (type == Collection.class || ForeignCollection.class.isAssignableFrom(type)) {
                Type genericType = field.getGenericType();
                if (!(genericType instanceof ParameterizedType)) {
                    throw new SQLException("Field class for '" + field.getName() + "' must be a parameterized Collection.");
                } else if (((ParameterizedType) genericType).getActualTypeArguments().length == 0) {
                    throw new SQLException("Field class for '" + field.getName() + "' must be a parameterized Collection with at least 1 type.");
                } else {
                    foreignColumnName = name;
                }
            } else {
                throw new SQLException("Field class for '" + field.getName() + "' must be of class " + ForeignCollection.class.getSimpleName() + " or Collection.");
            }
        } else if (dataPersister != null || databaseFieldConfig.isForeignCollection()) {
            foreignColumnName = name;
        } else if (byte[].class.isAssignableFrom(type)) {
            throw new SQLException("ORMLite does not know how to store " + type + " for field '" + field.getName() + "'. byte[] fields must specify dataType=DataType.BYTE_ARRAY or SERIALIZABLE");
        } else if (Serializable.class.isAssignableFrom(type)) {
            throw new SQLException("ORMLite does not know how to store " + type + " for field '" + field.getName() + "'.  Use another class, custom persister, or to serialize it use " + "dataType=DataType.SERIALIZABLE");
        } else {
            throw new IllegalArgumentException("ORMLite does not know how to store " + type + " for field " + field.getName() + ". Use another class or a custom persister.");
        }
        if (databaseFieldConfig.getColumnName() == null) {
            this.columnName = foreignColumnName;
        } else {
            this.columnName = databaseFieldConfig.getColumnName();
        }
        this.fieldConfig = databaseFieldConfig;
        if (databaseFieldConfig.isId()) {
            if (databaseFieldConfig.isGeneratedId() || databaseFieldConfig.getGeneratedIdSequence() != null) {
                throw new IllegalArgumentException("Must specify one of id, generatedId, and generatedIdSequence with " + field.getName());
            }
            this.isId = true;
            this.isGeneratedId = false;
            this.generatedIdSequence = null;
        } else if (databaseFieldConfig.isGeneratedId()) {
            if (databaseFieldConfig.getGeneratedIdSequence() != null) {
                throw new IllegalArgumentException("Must specify one of id, generatedId, and generatedIdSequence with " + field.getName());
            }
            this.isId = true;
            this.isGeneratedId = true;
            if (databaseType.isIdSequenceNeeded()) {
                this.generatedIdSequence = databaseType.generateIdSequenceName(str, this);
            } else {
                this.generatedIdSequence = null;
            }
        } else if (databaseFieldConfig.getGeneratedIdSequence() != null) {
            this.isId = true;
            this.isGeneratedId = true;
            foreignColumnName = databaseFieldConfig.getGeneratedIdSequence();
            if (databaseType.isEntityNamesMustBeUpCase()) {
                foreignColumnName = foreignColumnName.toUpperCase();
            }
            this.generatedIdSequence = foreignColumnName;
        } else {
            this.isId = false;
            this.isGeneratedId = false;
            this.generatedIdSequence = null;
        }
        if (this.isId && (databaseFieldConfig.isForeign() || databaseFieldConfig.isForeignAutoRefresh())) {
            throw new IllegalArgumentException("Id field " + field.getName() + " cannot also be a foreign object");
        }
        if (databaseFieldConfig.isUseGetSet()) {
            this.fieldGetMethod = DatabaseFieldConfig.findGetMethod(field, true);
            this.fieldSetMethod = DatabaseFieldConfig.findSetMethod(field, true);
        } else {
            if (!field.isAccessible()) {
                try {
                    this.field.setAccessible(true);
                } catch (SecurityException e4) {
                    throw new IllegalArgumentException("Could not open access to field " + field.getName() + ".  You may have to set useGetSet=true to fix.");
                }
            }
            this.fieldGetMethod = null;
            this.fieldSetMethod = null;
        }
        if (databaseFieldConfig.isAllowGeneratedIdInsert() && !databaseFieldConfig.isGeneratedId()) {
            throw new IllegalArgumentException("Field " + field.getName() + " must be a generated-id if allowGeneratedIdInsert = true");
        } else if (databaseFieldConfig.isForeignAutoRefresh() && !databaseFieldConfig.isForeign()) {
            throw new IllegalArgumentException("Field " + field.getName() + " must have foreign = true if foreignAutoRefresh = true");
        } else if (databaseFieldConfig.isForeignAutoCreate() && !databaseFieldConfig.isForeign()) {
            throw new IllegalArgumentException("Field " + field.getName() + " must have foreign = true if foreignAutoCreate = true");
        } else if (databaseFieldConfig.getForeignColumnName() != null && !databaseFieldConfig.isForeign()) {
            throw new IllegalArgumentException("Field " + field.getName() + " must have foreign = true if foreignColumnName is set");
        } else if (databaseFieldConfig.isVersion() && (dataPersister == null || !dataPersister.isValidForVersion())) {
            throw new IllegalArgumentException("Field " + field.getName() + " is not a valid type to be a version field");
        } else if (databaseFieldConfig.getMaxForeignAutoRefreshLevel() <= 0 || databaseFieldConfig.isForeignAutoRefresh()) {
            assignDataType(databaseType, dataPersister);
        } else {
            throw new IllegalArgumentException("Field " + field.getName() + " has maxForeignAutoRefreshLevel set but not foreignAutoRefresh is false");
        }
    }

    public void configDaoInformation(ConnectionSource connectionSource, Class<?> cls) throws SQLException {
        TableInfo tableInfo;
        BaseDaoImpl baseDaoImpl;
        FieldType fieldType;
        MappedQueryForId build;
        FieldType fieldType2 = null;
        Class type = this.field.getType();
        DatabaseType databaseType = connectionSource.getDatabaseType();
        String foreignColumnName = this.fieldConfig.getForeignColumnName();
        DatabaseTableConfig foreignTableConfig;
        BaseDaoImpl baseDaoImpl2;
        if (this.fieldConfig.isForeignAutoRefresh() || foreignColumnName != null) {
            BaseDaoImpl baseDaoImpl3;
            FieldType idField;
            foreignTableConfig = this.fieldConfig.getForeignTableConfig();
            if (foreignTableConfig == null) {
                baseDaoImpl2 = (BaseDaoImpl) DaoManager.createDao(connectionSource, type);
                tableInfo = baseDaoImpl2.getTableInfo();
                baseDaoImpl3 = baseDaoImpl2;
            } else {
                foreignTableConfig.extractFieldTypes(connectionSource);
                baseDaoImpl2 = (BaseDaoImpl) DaoManager.createDao(connectionSource, foreignTableConfig);
                tableInfo = baseDaoImpl2.getTableInfo();
                baseDaoImpl3 = baseDaoImpl2;
            }
            if (foreignColumnName == null) {
                idField = tableInfo.getIdField();
                if (idField == null) {
                    throw new IllegalArgumentException("Foreign field " + type + " does not have id field");
                }
            }
            idField = tableInfo.getFieldTypeByColumnName(foreignColumnName);
            if (idField == null) {
                throw new IllegalArgumentException("Foreign field " + type + " does not have field named '" + foreignColumnName + "'");
            }
            baseDaoImpl = baseDaoImpl3;
            fieldType = idField;
            build = MappedQueryForId.build(databaseType, tableInfo, idField);
        } else if (this.fieldConfig.isForeign()) {
            if (this.dataPersister == null || !this.dataPersister.isPrimitive()) {
                foreignTableConfig = this.fieldConfig.getForeignTableConfig();
                if (foreignTableConfig != null) {
                    foreignTableConfig.extractFieldTypes(connectionSource);
                    baseDaoImpl2 = (BaseDaoImpl) DaoManager.createDao(connectionSource, foreignTableConfig);
                } else {
                    baseDaoImpl2 = (BaseDaoImpl) DaoManager.createDao(connectionSource, type);
                }
                TableInfo tableInfo2 = baseDaoImpl2.getTableInfo();
                FieldType idField2 = tableInfo2.getIdField();
                if (idField2 == null) {
                    throw new IllegalArgumentException("Foreign field " + type + " does not have id field");
                } else if (!isForeignAutoCreate() || idField2.isGeneratedId()) {
                    tableInfo = tableInfo2;
                    fieldType = idField2;
                    baseDaoImpl = baseDaoImpl2;
                    build = null;
                } else {
                    throw new IllegalArgumentException("Field " + this.field.getName() + ", if foreignAutoCreate = true then class " + type.getSimpleName() + " must have id field with generatedId = true");
                }
            }
            throw new IllegalArgumentException("Field " + this + " is a primitive class " + type + " but marked as foreign");
        } else if (!this.fieldConfig.isForeignCollection()) {
            build = null;
            baseDaoImpl = null;
            fieldType = null;
            tableInfo = null;
        } else if (type == Collection.class || ForeignCollection.class.isAssignableFrom(type)) {
            Type genericType = this.field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
                if (actualTypeArguments.length == 0) {
                    throw new SQLException("Field class for '" + this.field.getName() + "' must be a parameterized Collection with at least 1 type.");
                } else if (actualTypeArguments[0] instanceof Class) {
                    Class cls2 = (Class) actualTypeArguments[0];
                    DatabaseTableConfig foreignTableConfig2 = this.fieldConfig.getForeignTableConfig();
                    if (foreignTableConfig2 == null) {
                        baseDaoImpl = (BaseDaoImpl) DaoManager.createDao(connectionSource, cls2);
                    } else {
                        baseDaoImpl = (BaseDaoImpl) DaoManager.createDao(connectionSource, foreignTableConfig2);
                    }
                    fieldType = null;
                    tableInfo = null;
                    fieldType2 = findForeignFieldType(cls2, cls, baseDaoImpl);
                    Object obj = null;
                } else {
                    throw new SQLException("Field class for '" + this.field.getName() + "' must be a parameterized Collection whose generic argument is an entity class not: " + actualTypeArguments[0]);
                }
            }
            throw new SQLException("Field class for '" + this.field.getName() + "' must be a parameterized Collection.");
        } else {
            throw new SQLException("Field class for '" + this.field.getName() + "' must be of class " + ForeignCollection.class.getSimpleName() + " or Collection.");
        }
        this.mappedQueryForId = build;
        this.foreignTableInfo = tableInfo;
        this.foreignFieldType = fieldType2;
        this.foreignDao = baseDaoImpl;
        this.foreignIdField = fieldType;
        if (this.foreignIdField != null) {
            assignDataType(databaseType, this.foreignIdField.getDataPersister());
        }
    }

    public Field getField() {
        return this.field;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getFieldName() {
        return this.field.getName();
    }

    public Class<?> getType() {
        return this.field.getType();
    }

    public String getColumnName() {
        return this.columnName;
    }

    public DataPersister getDataPersister() {
        return this.dataPersister;
    }

    public Object getDataTypeConfigObj() {
        return this.dataTypeConfigObj;
    }

    public SqlType getSqlType() {
        return this.fieldConverter.getSqlType();
    }

    public Object getDefaultValue() {
        return this.defaultValue;
    }

    public int getWidth() {
        return this.fieldConfig.getWidth();
    }

    public boolean isCanBeNull() {
        return this.fieldConfig.isCanBeNull();
    }

    public boolean isId() {
        return this.isId;
    }

    public boolean isGeneratedId() {
        return this.isGeneratedId;
    }

    public boolean isGeneratedIdSequence() {
        return this.generatedIdSequence != null;
    }

    public String getGeneratedIdSequence() {
        return this.generatedIdSequence;
    }

    public boolean isForeign() {
        return this.fieldConfig.isForeign();
    }

    public void assignField(Object obj, Object obj2, boolean z, ObjectCache objectCache) throws SQLException {
        if (!(this.foreignIdField == null || obj2 == null)) {
            Object extractJavaFieldValue = extractJavaFieldValue(obj);
            if (extractJavaFieldValue == null || !extractJavaFieldValue.equals(obj2)) {
                ObjectCache objectCache2 = this.foreignDao.getObjectCache();
                if (objectCache2 == null) {
                    extractJavaFieldValue = null;
                } else {
                    extractJavaFieldValue = objectCache2.get(getType(), obj2);
                }
                if (extractJavaFieldValue != null) {
                    obj2 = extractJavaFieldValue;
                } else if (!z) {
                    FieldType$LevelCounters fieldType$LevelCounters = (FieldType$LevelCounters) threadLevelCounters.get();
                    if (fieldType$LevelCounters.autoRefreshLevel == 0) {
                        fieldType$LevelCounters.autoRefreshLevelMax = this.fieldConfig.getMaxForeignAutoRefreshLevel();
                    }
                    if (fieldType$LevelCounters.autoRefreshLevel >= fieldType$LevelCounters.autoRefreshLevelMax) {
                        extractJavaFieldValue = this.foreignTableInfo.createObject();
                        this.foreignIdField.assignField(extractJavaFieldValue, obj2, false, objectCache);
                    } else {
                        if (this.mappedQueryForId == null) {
                            this.mappedQueryForId = MappedQueryForId.build(this.connectionSource.getDatabaseType(), this.foreignDao.getTableInfo(), this.foreignIdField);
                        }
                        fieldType$LevelCounters.autoRefreshLevel++;
                        DatabaseConnection readOnlyConnection;
                        try {
                            readOnlyConnection = this.connectionSource.getReadOnlyConnection();
                            Object execute = this.mappedQueryForId.execute(readOnlyConnection, obj2, objectCache);
                            this.connectionSource.releaseConnection(readOnlyConnection);
                            fieldType$LevelCounters.autoRefreshLevel--;
                            if (fieldType$LevelCounters.autoRefreshLevel <= 0) {
                                threadLevelCounters.remove();
                                extractJavaFieldValue = execute;
                            } else {
                                extractJavaFieldValue = execute;
                            }
                        } catch (Throwable th) {
                            fieldType$LevelCounters.autoRefreshLevel--;
                            if (fieldType$LevelCounters.autoRefreshLevel <= 0) {
                                threadLevelCounters.remove();
                            }
                        }
                    }
                    obj2 = extractJavaFieldValue;
                }
            } else {
                return;
            }
        }
        if (this.fieldSetMethod == null) {
            try {
                this.field.set(obj, obj2);
                return;
            } catch (Throwable e) {
                throw SqlExceptionUtil.create("Could not assign object '" + obj2 + "' to field " + this, e);
            } catch (Throwable e2) {
                throw SqlExceptionUtil.create("Could not assign object '" + obj2 + "' to field " + this, e2);
            }
        }
        try {
            this.fieldSetMethod.invoke(obj, new Object[]{obj2});
        } catch (Throwable e22) {
            throw SqlExceptionUtil.create("Could not call " + this.fieldSetMethod + " on object with '" + obj2 + "' for " + this, e22);
        }
    }

    public Object assignIdValue(Object obj, Number number, ObjectCache objectCache) throws SQLException {
        Object convertIdNumber = this.dataPersister.convertIdNumber(number);
        if (convertIdNumber == null) {
            throw new SQLException("Invalid class " + this.dataPersister + " for sequence-id " + this);
        }
        assignField(obj, convertIdNumber, false, objectCache);
        return convertIdNumber;
    }

    public <FV> FV extractRawJavaFieldValue(Object obj) throws SQLException {
        FV fv;
        if (this.fieldGetMethod == null) {
            try {
                fv = this.field.get(obj);
            } catch (Throwable e) {
                throw SqlExceptionUtil.create("Could not get field value for " + this, e);
            }
        }
        try {
            fv = this.fieldGetMethod.invoke(obj, new Object[0]);
        } catch (Throwable e2) {
            throw SqlExceptionUtil.create("Could not call " + this.fieldGetMethod + " for " + this, e2);
        }
        return fv;
    }

    public Object extractJavaFieldValue(Object obj) throws SQLException {
        Object extractRawJavaFieldValue = extractRawJavaFieldValue(obj);
        if (this.foreignIdField == null || extractRawJavaFieldValue == null) {
            return extractRawJavaFieldValue;
        }
        return this.foreignIdField.extractRawJavaFieldValue(extractRawJavaFieldValue);
    }

    public Object extractJavaFieldToSqlArgValue(Object obj) throws SQLException {
        return convertJavaFieldToSqlArgValue(extractJavaFieldValue(obj));
    }

    public Object convertJavaFieldToSqlArgValue(Object obj) throws SQLException {
        if (obj == null) {
            return null;
        }
        return this.fieldConverter.javaToSqlArg(this, obj);
    }

    public Object convertStringToJavaField(String str, int i) throws SQLException {
        if (str == null) {
            return null;
        }
        return this.fieldConverter.resultStringToJava(this, str, i);
    }

    public Object moveToNextValue(Object obj) {
        if (this.dataPersister == null) {
            return null;
        }
        return this.dataPersister.moveToNextValue(obj);
    }

    public FieldType getForeignIdField() {
        return this.foreignIdField;
    }

    public boolean isEscapedValue() {
        return this.dataPersister.isEscapedValue();
    }

    public Enum<?> getUnknownEnumVal() {
        return this.fieldConfig.getUnknownEnumValue();
    }

    public String getFormat() {
        return this.fieldConfig.getFormat();
    }

    public boolean isUnique() {
        return this.fieldConfig.isUnique();
    }

    public boolean isUniqueCombo() {
        return this.fieldConfig.isUniqueCombo();
    }

    public String getIndexName() {
        return this.fieldConfig.getIndexName(this.tableName);
    }

    public String getUniqueIndexName() {
        return this.fieldConfig.getUniqueIndexName(this.tableName);
    }

    public boolean isEscapedDefaultValue() {
        return this.dataPersister.isEscapedDefaultValue();
    }

    public boolean isComparable() throws SQLException {
        if (this.fieldConfig.isForeignCollection()) {
            return false;
        }
        if (this.dataPersister != null) {
            return this.dataPersister.isComparable();
        }
        throw new SQLException("Internal error.  Data-persister is not configured for field.  Please post _full_ exception with associated data objects to mailing list: " + this);
    }

    public boolean isArgumentHolderRequired() {
        return this.dataPersister.isArgumentHolderRequired();
    }

    public boolean isForeignCollection() {
        return this.fieldConfig.isForeignCollection();
    }

    public <FT, FID> BaseForeignCollection<FT, FID> buildForeignCollection(Object obj, FID fid) throws SQLException {
        if (this.foreignFieldType == null) {
            return null;
        }
        Dao dao = this.foreignDao;
        if (this.fieldConfig.isForeignCollectionEager()) {
            FieldType$LevelCounters fieldType$LevelCounters = (FieldType$LevelCounters) threadLevelCounters.get();
            if (fieldType$LevelCounters.foreignCollectionLevel == 0) {
                fieldType$LevelCounters.foreignCollectionLevelMax = this.fieldConfig.getForeignCollectionMaxEagerLevel();
            }
            if (fieldType$LevelCounters.foreignCollectionLevel >= fieldType$LevelCounters.foreignCollectionLevelMax) {
                return new LazyForeignCollection(dao, obj, fid, this.foreignFieldType, this.fieldConfig.getForeignCollectionOrderColumnName(), this.fieldConfig.isForeignCollectionOrderAscending());
            }
            fieldType$LevelCounters.foreignCollectionLevel++;
            try {
                BaseForeignCollection<FT, FID> eagerForeignCollection = new EagerForeignCollection(dao, obj, fid, this.foreignFieldType, this.fieldConfig.getForeignCollectionOrderColumnName(), this.fieldConfig.isForeignCollectionOrderAscending());
                return eagerForeignCollection;
            } finally {
                fieldType$LevelCounters.foreignCollectionLevel--;
            }
        } else {
            return new LazyForeignCollection(dao, obj, fid, this.foreignFieldType, this.fieldConfig.getForeignCollectionOrderColumnName(), this.fieldConfig.isForeignCollectionOrderAscending());
        }
    }

    public <T> T resultToJava(DatabaseResults databaseResults, Map<String, Integer> map) throws SQLException {
        Integer num = (Integer) map.get(this.columnName);
        if (num == null) {
            num = Integer.valueOf(databaseResults.findColumn(this.columnName));
            map.put(this.columnName, num);
        }
        T resultToJava = this.fieldConverter.resultToJava(this, databaseResults, num.intValue());
        if (this.fieldConfig.isForeign()) {
            if (databaseResults.wasNull(num.intValue())) {
                return null;
            }
        } else if (this.dataPersister.isPrimitive()) {
            if (this.fieldConfig.isThrowIfNull() && databaseResults.wasNull(num.intValue())) {
                throw new SQLException("Results value for primitive field '" + this.field.getName() + "' was an invalid null value");
            }
        } else if (!this.fieldConverter.isStreamType() && databaseResults.wasNull(num.intValue())) {
            return null;
        }
        return resultToJava;
    }

    public boolean isSelfGeneratedId() {
        return this.dataPersister.isSelfGeneratedId();
    }

    public boolean isAllowGeneratedIdInsert() {
        return this.fieldConfig.isAllowGeneratedIdInsert();
    }

    public String getColumnDefinition() {
        return this.fieldConfig.getColumnDefinition();
    }

    public boolean isForeignAutoCreate() {
        return this.fieldConfig.isForeignAutoCreate();
    }

    public boolean isVersion() {
        return this.fieldConfig.isVersion();
    }

    public Object generateId() {
        return this.dataPersister.generateId();
    }

    public boolean isReadOnly() {
        return this.fieldConfig.isReadOnly();
    }

    public <FV> FV getFieldValueIfNotDefault(Object obj) throws SQLException {
        FV extractJavaFieldValue = extractJavaFieldValue(obj);
        if (isFieldValueDefault(extractJavaFieldValue)) {
            return null;
        }
        return extractJavaFieldValue;
    }

    public boolean isObjectsFieldValueDefault(Object obj) throws SQLException {
        return isFieldValueDefault(extractJavaFieldValue(obj));
    }

    public Object getJavaDefaultValueDefault() {
        if (this.field.getType() == Boolean.TYPE) {
            return Boolean.valueOf(DEFAULT_VALUE_BOOLEAN);
        }
        if (this.field.getType() == Byte.TYPE || this.field.getType() == Byte.class) {
            return Byte.valueOf(DEFAULT_VALUE_BYTE);
        }
        if (this.field.getType() == Character.TYPE || this.field.getType() == Character.class) {
            return Character.valueOf(DEFAULT_VALUE_CHAR);
        }
        if (this.field.getType() == Short.TYPE || this.field.getType() == Short.class) {
            return Short.valueOf(DEFAULT_VALUE_SHORT);
        }
        if (this.field.getType() == Integer.TYPE || this.field.getType() == Integer.class) {
            return Integer.valueOf(DEFAULT_VALUE_INT);
        }
        if (this.field.getType() == Long.TYPE || this.field.getType() == Long.class) {
            return Long.valueOf(DEFAULT_VALUE_LONG);
        }
        if (this.field.getType() == Float.TYPE || this.field.getType() == Float.class) {
            return Float.valueOf(DEFAULT_VALUE_FLOAT);
        }
        if (this.field.getType() == Double.TYPE || this.field.getType() == Double.class) {
            return Double.valueOf(DEFAULT_VALUE_DOUBLE);
        }
        return null;
    }

    public <T> int createWithForeignDao(T t) throws SQLException {
        return this.foreignDao.create(t);
    }

    public static FieldType createFieldType(ConnectionSource connectionSource, String str, Field field, Class<?> cls) throws SQLException {
        DatabaseFieldConfig fromField = DatabaseFieldConfig.fromField(connectionSource.getDatabaseType(), str, field);
        if (fromField == null) {
            return null;
        }
        return new FieldType(connectionSource, str, field, fromField, cls);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        FieldType fieldType = (FieldType) obj;
        if (!this.field.equals(fieldType.field)) {
            return false;
        }
        if (this.parentClass == null) {
            if (fieldType.parentClass != null) {
                return false;
            }
        } else if (!this.parentClass.equals(fieldType.parentClass)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.field.hashCode();
    }

    public String toString() {
        return getClass().getSimpleName() + ":name=" + this.field.getName() + ",class=" + this.field.getDeclaringClass().getSimpleName();
    }

    private boolean isFieldValueDefault(Object obj) {
        if (obj == null) {
            return true;
        }
        return obj.equals(getJavaDefaultValueDefault());
    }

    private FieldType findForeignFieldType(Class<?> cls, Class<?> cls2, BaseDaoImpl<?, ?> baseDaoImpl) throws SQLException {
        String foreignCollectionForeignFieldName = this.fieldConfig.getForeignCollectionForeignFieldName();
        FieldType[] fieldTypes = baseDaoImpl.getTableInfo().getFieldTypes();
        int length = fieldTypes.length;
        int i = 0;
        while (i < length) {
            FieldType fieldType = fieldTypes[i];
            if (fieldType.getType() != cls2 || (foreignCollectionForeignFieldName != null && !fieldType.getField().getName().equals(foreignCollectionForeignFieldName))) {
                i++;
            } else if (fieldType.fieldConfig.isForeign() || fieldType.fieldConfig.isForeignAutoRefresh()) {
                return fieldType;
            } else {
                throw new SQLException("Foreign collection object " + cls + " for field '" + this.field.getName() + "' contains a field of class " + cls2 + " but it's not foreign");
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Foreign collection class ").append(cls.getName());
        stringBuilder.append(" for field '").append(this.field.getName()).append("' column-name does not contain a foreign field");
        if (foreignCollectionForeignFieldName != null) {
            stringBuilder.append(" named '").append(foreignCollectionForeignFieldName).append(CoreConstants.SINGLE_QUOTE_CHAR);
        }
        stringBuilder.append(" of class ").append(cls2.getName());
        throw new SQLException(stringBuilder.toString());
    }

    private void assignDataType(DatabaseType databaseType, DataPersister dataPersister) throws SQLException {
        this.dataPersister = dataPersister;
        if (dataPersister != null) {
            this.fieldConverter = databaseType.getFieldConverter(dataPersister);
            if (this.isGeneratedId && !dataPersister.isValidGeneratedType()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Generated-id field '").append(this.field.getName());
                stringBuilder.append("' in ").append(this.field.getDeclaringClass().getSimpleName());
                stringBuilder.append(" can't be type ").append(this.dataPersister.getSqlType());
                stringBuilder.append(".  Must be one of: ");
                for (DataType dataType : DataType.values()) {
                    DataPersister dataPersister2 = dataType.getDataPersister();
                    if (dataPersister2 != null && dataPersister2.isValidGeneratedType()) {
                        stringBuilder.append(dataType).append(' ');
                    }
                }
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (this.fieldConfig.isThrowIfNull() && !dataPersister.isPrimitive()) {
                throw new SQLException("Field " + this.field.getName() + " must be a primitive if set with throwIfNull");
            } else if (!this.isId || dataPersister.isAppropriateId()) {
                this.dataTypeConfigObj = dataPersister.makeConfigObject(this);
                String defaultValue = this.fieldConfig.getDefaultValue();
                if (defaultValue == null) {
                    this.defaultValue = null;
                } else if (this.isGeneratedId) {
                    throw new SQLException("Field '" + this.field.getName() + "' cannot be a generatedId and have a default value '" + defaultValue + "'");
                } else {
                    this.defaultValue = this.fieldConverter.parseDefaultString(this, defaultValue);
                }
            } else {
                throw new SQLException("Field '" + this.field.getName() + "' is of data type " + dataPersister + " which cannot be the ID field");
            }
        } else if (!this.fieldConfig.isForeign() && !this.fieldConfig.isForeignCollection()) {
            throw new SQLException("Data persister for field " + this + " is null but the field is not a foreign or foreignCollection");
        }
    }
}
