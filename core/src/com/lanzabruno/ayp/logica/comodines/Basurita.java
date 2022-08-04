package com.lanzabruno.ayp.logica.comodines;

import com.lanzabruno.ayp.logica.contenedor.Contenedor;
import com.lanzabruno.ayp.logica.contenedor.GestorContenedores;
import com.lanzabruno.ayp.logica.puntaje.Puntaje;

public class Basurita extends Comodin<Contenedor> {

    GestorContenedores gestor;

    public Basurita(int precio, Puntaje puntaje, GestorContenedores gestor){
        this.setPuntaje(puntaje);
        this.setPrecio(precio);
        this.gestor = gestor;
    }

    @Override
    public void aumentarPrecio() {
        this.setPrecio(this.getPrecio()+300);
    }

    @Override
    public void aplicar(Contenedor d) {
        if(this.available()){
            this.gestor.generarNuevoContenedor(d);
            this.aumentarPrecio();
        }
    }

}
