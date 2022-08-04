package com.lanzabruno.ayp.logica.region;

import com.lanzabruno.ayp.logica.casilla.Casilla;

public interface Funcion<C extends Casilla> {
    public void aplicar(C c);
    public boolean predicado(C c);
}
