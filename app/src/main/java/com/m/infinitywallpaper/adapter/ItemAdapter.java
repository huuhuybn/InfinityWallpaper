package com.m.infinitywallpaper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.m.infinitywallpaper.R;
import com.m.infinitywallpaper.holder.ItemHolder;
import com.m.infinitywallpaper.model.itemlist.ItemListResponse;
import com.m.infinitywallpaper.model.itemlist.Post;
import com.m.infinitywallpaper.retrofit.OnClickItemListener;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {


    private Context context;

    private ItemListResponse itemListResponse;

    private OnClickItemListener<Post> onClickItemListener;

    public OnClickItemListener getOnClickItemListener() {
        return onClickItemListener;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public ItemAdapter(Context context, ItemListResponse itemListResponse) {
        this.context = context;
        this.itemListResponse = itemListResponse;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        holder.tvTitle.setText(itemListResponse.getPosts().get(position).getTitle());
        Glide.with(context).load(itemListResponse.getPosts().get(position).getThumbnail()).into(holder.imgThumb);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemListener.onClickItem(itemListResponse.getPosts().get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemListResponse.getPosts().size();
    }
}
