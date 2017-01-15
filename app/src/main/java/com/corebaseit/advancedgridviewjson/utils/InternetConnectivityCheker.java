package com.corebaseit.advancedgridviewjson.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class InternetConnectivityCheker {

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void showNoInternetConnectionAlertDialogStay(Context context) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("No internet connection!")
                .setContentText("Make sure you have connection.")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        //reuse previous dialog instance
                        /*sDialog.setTitleText("Deleted!")
                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);*/
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }
}