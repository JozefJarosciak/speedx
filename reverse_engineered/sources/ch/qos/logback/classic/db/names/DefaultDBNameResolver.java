package ch.qos.logback.classic.db.names;

public class DefaultDBNameResolver implements DBNameResolver {
    public <N extends Enum<?>> String getColumnName(N n) {
        return n.toString().toLowerCase();
    }

    public <N extends Enum<?>> String getTableName(N n) {
        return n.toString().toLowerCase();
    }
}
