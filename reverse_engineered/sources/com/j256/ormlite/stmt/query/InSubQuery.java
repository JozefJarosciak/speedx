package com.j256.ormlite.stmt.query;

import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.QueryBuilder$InternalQueryBuilderWrapper;
import java.sql.SQLException;
import java.util.List;

public class InSubQuery extends BaseComparison {
    private final boolean in;
    private final QueryBuilder$InternalQueryBuilderWrapper subQueryBuilder;

    public /* bridge */ /* synthetic */ void appendSql(DatabaseType databaseType, String str, StringBuilder stringBuilder, List list) throws SQLException {
        super.appendSql(databaseType, str, stringBuilder, list);
    }

    public /* bridge */ /* synthetic */ String getColumnName() {
        return super.getColumnName();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public InSubQuery(String str, FieldType fieldType, QueryBuilder$InternalQueryBuilderWrapper queryBuilder$InternalQueryBuilderWrapper, boolean z) throws SQLException {
        super(str, fieldType, null, true);
        this.subQueryBuilder = queryBuilder$InternalQueryBuilderWrapper;
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
        this.subQueryBuilder.appendStatementString(stringBuilder, list);
        FieldType[] resultFieldTypes = this.subQueryBuilder.getResultFieldTypes();
        if (resultFieldTypes != null) {
            if (resultFieldTypes.length != 1) {
                throw new SQLException("There must be only 1 result column in sub-query but we found " + resultFieldTypes.length);
            } else if (this.fieldType.getSqlType() != resultFieldTypes[0].getSqlType()) {
                throw new SQLException("Outer column " + this.fieldType + " is not the same type as inner column " + resultFieldTypes[0]);
            }
        }
        stringBuilder.append(") ");
    }
}
