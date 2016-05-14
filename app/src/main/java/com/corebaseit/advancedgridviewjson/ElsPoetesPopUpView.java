package com.corebaseit.advancedgridviewjson;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by vincent on 10/2/16.
 */
public class ElsPoetesPopUpView extends Activity {

    private static final String EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_1 = "DATA";
    private static final String EXTRA_PASS_IMAGE_DATA_TO_POPUPVIEW_1 = "IMAGE";
    private static final String EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_2 = "TEXT";

    //Pasing 3 (MAX) POEMS:
    private static final String EXTRA_PASS_JSON_POEM_1 = "POEM1";
    private static final String EXTRA_PASS_JSON_POEM_2 = "POEM2";
    private static final String EXTRA_PASS_JSON_POEM_3 = "POEM3";

    private static final String TAG_NUMER_OF_FIRST_POEM = "fisrt_poem";
    private static final String TAG_NUMER_OF_SECOND_POEM = "second_poem";
    private static final String TAG_NUMER_OF_THIRD_POEM = "third_poem";
    //Passing 2 (MAX) Actors
    private static final String EXTRA_PASS_JSON_POEM_ACTOR_1 = "POEMACTOR1";
    private static final String EXTRA_PASS_JSON_POEM_ACTOR_2 = "POEMACTOR2";
    private static final String EXTRA_PASS_JSON_POEM_ACTOR_3 = "POEMACTOR3";

    //For Guided View Poem VIEW
    private static final String EXTRA_POETS_SECTION_PASS_NAME_OF_POET = "NAME_OF_POET";
    private static final String EXTRA_POETS_SECTION_PASS_POEM_VIDEO = "NAME_OF_POEM_VIDEO";
    private static final String EXTRA_POETS_SECTION_PASS_JSON_NAME_POEM = "NAME_OF_POEM";
    private static final String EXTRA_POETS_SECTION_PASS_JSON_POEM = "JSON_POEM";
    private static final String EXTRA_POETS_SECTION_PASS_IMAGE = "READER_IMAGE";
    private static final String EXTRA_POETS_SECTION_PASS_READER_NAME = "READER_NAME";
    private static final String EXTRA_FROM_FRAGMENT = "FROM_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_view_class);

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


        //Actual poem number:
        String actualPoem1 = extras.getString(TAG_NUMER_OF_FIRST_POEM, "");
        if(actualPoem1.isEmpty()||actualPoem1==null){
            actualPoem1 = "actualPoem1";
        }
        String actualPoem2 = extras.getString(TAG_NUMER_OF_SECOND_POEM, "");
        if(actualPoem2.isEmpty()||actualPoem2==null){
            actualPoem2 = "actualPoem1";
        }
        String actualPoem3 = extras.getString(TAG_NUMER_OF_THIRD_POEM, "");
        if(actualPoem3.isEmpty()||actualPoem3==null){
            actualPoem3 = "actualPoem1";
        }

        /**
         Toast.makeText(ElsPoetesPopUpView.this,
         "the values are:  " + actualPoem1
         + "   " + actualPoem2
         + "   " + actualPoem3, Toast.LENGTH_SHORT).show();
         */

        /*


                     Getting actual number from poem...  ******* START *********


         */
        //For 1st textView (First Poem);
        final int integerNumberOfFirstTextPoem = Integer.parseInt(actualPoem1.replaceAll("[\\D]", ""));
        final String firstTextPoemIs = Integer.toString(integerNumberOfFirstTextPoem);
        final String sendFirstTextPoemIs = "Poem-" + firstTextPoemIs + ".json";
        final String sendFirstImageNumber = "Poem-"+ firstTextPoemIs + ".jpg";

        //For 2nd textView (Second Poem);
        final int integerNumberOfSecondTextPoem = Integer.parseInt(actualPoem2.replaceAll("[\\D]", ""));
        final String secondTextPoemIs = Integer.toString(integerNumberOfSecondTextPoem);
        final String sendSecondTextPoemIs = "Poem-" + secondTextPoemIs + ".json";
        final String sendSecondImageNumber = "Poem-"+ secondTextPoemIs + ".jpg";

        //For 3rd textView (Third Poem);
        final int integerNumberOfThirdTextPoem = Integer.parseInt(actualPoem3.replaceAll("[\\D]", ""));
        final String thirdTextPoemIs = Integer.toString(integerNumberOfThirdTextPoem);
        final String sendThirdTextPoemIs = "Poem-"  + thirdTextPoemIs + ".json";
        final String sendThirdImageNumber = "Poem-" + thirdTextPoemIs + ".jpg";

        //Log.d("IMAGES", "images are:" + sendFirstImageNumber + "\n" + sendSecondImageNumber + "\n" + sendThirdImageNumber);

        /*


                     Getting actual number from poem...  ******* DONE ********


         */

        String imageSource = messageImage;

