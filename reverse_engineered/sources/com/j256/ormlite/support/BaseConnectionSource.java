package com.j256.ormlite.support;

import com.j256.ormlite.logger.Logger;
import java.sql.SQLException;

public abstract class BaseConnectionSource implements ConnectionSource {
    private ThreadLocal<NestedConnection> specialConnection = new ThreadLocal();

    private static class NestedConnection {
        public final DatabaseConnection connection;
        private int nestedC = 1;

        public NestedConnection(DatabaseConnection databaseConnection) {
            this.connection = databaseConnection;
        }

        public void increment() {
            this.nestedC++;
        }

        public int decrementAndGet() {
            this.nestedC--;
            return this.nestedC;
        }
    }

    public DatabaseConnection getSpecialConnection() {
        NestedConnection nestedConnection = (NestedConnection) this.specialConnection.get();
        if (nestedConnection == null) {
            return null;
        }
        return nestedConnection.connection;
    }

    protected DatabaseConnection getSavedConnection() {
        NestedConnection nestedConnection = (NestedConnection) this.specialConnection.get();
        if (nestedConnection == null) {
            return null;
        }
        return nestedConnection.connection;
    }

    protected boolean isSavedConnection(DatabaseConnection databaseConnection) {
        NestedConnection nestedConnection = (NestedConnection) this.specialConnection.get();
        if (nestedConnection == null) {
            return false;
        }
        return nestedConnection.connection == databaseConnection;
    }

    protected boolean saveSpecial(DatabaseConnection databaseConnection) throws SQLException {
        NestedConnection nestedConnection = (NestedConnection) this.specialConnection.get();
        if (nestedConnection == null) {
            this.specialConnection.set(new NestedConnection(databaseConnection));
            return true;
        } else if (nestedConnection.connection != databaseConnection) {
            throw new SQLException("trying to save connection " + databaseConnection + " but already have saved connection " + nestedConnection.connection);
        } else {
            nestedConnection.increment();
            return false;
        }
    }

    protected boolean clearSpecial(DatabaseConnection databaseConnection, Logger logger) {
        NestedConnection nestedConnection = (NestedConnection) this.specialConnection.get();
        if (databaseConnection == null) {
            return false;
        }
        if (nestedConnection == null) {
            logger.error("no connection has been saved when clear() called");
            return false;
        } else if (nestedConnection.connection == databaseConnection) {
            if (nestedConnection.decrementAndGet() == 0) {
                this.specialConnection.set(null);
            }
            return true;
        } else {
            logger.error("connection saved {} is not the one being cleared {}", nestedConnection.connection, (Object) databaseConnection);
            return false;
        }
    }
}
