package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.region.Funcion;
import com.lanzabruno.ayp.logica.casilla.Casilla;

import java.util.ArrayList;

public class RecogerCasillas<C extends Casilla> implements Funcion<C> {
    int cantidad;
    ArrayList<C> casillas;
    RecogerCasillas(int cantidad){
        this.cantidad = cantidad;
        this.casillas = new ArrayList<>();
    }
    public int recogidas(){
        return this.casillas.size();
    }
    public ArrayList<C> getCasillas() {
        return this.casillas;
    }
    @Override
    public void aplicar(C c) {
        this.casillas.add(c);
    }
    @Override
    public boolean predicado(C c) {
        return this.cantidad >= this.casillas.size();
    }
}
