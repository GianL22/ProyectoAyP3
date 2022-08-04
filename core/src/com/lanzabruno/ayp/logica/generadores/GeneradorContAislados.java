package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.casilla.ConFicha;

import java.util.ArrayList;

public class GeneradorContAislados<C extends Casilla> extends GeneradorContenedor<C>{
    boolean alternar = true;
    public GeneradorContAislados(){
        this.casillas = new ArrayList<>();
        this.setMaxFichas(3);
    }

    @Override
    public void aplicar(C casilla) {
        this.casillas.add(casilla);
        if (this.alternar){
            this.nFichas--;
            casilla.actualizarEstado(new ConFicha(this.colorFichas));
        }

        this.alternar = !this.alternar;
    }
}
