package com.corebaseit.advancedgridviewjson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.models.FavoritesListModel;

import java.util.List;

/**
 * Created by Vincent Bevia on 27/10/16. <br />
 * vbevia@ieee.org
 */

public class FavoritesListFragmentModelAdapter extends ArrayAdapter<FavoritesListModel> {
    Context context;
    List<FavoritesListModel> search;
    LayoutInflater vi = null;
    //MainActivity mainActivity = MainActivity.getInstance();


    public FavoritesListFragmentModelAdapter(Context context, List<FavoritesListModel> search) {
        super(context, android.R.layout.activity_list_item, search);
        this.context = context;
        this.search = search;

        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public static class ViewContainer {

        public TextView title;
        private LinearLayout linearView;

    }

    public View getView(final int position, View view, ViewGroup parent) {

        final ViewContainer viewContainer;
        View rowView = view;

        if (rowView == null) {

            rowView = vi.inflate(R.layout.sql_model_lis_item, parent, false);

            viewContainer = new ViewContainer();

            viewContainer.title = (TextView) rowView.findViewById(R.id.title);

            rowView.setTag(viewContainer);

        } else {
            viewContainer = (ViewContainer) rowView.getTag();
        }

        FavoritesListModel model = search.get(position);

        viewContainer.title.setText(model.getTitle());

        return rowView;
    }
}
