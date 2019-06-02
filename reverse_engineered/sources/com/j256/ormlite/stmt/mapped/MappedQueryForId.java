package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedQueryForId<T, ID> extends BaseMappedQuery<T, ID> {
    private final String label;

    protected MappedQueryForId(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr, FieldType[] fieldTypeArr2, String str2) {
        super(tableInfo, str, fieldTypeArr, fieldTypeArr2);
        this.label = str2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T execute(com.j256.ormlite.support.DatabaseConnection r8, ID r9, com.j256.ormlite.dao.ObjectCache r10) throws java.sql.SQLException {
        /*
        r7 = this;
        if (r10 == 0) goto L_0x000b;
    L_0x0002:
        r0 = r7.clazz;
        r0 = r10.get(r0, r9);
        if (r0 == 0) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        r0 = 1;
        r2 = new java.lang.Object[r0];
        r0 = 0;
        r1 = r7.convertIdToFieldObject(r9);
        r2[r0] = r1;
        r1 = r7.statement;
        r3 = r7.argFieldTypes;
        r0 = r8;
        r4 = r7;
        r5 = r10;
        r0 = r0.queryForOne(r1, r2, r3, r4, r5);
        if (r0 != 0) goto L_0x0036;
    L_0x0022:
        r1 = logger;
        r3 = "{} using '{}' and {} args, got no results";
        r4 = r7.label;
        r5 = r7.statement;
        r6 = r2.length;
        r6 = java.lang.Integer.valueOf(r6);
        r1.debug(r3, r4, r5, r6);
    L_0x0032:
        r7.logArgs(r2);
        goto L_0x000a;
    L_0x0036:
        r1 = com.j256.ormlite.support.DatabaseConnection.MORE_THAN_ONE;
        if (r0 != r1) goto L_0x006e;
    L_0x003a:
        r0 = logger;
        r1 = "{} using '{}' and {} args, got >1 results";
        r3 = r7.label;
        r4 = r7.statement;
        r5 = r2.length;
        r5 = java.lang.Integer.valueOf(r5);
        r0.error(r1, r3, r4, r5);
        r7.logArgs(r2);
        r0 = new java.sql.SQLException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r7.label;
        r1 = r1.append(r2);
        r2 = " got more than 1 result: ";
        r1 = r1.append(r2);
        r2 = r7.statement;
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x006e:
        r1 = logger;
        r3 = "{} using '{}' and {} args, got 1 result";
        r4 = r7.label;
        r5 = r7.statement;
        r6 = r2.length;
        r6 = java.lang.Integer.valueOf(r6);
        r1.debug(r3, r4, r5, r6);
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.mapped.MappedQueryForId.execute(com.j256.ormlite.support.DatabaseConnection, java.lang.Object, com.j256.ormlite.dao.ObjectCache):T");
    }

    public static <T, ID> MappedQueryForId<T, ID> build(DatabaseType databaseType, TableInfo<T, ID> tableInfo, FieldType fieldType) throws SQLException {
        if (fieldType == null) {
            fieldType = tableInfo.getIdField();
            if (fieldType == null) {
                throw new SQLException("Cannot query-for-id with " + tableInfo.getDataClass() + " because it doesn't have an id field");
            }
        }
        return new MappedQueryForId(tableInfo, buildStatement(databaseType, tableInfo, fieldType), new FieldType[]{fieldType}, tableInfo.getFieldTypes(), "query-for-id");
    }

    protected static <T, ID> String buildStatement(DatabaseType databaseType, TableInfo<T, ID> tableInfo, FieldType fieldType) {
        StringBuilder stringBuilder = new StringBuilder(64);
        BaseMappedStatement.appendTableName(databaseType, stringBuilder, "SELECT * FROM ", tableInfo.getTableName());
        BaseMappedStatement.appendWhereFieldEq(databaseType, fieldType, stringBuilder, null);
        return stringBuilder.toString();
    }

    private void logArgs(Object[] objArr) {
        if (objArr.length > 0) {
            logger.trace("{} arguments: {}", this.label, (Object) objArr);
        }
    }
}
