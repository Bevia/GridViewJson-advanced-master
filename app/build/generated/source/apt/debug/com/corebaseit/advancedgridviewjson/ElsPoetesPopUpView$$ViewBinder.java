// Generated code from Butter Knife. Do not modify!
package com.corebaseit.advancedgridviewjson;

import android.content.res.Resources;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import java.lang.SuppressWarnings;

public class ElsPoetesPopUpView$$ViewBinder<T extends ElsPoetesPopUpView> implements ViewBinder<T> {
  @Override
  @SuppressWarnings("ResourceType")
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624162, "field 'image1'");
    target.image1 = finder.castView(view, 2131624162, "field 'image1'");
    view = finder.findRequiredView(source, 2131624163, "field 'textUpperSide1'");
    target.textUpperSide1 = finder.castView(view, 2131624163, "field 'textUpperSide1'");
    view = finder.findRequiredView(source, 2131624164, "field 'textUpperSide2'");
    target.textUpperSide2 = finder.castView(view, 2131624164, "field 'textUpperSide2'");
    view = finder.findRequiredView(source, 2131624139, "field 'textViewActor1'");
    target.textViewActor1 = finder.castView(view, 2131624139, "field 'textViewActor1'");
    view = finder.findRequiredView(source, 2131624141, "field 'textViewActor2'");
    target.textViewActor2 = finder.castView(view, 2131624141, "field 'textViewActor2'");
    view = finder.findRequiredView(source, 2131624165, "field 'textViewActor3'");
    target.textViewActor3 = finder.castView(view, 2131624165, "field 'textViewActor3'");
    view = finder.findRequiredView(source, 2131624138, "field 'textviewPoem1' and method 'click1'");
    target.textviewPoem1 = finder.castView(view, 2131624138, "field 'textviewPoem1'");
    unbinder.view2131624138 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click1();
      }
    });
    view = finder.findRequiredView(source, 2131624140, "field 'textviewPoem2' and method 'click2'");
    target.textviewPoem2 = finder.castView(view, 2131624140, "field 'textviewPoem2'");
    unbinder.view2131624140 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click2();
      }
    });
    view = finder.findRequiredView(source, 2131624142, "field 'textviewPoem3' and method 'click3'");
    target.textviewPoem3 = finder.castView(view, 2131624142, "field 'textviewPoem3'");
    unbinder.view2131624142 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click3();
      }
    });
    Resources res = finder.getContext(source).getResources();
    target.EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_1 = res.getString(2131230790);
    target.EXTRA_PASS_IMAGE_DATA_TO_POPUPVIEW_1 = res.getString(2131230787);
    target.EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_2 = res.getString(2131230788);
    target.EXTRA_PASS_JSON_POEM_1 = res.getString(2131230775);
    target.EXTRA_PASS_JSON_POEM_2 = res.getString(2131230776);
    target.EXTRA_PASS_JSON_POEM_3 = res.getString(2131230777);
    target.TAG_NUMER_OF_FIRST_POEM = res.getString(2131230791);
    target.TAG_NUMER_OF_SECOND_POEM = res.getString(2131230792);
    target.TAG_NUMER_OF_THIRD_POEM = res.getString(2131230793);
    target.EXTRA_PASS_JSON_POEM_ACTOR_1 = res.getString(2131230778);
    target.EXTRA_PASS_JSON_POEM_ACTOR_2 = res.getString(2131230779);
    target.EXTRA_PASS_JSON_POEM_ACTOR_3 = res.getString(2131230780);
    target.EXTRA_POETS_SECTION_PASS_NAME_OF_POET = res.getString(2131230784);
    target.EXTRA_POETS_SECTION_PASS_POEM_VIDEO = res.getString(2131230785);
    target.EXTRA_POETS_SECTION_PASS_JSON_NAME_POEM = res.getString(2131230782);
    target.EXTRA_POETS_SECTION_PASS_JSON_POEM = res.getString(2131230783);
    target.EXTRA_POETS_SECTION_PASS_IMAGE = res.getString(2131230781);
    target.EXTRA_POETS_SECTION_PASS_READER_NAME = res.getString(2131230786);
    target.EXTRA_FROM_FRAGMENT = res.getString(2131230771);
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ElsPoetesPopUpView> implements Unbinder {
    private T target;

    View view2131624138;

    View view2131624140;

    View view2131624142;

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
      target.image1 = null;
      target.textUpperSide1 = null;
      target.textUpperSide2 = null;
      target.textViewActor1 = null;
      target.textViewActor2 = null;
      target.textViewActor3 = null;
      view2131624138.setOnClickListener(null);
      target.textviewPoem1 = null;
      view2131624140.setOnClickListener(null);
      target.textviewPoem2 = null;
      view2131624142.setOnClickListener(null);
      target.textviewPoem3 = null;
    }
  }
}
