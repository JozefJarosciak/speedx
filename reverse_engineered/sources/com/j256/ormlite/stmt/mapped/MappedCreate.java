package com.j256.ormlite.stmt.mapped;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Log.Level;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.GeneratedKeyHolder;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedCreate<T, ID> extends BaseMappedStatement<T, ID> {
    private String dataClassName;
    private final String queryNextSequenceStmt;
    private int versionFieldTypeIndex;

    private static class KeyHolder implements GeneratedKeyHolder {
        Number key;

        private KeyHolder() {
        }

        public Number getKey() {
            return this.key;
        }

        public void addKey(Number number) throws SQLException {
            if (this.key == null) {
                this.key = number;
                return;
            }
            throw new SQLException("generated key has already been set to " + this.key + ", now set to " + number);
        }
    }

    private MappedCreate(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr, String str2, int i) {
        super(tableInfo, str, fieldTypeArr);
        this.dataClassName = tableInfo.getDataClass().getSimpleName();
        this.queryNextSequenceStmt = str2;
        this.versionFieldTypeIndex = i;
    }

    public int insert(DatabaseType databaseType, DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        Object obj;
        FieldType[] fieldTypes;
        int length;
        FieldType fieldType;
        Object extractRawJavaFieldValue;
        Object fieldObjects;
        Object obj2;
        int insert;
        Number key;
        int i = 0;
        if (this.idField != null) {
            int i2;
            if (!this.idField.isAllowGeneratedIdInsert() || this.idField.isObjectsFieldValueDefault(t)) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (this.idField.isSelfGeneratedId() && this.idField.isGeneratedId()) {
                if (i2 != 0) {
                    this.idField.assignField(t, this.idField.generateId(), false, objectCache);
                    obj = null;
                    if (this.tableInfo.isForeignAutoCreate()) {
                        fieldTypes = this.tableInfo.getFieldTypes();
                        length = fieldTypes.length;
                        while (i < length) {
                            fieldType = fieldTypes[i];
                            if (fieldType.isForeignAutoCreate()) {
                                extractRawJavaFieldValue = fieldType.extractRawJavaFieldValue(t);
                                fieldType.createWithForeignDao(extractRawJavaFieldValue);
                            }
                            i++;
                        }
                    }
                    fieldObjects = getFieldObjects(t);
                    if (this.versionFieldTypeIndex >= 0) {
                    }
                    obj2 = null;
                    insert = databaseConnection.insert(this.statement, fieldObjects, this.argFieldTypes, obj);
                    logger.debug("insert data with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(fieldObjects.length), Integer.valueOf(insert));
                    if (fieldObjects.length > 0) {
                        logger.trace("insert arguments: {}", fieldObjects);
                    }
                    if (insert > 0) {
                        if (obj2 != null) {
                            this.argFieldTypes[this.versionFieldTypeIndex].assignField(t, obj2, false, null);
                        }
                        if (obj != null) {
                            key = obj.getKey();
                            if (key != null) {
                                throw new SQLException("generated-id key was not set by the update call");
                            } else if (key.longValue() != 0) {
                                throw new SQLException("generated-id key must not be 0 value");
                            } else {
                                assignIdValue(t, key, "keyholder", objectCache);
                            }
                        }
                        objectCache.put(this.clazz, this.idField.extractJavaFieldValue(t), t);
                    }
                    return insert;
                }
            } else if (this.idField.isGeneratedIdSequence() && databaseType.isSelectSequenceBeforeInsert()) {
                if (i2 != 0) {
                    assignSequenceId(databaseConnection, t, objectCache);
                    obj = null;
                    if (this.tableInfo.isForeignAutoCreate()) {
                        fieldTypes = this.tableInfo.getFieldTypes();
                        length = fieldTypes.length;
                        while (i < length) {
                            fieldType = fieldTypes[i];
                            if (fieldType.isForeignAutoCreate()) {
                                extractRawJavaFieldValue = fieldType.extractRawJavaFieldValue(t);
                                fieldType.createWithForeignDao(extractRawJavaFieldValue);
                            }
                            i++;
                        }
                    }
                    fieldObjects = getFieldObjects(t);
                    if (this.versionFieldTypeIndex >= 0) {
                    }
                    obj2 = null;
                    insert = databaseConnection.insert(this.statement, fieldObjects, this.argFieldTypes, obj);
                    logger.debug("insert data with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(fieldObjects.length), Integer.valueOf(insert));
                    if (fieldObjects.length > 0) {
                        logger.trace("insert arguments: {}", fieldObjects);
                    }
                    if (insert > 0) {
                        if (obj2 != null) {
                            this.argFieldTypes[this.versionFieldTypeIndex].assignField(t, obj2, false, null);
                        }
                        if (obj != null) {
                            key = obj.getKey();
                            if (key != null) {
                                throw new SQLException("generated-id key was not set by the update call");
                            } else if (key.longValue() != 0) {
                                assignIdValue(t, key, "keyholder", objectCache);
                            } else {
                                throw new SQLException("generated-id key must not be 0 value");
                            }
                        }
                        objectCache.put(this.clazz, this.idField.extractJavaFieldValue(t), t);
                    }
                    return insert;
                }
            } else if (this.idField.isGeneratedId() && i2 != 0) {
                KeyHolder keyHolder = new KeyHolder();
                if (this.tableInfo.isForeignAutoCreate()) {
                    fieldTypes = this.tableInfo.getFieldTypes();
                    length = fieldTypes.length;
                    while (i < length) {
                        fieldType = fieldTypes[i];
                        if (fieldType.isForeignAutoCreate()) {
                            extractRawJavaFieldValue = fieldType.extractRawJavaFieldValue(t);
                            if (extractRawJavaFieldValue != null && fieldType.getForeignIdField().isObjectsFieldValueDefault(extractRawJavaFieldValue)) {
                                fieldType.createWithForeignDao(extractRawJavaFieldValue);
                            }
                        }
                        i++;
                    }
                }
                fieldObjects = getFieldObjects(t);
                if (this.versionFieldTypeIndex >= 0 || fieldObjects[this.versionFieldTypeIndex] != null) {
                    obj2 = null;
                } else {
                    FieldType fieldType2 = this.argFieldTypes[this.versionFieldTypeIndex];
                    obj2 = fieldType2.moveToNextValue(null);
                    fieldObjects[this.versionFieldTypeIndex] = fieldType2.convertJavaFieldToSqlArgValue(obj2);
                }
                insert = databaseConnection.insert(this.statement, fieldObjects, this.argFieldTypes, obj);
                logger.debug("insert data with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(fieldObjects.length), Integer.valueOf(insert));
                if (fieldObjects.length > 0) {
                    logger.trace("insert arguments: {}", fieldObjects);
                }
                if (insert > 0) {
                    if (obj2 != null) {
                        this.argFieldTypes[this.versionFieldTypeIndex].assignField(t, obj2, false, null);
                    }
                    if (obj != null) {
                        key = obj.getKey();
                        if (key != null) {
                            throw new SQLException("generated-id key was not set by the update call");
                        } else if (key.longValue() != 0) {
                            throw new SQLException("generated-id key must not be 0 value");
                        } else {
                            assignIdValue(t, key, "keyholder", objectCache);
                        }
                    }
                    if (objectCache != null && foreignCollectionsAreAssigned(this.tableInfo.getForeignCollections(), t)) {
                        objectCache.put(this.clazz, this.idField.extractJavaFieldValue(t), t);
                    }
                }
                return insert;
            }
        }
        obj = null;
        try {
            if (this.tableInfo.isForeignAutoCreate()) {
                fieldTypes = this.tableInfo.getFieldTypes();
                length = fieldTypes.length;
                while (i < length) {
                    fieldType = fieldTypes[i];
                    if (fieldType.isForeignAutoCreate()) {
                        extractRawJavaFieldValue = fieldType.extractRawJavaFieldValue(t);
                        fieldType.createWithForeignDao(extractRawJavaFieldValue);
                    }
                    i++;
                }
            }
            fieldObjects = getFieldObjects(t);
            if (this.versionFieldTypeIndex >= 0) {
            }
            obj2 = null;
            insert = databaseConnection.insert(this.statement, fieldObjects, this.argFieldTypes, obj);
            logger.debug("insert data with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(fieldObjects.length), Integer.valueOf(insert));
            if (fieldObjects.length > 0) {
                logger.trace("insert arguments: {}", fieldObjects);
            }
            if (insert > 0) {
                if (obj2 != null) {
                    this.argFieldTypes[this.versionFieldTypeIndex].assignField(t, obj2, false, null);
                }
                if (obj != null) {
                    key = obj.getKey();
                    if (key != null) {
                        throw new SQLException("generated-id key was not set by the update call");
                    } else if (key.longValue() != 0) {
                        assignIdValue(t, key, "keyholder", objectCache);
                    } else {
                        throw new SQLException("generated-id key must not be 0 value");
                    }
                }
                objectCache.put(this.clazz, this.idField.extractJavaFieldValue(t), t);
            }
            return insert;
        } catch (Object obj22) {
            logger.debug("insert data with statement '{}' and {} args, threw exception: {}", this.statement, Integer.valueOf(fieldObjects.length), obj22);
            if (fieldObjects.length > 0) {
                logger.trace("insert arguments: {}", fieldObjects);
            }
            throw obj22;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Unable to run insert stmt on object " + t + ": " + this.statement, e);
        }
    }

    public static <T, ID> MappedCreate<T, ID> build(DatabaseType databaseType, TableInfo<T, ID> tableInfo) {
        int i;
        int i2;
        StringBuilder stringBuilder = new StringBuilder(128);
        BaseMappedStatement.appendTableName(databaseType, stringBuilder, "INSERT INTO ", tableInfo.getTableName());
        int i3 = -1;
        int i4 = 0;
        for (FieldType fieldType : tableInfo.getFieldTypes()) {
            if (isFieldCreatable(databaseType, fieldType)) {
                if (fieldType.isVersion()) {
                    i3 = i4;
                }
                i4++;
            }
        }
        FieldType[] fieldTypeArr = new FieldType[i4];
        if (i4 == 0) {
            databaseType.appendInsertNoColumns(stringBuilder);
        } else {
            stringBuilder.append(CoreConstants.LEFT_PARENTHESIS_CHAR);
            Object obj = 1;
            i = 0;
            for (FieldType fieldType2 : tableInfo.getFieldTypes()) {
                if (isFieldCreatable(databaseType, fieldType2)) {
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append(",");
                    }
                    BaseMappedStatement.appendFieldColumnName(databaseType, stringBuilder, fieldType2, null);
                    i2 = i + 1;
                    fieldTypeArr[i] = fieldType2;
                    i = i2;
                }
            }
            stringBuilder.append(") VALUES (");
            obj = 1;
            for (FieldType isFieldCreatable : tableInfo.getFieldTypes()) {
                if (isFieldCreatable(databaseType, isFieldCreatable)) {
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(CallerData.NA);
                }
            }
            stringBuilder.append(")");
        }
        return new MappedCreate(tableInfo, stringBuilder.toString(), fieldTypeArr, buildQueryNextSequence(databaseType, tableInfo.getIdField()), i3);
    }

    private boolean foreignCollectionsAreAssigned(FieldType[] fieldTypeArr, Object obj) throws SQLException {
        for (FieldType extractJavaFieldValue : fieldTypeArr) {
            if (extractJavaFieldValue.extractJavaFieldValue(obj) == null) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFieldCreatable(DatabaseType databaseType, FieldType fieldType) {
        if (fieldType.isForeignCollection() || fieldType.isReadOnly()) {
            return false;
        }
        if (databaseType.isIdSequenceNeeded() && databaseType.isSelectSequenceBeforeInsert()) {
            return true;
        }
        if (!fieldType.isGeneratedId() || fieldType.isSelfGeneratedId() || fieldType.isAllowGeneratedIdInsert()) {
            return true;
        }
        return false;
    }

    private static String buildQueryNextSequence(DatabaseType databaseType, FieldType fieldType) {
        if (fieldType == null) {
            return null;
        }
        String generatedIdSequence = fieldType.getGeneratedIdSequence();
        if (generatedIdSequence == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(64);
        databaseType.appendSelectNextValFromSequence(stringBuilder, generatedIdSequence);
        return stringBuilder.toString();
    }

    private void assignSequenceId(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        long queryForLong = databaseConnection.queryForLong(this.queryNextSequenceStmt);
        logger.debug("queried for sequence {} using stmt: {}", Long.valueOf(queryForLong), this.queryNextSequenceStmt);
        if (queryForLong == 0) {
            throw new SQLException("Should not have returned 0 for stmt: " + this.queryNextSequenceStmt);
        }
        assignIdValue(t, Long.valueOf(queryForLong), "sequence", objectCache);
    }

    private void assignIdValue(T t, Number number, String str, ObjectCache objectCache) throws SQLException {
        this.idField.assignIdValue(t, number, objectCache);
        if (logger.isLevelEnabled(Level.DEBUG)) {
            logger.debug("assigned id '{}' from {} to '{}' in {} object", new Object[]{number, str, this.idField.getFieldName(), this.dataClassName});
        }
    }
}
