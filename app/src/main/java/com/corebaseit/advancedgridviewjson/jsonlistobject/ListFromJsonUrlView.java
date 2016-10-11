package com.corebaseit.advancedgridviewjson.jsonlistobject;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.adapters.ListFromJsonUrlAdapter;
import com.corebaseit.advancedgridviewjson.constants.Constants;
import com.corebaseit.advancedgridviewjson.models.ListFromJsonUrlModel;
import com.corebaseit.advancedgridviewjson.utils.InternetConnectivityCheker;
import com.corebaseit.advancedgridviewjson.utils.DividerItemCustomDecoration;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFromJsonUrlView extends AppCompatActivity implements
        ListFromJsonUrlContract.ListFromJsonUrlContractView {

    private final InternetConnectivityCheker internetConnectivityCheker
            = new InternetConnectivityCheker();
    private RecyclerView recyclerView;
    private ListFromJsonUrlAdapter paymentAdapter;
    private ListFromJsonUrlPresenter historyPresenter;
    private AVLoadingIndicatorView myIndicator;
    private SwipeRefreshLayout swipeContainer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_from_json_url);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("JSON list View");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setSubtitle("   Poets names");

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemCustomDecoration(this));

        callRestService();
        loadingIndicatorView();
        reloadingList();
    }

    private void loadingIndicatorView() {
        myIndicator = (AVLoadingIndicatorView) findViewById(R.id.myProgress);
        myIndicator.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void reloadingList() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (internetConnectivityCheker.isOnline(ListFromJsonUrlView.this)) {
                    callRestService();
                } else {
                    internetConnectivityCheker.showNoInternetConnectionAlertDialogStay(ListFromJsonUrlView.this);
                    swipeContainer.setRefreshing(false);
                }
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                R.color.steelblue,
                R.color.blueberry_fresh,
                R.color.steelblue);
    }

    private void callRestService() {
        historyPresenter = new ListFromJsonUrlPresenter(this);
        String TAG_URLs = Constants.JSON_MAIN_URL;
        historyPresenter.initDataSet(TAG_URLs);
    }

    @Override
    public void showList(Vector<ListFromJsonUrlModel> models) {
        swipeContainer.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
        paymentAdapter = new ListFromJsonUrlAdapter(this, models);
        recyclerView.setAdapter(paymentAdapter);
        myIndicator.setVisibility(View.GONE);

    }
}
