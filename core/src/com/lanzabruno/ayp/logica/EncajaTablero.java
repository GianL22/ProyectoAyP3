package com.lanzabruno.ayp.logica;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.contenedor.Contenedor;
import com.lanzabruno.ayp.logica.region.Funcion;

import java.lang.invoke.CallSite;
import java.util.ArrayList;

public class EncajaTablero implements Funcion<Casilla> {
    ArrayList<Contenedor>  contenedorAEvaluar;
    boolean encaja;
    public EncajaTablero(ArrayList<Contenedor> contenedorAEvaluar){
        this.contenedorAEvaluar = contenedorAEvaluar;
        this.encaja = false;
    }
    public boolean getEncaja(){
        return this.encaja;
    }
    @Override
    public void aplicar(Casilla casilla) {
        for (Contenedor cont: this.contenedorAEvaluar){
            if (!this.encaja)
                this.encaja = cont.encaja(casilla);
        }
    }

    @Override
    public boolean predicado(Casilla casilla) {
        return !this.encaja;
    }
}
