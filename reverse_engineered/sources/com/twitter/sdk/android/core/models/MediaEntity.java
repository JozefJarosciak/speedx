package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MediaEntity extends UrlEntity {
    @SerializedName("id")
    public final long id;
    @SerializedName("id_str")
    public final String idStr;
    @SerializedName("media_url")
    public final String mediaUrl;
    @SerializedName("media_url_https")
    public final String mediaUrlHttps;
    @SerializedName("sizes")
    public final Sizes sizes;
    @SerializedName("source_status_id")
    public final long sourceStatusId;
    @SerializedName("source_status_id_str")
    public final String sourceStatusIdStr;
    @SerializedName("type")
    public final String type;
    @SerializedName("video_info")
    public final VideoInfo videoInfo;

    public static class Size implements Serializable {
        @SerializedName("h")
        /* renamed from: h */
        public final int f7086h;
        @SerializedName("resize")
        public final String resize;
        @SerializedName("w")
        /* renamed from: w */
        public final int f7087w;

        public Size(int i, int i2, String str) {
            this.f7087w = i;
            this.f7086h = i2;
            this.resize = str;
        }
    }

    public static class Sizes implements Serializable {
        @SerializedName("large")
        public final Size large;
        @SerializedName("medium")
        public final Size medium;
        @SerializedName("small")
        public final Size small;
        @SerializedName("thumb")
        public final Size thumb;

        public Sizes(Size size, Size size2, Size size3, Size size4) {
            this.thumb = size;
            this.small = size2;
            this.medium = size3;
            this.large = size4;
        }
    }

    @Deprecated
    public MediaEntity(String str, String str2, String str3, int i, int i2, long j, String str4, String str5, String str6, Sizes sizes, long j2, String str7, String str8) {
        this(str, str2, str3, i, i2, j, str4, str5, str6, sizes, j2, str7, str8, null);
    }

    public MediaEntity(String str, String str2, String str3, int i, int i2, long j, String str4, String str5, String str6, Sizes sizes, long j2, String str7, String str8, VideoInfo videoInfo) {
        super(str, str2, str3, i, i2);
        this.id = j;
        this.idStr = str4;
        this.mediaUrl = str5;
        this.mediaUrlHttps = str6;
        this.sizes = sizes;
        this.sourceStatusId = j2;
        this.sourceStatusIdStr = str7;
        this.type = str8;
        this.videoInfo = videoInfo;
    }
}
