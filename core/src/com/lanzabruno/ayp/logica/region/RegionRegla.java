package com.lanzabruno.ayp.logica.region;

import com.lanzabruno.ayp.logica.casilla.Casilla;

public interface RegionRegla<C extends Casilla> {
    public boolean cumplir(C casilla1, C casilla2);
}
