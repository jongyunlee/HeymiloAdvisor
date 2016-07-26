package com.heymilo.advisor.task;

import android.content.Context;
import com.heymilo.advisor.ApiPaths;
import com.heymilo.advisor.model.LoginObject;
import com.heymilo.advisor.model.LoginParams;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.Query;

public class LoginTask extends BaseAsyncTask<LoginObject> {

    private Context mContext;
    private String userId;
    private String password;

    private LoginTaskListener mListener;

    public interface LoginTaskListener {
	public void onSuccess(LoginObject result);
	public void onFailure(LoginObject result);
    }

    public void setLoginTaskListener(LoginTaskListener listener) {
	mListener = listener;
    }

    public LoginTask(Context context, String userId, String password) {
	super(context);
	mContext = context;
	this.userId = userId;
	this.password = password;
    }

    public interface LoginObjectTaskApi {
	@POST(ApiPaths.LOGIN_PATH)
	LoginObject create(@Body LoginParams params);
    }

    @Override
	protected LoginObject doInBackground(Void... args) {
	RestAdapter restAdapter = getRestAdapter();
	LoginObjectTaskApi register = restAdapter.create(LoginObjectTaskApi.class);
	LoginObject result = null;
	try {
	    result = register.create(new LoginParams(userId, password));
	} catch (RetrofitError error) {
	}
	return result;
    }

    @Override
	public void loadFinished(LoginObject result) {
	if (mListener != null && result != null) {
	    mListener.onSuccess(result);
	} else {
	    mListener.onFailure(result);
	}
    }

    @Override
	public String getLoadingMessage() {
	return "로딩중...";
    }

    @Override
	public String getLoadingId() {
	return "loading";
    }
}
