package com.squareup.picasso;

import android.graphics.Bitmap;

public interface Cache {
    public static final Cache NONE = new C43841();

    /* renamed from: com.squareup.picasso.Cache$1 */
    static class C43841 implements Cache {
        C43841() {
        }

        public Bitmap get(String str) {
            return null;
        }

        public void set(String str, Bitmap bitmap) {
        }

        public int size() {
            return 0;
        }

        public int maxSize() {
            return 0;
        }

        public void clear() {
        }

        public void clearKeyUri(String str) {
        }
    }

    void clear();

    void clearKeyUri(String str);

    Bitmap get(String str);

    int maxSize();

    void set(String str, Bitmap bitmap);

    int size();
}
