package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.List;

public class Between extends BaseComparison {
    private Object high;
    private Object low;

    public /* bridge */ /* synthetic */ void appendSql(DatabaseType databaseType, String str, StringBuilder stringBuilder, List list) throws SQLException {
        super.appendSql(databaseType, str, stringBuilder, list);
    }

    public /* bridge */ /* synthetic */ String getColumnName() {
        return super.getColumnName();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public Between(String str, FieldType fieldType, Object obj, Object obj2) throws SQLException {
        super(str, fieldType, null, true);
        this.low = obj;
        this.high = obj2;
    }

    public void appendOperation(StringBuilder stringBuilder) {
        stringBuilder.append("BETWEEN ");
    }

    public void appendValue(DatabaseType databaseType, StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException {
        if (this.low == null) {
            throw new IllegalArgumentException("BETWEEN low value for '" + this.columnName + "' is null");
        } else if (this.high == null) {
            throw new IllegalArgumentException("BETWEEN high value for '" + this.columnName + "' is null");
        } else {
            appendArgOrValue(databaseType, this.fieldType, stringBuilder, list, this.low);
            stringBuilder.append("AND ");
            appendArgOrValue(databaseType, this.fieldType, stringBuilder, list, this.high);
        }
    }
}
