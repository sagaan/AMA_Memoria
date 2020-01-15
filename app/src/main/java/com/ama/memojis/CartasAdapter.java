package com.ama.memojis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class CartasAdapter extends RecyclerView.Adapter<CartasAdapter.CartasHolder> {

    private List<Cartas> cartas;
    private Context contexto;
    private List<Cartas> cartasSeleccionadas;
    private List<CartasHolder> cartasHolders;
    private int intentos;
    private boolean shuffle;

    public CartasAdapter(List<Cartas> cartas, Context contexto){
        this.cartas = cartas;
        this.contexto = contexto;
        this.cartasSeleccionadas = new ArrayList<>();
        this.cartasHolders = new ArrayList<>();
        intentos = 0;
        shuffle = false;
    }

    public class CartasHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;

        public CartasHolder(@NonNull View v) {
            super(v);
            imagen = v.findViewById(R.id.imgCarta);
        }
    }

    @NonNull
    @Override
    public CartasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memoria,parent,false);

        return new CartasHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartasHolder holder, final int position) {
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cartas temp = cartas.get(position);
                temp.setId(position);
                if(temp.isSeleccionada()){
                    Toast.makeText(contexto, "Selecciona una carta diferente", Toast.LENGTH_SHORT).show();
                }else if(cartasSeleccionadas.size() < 2){
                    cartas.get(position).setSeleccionada(true);
                    cartasHolders.add(holder);
                    cartasSeleccionadas.add(temp);
                    holder.imagen.setImageResource(temp.getImagen());
                    if(cartasSeleccionadas.size() == 2){
                        if(cartasSeleccionadas.get(0).getImagen() == cartasSeleccionadas.get(1).getImagen()){
                            cartasSeleccionadas.removeAll(cartasSeleccionadas);
                            cartasHolders.removeAll(cartasHolders);
                            Toast.makeText(contexto, "Acertaste", Toast.LENGTH_SHORT).show();
                        }else{
                            intentos++;
                            String mensaje = "Fallaste, llevas " + intentos + " intentos de 4";
                            if(intentos == 4){
                                shuffle = true;
                                mensaje.concat("\nSe te acabaron los intentos");
                            }
                            Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    fail();
                                    if(shuffle)
                                        ((Activity) contexto).recreate();
                                }
                            },1000);

                        }//end else-if


                        if (shuffle) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            }, 2500);
                        }
                    }//end if
                }//end else-if
            }//end onClick()
        });
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    private void fail(){
        cartasHolders.get(0).imagen.setImageResource(R.drawable.back);
        cartasHolders.get(1).imagen.setImageResource(R.drawable.back);
        cartas.get(cartasSeleccionadas.get(0).getId()).setSeleccionada(false);
        cartas.get(cartasSeleccionadas.get(1).getId()).setSeleccionada(false);
        cartasSeleccionadas.removeAll(cartasSeleccionadas);
        cartasHolders.removeAll(cartasHolders);
    }
}
