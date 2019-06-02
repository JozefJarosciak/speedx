package com.j256.ormlite.misc;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseFieldConfig;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Collection;

public class JavaxPersistence {
    public static DatabaseFieldConfig createFieldConfig(DatabaseType databaseType, Field field) throws SQLException {
        Object obj = null;
        Object obj2 = null;
        Annotation annotation = null;
        Annotation annotation2 = null;
        Annotation annotation3 = null;
        Annotation annotation4 = null;
        Object obj3 = null;
        Object obj4 = null;
        Annotation annotation5 = null;
        Annotation[] annotations = field.getAnnotations();
        int length = annotations.length;
        int i = 0;
        while (i < length) {
            Annotation annotation6 = annotations[i];
            Class annotationType = annotation6.annotationType();
            if (annotationType.getName().equals("javax.persistence.Column")) {
                obj = annotation6;
            }
            if (annotationType.getName().equals("javax.persistence.Basic")) {
                obj2 = annotation6;
            }
            if (annotationType.getName().equals("javax.persistence.Id")) {
                annotation = annotation6;
            }
            if (annotationType.getName().equals("javax.persistence.GeneratedValue")) {
                annotation2 = annotation6;
            }
            if (annotationType.getName().equals("javax.persistence.OneToOne")) {
                annotation3 = annotation6;
            }
            if (annotationType.getName().equals("javax.persistence.ManyToOne")) {
                annotation4 = annotation6;
            }
            if (annotationType.getName().equals("javax.persistence.JoinColumn")) {
                obj3 = annotation6;
            }
            if (annotationType.getName().equals("javax.persistence.Enumerated")) {
                obj4 = annotation6;
            }
            if (!annotationType.getName().equals("javax.persistence.Version")) {
                annotation6 = annotation5;
            }
            i++;
            annotation5 = annotation6;
        }
        if (obj == null && obj2 == null && annotation == null && annotation3 == null && r8 == null && obj4 == null && annotation5 == null) {
            return null;
        }
        Boolean bool;
        Object invoke;
        DatabaseFieldConfig databaseFieldConfig = new DatabaseFieldConfig();
        String name = field.getName();
        if (databaseType.isEntityNamesMustBeUpCase()) {
            name = name.toUpperCase();
        }
        databaseFieldConfig.setFieldName(name);
        if (obj != null) {
            try {
                name = (String) obj.getClass().getMethod("name", new Class[0]).invoke(obj, new Object[0]);
                if (name != null && name.length() > 0) {
                    databaseFieldConfig.setColumnName(name);
                }
                name = (String) obj.getClass().getMethod("columnDefinition", new Class[0]).invoke(obj, new Object[0]);
                if (name != null && name.length() > 0) {
                    databaseFieldConfig.setColumnDefinition(name);
                }
                databaseFieldConfig.setWidth(((Integer) obj.getClass().getMethod("length", new Class[0]).invoke(obj, new Object[0])).intValue());
                bool = (Boolean) obj.getClass().getMethod("nullable", new Class[0]).invoke(obj, new Object[0]);
                if (bool != null) {
                    databaseFieldConfig.setCanBeNull(bool.booleanValue());
                }
                bool = (Boolean) obj.getClass().getMethod("unique", new Class[0]).invoke(obj, new Object[0]);
                if (bool != null) {
                    databaseFieldConfig.setUnique(bool.booleanValue());
                }
            } catch (Throwable e) {
                throw SqlExceptionUtil.create("Problem accessing fields from the @Column annotation for field " + field, e);
            }
        }
        if (obj2 != null) {
            try {
                bool = (Boolean) obj2.getClass().getMethod("optional", new Class[0]).invoke(obj2, new Object[0]);
                if (bool == null) {
                    databaseFieldConfig.setCanBeNull(true);
                } else {
                    databaseFieldConfig.setCanBeNull(bool.booleanValue());
                }
            } catch (Throwable e2) {
                throw SqlExceptionUtil.create("Problem accessing fields from the @Basic annotation for field " + field, e2);
            }
        }
        if (annotation != null) {
            if (annotation2 == null) {
                databaseFieldConfig.setId(true);
            } else {
                databaseFieldConfig.setGeneratedId(true);
            }
        }
        if (!(annotation3 == null && r8 == null)) {
            if (Collection.class.isAssignableFrom(field.getType()) || ForeignCollection.class.isAssignableFrom(field.getType())) {
                databaseFieldConfig.setForeignCollection(true);
                if (obj3 != null) {
                    try {
                        name = (String) obj3.getClass().getMethod("name", new Class[0]).invoke(obj3, new Object[0]);
                        if (name != null && name.length() > 0) {
                            databaseFieldConfig.setForeignCollectionColumnName(name);
                        }
                        invoke = obj3.getClass().getMethod("fetch", new Class[0]).invoke(obj3, new Object[0]);
                        if (invoke != null && invoke.toString().equals("EAGER")) {
                            databaseFieldConfig.setForeignCollectionEager(true);
                        }
                    } catch (Throwable e22) {
                        throw SqlExceptionUtil.create("Problem accessing fields from the @JoinColumn annotation for field " + field, e22);
                    }
                }
            }
            databaseFieldConfig.setForeign(true);
            if (obj3 != null) {
                try {
                    name = (String) obj3.getClass().getMethod("name", new Class[0]).invoke(obj3, new Object[0]);
                    if (name != null && name.length() > 0) {
                        databaseFieldConfig.setColumnName(name);
                    }
                    bool = (Boolean) obj3.getClass().getMethod("nullable", new Class[0]).invoke(obj3, new Object[0]);
                    if (bool != null) {
                        databaseFieldConfig.setCanBeNull(bool.booleanValue());
                    }
                    bool = (Boolean) obj3.getClass().getMethod("unique", new Class[0]).invoke(obj3, new Object[0]);
                    if (bool != null) {
                        databaseFieldConfig.setUnique(bool.booleanValue());
                    }
                } catch (Throwable e222) {
                    throw SqlExceptionUtil.create("Problem accessing fields from the @JoinColumn annotation for field " + field, e222);
                }
            }
        }
        if (obj4 != null) {
            try {
                invoke = obj4.getClass().getMethod("value", new Class[0]).invoke(obj4, new Object[0]);
                if (invoke == null || !invoke.toString().equals("STRING")) {
                    databaseFieldConfig.setDataType(DataType.ENUM_INTEGER);
                } else {
                    databaseFieldConfig.setDataType(DataType.ENUM_STRING);
                }
            } catch (Throwable e2222) {
                throw SqlExceptionUtil.create("Problem accessing fields from the @Enumerated annotation for field " + field, e2222);
            }
        }
        if (annotation5 != null) {
            databaseFieldConfig.setVersion(true);
        }
        if (databaseFieldConfig.getDataPersister() == null) {
            databaseFieldConfig.setDataPersister(DataPersisterManager.lookupForField(field));
        }
        boolean z = (DatabaseFieldConfig.findGetMethod(field, false) == null || DatabaseFieldConfig.findSetMethod(field, false) == null) ? false : true;
        databaseFieldConfig.setUseGetSet(z);
        return databaseFieldConfig;
    }

    public static String getEntityName(Class<?> cls) {
        Annotation[] annotations = cls.getAnnotations();
        int length = annotations.length;
        int i = 0;
        Annotation annotation = null;
        while (i < length) {
            Annotation annotation2 = annotations[i];
            if (!annotation2.annotationType().getName().equals("javax.persistence.Entity")) {
                annotation2 = annotation;
            }
            i++;
            annotation = annotation2;
        }
        if (annotation == null) {
            return null;
        }
        try {
            String str = (String) annotation.getClass().getMethod("name", new Class[0]).invoke(annotation, new Object[0]);
            if (str == null || str.length() <= 0) {
                return null;
            }
            return str;
        } catch (Throwable e) {
            throw new IllegalStateException("Could not get entity name from class " + cls, e);
        }
    }
}
