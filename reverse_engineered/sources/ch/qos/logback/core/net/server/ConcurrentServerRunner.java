package ch.qos.logback.core.net.server;

import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ConcurrentServerRunner<T extends Client> extends ContextAwareBase implements ServerRunner<T>, Runnable {
    private final Collection<T> clients = new ArrayList();
    private final Lock clientsLock = new ReentrantLock();
    private final Executor executor;
    private final ServerListener<T> listener;
    private boolean running;

    /* renamed from: ch.qos.logback.core.net.server.ConcurrentServerRunner$1 */
    class C03481 implements ClientVisitor<T> {
        C03481() {
        }

        public void visit(T t) {
            t.close();
        }
    }

    private class ClientWrapper implements Client {
        private final T delegate;

        public ClientWrapper(T t) {
            this.delegate = t;
        }

        public void close() {
            this.delegate.close();
        }

        public void run() {
            ConcurrentServerRunner.this.addClient(this.delegate);
            try {
                this.delegate.run();
            } finally {
                ConcurrentServerRunner.this.removeClient(this.delegate);
            }
        }
    }

    public ConcurrentServerRunner(ServerListener<T> serverListener, Executor executor) {
        this.listener = serverListener;
        this.executor = executor;
    }

    private void addClient(T t) {
        this.clientsLock.lock();
        try {
            this.clients.add(t);
        } finally {
            this.clientsLock.unlock();
        }
    }

    private Collection<T> copyClients() {
        this.clientsLock.lock();
        try {
            Collection<T> arrayList = new ArrayList(this.clients);
            return arrayList;
        } finally {
            this.clientsLock.unlock();
        }
    }

    private void removeClient(T t) {
        this.clientsLock.lock();
        try {
            this.clients.remove(t);
        } finally {
            this.clientsLock.unlock();
        }
    }

    public void accept(ClientVisitor<T> clientVisitor) {
        for (Client client : copyClients()) {
            try {
                clientVisitor.visit(client);
            } catch (RuntimeException e) {
                addError(client + ": " + e);
            }
        }
    }

    protected abstract boolean configureClient(T t);

    public boolean isRunning() {
        return this.running;
    }

    public void run() {
        setRunning(true);
        try {
            addInfo("listening on " + this.listener);
            while (!Thread.currentThread().isInterrupted()) {
                Client acceptClient = this.listener.acceptClient();
                if (configureClient(acceptClient)) {
                    try {
                        this.executor.execute(new ClientWrapper(acceptClient));
                    } catch (RejectedExecutionException e) {
                        addError(acceptClient + ": connection dropped");
                        acceptClient.close();
                    }
                } else {
                    addError(acceptClient + ": connection dropped");
                    acceptClient.close();
                }
            }
        } catch (InterruptedException e2) {
        } catch (Exception e3) {
            addError("listener: " + e3);
        }
        setRunning(false);
        addInfo("shutting down");
        this.listener.close();
    }

    protected void setRunning(boolean z) {
        this.running = z;
    }

    public void stop() throws IOException {
        this.listener.close();
        accept(new C03481());
    }
}
