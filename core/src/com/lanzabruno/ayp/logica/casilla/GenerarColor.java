package com.lanzabruno.ayp.logica.casilla;

import java.util.ArrayList;
import java.util.Random;

public class GenerarColor {
    Random random = new Random();
    ArrayList<String> colores;
    public GenerarColor(){
        this.colores = new ArrayList<>();
    }
    public void agregarColor(String color){
        this.colores.add(color);
    }
    public String generarColor(){
        return this.colores.get(random.nextInt(colores.size()));
    }
}
