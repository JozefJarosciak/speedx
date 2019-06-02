package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.List;

public class DeleteBuilder<T, ID> extends StatementBuilder<T, ID> {
    public DeleteBuilder(DatabaseType databaseType, TableInfo<T, ID> tableInfo, Dao<T, ID> dao) {
        super(databaseType, tableInfo, dao, StatementBuilder$StatementType.DELETE);
    }

    public PreparedDelete<T> prepare() throws SQLException {
        return super.prepareStatement(null);
    }

    public int delete() throws SQLException {
        return this.dao.delete(prepare());
    }

    @Deprecated
    public void clear() {
        reset();
    }

    public void reset() {
        super.reset();
    }

    protected void appendStatementStart(StringBuilder stringBuilder, List<ArgumentHolder> list) {
        stringBuilder.append("DELETE FROM ");
        this.databaseType.appendEscapedEntityName(stringBuilder, this.tableInfo.getTableName());
        stringBuilder.append(' ');
    }

    protected void appendStatementEnd(StringBuilder stringBuilder, List<ArgumentHolder> list) {
    }
}
