package com.sharono.example.ddd.school.application.school;

import com.sharono.example.ddd.school.application.IGateway;
import com.sharono.example.ddd.school.application.school.request.*;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class SchoolGateway implements IGateway {

    private ISchoolAPI schoolAPI;

    public SchoolGateway(Retrofit retrofit) {
        if (retrofit == null) {
            throw new NullPointerException();
        }
        this.schoolAPI = retrofit.create(ISchoolAPI.class);
    }

    public Observable<SchoolResponse> getSchool(SchoolRequest schoolRequest) {
        return this.schoolAPI.getSchool(schoolRequest.getSchoolId())
                .subscribeOn(Schedulers.io());
    }

    public Completable open(OpenRequest request) {
        return this.schoolAPI.open(request)
                .onErrorResumeNext(this::handleOpenError)
                .subscribeOn(Schedulers.io())
                .ignoreElements();
    }

    public Completable close(CloseRequest request) {
        return this.schoolAPI.close(request)
                .onErrorResumeNext(this::handleCloseError)
                .subscribeOn(Schedulers.io())
                .ignoreElements();
    }

    private Observable<CloseResponse> handleCloseError(Throwable throwable) {
        throw new RuntimeException(throwable);
    }

    private Observable<OpenResponse> handleOpenError(Throwable throwable) {
        throw new RuntimeException(throwable);
    }
}
