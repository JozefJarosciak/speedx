package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzh;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    private T LQ;
    private Bundle LR;
    private LinkedList<zza> LS;
    private final zzf<T> LT = new C33121(this);

    /* renamed from: com.google.android.gms.dynamic.zza$1 */
    class C33121 implements zzf<T> {
        final /* synthetic */ zza LU;

        C33121(zza zza) {
            this.LU = zza;
        }

        public void zza(T t) {
            this.LU.LQ = t;
            Iterator it = this.LU.LS.iterator();
            while (it.hasNext()) {
                ((zza) it.next()).zzb(this.LU.LQ);
            }
            this.LU.LS.clear();
            this.LU.LR = null;
        }
    }

    private interface zza {
        int getState();

        void zzb(LifecycleDelegate lifecycleDelegate);
    }

    /* renamed from: com.google.android.gms.dynamic.zza$5 */
    class C33165 implements OnClickListener {
        final /* synthetic */ Context zzaky;
        final /* synthetic */ int zzbjz;

        C33165(Context context, int i) {
            this.zzaky = context;
            this.zzbjz = i;
        }

        public void onClick(View view) {
            this.zzaky.startActivity(GooglePlayServicesUtil.zzfb(this.zzbjz));
        }
    }

    /* renamed from: com.google.android.gms.dynamic.zza$6 */
    class C33176 implements zza {
        final /* synthetic */ zza LU;

        C33176(zza zza) {
            this.LU = zza;
        }

        public int getState() {
            return 4;
        }

        public void zzb(LifecycleDelegate lifecycleDelegate) {
            this.LU.LQ.onStart();
        }
    }

    /* renamed from: com.google.android.gms.dynamic.zza$7 */
    class C33187 implements zza {
        final /* synthetic */ zza LU;

        C33187(zza zza) {
            this.LU = zza;
        }

        public int getState() {
            return 5;
        }

        public void zzb(LifecycleDelegate lifecycleDelegate) {
            this.LU.LQ.onResume();
        }
    }

    private void zza(Bundle bundle, zza zza) {
        if (this.LQ != null) {
            zza.zzb(this.LQ);
            return;
        }
        if (this.LS == null) {
            this.LS = new LinkedList();
        }
        this.LS.add(zza);
        if (bundle != null) {
            if (this.LR == null) {
                this.LR = (Bundle) bundle.clone();
            } else {
                this.LR.putAll(bundle);
            }
        }
        zza(this.LT);
    }

    public static void zzb(FrameLayout frameLayout) {
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        CharSequence zzc = zzh.zzc(context, isGooglePlayServicesAvailable, GooglePlayServicesUtil.zzbv(context));
        CharSequence zzh = zzh.zzh(context, isGooglePlayServicesAvailable);
        View linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        View textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(zzc);
        linearLayout.addView(textView);
        if (zzh != null) {
            View button = new Button(context);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(zzh);
            linearLayout.addView(button);
            button.setOnClickListener(new C33165(context, isGooglePlayServicesAvailable));
        }
    }

    private void zzmz(int i) {
        while (!this.LS.isEmpty() && ((zza) this.LS.getLast()).getState() >= i) {
            this.LS.removeLast();
        }
    }

    public void onCreate(final Bundle bundle) {
        zza(bundle, new zza(this) {
            final /* synthetic */ zza LU;

            public int getState() {
                return 1;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.LU.LQ.onCreate(bundle);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        final LayoutInflater layoutInflater2 = layoutInflater;
        final ViewGroup viewGroup2 = viewGroup;
        final Bundle bundle2 = bundle;
        zza(bundle, new zza(this) {
            final /* synthetic */ zza LU;

            public int getState() {
                return 2;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(this.LU.LQ.onCreateView(layoutInflater2, viewGroup2, bundle2));
            }
        });
        if (this.LQ == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.LQ != null) {
            this.LQ.onDestroy();
        } else {
            zzmz(1);
        }
    }

    public void onDestroyView() {
        if (this.LQ != null) {
            this.LQ.onDestroyView();
        } else {
            zzmz(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        zza(bundle2, new zza(this) {
            final /* synthetic */ zza LU;

            public int getState() {
                return 0;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.LU.LQ.onInflate(activity, bundle, bundle2);
            }
        });
    }

    public void onLowMemory() {
        if (this.LQ != null) {
            this.LQ.onLowMemory();
        }
    }

    public void onPause() {
        if (this.LQ != null) {
            this.LQ.onPause();
        } else {
            zzmz(5);
        }
    }

    public void onResume() {
        zza(null, new C33187(this));
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.LQ != null) {
            this.LQ.onSaveInstanceState(bundle);
        } else if (this.LR != null) {
            bundle.putAll(this.LR);
        }
    }

    public void onStart() {
        zza(null, new C33176(this));
    }

    public void onStop() {
        if (this.LQ != null) {
            this.LQ.onStop();
        } else {
            zzmz(4);
        }
    }

    protected void zza(FrameLayout frameLayout) {
        zzb(frameLayout);
    }

    protected abstract void zza(zzf<T> zzf);

    public T zzbcr() {
        return this.LQ;
    }
}
