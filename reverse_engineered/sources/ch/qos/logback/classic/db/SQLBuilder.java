package ch.qos.logback.classic.db;

import ch.qos.logback.classic.db.names.ColumnName;
import ch.qos.logback.classic.db.names.DBNameResolver;
import ch.qos.logback.classic.db.names.TableName;

public class SQLBuilder {
    public static String buildCreateExceptionTableSQL(DBNameResolver dBNameResolver) {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(dBNameResolver.getTableName(TableName.LOGGING_EVENT_EXCEPTION)).append(" (").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(" BIGINT NOT NULL, ").append(dBNameResolver.getColumnName(ColumnName.I)).append(" SMALLINT NOT NULL, ").append(dBNameResolver.getColumnName(ColumnName.TRACE_LINE)).append(" VARCHAR(254) NOT NULL, ").append("PRIMARY KEY (").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(", ").append(dBNameResolver.getColumnName(ColumnName.I)).append("), ").append("FOREIGN KEY (").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(") ").append("REFERENCES ").append(dBNameResolver.getTableName(TableName.LOGGING_EVENT)).append(" (").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(") ").append(")");
        return stringBuilder.toString();
    }

    public static String buildCreateLoggingEventTableSQL(DBNameResolver dBNameResolver) {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(dBNameResolver.getTableName(TableName.LOGGING_EVENT)).append(" (").append(dBNameResolver.getColumnName(ColumnName.TIMESTMP)).append(" BIGINT NOT NULL, ").append(dBNameResolver.getColumnName(ColumnName.FORMATTED_MESSAGE)).append(" TEXT NOT NULL, ").append(dBNameResolver.getColumnName(ColumnName.LOGGER_NAME)).append(" VARCHAR(254) NOT NULL, ").append(dBNameResolver.getColumnName(ColumnName.LEVEL_STRING)).append(" VARCHAR(254) NOT NULL, ").append(dBNameResolver.getColumnName(ColumnName.THREAD_NAME)).append(" VARCHAR(254), ").append(dBNameResolver.getColumnName(ColumnName.REFERENCE_FLAG)).append(" SMALLINT, ").append(dBNameResolver.getColumnName(ColumnName.ARG0)).append(" VARCHAR(254), ").append(dBNameResolver.getColumnName(ColumnName.ARG1)).append(" VARCHAR(254), ").append(dBNameResolver.getColumnName(ColumnName.ARG2)).append(" VARCHAR(254), ").append(dBNameResolver.getColumnName(ColumnName.ARG3)).append(" VARCHAR(254), ").append(dBNameResolver.getColumnName(ColumnName.CALLER_FILENAME)).append(" VARCHAR(254), ").append(dBNameResolver.getColumnName(ColumnName.CALLER_CLASS)).append(" VARCHAR(254), ").append(dBNameResolver.getColumnName(ColumnName.CALLER_METHOD)).append(" VARCHAR(254), ").append(dBNameResolver.getColumnName(ColumnName.CALLER_LINE)).append(" CHAR(4), ").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT").append(")");
        return stringBuilder.toString();
    }

    public static String buildCreatePropertyTableSQL(DBNameResolver dBNameResolver) {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(dBNameResolver.getTableName(TableName.LOGGING_EVENT_PROPERTY)).append(" (").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(" BIGINT NOT NULL, ").append(dBNameResolver.getColumnName(ColumnName.MAPPED_KEY)).append(" VARCHAR(254) NOT NULL, ").append(dBNameResolver.getColumnName(ColumnName.MAPPED_VALUE)).append(" VARCHAR(254) NOT NULL, ").append("PRIMARY KEY (").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(", ").append(dBNameResolver.getColumnName(ColumnName.MAPPED_KEY)).append("), ").append("FOREIGN KEY (").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(") ").append("REFERENCES ").append(dBNameResolver.getTableName(TableName.LOGGING_EVENT)).append(" (").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(") ").append(")");
        return stringBuilder.toString();
    }

    public static String buildInsertExceptionSQL(DBNameResolver dBNameResolver) {
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        stringBuilder.append(dBNameResolver.getTableName(TableName.LOGGING_EVENT_EXCEPTION)).append(" (").append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(", ").append(dBNameResolver.getColumnName(ColumnName.I)).append(", ").append(dBNameResolver.getColumnName(ColumnName.TRACE_LINE)).append(") ").append("VALUES (?, ?, ?)");
        return stringBuilder.toString();
    }

    public static String buildInsertPropertiesSQL(DBNameResolver dBNameResolver) {
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        stringBuilder.append(dBNameResolver.getTableName(TableName.LOGGING_EVENT_PROPERTY)).append(" (");
        stringBuilder.append(dBNameResolver.getColumnName(ColumnName.EVENT_ID)).append(", ");
        stringBuilder.append(dBNameResolver.getColumnName(ColumnName.MAPPED_KEY)).append(", ");
        stringBuilder.append(dBNameResolver.getColumnName(ColumnName.MAPPED_VALUE)).append(") ");
        stringBuilder.append("VALUES (?, ?, ?)");
        return stringBuilder.toString();
    }

    public static String buildInsertSQL(DBNameResolver dBNameResolver) {
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        stringBuilder.append(dBNameResolver.getTableName(TableName.LOGGING_EVENT)).append(" (").append(dBNameResolver.getColumnName(ColumnName.TIMESTMP)).append(", ").append(dBNameResolver.getColumnName(ColumnName.FORMATTED_MESSAGE)).append(", ").append(dBNameResolver.getColumnName(ColumnName.LOGGER_NAME)).append(", ").append(dBNameResolver.getColumnName(ColumnName.LEVEL_STRING)).append(", ").append(dBNameResolver.getColumnName(ColumnName.THREAD_NAME)).append(", ").append(dBNameResolver.getColumnName(ColumnName.REFERENCE_FLAG)).append(", ").append(dBNameResolver.getColumnName(ColumnName.ARG0)).append(", ").append(dBNameResolver.getColumnName(ColumnName.ARG1)).append(", ").append(dBNameResolver.getColumnName(ColumnName.ARG2)).append(", ").append(dBNameResolver.getColumnName(ColumnName.ARG3)).append(", ").append(dBNameResolver.getColumnName(ColumnName.CALLER_FILENAME)).append(", ").append(dBNameResolver.getColumnName(ColumnName.CALLER_CLASS)).append(", ").append(dBNameResolver.getColumnName(ColumnName.CALLER_METHOD)).append(", ").append(dBNameResolver.getColumnName(ColumnName.CALLER_LINE)).append(") ").append("VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return stringBuilder.toString();
    }
}
