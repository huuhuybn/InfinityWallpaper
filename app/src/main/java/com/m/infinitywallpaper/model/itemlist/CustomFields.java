
package com.m.infinitywallpaper.model.itemlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomFields {

    @SerializedName("post_views_count")
    @Expose
    private List<String> postViewsCount = null;

    public List<String> getPostViewsCount() {
        return postViewsCount;
    }

    public void setPostViewsCount(List<String> postViewsCount) {
        this.postViewsCount = postViewsCount;
    }

}
