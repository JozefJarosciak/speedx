package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedUpdateId<T, ID> extends BaseMappedStatement<T, ID> {
    private MappedUpdateId(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr) {
        super(tableInfo, str, fieldTypeArr);
    }

    public int execute(DatabaseConnection databaseConnection, T t, ID id, ObjectCache objectCache) throws SQLException {
        try {
            Object obj = new Object[]{convertIdToFieldObject(id), extractIdToFieldObject(t)};
            int update = databaseConnection.update(this.statement, obj, this.argFieldTypes);
            if (update > 0) {
                if (objectCache != null) {
                    T updateId = objectCache.updateId(this.clazz, this.idField.extractJavaFieldValue(t), id);
                    if (!(updateId == null || updateId == t)) {
                        this.idField.assignField(updateId, id, false, objectCache);
                    }
                }
                this.idField.assignField(t, id, false, objectCache);
            }
            logger.debug("updating-id with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(obj.length), Integer.valueOf(update));
            if (obj.length > 0) {
                logger.trace("updating-id arguments: {}", obj);
            }
            return update;
        } catch (Throwable e) {
            throw SqlExceptionUtil.create("Unable to run update-id stmt on object " + t + ": " + this.statement, e);
        }
    }

    public static <T, ID> MappedUpdateId<T, ID> build(DatabaseType databaseType, TableInfo<T, ID> tableInfo) throws SQLException {
        FieldType idField = tableInfo.getIdField();
        if (idField == null) {
            throw new SQLException("Cannot update-id in " + tableInfo.getDataClass() + " because it doesn't have an id field");
        }
        StringBuilder stringBuilder = new StringBuilder(64);
        BaseMappedStatement.appendTableName(databaseType, stringBuilder, "UPDATE ", tableInfo.getTableName());
        stringBuilder.append("SET ");
        BaseMappedStatement.appendFieldColumnName(databaseType, stringBuilder, idField, null);
        stringBuilder.append("= ? ");
        BaseMappedStatement.appendWhereFieldEq(databaseType, idField, stringBuilder, null);
        return new MappedUpdateId(tableInfo, stringBuilder.toString(), new FieldType[]{idField, idField});
    }

    private Object extractIdToFieldObject(T t) throws SQLException {
        return this.idField.extractJavaFieldToSqlArgValue(t);
    }
}
