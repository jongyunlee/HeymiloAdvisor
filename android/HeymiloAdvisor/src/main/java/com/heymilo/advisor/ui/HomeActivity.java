package com.heymilo.advisor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.heymilo.advisor.R;
import com.heymilo.advisor.util.AuthManager;
import com.heymilo.advisor.ui.BaseDialogFragment.DialogClickListener;

public class HomeActivity extends BaseFragmentActivity {

    @InjectView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @InjectView(R.id.nav_view) NavigationView mNavigationView; // 왼쪽에서 나오는 뷰
    @InjectView(R.id.btn_logout) LinearLayout mLogoutButton;
    @InjectView(R.id.btn_login) LinearLayout mLoginButton;
    @InjectView(R.id.txt_username) TextView mUserNameView;

    @Override
    protected int getLayoutResources() {
	return R.layout.activity_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
	ButterKnife.inject(this);

	initActionBar();
	initDrawerContent();
	refreshAuthInfo();
    }

    @Override
    public BaseFragment getFragment() {
        return HomeTabFragment.newInstance();
    }

    private void initActionBar() {
	toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);

	final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
	actionBar.setLogo(R.drawable.logo_home);
	actionBar.setDisplayUseLogoEnabled(true);
	actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch(item.getItemId()) {
	case android.R.id.home:
	    mDrawerLayout.openDrawer(GravityCompat.START); // 왼쪽에서 네비게이션 드로워가 나옴
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    private void initDrawerContent() {
	mLoginButton.setOnClickListener(onButtonsClick);
	mLogoutButton.setOnClickListener(onButtonsClick);
	mNavigationView.setItemIconTintList(null);
	mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(MenuItem menuItem) {
		    mDrawerLayout.closeDrawers();
		    setActionBarTitle(menuItem.getTitle().toString());
		    Toast.makeText(HomeActivity.this, "menuItem : " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
		    return true;
		}
	    });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	return true;
    }

    @Override
    public void onBackPressed() {
	if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
	    mDrawerLayout.closeDrawer(GravityCompat.START);
	    return;
	}
	super.onBackPressed();
    }

    public void refreshAuthInfo() {
	if (AuthManager.isLoggedIn(this)) {
	    mLogoutButton.setVisibility(View.VISIBLE);
	    mLoginButton.setVisibility(View.GONE);
	    mUserNameView.setText(AuthManager.getUserInfo(this).getUserId());
	} else {
	    mLogoutButton.setVisibility(View.GONE);
	    mLoginButton.setVisibility(View.VISIBLE);
	}
    }

    OnClickListener onButtonsClick = new OnClickListener() {
	    @Override
	    public void onClick(View v) {
		Intent intent;
		switch(v.getId()) {
		case R.id.btn_logout:
		    // AuthManager.logout(HomeActivity.this);
		    showLogoutDialog();
		    break;
		case R.id.btn_login:
		    showLoginDialog();
		    break;
		}
	    }
	};

    public void showLoginDialog() {
	FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
	if (prev != null) {
	    ft.remove(prev);
	}
	ft.addToBackStack(null);
	SignupLoginFragment newFragment
	    = SignupLoginFragment.newInstance();
	newFragment.show(ft, "dialog");
    }

    public void showLogoutDialog() {
	FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
	if (prev != null) {
	    ft.remove(prev);
	}
	ft.addToBackStack(null);
	BaseDialogFragment newFragment
	    = BaseDialogFragment.newInstance("",
					     "로그아웃 하시겠습니까?",
					     false);
	newFragment.setDialogClickListener(new DialogClickListener() {
		@Override
		public void onOk() {
		    AuthManager.logout(HomeActivity.this);
		    refreshAuthInfo();
		}

		@Override
		public void onCancel() {

		}
	    });
	newFragment.show(ft, "dialog");
    }
}
