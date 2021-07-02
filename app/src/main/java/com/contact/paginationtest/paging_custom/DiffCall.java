package com.contact.paginationtest.paging_custom;

import androidx.recyclerview.widget.DiffUtil;

import com.contact.paginationtest.Modal;

import java.util.List;

public class DiffCall extends DiffUtil.Callback {

    List<Modal> oldList;
    List<Modal> newList;

    public DiffCall(List<Modal> oldList, List<Modal> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id.equals(newList.get(newItemPosition).id);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
