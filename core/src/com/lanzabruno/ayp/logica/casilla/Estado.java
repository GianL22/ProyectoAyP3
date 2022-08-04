package com.lanzabruno.ayp.logica.casilla;

public abstract class Estado{
    String contenido;
    public abstract boolean insertable();
    public abstract Estado desocupar();
    public abstract Estado insertar(Estado e);
    public abstract String getContenido();

}
