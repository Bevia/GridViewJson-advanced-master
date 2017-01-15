package com.corebaseit.advancedgridviewjson.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.corebaseit.advancedgridviewjson.R;

/**
 * Created by vbevia on 09/11/2016.
 */

public class ErrorMessageDialog {

    private Context context;
    private String message;


    public ErrorMessageDialog(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    public void Show() {
        final Dialog dialog = new Dialog(this.context);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.error_dialog);
        Button ok_button = (Button)dialog.findViewById(R.id.ok_button);
        TextView text = (TextView)dialog.findViewById(R.id.text);
        text.setText(this.message);
        ok_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
