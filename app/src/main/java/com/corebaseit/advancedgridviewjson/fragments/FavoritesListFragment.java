package com.corebaseit.advancedgridviewjson.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.corebaseit.advancedgridviewjson.FavoritosActivity;
import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.adapters.FavoritesListFragmentModelAdapter;
import com.corebaseit.advancedgridviewjson.db.FavoritesListFragmentHelper;
import com.corebaseit.advancedgridviewjson.models.FavoritesListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavoritesListFragment extends Fragment implements AdapterView.OnItemLongClickListener{

    @BindView(R.id.favorites_activity)
    Button favoritesActivity;
    Unbinder gridViewBinder;
    private ListView listview;
    private List<FavoritesListModel> searchList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorites_grid, container, false);
        gridViewBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listview = (ListView) getActivity().findViewById(R.id.listView);
        listview.setOnItemLongClickListener(this);
        loadList(searchList);

        favoritesActivity .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (getActivity(), FavoritosActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * db Stuff ----> BEGIN
     * @param searchList
     */
    private void loadList(List<FavoritesListModel> searchList) {

        this.searchList = searchList;
        FavoritesListFragmentHelper datasource = new FavoritesListFragmentHelper(getActivity());
        datasource.open();

        this.searchList = datasource.findAll();
        if (this.searchList.size() == 0) {
            this.searchList = datasource.findAll(); //don't need to redeclared the list!
        }
        datasource.close();

        ArrayAdapter<FavoritesListModel> adapter = new FavoritesListFragmentModelAdapter(getActivity(), this.searchList);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        FavoritesListFragmentModelAdapter adapter = (FavoritesListFragmentModelAdapter) adapterView.getAdapter();
        FavoritesListModel model = adapter.getItem(i);

        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
        adb.setTitle("Delete?");
        adb.setMessage("Are you sure you want to delete: \""
                + model.getTitle() + "\" from the list?");
        final int id = (int) model.getId();
        adb.setNegativeButton("Cancel", null);
        adb.setPositiveButton("Ok",
                new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FavoritesListFragmentHelper datasource = new FavoritesListFragmentHelper(getContext());
                        datasource.open();
                        datasource.deleteRow(id);
                        datasource.close();
                        FavoritesListFragment.this.setReferashData();
                    }
                });
        adb.show();


        return true;


    }

    public void setReferashData() {

        FavoritesListFragmentHelper datasource = new FavoritesListFragmentHelper(getActivity());
        searchList = datasource.findAll();
        if (searchList.size() == 0) {

            promptUser();

        }
        ArrayAdapter<FavoritesListModel> adapter = new FavoritesListFragmentModelAdapter(getActivity(), searchList);
        listview.setAdapter(adapter);
        datasource.close();

//        listview.refreshDrawableState();
        adapter.notifyDataSetChanged();

    }

    private void promptUser() {
        Toast.makeText(getActivity(), "Empty list!", Toast.LENGTH_SHORT).show();
        /*search_edit.setText(" ");*/

    }

    /**
     * db Stuff ----> END
     */


    @Override
    public void onResume() {
        super.onResume();
        loadList(searchList);

    }

}

