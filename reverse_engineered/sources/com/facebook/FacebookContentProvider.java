package com.facebook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import com.facebook.internal.C3028n;
import java.io.FileNotFoundException;
import java.util.UUID;

public class FacebookContentProvider extends ContentProvider {
    /* renamed from: a */
    private static final String f13373a = FacebookContentProvider.class.getName();

    /* renamed from: a */
    public static String m14294a(String str, UUID uuid, String str2) {
        return String.format("%s%s/%s/%s", new Object[]{"content://com.facebook.app.FacebookContentProvider", str, uuid.toString(), str2});
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        Pair a = m14295a(uri);
        if (a == null) {
            throw new FileNotFoundException();
        }
        try {
            return ParcelFileDescriptor.open(C3028n.m14640a((UUID) a.first, (String) a.second), 268435456);
        } catch (FileNotFoundException e) {
            Log.e(f13373a, "Got unexpected exception:" + e);
            throw e;
        }
    }

    /* renamed from: a */
    Pair<UUID, String> m14295a(Uri uri) {
        try {
            String[] split = uri.getPath().substring(1).split("/");
            String str = split[0];
            return new Pair(UUID.fromString(str), split[1]);
        } catch (Exception e) {
            return null;
        }
    }
}
