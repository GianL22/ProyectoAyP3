package com.lanzabruno.ayp.logica.comodines;

import com.lanzabruno.ayp.logica.puntaje.Puntaje;

public abstract class Comodin<D>{
    private int precio;
    private Puntaje puntaje;

    public abstract void aplicar(D d);

    public abstract void aumentarPrecio();

    public boolean available(){

        boolean verif = this.puntaje.getPuntos()>=this.precio;
        if(verif){
            this.puntaje.aumentarPuntos(-1*precio);
        }
        return verif;
    }

    public void setPrecio(int precio){
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPuntaje(Puntaje Puntaje) {
        this.puntaje = Puntaje;
    }

}
