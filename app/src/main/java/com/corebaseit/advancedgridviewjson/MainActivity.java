package com.corebaseit.advancedgridviewjson;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.corebaseit.advancedgridviewjson.fragments.FavoritesListFragment;
import com.corebaseit.advancedgridviewjson.fragments.GridViewFragment;
import com.corebaseit.advancedgridviewjson.fragments.ListViewFragment;
import com.corebaseit.advancedgridviewjson.utils.AnalyticsApplication;
import com.crashlytics.android.Crashlytics;
import com.example.myaarlibrary.LibraryClass;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private Menu menu;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private static final String TAG = "MainActivity";

    /**
     * You can use Butter Knife inject() method anywhere you would otherwise use the findViewById()
     * method to save time and avoid code repetition when you have to instantiate the views in the layout
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        assert appBarLayout != null;
        appBarLayout.addOnOffsetChangedListener(this);

        setupViewPager(viewPager);
        //keep the three Fragments in memory!
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);

        LibraryClass libraryClass =new LibraryClass();
        libraryClass.printLog("Hello new .arr lib");

        UUID.randomUUID();

        Log.d("UUID", "the UUID: " + UUID.randomUUID());

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        /*
      The {@link Tracker} used to record screen views.
     */
        Tracker mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        // [START custom_event]
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
        // [END custom_event]

        // [START screen_view_hit]
        Log.i(TAG, "Setting screen name: " + "MainActivity");
        mTracker.setScreenName("Activity~" + "MainActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset)
    {
        if (offset == 0)
        {
            getSupportActionBar().setTitle(getString(R.string.myTitle));
            getSupportActionBar().setSubtitle(getString(R.string.poemsfromspain));
            showOverflowMenu(true);
        }
        else
        {
            getSupportActionBar().setTitle(getString(R.string.myTitle));
            getSupportActionBar().setSubtitle(" ");
            showOverflowMenu(false);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new GridViewFragment(), "Crid View");
        adapter.addFrag(new ListViewFragment(), "List View");
        adapter.addFrag(new FavoritesListFragment(), "Favorites View");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                final View v =findViewById(R.id.action_more);
                if (v != null) {
                    v.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return false;
                        }
                    });
                }
            }
        });

        return true;
    }

    public void showOverflowMenu(boolean showMenu){
        if(menu == null)
            return;
        menu.setGroupVisible(R.id.main_menu_group, showMenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open(filename)));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

}