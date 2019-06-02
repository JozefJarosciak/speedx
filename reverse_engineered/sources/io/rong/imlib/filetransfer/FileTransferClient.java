package io.rong.imlib.filetransfer;

import android.text.TextUtils;
import io.rong.imlib.filetransfer.FtConst.ServiceType;

public class FileTransferClient {
    private static FileTransferClient sInstance;
    private Configuration configuration;
    private CallDispatcher dispatcher = new CallDispatcher();

    private FileTransferClient(Configuration configuration) {
        this.configuration = configuration;
    }

    public static void init(Configuration configuration) {
        sInstance = new FileTransferClient(configuration);
    }

    public static FileTransferClient getInstance() {
        return sInstance;
    }

    public void upload(String str, String str2, RequestOption requestOption) {
        Request request = null;
        if (this.configuration.serviceType == ServiceType.QI_NIU) {
            request = new QiniuRequest(this.configuration, requestOption.getRequestCallBack());
            request.token = str2;
            request.mimeType = requestOption.getMimeType();
            request.method = "POST";
            request.serverIp = requestOption.getServerIp();
            request.url = str;
            request.tag = str;
            request.fileName = requestOption.getFileName();
        }
        Call.create(this.dispatcher, request, requestOption.getRequestCallBack()).enqueue();
    }

    public void download(String str, RequestOption requestOption) {
        Request request = null;
        if (this.configuration.serviceType == ServiceType.QI_NIU) {
            request = new QiniuRequest(this.configuration, requestOption.getRequestCallBack());
            request.mimeType = requestOption.getMimeType();
            request.method = "GET";
            request.url = str;
            request.tag = str;
            request.fileName = requestOption.getFileName();
        }
        Call.create(this.dispatcher, request, requestOption.getRequestCallBack()).enqueue();
    }

    public void cancel(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.dispatcher.cancel(str);
        }
    }
}
