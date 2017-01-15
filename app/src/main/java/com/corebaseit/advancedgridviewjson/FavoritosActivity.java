package com.corebaseit.advancedgridviewjson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.corebaseit.advancedgridviewjson.jsonlistobject.ListFromJsonUrlView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class FavoritosActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Detail View");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setSubtitle("   Poets");

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        // Every time a button is clicked, we want to broadcast a notification.
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     sendMessage();*/

                Intent intent = new Intent(FavoritosActivity.this, ListFromJsonUrlView.class);
                String message = "Hello there";
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

            }
        });
    }
}
