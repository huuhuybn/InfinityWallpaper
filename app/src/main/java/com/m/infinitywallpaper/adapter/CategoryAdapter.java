package com.m.infinitywallpaper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m.infinitywallpaper.R;
import com.m.infinitywallpaper.holder.CategoryHolder;
import com.m.infinitywallpaper.model.CategoryResponse;
import com.m.infinitywallpaper.retrofit.OnClickItemListener;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {


    private Context context;

    private CategoryResponse categoryResponse;

    public OnClickItemListener getOnClickItemListener() {
        return onClickItemListener;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    private OnClickItemListener onClickItemListener;

    public CategoryAdapter(Context context, CategoryResponse categoryResponse) {
        this.context = context;
        this.categoryResponse = categoryResponse;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.category_item,parent,false);

        return new CategoryHolder(view);
    }

    // set data
    @Override
    public void onBindViewHolder(@NonNull final CategoryHolder holder, int position) {
        holder.tvTitle.setText(categoryResponse.getCategories().get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemListener.onClickItem(categoryResponse.getCategories().get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    // return size of list
    @Override
    public int getItemCount() {
        return categoryResponse.getCategories().size();
    }


}
