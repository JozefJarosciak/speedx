package io.rong.imlib.filetransfer;

import io.rong.imlib.filetransfer.FtConst.ServiceType;

public class Configuration {
    public final int connectTimeout;
    public final int readTimeout;
    public final ServiceType serviceType;

    public static class Builder {
        public int connectTimeout;
        public int readTimeout;
        public ServiceType serviceType;

        public Builder serverType(ServiceType serviceType) {
            this.serviceType = serviceType;
            return this;
        }

        public Builder connectTimeout(int i) {
            this.connectTimeout = i;
            return this;
        }

        public Builder readTimeout(int i) {
            this.readTimeout = i;
            return this;
        }

        public Configuration build() {
            return new Configuration();
        }
    }

    private Configuration(Builder builder) {
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.serviceType = builder.serviceType;
    }
}
