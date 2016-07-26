package com.heymilo.advisor.ui;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.heymilo.advisor.R;
import com.heymilo.advisor.model.Product;
import com.heymilo.advisor.util.StringFormatter;
import com.heymilo.advisor.widget.ProgressImageView;

public class ProductDetailFragment extends BaseFragment {

    @InjectView(R.id.img) ProgressImageView mImageView;
    @InjectView(R.id.txt_name) TextView mNameView;
    @InjectView(R.id.rating) RatingBar mRatingBar;
    @InjectView(R.id.txt_price) TextView mPriceView;
    @InjectView(R.id.txt_ingredients) TextView mIngredientsView;

    private BaseActivity mActivity;
    private Product product;

    public static ProductDetailFragment newInstance() {
	ProductDetailFragment f = new ProductDetailFragment();
	return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	mActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_product_detail, null);
	ButterKnife.inject(this, v);
	product = new Product("Origen Adult Grain", 103000, 4.5f, "http://www.orijen.ca/wp-content/uploads/2013/03/adult-dog-177x300.png", 5, "뼈없는 닭, 닭 고기, 닭 간, 청어, 뼈없는 칠면조, 칠면조 고기, <font color=\"#0000ff\">칠면조 간</font>, 계란, 연어, 닭 심장, 닭 연골, 닭 간 지방, 빨간 렌틸콩, 완두콩, 녹색 렌틸콩, <font color=\"#ff0000\">건조 알팔파</font>, 참마, <font color=\"#ff0000\">완두콩 섬유</font>, 병아리 콩, 호박, 버터호두호박, 시금치 잎, 당근, 빨간사과, 배, 크랜베리, 블루 베리, 다시마, 감초, 당귀, 호로 파, 천수국, 회향, 박하 잎, 카모마일, 민들레, 여름 야채, 로즈마리, 장구균, 비타민 A, 비타민 D3, 비타민 E, 니아신, 리보플라빈, 엽산, 비오틴, 비타민 B12, 아연 단백질, 철 단백질, 망간 단백질, 구리 단백질, 셀레늄 효모");
	mImageView.displayImage(product.getImage());
	mNameView.setText(product.getName());
	mRatingBar.setRating(product.getStar());
	mPriceView.setText(StringFormatter.formatPrice(product.getPrice()));
	mIngredientsView.setText(Html.fromHtml(product.getIngredients()));
	return v;
    }
}
