package com.eetac.upc.dsa.minimo2;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eetac.upc.dsa.minimo2.models.*;
import com.eetac.upc.dsa.minimo2.models.Museums;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyMuseumRecyclerViewAdapter extends RecyclerView.Adapter<MyMuseumRecyclerViewAdapter.ViewHolder> {
    private List<Element> elements;
    private MainActivity main;



    public MyMuseumRecyclerViewAdapter(List<Element> elements, MainActivity mainActivity){

        this.elements=elements;
        this.main=mainActivity;
    }

    @NonNull
    @Override
    public MyMuseumRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.museum_layout,parent,false));    }

    @Override
    public void onBindViewHolder(@NonNull MyMuseumRecyclerViewAdapter.ViewHolder holder, int position) {
        Element element=elements.get(position);
        holder.adreca.setText(element.getAdrecaNom());
        Picasso.get().load(element.getImatge().get(0)).into(holder.foto);
        holder.button_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_detail = new Intent(v.getContext(), MuseumDetail.class);
                Bundle b = new Bundle();
                Gson gson = new Gson();
                String myJson = gson.toJson(elements.get(holder.getAdapterPosition()));
                activity_detail.putExtra("myjson", myJson);
                main.startActivity(activity_detail);

            }
        });

    }

    @Override
    public int getItemCount() {
        return elements.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        private TextView adreca;
        private ImageView foto;
        private Button button_info;


        public ViewHolder(View v) {
            super(v);
            adreca = v.findViewById(R.id.adreca);
            foto = v.findViewById(R.id.foto);
            button_info = v.findViewById(R.id.button_info);
            linearLayout = v.findViewById(R.id.linearLayout);
        }
    }
}