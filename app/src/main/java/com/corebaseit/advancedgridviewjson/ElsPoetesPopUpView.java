package com.corebaseit.advancedgridviewjson;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vincent on 10/2/16.
 */
public class ElsPoetesPopUpView extends Activity {

    @BindView(R.id.pupUpImage)
    ImageView image1;
    @BindView(R.id.textUpperSide1)
    TextView textUpperSide1;
    @BindView(R.id.textUpperSide2)
    TextView textUpperSide2;
    @BindView(R.id.textView2linear1LSVRBottomBottom)
    TextView textViewActor1;
    @BindView(R.id.textView4linear1LSVRBottomBottom)
    TextView textViewActor2;
    @BindView(R.id.textView5linear1LSVRBottomBottom)
    TextView textViewActor3;
    @BindView(R.id.textView1linear1LSVRBottomUp)
    TextView textviewPoem1;
    @BindView(R.id.textView3linear1LSVRBottomUp)
    TextView textviewPoem2;
    @BindView(R.id.textView5linear1LSVRBottomUp)
    TextView textviewPoem3;

    @BindString(R.string.POPUPVIEW_1)
    String EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_1;
    @BindString(R.string.IMAGE_DATA_TO_POPUPVIEW_1)
    String EXTRA_PASS_IMAGE_DATA_TO_POPUPVIEW_1;
    @BindString(R.string.JSON_DATA_TO_POPUPVIEW_2)
    String EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_2;
    //Pasing 3 (MAX) POEMS:
    @BindString(R.string.EXTRA_PASS_JSON_POEM_1)
    String EXTRA_PASS_JSON_POEM_1;
    @BindString(R.string.EXTRA_PASS_JSON_POEM_2)
    String EXTRA_PASS_JSON_POEM_2;
    @BindString(R.string.EXTRA_PASS_JSON_POEM_3)
    String EXTRA_PASS_JSON_POEM_3;
    @BindString(R.string.TAG_NUMER_OF_FIRST_POEM)
    String TAG_NUMER_OF_FIRST_POEM;
    @BindString(R.string.TAG_NUMER_OF_SECOND_POEM)
    String TAG_NUMER_OF_SECOND_POEM;
    @BindString(R.string.TAG_NUMER_OF_THIRD_POEM)
    String TAG_NUMER_OF_THIRD_POEM;
    //Passing 2 (MAX) Actors
    @BindString(R.string.EXTRA_PASS_JSON_POEM_ACTOR_1)
    String EXTRA_PASS_JSON_POEM_ACTOR_1;
    @BindString(R.string.EXTRA_PASS_JSON_POEM_ACTOR_2)
    String EXTRA_PASS_JSON_POEM_ACTOR_2;
    @BindString(R.string.EXTRA_PASS_JSON_POEM_ACTOR_3)
    String EXTRA_PASS_JSON_POEM_ACTOR_3;
    //For Guided View Poem VIEW
    @BindString(R.string.EXTRA_POETS_SECTION_PASS_NAME_OF_POET)
    String EXTRA_POETS_SECTION_PASS_NAME_OF_POET;
    @BindString(R.string.EXTRA_POETS_SECTION_PASS_POEM_VIDEO)
    String EXTRA_POETS_SECTION_PASS_POEM_VIDEO;
    @BindString(R.string.EXTRA_POETS_SECTION_PASS_JSON_NAME_POEM)
    String EXTRA_POETS_SECTION_PASS_JSON_NAME_POEM;
    @BindString(R.string.EXTRA_POETS_SECTION_PASS_JSON_POEM)
    String EXTRA_POETS_SECTION_PASS_JSON_POEM;
    @BindString(R.string.EXTRA_POETS_SECTION_PASS_IMAGE)
    String EXTRA_POETS_SECTION_PASS_IMAGE;
    @BindString(R.string.EXTRA_POETS_SECTION_PASS_READER_NAME)
    String EXTRA_POETS_SECTION_PASS_READER_NAME;
    @BindString(R.string.EXTRA_FROM_FRAGMENT)
    String EXTRA_FROM_FRAGMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_view_class);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        //Data:
        final String messageImage = extras.getString(EXTRA_PASS_IMAGE_DATA_TO_POPUPVIEW_1, "");//image jpg
        final String messageData = extras.getString(EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_1, "");//Nombre del poeta arriba
        final String messageText = extras.getString(EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_2, ""); //Bio extended
        //Poems:
        final String poem1 = extras.getString(EXTRA_PASS_JSON_POEM_1, "");//Poema 1 Texto
        final String poem2 = extras.getString(EXTRA_PASS_JSON_POEM_2, "");//Poema 2 Texto
        final String poem3 = extras.getString(EXTRA_PASS_JSON_POEM_3, "");//Poema 3 Texto

        //Actors:
        final String actor1 = extras.getString(EXTRA_PASS_JSON_POEM_ACTOR_1, ""); // Actor1 o poeta1  texto
        final String actor2 = extras.getString(EXTRA_PASS_JSON_POEM_ACTOR_2, ""); // Actor2 o poeta2  texto
        final String actor3 = extras.getString(EXTRA_PASS_JSON_POEM_ACTOR_3, ""); // Actor2 o poeta2  texto

        try {
            Drawable mDrawable = MainActivity.getAssetImage(this, messageImage);
            image1.setImageDrawable(mDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Typeface face_1 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textUpperSide1.setTypeface(face_1);
        textUpperSide1.setText(Html.fromHtml(messageData));

        final Typeface face_2 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textUpperSide2.setTypeface(face_2);
        textUpperSide2.setText(Html.fromHtml(messageText));

        /**
         *
         *
         *                             Poets OR Actors:
         *
         */


        final Typeface facetextViewActor1 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textViewActor1.setTypeface(facetextViewActor1);
        textViewActor1.setText(Html.fromHtml(actor1));

        final Typeface facetextViewActor2 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textViewActor2.setTypeface(facetextViewActor2);
        textViewActor2.setText(Html.fromHtml(actor2));

        final Typeface facetextViewActor3 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textViewActor3.setTypeface(facetextViewActor3);
        textViewActor3.setText(Html.fromHtml(actor3));

        /**
         *
         *                            Poems:
         *
         */

        final Typeface facePoem_1 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textviewPoem1.setTypeface(facePoem_1);
        textviewPoem1.setText(Html.fromHtml(poem1));

        final Typeface facePoem_2 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textviewPoem2.setTypeface(facePoem_2);
        textviewPoem2.setText(Html.fromHtml(poem2));

        final Typeface facePoem_p1 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textviewPoem3.setTypeface(facePoem_p1);
        textviewPoem3.setText(Html.fromHtml(poem3));
    }

    @OnClick(R.id.textView1linear1LSVRBottomUp)
    public void click1() {
        // TODO Click text

        Toast.makeText(ElsPoetesPopUpView.this,
                "pressing text....1  ", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.textView3linear1LSVRBottomUp)
    public void click2() {
        // TODO Click text

        Toast.makeText(ElsPoetesPopUpView.this,
                "pressing text....2  ", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.textView5linear1LSVRBottomUp)
    public void click3() {
        // TODO Click text

        Toast.makeText(ElsPoetesPopUpView.this,
                "pressing text....3  ", Toast.LENGTH_SHORT).show();
    }
}