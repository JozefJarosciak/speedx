package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class StatementBuilder<T, ID> {
    private static Logger logger = LoggerFactory.getLogger(StatementBuilder.class);
    protected boolean addTableName;
    protected final Dao<T, ID> dao;
    protected final DatabaseType databaseType;
    protected final TableInfo<T, ID> tableInfo;
    protected final String tableName;
    protected StatementBuilder$StatementType type;
    protected Where<T, ID> where = null;

    protected abstract void appendStatementEnd(StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException;

    protected abstract void appendStatementStart(StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException;

    public StatementBuilder(DatabaseType databaseType, TableInfo<T, ID> tableInfo, Dao<T, ID> dao, StatementBuilder$StatementType statementBuilder$StatementType) {
        this.databaseType = databaseType;
        this.tableInfo = tableInfo;
        this.tableName = tableInfo.getTableName();
        this.dao = dao;
        this.type = statementBuilder$StatementType;
        if (!statementBuilder$StatementType.isOkForStatementBuilder()) {
            throw new IllegalStateException("Building a statement from a " + statementBuilder$StatementType + " statement is not allowed");
        }
    }

    public Where<T, ID> where() {
        this.where = new Where(this.tableInfo, this, this.databaseType);
        return this.where;
    }

    public void setWhere(Where<T, ID> where) {
        this.where = where;
    }

    protected MappedPreparedStmt<T, ID> prepareStatement(Long l) throws SQLException {
        List arrayList = new ArrayList();
        String buildStatementString = buildStatementString(arrayList);
        ArgumentHolder[] argumentHolderArr = (ArgumentHolder[]) arrayList.toArray(new ArgumentHolder[arrayList.size()]);
        FieldType[] resultFieldTypes = getResultFieldTypes();
        FieldType[] fieldTypeArr = new FieldType[arrayList.size()];
        for (int i = 0; i < argumentHolderArr.length; i++) {
            fieldTypeArr[i] = argumentHolderArr[i].getFieldType();
        }
        if (this.type.isOkForStatementBuilder()) {
            return new MappedPreparedStmt(this.tableInfo, buildStatementString, fieldTypeArr, resultFieldTypes, argumentHolderArr, this.databaseType.isLimitSqlSupported() ? null : l, this.type);
        }
        throw new IllegalStateException("Building a statement from a " + this.type + " statement is not allowed");
    }

    public String prepareStatementString() throws SQLException {
        return buildStatementString(new ArrayList());
    }

    public StatementBuilder$StatementInfo prepareStatementInfo() throws SQLException {
        List arrayList = new ArrayList();
        return new StatementBuilder$StatementInfo(buildStatementString(arrayList), arrayList, null);
    }

    @Deprecated
    public void clear() {
        reset();
    }

    public void reset() {
        this.where = null;
    }

    protected String buildStatementString(List<ArgumentHolder> list) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder(128);
        appendStatementString(stringBuilder, list);
        String stringBuilder2 = stringBuilder.toString();
        logger.debug("built statement {}", stringBuilder2);
        return stringBuilder2;
    }

    protected void appendStatementString(StringBuilder stringBuilder, List<ArgumentHolder> list) throws SQLException {
        appendStatementStart(stringBuilder, list);
        appendWhereStatement(stringBuilder, list, StatementBuilder$WhereOperation.FIRST);
        appendStatementEnd(stringBuilder, list);
    }

    protected boolean appendWhereStatement(StringBuilder stringBuilder, List<ArgumentHolder> list, StatementBuilder$WhereOperation statementBuilder$WhereOperation) throws SQLException {
        if (this.where != null) {
            statementBuilder$WhereOperation.appendBefore(stringBuilder);
            this.where.appendSql(this.addTableName ? this.tableName : null, stringBuilder, list);
            statementBuilder$WhereOperation.appendAfter(stringBuilder);
            return false;
        } else if (statementBuilder$WhereOperation == StatementBuilder$WhereOperation.FIRST) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean shouldPrependTableNameToColumns() {
        return false;
    }

    protected FieldType[] getResultFieldTypes() {
        return null;
    }

    protected FieldType verifyColumnName(String str) {
        return this.tableInfo.getFieldTypeByColumnName(str);
    }

    StatementBuilder$StatementType getType() {
        return this.type;
    }
}
