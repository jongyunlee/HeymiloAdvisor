package com.heymilo.advisor.util;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.SearchView.OnSuggestionListener;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.heymilo.advisor.R;
import com.heymilo.advisor.ui.BaseActivity;

public class SearchViewUtils {

    BaseActivity mActivity;
    SearchViewListener mListener;
    String[] searchWords;
    SimpleCursorAdapter mSearchAdapter;

    public interface SearchViewListener {
	public void onSuggestionClick(String item);
	public boolean onQueryTextSubmit(String query);
    }

    public void setSearchViewUtils(SearchViewListener listener) {
	mListener = listener;
    }

    public SearchViewUtils(BaseActivity activity) {
	mActivity = activity;
    }

    public void setSearchWords(String[] searchWords) {
	this.searchWords = searchWords;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	inflater.inflate(R.menu.search, menu);
	MenuItem item = menu.findItem(R.id.action_search);
	final SearchView sv = new SearchView(mActivity.getSupportActionBar().getThemedContext());
	MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
	MenuItemCompat.setActionView(item, sv);
	sv.setOnQueryTextListener(new OnQueryTextListener() {
		@Override
		public boolean onQueryTextSubmit(String query) {
		    if (mListener != null)
			mListener.onQueryTextSubmit(query);
		    return false;
		}

		@Override
		public boolean onQueryTextChange(String newText) {
		    String[] columnNames = {"_id","text"};
		    MatrixCursor cursor = new MatrixCursor(columnNames);
		    String[] temp = new String[2];
		    int id = 0;
		    if (searchWords == null) return false;
		    for(String item : searchWords){
			temp[0] = Integer.toString(id++);
			temp[1] = item;
			if (HangulUtils.matchString(item, newText)) {
			    cursor.addRow(temp);
			}
		    }
		    String[] from = {"text"};
		    int[] to = {R.id.txt_title};
		    if (mSearchAdapter == null) {
			mSearchAdapter = new SimpleCursorAdapter(mActivity, R.layout.item_search, cursor, from, to);
		    } else {
			mSearchAdapter.changeCursor(cursor);
		    }
		    sv.setSuggestionsAdapter(mSearchAdapter);
		    sv.setOnSuggestionListener(new OnSuggestionListener() {

			    @Override
			    public boolean onSuggestionClick(int position) {
				Cursor cur = (Cursor)mSearchAdapter.getItem(position);
				String selectedItem = cur.getString(1);
				if (mListener != null)
				    mListener.onSuggestionClick(selectedItem);
				return false;
			    }

			    @Override
			    public boolean onSuggestionSelect(int position) {
				return false;
			    }
			});
		    return false;
		}
	    });
    }

}
