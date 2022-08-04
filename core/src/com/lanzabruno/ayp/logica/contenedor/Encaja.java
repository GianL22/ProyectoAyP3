package com.lanzabruno.ayp.logica.contenedor;

import com.lanzabruno.ayp.logica.region.Funcion;
import com.lanzabruno.ayp.logica.casilla.Casilla;

import java.util.ArrayList;

public class Encaja<C extends Casilla> implements Funcion<C> {
    ArrayList<C> casillasContenedor;
    int casillasValidas;
    Encaja(ArrayList<C> casillasContenedor){
        this.casillasContenedor = casillasContenedor;
    }
    public int getCasillasValidas() {
        return this.casillasValidas;
    }

    @Override
    public void aplicar(C c) {
        casillasValidas++;
    }

    @Override
    public boolean predicado(C c) {
        C casCont = this.casillasContenedor.get(0);
        boolean valida = (casCont.ocupado() && !c.ocupado()) && (casCont.isArriba() == c.isArriba());
        this.casillasContenedor.remove(0);
        return valida;
    }
}
