package com.lanzabruno.ayp.logica.contenedor;

import com.lanzabruno.ayp.logica.generadores.GeneradorContenedor;
import com.lanzabruno.ayp.logica.casilla.Casilla;

public interface TipoContenedor<C extends Casilla> {
    public GeneradorContenedor<C> generador();
}
