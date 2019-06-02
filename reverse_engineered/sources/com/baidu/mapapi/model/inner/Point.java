package com.baidu.mapapi.model.inner;

import java.io.Serializable;

public class Point implements Serializable {
    /* renamed from: x */
    public int f3293x;
    /* renamed from: y */
    public int f3294y;

    public Point(int i, int i2) {
        this.f3293x = i;
        this.f3294y = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point point = (Point) obj;
        return this.f3293x != point.f3293x ? false : this.f3294y == point.f3294y;
    }

    public int getmPtx() {
        return this.f3293x;
    }

    public int getmPty() {
        return this.f3294y;
    }

    public int hashCode() {
        return ((this.f3293x + 31) * 31) + this.f3294y;
    }

    public void setmPtx(int i) {
        this.f3293x = i;
    }

    public void setmPty(int i) {
        this.f3294y = i;
    }

    public String toString() {
        return "Point [x=" + this.f3293x + ", y=" + this.f3294y + "]";
    }
}
