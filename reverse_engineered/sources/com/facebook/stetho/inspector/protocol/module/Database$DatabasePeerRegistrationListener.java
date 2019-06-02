package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import java.util.List;

class Database$DatabasePeerRegistrationListener implements PeerRegistrationListener {
    private final List<Database$DatabaseDriver> mDatabaseDrivers;

    private Database$DatabasePeerRegistrationListener(List<Database$DatabaseDriver> list) {
        this.mDatabaseDrivers = list;
    }

    public void onPeerRegistered(JsonRpcPeer jsonRpcPeer) {
        for (Database$DatabaseDriver access$300 : this.mDatabaseDrivers) {
            access$300.onRegistered(jsonRpcPeer);
        }
    }

    public void onPeerUnregistered(JsonRpcPeer jsonRpcPeer) {
        for (Database$DatabaseDriver access$400 : this.mDatabaseDrivers) {
            access$400.onUnregistered(jsonRpcPeer);
        }
    }
}
