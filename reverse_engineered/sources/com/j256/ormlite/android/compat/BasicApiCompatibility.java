package com.j256.ormlite.android.compat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.compat.ApiCompatibility.CancellationHook;

public class BasicApiCompatibility implements ApiCompatibility {
    public Cursor rawQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, CancellationHook cancellationHook) {
        return sQLiteDatabase.rawQuery(str, strArr);
    }

    public CancellationHook createCancellationHook() {
        return null;
    }
}
