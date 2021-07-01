package com.contact.paginationtest;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class PhotoDataSourceFactory extends DataSource.Factory<Long, Modal> {

    PhotoDataSource photoDataSource;
    MutableLiveData<PhotoDataSource> mutableLiveData;

    public PhotoDataSourceFactory() {
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Long,Modal> create() {
        photoDataSource = new PhotoDataSource();
        mutableLiveData.postValue(photoDataSource);
        return photoDataSource;
    }

    public MutableLiveData<PhotoDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
