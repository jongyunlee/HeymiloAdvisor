package com.heymilo.advisor.ui;

import android.content.Intent;
import com.heymilo.advisor.R;
import com.heymilo.advisor.ui.BaseFragment;
import com.heymilo.advisor.ui.BaseFragmentActivity;

public class ProductDetailActivity extends BaseFragmentActivity {

    @Override
    public BaseFragment getFragment() {
	return ProductDetailFragment.newInstance();
    }

}
