package com.heymilo.advisor.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import com.heymilo.advisor.R;
import com.heymilo.advisor.util.SearchViewUtils;

public class HomeTabFragment extends TabsFragment {

    private BaseActivity mActivity;
    private String[] titles;
    private SearchViewUtils mSearchViewUtils;

    @Override
    public String[] getTabTitles() {
	return titles;
    }

    @Override
    public Fragment getTabFragment(int position) {
	Fragment fragment;
	switch(position) {
	case 0:
	    return HomeFragment.newInstance();
	}
	return StringFragment.newInstance(titles[position]);
    }

    public static HomeTabFragment newInstance() {
	HomeTabFragment f = new HomeTabFragment();
	return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	mActivity = (BaseActivity) getActivity();
	titles = new String[4];
	titles[0] = "홈";
	titles[1] = "리뷰";
	titles[2] = "성분";
	titles[3] = "블로그";
	setupSearchView();
    }

    public void setupSearchView() {
	setHasOptionsMenu(true);
	mSearchViewUtils= new SearchViewUtils(mActivity);
	mSearchViewUtils.setSearchViewUtils(new SearchViewUtils.SearchViewListener() {
		@Override
		public void onSuggestionClick(String item) {

		}

		@Override
		public boolean onQueryTextSubmit(String query) {
		    // ((HomeActivity)mActivity).changeFragmentToBackStack(SearchUserFragment.newInstance(query, false, ""), "search");
		    return true;
		}
	    });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	mSearchViewUtils.onCreateOptionsMenu(menu, inflater);
    }
}
