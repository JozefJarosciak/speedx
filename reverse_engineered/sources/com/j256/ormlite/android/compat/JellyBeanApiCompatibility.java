package com.j256.ormlite.android.compat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CancellationSignal;
import com.j256.ormlite.android.compat.ApiCompatibility.CancellationHook;

public class JellyBeanApiCompatibility extends BasicApiCompatibility {

    protected static class JellyBeanCancellationHook implements CancellationHook {
        private final CancellationSignal cancellationSignal = new CancellationSignal();

        public void cancel() {
            this.cancellationSignal.cancel();
        }
    }

    public Cursor rawQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, CancellationHook cancellationHook) {
        if (cancellationHook == null) {
            return sQLiteDatabase.rawQuery(str, strArr);
        }
        return sQLiteDatabase.rawQuery(str, strArr, ((JellyBeanCancellationHook) cancellationHook).cancellationSignal);
    }

    public CancellationHook createCancellationHook() {
        return new JellyBeanCancellationHook();
    }
}
