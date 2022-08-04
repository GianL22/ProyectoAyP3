package com.lanzabruno.ayp.logica.casilla;

public class ConFicha extends Estado{
    public ConFicha(String contenido){
        this.contenido = contenido;
    }
    @Override
    public boolean insertable() {
        return false;
    }
    @Override
    public Estado desocupar() {
        return new Vacio();
    }
    //Nunca debería ocurrir
    @Override
    public Estado insertar(Estado e) {
        return this;
    }

    @Override
    public String getContenido() {
        return this.contenido;
    }
}
