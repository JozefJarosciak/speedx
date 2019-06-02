package io.rong.imlib.filetransfer;

public class Call {
    private final CallDispatcher dispatcher;
    private final Request request;
    private final RequestCallBack requestCallBack;
    private final Object tag;

    class AsyncCall implements Runnable {
        AsyncCall() {
        }

        public Object tag() {
            return Call.this.request.tag;
        }

        public void cancelDirectly() {
            Call.this.requestCallBack.onCanceled();
        }

        public void cancel() {
            Call.this.request.cancel();
        }

        public void run() {
            try {
                Call.this.request.sendRequest();
            } catch (Exception e) {
                Call.this.requestCallBack.onError(30002);
                e.printStackTrace();
            } finally {
                Call.this.dispatcher.finish(this);
            }
        }
    }

    private Call(CallDispatcher callDispatcher, Request request, RequestCallBack requestCallBack) {
        this.request = request;
        this.requestCallBack = requestCallBack;
        this.tag = request.tag;
        this.dispatcher = callDispatcher;
    }

    public static Call create(CallDispatcher callDispatcher, Request request, RequestCallBack requestCallBack) {
        return new Call(callDispatcher, request, requestCallBack);
    }

    public void enqueue() {
        this.dispatcher.enqueue(new AsyncCall());
    }
}
