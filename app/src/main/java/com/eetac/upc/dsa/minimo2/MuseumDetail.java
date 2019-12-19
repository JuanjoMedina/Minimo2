package com.eetac.upc.dsa.minimo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MuseumDetail extends AppCompatActivity {

    private int museum_pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);
        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if(b != null)
            value = b.getInt("key");
    }
}
