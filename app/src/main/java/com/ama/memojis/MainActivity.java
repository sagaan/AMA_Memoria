package com.ama.memojis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Integer> imagenes = new ArrayList<>();

        MtdsShuffle obj = new MtdsShuffle();

        List<Cartas> listCartas = obj.shuffle();

        for(Cartas carta : listCartas){
            System.out.println(carta.getImagen()+"\n");
        }

        //CartasAdapter cartasAdapter = new CartasAdapter(listCartas, this);

        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        lManager = new GridLayoutManager(this,3);
        recycler.setLayoutManager(lManager);

        adapter = new CartasAdapter(listCartas, MainActivity.this);
        recycler.setAdapter(adapter);

        /*GridView gridCartas = new GridView(this);
        setContentView(gridCartas);


        gridCartas.setNumColumns(3);
        gridCartas.setColumnWidth(135);
        gridCartas.setAdapter(cartasAdapter);*/
    }
}
