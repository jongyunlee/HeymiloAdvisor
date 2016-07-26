// Generated code from Butter Knife. Do not modify!
package com.heymilo.advisor.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class BannerFragment$$ViewInjector<T extends com.heymilo.advisor.ui.BannerFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689627, "field 'mProductView1'");
    target.mProductView1 = finder.castView(view, 2131689627, "field 'mProductView1'");
    view = finder.findRequiredView(source, 2131689628, "field 'mProductView2'");
    target.mProductView2 = finder.castView(view, 2131689628, "field 'mProductView2'");
    view = finder.findRequiredView(source, 2131689629, "field 'mProductView3'");
    target.mProductView3 = finder.castView(view, 2131689629, "field 'mProductView3'");
  }

  @Override public void reset(T target) {
    target.mProductView1 = null;
    target.mProductView2 = null;
    target.mProductView3 = null;
  }
}
