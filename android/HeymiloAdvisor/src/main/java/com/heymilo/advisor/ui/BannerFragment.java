package com.heymilo.advisor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.heymilo.advisor.R;
import com.heymilo.advisor.model.Product;
import com.heymilo.advisor.widget.ProductView;

public class BannerFragment extends BaseFragment {

    @InjectView(R.id.product1) ProductView mProductView1;
    @InjectView(R.id.product2) ProductView mProductView2;
    @InjectView(R.id.product3) ProductView mProductView3;

    private BaseActivity mActivity;
    Product p1;
    Product p2;
    Product p3;

    public void setProducts(Product p1, Product p2, Product p3) {
	this.p1 = p1;
	this.p2 = p2;
	this.p3 = p3;
    }

    public static BannerFragment newInstance(Product p1, Product p2, Product p3) {
	BannerFragment f = new BannerFragment();
	f.setProducts(p1, p2, p3);
	return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	mActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_banner, null);
	ButterKnife.inject(this, v);
	mProductView1.setProduct(p1);
	mProductView2.setProduct(p2);
	mProductView3.setProduct(p3);
	return v;
    }
}
