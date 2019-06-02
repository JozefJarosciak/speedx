package ch.qos.logback.core.util;

class CharSequenceState {
    /* renamed from: c */
    final char f14c;
    int occurrences = 1;

    public CharSequenceState(char c) {
        this.f14c = c;
    }

    void incrementOccurrences() {
        this.occurrences++;
    }
}
