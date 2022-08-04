package com.lanzabruno.ayp.logica.comodines;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Triangulo;
import com.lanzabruno.ayp.logica.puntaje.Puntaje;

public class Martillo  extends Comodin<Casilla<Triangulo>> {

    public Martillo(int precio, Puntaje puntaje){
        this.setPrecio(precio);
        this.setPuntaje(puntaje);
    }

    @Override
    public void aumentarPrecio() {
        this.setPrecio(this.getPrecio()*2);
    }

    @Override
    public void aplicar(Casilla<Triangulo> d) {
        if(this.available()){
            d.desocupar();
            this.aumentarPrecio();
        }
    }

}
