package com.corebaseit.advancedgridviewjson.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.corebaseit.advancedgridviewjson.AnalyticsApplication;
import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.adapters.ImageAdapter;
import com.corebaseit.advancedgridviewjson.models.ElsPoetesJsonDataModel;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GridViewFragment extends Fragment {

    static ArrayList<ElsPoetesJsonDataModel> modelJsonPoets; //Static so that I can use it in ElsActors!

    /**
     * The {@link Tracker} used to record screen views.
     */
    private Tracker mTracker;

    @BindView(R.id.gridView) GridView gridView;
    private ImageAdapter imageAdapter;

    @BindString(R.string.TAG_POETS_JSON) String TAG_POETS_JSON;

    private String TAG_ACTORS_JSON;

    private String TAG_NAME;
    private String TAG_BIO;
    private String TAG_BIO_EXTENDED;
    private String TAG_NUMBER;

    private String TAG_POEM_NAME;
    private String TAG_POEM_ACTOR_NUMBER;
    private String TAG_POEM_POET_NUMBER;
    private String TAG_POEM_PROLOGUE;
    private String TAG_POEM_NUMBER;
    private String TAG_FIRST_POEM;
    private String TAG_SECOND_POEM;
    private String TAG_THIRD_POEM;
    private String TAG_FIRST_ACTOR;
    private String TAG_SECOND_ACTOR;
    private String TAG_THIRD_ACTOR;
    private Unbinder gridViewBinder;

    public GridViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.gridview_fragment, container, false);
        gridViewBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * It will throw exception if the target view can not be found. To indicate that the
     * field may not be present in the layout, use any variant of the @Nullable annotation.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = (GridView) getActivity().findViewById(R.id.gridView);

        /**
         * Gridview showing/hiding toolbar:
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            gridView.setNestedScrollingEnabled(true);
        }

        // [START custom_event]
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Gridview VIEW")
                .build());
        // [END custom_event]

        new poemaLeido().execute();

    }

    private void refreshGridView() {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int numColumns = (screenWidth) / (screenWidth/2);

        gridView.setNumColumns(numColumns);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        refreshGridView();
    }

    //Grab the POEM...and place it in Adapter!
    public class poemaLeido extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... urls) {

            Type listType = new TypeToken<ArrayList<ElsPoetesJsonDataModel>>() { }.getType();

            modelJsonPoets = new GsonBuilder().create().fromJson(loadJSONPoemFromAsset(TAG_POETS_JSON), listType);

            //Hashmap for ListView
            ArrayList<HashMap<String, String>> songsList = new ArrayList<>();

            for (ElsPoetesJsonDataModel post : modelJsonPoets) {

                String jsonPoemLineNumber = post.getBio();
                String jsonPoemText = post.getBioExtended();
                String jsonPoemStartTime = post.getName();
                String jsonFirstPoem = post.getNameFirstPoem();
                String jsonSecondPoem = post.getNameSecondtPoem();
                String jsonThirdPoem = post.getNameThirdPoem();
                String jsonFirstActor = post.getNameFirstActor();
                String jsonSecondActor = post.getNameSecondActor();
                String jsonThirdActor = post.getNameThirdActor();
                Integer jsonPoemEndTime = post.getNumber();
                String intToStringNumber = Integer.toString(jsonPoemEndTime);

                HashMap<String, String> map = new HashMap<>();

                map.put(TAG_NAME, jsonPoemLineNumber);
                map.put(TAG_BIO, jsonPoemText);
                map.put(TAG_BIO_EXTENDED, jsonPoemStartTime);
                map.put(TAG_FIRST_POEM, jsonFirstPoem);
                map.put(TAG_SECOND_POEM,jsonSecondPoem);
                map.put(TAG_THIRD_POEM,jsonThirdPoem);
                map.put(TAG_FIRST_ACTOR, jsonFirstActor);
                map.put(TAG_SECOND_ACTOR,jsonSecondActor);
                map.put(TAG_THIRD_ACTOR,jsonThirdActor);
                map.put(TAG_NUMBER, intToStringNumber);

                songsList.add(map);

            }
            return (songsList);
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
            super.onPostExecute(result);

            imageAdapter = new ImageAdapter(getActivity(), R.layout.gridview_layout, modelJsonPoets);
            gridView.setAdapter(imageAdapter);

        }
    }

    public String loadJSONPoemFromAsset(String jsonName) {
        String json;
        try {
            InputStream is = getActivity().getAssets().open(jsonName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshGridView();
    }
    
    @Override public void onDestroyView() {
        super.onDestroyView();
        gridViewBinder.unbind();
    }
}