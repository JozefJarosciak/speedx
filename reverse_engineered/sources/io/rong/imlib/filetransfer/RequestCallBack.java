package io.rong.imlib.filetransfer;

public interface RequestCallBack {
    void onCanceled();

    void onComplete(String str);

    void onError(int i);

    void onProgress(int i);
}
