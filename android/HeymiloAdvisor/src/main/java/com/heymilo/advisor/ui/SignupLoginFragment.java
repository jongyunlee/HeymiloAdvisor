package com.heymilo.advisor.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.heymilo.advisor.R;
import com.heymilo.advisor.task.LoginTask;
import com.heymilo.advisor.task.LoginTask.LoginTaskListener;
import com.heymilo.advisor.model.LoginObject;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.heymilo.advisor.ui.BaseDialogFragment.DialogClickListener;
import com.heymilo.advisor.util.AuthManager;

public class SignupLoginFragment extends DialogFragment {

    @InjectView(R.id.root) RelativeLayout rootView;
    @InjectView(R.id.btn_signup) Button mSignupButton;
    @InjectView(R.id.edit_email) EditText mEmailEdit;
    @InjectView(R.id.edit_password) EditText mPasswordEdit;
    @InjectView(R.id.btn_login) Button mLoginButton;

    private BaseActivity mActivity;
    private Dialog dialog;
    private LoginTask mLoginTask;

    public static SignupLoginFragment newInstance() {
	SignupLoginFragment f = new SignupLoginFragment();
	return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	mActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_signup_login, null);
	ButterKnife.inject(this, v);

	rootView.setOnClickListener(onButtonsClick);
	mSignupButton.setOnClickListener(onButtonsClick);
	mLoginButton.setOnClickListener(onButtonsClick);
	return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
	dialog = new Dialog(mActivity, android.R.style.Theme_Translucent_NoTitleBar);
        return dialog;
    }

    OnClickListener onButtonsClick = new OnClickListener() {
	    @Override
	    public void onClick(View v) {
		switch(v.getId()) {
		case R.id.root:
		    dismiss();
		    break;
		case R.id.btn_signup:
		    dismiss();
		    showSignupDialog();
		    break;
		case R.id.btn_login:
		    login();
		    break;
		}
	    }
	};

    public void showSignupDialog() {
	FragmentTransaction ft = getFragmentManager().beginTransaction();
	Fragment prev = getFragmentManager().findFragmentByTag("dialog");
	if (prev != null) {
	    ft.remove(prev);
	}
	ft.addToBackStack(null);
	SignupFragment newFragment
	    = SignupFragment.newInstance();
	newFragment.show(ft, "dialog");
    }

    public void login() {
	mLoginTask = new LoginTask(mActivity, mEmailEdit.getText().toString(), mPasswordEdit.getText().toString());
	mLoginTask.setLoginTaskListener(new LoginTaskListener() {
		@Override
		public void onSuccess(LoginObject result) {
		    AuthManager.setToken(mActivity, result.getToken());
		    dismiss();
		    ((HomeActivity)mActivity).refreshAuthInfo();
		}

		@Override
		public void onFailure(LoginObject result) {
		    if (result != null) {
			showDialogMessage(result.getMessage());
		    } else {
			showDialogMessage("NETWORK ERROR");
		    }
		}
	    });
	mLoginTask.execute();
    }

    @Override
    public void onPause() {
	if (mLoginTask != null) {
	    mLoginTask.cancel(true);
	}
	super.onPause();
    }



    public void showDialogMessage(String message) {
	FragmentTransaction ft = getFragmentManager().beginTransaction();
	Fragment prev = getFragmentManager().findFragmentByTag("dialog");
	if (prev != null) {
	    ft.remove(prev);
	}
	ft.addToBackStack(null);
	BaseDialogFragment newFragment
	    = BaseDialogFragment.newInstance("",
					     message,
					     false);
	newFragment.show(ft, "dialog");
    }
}
