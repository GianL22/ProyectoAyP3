package com.lanzabruno.ayp.logica.contenedor;

import com.lanzabruno.ayp.logica.EstadoJuego;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.region.GestorRegiones;
import com.lanzabruno.ayp.logica.puntaje.EstrategiaContenedor;
import com.lanzabruno.ayp.logica.puntaje.Puntaje;

import java.util.ArrayList;

public class GestorContenedores {
    FabricaContenedores fabrica;
    GestorRegiones gestorRegiones;
    EstadoJuego estadoJuego;
    ArrayList<Contenedor> contenedores;
    Puntaje puntaje;
    int nContenedores;
    public GestorContenedores(FabricaContenedores fabrica, int nContenedores,
                              GestorRegiones gestorRegiones, EstadoJuego estadoJuego,
                              Puntaje puntaje){
        this.fabrica = fabrica;
        this.gestorRegiones = gestorRegiones;
        this.contenedores = new ArrayList<>();
        this.nContenedores = nContenedores;
        this.estadoJuego = estadoJuego;
        this.generar();
        this.puntaje = puntaje;
    }
    private void generar(){
        Contenedor contenedor;
        for (int i = 0; i < this.nContenedores; i++){
            contenedor = this.fabrica.generarContenedor();
            contenedor.setGestor(this);
            contenedores.add(contenedor);
        }
    }
    public void generarNuevoContenedor(Contenedor contenedor){
        if (this.contenedores.contains(contenedor)) {
            int index = this.contenedores.indexOf(contenedor);
            this.contenedores.remove(contenedor);
            contenedor = this.fabrica.generarContenedor();
            contenedor.setGestor(this);
            this.contenedores.add(index, contenedor);
        }
    }
    public void rotar(Contenedor contenedor){
        if (this.contenedores.contains(contenedor)) {
            int index = this.contenedores.indexOf(contenedor);
            this.contenedores.remove(contenedor);
            contenedor = this.fabrica.generarContenedorRotado(contenedor.getTipo(),
                         contenedor.getnCasillasOcupadas(), contenedor.getRotacion(),
                         contenedor.getColor());
            contenedor.setGestor(this);
            this.contenedores.add(index, contenedor);
        }
    }
    public void rotarTodosContenedores(){
        for (int i = 0; i<this.nContenedores; i++){
            this.contenedores.get(i).rotar();
        }
    }
    public ArrayList<Contenedor> getContenedores() {
        return this.contenedores;
    }
    public void setContenedores(ArrayList<Contenedor> contenedores){
        this.contenedores = contenedores;
    }
    public Contenedor getContenedor(int index){
        return this.contenedores.get(index);
    }
    public int getnContenedores(){
        return this.nContenedores;
    }
    public void jugada(Casilla casillaJugada, int NFichasColocadas){
        this.gestorRegiones.limpiar();
        //Se rotan cada uno de los contenedores para verificar si existe aún alguna jugada
        this.estadoJuego.actualizarEstado(this,casillaJugada);
        this.puntaje.sumarPuntos(NFichasColocadas, new EstrategiaContenedor());
        System.out.println(this.estadoJuego.getDerrota());
    }
}
