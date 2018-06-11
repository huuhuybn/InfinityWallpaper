package com.m.infinitywallpaper.retrofit;

import com.m.infinitywallpaper.model.CategoryResponse;
import com.m.infinitywallpaper.model.detail.DetailResponse;
import com.m.infinitywallpaper.model.itemlist.ItemListResponse;
import com.m.infinitywallpaper.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //&username={username}&password={password}
    @GET("api/user/generate_auth_cookie/?insecure=cool")
    Call<LoginResponse> login(@Query("username") String username, @Query("password") String password);

    @GET("api/get_category_index")
    Call<CategoryResponse> getCategoryList();

    @GET("api/get_category_posts")
    Call<ItemListResponse> getItemCategoryList(@Query("id") int id);

    @GET("api/get_post")
    Call<DetailResponse> getDetailItem(@Query("id") int id);
}
