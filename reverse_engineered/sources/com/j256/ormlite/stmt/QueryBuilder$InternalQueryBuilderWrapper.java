package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;
import java.sql.SQLException;
import java.util.List;

public class QueryBuilder$InternalQueryBuilderWrapper {
    private final QueryBuilder<?, ?> queryBuilder;

    QueryBuilder$InternalQueryBuilderWrapper(QueryBuilder<?, ?> queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public void appendStatementString(StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException {
        this.queryBuilder.appendStatementString(stringBuilder, list);
    }

    public FieldType[] getResultFieldTypes() {
        return this.queryBuilder.getResultFieldTypes();
    }
}
