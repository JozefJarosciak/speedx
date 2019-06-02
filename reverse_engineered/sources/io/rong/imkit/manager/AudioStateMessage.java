package io.rong.imkit.manager;

public class AudioStateMessage {
    public Object obj;
    public int what;

    public static AudioStateMessage obtain() {
        return new AudioStateMessage();
    }
}
