// Generated code from Butter Knife. Do not modify!
package com.heymilo.advisor.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SignupFragment$$ViewInjector<T extends com.heymilo.advisor.ui.SignupFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689647, "field 'rootView'");
    target.rootView = finder.castView(view, 2131689647, "field 'rootView'");
    view = finder.findRequiredView(source, 2131689653, "field 'mCancelButton'");
    target.mCancelButton = finder.castView(view, 2131689653, "field 'mCancelButton'");
    view = finder.findRequiredView(source, 2131689654, "field 'mSignupButton'");
    target.mSignupButton = finder.castView(view, 2131689654, "field 'mSignupButton'");
    view = finder.findRequiredView(source, 2131689633, "field 'mEmailEdit'");
    target.mEmailEdit = finder.castView(view, 2131689633, "field 'mEmailEdit'");
    view = finder.findRequiredView(source, 2131689634, "field 'mPasswordEdit'");
    target.mPasswordEdit = finder.castView(view, 2131689634, "field 'mPasswordEdit'");
  }

  @Override public void reset(T target) {
    target.rootView = null;
    target.mCancelButton = null;
    target.mSignupButton = null;
    target.mEmailEdit = null;
    target.mPasswordEdit = null;
  }
}
