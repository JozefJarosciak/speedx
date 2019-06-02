package com.facebook.stetho.inspector.protocol.module;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Database.AddDatabaseEvent;
import com.facebook.stetho.inspector.protocol.module.Database.DatabaseObject;
import com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse;
import java.util.List;

public abstract class Database$DatabaseDriver {
    protected Context mContext;

    /* renamed from: com.facebook.stetho.inspector.protocol.module.Database$DatabaseDriver$ExecuteResultHandler */
    public interface ExecuteResultHandler<T> {
        T handleInsert(long j) throws SQLiteException;

        T handleRawQuery() throws SQLiteException;

        T handleSelect(Cursor cursor) throws SQLiteException;

        T handleUpdateDelete(int i) throws SQLiteException;
    }

    public abstract ExecuteSQLResponse executeSQL(String str, String str2, ExecuteResultHandler<ExecuteSQLResponse> executeResultHandler) throws SQLiteException;

    public abstract List<String> getDatabaseNames();

    public abstract List<String> getTableNames(String str);

    public Database$DatabaseDriver(Context context) {
        this.mContext = context;
    }

    private final void onRegistered(JsonRpcPeer jsonRpcPeer) {
        for (String str : getDatabaseNames()) {
            DatabaseObject databaseObject = new DatabaseObject();
            databaseObject.id = str;
            databaseObject.name = str;
            databaseObject.domain = this.mContext.getPackageName();
            databaseObject.version = "N/A";
            AddDatabaseEvent addDatabaseEvent = new AddDatabaseEvent();
            addDatabaseEvent.database = databaseObject;
            jsonRpcPeer.invokeMethod("Database.addDatabase", addDatabaseEvent, null);
        }
    }

    private final void onUnregistered(JsonRpcPeer jsonRpcPeer) {
    }
}
