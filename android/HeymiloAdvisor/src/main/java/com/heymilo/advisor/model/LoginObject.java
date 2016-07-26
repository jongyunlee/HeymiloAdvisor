package com.heymilo.advisor.model;

import com.google.gson.annotations.SerializedName;
import java.sql.Timestamp;

public class LoginObject {

    @SerializedName("token") private String token;
    @SerializedName("success") private boolean success;
    @SerializedName("message") private String message;

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }


    public boolean getSuccess() {
	return success;
    }

    public void setSuccess(boolean success) {
	this.success = success;
    }


    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }
}
