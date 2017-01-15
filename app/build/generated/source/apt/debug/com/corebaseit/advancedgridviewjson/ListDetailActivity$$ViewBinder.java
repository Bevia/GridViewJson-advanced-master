// Generated code from Butter Knife. Do not modify!
package com.corebaseit.advancedgridviewjson;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ListDetailActivity$$ViewBinder<T extends ListDetailActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624081, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624081, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624038, "field 'image'");
    target.image = finder.castView(view, 2131624038, "field 'image'");
    view = finder.findRequiredView(source, 2131624084, "field 'imageFullSizeImage'");
    target.imageFullSizeImage = finder.castView(view, 2131624084, "field 'imageFullSizeImage'");
    view = finder.findRequiredView(source, 2131624085, "field 'zoomOutImage'");
    target.zoomOutImage = finder.castView(view, 2131624085, "field 'zoomOutImage'");
    view = finder.findRequiredView(source, 2131624088, "field 'zoomImage'");
    target.zoomImage = finder.castView(view, 2131624088, "field 'zoomImage'");
    view = finder.findRequiredView(source, 2131624089, "field 'txv_row1'");
    target.txv_row1 = finder.castView(view, 2131624089, "field 'txv_row1'");
    view = finder.findRequiredView(source, 2131624090, "field 'txv_row2'");
    target.txv_row2 = finder.castView(view, 2131624090, "field 'txv_row2'");
    view = finder.findRequiredView(source, 2131624083, "field 'fullImagePoetview'");
    target.fullImagePoetview = finder.castView(view, 2131624083, "field 'fullImagePoetview'");
    view = finder.findRequiredView(source, 2131624086, "field 'mainview'");
    target.mainview = finder.castView(view, 2131624086, "field 'mainview'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ListDetailActivity> implements Unbinder {
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
      target.toolbar = null;
      target.image = null;
      target.imageFullSizeImage = null;
      target.zoomOutImage = null;
      target.zoomImage = null;
      target.txv_row1 = null;
      target.txv_row2 = null;
      target.fullImagePoetview = null;
      target.mainview = null;
    }
  }
}
