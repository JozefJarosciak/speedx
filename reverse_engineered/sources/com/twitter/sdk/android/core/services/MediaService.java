package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.models.C1512c;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

public interface MediaService {
    @POST("/1.1/media/upload.json")
    @Multipart
    void upload(@Part("media") TypedFile typedFile, @Part("media_data") TypedFile typedFile2, @Part("additional_owners") TypedString typedString, C4580e<C1512c> c4580e);
}
