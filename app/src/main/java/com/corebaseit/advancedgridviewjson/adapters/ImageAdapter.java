package com.corebaseit.advancedgridviewjson.adapters;

/**
 * Created by vincent on 10/2/16.
 * www.corebaseit.com "good android stuff coming soon...."
 */
/**
 * Copyright 2013 Square, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.corebaseit.advancedgridviewjson.ElsPoetesPopUpView;
import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.models.ElsPoetesJsonDataModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends ArrayAdapter<ElsPoetesJsonDataModel> {

    private Context context;
    private List<ElsPoetesJsonDataModel> poetsList;
    private static LayoutInflater poeticaPoetsInflater = null;
    private static final String enContextoImage_1 = "pictures/Poet-";
    private ProgressBar progressView;

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

    public static class ViewHolder {

        public ImageView imageView;
        public TextView bio;
        public TextView name;
        public TextView poem1;
        public TextView poem2;
        public TextView poem3;
        public TextView actor1;
        public TextView actor2;
        public TextView actor3;
        public ProgressBar progressBar;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = poeticaPoetsInflater.inflate(R.layout.gridview_layout, parent, false);

            holder.imageView = (ImageView) convertView.findViewById(R.id.image);

            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
            progressView = holder.progressBar;

            holder.bio = (TextView) convertView.findViewById(R.id.textView1linear1LSVRUpBottom);
            ((TextView) convertView.findViewById(R.id.textView1linear1LSVRUpBottom)).setTypeface(Typeface.
                    createFromAsset(convertView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            holder.name = (TextView) convertView.findViewById(R.id.textView1linear1LSVRUpUp);
            ((TextView) convertView.findViewById(R.id.textView1linear1LSVRUpUp)).setTypeface(Typeface.
                    createFromAsset(convertView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            holder.poem1 = (TextView) convertView.findViewById(R.id.textView1linear1LSVRBottomUp);
            ((TextView) convertView.findViewById(R.id.textView1linear1LSVRBottomUp)).setTypeface(Typeface.
                    createFromAsset(convertView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            holder.poem2 = (TextView) convertView.findViewById(R.id.textView3linear1LSVRBottomUp);
            ((TextView) convertView.findViewById(R.id.textView3linear1LSVRBottomUp)).setTypeface(Typeface.
                    createFromAsset(convertView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            holder.poem3 = (TextView) convertView.findViewById(R.id.textView5linear1LSVRBottomUp);
            ((TextView) convertView.findViewById(R.id.textView5linear1LSVRBottomUp)).setTypeface(Typeface.
                    createFromAsset(convertView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            holder.actor1 = (TextView) convertView.findViewById(R.id.textView2linear1LSVRBottomBottom);
            ((TextView) convertView.findViewById(R.id.textView2linear1LSVRBottomBottom)).setTypeface(Typeface.
                    createFromAsset(convertView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            holder.actor2 = (TextView) convertView.findViewById(R.id.textView4linear1LSVRBottomBottom);
            ((TextView) convertView.findViewById(R.id.textView4linear1LSVRBottomBottom)).setTypeface(Typeface.
                    createFromAsset(convertView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            holder.actor3 = (TextView) convertView.findViewById(R.id.textView6linear1LSVRBottomBottom);
            ((TextView) convertView.findViewById(R.id.textView6linear1LSVRBottomBottom)).setTypeface(Typeface.
                    createFromAsset(convertView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));


            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
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

        progressView.setVisibility(View.VISIBLE);
        Picasso.with(context)
                .load("file:///android_asset/"
                        + enContextoImage_1 + flower.getNumber()
                        + "-Menu@2x.jpg")
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

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

        return convertView;
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