package com.m.infinitywallpaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.m.infinitywallpaper.adapter.CategoryAdapter;
import com.m.infinitywallpaper.common.Constant;
import com.m.infinitywallpaper.model.Category;
import com.m.infinitywallpaper.model.CategoryResponse;
import com.m.infinitywallpaper.model.LoginResponse;
import com.m.infinitywallpaper.retrofit.APIClient;
import com.m.infinitywallpaper.retrofit.ApiInterface;
import com.m.infinitywallpaper.retrofit.OnClickItemListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends MActivity {
    private RecyclerView lvList;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        lvList = findViewById(R.id.lvList);
        getCategoryList();


    }

    public void getCategoryList(){
        showProgress();
        ApiInterface apiService =
                APIClient.getClient().create(ApiInterface.class);
        Call<CategoryResponse> call = apiService.getCategoryList();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(final Call<CategoryResponse> call, Response<CategoryResponse> response) {
                hideProgress();
                printLog(CategoryActivity.class,new Gson().toJson(response.body()).toString());

                categoryAdapter = new CategoryAdapter(CategoryActivity.this,response.body());
                lvList.setAdapter(categoryAdapter);
                lvList.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));

                categoryAdapter.setOnClickItemListener(new OnClickItemListener<Category>() {
                    @Override
                    public void onClickItem(Category category) {
                        int idCate = category.getId();
                        Intent intent = new Intent(CategoryActivity.this,ItemListActivity.class);
                        intent.putExtra(Constant.DATA,idCate);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                hideProgress();
            }
        });
    }

}

