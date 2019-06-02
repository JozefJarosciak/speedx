package com.beastbikes.android.utils;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.AsyncTask;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Projection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* compiled from: MarkersClusterizer */
/* renamed from: com.beastbikes.android.utils.n */
public class C2568n {
    /* renamed from: a */
    private static MapboxMap f12047a;
    /* renamed from: b */
    private static int f12048b;

    /* compiled from: MarkersClusterizer */
    /* renamed from: com.beastbikes.android.utils.n$a */
    private static class C2567a extends AsyncTask<LinkedHashMap<MarkerOptions, Point>, Void, LinkedHashMap<Point, ArrayList<MarkerOptions>>> {
        private C2567a() {
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12876a((LinkedHashMap[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12877a((LinkedHashMap) obj);
        }

        /* renamed from: a */
        private double m12875a(float f, float f2, float f3, float f4) {
            return Math.sqrt((double) (((f3 - f) * (f3 - f)) + ((f4 - f2) * (f4 - f2))));
        }

        /* renamed from: a */
        protected LinkedHashMap<Point, ArrayList<MarkerOptions>> m12876a(LinkedHashMap<MarkerOptions, Point>... linkedHashMapArr) {
            LinkedHashMap<Point, ArrayList<MarkerOptions>> linkedHashMap = new LinkedHashMap();
            LinkedHashMap linkedHashMap2 = linkedHashMapArr[0];
            for (MarkerOptions markerOptions : linkedHashMap2.keySet()) {
                Point point = (Point) linkedHashMap2.get(markerOptions);
                double d = -1.0d;
                Point point2 = null;
                for (Point point3 : linkedHashMap.keySet()) {
                    Point point32;
                    double a = m12875a((float) point.x, (float) point.y, (float) point32.x, (float) point32.y);
                    if (a > ((double) C2568n.f12048b) || (a >= r4 && r4 != -1.0d)) {
                        point32 = point2;
                    } else {
                        d = a;
                    }
                    point2 = point32;
                }
                if (point2 != null) {
                    ((ArrayList) linkedHashMap.get(point2)).add(markerOptions);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(markerOptions);
                    linkedHashMap.put(point, arrayList);
                }
            }
            return linkedHashMap;
        }

        /* renamed from: a */
        protected void m12877a(LinkedHashMap<Point, ArrayList<MarkerOptions>> linkedHashMap) {
            C2568n.f12047a.removeAnnotations();
            List arrayList = new ArrayList();
            for (Point point : linkedHashMap.keySet()) {
                arrayList.add((MarkerOptions) ((ArrayList) linkedHashMap.get(point)).get(0));
            }
            C2568n.f12047a.addMarkers(arrayList);
        }
    }

    /* renamed from: a */
    public static LinkedHashMap<Point, ArrayList<MarkerOptions>> m12879a(MapboxMap mapboxMap, ArrayList<MarkerOptions> arrayList) throws ExecutionException, InterruptedException {
        return C2568n.m12880a(mapboxMap, arrayList, 25);
    }

    /* renamed from: a */
    public static LinkedHashMap<Point, ArrayList<MarkerOptions>> m12880a(MapboxMap mapboxMap, ArrayList<MarkerOptions> arrayList, int i) throws ExecutionException, InterruptedException {
        f12047a = mapboxMap;
        Projection projection = mapboxMap.getProjection();
        f12048b = i;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MarkerOptions markerOptions = (MarkerOptions) it.next();
            PointF toScreenLocation = projection.toScreenLocation(markerOptions.getPosition());
            linkedHashMap.put(markerOptions, new Point((int) toScreenLocation.x, (int) toScreenLocation.y));
        }
        f12047a.removeAnnotations();
        C2567a c2567a = new C2567a();
        c2567a.execute(new LinkedHashMap[]{linkedHashMap});
        return (LinkedHashMap) c2567a.get();
    }
}
