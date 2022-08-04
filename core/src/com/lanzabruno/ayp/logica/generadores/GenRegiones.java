package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.region.Region;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Poligono;

import java.util.ArrayList;

public abstract class GenRegiones<D extends Poligono,  C extends Casilla>{
    ArrayList<D> caminoRegion;
    public abstract ArrayList<D> obtenerCaminoRegion();
    public abstract ArrayList<Region<D,C>> generar(ArrayList<C> casillas);
}
