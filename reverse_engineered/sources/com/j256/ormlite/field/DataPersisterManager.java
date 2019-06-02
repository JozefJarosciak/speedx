package com.j256.ormlite.field;

import com.j256.ormlite.field.types.EnumStringType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPersisterManager {
    private static final DataPersister DEFAULT_ENUM_PERSISTER = EnumStringType.getSingleton();
    private static final Map<String, DataPersister> builtInMap = new HashMap();
    private static List<DataPersister> registeredPersisters = null;

    static {
        for (DataType dataPersister : DataType.values()) {
            DataPersister dataPersister2 = dataPersister.getDataPersister();
            if (dataPersister2 != null) {
                for (Class name : dataPersister2.getAssociatedClasses()) {
                    builtInMap.put(name.getName(), dataPersister2);
                }
                if (dataPersister2.getAssociatedClassNames() != null) {
                    for (Object put : dataPersister2.getAssociatedClassNames()) {
                        builtInMap.put(put, dataPersister2);
                    }
                }
            }
        }
    }

    private DataPersisterManager() {
    }

    public static void registerDataPersisters(DataPersister... dataPersisterArr) {
        List arrayList = new ArrayList();
        if (registeredPersisters != null) {
            arrayList.addAll(registeredPersisters);
        }
        for (Object add : dataPersisterArr) {
            arrayList.add(add);
        }
        registeredPersisters = arrayList;
    }

    public static void clear() {
        registeredPersisters = null;
    }

    public static DataPersister lookupForField(Field field) {
        if (registeredPersisters != null) {
            for (DataPersister dataPersister : registeredPersisters) {
                if (dataPersister.isValidForField(field)) {
                    return dataPersister;
                }
                for (Class cls : dataPersister.getAssociatedClasses()) {
                    if (field.getType() == cls) {
                        return dataPersister;
                    }
                }
            }
        }
        DataPersister dataPersister2 = (DataPersister) builtInMap.get(field.getType().getName());
        if (dataPersister2 != null) {
            return dataPersister2;
        }
        if (field.getType().isEnum()) {
            return DEFAULT_ENUM_PERSISTER;
        }
        return null;
    }
}
