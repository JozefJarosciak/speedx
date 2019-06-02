package com.j256.ormlite.field;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.types.VoidType;
import com.j256.ormlite.misc.JavaxPersistence;
import com.j256.ormlite.table.DatabaseTableConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class DatabaseFieldConfig {
    public static final boolean DEFAULT_CAN_BE_NULL = true;
    public static final DataType DEFAULT_DATA_TYPE = DataType.UNKNOWN;
    public static final boolean DEFAULT_FOREIGN_COLLECTION_ORDER_ASCENDING = true;
    private static final int DEFAULT_MAX_EAGER_FOREIGN_COLLECTION_LEVEL = 1;
    public static final Class<? extends DataPersister> DEFAULT_PERSISTER_CLASS = VoidType.class;
    private boolean allowGeneratedIdInsert;
    private boolean canBeNull = true;
    private String columnDefinition;
    private String columnName;
    private DataPersister dataPersister;
    private DataType dataType = DEFAULT_DATA_TYPE;
    private String defaultValue;
    private String fieldName;
    private boolean foreign;
    private boolean foreignAutoCreate;
    private boolean foreignAutoRefresh;
    private boolean foreignCollection;
    private String foreignCollectionColumnName;
    private boolean foreignCollectionEager;
    private String foreignCollectionForeignFieldName;
    private int foreignCollectionMaxEagerLevel = 1;
    private boolean foreignCollectionOrderAscending = true;
    private String foreignCollectionOrderColumnName;
    private String foreignColumnName;
    private DatabaseTableConfig<?> foreignTableConfig;
    private String format;
    private boolean generatedId;
    private String generatedIdSequence;
    private boolean id;
    private boolean index;
    private String indexName;
    private int maxForeignAutoRefreshLevel = -1;
    private boolean persisted = true;
    private Class<? extends DataPersister> persisterClass = DEFAULT_PERSISTER_CLASS;
    private boolean readOnly;
    private boolean throwIfNull;
    private boolean unique;
    private boolean uniqueCombo;
    private boolean uniqueIndex;
    private String uniqueIndexName;
    private Enum<?> unknownEnumValue;
    private boolean useGetSet;
    private boolean version;
    private int width;

    public DatabaseFieldConfig(String str) {
        this.fieldName = str;
    }

    public DatabaseFieldConfig(String str, String str2, DataType dataType, String str3, int i, boolean z, boolean z2, boolean z3, String str4, boolean z4, DatabaseTableConfig<?> databaseTableConfig, boolean z5, Enum<?> enumR, boolean z6, String str5, boolean z7, String str6, String str7, boolean z8, int i2, int i3) {
        this.fieldName = str;
        this.columnName = str2;
        this.dataType = DataType.UNKNOWN;
        this.defaultValue = str3;
        this.width = i;
        this.canBeNull = z;
        this.id = z2;
        this.generatedId = z3;
        this.generatedIdSequence = str4;
        this.foreign = z4;
        this.foreignTableConfig = databaseTableConfig;
        this.useGetSet = z5;
        this.unknownEnumValue = enumR;
        this.throwIfNull = z6;
        this.format = str5;
        this.unique = z7;
        this.indexName = str6;
        this.uniqueIndexName = str7;
        this.foreignAutoRefresh = z8;
        this.maxForeignAutoRefreshLevel = i2;
        this.foreignCollectionMaxEagerLevel = i3;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String str) {
        this.fieldName = str;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String str) {
        this.columnName = str;
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public DataPersister getDataPersister() {
        if (this.dataPersister == null) {
            return this.dataType.getDataPersister();
        }
        return this.dataPersister;
    }

    public void setDataPersister(DataPersister dataPersister) {
        this.dataPersister = dataPersister;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String str) {
        this.defaultValue = str;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public boolean isCanBeNull() {
        return this.canBeNull;
    }

    public void setCanBeNull(boolean z) {
        this.canBeNull = z;
    }

    public boolean isId() {
        return this.id;
    }

    public void setId(boolean z) {
        this.id = z;
    }

    public boolean isGeneratedId() {
        return this.generatedId;
    }

    public void setGeneratedId(boolean z) {
        this.generatedId = z;
    }

    public String getGeneratedIdSequence() {
        return this.generatedIdSequence;
    }

    public void setGeneratedIdSequence(String str) {
        this.generatedIdSequence = str;
    }

    public boolean isForeign() {
        return this.foreign;
    }

    public void setForeign(boolean z) {
        this.foreign = z;
    }

    public DatabaseTableConfig<?> getForeignTableConfig() {
        return this.foreignTableConfig;
    }

    public void setForeignTableConfig(DatabaseTableConfig<?> databaseTableConfig) {
        this.foreignTableConfig = databaseTableConfig;
    }

    public boolean isUseGetSet() {
        return this.useGetSet;
    }

    public void setUseGetSet(boolean z) {
        this.useGetSet = z;
    }

    public Enum<?> getUnknownEnumValue() {
        return this.unknownEnumValue;
    }

    public void setUnknownEnumValue(Enum<?> enumR) {
        this.unknownEnumValue = enumR;
    }

    public boolean isThrowIfNull() {
        return this.throwIfNull;
    }

    public void setThrowIfNull(boolean z) {
        this.throwIfNull = z;
    }

    public boolean isPersisted() {
        return this.persisted;
    }

    public void setPersisted(boolean z) {
        this.persisted = z;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public boolean isUnique() {
        return this.unique;
    }

    public void setUnique(boolean z) {
        this.unique = z;
    }

    public boolean isUniqueCombo() {
        return this.uniqueCombo;
    }

    public void setUniqueCombo(boolean z) {
        this.uniqueCombo = z;
    }

    public boolean isIndex() {
        return this.index;
    }

    public void setIndex(boolean z) {
        this.index = z;
    }

    public String getIndexName(String str) {
        if (this.index && this.indexName == null) {
            this.indexName = findIndexName(str);
        }
        return this.indexName;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public boolean isUniqueIndex() {
        return this.uniqueIndex;
    }

    public void setUniqueIndex(boolean z) {
        this.uniqueIndex = z;
    }

    public String getUniqueIndexName(String str) {
        if (this.uniqueIndex && this.uniqueIndexName == null) {
            this.uniqueIndexName = findIndexName(str);
        }
        return this.uniqueIndexName;
    }

    public void setUniqueIndexName(String str) {
        this.uniqueIndexName = str;
    }

    public void setForeignAutoRefresh(boolean z) {
        this.foreignAutoRefresh = z;
    }

    public boolean isForeignAutoRefresh() {
        return this.foreignAutoRefresh;
    }

    public int getMaxForeignAutoRefreshLevel() {
        return this.maxForeignAutoRefreshLevel;
    }

    public void setMaxForeignAutoRefreshLevel(int i) {
        this.maxForeignAutoRefreshLevel = i;
    }

    public boolean isForeignCollection() {
        return this.foreignCollection;
    }

    public void setForeignCollection(boolean z) {
        this.foreignCollection = z;
    }

    public boolean isForeignCollectionEager() {
        return this.foreignCollectionEager;
    }

    public void setForeignCollectionEager(boolean z) {
        this.foreignCollectionEager = z;
    }

    public int getForeignCollectionMaxEagerLevel() {
        return this.foreignCollectionMaxEagerLevel;
    }

    public void setForeignCollectionMaxEagerLevel(int i) {
        this.foreignCollectionMaxEagerLevel = i;
    }

    @Deprecated
    public void setMaxEagerForeignCollectionLevel(int i) {
        this.foreignCollectionMaxEagerLevel = i;
    }

    @Deprecated
    public void setForeignCollectionMaxEagerForeignCollectionLevel(int i) {
        this.foreignCollectionMaxEagerLevel = i;
    }

    public String getForeignCollectionColumnName() {
        return this.foreignCollectionColumnName;
    }

    public void setForeignCollectionColumnName(String str) {
        this.foreignCollectionColumnName = str;
    }

    public String getForeignCollectionOrderColumnName() {
        return this.foreignCollectionOrderColumnName;
    }

    @Deprecated
    public void setForeignCollectionOrderColumn(String str) {
        this.foreignCollectionOrderColumnName = str;
    }

    public void setForeignCollectionOrderColumnName(String str) {
        this.foreignCollectionOrderColumnName = str;
    }

    public boolean isForeignCollectionOrderAscending() {
        return this.foreignCollectionOrderAscending;
    }

    public void setForeignCollectionOrderAscending(boolean z) {
        this.foreignCollectionOrderAscending = z;
    }

    public String getForeignCollectionForeignFieldName() {
        return this.foreignCollectionForeignFieldName;
    }

    @Deprecated
    public void setForeignCollectionForeignColumnName(String str) {
        this.foreignCollectionForeignFieldName = str;
    }

    public void setForeignCollectionForeignFieldName(String str) {
        this.foreignCollectionForeignFieldName = str;
    }

    public Class<? extends DataPersister> getPersisterClass() {
        return this.persisterClass;
    }

    public void setPersisterClass(Class<? extends DataPersister> cls) {
        this.persisterClass = cls;
    }

    public boolean isAllowGeneratedIdInsert() {
        return this.allowGeneratedIdInsert;
    }

    public void setAllowGeneratedIdInsert(boolean z) {
        this.allowGeneratedIdInsert = z;
    }

    public String getColumnDefinition() {
        return this.columnDefinition;
    }

    public void setColumnDefinition(String str) {
        this.columnDefinition = str;
    }

    public boolean isForeignAutoCreate() {
        return this.foreignAutoCreate;
    }

    public void setForeignAutoCreate(boolean z) {
        this.foreignAutoCreate = z;
    }

    public boolean isVersion() {
        return this.version;
    }

    public void setVersion(boolean z) {
        this.version = z;
    }

    public String getForeignColumnName() {
        return this.foreignColumnName;
    }

    public void setForeignColumnName(String str) {
        this.foreignColumnName = str;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean z) {
        this.readOnly = z;
    }

    public static DatabaseFieldConfig fromField(DatabaseType databaseType, String str, Field field) throws SQLException {
        DatabaseField databaseField = (DatabaseField) field.getAnnotation(DatabaseField.class);
        if (databaseField == null) {
            ForeignCollectionField foreignCollectionField = (ForeignCollectionField) field.getAnnotation(ForeignCollectionField.class);
            if (foreignCollectionField != null) {
                return fromForeignCollection(databaseType, field, foreignCollectionField);
            }
            return JavaxPersistence.createFieldConfig(databaseType, field);
        } else if (databaseField.persisted()) {
            return fromDatabaseField(databaseType, str, field, databaseField);
        } else {
            return null;
        }
    }

    public static Method findGetMethod(Field field, boolean z) {
        String methodFromField = methodFromField(field, "get");
        try {
            Method method = field.getDeclaringClass().getMethod(methodFromField, new Class[0]);
            if (method.getReturnType() == field.getType()) {
                return method;
            }
            if (!z) {
                return null;
            }
            throw new IllegalArgumentException("Return type of get method " + methodFromField + " does not return " + field.getType());
        } catch (Exception e) {
            if (!z) {
                return null;
            }
            throw new IllegalArgumentException("Could not find appropriate get method for " + field);
        }
    }

    public static Method findSetMethod(Field field, boolean z) {
        String methodFromField = methodFromField(field, "set");
        try {
            Method method = field.getDeclaringClass().getMethod(methodFromField, new Class[]{field.getType()});
            if (method.getReturnType() == Void.TYPE) {
                return method;
            }
            if (!z) {
                return null;
            }
            throw new IllegalArgumentException("Return type of set method " + methodFromField + " returns " + method.getReturnType() + " instead of void");
        } catch (Exception e) {
            if (!z) {
                return null;
            }
            throw new IllegalArgumentException("Could not find appropriate set method for " + field);
        }
    }

    public static DatabaseFieldConfig fromDatabaseField(DatabaseType databaseType, String str, Field field, DatabaseField databaseField) {
        DatabaseFieldConfig databaseFieldConfig = new DatabaseFieldConfig();
        databaseFieldConfig.fieldName = field.getName();
        if (databaseType.isEntityNamesMustBeUpCase()) {
            databaseFieldConfig.fieldName = databaseFieldConfig.fieldName.toUpperCase();
        }
        databaseFieldConfig.columnName = valueIfNotBlank(databaseField.columnName());
        databaseFieldConfig.dataType = databaseField.dataType();
        String defaultValue = databaseField.defaultValue();
        if (!defaultValue.equals(DatabaseField.DEFAULT_STRING)) {
            databaseFieldConfig.defaultValue = defaultValue;
        }
        databaseFieldConfig.width = databaseField.width();
        databaseFieldConfig.canBeNull = databaseField.canBeNull();
        databaseFieldConfig.id = databaseField.id();
        databaseFieldConfig.generatedId = databaseField.generatedId();
        databaseFieldConfig.generatedIdSequence = valueIfNotBlank(databaseField.generatedIdSequence());
        databaseFieldConfig.foreign = databaseField.foreign();
        databaseFieldConfig.useGetSet = databaseField.useGetSet();
        databaseFieldConfig.unknownEnumValue = findMatchingEnumVal(field, databaseField.unknownEnumName());
        databaseFieldConfig.throwIfNull = databaseField.throwIfNull();
        databaseFieldConfig.format = valueIfNotBlank(databaseField.format());
        databaseFieldConfig.unique = databaseField.unique();
        databaseFieldConfig.uniqueCombo = databaseField.uniqueCombo();
        databaseFieldConfig.index = databaseField.index();
        databaseFieldConfig.indexName = valueIfNotBlank(databaseField.indexName());
        databaseFieldConfig.uniqueIndex = databaseField.uniqueIndex();
        databaseFieldConfig.uniqueIndexName = valueIfNotBlank(databaseField.uniqueIndexName());
        databaseFieldConfig.foreignAutoRefresh = databaseField.foreignAutoRefresh();
        databaseFieldConfig.maxForeignAutoRefreshLevel = databaseField.maxForeignAutoRefreshLevel();
        databaseFieldConfig.persisterClass = databaseField.persisterClass();
        databaseFieldConfig.allowGeneratedIdInsert = databaseField.allowGeneratedIdInsert();
        databaseFieldConfig.columnDefinition = valueIfNotBlank(databaseField.columnDefinition());
        databaseFieldConfig.foreignAutoCreate = databaseField.foreignAutoCreate();
        databaseFieldConfig.version = databaseField.version();
        databaseFieldConfig.foreignColumnName = valueIfNotBlank(databaseField.foreignColumnName());
        databaseFieldConfig.readOnly = databaseField.readOnly();
        return databaseFieldConfig;
    }

    public void postProcess() {
        if (this.foreignColumnName != null) {
            this.foreignAutoRefresh = true;
        }
        if (this.foreignAutoRefresh && this.maxForeignAutoRefreshLevel == -1) {
            this.maxForeignAutoRefreshLevel = 2;
        }
    }

    public static Enum<?> findMatchingEnumVal(Field field, String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        for (Enum<?> enumR : (Enum[]) field.getType().getEnumConstants()) {
            if (enumR.name().equals(str)) {
                return enumR;
            }
        }
        throw new IllegalArgumentException("Unknwown enum unknown name " + str + " for field " + field);
    }

    private static DatabaseFieldConfig fromForeignCollection(DatabaseType databaseType, Field field, ForeignCollectionField foreignCollectionField) {
        DatabaseFieldConfig databaseFieldConfig = new DatabaseFieldConfig();
        databaseFieldConfig.fieldName = field.getName();
        if (foreignCollectionField.columnName().length() > 0) {
            databaseFieldConfig.columnName = foreignCollectionField.columnName();
        }
        databaseFieldConfig.foreignCollection = true;
        databaseFieldConfig.foreignCollectionEager = foreignCollectionField.eager();
        int maxEagerForeignCollectionLevel = foreignCollectionField.maxEagerForeignCollectionLevel();
        if (maxEagerForeignCollectionLevel != 1) {
            databaseFieldConfig.foreignCollectionMaxEagerLevel = maxEagerForeignCollectionLevel;
        } else {
            databaseFieldConfig.foreignCollectionMaxEagerLevel = foreignCollectionField.maxEagerLevel();
        }
        databaseFieldConfig.foreignCollectionOrderColumnName = valueIfNotBlank(foreignCollectionField.orderColumnName());
        databaseFieldConfig.foreignCollectionOrderAscending = foreignCollectionField.orderAscending();
        databaseFieldConfig.foreignCollectionColumnName = valueIfNotBlank(foreignCollectionField.columnName());
        String valueIfNotBlank = valueIfNotBlank(foreignCollectionField.foreignFieldName());
        if (valueIfNotBlank == null) {
            databaseFieldConfig.foreignCollectionForeignFieldName = valueIfNotBlank(valueIfNotBlank(foreignCollectionField.foreignColumnName()));
        } else {
            databaseFieldConfig.foreignCollectionForeignFieldName = valueIfNotBlank;
        }
        return databaseFieldConfig;
    }

    private String findIndexName(String str) {
        if (this.columnName == null) {
            return str + "_" + this.fieldName + "_idx";
        }
        return str + "_" + this.columnName + "_idx";
    }

    private static String valueIfNotBlank(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return str;
    }

    private static String methodFromField(Field field, String str) {
        return str + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }
}
