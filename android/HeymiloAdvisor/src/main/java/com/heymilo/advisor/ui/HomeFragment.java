package com.heymilo.advisor.ui;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.heymilo.advisor.R;
import com.heymilo.advisor.model.Product;
import com.heymilo.advisor.util.AuthManager;
import com.heymilo.advisor.widget.BannerSlider;
import com.heymilo.advisor.widget.ProductView;
import java.util.ArrayList;

public class HomeFragment extends BaseFragment {

    @InjectView(R.id.slider) BannerSlider mSlider;
    @InjectView(R.id.product1) ProductView mProductView1;
    @InjectView(R.id.product2) ProductView mProductView2;
    @InjectView(R.id.product3) ProductView mProductView3;

    private BaseActivity mActivity;

    public static HomeFragment newInstance() {
	HomeFragment f = new HomeFragment();
	return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	mActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_home, null);
	ButterKnife.inject(this, v);

	ArrayList<Product> products = new ArrayList<Product>();
	products.add(new Product("Origen Adult Grain", 103000, 4.5f, "http://www.orijen.ca/wp-content/uploads/2013/03/adult-dog-177x300.png", 5));
	products.add(new Product("Wellness CORE Grain", 69000, 5, "https://d1rgby1m7uuvjr.cloudfront.net/is/catalog/49161._AC_SL1500_V1453242685_.jpg", 3));
	products.add(new Product("Natural Balance L.I.D. Limited Ingredient Diets Sweet Potato & Venison Formula", 65000, 4, "https://d1rgby1m7uuvjr.cloudfront.net/is/catalog/76781_MAIN._AC_SL1500_V1460478783_.jpg", 5));
	FragmentManager fm = getChildFragmentManager();
	mSlider.setProducts(fm, products);
	// mSlider.setTitle("인기제품\n\"" + AuthManager.getUserNickname(mActivity) + "\"와 비슷한 아이들에게 인기있는 제품을 찾아보세요!");

	mProductView1.setProduct(new Product("Origen Adult Grain", 103000, 4.5f, "http://www.orijen.ca/wp-content/uploads/2013/03/adult-dog-177x300.png", 5));
	mProductView2.setProduct(new Product("Wellness CORE Grain", 69000, 5, "https://d1rgby1m7uuvjr.cloudfront.net/is/catalog/49161._AC_SL1500_V1453242685_.jpg", 3));
	mProductView3.setProduct(new Product("Natural Balance L.I.D. Limited Ingredient Diets Sweet Potato & Venison Formula", 65000, 4, "https://d1rgby1m7uuvjr.cloudfront.net/is/catalog/76781_MAIN._AC_SL1500_V1460478783_.jpg", 5));
	mProductView1.setOnClickListener(onButtonsClick);
	mProductView2.setOnClickListener(onButtonsClick);
	mProductView3.setOnClickListener(onButtonsClick);
	return v;
    }

    OnClickListener onButtonsClick = new OnClickListener() {
	    @Override
	    public void onClick(View v) {
		Intent intent;
		switch(v.getId()) {
		case R.id.product1:
		    intent = new Intent(mActivity, ProductDetailActivity.class);
		    startActivity(intent);
		    break;
		case R.id.product2:
		    intent = new Intent(mActivity, ProductDetailActivity.class);
		    startActivity(intent);
		    break;
		case R.id.product3:
		    intent = new Intent(mActivity, ProductDetailActivity.class);
		    startActivity(intent);
		    break;
		}
	    }
	};
}
