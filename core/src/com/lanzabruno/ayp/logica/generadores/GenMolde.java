package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Poligono;

import java.util.ArrayList;

public interface GenMolde <D extends Poligono>{
    public ArrayList<Casilla<D>> generar();
}
