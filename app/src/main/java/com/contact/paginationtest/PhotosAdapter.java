package com.contact.paginationtest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import static com.contact.paginationtest.Modal.CALLBACK;

public class PhotosAdapter extends PagedListAdapter<Modal, PhotosAdapter.PhotoViewHolder> {

    public PhotosAdapter() {
        super(CALLBACK);
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_photoes,viewGroup,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int i) {
        Modal m = getItem(i);
        Log.e("Adapter", new Gson().toJson(m));
        Glide.with(photoViewHolder.itemView.getContext())
                .load(m.thumbnailUrl)
                .into(photoViewHolder.ivPhoto);

        photoViewHolder.tv.setText(m.title);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tv;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.imageView);
            tv = itemView.findViewById(R.id.txt);
        }
    }
}