        try {
            Drawable mDrawable = MainActivity.getAssetImage(this, imageSource);
            ImageView image1 = (ImageView) findViewById(R.id.pupUpImage);
            image1.setImageDrawable(mDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Log.d("poemNumbers: ", "is: " + actualPoem1 +  "   " + actualPoem2);

        TextView textUpperSide1 = (TextView) findViewById(R.id.textUpperSide1);
        final Typeface face_1 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textUpperSide1.setTypeface(face_1);
        textUpperSide1.setText(Html.fromHtml(messageData));

        TextView textUpperSide2 = (TextView) findViewById(R.id.textUpperSide2);
        final Typeface face_2 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textUpperSide2.setTypeface(face_2);
        textUpperSide2.setText(Html.fromHtml(messageText));

        /**
         *
         *
         *                             Poets OR Actors:
         *
         */

        TextView textViewActor1 = (TextView) findViewById(R.id.textView2linear1LSVRBottomBottom);
        final Typeface facetextViewActor1 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textViewActor1.setTypeface(facetextViewActor1);
        textViewActor1.setText(Html.fromHtml(actor1));

        TextView textViewActor2 = (TextView) findViewById(R.id.textView4linear1LSVRBottomBottom);
        final Typeface facetextViewActor2 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textViewActor2.setTypeface(facetextViewActor2);
        textViewActor2.setText(Html.fromHtml(actor2));

        TextView textViewActor3 = (TextView) findViewById(R.id.textView5linear1LSVRBottomBottom);
        final Typeface facetextViewActor3 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textViewActor3.setTypeface(facetextViewActor3);
        textViewActor3.setText(Html.fromHtml(actor3));

        /**
         *
         *                            Poems:
         *
         */

        TextView textviewPoem1 = (TextView) findViewById(R.id.textView1linear1LSVRBottomUp); //POEM #1
        final Typeface facePoem_1 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textviewPoem1.setTypeface(facePoem_1);
        textviewPoem1.setText(Html.fromHtml(poem1));


        textviewPoem1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                Toast.makeText(ElsPoetesPopUpView.this,
                        "pressing text....  ", Toast.LENGTH_SHORT).show();

                /**
                 Intent intent = new Intent(PopUpClass.this, VideoLecturaGuiadaGuiadoDetailView.class);
                 intent.putExtra(EXTRA_POETS_SECTION_PASS_NAME_OF_POET, messageData);
                 intent.putExtra(EXTRA_POETS_SECTION_PASS_POEM_VIDEO, actualPoem1);
                 intent.putExtra(EXTRA_POETS_SECTION_PASS_JSON_NAME_POEM, poem1);
                 intent.putExtra(EXTRA_POETS_SECTION_PASS_JSON_POEM, sendFirstTextPoemIs);
                 intent.putExtra(EXTRA_POETS_SECTION_PASS_IMAGE, sendFirstImageNumber);
                 intent.putExtra(EXTRA_POETS_SECTION_PASS_READER_NAME, actor1);
                 intent.putExtra(EXTRA_FROM_FRAGMENT, "FROM_FRAGMENT");

                 //Log.d("IMAGES", "images are:" + sendFirstImageNumber);

                 startActivity(intent);
                 */

            }
        });


        TextView textviewPoem2 = (TextView) findViewById(R.id.textView3linear1LSVRBottomUp); //POEM #2
        final Typeface facePoem_2 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textviewPoem2.setTypeface(facePoem_2);
        textviewPoem2.setText(Html.fromHtml(poem2));

        /**
         textviewPoem2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        Intent intent = new Intent(PopUpClass.this, VideoLecturaGuiadaGuiadoDetailView.class);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_NAME_OF_POET, messageData);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_POEM_VIDEO, actualPoem2);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_JSON_NAME_POEM, poem2);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_JSON_POEM, sendSecondTextPoemIs);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_IMAGE, sendSecondImageNumber);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_READER_NAME, actor2);
        intent.putExtra(EXTRA_FROM_FRAGMENT, "FROM_FRAGMENT");


        //Log.d("IMAGES", "images are:" + sendSecondImageNumber);

        startActivity(intent);

        }
        });  */

        TextView textviewPoem3 = (TextView) findViewById(R.id.textView5linear1LSVRBottomUp); //POEM #3
        final Typeface facePoem_p1 = Typeface.createFromAsset(this.getAssets(), "fonts/Quattrocento Regular.ttf");
        textviewPoem3.setTypeface(facePoem_p1);
        textviewPoem3.setText(Html.fromHtml(poem3));

        /**
         textviewPoem3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        Intent intent = new Intent(PopUpClass.this, VideoLecturaGuiadaGuiadoDetailView.class);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_NAME_OF_POET, messageData);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_POEM_VIDEO, actualPoem3);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_JSON_NAME_POEM, poem3);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_JSON_POEM, sendThirdTextPoemIs);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_IMAGE, sendThirdImageNumber);
        intent.putExtra(EXTRA_POETS_SECTION_PASS_READER_NAME, actor3);
        intent.putExtra(EXTRA_FROM_FRAGMENT, "FROM_FRAGMENT");

        //Log.d("IMAGES", "images are:" + sendThirdImageNumber);

        startActivity(intent);
        }
        });
         */
    }
}