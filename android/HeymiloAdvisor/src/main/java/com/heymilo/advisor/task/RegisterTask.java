package com.heymilo.advisor.task;

import android.content.Context;
import com.heymilo.advisor.ApiPaths;
import com.heymilo.advisor.model.RegisterObject;
import com.heymilo.advisor.model.RegisterParams;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.Body;

public class RegisterTask extends BaseAsyncTask<RegisterObject> {

    private Context mContext;
    private String userId;
    private String password;

    private RegisterTaskListener mListener;

    public interface RegisterTaskListener {
	public void onSuccess(RegisterObject result);
	public void onFailure(RegisterObject result);
    }

    public void setRegisterTaskListener(RegisterTaskListener listener) {
	mListener = listener;
    }

    public RegisterTask(Context context, String userId, String password) {
	super(context);
	mContext = context;
	this.userId = userId;
	this.password = password;
    }

    public interface RegisterObjectTaskApi {
	@POST(ApiPaths.REGISTER_PATH)
	RegisterObject create(@Body RegisterParams params);
    }

    @Override
	protected RegisterObject doInBackground(Void... args) {
	RestAdapter restAdapter = getRestAdapter();
	RegisterObjectTaskApi register = restAdapter.create(RegisterObjectTaskApi.class);
	RegisterObject result = null;
	try {
	    result = register.create(new RegisterParams(userId, password));
	} catch (RetrofitError error) {
	}
	return result;
    }

    @Override
	public void loadFinished(RegisterObject result) {
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
