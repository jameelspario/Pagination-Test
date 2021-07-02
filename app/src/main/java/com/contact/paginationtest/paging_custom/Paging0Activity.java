package com.contact.paginationtest.paging_custom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.contact.paginationtest.GetDataService;
import com.contact.paginationtest.Modal;
import com.contact.paginationtest.R;
import com.contact.paginationtest.RetrofitClient;
import com.contact.paginationtest.databinding.ActivityPaging0Binding;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Paging0Activity extends AppCompatActivity {


    ActivityPaging0Binding binding;
    private PaginationScrollListener scrollListener;
    Paging0Adapter adapter;

    int index = 0; //offset
    int size = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaging0Binding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        initAdapter();
    }

    void initAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setHasFixedSize(true);

        adapter = new Paging0Adapter(this);

        scrollListener=new PaginationScrollListener(layoutManager);
        scrollListener.setOnLoadMoreListener(() -> {
            if(!adapter.isLoading() && !adapter.reachedEnd()){
                loadMore();
            }
        });

        binding.recycler.addOnScrollListener(scrollListener);

        binding.recycler.setAdapter(adapter);

        firstLoad();

    }

    void firstLoad(){
        index = 1;
        adapter.clearData();
        callApi();

    }

    void loadMore(){
        index++;
        adapter.addLoadingView();
        callApi();

    }

    void reachedEnd(){
        adapter.removeLoadingView();
        scrollListener.setLoaded();
    }

    void callApi(){

        Call<List<Modal>> data = RetrofitClient.getRetrofitInstance().create(GetDataService.class).getAllPhotos(index);
        data.enqueue(new Callback<List<Modal>>() {
            @Override
            public void onResponse(Call<List<Modal>> call, Response<List<Modal>> response) {
                List<Modal> photosList = response.body();
                Log.e("PhotoDataSource 1", new Gson().toJson(photosList));
                updateData(photosList);
            }

            @Override
            public void onFailure(Call<List<Modal>> call, Throwable t) {

            }
        });
    }

    void updateData(List<Modal> photo){
        if (photo != null) {
            reachedEnd();
            //.sort(hotpostmodelList, new ListSorting.ShortHotpostmodelByTime());
            adapter.addData(photo);
        }else{
            if(index>0){
                reachedEnd();
            } else {
                //loadingAnimView.showError();
                ///loadingAnimView.setErrText("");
            }
        }

    }


}