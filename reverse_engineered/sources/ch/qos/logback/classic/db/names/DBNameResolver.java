package ch.qos.logback.classic.db.names;

public interface DBNameResolver {
    <N extends Enum<?>> String getColumnName(N n);

    <N extends Enum<?>> String getTableName(N n);
}
