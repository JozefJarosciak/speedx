package ch.qos.logback.core.net.ssl;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class KeyStoreFactoryBean {
    private String location;
    private String password;
    private String provider;
    private String type;

    private KeyStore newKeyStore() throws NoSuchAlgorithmException, NoSuchProviderException, KeyStoreException {
        return getProvider() != null ? KeyStore.getInstance(getType(), getProvider()) : KeyStore.getInstance(getType());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.KeyStore createKeyStore() throws java.security.NoSuchProviderException, java.security.NoSuchAlgorithmException, java.security.KeyStoreException {
        /*
        r6 = this;
        r0 = r6.getLocation();
        if (r0 != 0) goto L_0x000e;
    L_0x0006:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "location is required";
        r0.<init>(r1);
        throw r0;
    L_0x000e:
        r0 = 0;
        r1 = r6.getLocation();	 Catch:{ NoSuchProviderException -> 0x0037, NoSuchAlgorithmException -> 0x005f, FileNotFoundException -> 0x007d, Exception -> 0x009b, all -> 0x00cd }
        r1 = ch.qos.logback.core.util.LocationUtil.urlForResource(r1);	 Catch:{ NoSuchProviderException -> 0x0037, NoSuchAlgorithmException -> 0x005f, FileNotFoundException -> 0x007d, Exception -> 0x009b, all -> 0x00cd }
        r0 = r1.openStream();	 Catch:{ NoSuchProviderException -> 0x0037, NoSuchAlgorithmException -> 0x005f, FileNotFoundException -> 0x007d, Exception -> 0x009b, all -> 0x00cd }
        r1 = r6.newKeyStore();	 Catch:{ NoSuchProviderException -> 0x0037, NoSuchAlgorithmException -> 0x005f, FileNotFoundException -> 0x007d, Exception -> 0x00d2 }
        r2 = r6.getPassword();	 Catch:{ NoSuchProviderException -> 0x0037, NoSuchAlgorithmException -> 0x005f, FileNotFoundException -> 0x007d, Exception -> 0x00d2 }
        r2 = r2.toCharArray();	 Catch:{ NoSuchProviderException -> 0x0037, NoSuchAlgorithmException -> 0x005f, FileNotFoundException -> 0x007d, Exception -> 0x00d2 }
        r1.load(r0, r2);	 Catch:{ NoSuchProviderException -> 0x0037, NoSuchAlgorithmException -> 0x005f, FileNotFoundException -> 0x007d, Exception -> 0x00d2 }
        if (r0 == 0) goto L_0x002f;
    L_0x002c:
        r0.close();	 Catch:{ IOException -> 0x0030 }
    L_0x002f:
        return r1;
    L_0x0030:
        r0 = move-exception;
        r2 = java.lang.System.err;
        r0.printStackTrace(r2);
        goto L_0x002f;
    L_0x0037:
        r1 = move-exception;
        r1 = new java.security.NoSuchProviderException;	 Catch:{ all -> 0x0055 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0055 }
        r2.<init>();	 Catch:{ all -> 0x0055 }
        r3 = "no such keystore provider: ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0055 }
        r3 = r6.getProvider();	 Catch:{ all -> 0x0055 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0055 }
        r2 = r2.toString();	 Catch:{ all -> 0x0055 }
        r1.<init>(r2);	 Catch:{ all -> 0x0055 }
        throw r1;	 Catch:{ all -> 0x0055 }
    L_0x0055:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0059:
        if (r1 == 0) goto L_0x005e;
    L_0x005b:
        r1.close();	 Catch:{ IOException -> 0x00c6 }
    L_0x005e:
        throw r0;
    L_0x005f:
        r1 = move-exception;
        r1 = new java.security.NoSuchAlgorithmException;	 Catch:{ all -> 0x0055 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0055 }
        r2.<init>();	 Catch:{ all -> 0x0055 }
        r3 = "no such keystore type: ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0055 }
        r3 = r6.getType();	 Catch:{ all -> 0x0055 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0055 }
        r2 = r2.toString();	 Catch:{ all -> 0x0055 }
        r1.<init>(r2);	 Catch:{ all -> 0x0055 }
        throw r1;	 Catch:{ all -> 0x0055 }
    L_0x007d:
        r1 = move-exception;
        r1 = new java.security.KeyStoreException;	 Catch:{ all -> 0x0055 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0055 }
        r2.<init>();	 Catch:{ all -> 0x0055 }
        r3 = r6.getLocation();	 Catch:{ all -> 0x0055 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0055 }
        r3 = ": file not found";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0055 }
        r2 = r2.toString();	 Catch:{ all -> 0x0055 }
        r1.<init>(r2);	 Catch:{ all -> 0x0055 }
        throw r1;	 Catch:{ all -> 0x0055 }
    L_0x009b:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x009f:
        r2 = new java.security.KeyStoreException;	 Catch:{ all -> 0x00c4 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c4 }
        r3.<init>();	 Catch:{ all -> 0x00c4 }
        r4 = r6.getLocation();	 Catch:{ all -> 0x00c4 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c4 }
        r4 = ": ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c4 }
        r4 = r0.getMessage();	 Catch:{ all -> 0x00c4 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c4 }
        r3 = r3.toString();	 Catch:{ all -> 0x00c4 }
        r2.<init>(r3, r0);	 Catch:{ all -> 0x00c4 }
        throw r2;	 Catch:{ all -> 0x00c4 }
    L_0x00c4:
        r0 = move-exception;
        goto L_0x0059;
    L_0x00c6:
        r1 = move-exception;
        r2 = java.lang.System.err;
        r1.printStackTrace(r2);
        goto L_0x005e;
    L_0x00cd:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0059;
    L_0x00d2:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x009f;
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.qos.logback.core.net.ssl.KeyStoreFactoryBean.createKeyStore():java.security.KeyStore");
    }

    public String getLocation() {
        return this.location;
    }

    public String getPassword() {
        return this.password == null ? SSL.DEFAULT_KEYSTORE_PASSWORD : this.password;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getType() {
        return this.type == null ? SSL.DEFAULT_KEYSTORE_TYPE : this.type;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
