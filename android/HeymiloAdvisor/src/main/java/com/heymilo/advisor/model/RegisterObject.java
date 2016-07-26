package com.heymilo.advisor.model;

import com.google.gson.annotations.SerializedName;
import java.sql.Timestamp;

public class RegisterObject {

    @SerializedName("success") private boolean success;
    @SerializedName("message") private String message;
    @SerializedName("token") private String token;

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }


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
}
