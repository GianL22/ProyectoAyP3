package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.casilla.ConFicha;

import java.util.ArrayList;

public class GeneradorContAdyacentes<C extends Casilla> extends GeneradorContenedor<C>{
    public GeneradorContAdyacentes(){
        this.casillas = new ArrayList<>();
        this.setMaxFichas(5);
    }
    @Override
    public void aplicar(C casilla) {
        this.casillas.add(casilla);
        this.nFichas--;
        casilla.actualizarEstado(new ConFicha(this.colorFichas));

    }
}
