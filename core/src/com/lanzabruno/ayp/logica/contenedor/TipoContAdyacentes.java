package com.lanzabruno.ayp.logica.contenedor;

import com.lanzabruno.ayp.logica.generadores.GeneradorContAdyacentes;
import com.lanzabruno.ayp.logica.casilla.Casilla;

public class TipoContAdyacentes<C extends Casilla> implements TipoContenedor<C> {
    @Override
    public GeneradorContAdyacentes<C> generador() {
        return new GeneradorContAdyacentes<>();
    }
}
