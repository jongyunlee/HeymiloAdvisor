package com.heymilo.advisor.model;

import com.google.gson.annotations.SerializedName;
import java.lang.Cloneable;
import java.sql.Timestamp;

public class UserInfo implements Cloneable {

    @SerializedName("user_id") private String userId;

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
