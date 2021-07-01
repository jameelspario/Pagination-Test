package com.contact.paginationtest;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoDataSource extends PageKeyedDataSource<Long,Modal> {

    GetDataService dataService;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Modal> callback) {

        dataService = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<Modal>> data = dataService.getAllPhotos(1);
        data.enqueue(new Callback<List<Modal>>() {
            @Override
            public void onResponse(Call<List<Modal>> call, Response<List<Modal>> response) {
                List<Modal> photosList = response.body();
                Log.e("PhotoDataSource 1", new Gson().toJson(photosList));
                callback.onResult(photosList,null, (long)2);
            }

            @Override
            public void onFailure(Call<List<Modal>> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Modal> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Modal> callback) {


        dataService = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<Modal>> data = dataService.getAllPhotos(params.key);
        data.enqueue(new Callback<List<Modal>>() {
            @Override
            public void onResponse(Call<List<Modal>> call, Response<List<Modal>> response) {
                List<Modal> photosList = response.body();
                Log.e("PhotoDataSource 2", "key"+params.key +" " + new Gson().toJson(photosList));
                callback.onResult(photosList, params.key+1);
            }

            @Override
            public void onFailure(Call<List<Modal>> call, Throwable t) {

            }
        });


    }
}
