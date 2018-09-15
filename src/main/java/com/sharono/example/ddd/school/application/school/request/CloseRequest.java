package com.sharono.example.ddd.school.application.school.request;


import com.google.gson.annotations.SerializedName;

public class CloseRequest {

    @SerializedName("id") private int schoolId;

    public CloseRequest(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getSchoolId() {
        return schoolId;
    }
}
