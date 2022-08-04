package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.region.Region;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Poligono;

import java.util.ArrayList;

public class Generador<D extends Poligono, C extends Casilla> {
    private int lineasMax;
    private GenLinea<C> genlinea;
    private UnirLineas<D,C> unirLineas;
    private ArrayList<C> casillas;
    public Generador(int lineasMax, GenLinea<C> genlinea, UnirLineas<D,C> unirLineas){
        this.lineasMax = lineasMax;
        this.genlinea = genlinea;
        this.unirLineas = unirLineas;
        this.casillas = new ArrayList<>();
    }

    public ArrayList<C> getCasillas() {
        return this.casillas;
    }

    public ArrayList<Region<D,C>> generarRegiones(GenRegiones<D, C> genRegiones){
        return genRegiones.generar(this.casillas);
    }
    public void generarTablero(){
        ArrayList<C> antLinea = this.genlinea.generar(1);
        ArrayList<C> actLinea;
        casillas.addAll(antLinea);
        for (int i = 2; i <= lineasMax; i++){
            actLinea = this.genlinea.generar(i);
            this.unirLineas.unir(antLinea,actLinea,i);
            antLinea = actLinea;
            this.casillas.addAll(antLinea);
        }
    }
}
