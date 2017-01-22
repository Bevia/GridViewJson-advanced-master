// Generated code from Butter Knife. Do not modify!
package com.corebaseit.advancedgridviewjson.adapters;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class GridViewAdapter$ViewHolder$$ViewBinder<T extends GridViewAdapter.ViewHolder> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624038, "field 'imageView'");
    target.imageView = finder.castView(view, 2131624038, "field 'imageView'");
    view = finder.findRequiredView(source, 2131624135, "field 'name'");
    target.name = finder.castView(view, 2131624135, "field 'name'");
    view = finder.findRequiredView(source, 2131624138, "field 'poem1'");
    target.poem1 = finder.castView(view, 2131624138, "field 'poem1'");
    view = finder.findRequiredView(source, 2131624140, "field 'poem2'");
    target.poem2 = finder.castView(view, 2131624140, "field 'poem2'");
    view = finder.findRequiredView(source, 2131624142, "field 'poem3'");
    target.poem3 = finder.castView(view, 2131624142, "field 'poem3'");
    view = finder.findRequiredView(source, 2131624139, "field 'actor1'");
    target.actor1 = finder.castView(view, 2131624139, "field 'actor1'");
    view = finder.findRequiredView(source, 2131624141, "field 'actor2'");
    target.actor2 = finder.castView(view, 2131624141, "field 'actor2'");
    view = finder.findRequiredView(source, 2131624143, "field 'actor3'");
    target.actor3 = finder.castView(view, 2131624143, "field 'actor3'");
    view = finder.findRequiredView(source, 2131624136, "field 'bio'");
    target.bio = finder.castView(view, 2131624136, "field 'bio'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends GridViewAdapter.ViewHolder> implements Unbinder {
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
      target.imageView = null;
      target.name = null;
      target.poem1 = null;
      target.poem2 = null;
      target.poem3 = null;
      target.actor1 = null;
      target.actor2 = null;
      target.actor3 = null;
      target.bio = null;
    }
  }
}
