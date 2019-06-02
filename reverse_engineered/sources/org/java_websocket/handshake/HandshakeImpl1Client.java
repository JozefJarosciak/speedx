package org.java_websocket.handshake;

import org.slf4j.Marker;

public class HandshakeImpl1Client extends HandshakedataImpl1 implements ClientHandshakeBuilder {
    private String resourceDescriptor = Marker.ANY_MARKER;

    public void setResourceDescriptor(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("http resource descriptor must not be null");
        }
        this.resourceDescriptor = str;
    }

    public String getResourceDescriptor() {
        return this.resourceDescriptor;
    }
}
