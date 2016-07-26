package com.heymilo.advisor.ui;

import android.content.Intent;
import com.heymilo.advisor.R;
import com.heymilo.advisor.ui.BaseFragment;
import com.heymilo.advisor.ui.BaseFragmentActivity;

public class SignupActivity extends BaseFragmentActivity {

    public static final int SIGNUP_SNS = 2;

    @Override
    public BaseFragment getFragment() {
	return StringFragment.newInstance("Signup");
    }

}
