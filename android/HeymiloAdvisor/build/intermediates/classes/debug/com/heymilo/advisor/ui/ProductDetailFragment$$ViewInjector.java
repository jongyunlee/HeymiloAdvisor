// Generated code from Butter Knife. Do not modify!
package com.heymilo.advisor.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ProductDetailFragment$$ViewInjector<T extends com.heymilo.advisor.ui.ProductDetailFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689642, "field 'mImageView'");
    target.mImageView = finder.castView(view, 2131689642, "field 'mImageView'");
    view = finder.findRequiredView(source, 2131689643, "field 'mNameView'");
    target.mNameView = finder.castView(view, 2131689643, "field 'mNameView'");
    view = finder.findRequiredView(source, 2131689644, "field 'mRatingBar'");
    target.mRatingBar = finder.castView(view, 2131689644, "field 'mRatingBar'");
    view = finder.findRequiredView(source, 2131689645, "field 'mPriceView'");
    target.mPriceView = finder.castView(view, 2131689645, "field 'mPriceView'");
    view = finder.findRequiredView(source, 2131689646, "field 'mIngredientsView'");
    target.mIngredientsView = finder.castView(view, 2131689646, "field 'mIngredientsView'");
  }

  @Override public void reset(T target) {
    target.mImageView = null;
    target.mNameView = null;
    target.mRatingBar = null;
    target.mPriceView = null;
    target.mIngredientsView = null;
  }
}
