package com.lanzabruno.ayp.logica.region;

import com.lanzabruno.ayp.logica.casilla.Casilla;

public class MismosColores<C extends Casilla> implements RegionRegla<C> {
    @Override
    public boolean cumplir(C antCas, C actCas) {
        return actCas.getContenido() != "VACIO" && antCas.getContenido() == actCas.getContenido();
    }
}
