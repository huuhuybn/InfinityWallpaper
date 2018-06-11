package com.m.infinitywallpaper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.artjimlop.altex.AltexImageDownloader;
import com.bumptech.glide.Glide;
import com.m.infinitywallpaper.common.Constant;
import com.m.infinitywallpaper.model.detail.DetailResponse;
import com.m.infinitywallpaper.retrofit.APIClient;
import com.m.infinitywallpaper.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ACDetail extends MActivity {

    private int id;
    private ImageView imgThumb;
    private TextView tvTitle;
    private Button btnSetAs;
    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acdetail);
        id = getIntent().getIntExtra(Constant.DATA,-1);


        imgThumb = findViewById(R.id.imgThumb);
        tvTitle = findViewById(R.id.tvTitle);
        btnSetAs = findViewById(R.id.btnSetAs);
        btnDownload = findViewById(R.id.btnDownload);


        showProgress();
        ApiInterface apiService =
                APIClient.getClient().create(ApiInterface.class);
        Call<DetailResponse> call = apiService.getDetailItem(id);

        call.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, final Response<DetailResponse> response) {
                Glide.with(getApplicationContext()).load(response.body().getPost().getAttachments().get(0).getUrl()).into(imgThumb);
                tvTitle.setText(response.body().getPost().getAttachments().get(0).getTitle());
                hideProgress();


                btnDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AltexImageDownloader.writeToDisk(getApplicationContext(), response.body().getPost().getAttachments().get(0).getUrl(), getString(R.string.app_name));
                        showMessage(getString(R.string.notify_save_successful));
                    }
                });

            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                hideProgress();
            }
        });



    }
}
