// Generated code from Butter Knife. Do not modify!
package com.heymilo.advisor.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SignupLoginFragment$$ViewInjector<T extends com.heymilo.advisor.ui.SignupLoginFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689647, "field 'rootView'");
    target.rootView = finder.castView(view, 2131689647, "field 'rootView'");
    view = finder.findRequiredView(source, 2131689654, "field 'mSignupButton'");
    target.mSignupButton = finder.castView(view, 2131689654, "field 'mSignupButton'");
    view = finder.findRequiredView(source, 2131689633, "field 'mEmailEdit'");
    target.mEmailEdit = finder.castView(view, 2131689633, "field 'mEmailEdit'");
    view = finder.findRequiredView(source, 2131689634, "field 'mPasswordEdit'");
    target.mPasswordEdit = finder.castView(view, 2131689634, "field 'mPasswordEdit'");
    view = finder.findRequiredView(source, 2131689658, "field 'mLoginButton'");
    target.mLoginButton = finder.castView(view, 2131689658, "field 'mLoginButton'");
  }

  @Override public void reset(T target) {
    target.rootView = null;
    target.mSignupButton = null;
    target.mEmailEdit = null;
    target.mPasswordEdit = null;
    target.mLoginButton = null;
  }
}
