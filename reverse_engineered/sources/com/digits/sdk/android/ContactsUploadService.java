package com.digits.sdk.android;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import com.twitter.sdk.android.core.TwitterApiException;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.C4835k;
import io.fabric.sdk.android.services.concurrency.internal.C4910b;
import io.fabric.sdk.android.services.concurrency.internal.C4911c;
import io.fabric.sdk.android.services.concurrency.internal.C4914g;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ContactsUploadService extends IntentService {
    /* renamed from: a */
    private ContactsClient f13083a;
    /* renamed from: b */
    private C2932t f13084b;
    /* renamed from: c */
    private C2933u f13085c;
    /* renamed from: d */
    private C4914g f13086d;
    /* renamed from: e */
    private C4835k f13087e;
    /* renamed from: f */
    private Locale f13088f;

    public ContactsUploadService() {
        super("UPLOAD_WORKER");
        m13847a(aa.a().j(), new C2932t(this), new C2933u(), new C4914g(2, new C4910b(1), new C4911c(1000)), C1520c.h(), Locale.getDefault());
    }

    ContactsUploadService(ContactsClient contactsClient, C2932t c2932t, C2933u c2933u, C4914g c4914g, C4835k c4835k, Locale locale) {
        super("UPLOAD_WORKER");
        m13847a(contactsClient, c2932t, c2933u, c4914g, c4835k, locale);
    }

    /* renamed from: a */
    private void m13847a(ContactsClient contactsClient, C2932t c2932t, C2933u c2933u, C4914g c4914g, C4835k c4835k, Locale locale) {
        this.f13083a = contactsClient;
        this.f13084b = c2932t;
        this.f13085c = c2933u;
        this.f13086d = c4914g;
        this.f13087e = c4835k;
        this.f13088f = locale;
        setIntentRedelivery(true);
    }

    protected void onHandleIntent(Intent intent) {
        this.f13085c.m14251a();
        try {
            List b = m13848b();
            int size = b.size();
            int a = m13849a(size);
            final AtomicInteger atomicInteger = new AtomicInteger(0);
            for (int i = 0; i < a; i++) {
                int i2 = i * 100;
                final by byVar = new by(b.subList(i2, Math.min(size, i2 + 100)));
                this.f13086d.a(new Runnable(this) {
                    /* renamed from: c */
                    final /* synthetic */ ContactsUploadService f13082c;

                    public void run() {
                        try {
                            this.f13082c.f13083a.m13843a(byVar);
                            atomicInteger.addAndGet(byVar.f6886a.size());
                        } catch (RetrofitError e) {
                            this.f13082c.m13852a(e);
                        }
                    }
                });
            }
            this.f13086d.shutdown();
            if (!this.f13086d.awaitTermination(300, TimeUnit.SECONDS)) {
                this.f13086d.shutdownNow();
                m13850a();
            } else if (atomicInteger.get() == 0) {
                m13850a();
            } else {
                this.f13085c.m14253a(System.currentTimeMillis());
                this.f13085c.m14252a(atomicInteger.get());
                m13851a(new ContactsUploadResult(atomicInteger.get(), size));
            }
        } catch (Exception e) {
            m13850a();
        }
    }

    /* renamed from: a */
    int m13849a(int i) {
        return ((i + 100) - 1) / 100;
    }

    /* renamed from: b */
    private List<String> m13848b() {
        Cursor cursor = null;
        Collections.emptyList();
        try {
            cursor = this.f13084b.m14249a();
            List<String> a = this.f13084b.m14250a(cursor);
            return a;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* renamed from: a */
    void m13850a() {
        sendBroadcast(new Intent("com.digits.sdk.android.UPLOAD_FAILED"));
    }

    /* renamed from: a */
    void m13851a(ContactsUploadResult contactsUploadResult) {
        Intent intent = new Intent("com.digits.sdk.android.UPLOAD_COMPLETE");
        intent.putExtra("com.digits.sdk.android.UPLOAD_COMPLETE_EXTRA", contactsUploadResult);
        sendBroadcast(intent);
    }

    /* renamed from: a */
    void m13852a(RetrofitError retrofitError) {
        Response response = retrofitError.getResponse();
        int status = response == null ? 0 : response.getStatus();
        TwitterApiException convert = TwitterApiException.convert(retrofitError);
        this.f13087e.d("Digits", String.format(this.f13088f, "contact upload error, httpStatus=%d, errorCode=%d, errorMessage=%s", new Object[]{Integer.valueOf(status), Integer.valueOf(convert.getErrorCode()), convert.getErrorMessage()}));
    }
}
