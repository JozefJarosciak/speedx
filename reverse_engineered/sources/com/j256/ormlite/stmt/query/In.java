package com.j256.ormlite.stmt.query;

import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class In extends BaseComparison {
    private final boolean in;
    private Iterable<?> objects;

    public /* bridge */ /* synthetic */ void appendSql(DatabaseType databaseType, String str, StringBuilder stringBuilder, List list) throws SQLException {
        super.appendSql(databaseType, str, stringBuilder, list);
    }

    public /* bridge */ /* synthetic */ String getColumnName() {
        return super.getColumnName();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public In(String str, FieldType fieldType, Iterable<?> iterable, boolean z) throws SQLException {
        super(str, fieldType, null, true);
        this.objects = iterable;
        this.in = z;
    }

    public In(String str, FieldType fieldType, Object[] objArr, boolean z) throws SQLException {
        super(str, fieldType, null, true);
        this.objects = Arrays.asList(objArr);
        this.in = z;
    }

    public void appendOperation(StringBuilder stringBuilder) {
        if (this.in) {
            stringBuilder.append("IN ");
        } else {
            stringBuilder.append("NOT IN ");
        }
    }

    public void appendValue(DatabaseType databaseType, StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException {
        stringBuilder.append(CoreConstants.LEFT_PARENTHESIS_CHAR);
        Object obj = 1;
        for (Object next : this.objects) {
            if (next == null) {
                throw new IllegalArgumentException("one of the IN values for '" + this.columnName + "' is null");
            }
            Object obj2;
            if (obj != null) {
                obj2 = null;
            } else {
                stringBuilder.append(CoreConstants.COMMA_CHAR);
                obj2 = obj;
            }
            super.appendArgOrValue(databaseType, this.fieldType, stringBuilder, list, next);
            obj = obj2;
        }
        stringBuilder.append(") ");
    }
}
