package com.heymilo.advisor.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.heymilo.advisor.R;
import com.heymilo.advisor.model.Product;
import com.heymilo.advisor.ui.BannerFragment;
import com.heymilo.advisor.ui.StringFragment;
import com.heymilo.advisor.util.LogUtils;
import java.lang.InterruptedException;
import java.lang.Thread;
import java.util.List;

public class BannerSlider extends RelativeLayout {

    @InjectView(R.id.pager) ViewPager mPager;
    @InjectView(R.id.txt_title) TextView mTitleView;

    Context context;
    LayoutInflater mInflater;
    List<Product> products;

    private ViewPagerAdapter mAdapter;
    private int currentPosition = 0;
    private AutoScrollThread mThread;

    public BannerSlider(Context context) {
	this(context, null);
    }

    public BannerSlider(Context context, AttributeSet attrs) {
	super(context, attrs);
	this.context = context;
	initView();
    }

    public void initView() {
	mInflater = LayoutInflater.from(context);
	mInflater.inflate(R.layout.view_banner_slider, this, true);
	ButterKnife.inject(this);
    }

    public void setProducts(FragmentManager fm, List<Product> products) {
	setVisibility(View.VISIBLE);
	this.products = products;
	if (products == null || products.size() == 0) {
	    setVisibility(View.GONE);
	    return;
	}

	mAdapter = new ViewPagerAdapter(fm);
	mPager.setAdapter(mAdapter);
	mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
		    currentPosition = position;
		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	    });

	mThread = null;
	mThread = new AutoScrollThread();
	mThread.start();
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            // return BannerFragment.newInstance(products.get(position));
	    Product p1 = position * 3 < products.size() ? products.get(position * 3) : null;
	    Product p2 = position * 3 + 1 < products.size() ? products.get(position * 3 + 1) : null;
	    Product p3 = position * 3 + 2 < products.size() ? products.get(position * 3 + 2) : null;
            return BannerFragment.newInstance(p1, p2, p3);
        }

        @Override
        public int getCount() {
            // return products.size();
            return products.size() / 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
	    // return products.get(position).getName();
	    return "position " + position;
        }
    }

    class AutoScrollThread extends Thread {
	private boolean mActive = false;

	public void run() {
	    mActive = true;
	    while(mActive) {
		doThings();
	    }
	}

	public void terminate() {
	    mActive = false;
	}

	private void doThings() {
	    try {
		Thread.sleep(8000);
	    } catch (InterruptedException ignore) { }

	    mHandler.sendEmptyMessage(0);
	}
    }

    @Override
    public void onDetachedFromWindow() {
	if (mThread != null)
	    mThread.terminate();
    }

    Handler mHandler = new Handler() {
	    @Override
	    public void handleMessage(Message msg) {
		mPager.setCurrentItem((currentPosition+1)%products.size());
	    }
	};

    public int getCurrentItem() {
	return mPager.getCurrentItem();
    }

    public void setTitle(String title) {
	mTitleView.setText(title);
    }
}
