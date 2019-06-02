package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedDelete<T, ID> extends BaseMappedStatement<T, ID> {
    private MappedDelete(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr) {
        super(tableInfo, str, fieldTypeArr);
    }

    public static <T, ID> MappedDelete<T, ID> build(DatabaseType databaseType, TableInfo<T, ID> tableInfo) throws SQLException {
        FieldType idField = tableInfo.getIdField();
        if (idField == null) {
            throw new SQLException("Cannot delete from " + tableInfo.getDataClass() + " because it doesn't have an id field");
        }
        StringBuilder stringBuilder = new StringBuilder(64);
        BaseMappedStatement.appendTableName(databaseType, stringBuilder, "DELETE FROM ", tableInfo.getTableName());
        BaseMappedStatement.appendWhereFieldEq(databaseType, idField, stringBuilder, null);
        return new MappedDelete(tableInfo, stringBuilder.toString(), new FieldType[]{idField});
    }

    public int delete(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        try {
            Object fieldObjects = getFieldObjects(t);
            int delete = databaseConnection.delete(this.statement, fieldObjects, this.argFieldTypes);
            logger.debug("delete data with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(fieldObjects.length), Integer.valueOf(delete));
            if (fieldObjects.length > 0) {
                logger.trace("delete arguments: {}", fieldObjects);
            }
            if (delete > 0 && objectCache != null) {
                objectCache.remove(this.clazz, this.idField.extractJavaFieldToSqlArgValue(t));
            }
            return delete;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Unable to run delete stmt on object " + t + ": " + this.statement, e);
        }
    }

    public int deleteById(DatabaseConnection databaseConnection, ID id, ObjectCache objectCache) throws SQLException {
        try {
            Object obj = new Object[]{convertIdToFieldObject(id)};
            int delete = databaseConnection.delete(this.statement, obj, this.argFieldTypes);
            logger.debug("delete data with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(obj.length), Integer.valueOf(delete));
            if (obj.length > 0) {
                logger.trace("delete arguments: {}", obj);
            }
            if (delete > 0 && objectCache != null) {
                objectCache.remove(this.clazz, id);
            }
            return delete;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Unable to run deleteById stmt on id " + id + ": " + this.statement, e);
        }
    }
}
