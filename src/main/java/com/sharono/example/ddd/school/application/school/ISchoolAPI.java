package com.sharono.example.ddd.school.application.school;

import com.sharono.example.ddd.school.application.school.request.*;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ISchoolAPI {

    @GET("school")
    Observable<SchoolResponse> getSchool(@Header("id") int id);

    @POST("school")
    Observable<OpenResponse> open(@Body OpenRequest request);

    @POST("school")
    Observable<CloseResponse> close(@Body CloseRequest request);
}
