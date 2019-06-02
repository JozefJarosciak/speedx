package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedUpdate<T, ID> extends BaseMappedStatement<T, ID> {
    private final FieldType versionFieldType;
    private final int versionFieldTypeIndex;

    private MappedUpdate(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr, FieldType fieldType, int i) {
        super(tableInfo, str, fieldTypeArr);
        this.versionFieldType = fieldType;
        this.versionFieldTypeIndex = i;
    }

    public static <T, ID> MappedUpdate<T, ID> build(DatabaseType databaseType, TableInfo<T, ID> tableInfo) throws SQLException {
        FieldType idField = tableInfo.getIdField();
        if (idField == null) {
            throw new SQLException("Cannot update " + tableInfo.getDataClass() + " because it doesn't have an id field");
        }
        StringBuilder stringBuilder = new StringBuilder(64);
        BaseMappedStatement.appendTableName(databaseType, stringBuilder, "UPDATE ", tableInfo.getTableName());
        FieldType fieldType = null;
        int i = -1;
        int i2 = 0;
        for (FieldType fieldType2 : tableInfo.getFieldTypes()) {
            if (isFieldUpdatable(fieldType2, idField)) {
                if (fieldType2.isVersion()) {
                    i = i2;
                    fieldType = fieldType2;
                }
                i2++;
            }
        }
        i2++;
        if (fieldType != null) {
            i2++;
        }
        FieldType[] fieldTypeArr = new FieldType[i2];
        int i3 = 0;
        FieldType[] fieldTypes = tableInfo.getFieldTypes();
        int length = fieldTypes.length;
        int i4 = 0;
        Object obj = 1;
        while (i4 < length) {
            Object obj2;
            int i5;
            FieldType fieldType3 = fieldTypes[i4];
            if (isFieldUpdatable(fieldType3, idField)) {
                if (obj != null) {
                    stringBuilder.append("SET ");
                    obj = null;
                } else {
                    stringBuilder.append(", ");
                }
                BaseMappedStatement.appendFieldColumnName(databaseType, stringBuilder, fieldType3, null);
                int i6 = i3 + 1;
                fieldTypeArr[i3] = fieldType3;
                stringBuilder.append("= ?");
                obj2 = obj;
                i2 = i6;
            } else {
                i5 = i3;
                obj2 = obj;
                i2 = i5;
            }
            i4++;
            i5 = i2;
            obj = obj2;
            i3 = i5;
        }
        stringBuilder.append(' ');
        BaseMappedStatement.appendWhereFieldEq(databaseType, idField, stringBuilder, null);
        i2 = i3 + 1;
        fieldTypeArr[i3] = idField;
        if (fieldType != null) {
            stringBuilder.append(" AND ");
            BaseMappedStatement.appendFieldColumnName(databaseType, stringBuilder, fieldType, null);
            stringBuilder.append("= ?");
            i3 = i2 + 1;
            fieldTypeArr[i2] = fieldType;
        }
        return new MappedUpdate(tableInfo, stringBuilder.toString(), fieldTypeArr, fieldType, i);
    }

    public int update(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        int i = 0;
        try {
            if (this.argFieldTypes.length <= 1) {
                return 0;
            }
            Object obj;
            Object fieldObjects = getFieldObjects(t);
            if (this.versionFieldType != null) {
                Object moveToNextValue = this.versionFieldType.moveToNextValue(this.versionFieldType.extractJavaFieldValue(t));
                fieldObjects[this.versionFieldTypeIndex] = this.versionFieldType.convertJavaFieldToSqlArgValue(moveToNextValue);
                obj = moveToNextValue;
            } else {
                obj = null;
            }
            int update = databaseConnection.update(this.statement, fieldObjects, this.argFieldTypes);
            if (update > 0) {
                if (obj != null) {
                    this.versionFieldType.assignField(t, obj, false, null);
                }
                if (objectCache != null) {
                    T t2 = objectCache.get(this.clazz, this.idField.extractJavaFieldValue(t));
                    if (!(t2 == null || t2 == t)) {
                        FieldType[] fieldTypes = this.tableInfo.getFieldTypes();
                        int length = fieldTypes.length;
                        while (i < length) {
                            FieldType fieldType = fieldTypes[i];
                            if (fieldType != this.idField) {
                                fieldType.assignField(t2, fieldType.extractJavaFieldValue(t), false, objectCache);
                            }
                            i++;
                        }
                    }
                }
            }
            logger.debug("update data with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(fieldObjects.length), Integer.valueOf(update));
            if (fieldObjects.length > 0) {
                logger.trace("update arguments: {}", fieldObjects);
            }
            return update;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Unable to run update stmt on object " + t + ": " + this.statement, e);
        }
    }

    private static boolean isFieldUpdatable(FieldType fieldType, FieldType fieldType2) {
        if (fieldType == fieldType2 || fieldType.isForeignCollection() || fieldType.isReadOnly()) {
            return false;
        }
        return true;
    }
}
