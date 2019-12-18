package com.eetac.upc.dsa.minimo2;

import com.eetac.upc.dsa.minimo2.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public interface MuseumApi {
    String Url="https://do.diba.cat/api/dataset/museus/format/json/pag-ini/1/pag-fi/";
    @GET("11")
    Call<Museums> getMuseums();

    static MuseumApi createMuseumApi() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(MuseumApi.class);
    }
}