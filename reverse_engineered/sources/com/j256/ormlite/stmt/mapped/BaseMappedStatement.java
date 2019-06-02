package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseMappedStatement<T, ID> {
    protected static Logger logger = LoggerFactory.getLogger(BaseMappedStatement.class);
    protected final FieldType[] argFieldTypes;
    protected final Class<T> clazz;
    protected final FieldType idField;
    protected final String statement;
    protected final TableInfo<T, ID> tableInfo;

    protected BaseMappedStatement(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr) {
        this.tableInfo = tableInfo;
        this.clazz = tableInfo.getDataClass();
        this.idField = tableInfo.getIdField();
        this.statement = str;
        this.argFieldTypes = fieldTypeArr;
    }

    protected Object[] getFieldObjects(Object obj) throws SQLException {
        Object[] objArr = new Object[this.argFieldTypes.length];
        for (int i = 0; i < this.argFieldTypes.length; i++) {
            FieldType fieldType = this.argFieldTypes[i];
            if (fieldType.isAllowGeneratedIdInsert()) {
                objArr[i] = fieldType.getFieldValueIfNotDefault(obj);
            } else {
                objArr[i] = fieldType.extractJavaFieldToSqlArgValue(obj);
            }
            if (objArr[i] == null && fieldType.getDefaultValue() != null) {
                objArr[i] = fieldType.getDefaultValue();
            }
        }
        return objArr;
    }

    protected Object convertIdToFieldObject(ID id) throws SQLException {
        return this.idField.convertJavaFieldToSqlArgValue(id);
    }

    static void appendWhereFieldEq(DatabaseType databaseType, FieldType fieldType, StringBuilder stringBuilder, List<FieldType> list) {
        stringBuilder.append("WHERE ");
        appendFieldColumnName(databaseType, stringBuilder, fieldType, list);
        stringBuilder.append("= ?");
    }

    static void appendTableName(DatabaseType databaseType, StringBuilder stringBuilder, String str, String str2) {
        if (str != null) {
            stringBuilder.append(str);
        }
        databaseType.appendEscapedEntityName(stringBuilder, str2);
        stringBuilder.append(' ');
    }

    static void appendFieldColumnName(DatabaseType databaseType, StringBuilder stringBuilder, FieldType fieldType, List<FieldType> list) {
        databaseType.appendEscapedEntityName(stringBuilder, fieldType.getColumnName());
        if (list != null) {
            list.add(fieldType);
        }
        stringBuilder.append(' ');
    }

    public String toString() {
        return "MappedStatement: " + this.statement;
    }
}
