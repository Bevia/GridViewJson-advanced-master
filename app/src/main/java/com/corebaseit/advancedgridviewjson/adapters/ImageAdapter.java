package com.corebaseit.advancedgridviewjson.adapters;

/**
 * Created by vincent on 10/2/16.
 * www.corebaseit.com "good android stuff coming soon...."
 */
/**
 *

 AVLoadingIndicatorView:

 Copyright 2015 jack wang

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.corebaseit.advancedgridviewjson.ElsPoetesPopUpView;
import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.models.ElsPoetesJsonDataModel;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends ArrayAdapter<ElsPoetesJsonDataModel> {

    private Context context;
    private List<ElsPoetesJsonDataModel> poetsList;
    private static LayoutInflater poeticaPoetsInflater = null;
    private static final String enContextoImage_1 = "pictures/Poet-";

    private static final String TAG_NUMER_OF_FIRST_POEM = "fisrt_poem";
    private static final String TAG_NUMER_OF_SECOND_POEM = "second_poem";
    private static final String TAG_NUMER_OF_THIRD_POEM = "third_poem";

    private static final String EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_1 = "DATA";
    private static final String EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_2 = "TEXT";
    private static final String EXTRA_PASS_IMAGE_DATA_TO_POPUPVIEW_1 = "IMAGE";
    //Pasing 3 (MAX) POEMS:
    private static final String EXTRA_PASS_JSON_POEM_1 = "POEM1";
    private static final String EXTRA_PASS_JSON_POEM_2 = "POEM2";
    private static final String EXTRA_PASS_JSON_POEM_3 = "POEM3"; //only a few have this third one!!
    //Passing 2 (MAX) Actors
    private static final String EXTRA_PASS_JSON_POEM_ACTOR_1 = "POEMACTOR1";
    private static final String EXTRA_PASS_JSON_POEM_ACTOR_2 = "POEMACTOR2";
    private static final String EXTRA_PASS_JSON_POEM_ACTOR_3 = "POEMACTOR3";

    public ImageAdapter(Context context, int resource, List<ElsPoetesJsonDataModel> objects) {

        super(context, resource, objects);
        this.context = context;
        this.poetsList = objects;

        poeticaPoetsInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return poetsList.size();
    }

    public long getItemId(int position) {
        return 0;
    }



    static class ViewHolder {

        AVLoadingIndicatorView myIndicator;
        @BindView(R.id.image) ImageView imageView;
        @BindView(R.id.textView1linear1LSVRUpUp) TextView name;
        @BindView(R.id.textView1linear1LSVRBottomUp) TextView poem1;
        @BindView(R.id.textView3linear1LSVRBottomUp) TextView poem2;
        @BindView(R.id.textView5linear1LSVRBottomUp) TextView poem3;
        @BindView(R.id.textView2linear1LSVRBottomBottom) TextView actor1;
        @BindView(R.id.textView4linear1LSVRBottomBottom) TextView actor2;
        @BindView(R.id.textView6linear1LSVRBottomBottom) TextView actor3;
        @BindView(R.id.textView1linear1LSVRUpBottom) TextView bio;
       // @BindView(R.id.progressBar) ProgressBar progressBar;

        public ViewHolder(View view) {
           ButterKnife.bind(this, view);
        }
    }

    public View getView(final int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view != null) {
            holder = (ViewHolder) view.getTag();
        }else {

            view = poeticaPoetsInflater.inflate(R.layout.gridview_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);

            holder.myIndicator = (AVLoadingIndicatorView)view.findViewById(R.id.myProgress);

            ((TextView) view.findViewById(R.id.textView1linear1LSVRUpBottom)).setTypeface(Typeface.
                    createFromAsset(view.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            ((TextView) view.findViewById(R.id.textView1linear1LSVRUpUp)).setTypeface(Typeface.
                    createFromAsset(view.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));


            ((TextView) view.findViewById(R.id.textView1linear1LSVRBottomUp)).setTypeface(Typeface.
                    createFromAsset(view.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            ((TextView) view.findViewById(R.id.textView3linear1LSVRBottomUp)).setTypeface(Typeface.
                    createFromAsset(view.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            ((TextView) view.findViewById(R.id.textView5linear1LSVRBottomUp)).setTypeface(Typeface.
                    createFromAsset(view.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            ((TextView) view.findViewById(R.id.textView2linear1LSVRBottomBottom)).setTypeface(Typeface.
                    createFromAsset(view.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            ((TextView) view.findViewById(R.id.textView4linear1LSVRBottomBottom)).setTypeface(Typeface.
                    createFromAsset(view.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            ((TextView) view.findViewById(R.id.textView6linear1LSVRBottomBottom)).setTypeface(Typeface.
                    createFromAsset(view.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

        }

        final ElsPoetesJsonDataModel flower = poetsList.get(position);
        holder.bio.setText(Html.fromHtml(flower.getBio()));
        holder.name.setText(Html.fromHtml(flower.getName()));
        holder.poem1.setText(Html.fromHtml(flower.getNameFirstPoem()));
        holder.poem2.setText(Html.fromHtml(flower.getNameSecondtPoem()));
        holder.poem3.setText(Html.fromHtml(flower.getNameThirdPoem()));
        holder.actor1.setText(Html.fromHtml(flower.getNameFirstActor()));
        holder.actor2.setText(Html.fromHtml(flower.getNameSecondActor()));
        holder.actor3.setText(Html.fromHtml(flower.getNameThirdActor()));

        //progressView.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load("file:///android_asset/"
                        + enContextoImage_1 + flower.getNumber()
                        + "-Menu@2x.jpg")
                .fitCenter()
                .into(holder.imageView);

        holder.bio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                datos(position);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                datos(position);
            }
        });

        holder.poem1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(context, "" + flower.getName(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        holder.poem2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(context, "" + flower.getName(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        holder.poem3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(context, "" + flower.getName(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private void datos(int position) {

        final ElsPoetesJsonDataModel flower = poetsList.get(position);

        Intent intent = new Intent(context, ElsPoetesPopUpView.class);

        intent.putExtra(EXTRA_PASS_IMAGE_DATA_TO_POPUPVIEW_1, "pictures/Poet-" + flower.getNumber() + "-Menu@2x.jpg");
        intent.putExtra(EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_1, flower.getName());
        intent.putExtra(EXTRA_PASS_JSON_DATA_TO_POPUPVIEW_2, flower.getBioExtended());
        //POEMS
        intent.putExtra(EXTRA_PASS_JSON_POEM_1, flower.getNameFirstPoem());
        intent.putExtra(EXTRA_PASS_JSON_POEM_2, flower.getNameSecondtPoem());
        intent.putExtra(EXTRA_PASS_JSON_POEM_3, flower.getNameThirdPoem());
        intent.putExtra(TAG_NUMER_OF_FIRST_POEM, flower.getPomeNumberOne());
        intent.putExtra(TAG_NUMER_OF_SECOND_POEM, flower.getPomeNumberTwo());
        intent.putExtra(TAG_NUMER_OF_THIRD_POEM, flower.getPomeNumbeThree());
        intent.putExtra(EXTRA_PASS_JSON_POEM_ACTOR_1, flower.getNameFirstActor());
        intent.putExtra(EXTRA_PASS_JSON_POEM_ACTOR_2, flower.getNameSecondActor());
        intent.putExtra(EXTRA_PASS_JSON_POEM_ACTOR_3, flower.getNameThirdActor());

        context.startActivity(intent);

    }
}