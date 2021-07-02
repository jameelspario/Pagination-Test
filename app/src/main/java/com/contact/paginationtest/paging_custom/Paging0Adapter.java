package com.contact.paginationtest.paging_custom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.contact.paginationtest.Modal;
import com.contact.paginationtest.databinding.AdapterLayoutBinding;
import com.squareup.picasso.Picasso;

public class Paging0Adapter extends AbstractAdapter<Modal>{


    public Paging0Adapter(Context _context) {
        super(_context);
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        return new Holder(AdapterLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    protected void bindViewHolder2(RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(items_list.get(position));
    }

    class Holder extends RecyclerView.ViewHolder{
        AdapterLayoutBinding binding;
        public Holder(@NonNull AdapterLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void bind(Modal m){
            Log.e("Abst 1", "addData");

            //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(binding.imageView);
            Picasso.get().load("https://i.ibb.co/stsW9Gk/Screenshot-1.png").into(binding.imageView);

            binding.txt.setText(m.title);
        }
    }
}
