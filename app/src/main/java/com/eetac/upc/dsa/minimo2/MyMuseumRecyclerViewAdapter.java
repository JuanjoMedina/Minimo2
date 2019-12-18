package com.eetac.upc.dsa.minimo2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eetac.upc.dsa.minimo2.models.*;
import com.eetac.upc.dsa.minimo2.models.Museums;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyMuseumRecyclerViewAdapter extends RecyclerView.Adapter<MyMuseumRecyclerViewAdapter.ViewHolder> {
    private List<Element> elements;

    public MyMuseumRecyclerViewAdapter(List<Element> elements){
        this.elements=elements;
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

    }

    @Override
    public int getItemCount() {
        return elements.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView adreca;
        private ImageView foto;


        public ViewHolder(View v) {
            super(v);
            adreca=v.findViewById(R.id.adreca);
            foto=v.findViewById(R.id.foto);
            linearLayout = v.findViewById(R.id.linearLayout);
        }
    }
}