// Generated code from Butter Knife. Do not modify!
package com.corebaseit.advancedgridviewjson;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MainActivity$$ViewBinder<T extends MainActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624095, "field 'tabLayout'");
    target.tabLayout = finder.castView(view, 2131624095, "field 'tabLayout'");
    view = finder.findRequiredView(source, 2131624081, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624081, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624096, "field 'viewPager'");
    target.viewPager = finder.castView(view, 2131624096, "field 'viewPager'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends MainActivity> implements Unbinder {
    private T target;

    protected InnerUnbinder(T target) {
      this.target = target;
    }

    @Override
    public final void unbind() {
      if (target == null) throw new IllegalStateException("Bindings already cleared.");
      unbind(target);
      target = null;
    }

    protected void unbind(T target) {
      target.tabLayout = null;
      target.toolbar = null;
      target.viewPager = null;
    }
  }
}
