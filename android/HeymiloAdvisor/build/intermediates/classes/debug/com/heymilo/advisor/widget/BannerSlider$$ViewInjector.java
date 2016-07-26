// Generated code from Butter Knife. Do not modify!
package com.heymilo.advisor.widget;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class BannerSlider$$ViewInjector<T extends com.heymilo.advisor.widget.BannerSlider> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689660, "field 'mPager'");
    target.mPager = finder.castView(view, 2131689660, "field 'mPager'");
    view = finder.findRequiredView(source, 2131689631, "field 'mTitleView'");
    target.mTitleView = finder.castView(view, 2131689631, "field 'mTitleView'");
  }

  @Override public void reset(T target) {
    target.mPager = null;
    target.mTitleView = null;
  }
}
