// Generated code from Butter Knife. Do not modify!
package com.heymilo.advisor.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class HomeActivity$$ViewInjector<T extends com.heymilo.advisor.ui.HomeActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689612, "field 'mDrawerLayout'");
    target.mDrawerLayout = finder.castView(view, 2131689612, "field 'mDrawerLayout'");
    view = finder.findRequiredView(source, 2131689618, "field 'mNavigationView'");
    target.mNavigationView = finder.castView(view, 2131689618, "field 'mNavigationView'");
    view = finder.findRequiredView(source, 2131689679, "field 'mLogoutButton'");
    target.mLogoutButton = finder.castView(view, 2131689679, "field 'mLogoutButton'");
    view = finder.findRequiredView(source, 2131689658, "field 'mLoginButton'");
    target.mLoginButton = finder.castView(view, 2131689658, "field 'mLoginButton'");
    view = finder.findRequiredView(source, 2131689681, "field 'mUserNameView'");
    target.mUserNameView = finder.castView(view, 2131689681, "field 'mUserNameView'");
  }

  @Override public void reset(T target) {
    target.mDrawerLayout = null;
    target.mNavigationView = null;
    target.mLogoutButton = null;
    target.mLoginButton = null;
    target.mUserNameView = null;
  }
}
