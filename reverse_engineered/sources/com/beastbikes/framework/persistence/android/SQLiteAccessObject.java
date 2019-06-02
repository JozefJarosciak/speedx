package com.beastbikes.framework.persistence.android;

import android.content.ContentProvider;
import com.beastbikes.framework.persistence.C1663b;
import com.beastbikes.framework.persistence.PersistentObject;

public abstract class SQLiteAccessObject<T extends PersistentObject> extends ContentProvider implements C1663b<T> {
}
