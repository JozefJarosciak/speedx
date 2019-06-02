package com.j256.ormlite.db;

import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.field.BaseFieldConverter;
import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldConverter;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDatabaseType implements DatabaseType {
    protected static String DEFAULT_SEQUENCE_SUFFIX = "_id_seq";
    protected Driver driver;

    /* renamed from: com.j256.ormlite.db.BaseDatabaseType$1 */
    static /* synthetic */ class C41081 {
        static final /* synthetic */ int[] $SwitchMap$com$j256$ormlite$field$SqlType = new int[SqlType.values().length];

        static {
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.LONG_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BOOLEAN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DATE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.CHAR.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE_ARRAY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SHORT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.INTEGER.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.LONG.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.FLOAT.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DOUBLE.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SERIALIZABLE.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BIG_DECIMAL.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.UNKNOWN.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    protected static class BooleanNumberFieldConverter extends BaseFieldConverter {
        protected BooleanNumberFieldConverter() {
        }

        public SqlType getSqlType() {
            return SqlType.BOOLEAN;
        }

        public Object parseDefaultString(FieldType fieldType, String str) {
            return Boolean.parseBoolean(str) ? Byte.valueOf((byte) 1) : Byte.valueOf((byte) 0);
        }

        public Object javaToSqlArg(FieldType fieldType, Object obj) {
            return ((Boolean) obj).booleanValue() ? Byte.valueOf((byte) 1) : Byte.valueOf((byte) 0);
        }

        public Object resultToSqlArg(FieldType fieldType, DatabaseResults databaseResults, int i) throws SQLException {
            return Byte.valueOf(databaseResults.getByte(i));
        }

        public Object sqlArgToJava(FieldType fieldType, Object obj, int i) {
            return ((Byte) obj).byteValue() == (byte) 1 ? Boolean.valueOf(true) : Boolean.valueOf(false);
        }

        public Object resultStringToJava(FieldType fieldType, String str, int i) {
            return sqlArgToJava(fieldType, Byte.valueOf(Byte.parseByte(str)), i);
        }
    }

    protected abstract String getDriverClassName();

    public void loadDriver() throws SQLException {
        String driverClassName = getDriverClassName();
        if (driverClassName != null) {
            try {
                Class.forName(driverClassName);
            } catch (Throwable e) {
                throw SqlExceptionUtil.create("Driver class was not found for " + getDatabaseName() + " database.  Missing jar with class " + driverClassName + ".", e);
            }
        }
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void appendColumnArg(String str, StringBuilder stringBuilder, FieldType fieldType, List<String> list, List<String> list2, List<String> list3, List<String> list4) throws SQLException {
        appendEscapedEntityName(stringBuilder, fieldType.getColumnName());
        stringBuilder.append(' ');
        DataPersister dataPersister = fieldType.getDataPersister();
        int width = fieldType.getWidth();
        if (width == 0) {
            width = dataPersister.getDefaultWidth();
        }
        switch (C41081.$SwitchMap$com$j256$ormlite$field$SqlType[dataPersister.getSqlType().ordinal()]) {
            case 1:
                appendStringType(stringBuilder, fieldType, width);
                break;
            case 2:
                appendLongStringType(stringBuilder, fieldType, width);
                break;
            case 3:
                appendBooleanType(stringBuilder, fieldType, width);
                break;
            case 4:
                appendDateType(stringBuilder, fieldType, width);
                break;
            case 5:
                appendCharType(stringBuilder, fieldType, width);
                break;
            case 6:
                appendByteType(stringBuilder, fieldType, width);
                break;
            case 7:
                appendByteArrayType(stringBuilder, fieldType, width);
                break;
            case 8:
                appendShortType(stringBuilder, fieldType, width);
                break;
            case 9:
                appendIntegerType(stringBuilder, fieldType, width);
                break;
            case 10:
                appendLongType(stringBuilder, fieldType, width);
                break;
            case 11:
                appendFloatType(stringBuilder, fieldType, width);
                break;
            case 12:
                appendDoubleType(stringBuilder, fieldType, width);
                break;
            case 13:
                appendSerializableType(stringBuilder, fieldType, width);
                break;
            case 14:
                appendBigDecimalNumericType(stringBuilder, fieldType, width);
                break;
            default:
                throw new IllegalArgumentException("Unknown SQL-type " + dataPersister.getSqlType());
        }
        stringBuilder.append(' ');
        if (fieldType.isGeneratedIdSequence() && !fieldType.isSelfGeneratedId()) {
            configureGeneratedIdSequence(stringBuilder, fieldType, list2, list, list4);
        } else if (fieldType.isGeneratedId() && !fieldType.isSelfGeneratedId()) {
            configureGeneratedId(str, stringBuilder, fieldType, list2, list3, list, list4);
        } else if (fieldType.isId()) {
            configureId(stringBuilder, fieldType, list2, list, list4);
        }
        if (!fieldType.isGeneratedId()) {
            Object defaultValue = fieldType.getDefaultValue();
            if (defaultValue != null) {
                stringBuilder.append("DEFAULT ");
                appendDefaultValue(stringBuilder, fieldType, defaultValue);
                stringBuilder.append(' ');
            }
            if (fieldType.isCanBeNull()) {
                appendCanBeNull(stringBuilder, fieldType);
            } else {
                stringBuilder.append("NOT NULL ");
            }
            if (fieldType.isUnique()) {
                addSingleUnique(stringBuilder, fieldType, list, list3);
            }
        }
    }

    protected void appendStringType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        if (isVarcharFieldWidthSupported()) {
            stringBuilder.append("VARCHAR(").append(i).append(")");
        } else {
            stringBuilder.append("VARCHAR");
        }
    }

    protected void appendLongStringType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("TEXT");
    }

    protected void appendDateType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("TIMESTAMP");
    }

    protected void appendBooleanType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("BOOLEAN");
    }

    protected void appendCharType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("CHAR");
    }

    protected void appendByteType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("TINYINT");
    }

    protected void appendShortType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("SMALLINT");
    }

    private void appendIntegerType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("INTEGER");
    }

    protected void appendLongType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("BIGINT");
    }

    private void appendFloatType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("FLOAT");
    }

    private void appendDoubleType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("DOUBLE PRECISION");
    }

    protected void appendByteArrayType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("BLOB");
    }

    protected void appendSerializableType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("BLOB");
    }

    protected void appendBigDecimalNumericType(StringBuilder stringBuilder, FieldType fieldType, int i) {
        stringBuilder.append("NUMERIC");
    }

    private void appendDefaultValue(StringBuilder stringBuilder, FieldType fieldType, Object obj) {
        if (fieldType.isEscapedDefaultValue()) {
            appendEscapedWord(stringBuilder, obj.toString());
        } else {
            stringBuilder.append(obj);
        }
    }

    protected void configureGeneratedIdSequence(StringBuilder stringBuilder, FieldType fieldType, List<String> list, List<String> list2, List<String> list3) throws SQLException {
        throw new SQLException("GeneratedIdSequence is not supported by database " + getDatabaseName() + " for field " + fieldType);
    }

    protected void configureGeneratedId(String str, StringBuilder stringBuilder, FieldType fieldType, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        throw new IllegalStateException("GeneratedId is not supported by database " + getDatabaseName() + " for field " + fieldType);
    }

    protected void configureId(StringBuilder stringBuilder, FieldType fieldType, List<String> list, List<String> list2, List<String> list3) {
    }

    public void addPrimaryKeySql(FieldType[] fieldTypeArr, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        StringBuilder stringBuilder = null;
        for (FieldType fieldType : fieldTypeArr) {
            if ((!fieldType.isGeneratedId() || generatedIdSqlAtEnd() || fieldType.isSelfGeneratedId()) && fieldType.isId()) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder(48);
                    stringBuilder.append("PRIMARY KEY (");
                } else {
                    stringBuilder.append(CoreConstants.COMMA_CHAR);
                }
                appendEscapedEntityName(stringBuilder, fieldType.getColumnName());
            }
        }
        if (stringBuilder != null) {
            stringBuilder.append(") ");
            list.add(stringBuilder.toString());
        }
    }

    protected boolean generatedIdSqlAtEnd() {
        return true;
    }

    public void addUniqueComboSql(FieldType[] fieldTypeArr, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        StringBuilder stringBuilder = null;
        for (FieldType fieldType : fieldTypeArr) {
            if (fieldType.isUniqueCombo()) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder(48);
                    stringBuilder.append("UNIQUE (");
                } else {
                    stringBuilder.append(CoreConstants.COMMA_CHAR);
                }
                appendEscapedEntityName(stringBuilder, fieldType.getColumnName());
            }
        }
        if (stringBuilder != null) {
            stringBuilder.append(") ");
            list.add(stringBuilder.toString());
        }
    }

    public void dropColumnArg(FieldType fieldType, List<String> list, List<String> list2) {
    }

    public void appendEscapedWord(StringBuilder stringBuilder, String str) {
        stringBuilder.append(CoreConstants.SINGLE_QUOTE_CHAR).append(str).append(CoreConstants.SINGLE_QUOTE_CHAR);
    }

    public void appendEscapedEntityName(StringBuilder stringBuilder, String str) {
        stringBuilder.append('`').append(str).append('`');
    }

    public String generateIdSequenceName(String str, FieldType fieldType) {
        String str2 = str + DEFAULT_SEQUENCE_SUFFIX;
        if (isEntityNamesMustBeUpCase()) {
            return str2.toUpperCase();
        }
        return str2;
    }

    public String getCommentLinePrefix() {
        return "-- ";
    }

    public FieldConverter getFieldConverter(DataPersister dataPersister) {
        return dataPersister;
    }

    public boolean isIdSequenceNeeded() {
        return false;
    }

    public boolean isVarcharFieldWidthSupported() {
        return true;
    }

    public boolean isLimitSqlSupported() {
        return true;
    }

    public boolean isOffsetSqlSupported() {
        return true;
    }

    public boolean isOffsetLimitArgument() {
        return false;
    }

    public boolean isLimitAfterSelect() {
        return false;
    }

    public void appendLimitValue(StringBuilder stringBuilder, long j, Long l) {
        stringBuilder.append("LIMIT ").append(j).append(' ');
    }

    public void appendOffsetValue(StringBuilder stringBuilder, long j) {
        stringBuilder.append("OFFSET ").append(j).append(' ');
    }

    public void appendSelectNextValFromSequence(StringBuilder stringBuilder, String str) {
    }

    public void appendCreateTableSuffix(StringBuilder stringBuilder) {
    }

    public boolean isCreateTableReturnsZero() {
        return true;
    }

    public boolean isCreateTableReturnsNegative() {
        return false;
    }

    public boolean isEntityNamesMustBeUpCase() {
        return false;
    }

    public boolean isNestedSavePointsSupported() {
        return true;
    }

    public String getPingStatement() {
        return "SELECT 1";
    }

    public boolean isBatchUseTransaction() {
        return false;
    }

    public boolean isTruncateSupported() {
        return false;
    }

    public boolean isCreateIfNotExistsSupported() {
        return false;
    }

    public boolean isCreateIndexIfNotExistsSupported() {
        return isCreateIfNotExistsSupported();
    }

    public boolean isSelectSequenceBeforeInsert() {
        return false;
    }

    public boolean isAllowGeneratedIdInsertSupported() {
        return true;
    }

    public <T> DatabaseTableConfig<T> extractDatabaseTableConfig(ConnectionSource connectionSource, Class<T> cls) throws SQLException {
        return null;
    }

    public void appendInsertNoColumns(StringBuilder stringBuilder) {
        stringBuilder.append("() VALUES ()");
    }

    private void appendCanBeNull(StringBuilder stringBuilder, FieldType fieldType) {
    }

    private void addSingleUnique(StringBuilder stringBuilder, FieldType fieldType, List<String> list, List<String> list2) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(" UNIQUE (");
        appendEscapedEntityName(stringBuilder2, fieldType.getColumnName());
        stringBuilder2.append(")");
        list.add(stringBuilder2.toString());
    }
}
