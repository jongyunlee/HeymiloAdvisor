package com.heymilo.advisor.ui;

import com.heymilo.advisor.R;

public class AgreeActivity extends BaseFragmentActivity {
    @Override
    public BaseFragment getFragment() {
	int type = getIntent().getIntExtra("TYPE", 1);
	return AgreeFragment.newInstance(type);
    }
}
