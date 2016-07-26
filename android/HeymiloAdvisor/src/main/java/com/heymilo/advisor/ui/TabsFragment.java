package com.heymilo.advisor.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.ButterKnife;
import com.astuetz.PagerSlidingTabStrip;
import com.heymilo.advisor.R;

public abstract class TabsFragment extends BaseFragment {

    @InjectView(R.id.tabs) public PagerSlidingTabStrip mTabHost;
    @InjectView(R.id.pager) public ViewPager pager;

    private BaseActivity mActivity;
    private ViewPagerAdapter pagerAdapter;
    private int currentPagerPosition = 0;
    private LinearLayout mTabsLinearLayout;
    public abstract String[] getTabTitles();
    public abstract Fragment getTabFragment(int position);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	mActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_tabs, null);
	ButterKnife.inject(this, v);

	// if (mActivity instanceof HomeActivity)
	//     ((HomeActivity)mActivity).setToolbarOverlay(false);

	initTabs();
	return v;
    }

    public void initTabs() {
	pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        pager.setAdapter(pagerAdapter);

	mTabHost.setShouldExpand(true);
	mTabHost.setViewPager(pager);

	mTabsLinearLayout = ((LinearLayout)mTabHost.getChildAt(0));
	for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
	    TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
	    if(i == 0){
		tv.setTextColor(0xFFFFFFFF);
	    } else {
		tv.setTextColor(0xFFCFCFCF);
	    }
	    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
	    tv.setPadding(0, 0, 0, 0);
	    tv.setLayoutParams(params);
	}

        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
		@Override
		public void onPageSelected(int position) {
		    currentPagerPosition = position;
		    for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
			TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
			if(i == position){
			    tv.setTextColor(0xFFFFFFFF);
			} else {
			    tv.setTextColor(0xFFCFCFCF);
			}
		    }
		}
	    });

	pager.setCurrentItem(currentPagerPosition);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

	String[] titles;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
	    titles = getTabTitles();
        }

        public Fragment getItem(int position) {
            return getTabFragment(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
	    return titles[position];
        }
    }

    public void refresh() {

    }
}
