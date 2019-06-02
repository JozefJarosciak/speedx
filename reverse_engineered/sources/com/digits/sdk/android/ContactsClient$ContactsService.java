package com.digits.sdk.android;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

interface ContactsClient$ContactsService {
    @POST("/1.1/contacts/destroy/all.json")
    void deleteAll(C2929p<Response> c2929p);

    @POST("/1.1/contacts/upload.json")
    bx upload(@Body by byVar);

    @GET("/1.1/contacts/users_and_uploaded_by.json")
    void usersAndUploadedBy(@Query("next_cursor") String str, @Query("count") Integer num, C2929p<Object> c2929p);
}
