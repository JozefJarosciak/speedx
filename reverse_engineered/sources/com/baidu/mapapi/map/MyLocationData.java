package com.baidu.mapapi.map;

public class MyLocationData {
    public final float accuracy;
    public final float direction;
    public final double latitude;
    public final double longitude;
    public final int satellitesNum;
    public final float speed;

    public static class Builder {
        /* renamed from: a */
        private double f3069a;
        /* renamed from: b */
        private double f3070b;
        /* renamed from: c */
        private float f3071c;
        /* renamed from: d */
        private float f3072d;
        /* renamed from: e */
        private float f3073e;
        /* renamed from: f */
        private int f3074f;

        public Builder accuracy(float f) {
            this.f3073e = f;
            return this;
        }

        public MyLocationData build() {
            return new MyLocationData(this.f3069a, this.f3070b, this.f3071c, this.f3072d, this.f3073e, this.f3074f);
        }

        public Builder direction(float f) {
            this.f3072d = f;
            return this;
        }

        public Builder latitude(double d) {
            this.f3069a = d;
            return this;
        }

        public Builder longitude(double d) {
            this.f3070b = d;
            return this;
        }

        public Builder satellitesNum(int i) {
            this.f3074f = i;
            return this;
        }

        public Builder speed(float f) {
            this.f3071c = f;
            return this;
        }
    }

    MyLocationData(double d, double d2, float f, float f2, float f3, int i) {
        this.latitude = d;
        this.longitude = d2;
        this.speed = f;
        this.direction = f2;
        this.accuracy = f3;
        this.satellitesNum = i;
    }
}
