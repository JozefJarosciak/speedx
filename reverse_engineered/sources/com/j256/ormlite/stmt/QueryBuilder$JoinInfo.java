package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;

class QueryBuilder$JoinInfo {
    FieldType localField;
    StatementBuilder$WhereOperation operation;
    final QueryBuilder<?, ?> queryBuilder;
    FieldType remoteField;
    final /* synthetic */ QueryBuilder this$0;
    final String type;

    public QueryBuilder$JoinInfo(QueryBuilder queryBuilder, String str, QueryBuilder<?, ?> queryBuilder2, StatementBuilder$WhereOperation statementBuilder$WhereOperation) {
        this.this$0 = queryBuilder;
        this.type = str;
        this.queryBuilder = queryBuilder2;
        this.operation = statementBuilder$WhereOperation;
    }
}
