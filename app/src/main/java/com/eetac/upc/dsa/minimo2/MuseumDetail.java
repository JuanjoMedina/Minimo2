package com.eetac.upc.dsa.minimo2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.eetac.upc.dsa.minimo2.models.*;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MuseumDetail extends AppCompatActivity {

    private TextView adreca;
    private TextView descripcio;
    private TextView grup_adreca_adreca;
    private TextView grup_adreca_codi_postal;
    private TextView group_adreca_municipi_nom;
    private TextView email;
    private TextView telefon_contacte;
    private TextView nombre_habitants;
    private TextView extensio ;
    private TextView altitud;
    private ImageView municipi_escut;
    private ImageView municipi_bandera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);
        Gson gson = new Gson();
        Element ob = gson.fromJson(getIntent().getStringExtra("myjson"), Element.class);
        adreca=findViewById(R.id.adreca_nom);
        descripcio=findViewById(R.id.descripcio);
        grup_adreca_adreca=findViewById(R.id.grup_adreca_adreca);
        grup_adreca_codi_postal=findViewById(R.id.grup_adreca_codi_postal);
        group_adreca_municipi_nom=findViewById(R.id.grup_adreca_municipi_nom);
        email=findViewById(R.id.email);
        telefon_contacte=findViewById(R.id.telefon_contacte);
        nombre_habitants=findViewById(R.id.nombre_habitants);
        extensio=findViewById(R.id.extensio);
        altitud=findViewById(R.id.altitud);
        municipi_escut=findViewById(R.id.municipi_escut);
        municipi_bandera=findViewById(R.id.municipi_bandera);

        adreca.setText(ob.getAdrecaNom());
        descripcio.setText(ob.getDescripcio());
        grup_adreca_adreca.setText(ob.getGrupAdreca().getAdreca());
        grup_adreca_codi_postal.setText(ob.getGrupAdreca().getCodiPostal());
        group_adreca_municipi_nom.setText(ob.getGrupAdreca().getMunicipiNom());
        email.setText(ob.getEmail().get(0));
        telefon_contacte.setText(ob.getTelefonContacte().get(0));
        nombre_habitants.setText("");
        extensio.setText("");
        altitud.setText("");
        Picasso.get().load(ob.getRelMunicipis().getMunicipiEscut()).into(municipi_escut);
        Picasso.get().load(ob.getRelMunicipis().getMunicipiEscut()).into(municipi_escut);

    }
}
