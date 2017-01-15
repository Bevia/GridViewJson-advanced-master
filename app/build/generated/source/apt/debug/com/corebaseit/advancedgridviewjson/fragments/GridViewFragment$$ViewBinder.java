// Generated code from Butter Knife. Do not modify!
package com.corebaseit.advancedgridviewjson.fragments;

import android.content.res.Resources;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import java.lang.SuppressWarnings;

public class GridViewFragment$$ViewBinder<T extends GridViewFragment> implements ViewBinder<T> {
  @Override
  @SuppressWarnings("ResourceType")
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624128, "field 'gridView'");
    target.gridView = finder.castView(view, 2131624128, "field 'gridView'");
    Resources res = finder.getContext(source).getResources();
    target.TAG_POETS_JSON = res.getString(2131230794);
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends GridViewFragment> implements Unbinder {
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
      target.gridView = null;
    }
  }
}
