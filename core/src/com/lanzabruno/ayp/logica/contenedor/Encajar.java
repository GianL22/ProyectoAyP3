package com.lanzabruno.ayp.logica.contenedor;

import com.lanzabruno.ayp.logica.region.Funcion;
import com.lanzabruno.ayp.logica.casilla.Casilla;

import java.util.ArrayList;

public class Encajar<C extends Casilla> implements Funcion<C> {
    ArrayList<C> casillasContenedor;
    int casillasOcupadas;
    Encajar(ArrayList<C> casillasContenedor,int casillasOcupadas ){
        this.casillasContenedor = casillasContenedor;
        this.casillasOcupadas = casillasOcupadas;
    }
    @Override
    public void aplicar(C c) {
        C casillaCont = casillasContenedor.get(0);
        c.actualizarEstado(casillaCont.getEstado());
        casillasContenedor.remove(0);
    }

    @Override
    public boolean predicado(C c) {
        return this.casillasOcupadas != 0;
    }
}
