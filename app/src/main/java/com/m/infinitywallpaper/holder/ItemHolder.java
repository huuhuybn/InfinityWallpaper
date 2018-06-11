package com.m.infinitywallpaper.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.infinitywallpaper.R;

public class ItemHolder extends RecyclerView.ViewHolder {
    public final ImageView imgThumb;
    public final TextView tvTitle;


    public ItemHolder(View itemView) {
        super(itemView);

        imgThumb = itemView.findViewById(R.id.imgThumb);
        tvTitle = itemView.findViewById(R.id.tvTitle);

    }
}
