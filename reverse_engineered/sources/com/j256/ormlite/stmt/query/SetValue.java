package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.NullArgHolder;
import java.sql.SQLException;
import java.util.List;

public class SetValue extends BaseComparison {
    private static final ArgumentHolder nullValue = new NullArgHolder();

    public /* bridge */ /* synthetic */ void appendSql(DatabaseType databaseType, String str, StringBuilder stringBuilder, List list) throws SQLException {
        super.appendSql(databaseType, str, stringBuilder, list);
    }

    public /* bridge */ /* synthetic */ void appendValue(DatabaseType databaseType, StringBuilder stringBuilder, List list) throws SQLException {
        super.appendValue(databaseType, stringBuilder, list);
    }

    public /* bridge */ /* synthetic */ String getColumnName() {
        return super.getColumnName();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public SetValue(String str, FieldType fieldType, Object obj) throws SQLException {
        if (obj == null) {
            obj = nullValue;
        }
        super(str, fieldType, obj, false);
    }

    public void appendOperation(StringBuilder stringBuilder) {
        stringBuilder.append("= ");
    }
}
