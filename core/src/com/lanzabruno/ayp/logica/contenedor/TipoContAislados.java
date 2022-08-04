package com.lanzabruno.ayp.logica.contenedor;

import com.lanzabruno.ayp.logica.generadores.GeneradorContAislados;
import com.lanzabruno.ayp.logica.casilla.Casilla;

public class TipoContAislados<C extends Casilla> implements TipoContenedor<C>{
    @Override
    public GeneradorContAislados<C> generador() {
        return new GeneradorContAislados<>();
    }
}
