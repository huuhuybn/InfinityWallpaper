package com.m.infinitywallpaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.m.infinitywallpaper.adapter.ItemAdapter;
import com.m.infinitywallpaper.common.Constant;
import com.m.infinitywallpaper.model.itemlist.ItemListResponse;
import com.m.infinitywallpaper.model.itemlist.Post;
import com.m.infinitywallpaper.retrofit.APIClient;
import com.m.infinitywallpaper.retrofit.ApiInterface;
import com.m.infinitywallpaper.retrofit.OnClickItemListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListActivity extends MActivity {


    private int id;
    private RecyclerView lvList;

    private ItemAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        lvList = findViewById(R.id.lvList);


        id = getIntent().getIntExtra(Constant.DATA,-1);
        getItemListCategory(id);
    }


    public void getItemListCategory(int id){
        showProgress();
        ApiInterface apiService =
                APIClient.getClient().create(ApiInterface.class);
        Call<ItemListResponse> call = apiService.getItemCategoryList(id);
        call.enqueue(new Callback<ItemListResponse>() {
            @Override
            public void onResponse(Call<ItemListResponse> call, Response<ItemListResponse> response) {

                hideProgress();
                itemAdapter = new ItemAdapter(ItemListActivity.this,response.body());
                lvList.setAdapter(itemAdapter);
                lvList.setLayoutManager(new LinearLayoutManager(ItemListActivity.this));

                itemAdapter.setOnClickItemListener(new OnClickItemListener<Post>() {
                    @Override
                    public void onClickItem(Post post) {
                        Intent intent
                                 = new Intent(ItemListActivity.this,ACDetail.class);
                        intent.putExtra(Constant.DATA,post.getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<ItemListResponse> call, Throwable t) {

                hideProgress();
            }
        });
    }
}
