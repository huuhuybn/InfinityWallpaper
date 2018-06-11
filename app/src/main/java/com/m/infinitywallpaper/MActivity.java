package com.m.infinitywallpaper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.m.infinitywallpaper.common.Constant;

public class MActivity extends AppCompatActivity {


    public ProgressDialog progressDialog;


    public void printLog(Class aClass,String data){
        if (Constant.isDEBUG) Log.e(aClass.getName(),data);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));


    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showProgress() {

        if (progressDialog != null) progressDialog.show();

    }


    public void hideProgress() {

        if (progressDialog != null) progressDialog.dismiss();
    }


    public void startNewActivity(MActivity currentAc,Class activity,boolean isFinish) {
        Intent intent = new Intent(currentAc, activity);
        startActivity(intent);
        if (isFinish) currentAc.finish();
    }


}
