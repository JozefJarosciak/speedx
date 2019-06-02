package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadata;
import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadataCollection;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

final class MetadataManager {
    private static final String ALTERNATE_FORMATS_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto";
    static final MetadataLoader DEFAULT_METADATA_LOADER = new C40561();
    static final String MULTI_FILE_PHONE_NUMBER_METADATA_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto";
    private static final String SHORT_NUMBER_METADATA_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto";
    static final String SINGLE_FILE_PHONE_NUMBER_METADATA_FILE_NAME = "/com/google/i18n/phonenumbers/data/SingleFilePhoneNumberMetadataProto";
    private static final Set<Integer> alternateFormatsCountryCodes = AlternateFormatsCountryCodeSet.getCountryCodeSet();
    private static final ConcurrentHashMap<Integer, PhoneMetadata> alternateFormatsMap = new ConcurrentHashMap();
    private static final Logger logger = Logger.getLogger(MetadataManager.class.getName());
    private static final ConcurrentHashMap<String, PhoneMetadata> shortNumberMetadataMap = new ConcurrentHashMap();
    private static final Set<String> shortNumberMetadataRegionCodes = ShortNumbersRegionCodeSet.getRegionCodeSet();

    /* renamed from: com.google.i18n.phonenumbers.MetadataManager$1 */
    static class C40561 implements MetadataLoader {
        C40561() {
        }

        public InputStream loadMetadata(String str) {
            return MetadataManager.class.getResourceAsStream(str);
        }
    }

    static class SingleFileMetadataMaps {
        private final Map<Integer, PhoneMetadata> countryCallingCodeToMetadata;
        private final Map<String, PhoneMetadata> regionCodeToMetadata;

        static SingleFileMetadataMaps load(String str, MetadataLoader metadataLoader) {
            List<PhoneMetadata> access$000 = MetadataManager.getMetadataFromSingleFileName(str, metadataLoader);
            Map hashMap = new HashMap();
            Map hashMap2 = new HashMap();
            for (PhoneMetadata phoneMetadata : access$000) {
                String id = phoneMetadata.getId();
                if (PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY.equals(id)) {
                    hashMap2.put(Integer.valueOf(phoneMetadata.getCountryCode()), phoneMetadata);
                } else {
                    hashMap.put(id, phoneMetadata);
                }
            }
            return new SingleFileMetadataMaps(hashMap, hashMap2);
        }

        private SingleFileMetadataMaps(Map<String, PhoneMetadata> map, Map<Integer, PhoneMetadata> map2) {
            this.regionCodeToMetadata = Collections.unmodifiableMap(map);
            this.countryCallingCodeToMetadata = Collections.unmodifiableMap(map2);
        }

        PhoneMetadata get(String str) {
            return (PhoneMetadata) this.regionCodeToMetadata.get(str);
        }

        PhoneMetadata get(int i) {
            return (PhoneMetadata) this.countryCallingCodeToMetadata.get(Integer.valueOf(i));
        }
    }

    private MetadataManager() {
    }

    static PhoneMetadata getAlternateFormatsForCountry(int i) {
        if (alternateFormatsCountryCodes.contains(Integer.valueOf(i))) {
            return getMetadataFromMultiFilePrefix(Integer.valueOf(i), alternateFormatsMap, ALTERNATE_FORMATS_FILE_PREFIX, DEFAULT_METADATA_LOADER);
        }
        return null;
    }

    static PhoneMetadata getShortNumberMetadataForRegion(String str) {
        if (shortNumberMetadataRegionCodes.contains(str)) {
            return getMetadataFromMultiFilePrefix(str, shortNumberMetadataMap, SHORT_NUMBER_METADATA_FILE_PREFIX, DEFAULT_METADATA_LOADER);
        }
        return null;
    }

    static Set<String> getSupportedShortNumberRegions() {
        return Collections.unmodifiableSet(shortNumberMetadataRegionCodes);
    }

    static <T> PhoneMetadata getMetadataFromMultiFilePrefix(T t, ConcurrentHashMap<T, PhoneMetadata> concurrentHashMap, String str, MetadataLoader metadataLoader) {
        PhoneMetadata phoneMetadata = (PhoneMetadata) concurrentHashMap.get(t);
        if (phoneMetadata != null) {
            return phoneMetadata;
        }
        String str2 = str + "_" + t;
        List metadataFromSingleFileName = getMetadataFromSingleFileName(str2, metadataLoader);
        if (metadataFromSingleFileName.size() > 1) {
            logger.log(Level.WARNING, "more than one metadata in file " + str2);
        }
        phoneMetadata = (PhoneMetadata) metadataFromSingleFileName.get(0);
        PhoneMetadata phoneMetadata2 = (PhoneMetadata) concurrentHashMap.putIfAbsent(t, phoneMetadata);
        if (phoneMetadata2 == null) {
            phoneMetadata2 = phoneMetadata;
        }
        return phoneMetadata2;
    }

    static SingleFileMetadataMaps getSingleFileMetadataMaps(AtomicReference<SingleFileMetadataMaps> atomicReference, String str, MetadataLoader metadataLoader) {
        SingleFileMetadataMaps singleFileMetadataMaps = (SingleFileMetadataMaps) atomicReference.get();
        if (singleFileMetadataMaps != null) {
            return singleFileMetadataMaps;
        }
        atomicReference.compareAndSet(null, SingleFileMetadataMaps.load(str, metadataLoader));
        return (SingleFileMetadataMaps) atomicReference.get();
    }

    private static List<PhoneMetadata> getMetadataFromSingleFileName(String str, MetadataLoader metadataLoader) {
        InputStream loadMetadata = metadataLoader.loadMetadata(str);
        if (loadMetadata == null) {
            throw new IllegalStateException("missing metadata: " + str);
        }
        List<PhoneMetadata> metadataList = loadMetadataAndCloseInput(loadMetadata).getMetadataList();
        if (metadataList.size() != 0) {
            return metadataList;
        }
        throw new IllegalStateException("empty metadata: " + str);
    }

    private static PhoneMetadataCollection loadMetadataAndCloseInput(InputStream inputStream) {
        ObjectInputStream objectInputStream;
        Throwable e;
        try {
            objectInputStream = new ObjectInputStream(inputStream);
            try {
                PhoneMetadataCollection phoneMetadataCollection = new PhoneMetadataCollection();
                phoneMetadataCollection.readExternal(objectInputStream);
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (Throwable e2) {
                        logger.log(Level.WARNING, "error closing input stream (ignored)", e2);
                    }
                } else {
                    inputStream.close();
                }
                return phoneMetadataCollection;
            } catch (Throwable e22) {
                throw new RuntimeException("cannot load/parse metadata", e22);
            } catch (Throwable th) {
                e22 = th;
                if (objectInputStream == null) {
                    inputStream.close();
                } else {
                    try {
                        objectInputStream.close();
                    } catch (Throwable e3) {
                        logger.log(Level.WARNING, "error closing input stream (ignored)", e3);
                    }
                }
                throw e22;
            }
        } catch (Throwable e222) {
            throw new RuntimeException("cannot load/parse metadata", e222);
        } catch (Throwable th2) {
            e222 = th2;
            objectInputStream = null;
            if (objectInputStream == null) {
                objectInputStream.close();
            } else {
                inputStream.close();
            }
            throw e222;
        }
    }
}
