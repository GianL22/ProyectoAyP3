package com.lanzabruno.ayp.logica.region;

//import juego.Juego;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Poligono;

import java.util.ArrayList;

public class Region<D extends Poligono, C extends Casilla>{
    private ArrayList<C> casillas;
    private int id;
    private boolean cumplir;
    RegionRegla regla;

    public Region(int id, RegionRegla<C> regla){
        this.casillas = new ArrayList<>();
        this.id = id;
        this.regla = regla;
    }
    public void setCasillas(ArrayList<C> casillas){
        this.casillas = casillas;
        for (C c : casillas) c.agregarRegion(this);
    }
    public ArrayList<C> getCasillas(){
        return this.casillas;
    }

    public int getId(){
        return this.id;
    }
    public void limpiar(){
        for (C c: this.casillas){
            c.desocupar();
            this.cumplir = false;
        }
    };
    boolean comprobar(){
        Casilla<D> antCasilla = this.casillas.get(0);
        for (C c: this.casillas){
            if (!this.regla.cumplir(antCasilla, c)) return false;
        }
        return true;
    }
    public void actualizar(){
        this.cumplir =this.comprobar();
    }
    public boolean cumple(){
        return this.cumplir;
    }
}
