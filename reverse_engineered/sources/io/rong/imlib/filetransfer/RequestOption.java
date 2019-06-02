package io.rong.imlib.filetransfer;

import io.rong.imlib.filetransfer.FtConst.MimeType;

public class RequestOption {
    private int end;
    private String fileName;
    private MimeType mimeType;
    private RequestCallBack requestCallBack;
    private String serverIp;
    private int start;

    public RequestOption(String str, MimeType mimeType, RequestCallBack requestCallBack) {
        this.fileName = str;
        this.mimeType = mimeType;
        this.requestCallBack = requestCallBack;
    }

    public RequestOption(String str, MimeType mimeType, String str2, RequestCallBack requestCallBack) {
        this.fileName = str;
        this.mimeType = mimeType;
        this.serverIp = str2;
        this.requestCallBack = requestCallBack;
    }

    public RequestOption(int i, int i2, MimeType mimeType, String str, RequestCallBack requestCallBack) {
        this.start = i;
        this.end = i2;
        this.mimeType = mimeType;
        this.serverIp = str;
        this.requestCallBack = requestCallBack;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public MimeType getMimeType() {
        return this.mimeType;
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public RequestCallBack getRequestCallBack() {
        return this.requestCallBack;
    }

    public String getFileName() {
        return this.fileName;
    }
}
