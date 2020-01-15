package com.ama.memojis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MtdsShuffle {

    private List<Integer> imagenes = new ArrayList<>();

    public MtdsShuffle(){
        imagenes.add(R.drawable.alien);
        imagenes.add(R.drawable.cat);
        imagenes.add(R.drawable.crazy);
        imagenes.add(R.drawable.cry_cat);
        imagenes.add(R.drawable.glasses);
        imagenes.add(R.drawable.meh);
        imagenes.add(R.drawable.mmm);
        imagenes.add(R.drawable.moon);
        imagenes.add(R.drawable.red_cat);
        imagenes.add(R.drawable.robot);
        imagenes.add(R.drawable.sick);
        imagenes.add(R.drawable.sun);
    }

    public List<Cartas> shuffle(){
        List<Cartas> cartas = new ArrayList<>();
        Random random = new Random();
        List<Integer> tempImg = imagenes;

        for(int F=0; F<=11; F++){
            cartas.add(new Cartas(F,-1, false));
        }

        int currentImg = -1;
        int boundImg = 12;
        for(int F=0; F<=5; F++){
            int band = 0;
            int randomImg = random.nextInt(boundImg);
            currentImg = tempImg.get(randomImg);
            tempImg.remove(randomImg);
            boundImg--;

            do{
                int tempRandom = random.nextInt(12);
                if(cartas.get(tempRandom).getImagen() == -1){
                    cartas.get(tempRandom).setImagen(currentImg);
                    band++;
                    System.out.println("Imagen: " + currentImg + " asignada a carta " + tempRandom);
                }
            }while(band != 2);
        }

        return cartas;
    }


}
