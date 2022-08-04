package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.region.Funcion;
import com.lanzabruno.ayp.logica.casilla.Casilla;

import java.util.ArrayList;

public abstract class GeneradorContenedor<C extends Casilla> implements Funcion<C> {
    ArrayList<C> casillas;
    int nFichas, maxFichas;
    String colorFichas;
    public void setNFichas(int fichas){
        this.nFichas = fichas;
    }

    public void setMaxFichas(int maxFichas){
        this.maxFichas = maxFichas;
    }
    public int getMaxFichas() {
        return this.maxFichas;
    }

    public void setColorFichas(String colorFichas){
        this.colorFichas = colorFichas;
    }
    public String getColorFichas(){
        return this.colorFichas;
    }

    public ArrayList<C> getCasillas(){
        return this.casillas;
    }
    public boolean predicado(C casilla){
        return this.nFichas > 0;
    };
    public abstract void aplicar(C casilla);
}
