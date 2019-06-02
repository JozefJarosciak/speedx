package cn.sharesdk.framework.p011b.p012a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: DBHelp */
/* renamed from: cn.sharesdk.framework.b.a.a */
public class C0585a extends SQLiteOpenHelper {
    public C0585a(Context context) {
        super(context.getApplicationContext(), "sharesdk.db", null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(" create table  message(_id integer primary key autoincrement,post_time timestamp not null, message_data text not null);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
    }

    public synchronized void close() {
        super.close();
    }
}
