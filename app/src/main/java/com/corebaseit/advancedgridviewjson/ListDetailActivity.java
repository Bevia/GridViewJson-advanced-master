package com.corebaseit.advancedgridviewjson;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;
import com.corebaseit.advancedgridviewjson.db.FavoritesListFragmentHelper;
import com.corebaseit.advancedgridviewjson.models.FavoritesListModel;
import com.corebaseit.advancedgridviewjson.utils.RuntimePermissionsActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
import static com.corebaseit.advancedgridviewjson.db.SQLiteHelper.LOGTAG;

public class ListDetailActivity extends RuntimePermissionsActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.imageFullSizeImage)
    ImageView imageFullSizeImage;

    @BindView(R.id.zoomOutImage)
    ImageView zoomOutImage;

    @BindView(R.id.zoomImage)
    ImageView zoomImage;

    @BindView(R.id.txv_row1)
    TextView txv_row1;

    @BindView(R.id.txv_row2)
    TextView txv_row2;

    @BindView(R.id.fullImagePoetview)
    FrameLayout fullImagePoetview;

    @BindView(R.id.mainview)
    RelativeLayout mainview;

    private static final String enContextoImage_1 = "pictures/Poet-";
    private String TAG_GET_PICTURE_URL;
    private String TAG_GET_TEXT_BELOW_IMAGE;
    private String TAG_IMAGE_TO_DISPLAY;
    private String TAG_GET_TEXT_BELOW_TITLE;
    final int version = Build.VERSION.SDK_INT;
    private Bitmap mBitmap;
    private String TAG_IMAGE_NAME;
    private Uri TAG_URI_FILE;
    private File imageFile;
    private View decorView;
    private String TAG = "PERMISSIONS";
    private static final int REQUEST_PERMISSIONS = 20;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        decorView = getWindow().getDecorView();

        getSupportActionBar().setTitle("Detail View");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setSubtitle("   Poets");

        Bundle extras = getIntent().getExtras();
        TAG_GET_PICTURE_URL = extras.getString("imagePath");
        TAG_GET_TEXT_BELOW_IMAGE = extras.getString("textBelowImage");
        TAG_GET_TEXT_BELOW_TITLE = extras.getString("textBio");

        TAG_IMAGE_TO_DISPLAY = "file:///android_asset/"
                + enContextoImage_1 + TAG_GET_PICTURE_URL
                + "-Menu@2x.jpg";

        //Getting plain name of image:
        //We'll use this TAG latter for Social Sharing.
        imageFile = new File(TAG_IMAGE_TO_DISPLAY);
        TAG_IMAGE_NAME = imageFile.getName();

        final int MAX_WIDTH = 1463;
        final int MAX_HEIGHT = 2048;

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        txv_row1.setTypeface(Typeface.
                createFromAsset(ListDetailActivity.this.getAssets(), "fonts/Quattrocento Regular.ttf"));
        txv_row1.setText(TAG_GET_TEXT_BELOW_IMAGE);

        if (version >= 23) {
            txv_row2.setTextColor(ContextCompat.getColor(this, R.color.blue_stone));
        } else if (version < 23) {
            txv_row2.setTextColor(this.getResources().getColor(R.color.blue_stone));
        }
        txv_row2.setTypeface(Typeface.
                createFromAsset(ListDetailActivity.this.getAssets(), "fonts/QuattrocentoSans Italic.ttf"));
        txv_row2.setText(TAG_GET_TEXT_BELOW_TITLE);

        ViewPropertyAnimation.Animator animationObject = new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
                view.setAlpha(0f);
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                fadeAnim.setDuration(2000);
                fadeAnim.start();
            }
        };

        Glide.with(this)
                .load(TAG_IMAGE_TO_DISPLAY)
                .animate(animationObject)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable>
                            target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        placeZoomImage();
                        return false;
                    }
                })
                .into(image);

        Picasso.with(this)
                .load(TAG_IMAGE_TO_DISPLAY)
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerInside()
                .into(imageFullSizeImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        zoomOutImage.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {
                    }
                });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullImagePoetview.setVisibility(View.VISIBLE);
                mainview.setVisibility(View.GONE);

                /**
                 *   Moving to full POET Image, lets hide the toolbar and the navigation bar!
                 */
                toolbar.setVisibility(View.GONE);
                hideNavigationBar();
            }
        });

        fullImagePoetview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullImagePoetview.setVisibility(View.GONE);
                mainview.setVisibility(View.VISIBLE);

                /**
                 *   Recover the toolbar and the navigation bar!
                 */
                toolbar.setVisibility(View.VISIBLE);
                decorView = getWindow().getDecorView();
                int uiOptions = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                }
                decorView.setSystemUiVisibility(uiOptions);
            }
        });
    }

    private void placeZoomImage() {
        zoomImage.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem settingsMenuItem = menu.findItem(R.id.sharing);
        SpannableString s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue_stone)), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.sharing, menu);

        /**
         * This is use to hide the menu...in case you need it!
         */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sharing) {

            /**
             * FROM: file:///android_asset/pictures/Poet-1-Menu@2x.jpg
             * You need only
             * pictures/
             * + the name of your image!
             */

            if (Build.VERSION.SDK_INT >= 23) {
                ListDetailActivity.super.requestAppPermissions(new
                                String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, R.string
                                .runtime_permissions_txt
                        , REQUEST_PERMISSIONS);
            } else {
                shareImage("pictures/" + TAG_IMAGE_NAME);
            }

            return true;
        }
        if (id == R.id.save) {

            Toast.makeText(this, "Saved...", Toast.LENGTH_SHORT).show();
            createOwnData(TAG_GET_TEXT_BELOW_TITLE); //Save data into db...

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * db Stuff ----> BEGIN
     */
    private void createOwnData(String title) {

        FavoritesListModel newSearch = new FavoritesListModel(); //Declare newSearch as an instance of Poets
        newSearch.setTitle(title);
        FavoritesListFragmentHelper datasource = new FavoritesListFragmentHelper(ListDetailActivity.this);
        datasource.open();

        datasource.deleteRow(title);
        newSearch = datasource.create(newSearch);

        datasource.close();

      /*  Toast.makeText(ListDetailActivity.this, title, Toast.LENGTH_SHORT).show();*/

        Log.i(LOGTAG, "person created with id: " + newSearch.getId());

    }

    /**
     * db Stuff ----> END
     */

    /**
     * @param img
     * @return bitmap (from given asset Uri)
     * <p>
     * for instance, for a file like so:
     * file:///android_asset/pictures/Poet-1-Menu@2x.jpg
     * you need to pass:
     * pictures/Poet-1-Menu@2x.jpg
     */

    private Bitmap getBitmapFromAsset(String img) {
        InputStream bitmap = null;
        try {
            bitmap = getAssets().open(img);
            Bitmap bit = BitmapFactory.decodeStream(bitmap);
            return bit;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null)
                try {
                    bitmap.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    /**
     * Share text and image over Social Networks...
     *
     * @param img
     */
    private void shareImage(String img) {

        Bitmap icon = getBitmapFromAsset(img);

        String name = TAG_GET_TEXT_BELOW_IMAGE;
        String text = "I'd love you to read about " + name + " ,let me know if you're interested ;)";

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "title");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = getContentResolver().insert(EXTERNAL_CONTENT_URI,
                values);

        OutputStream outstream;
        try {
            outstream = getContentResolver().openOutputStream(uri != null ? uri : null);
            assert icon != null;
            icon.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
            assert outstream != null;
            outstream.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        /**
         * Share image and txt over any social sharing network
         */

        share.putExtra(Intent.EXTRA_TEXT, text);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Image"));
    }

    public void hideNavigationBar() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    public void onPermissionsGranted(final int requestCode) {
        shareImage("pictures/" + TAG_IMAGE_NAME);
    }
}
