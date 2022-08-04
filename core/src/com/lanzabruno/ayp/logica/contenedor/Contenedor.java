package com.lanzabruno.ayp.logica.contenedor;

import com.lanzabruno.ayp.logica.formas.Poligono;
import com.lanzabruno.ayp.logica.casilla.Casilla;

import java.util.ArrayList;

public class Contenedor <C extends Casilla, D extends Poligono>{

    int nCasillasOcupadas;
    TipoContenedor tipo;
    ArrayList<C> casillasContenedor;
    ArrayList<D> camino;
    GestorContenedores gestor;
    String color;
    int rotacion;
    public Contenedor(int nCasillasOcupadas, ArrayList<C> casillasContenedor,
                      ArrayList<D> camino, TipoContenedor tipo, int rotacion, String color){
        this.nCasillasOcupadas = nCasillasOcupadas;
        this.casillasContenedor = casillasContenedor;
        this.camino = camino;
        this.tipo = tipo;
        this.rotacion = rotacion;
        this.color = color;
    }
    public boolean encaja(C casilla){
        ArrayList<C> auxCas = new ArrayList<>();
        auxCas.addAll(this.casillasContenedor);
        ArrayList<D> auxCam = new ArrayList<>();
        auxCam.addAll(this.camino);
        Encaja encaja = new Encaja<>(auxCas);
        casilla.aplicar(encaja,auxCam);
        return encaja.getCasillasValidas() >= this.nCasillasOcupadas;
    }
    public void encajar(C casilla){
        if (this.encaja((casilla))){
            ArrayList<C> auxCas = new ArrayList<>();
            auxCas.addAll(this.casillasContenedor);
            ArrayList<D> auxCam = new ArrayList<>();
            auxCam.addAll(this.camino);
            Encajar encajar = new Encajar<>(this.casillasContenedor,this.nCasillasOcupadas);
            casilla.aplicar(encajar,this.camino);
            this.gestor.generarNuevoContenedor(this);
            this.gestor.jugada(casilla, this.nCasillasOcupadas);
        }
    }
    public void rotar(){
        this.gestor.rotar(this);
    }
    public void setGestor(GestorContenedores gestor){
        this.gestor = gestor;
    }
    public ArrayList<D> getCamino() {
        return this.camino;
    }
    public int getnCasillasOcupadas() {
        return this.nCasillasOcupadas;
    }
    public int getRotacion() {
        return this.rotacion;
    }
    public TipoContenedor getTipo() {
        return this.tipo;
    }
    public String getColor() {
        return this.color;
    }
}
