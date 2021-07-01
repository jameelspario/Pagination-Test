package com.contact.paginationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        PhotosAdapter photosAdapter = new PhotosAdapter();

        mainActivityViewModel.getPagedListLiveData().observe(this, photos -> {
            Log.e("Main", new Gson().toJson(photos));
            photosAdapter.submitList(photos);

        });

        recyclerView.setAdapter(photosAdapter);

    }
}