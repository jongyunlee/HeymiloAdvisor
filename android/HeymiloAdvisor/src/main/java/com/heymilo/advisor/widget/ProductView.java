package com.heymilo.advisor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.heymilo.advisor.R;
import com.heymilo.advisor.model.Product;

public class ProductView extends RelativeLayout {

    LayoutInflater mInflater;
    private Context context;

    ProgressImageView imageView;
    TextView nameView;
    TextView reviewCountView;
    RatingBar ratingBar;

    public ProductView(Context context) {
	super(context);
	this.context = context;
    }

    public ProductView(Context context, AttributeSet attrs) {
	super(context, attrs);
	this.context = context;
	parseAttrs(context, attrs);
	initView();
    }

    public void parseAttrs(Context context, AttributeSet attrs) {

    }

    public void initView() {
	mInflater = LayoutInflater.from(context);
	mInflater.inflate(R.layout.view_product, this, true);
	imageView = (ProgressImageView) findViewById(R.id.img);
	nameView = (TextView) findViewById(R.id.txt_name);
	reviewCountView = (TextView) findViewById(R.id.txt_review_count);
	ratingBar = (RatingBar) findViewById(R.id.rating);
    }

    public void setProduct(Product product) {
	if (product == null) {
	    setVisibility(View.GONE);
	    return;
	} else {
	    setVisibility(View.VISIBLE);
	}
	imageView.displayImage(product.getImage());
	nameView.setText(product.getName());
	reviewCountView.setText("(" + product.getReviewCount() + ")");
	ratingBar.setRating(product.getStar());
    }
}
