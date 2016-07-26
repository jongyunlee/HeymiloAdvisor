package com.heymilo.advisor.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.heymilo.advisor.model.RegisterObject;
import com.heymilo.advisor.task.RegisterTask.RegisterTaskListener;
import com.heymilo.advisor.task.RegisterTask;
import com.heymilo.advisor.ui.BaseDialogFragment.DialogClickListener;
import com.heymilo.advisor.util.AuthManager;

public class SignupFragment extends DialogFragment {

    @InjectView(R.id.root) RelativeLayout rootView;
    @InjectView(R.id.btn_cancel) Button mCancelButton;
    @InjectView(R.id.btn_signup) Button mSignupButton;
    @InjectView(R.id.edit_email) EditText mEmailEdit;
    @InjectView(R.id.edit_password) EditText mPasswordEdit;

    private BaseActivity mActivity;
    private Dialog dialog;
    private RegisterTask mRegisterTask;

    public static SignupFragment newInstance() {
	SignupFragment f = new SignupFragment();
	return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	mActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_signup, null);
	ButterKnife.inject(this, v);

	rootView.setOnClickListener(onButtonsClick);
	mCancelButton.setOnClickListener(onButtonsClick);
	mSignupButton.setOnClickListener(onButtonsClick);
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
		case R.id.btn_cancel:
		    dismiss();
		    break;
		case R.id.btn_signup:
		    signup();
		    break;
		}
	    }
	};

    public void signup() {
	mRegisterTask = new RegisterTask(mActivity,
					 mEmailEdit.getText().toString(),
					 mPasswordEdit.getText().toString());
	mRegisterTask.setRegisterTaskListener(new RegisterTaskListener() {
		@Override
		public void onSuccess(RegisterObject result) {
		    AuthManager.setToken(mActivity, result.getToken());
		    ((HomeActivity)mActivity).refreshAuthInfo();
		    dismiss();
		}

		@Override
		public void onFailure(RegisterObject result) {
		    if (result != null) {
			showDialogMessage(result.getMessage());
		    } else {
			showDialogMessage("Network ERROR");
		    }
		}
	    });
	mRegisterTask.execute();
    }

    @Override
    public void onPause() {
	if (mRegisterTask != null) {
	    mRegisterTask.cancel(true);
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
