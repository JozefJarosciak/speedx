package p203u.aly;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import java.io.File;

/* compiled from: UMCCPathDatabaseContext */
/* renamed from: u.aly.bn */
public class bn extends ContextWrapper {
    /* renamed from: a */
    private String f18903a;

    public bn(Context context, String str) {
        super(context);
        this.f18903a = str;
    }

    public SQLiteDatabase openOrCreateDatabase(String str, int i, CursorFactory cursorFactory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str).getAbsolutePath(), cursorFactory);
    }

    public File getDatabasePath(String str) {
        File file = new File(this.f18903a + str);
        if (!(file.getParentFile().exists() || file.getParentFile().isDirectory())) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    public SQLiteDatabase openOrCreateDatabase(String str, int i, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str).getAbsolutePath(), cursorFactory, databaseErrorHandler);
    }
}
