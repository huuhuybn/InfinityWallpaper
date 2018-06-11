package com.m.infinitywallpaper.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.m.infinitywallpaper.R;

public class CategoryHolder extends RecyclerView.ViewHolder {

    public final TextView tvTitle;

    public CategoryHolder(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);

    }


}
