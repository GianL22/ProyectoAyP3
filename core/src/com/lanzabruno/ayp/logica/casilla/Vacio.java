package com.lanzabruno.ayp.logica.casilla;

public class Vacio extends Estado {
    public Vacio(){
        this.contenido = "VACIO";
    }
    @Override
    public boolean insertable() {
        return true;
    }
    @Override
    public Estado desocupar() {
        return this;
    }
    @Override
    public Estado insertar(Estado e) {
        return e;
    }
    @Override
    public String getContenido() {
        return this.contenido;
    }
}
