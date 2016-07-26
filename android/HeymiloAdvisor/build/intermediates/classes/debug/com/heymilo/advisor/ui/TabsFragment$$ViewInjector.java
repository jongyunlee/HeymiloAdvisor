// Generated code from Butter Knife. Do not modify!
package com.heymilo.advisor.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class TabsFragment$$ViewInjector<T extends com.heymilo.advisor.ui.TabsFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689661, "field 'mTabHost'");
    target.mTabHost = finder.castView(view, 2131689661, "field 'mTabHost'");
    view = finder.findRequiredView(source, 2131689660, "field 'pager'");
    target.pager = finder.castView(view, 2131689660, "field 'pager'");
  }

  @Override public void reset(T target) {
    target.mTabHost = null;
    target.pager = null;
  }
}
