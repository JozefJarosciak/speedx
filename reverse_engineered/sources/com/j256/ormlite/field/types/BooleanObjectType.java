package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;

public class BooleanObjectType extends BaseDataType {
    private static final BooleanObjectType singleTon = new BooleanObjectType();

    public static BooleanObjectType getSingleton() {
        return singleTon;
    }

    private BooleanObjectType() {
        super(SqlType.BOOLEAN, new Class[]{Boolean.class});
    }

    protected BooleanObjectType(SqlType sqlType, Class<?>[] clsArr) {
        super(sqlType, clsArr);
    }

    public Object parseDefaultString(FieldType fieldType, String str) {
        return Boolean.valueOf(Boolean.parseBoolean(str));
    }

    public Object resultToSqlArg(FieldType fieldType, DatabaseResults databaseResults, int i) throws SQLException {
        return Boolean.valueOf(databaseResults.getBoolean(i));
    }

    public boolean isEscapedValue() {
        return false;
    }

    public boolean isAppropriateId() {
        return false;
    }
}
