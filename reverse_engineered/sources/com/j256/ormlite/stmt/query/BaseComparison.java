package com.j256.ormlite.stmt.query;

import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.ColumnArg;
import com.j256.ormlite.stmt.SelectArg;
import java.sql.SQLException;
import java.util.List;

abstract class BaseComparison implements Comparison {
    private static final String NUMBER_CHARACTERS = "0123456789.-+";
    protected final String columnName;
    protected final FieldType fieldType;
    private final Object value;

    public abstract void appendOperation(StringBuilder stringBuilder);

    protected BaseComparison(String str, FieldType fieldType, Object obj, boolean z) throws SQLException {
        if (!z || fieldType == null || fieldType.isComparable()) {
            this.columnName = str;
            this.fieldType = fieldType;
            this.value = obj;
            return;
        }
        throw new SQLException("Field '" + str + "' is of data type " + fieldType.getDataPersister() + " which can not be compared");
    }

    public void appendSql(DatabaseType databaseType, String str, StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException {
        if (str != null) {
            databaseType.appendEscapedEntityName(stringBuilder, str);
            stringBuilder.append(CoreConstants.DOT);
        }
        databaseType.appendEscapedEntityName(stringBuilder, this.columnName);
        stringBuilder.append(' ');
        appendOperation(stringBuilder);
        appendValue(databaseType, stringBuilder, list);
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void appendValue(DatabaseType databaseType, StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException {
        appendArgOrValue(databaseType, this.fieldType, stringBuilder, list, this.value);
    }

    protected void appendArgOrValue(DatabaseType databaseType, FieldType fieldType, StringBuilder stringBuilder, List<ArgumentHolder> list, Object obj) throws SQLException {
        int i = 1;
        if (obj == null) {
            throw new SQLException("argument for '" + fieldType.getFieldName() + "' is null");
        }
        if (obj instanceof ArgumentHolder) {
            stringBuilder.append('?');
            ArgumentHolder argumentHolder = (ArgumentHolder) obj;
            argumentHolder.setMetaInfo(this.columnName, fieldType);
            list.add(argumentHolder);
        } else if (obj instanceof ColumnArg) {
            ColumnArg columnArg = (ColumnArg) obj;
            r1 = columnArg.getTableName();
            if (r1 != null) {
                databaseType.appendEscapedEntityName(stringBuilder, r1);
                stringBuilder.append(CoreConstants.DOT);
            }
            databaseType.appendEscapedEntityName(stringBuilder, columnArg.getColumnName());
        } else if (fieldType.isArgumentHolderRequired()) {
            stringBuilder.append('?');
            ArgumentHolder selectArg = new SelectArg();
            selectArg.setMetaInfo(this.columnName, fieldType);
            selectArg.setValue(obj);
            list.add(selectArg);
        } else if (fieldType.isForeign() && fieldType.getType().isAssignableFrom(obj.getClass())) {
            FieldType foreignIdField = fieldType.getForeignIdField();
            appendArgOrValue(databaseType, foreignIdField, stringBuilder, list, foreignIdField.extractJavaFieldValue(obj));
            i = 0;
        } else if (fieldType.isEscapedValue()) {
            databaseType.appendEscapedWord(stringBuilder, fieldType.convertJavaFieldToSqlArgValue(obj).toString());
        } else if (fieldType.isForeign()) {
            r1 = fieldType.convertJavaFieldToSqlArgValue(obj).toString();
            if (r1.length() <= 0 || NUMBER_CHARACTERS.indexOf(r1.charAt(0)) >= 0) {
                stringBuilder.append(r1);
            } else {
                throw new SQLException("Foreign field " + fieldType + " does not seem to be producing a numerical value '" + r1 + "'. Maybe you are passing the wrong object to comparison: " + this);
            }
        } else {
            stringBuilder.append(fieldType.convertJavaFieldToSqlArgValue(obj));
        }
        if (i != 0) {
            stringBuilder.append(' ');
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.columnName).append(' ');
        appendOperation(stringBuilder);
        stringBuilder.append(' ');
        stringBuilder.append(this.value);
        return stringBuilder.toString();
    }
}
