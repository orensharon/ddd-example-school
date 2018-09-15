package com.sharono.example.ddd.school.application.school.request;


import com.google.gson.annotations.SerializedName;

public class OpenRequest {

    @SerializedName("id") private int schoolId;

    public OpenRequest(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getSchoolId() {
        return schoolId;
    }
}
