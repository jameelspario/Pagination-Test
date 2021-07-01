package com.contact.paginationtest;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class Modal {

    public Integer albumId;
    public Integer id;
    public String title;
    public String url;
    public String thumbnailUrl;


    public static final DiffUtil.ItemCallback<Modal> CALLBACK = new DiffUtil.ItemCallback<Modal>() {
        @Override
        public boolean areItemsTheSame(@NonNull Modal photos, @NonNull Modal t1) {
            return photos.id.equals(t1.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Modal photos, @NonNull Modal t1) {
            return photos.equals(t1);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modal modal = (Modal) o;
        return Objects.equals(albumId, modal.albumId) &&
                Objects.equals(id, modal.id) &&
                Objects.equals(title, modal.title) &&
                Objects.equals(url, modal.url) &&
                Objects.equals(thumbnailUrl, modal.thumbnailUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, id, title, url, thumbnailUrl);
    }
}
