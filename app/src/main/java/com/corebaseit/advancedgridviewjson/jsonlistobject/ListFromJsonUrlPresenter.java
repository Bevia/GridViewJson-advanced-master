package com.corebaseit.advancedgridviewjson.jsonlistobject;

import android.content.Context;
import android.util.Log;

import com.corebaseit.advancedgridviewjson.resttasks.ListFromJsonUrlTask;
import com.corebaseit.advancedgridviewjson.models.ListFromJsonUrlModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Created by vincentbevia on 06/10/16.
 */

public class ListFromJsonUrlPresenter implements
        ListFromJsonUrlTask.Callable {

    private Vector<ListFromJsonUrlModel> result;
    private ListFromJsonUrlContract.ListFromJsonUrlContractView showList;

    public ListFromJsonUrlPresenter(ListFromJsonUrlContract.ListFromJsonUrlContractView showList) {
        this.showList = showList;
    }

    void initDataSet(String is) {
        URL url = null;
        try {
            url = new URL(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ListFromJsonUrlTask task = new ListFromJsonUrlTask(url);
        task.setCallable(this);
        task.execute();
    }

    @Override
    public void showResult(ListFromJsonUrlTask task) {
        try {

            JSONObject theContent = new JSONObject(task.getResult());
            JSONObject historyObject = theContent.getJSONObject("data");

            if (historyObject.has("methods")) {

                JSONArray historyArray = historyObject.getJSONArray("methods");

                result = new Vector<>(historyArray.length());

                ListFromJsonUrlModel model;

                for (int i = 0, n = historyArray.length(); i < n; i++) {

                    model = new ListFromJsonUrlModel(historyArray.getJSONObject(i));
                    result.add(model);
                }
            }

            this.showList.showList(result);
            Log.d("JSONUrl", "the JSON: " + "request OK");

        } catch (JSONException e) {
            Log.d("JSONUrl", "the JSON: " + "has ERRORs");
            e.printStackTrace();
        }
    }
}
