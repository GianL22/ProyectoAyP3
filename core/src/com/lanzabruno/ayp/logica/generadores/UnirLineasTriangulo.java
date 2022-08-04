package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Triangulo;

import java.util.ArrayList;

public class UnirLineasTriangulo implements UnirLineas<Triangulo, Casilla<Triangulo>>{
    public void unir(ArrayList<Casilla<Triangulo>> ant, ArrayList<Casilla<Triangulo>> act, int linea){
        for ( Casilla<Triangulo> c: act) {
            if (!c.isArriba()) {
                if (!ant.get(0).isArriba()) ant.remove(0);
                c.agregarVecino(Triangulo.ABA, ant.get(0));
                ant.remove(0);
            }
        }
    }
}
