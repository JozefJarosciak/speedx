package io.rong.message;

public final class StickerMessage$Builder {
    private String category = null;
    private boolean isInstalled = false;
    private String name = null;

    public StickerMessage$Builder category(String str) {
        this.category = str;
        return this;
    }

    public StickerMessage$Builder name(String str) {
        this.name = str;
        return this;
    }

    public StickerMessage$Builder isInstalled(boolean z) {
        this.isInstalled = z;
        return this;
    }

    public StickerMessage build() {
        return new StickerMessage(this, null);
    }
}
