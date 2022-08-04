package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.casilla.Casilla;

import java.util.ArrayList;

public interface UnirLineas<D,C extends Casilla>{
    public void unir(ArrayList<C> ant, ArrayList<C> act, int linea);
}
