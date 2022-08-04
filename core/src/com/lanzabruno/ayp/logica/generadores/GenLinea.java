package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.casilla.Casilla;

import java.util.ArrayList;

public interface GenLinea<C extends Casilla> {
    public ArrayList<C> generar(int l);
}
