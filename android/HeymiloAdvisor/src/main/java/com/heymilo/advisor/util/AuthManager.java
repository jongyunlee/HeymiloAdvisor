package com.heymilo.advisor.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.heymilo.advisor.Constants;
import java.util.StringTokenizer;
import com.heymilo.advisor.model.UserInfo;

public class AuthManager {

    public static final String KEY_SESSION_PREFERENCE = "keys2";
    public static final String keys2Token = "keys2Token";
    private static String token;
    // private static UserInfo info;

    public static String getToken(Context context) {
	if (TextUtils.isEmpty(token)) {
	    token = getStringValue(context, keys2Token);
	}
	return token;
    }

    public static void setToken(Context context, String t) {
	token = t;
	saveStringValue(context, keys2Token, t);
    }

    public static boolean isLoggedIn(Context context) {
	if (!TextUtils.isEmpty(getToken(context))) {
	    return true;
	}
	return false;
    }

    public static void logout(Context context) {
	token = "";
	// info = null;
	setToken(context, "");
    }

    public static UserInfo getUserInfo(Context context) {
	token = getToken(context);
	LogUtils.common("AuthManager", "token : " + token);
	UserInfo info = (UserInfo)GsonUtils.getGsonObject().fromJson(new String(Base64.decode(token.split("\\.")[1], 0)), UserInfo.class);
	return info;
    }

    public static String getAuthHeader(Context context) {
	return "Authorization: " + "Bearer " + getToken(context);
    }

    public static SharedPreferences getPrefs(Context context) {
	return context.getSharedPreferences(KEY_SESSION_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static void saveStringValue(Context context, String name, String value) {
	if (value != null) {
	    SharedPreferences.Editor editor = getPrefs(context).edit();
	    editor.putString(name, value);
	    editor.commit();
	}
    }

    public static void saveIntegerValue(Context context, String name, int value) {
	SharedPreferences.Editor editor = getPrefs(context).edit();
	editor.putInt(name, value);
	editor.commit();
    }

    public static void saveBooleanValue(Context context, String name, boolean value) {
	SharedPreferences.Editor editor = getPrefs(context).edit();
	editor.putBoolean(name, value);
	editor.commit();
    }

    public static String getStringValue(Context context, String name) {
	return getPrefs(context).getString(name, "");
    }

    public static int getIntegerValue(Context context, String name) {
	return getPrefs(context).getInt(name, 0);
    }

    public static boolean getBooleanValue(Context context, String name) {
	return getPrefs(context).getBoolean(name, true);
    }
}
