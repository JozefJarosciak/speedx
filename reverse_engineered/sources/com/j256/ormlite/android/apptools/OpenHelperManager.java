package com.j256.ormlite.android.apptools;

import android.content.Context;
import android.content.res.Resources;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OpenHelperManager {
    private static final String HELPER_CLASS_RESOURCE_NAME = "open_helper_classname";
    private static volatile OrmLiteSqliteOpenHelper helper = null;
    private static Class<? extends OrmLiteSqliteOpenHelper> helperClass = null;
    private static int instanceCount = 0;
    private static Logger logger = LoggerFactory.getLogger(OpenHelperManager.class);
    private static boolean wasClosed = false;

    public static synchronized void setOpenHelperClass(Class<? extends OrmLiteSqliteOpenHelper> cls) {
        synchronized (OpenHelperManager.class) {
            if (cls == null) {
                helperClass = null;
            } else {
                innerSetHelperClass(cls);
            }
        }
    }

    public static synchronized void setHelper(OrmLiteSqliteOpenHelper ormLiteSqliteOpenHelper) {
        synchronized (OpenHelperManager.class) {
            helper = ormLiteSqliteOpenHelper;
        }
    }

    public static synchronized <T extends OrmLiteSqliteOpenHelper> T getHelper(Context context, Class<T> cls) {
        T loadHelper;
        synchronized (OpenHelperManager.class) {
            if (cls == null) {
                throw new IllegalArgumentException("openHelperClass argument is null");
            }
            innerSetHelperClass(cls);
            loadHelper = loadHelper(context, cls);
        }
        return loadHelper;
    }

    @Deprecated
    public static synchronized OrmLiteSqliteOpenHelper getHelper(Context context) {
        OrmLiteSqliteOpenHelper loadHelper;
        synchronized (OpenHelperManager.class) {
            if (helperClass == null) {
                if (context == null) {
                    throw new IllegalArgumentException("context argument is null");
                }
                innerSetHelperClass(lookupHelperClass(context.getApplicationContext(), context.getClass()));
            }
            loadHelper = loadHelper(context, helperClass);
        }
        return loadHelper;
    }

    @Deprecated
    public static void release() {
        releaseHelper();
    }

    public static synchronized void releaseHelper() {
        synchronized (OpenHelperManager.class) {
            instanceCount--;
            logger.trace("releasing helper {}, instance count = {}", helper, Integer.valueOf(instanceCount));
            if (instanceCount <= 0) {
                if (helper != null) {
                    logger.trace("zero instances, closing helper {}", helper);
                    helper.close();
                    helper = null;
                    wasClosed = true;
                }
                if (instanceCount < 0) {
                    logger.error("too many calls to release helper, instance count = {}", Integer.valueOf(instanceCount));
                }
            }
        }
    }

    private static void innerSetHelperClass(Class<? extends OrmLiteSqliteOpenHelper> cls) {
        if (cls == null) {
            throw new IllegalStateException("Helper class was trying to be reset to null");
        } else if (helperClass == null) {
            helperClass = cls;
        } else if (helperClass != cls) {
            throw new IllegalStateException("Helper class was " + helperClass + " but is trying to be reset to " + cls);
        }
    }

    private static <T extends OrmLiteSqliteOpenHelper> T loadHelper(Context context, Class<T> cls) {
        if (helper == null) {
            if (wasClosed) {
                logger.info("helper was already closed and is being re-opened");
            }
            if (context == null) {
                throw new IllegalArgumentException("context argument is null");
            }
            helper = constructHelper(context.getApplicationContext(), cls);
            logger.trace("zero instances, created helper {}", helper);
            BaseDaoImpl.clearAllInternalObjectCaches();
            DaoManager.clearDaoCache();
            instanceCount = 0;
        }
        instanceCount++;
        logger.trace("returning helper {}, instance count = {} ", helper, Integer.valueOf(instanceCount));
        return helper;
    }

    private static OrmLiteSqliteOpenHelper constructHelper(Context context, Class<? extends OrmLiteSqliteOpenHelper> cls) {
        try {
            try {
                return (OrmLiteSqliteOpenHelper) cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
            } catch (Throwable e) {
                throw new IllegalStateException("Could not construct instance of helper class " + cls, e);
            }
        } catch (Throwable e2) {
            throw new IllegalStateException("Could not find public constructor that has a single (Context) argument for helper class " + cls, e2);
        }
    }

    private static Class<? extends OrmLiteSqliteOpenHelper> lookupHelperClass(Context context, Class<?> cls) {
        Class<? extends OrmLiteSqliteOpenHelper> cls2;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(HELPER_CLASS_RESOURCE_NAME, "string", context.getPackageName());
        if (identifier != 0) {
            String string = resources.getString(identifier);
            try {
                cls2 = Class.forName(string);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not create helper instance for class " + string, e);
            }
        }
        for (Class cls3 = cls; cls3 != null; cls3 = cls3.getSuperclass()) {
            Type genericSuperclass = cls3.getGenericSuperclass();
            if (genericSuperclass != null && (genericSuperclass instanceof ParameterizedType)) {
                Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                if (!(actualTypeArguments == null || actualTypeArguments.length == 0)) {
                    for (Type genericSuperclass2 : actualTypeArguments) {
                        if (genericSuperclass2 instanceof Class) {
                            cls2 = (Class) genericSuperclass2;
                            if (OrmLiteSqliteOpenHelper.class.isAssignableFrom(cls2)) {
                            }
                        }
                    }
                    continue;
                }
            }
        }
        throw new IllegalStateException("Could not find OpenHelperClass because none of the generic parameters of class " + cls + " extends OrmLiteSqliteOpenHelper.  You should use getHelper(Context, Class) instead.");
        return cls2;
    }
}
