package com.mapbox.mapboxsdk.maps;

class CompassViewSettings extends ViewSettings {
    private boolean fadeFacingNorth = true;

    public boolean isFadeFacingNorth() {
        return this.fadeFacingNorth;
    }

    public void setFadeFacingNorth(boolean z) {
        this.fadeFacingNorth = z;
    }
}
