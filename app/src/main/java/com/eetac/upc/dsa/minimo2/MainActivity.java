package com.eetac.upc.dsa.minimo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eetac.upc.dsa.minimo2.models.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private EditText login;
    private EditText pass;
    private Response<Museums> response_api;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        sharedPref=this.getPreferences(Context.MODE_PRIVATE);
        callMuseums();

    }
    void callMuseums (){
        MuseumApi museumApi=MuseumApi.createMuseumApi();
        Call<Museums> museumsCall = museumApi.getMuseums();

        museumsCall.enqueue(new Callback<Museums>() {
            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {
                if (response.isSuccessful()) {
                    if (sharedPref.getBoolean("AUTH",false))
                    {
                        setContentView(R.layout.activity_museums);
                        recyclerView = findViewById(R.id.recycler);
                        Museums museums = response.body();
                        create_adapter(museums);
                    }
                    else {
                        setContentView(R.layout.login);
                        login = findViewById(R.id.login);
                        pass = findViewById(R.id.pass);
                        showProgress(false);
                        response_api = response;
                    }

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
        MyMuseumRecyclerViewAdapter Adapter = new MyMuseumRecyclerViewAdapter(museums.getElements(),this);
        recyclerView.setAdapter(Adapter) ;
    }

    public void setLogin(View v){

        if (login.getText().toString().equals("user") && pass.getText().toString().equals("dsamola")) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("USER", login.getText().toString());
            editor.putString("PASS", pass.getText().toString());
            editor.putBoolean("AUTH", true);
            editor.apply();
            setContentView(R.layout.activity_museums);
            recyclerView = findViewById(R.id.recycler);
            Museums museums = response_api.body();
            create_adapter(museums);
        }

    }

}
