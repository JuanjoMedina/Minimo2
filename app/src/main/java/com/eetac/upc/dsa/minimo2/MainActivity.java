package com.eetac.upc.dsa.minimo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.eetac.upc.dsa.minimo2.models.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        MuseumApi museumApi=MuseumApi.createMuseumApi();
        Call<Museums> museumsCall = museumApi.getMuseums();

        museumsCall.enqueue(new Callback<Museums>() {
            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {
                if (response.isSuccessful()) {
                    setContentView(R.layout.activity_museums);
                    recyclerView = findViewById(R.id.recycler);
                    Museums museums = response.body();
                    showProgress( false );
                    create_adapter(museums);
                }
            }

            @Override
            public void onFailure(Call<Museums> call, Throwable t) {
                Log.i("ERROR","Failed");
            }
        });
    }

    void showProgress(boolean show){
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.INVISIBLE);

    }

    void create_adapter(Museums museums){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyMuseumRecyclerViewAdapter Adapter = new MyMuseumRecyclerViewAdapter(museums.getElements());
        recyclerView.setAdapter(Adapter) ;
    }

}
