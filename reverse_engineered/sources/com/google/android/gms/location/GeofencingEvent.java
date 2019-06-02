package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeofencingEvent {
    private final int acP;
    private final List<Geofence> acQ;
    private final Location acR;
    private final int zzbym;

    private GeofencingEvent(int i, int i2, List<Geofence> list, Location location) {
        this.zzbym = i;
        this.acP = i2;
        this.acQ = list;
        this.acR = location;
    }

    public static GeofencingEvent fromIntent(Intent intent) {
        return intent == null ? null : new GeofencingEvent(intent.getIntExtra("gms_error_code", -1), zzv(intent), zzw(intent), (Location) intent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
    }

    private static int zzv(Intent intent) {
        int intExtra = intent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
        return intExtra == -1 ? -1 : (intExtra == 1 || intExtra == 2 || intExtra == 4) ? intExtra : -1;
    }

    private static List<Geofence> zzw(Intent intent) {
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        if (arrayList == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(ParcelableGeofence.zzaa((byte[]) it.next()));
        }
        return arrayList2;
    }

    public int getErrorCode() {
        return this.zzbym;
    }

    public int getGeofenceTransition() {
        return this.acP;
    }

    public List<Geofence> getTriggeringGeofences() {
        return this.acQ;
    }

    public Location getTriggeringLocation() {
        return this.acR;
    }

    public boolean hasError() {
        return this.zzbym != -1;
    }
}
