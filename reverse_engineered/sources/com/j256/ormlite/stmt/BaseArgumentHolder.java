package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import java.sql.SQLException;

public abstract class BaseArgumentHolder implements ArgumentHolder {
    private String columnName = null;
    private FieldType fieldType = null;
    private SqlType sqlType = null;

    protected abstract Object getValue();

    protected abstract boolean isValueSet();

    public abstract void setValue(Object obj);

    public BaseArgumentHolder(String str) {
        this.columnName = str;
    }

    public BaseArgumentHolder(SqlType sqlType) {
        this.sqlType = sqlType;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setMetaInfo(String str) {
        if (this.columnName == null || this.columnName.equals(str)) {
            this.columnName = str;
            return;
        }
        throw new IllegalArgumentException("Column name cannot be set twice from " + this.columnName + " to " + str + ".  Using a SelectArg twice in query with different columns?");
    }

    public void setMetaInfo(FieldType fieldType) {
        if (this.fieldType == null || this.fieldType == fieldType) {
            this.fieldType = fieldType;
            return;
        }
        throw new IllegalArgumentException("FieldType name cannot be set twice from " + this.fieldType + " to " + fieldType + ".  Using a SelectArg twice in query with different columns?");
    }

    public void setMetaInfo(String str, FieldType fieldType) {
        setMetaInfo(str);
        setMetaInfo(fieldType);
    }

    public Object getSqlArgValue() throws SQLException {
        if (isValueSet()) {
            Object value = getValue();
            if (value == null) {
                return null;
            }
            if (this.fieldType == null) {
                return value;
            }
            if (this.fieldType.isForeign() && this.fieldType.getType() == value.getClass()) {
                return this.fieldType.getForeignIdField().extractJavaFieldValue(value);
            }
            return this.fieldType.convertJavaFieldToSqlArgValue(value);
        }
        throw new SQLException("Column value has not been set for " + this.columnName);
    }

    public FieldType getFieldType() {
        return this.fieldType;
    }

    public SqlType getSqlType() {
        return this.sqlType;
    }

    public String toString() {
        if (!isValueSet()) {
            return "[unset]";
        }
        try {
            Object sqlArgValue = getSqlArgValue();
            if (sqlArgValue == null) {
                return "[null]";
            }
            return sqlArgValue.toString();
        } catch (SQLException e) {
            return "[could not get value: " + e + "]";
        }
    }
}
