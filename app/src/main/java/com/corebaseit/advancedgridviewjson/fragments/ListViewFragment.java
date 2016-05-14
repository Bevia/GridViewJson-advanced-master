package com.corebaseit.advancedgridviewjson.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.adapters.RecycleAdapter;
import com.corebaseit.advancedgridviewjson.models.ElsPoetesJsonDataModel;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {

    static ArrayList<ElsPoetesJsonDataModel> modelJsonPoets; //Static so that I can use it in ElsActors!

    private static final String TAG_POETS_JSON = "Poets.en.json";
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
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public ListViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycleView);
        /** use this setting to improve performance if you know that changes
         in content do not change the layout size of the RecyclerView */
        recyclerView.setHasFixedSize(true);
        //use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        new getMyPoems().execute();
    }

    //Grab the POEM...and place it in Adapter!
    public class getMyPoems extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... urls) {

            Type listType = new TypeToken<ArrayList<ElsPoetesJsonDataModel>>() {
            }.getType();

            modelJsonPoets = new GsonBuilder().create().fromJson(loadJSONPoemFromAsset(TAG_POETS_JSON), listType);

            /**
             *  Hashmap for ListView
             */

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
                map.put(TAG_SECOND_POEM, jsonSecondPoem);
                map.put(TAG_THIRD_POEM, jsonThirdPoem);
                map.put(TAG_FIRST_ACTOR, jsonFirstActor);
                map.put(TAG_SECOND_ACTOR, jsonSecondActor);
                map.put(TAG_THIRD_ACTOR, jsonThirdActor);
                map.put(TAG_NUMBER, intToStringNumber);

                songsList.add(map);

            }
            return (songsList);
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
            super.onPostExecute(result);

            RecycleAdapter newadapter = new RecycleAdapter(getActivity(), modelJsonPoets);
            recyclerView.setAdapter(newadapter);

            newadapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    Toast.makeText(getActivity(), " from fragment... "
                            + modelJsonPoets.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public String loadJSONPoemFromAsset(String jsonName) {
        String json = null;
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

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
