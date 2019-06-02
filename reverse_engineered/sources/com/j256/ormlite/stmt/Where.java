package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.query.Between;
import com.j256.ormlite.stmt.query.Clause;
import com.j256.ormlite.stmt.query.Exists;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.stmt.query.InSubQuery;
import com.j256.ormlite.stmt.query.IsNotNull;
import com.j256.ormlite.stmt.query.IsNull;
import com.j256.ormlite.stmt.query.ManyClause;
import com.j256.ormlite.stmt.query.NeedsFutureClause;
import com.j256.ormlite.stmt.query.Not;
import com.j256.ormlite.stmt.query.Raw;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Where<T, ID> {
    private static final int CLAUSE_STACK_START_SIZE = 4;
    private Clause[] clauseStack = new Clause[4];
    private int clauseStackLevel;
    private final DatabaseType databaseType;
    private final String idColumnName;
    private final FieldType idFieldType;
    private NeedsFutureClause needsFuture = null;
    private final StatementBuilder<T, ID> statementBuilder;
    private final TableInfo<T, ID> tableInfo;

    Where(TableInfo<T, ID> tableInfo, StatementBuilder<T, ID> statementBuilder, DatabaseType databaseType) {
        this.tableInfo = tableInfo;
        this.statementBuilder = statementBuilder;
        this.idFieldType = tableInfo.getIdField();
        if (this.idFieldType == null) {
            this.idColumnName = null;
        } else {
            this.idColumnName = this.idFieldType.getColumnName();
        }
        this.databaseType = databaseType;
    }

    public Where<T, ID> and() {
        Object manyClause = new ManyClause(pop(ManyClause.AND_OPERATION), ManyClause.AND_OPERATION);
        push(manyClause);
        addNeedsFuture(manyClause);
        return this;
    }

    public Where<T, ID> and(Where<T, ID> where, Where<T, ID> where2, Where<T, ID>... whereArr) {
        Clause[] buildClauseArray = buildClauseArray(whereArr, ManyClause.AND_OPERATION);
        addClause(new ManyClause(pop(ManyClause.AND_OPERATION), pop(ManyClause.AND_OPERATION), buildClauseArray, ManyClause.AND_OPERATION));
        return this;
    }

    public Where<T, ID> and(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Must have at least one clause in and(numClauses)");
        }
        Clause[] clauseArr = new Clause[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            clauseArr[i2] = pop(ManyClause.AND_OPERATION);
        }
        addClause(new ManyClause(clauseArr, ManyClause.AND_OPERATION));
        return this;
    }

    public Where<T, ID> between(String str, Object obj, Object obj2) throws SQLException {
        addClause(new Between(str, findColumnFieldType(str), obj, obj2));
        return this;
    }

    public Where<T, ID> eq(String str, Object obj) throws SQLException {
        addClause(new SimpleComparison(str, findColumnFieldType(str), obj, SimpleComparison.EQUAL_TO_OPERATION));
        return this;
    }

    public Where<T, ID> ge(String str, Object obj) throws SQLException {
        addClause(new SimpleComparison(str, findColumnFieldType(str), obj, SimpleComparison.GREATER_THAN_EQUAL_TO_OPERATION));
        return this;
    }

    public Where<T, ID> gt(String str, Object obj) throws SQLException {
        addClause(new SimpleComparison(str, findColumnFieldType(str), obj, SimpleComparison.GREATER_THAN_OPERATION));
        return this;
    }

    public Where<T, ID> in(String str, Iterable<?> iterable) throws SQLException {
        addClause(new In(str, findColumnFieldType(str), iterable, true));
        return this;
    }

    public Where<T, ID> notIn(String str, Iterable<?> iterable) throws SQLException {
        addClause(new In(str, findColumnFieldType(str), iterable, false));
        return this;
    }

    public Where<T, ID> in(String str, Object... objArr) throws SQLException {
        return in(true, str, objArr);
    }

    public Where<T, ID> notIn(String str, Object... objArr) throws SQLException {
        return in(false, str, objArr);
    }

    public Where<T, ID> in(String str, QueryBuilder<?, ?> queryBuilder) throws SQLException {
        return in(true, str, (QueryBuilder) queryBuilder);
    }

    public Where<T, ID> notIn(String str, QueryBuilder<?, ?> queryBuilder) throws SQLException {
        return in(false, str, (QueryBuilder) queryBuilder);
    }

    public Where<T, ID> exists(QueryBuilder<?, ?> queryBuilder) {
        queryBuilder.enableInnerQuery();
        addClause(new Exists(new QueryBuilder$InternalQueryBuilderWrapper(queryBuilder)));
        return this;
    }

    public Where<T, ID> isNull(String str) throws SQLException {
        addClause(new IsNull(str, findColumnFieldType(str)));
        return this;
    }

    public Where<T, ID> isNotNull(String str) throws SQLException {
        addClause(new IsNotNull(str, findColumnFieldType(str)));
        return this;
    }

    public Where<T, ID> le(String str, Object obj) throws SQLException {
        addClause(new SimpleComparison(str, findColumnFieldType(str), obj, SimpleComparison.LESS_THAN_EQUAL_TO_OPERATION));
        return this;
    }

    public Where<T, ID> lt(String str, Object obj) throws SQLException {
        addClause(new SimpleComparison(str, findColumnFieldType(str), obj, SimpleComparison.LESS_THAN_OPERATION));
        return this;
    }

    public Where<T, ID> like(String str, Object obj) throws SQLException {
        addClause(new SimpleComparison(str, findColumnFieldType(str), obj, SimpleComparison.LIKE_OPERATION));
        return this;
    }

    public Where<T, ID> ne(String str, Object obj) throws SQLException {
        addClause(new SimpleComparison(str, findColumnFieldType(str), obj, SimpleComparison.NOT_EQUAL_TO_OPERATION));
        return this;
    }

    public Where<T, ID> not() {
        Object not = new Not();
        addClause(not);
        addNeedsFuture(not);
        return this;
    }

    public Where<T, ID> not(Where<T, ID> where) {
        addClause(new Not(pop("NOT")));
        return this;
    }

    public Where<T, ID> or() {
        Object manyClause = new ManyClause(pop(ManyClause.OR_OPERATION), ManyClause.OR_OPERATION);
        push(manyClause);
        addNeedsFuture(manyClause);
        return this;
    }

    public Where<T, ID> or(Where<T, ID> where, Where<T, ID> where2, Where<T, ID>... whereArr) {
        Clause[] buildClauseArray = buildClauseArray(whereArr, ManyClause.OR_OPERATION);
        addClause(new ManyClause(pop(ManyClause.OR_OPERATION), pop(ManyClause.OR_OPERATION), buildClauseArray, ManyClause.OR_OPERATION));
        return this;
    }

    public Where<T, ID> or(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Must have at least one clause in or(numClauses)");
        }
        Clause[] clauseArr = new Clause[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            clauseArr[i2] = pop(ManyClause.OR_OPERATION);
        }
        addClause(new ManyClause(clauseArr, ManyClause.OR_OPERATION));
        return this;
    }

    public Where<T, ID> idEq(ID id) throws SQLException {
        if (this.idColumnName == null) {
            throw new SQLException("Object has no id column specified");
        }
        addClause(new SimpleComparison(this.idColumnName, this.idFieldType, id, SimpleComparison.EQUAL_TO_OPERATION));
        return this;
    }

    public <OD> Where<T, ID> idEq(Dao<OD, ?> dao, OD od) throws SQLException {
        if (this.idColumnName == null) {
            throw new SQLException("Object has no id column specified");
        }
        addClause(new SimpleComparison(this.idColumnName, this.idFieldType, dao.extractId(od), SimpleComparison.EQUAL_TO_OPERATION));
        return this;
    }

    public Where<T, ID> raw(String str, ArgumentHolder... argumentHolderArr) {
        for (ArgumentHolder argumentHolder : argumentHolderArr) {
            String columnName = argumentHolder.getColumnName();
            if (columnName != null) {
                argumentHolder.setMetaInfo(findColumnFieldType(columnName));
            } else if (argumentHolder.getSqlType() == null) {
                throw new IllegalArgumentException("Either the column name or SqlType must be set on each argument");
            }
        }
        addClause(new Raw(str, argumentHolderArr));
        return this;
    }

    public Where<T, ID> rawComparison(String str, String str2, Object obj) throws SQLException {
        addClause(new SimpleComparison(str, findColumnFieldType(str), obj, str2));
        return this;
    }

    public PreparedQuery<T> prepare() throws SQLException {
        return this.statementBuilder.prepareStatement(null);
    }

    public List<T> query() throws SQLException {
        return checkQueryBuilderMethod("query()").query();
    }

    public GenericRawResults<String[]> queryRaw() throws SQLException {
        return checkQueryBuilderMethod("queryRaw()").queryRaw();
    }

    public T queryForFirst() throws SQLException {
        return checkQueryBuilderMethod("queryForFirst()").queryForFirst();
    }

    public String[] queryRawFirst() throws SQLException {
        return checkQueryBuilderMethod("queryRawFirst()").queryRawFirst();
    }

    public long countOf() throws SQLException {
        return checkQueryBuilderMethod("countOf()").countOf();
    }

    public CloseableIterator<T> iterator() throws SQLException {
        return checkQueryBuilderMethod("iterator()").iterator();
    }

    @Deprecated
    public Where<T, ID> clear() {
        return reset();
    }

    public Where<T, ID> reset() {
        for (int i = 0; i < this.clauseStackLevel; i++) {
            this.clauseStack[i] = null;
        }
        this.clauseStackLevel = 0;
        return this;
    }

    public String getStatement() throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        appendSql(null, stringBuilder, new ArrayList());
        return stringBuilder.toString();
    }

    void appendSql(String str, StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException {
        if (this.clauseStackLevel == 0) {
            throw new IllegalStateException("No where clauses defined.  Did you miss a where operation?");
        } else if (this.clauseStackLevel != 1) {
            throw new IllegalStateException("Both the \"left-hand\" and \"right-hand\" clauses have been defined.  Did you miss an AND or OR?");
        } else if (this.needsFuture != null) {
            throw new IllegalStateException("The SQL statement has not been finished since there are previous operations still waiting for clauses.");
        } else {
            peek().appendSql(this.databaseType, str, stringBuilder, list);
        }
    }

    public String toString() {
        if (this.clauseStackLevel == 0) {
            return "empty where clause";
        }
        return "where clause: " + peek();
    }

    private QueryBuilder<T, ID> checkQueryBuilderMethod(String str) throws SQLException {
        if (this.statementBuilder instanceof QueryBuilder) {
            return (QueryBuilder) this.statementBuilder;
        }
        throw new SQLException("Cannot call " + str + " on a statement of type " + this.statementBuilder.getType());
    }

    private Where<T, ID> in(boolean z, String str, Object... objArr) throws SQLException {
        if (objArr.length == 1) {
            if (objArr[0].getClass().isArray()) {
                throw new IllegalArgumentException("Object argument to " + (z ? "IN" : "notId") + " seems to be an array within an array");
            } else if (objArr[0] instanceof Where) {
                throw new IllegalArgumentException("Object argument to " + (z ? "IN" : "notId") + " seems to be a Where object, did you mean the QueryBuilder?");
            } else if (objArr[0] instanceof PreparedStmt) {
                throw new IllegalArgumentException("Object argument to " + (z ? "IN" : "notId") + " seems to be a prepared statement, did you mean the QueryBuilder?");
            }
        }
        addClause(new In(str, findColumnFieldType(str), objArr, z));
        return this;
    }

    private Where<T, ID> in(boolean z, String str, QueryBuilder<?, ?> queryBuilder) throws SQLException {
        if (queryBuilder.getSelectColumnCount() == 1) {
            queryBuilder.enableInnerQuery();
            addClause(new InSubQuery(str, findColumnFieldType(str), new QueryBuilder$InternalQueryBuilderWrapper(queryBuilder), z));
            return this;
        } else if (queryBuilder.getSelectColumnCount() == 0) {
            throw new SQLException("Inner query must have only 1 select column specified instead of *");
        } else {
            throw new SQLException("Inner query must have only 1 select column specified instead of " + queryBuilder.getSelectColumnCount() + ": " + Arrays.toString(queryBuilder.getSelectColumns().toArray(new String[0])));
        }
    }

    private Clause[] buildClauseArray(Where<T, ID>[] whereArr, String str) {
        if (whereArr.length == 0) {
            return null;
        }
        Clause[] clauseArr = new Clause[whereArr.length];
        for (int length = whereArr.length - 1; length >= 0; length--) {
            clauseArr[length] = pop(str);
        }
        return clauseArr;
    }

    private void addNeedsFuture(NeedsFutureClause needsFutureClause) {
        if (this.needsFuture != null) {
            throw new IllegalStateException(this.needsFuture + " is already waiting for a future clause, can't add: " + needsFutureClause);
        }
        this.needsFuture = needsFutureClause;
    }

    private void addClause(Clause clause) {
        if (this.needsFuture == null) {
            push(clause);
            return;
        }
        this.needsFuture.setMissingClause(clause);
        this.needsFuture = null;
    }

    private FieldType findColumnFieldType(String str) {
        return this.tableInfo.getFieldTypeByColumnName(str);
    }

    private void push(Clause clause) {
        if (this.clauseStackLevel == this.clauseStack.length) {
            Clause[] clauseArr = new Clause[(this.clauseStackLevel * 2)];
            for (int i = 0; i < this.clauseStackLevel; i++) {
                clauseArr[i] = this.clauseStack[i];
                this.clauseStack[i] = null;
            }
            this.clauseStack = clauseArr;
        }
        Clause[] clauseArr2 = this.clauseStack;
        int i2 = this.clauseStackLevel;
        this.clauseStackLevel = i2 + 1;
        clauseArr2[i2] = clause;
    }

    private Clause pop(String str) {
        if (this.clauseStackLevel == 0) {
            throw new IllegalStateException("Expecting there to be a clause already defined for '" + str + "' operation");
        }
        Clause[] clauseArr = this.clauseStack;
        int i = this.clauseStackLevel - 1;
        this.clauseStackLevel = i;
        Clause clause = clauseArr[i];
        this.clauseStack[this.clauseStackLevel] = null;
        return clause;
    }

    private Clause peek() {
        return this.clauseStack[this.clauseStackLevel - 1];
    }
}
