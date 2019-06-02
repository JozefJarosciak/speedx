package com.j256.ormlite.stmt;

import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.query.OrderBy;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryBuilder<T, ID> extends StatementBuilder<T, ID> {
    private boolean distinct;
    private List<String> groupByList;
    private String groupByRaw;
    private String having;
    private final FieldType idField;
    private boolean isCountOfQuery;
    private boolean isInnerQuery;
    private List<QueryBuilder$JoinInfo> joinList;
    private Long limit;
    private Long offset;
    private ArgumentHolder[] orderByArgs;
    private List<OrderBy> orderByList;
    private String orderByRaw;
    private FieldType[] resultFieldTypes;
    private List<String> selectColumnList;
    private boolean selectIdColumn = true;
    private List<String> selectRawList;

    public QueryBuilder(DatabaseType databaseType, TableInfo<T, ID> tableInfo, Dao<T, ID> dao) {
        super(databaseType, tableInfo, dao, StatementBuilder$StatementType.SELECT);
        this.idField = tableInfo.getIdField();
    }

    void enableInnerQuery() {
        this.isInnerQuery = true;
    }

    int getSelectColumnCount() {
        if (this.isCountOfQuery) {
            return 1;
        }
        if (this.selectRawList != null && !this.selectRawList.isEmpty()) {
            return this.selectRawList.size();
        }
        if (this.selectColumnList == null) {
            return 0;
        }
        return this.selectColumnList.size();
    }

    List<String> getSelectColumns() {
        if (this.isCountOfQuery) {
            return Collections.singletonList("COUNT(*)");
        }
        if (this.selectRawList != null && !this.selectRawList.isEmpty()) {
            return this.selectRawList;
        }
        if (this.selectColumnList == null) {
            return Collections.emptyList();
        }
        return this.selectColumnList;
    }

    public PreparedQuery<T> prepare() throws SQLException {
        return super.prepareStatement(this.limit);
    }

    public QueryBuilder<T, ID> selectColumns(String... strArr) {
        if (this.selectColumnList == null) {
            this.selectColumnList = new ArrayList();
        }
        for (String addSelectColumnToList : strArr) {
            addSelectColumnToList(addSelectColumnToList);
        }
        return this;
    }

    public QueryBuilder<T, ID> selectColumns(Iterable<String> iterable) {
        if (this.selectColumnList == null) {
            this.selectColumnList = new ArrayList();
        }
        for (String addSelectColumnToList : iterable) {
            addSelectColumnToList(addSelectColumnToList);
        }
        return this;
    }

    public QueryBuilder<T, ID> selectRaw(String... strArr) {
        if (this.selectRawList == null) {
            this.selectRawList = new ArrayList();
        }
        for (Object add : strArr) {
            this.selectRawList.add(add);
        }
        return this;
    }

    public QueryBuilder<T, ID> groupBy(String str) {
        if (verifyColumnName(str).isForeignCollection()) {
            throw new IllegalArgumentException("Can't groupBy foreign colletion field: " + str);
        }
        if (this.groupByList == null) {
            this.groupByList = new ArrayList();
        }
        this.groupByList.add(str);
        this.selectIdColumn = false;
        return this;
    }

    public QueryBuilder<T, ID> groupByRaw(String str) {
        this.groupByRaw = str;
        return this;
    }

    public QueryBuilder<T, ID> orderBy(String str, boolean z) {
        if (verifyColumnName(str).isForeignCollection()) {
            throw new IllegalArgumentException("Can't orderBy foreign colletion field: " + str);
        }
        if (this.orderByList == null) {
            this.orderByList = new ArrayList();
        }
        this.orderByList.add(new OrderBy(str, z));
        return this;
    }

    public QueryBuilder<T, ID> orderByRaw(String str) {
        return orderByRaw(str, (ArgumentHolder[]) null);
    }

    public QueryBuilder<T, ID> orderByRaw(String str, ArgumentHolder... argumentHolderArr) {
        this.orderByRaw = str;
        this.orderByArgs = argumentHolderArr;
        return this;
    }

    public QueryBuilder<T, ID> distinct() {
        this.distinct = true;
        this.selectIdColumn = false;
        return this;
    }

    @Deprecated
    public QueryBuilder<T, ID> limit(int i) {
        return limit(Long.valueOf((long) i));
    }

    public QueryBuilder<T, ID> limit(Long l) {
        this.limit = l;
        return this;
    }

    @Deprecated
    public QueryBuilder<T, ID> offset(int i) throws SQLException {
        return offset(Long.valueOf((long) i));
    }

    public QueryBuilder<T, ID> offset(Long l) throws SQLException {
        if (this.databaseType.isOffsetSqlSupported()) {
            this.offset = l;
            return this;
        }
        throw new SQLException("Offset is not supported by this database");
    }

    public QueryBuilder<T, ID> setCountOf(boolean z) {
        this.isCountOfQuery = z;
        return this;
    }

    public QueryBuilder<T, ID> having(String str) {
        this.having = str;
        return this;
    }

    public QueryBuilder<T, ID> join(QueryBuilder<?, ?> queryBuilder) throws SQLException {
        addJoinInfo("INNER", queryBuilder, StatementBuilder$WhereOperation.AND);
        return this;
    }

    public QueryBuilder<T, ID> joinOr(QueryBuilder<?, ?> queryBuilder) throws SQLException {
        addJoinInfo("INNER", queryBuilder, StatementBuilder$WhereOperation.OR);
        return this;
    }

    public QueryBuilder<T, ID> leftJoin(QueryBuilder<?, ?> queryBuilder) throws SQLException {
        addJoinInfo("LEFT", queryBuilder, StatementBuilder$WhereOperation.AND);
        return this;
    }

    public QueryBuilder<T, ID> leftJoinOr(QueryBuilder<?, ?> queryBuilder) throws SQLException {
        addJoinInfo("LEFT", queryBuilder, StatementBuilder$WhereOperation.OR);
        return this;
    }

    public List<T> query() throws SQLException {
        return this.dao.query(prepare());
    }

    public GenericRawResults<String[]> queryRaw() throws SQLException {
        return this.dao.queryRaw(prepareStatementString(), new String[0]);
    }

    public T queryForFirst() throws SQLException {
        return this.dao.queryForFirst(prepare());
    }

    public String[] queryRawFirst() throws SQLException {
        return (String[]) this.dao.queryRaw(prepareStatementString(), new String[0]).getFirstResult();
    }

    public CloseableIterator<T> iterator() throws SQLException {
        return this.dao.iterator(prepare());
    }

    public long countOf() throws SQLException {
        setCountOf(true);
        return this.dao.countOf(prepare());
    }

    @Deprecated
    public void clear() {
        reset();
    }

    public void reset() {
        super.reset();
        this.distinct = false;
        this.selectIdColumn = true;
        this.selectColumnList = null;
        this.selectRawList = null;
        this.orderByList = null;
        this.orderByRaw = null;
        this.groupByList = null;
        this.groupByRaw = null;
        this.isInnerQuery = false;
        this.isCountOfQuery = false;
        this.having = null;
        this.limit = null;
        this.offset = null;
        if (this.joinList != null) {
            this.joinList.clear();
            this.joinList = null;
        }
        this.addTableName = false;
    }

    protected void appendStatementStart(StringBuilder stringBuilder, List<ArgumentHolder> list) {
        if (this.joinList == null) {
            setAddTableName(false);
        } else {
            setAddTableName(true);
        }
        stringBuilder.append("SELECT ");
        if (this.databaseType.isLimitAfterSelect()) {
            appendLimit(stringBuilder);
        }
        if (this.distinct) {
            stringBuilder.append("DISTINCT ");
        }
        if (this.isCountOfQuery) {
            this.type = StatementBuilder$StatementType.SELECT_LONG;
            stringBuilder.append("COUNT(*) ");
        } else if (this.selectRawList == null || this.selectRawList.isEmpty()) {
            this.type = StatementBuilder$StatementType.SELECT;
            appendColumns(stringBuilder);
        } else {
            this.type = StatementBuilder$StatementType.SELECT_RAW;
            appendSelectRaw(stringBuilder);
        }
        stringBuilder.append("FROM ");
        this.databaseType.appendEscapedEntityName(stringBuilder, this.tableName);
        stringBuilder.append(' ');
        if (this.joinList != null) {
            appendJoinSql(stringBuilder);
        }
    }

    protected FieldType[] getResultFieldTypes() {
        return this.resultFieldTypes;
    }

    protected boolean appendWhereStatement(StringBuilder stringBuilder, List<ArgumentHolder> list, StatementBuilder$WhereOperation statementBuilder$WhereOperation) throws SQLException {
        boolean z = statementBuilder$WhereOperation == StatementBuilder$WhereOperation.FIRST;
        if (this.where != null) {
            z = super.appendWhereStatement(stringBuilder, list, statementBuilder$WhereOperation);
        }
        if (this.joinList == null) {
            return z;
        }
        boolean z2 = z;
        for (QueryBuilder$JoinInfo queryBuilder$JoinInfo : this.joinList) {
            StatementBuilder$WhereOperation statementBuilder$WhereOperation2;
            if (z2) {
                statementBuilder$WhereOperation2 = StatementBuilder$WhereOperation.FIRST;
            } else {
                statementBuilder$WhereOperation2 = queryBuilder$JoinInfo.operation;
            }
            z2 = queryBuilder$JoinInfo.queryBuilder.appendWhereStatement(stringBuilder, list, statementBuilder$WhereOperation2);
        }
        return z2;
    }

    protected void appendStatementEnd(StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException {
        appendGroupBys(stringBuilder);
        appendHaving(stringBuilder);
        appendOrderBys(stringBuilder, list);
        if (!this.databaseType.isLimitAfterSelect()) {
            appendLimit(stringBuilder);
        }
        appendOffset(stringBuilder);
        setAddTableName(false);
    }

    protected boolean shouldPrependTableNameToColumns() {
        return this.joinList != null;
    }

    private void setAddTableName(boolean z) {
        this.addTableName = z;
        if (this.joinList != null) {
            for (QueryBuilder$JoinInfo queryBuilder$JoinInfo : this.joinList) {
                queryBuilder$JoinInfo.queryBuilder.setAddTableName(z);
            }
        }
    }

    private void addJoinInfo(String str, QueryBuilder<?, ?> queryBuilder, StatementBuilder$WhereOperation statementBuilder$WhereOperation) throws SQLException {
        QueryBuilder$JoinInfo queryBuilder$JoinInfo = new QueryBuilder$JoinInfo(this, str, queryBuilder, statementBuilder$WhereOperation);
        matchJoinedFields(queryBuilder$JoinInfo, queryBuilder);
        if (this.joinList == null) {
            this.joinList = new ArrayList();
        }
        this.joinList.add(queryBuilder$JoinInfo);
    }

    private void matchJoinedFields(QueryBuilder$JoinInfo queryBuilder$JoinInfo, QueryBuilder<?, ?> queryBuilder) throws SQLException {
        int i = 0;
        for (FieldType fieldType : this.tableInfo.getFieldTypes()) {
            FieldType foreignIdField = fieldType.getForeignIdField();
            if (fieldType.isForeign() && foreignIdField.equals(queryBuilder.tableInfo.getIdField())) {
                queryBuilder$JoinInfo.localField = fieldType;
                queryBuilder$JoinInfo.remoteField = foreignIdField;
                return;
            }
        }
        FieldType[] fieldTypes = queryBuilder.tableInfo.getFieldTypes();
        int length = fieldTypes.length;
        while (i < length) {
            FieldType fieldType2 = fieldTypes[i];
            if (fieldType2.isForeign() && fieldType2.getForeignIdField().equals(this.idField)) {
                queryBuilder$JoinInfo.localField = this.idField;
                queryBuilder$JoinInfo.remoteField = fieldType2;
                return;
            }
            i++;
        }
        throw new SQLException("Could not find a foreign " + this.tableInfo.getDataClass() + " field in " + queryBuilder.tableInfo.getDataClass() + " or vice versa");
    }

    private void addSelectColumnToList(String str) {
        verifyColumnName(str);
        this.selectColumnList.add(str);
    }

    private void appendJoinSql(StringBuilder stringBuilder) {
        for (QueryBuilder$JoinInfo queryBuilder$JoinInfo : this.joinList) {
            stringBuilder.append(queryBuilder$JoinInfo.type).append(" JOIN ");
            this.databaseType.appendEscapedEntityName(stringBuilder, queryBuilder$JoinInfo.queryBuilder.tableName);
            stringBuilder.append(" ON ");
            this.databaseType.appendEscapedEntityName(stringBuilder, this.tableName);
            stringBuilder.append(CoreConstants.DOT);
            this.databaseType.appendEscapedEntityName(stringBuilder, queryBuilder$JoinInfo.localField.getColumnName());
            stringBuilder.append(" = ");
            this.databaseType.appendEscapedEntityName(stringBuilder, queryBuilder$JoinInfo.queryBuilder.tableName);
            stringBuilder.append(CoreConstants.DOT);
            this.databaseType.appendEscapedEntityName(stringBuilder, queryBuilder$JoinInfo.remoteField.getColumnName());
            stringBuilder.append(' ');
            if (queryBuilder$JoinInfo.queryBuilder.joinList != null) {
                queryBuilder$JoinInfo.queryBuilder.appendJoinSql(stringBuilder);
            }
        }
    }

    private void appendSelectRaw(StringBuilder stringBuilder) {
        Object obj = 1;
        for (String str : this.selectRawList) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(", ");
            }
            stringBuilder.append(str);
        }
        stringBuilder.append(' ');
    }

    private void appendColumns(StringBuilder stringBuilder) {
        if (this.selectColumnList == null) {
            if (this.addTableName) {
                this.databaseType.appendEscapedEntityName(stringBuilder, this.tableName);
                stringBuilder.append(CoreConstants.DOT);
            }
            stringBuilder.append("* ");
            this.resultFieldTypes = this.tableInfo.getFieldTypes();
            return;
        }
        Object obj;
        if (this.isInnerQuery) {
            obj = 1;
        } else {
            obj = null;
        }
        List arrayList = new ArrayList(this.selectColumnList.size() + 1);
        Object obj2 = obj;
        Object obj3 = 1;
        for (String fieldTypeByColumnName : this.selectColumnList) {
            FieldType fieldTypeByColumnName2 = this.tableInfo.getFieldTypeByColumnName(fieldTypeByColumnName);
            if (fieldTypeByColumnName2.isForeignCollection()) {
                arrayList.add(fieldTypeByColumnName2);
            } else {
                if (obj3 != null) {
                    obj3 = null;
                } else {
                    stringBuilder.append(CoreConstants.COMMA_CHAR);
                }
                appendFieldColumnName(stringBuilder, fieldTypeByColumnName2, arrayList);
                if (fieldTypeByColumnName2 == this.idField) {
                    obj = 1;
                } else {
                    obj = obj2;
                }
                obj2 = obj;
            }
        }
        if (obj2 == null && this.selectIdColumn) {
            if (obj3 == null) {
                stringBuilder.append(CoreConstants.COMMA_CHAR);
            }
            appendFieldColumnName(stringBuilder, this.idField, arrayList);
        }
        stringBuilder.append(' ');
        this.resultFieldTypes = (FieldType[]) arrayList.toArray(new FieldType[arrayList.size()]);
    }

    private void appendFieldColumnName(StringBuilder stringBuilder, FieldType fieldType, List<FieldType> list) {
        appendColumnName(stringBuilder, fieldType.getColumnName());
        if (list != null) {
            list.add(fieldType);
        }
    }

    private void appendLimit(StringBuilder stringBuilder) {
        if (this.limit != null && this.databaseType.isLimitSqlSupported()) {
            this.databaseType.appendLimitValue(stringBuilder, this.limit.longValue(), this.offset);
        }
    }

    private void appendOffset(StringBuilder stringBuilder) throws SQLException {
        if (this.offset != null) {
            if (!this.databaseType.isOffsetLimitArgument()) {
                this.databaseType.appendOffsetValue(stringBuilder, this.offset.longValue());
            } else if (this.limit == null) {
                throw new SQLException("If the offset is specified, limit must also be specified with this database");
            }
        }
    }

    private void appendGroupBys(StringBuilder stringBuilder) {
        boolean z;
        if (hasGroupStuff()) {
            appendGroupBys(stringBuilder, true);
            z = false;
        } else {
            z = true;
        }
        if (this.joinList != null) {
            for (QueryBuilder$JoinInfo queryBuilder$JoinInfo : this.joinList) {
                if (queryBuilder$JoinInfo.queryBuilder != null && queryBuilder$JoinInfo.queryBuilder.hasGroupStuff()) {
                    queryBuilder$JoinInfo.queryBuilder.appendGroupBys(stringBuilder, z);
                }
            }
        }
    }

    private boolean hasGroupStuff() {
        return ((this.groupByList == null || this.groupByList.isEmpty()) && this.groupByRaw == null) ? false : true;
    }

    private void appendGroupBys(StringBuilder stringBuilder, boolean z) {
        if (z) {
            stringBuilder.append("GROUP BY ");
        }
        if (this.groupByRaw != null) {
            if (!z) {
                stringBuilder.append(CoreConstants.COMMA_CHAR);
            }
            stringBuilder.append(this.groupByRaw);
        } else {
            for (String str : this.groupByList) {
                if (z) {
                    z = false;
                } else {
                    stringBuilder.append(CoreConstants.COMMA_CHAR);
                }
                appendColumnName(stringBuilder, str);
            }
        }
        stringBuilder.append(' ');
    }

    private void appendOrderBys(StringBuilder stringBuilder, List<ArgumentHolder> list) {
        boolean z;
        if (hasOrderStuff()) {
            appendOrderBys(stringBuilder, true, list);
            z = false;
        } else {
            z = true;
        }
        if (this.joinList != null) {
            for (QueryBuilder$JoinInfo queryBuilder$JoinInfo : this.joinList) {
                if (queryBuilder$JoinInfo.queryBuilder != null && queryBuilder$JoinInfo.queryBuilder.hasOrderStuff()) {
                    queryBuilder$JoinInfo.queryBuilder.appendOrderBys(stringBuilder, z, list);
                }
            }
        }
    }

    private boolean hasOrderStuff() {
        return ((this.orderByList == null || this.orderByList.isEmpty()) && this.orderByRaw == null) ? false : true;
    }

    private void appendOrderBys(StringBuilder stringBuilder, boolean z, List<ArgumentHolder> list) {
        if (z) {
            stringBuilder.append("ORDER BY ");
        }
        if (this.orderByRaw != null) {
            if (!z) {
                stringBuilder.append(CoreConstants.COMMA_CHAR);
            }
            stringBuilder.append(this.orderByRaw);
            if (this.orderByArgs != null) {
                for (Object add : this.orderByArgs) {
                    list.add(add);
                }
            }
            z = false;
        }
        if (this.orderByList != null) {
            for (OrderBy orderBy : this.orderByList) {
                if (z) {
                    z = false;
                } else {
                    stringBuilder.append(CoreConstants.COMMA_CHAR);
                }
                appendColumnName(stringBuilder, orderBy.getColumnName());
                if (!orderBy.isAscending()) {
                    stringBuilder.append(" DESC");
                }
            }
        }
        stringBuilder.append(' ');
    }

    private void appendColumnName(StringBuilder stringBuilder, String str) {
        if (this.addTableName) {
            this.databaseType.appendEscapedEntityName(stringBuilder, this.tableName);
            stringBuilder.append(CoreConstants.DOT);
        }
        this.databaseType.appendEscapedEntityName(stringBuilder, str);
    }

    private void appendHaving(StringBuilder stringBuilder) {
        if (this.having != null) {
            stringBuilder.append("HAVING ").append(this.having).append(' ');
        }
    }
}
