package com.sharono.example.ddd.school.application.school.request;


import com.google.gson.annotations.SerializedName;

public class SchoolRequest {

    @SerializedName("id") private int schoolId;

    public SchoolRequest(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getSchoolId() {
        return schoolId;
    }
}
