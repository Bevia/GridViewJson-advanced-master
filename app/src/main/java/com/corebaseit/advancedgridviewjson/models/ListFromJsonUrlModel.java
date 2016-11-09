package com.corebaseit.advancedgridviewjson.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vincent Bevia on 27/10/16. <br />
 * vbevia@ieee.org
 */

public class ListFromJsonUrlModel {

    private String id, label, type, name, image;

    public ListFromJsonUrlModel(JSONObject data) throws JSONException {

        this.label = data.getString("label");
        this.type = data.getString("type");
        this.id = data.getString("id");
        this.name = data.getString("name");
        this.image = data.getString("image");

    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }
}