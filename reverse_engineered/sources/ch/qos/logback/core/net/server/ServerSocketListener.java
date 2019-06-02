package ch.qos.logback.core.net.server;

import ch.qos.logback.core.util.CloseUtil;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public abstract class ServerSocketListener<T extends Client> implements ServerListener<T> {
    private final ServerSocket serverSocket;

    public ServerSocketListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    private String socketAddressToString(SocketAddress socketAddress) {
        String obj = socketAddress.toString();
        int indexOf = obj.indexOf("/");
        return indexOf >= 0 ? obj.substring(indexOf + 1) : obj;
    }

    public T acceptClient() throws IOException {
        Socket accept = this.serverSocket.accept();
        return createClient(socketAddressToString(accept.getRemoteSocketAddress()), accept);
    }

    public void close() {
        CloseUtil.closeQuietly(this.serverSocket);
    }

    protected abstract T createClient(String str, Socket socket) throws IOException;

    public String toString() {
        return socketAddressToString(this.serverSocket.getLocalSocketAddress());
    }
}
