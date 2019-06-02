package com.mob.tools.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.mob.tools.MobLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class LocalDB {
    private File dbFile;
    private HashMap<String, Object> map;

    public void open(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.dbFile = new File(str);
                if (this.dbFile.exists()) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.dbFile));
                    this.map = (HashMap) objectInputStream.readObject();
                    objectInputStream.close();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16934d(th);
        }
    }

    private void commit() {
        if (this.map != null && this.dbFile != null) {
            try {
                if (!this.dbFile.getParentFile().exists()) {
                    this.dbFile.getParentFile().mkdirs();
                }
                synchronized (this.map) {
                    OutputStream fileOutputStream = new FileOutputStream(this.dbFile);
                    if (fileOutputStream.getChannel().tryLock() != null) {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(this.map);
                        objectOutputStream.flush();
                        objectOutputStream.close();
                    } else {
                        fileOutputStream.close();
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
            }
        }
    }

    private void put(String str, Object obj) {
        if (this.map == null) {
            this.map = new HashMap();
        }
        this.map.put(str, obj);
    }

    private Object get(String str) {
        if (this.map == null) {
            return null;
        }
        return this.map.get(str);
    }

    public void putString(String str, String str2) {
        put(str, str2);
        commit();
    }

    public String getString(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return String.valueOf(obj);
    }

    public void putBoolean(String str, Boolean bool) {
        put(str, bool);
        commit();
    }

    public boolean getBoolean(String str) {
        Object obj = get(str);
        if (obj == null) {
            return false;
        }
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : false;
    }

    public void putLong(String str, Long l) {
        put(str, l);
        commit();
    }

    public long getLong(String str) {
        Object obj = get(str);
        if (obj == null) {
            return 0;
        }
        return obj instanceof Long ? ((Long) obj).longValue() : 0;
    }

    public void putInt(String str, Integer num) {
        put(str, num);
        commit();
    }

    public int getInt(String str) {
        Object obj = get(str);
        if (obj == null) {
            return 0;
        }
        return obj instanceof Integer ? ((Integer) obj).intValue() : 0;
    }

    public void putFloat(String str, Float f) {
        put(str, f);
        commit();
    }

    public float getFloat(String str) {
        Object obj = get(str);
        if (obj == null) {
            return 0.0f;
        }
        return obj instanceof Integer ? ((Float) obj).floatValue() : 0.0f;
    }

    public void remove(String str) {
        if (this.map == null) {
            this.map = new HashMap();
        }
        this.map.remove(str);
        commit();
    }

    public void putObject(String str, Object obj) {
        if (obj != null) {
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                objectOutputStream.close();
                putString(str, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
            }
        }
    }

    public Object getObject(String str) {
        try {
            Object string = getString(str);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(string, 2)));
            string = objectInputStream.readObject();
            objectInputStream.close();
            return string;
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
            return null;
        }
    }
}
