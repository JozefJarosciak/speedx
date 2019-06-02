package io.rong.imlib.navigation;

public class LocationConfig {
    private boolean configure;
    private int[] conversationTypes;
    private int distanceFilter;
    private int maxParticipant;
    private int refreshInterval;

    public boolean isConfigure() {
        return this.configure;
    }

    public void setConfigure(boolean z) {
        this.configure = z;
    }

    public int[] getConversationTypes() {
        return this.conversationTypes;
    }

    public void setConversationTypes(int[] iArr) {
        this.conversationTypes = iArr;
    }

    public int getMaxParticipant() {
        return this.maxParticipant;
    }

    public void setMaxParticipant(int i) {
        this.maxParticipant = i;
    }

    public int getRefreshInterval() {
        return this.refreshInterval;
    }

    public void setRefreshInterval(int i) {
        this.refreshInterval = i;
    }

    public int getDistanceFilter() {
        return this.distanceFilter;
    }

    public void setDistanceFilter(int i) {
        this.distanceFilter = i;
    }
}
