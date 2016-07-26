package com.heymilo.advisor.model;

import com.google.gson.annotations.SerializedName;
import java.sql.Timestamp;

public class RegisterParams {

    @SerializedName("user_id") private String userId;
    @SerializedName("password") private String password;
    public RegisterParams(String userId, String password) {
	this.userId = userId;
	this.password = password;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }


    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }
}
