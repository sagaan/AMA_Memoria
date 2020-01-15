package com.ama.memojis;

import lombok.Data;

@Data
public class Cartas {
    private int id;
    private int imagen;
    private boolean seleccionada;

    public Cartas(int id, int imagen, boolean seleccionada) {
        this.id = id;
        this.imagen = imagen;
        this.seleccionada = seleccionada;
    }
}
