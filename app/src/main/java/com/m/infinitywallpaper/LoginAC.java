package com.m.infinitywallpaper;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.m.infinitywallpaper.common.Constant;
import com.m.infinitywallpaper.common.SharePrefManager;
import com.m.infinitywallpaper.model.LoginResponse;
import com.m.infinitywallpaper.retrofit.APIClient;
import com.m.infinitywallpaper.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginAC extends MActivity {
    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_ac);

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btn_login);
        tvSignUp = findViewById(R.id.tvSignUp);
        // check login status



        if (SharePrefManager.instance(this).isLogin()){
            startNewActivity(LoginAC.this, MainActivity.class, true);
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                ApiInterface apiService =
                        APIClient.getClient().create(ApiInterface.class);

                Call<LoginResponse> call = apiService.login(etUserName.getText().toString().trim(), etPassword.getText().toString().trim());
                call.enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        hideProgress();

                        if (Constant.isDEBUG)
                            Log.e("LOGIN", response.body().getUser().getUsername());

                        if (response.body().getStatus().equals(Constant.OK)) {


                            SharePrefManager.instance(LoginAC.this).setIsLogin();
                            startNewActivity(LoginAC.this, MainActivity.class, true);
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        hideProgress();

                        showMessage(getString(R.string.notify_failure));

                    }
                });
            }
        });




        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


}
