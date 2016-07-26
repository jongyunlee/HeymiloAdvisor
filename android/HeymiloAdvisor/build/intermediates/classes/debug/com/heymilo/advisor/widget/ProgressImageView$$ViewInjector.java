// Generated code from Butter Knife. Do not modify!
package com.heymilo.advisor.widget;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ProgressImageView$$ViewInjector<T extends com.heymilo.advisor.widget.ProgressImageView> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689699, "field 'mImageView'");
    target.mImageView = finder.castView(view, 2131689699, "field 'mImageView'");
    view = finder.findRequiredView(source, 2131689700, "field 'mProgressBar'");
    target.mProgressBar = finder.castView(view, 2131689700, "field 'mProgressBar'");
  }

  @Override public void reset(T target) {
    target.mImageView = null;
    target.mProgressBar = null;
  }
}
