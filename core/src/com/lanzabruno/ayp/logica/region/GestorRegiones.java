package com.lanzabruno.ayp.logica.region;

import com.lanzabruno.ayp.logica.puntaje.EstrategiaContenedor;
import com.lanzabruno.ayp.logica.puntaje.EstrategiaRegiones;
import com.lanzabruno.ayp.logica.puntaje.Puntaje;
import com.lanzabruno.ayp.logica.region.Region;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Poligono;

import java.util.ArrayList;

public class GestorRegiones<D extends Poligono, C extends Casilla> {
    ArrayList<Region<D,C>> regiones;
    Puntaje puntaje;
    public GestorRegiones(ArrayList<Region<D,C>> regiones, Puntaje puntaje){
        this.regiones = regiones;
        this.puntaje = puntaje;
    }
    public void limpiar(){
        int cont = 0;
        for (Region r : this.regiones){
            if (r.cumple()) {
                r.limpiar();
                cont++;
                System.out.println("Cumplio la region " + r.getId());
            }
        }
        System.out.println("numero de regiones que han cumplido: " + cont);
        this.puntaje.sumarPuntos(cont, new EstrategiaRegiones());
    }
}
