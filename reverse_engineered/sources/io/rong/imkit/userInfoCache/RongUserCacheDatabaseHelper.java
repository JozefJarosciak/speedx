package io.rong.imkit.userInfoCache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;

class RongUserCacheDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "IMKitUserInfoCache";
    private static final int DB_VERSION = 1;
    private static String dbPath;
    private SQLiteDatabase database;

    public RongUserCacheDatabaseHelper(Context context) {
        this(context, DB_NAME, null, 1);
    }

    private RongUserCacheDatabaseHelper(Context context, String str, CursorFactory cursorFactory, int i) {
        super(new RongDatabaseContext(context, dbPath), str, cursorFactory, i);
    }

    public static void setDbPath(Context context, String str, String str2) {
        dbPath = context.getFilesDir().getAbsolutePath();
        dbPath += File.separator + str + File.separator + str2;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.database = sQLiteDatabase;
        sQLiteDatabase.execSQL("CREATE TABLE users (id TEXT PRIMARY KEY NOT NULL UNIQUE, name TEXT, portrait TEXT)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS id_idx_users ON users(id)");
        sQLiteDatabase.execSQL("CREATE TABLE group_users (group_id TEXT NOT NULL, user_id TEXT NOT NULL, nickname TEXT)");
        sQLiteDatabase.execSQL("CREATE TABLE groups (id TEXT PRIMARY KEY NOT NULL UNIQUE, name TEXT, portrait TEXT)");
        sQLiteDatabase.execSQL("CREATE TABLE discussions (id TEXT PRIMARY KEY NOT NULL UNIQUE, name TEXT, portrait TEXT)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
