package com.lanzabruno.ayp.logica;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.contenedor.Contenedor;
import com.lanzabruno.ayp.logica.contenedor.GestorContenedores;
import com.lanzabruno.ayp.logica.formas.Poligono;
import com.lanzabruno.ayp.pantallas.PantallaJuego;

import java.util.ArrayList;

public class EstadoJuego<D extends Poligono> {
    boolean derrota;
    ArrayList<D> direcciones;
    public EstadoJuego(ArrayList<D> direcciones){
        this.direcciones = direcciones;
        this.derrota = false;
    }
    private boolean determinarDerrota(GestorContenedores gestor, Casilla casilla){
        ArrayList<Contenedor> contenedoresOriginales = new ArrayList<>();
        contenedoresOriginales.addAll(gestor.getContenedores());
        boolean res = false;
        for (int i = 0; i < 6 && !res; i++){
            gestor.rotarTodosContenedores();
            ArrayList<Contenedor> contenedores = gestor.getContenedores();
            EncajaTablero encaja = new EncajaTablero(contenedores);
            casilla.aplicar(encaja, new ArrayList(), this.direcciones, new ArrayList<>());
            res = encaja.getEncaja();
        }
        gestor.setContenedores(contenedoresOriginales);
        return res;
    }

    public boolean getDerrota() {
        return this.derrota;
    }

    public void actualizarEstado(GestorContenedores gestor, Casilla casilla){
        this.derrota = !this.determinarDerrota(gestor, casilla);
    }
}
