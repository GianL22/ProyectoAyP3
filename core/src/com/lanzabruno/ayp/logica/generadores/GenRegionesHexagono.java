package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.region.MismosColores;
import com.lanzabruno.ayp.logica.region.Region;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Triangulo;

import java.util.ArrayList;

public class GenRegionesHexagono extends GenRegiones<Triangulo, Casilla<Triangulo>>{
    public ArrayList<Triangulo> obtenerCaminoRegion(){
        ArrayList<Triangulo> camino = new ArrayList<>();
        camino.add(Triangulo.DER);
        camino.add(Triangulo.DER);
        camino.add(Triangulo.ABA);
        camino.add(Triangulo.IZQ);
        camino.add(Triangulo.IZQ);
        return camino;
    }
    public ArrayList<Region<Triangulo,Casilla<Triangulo>>> generar(ArrayList<Casilla<Triangulo>> casillas){
        ArrayList<Region<Triangulo,Casilla<Triangulo>>> Regiones = new ArrayList<>();
        Region<Triangulo,Casilla<Triangulo>> r;
        int id = 0;
        RecogerCasillas<Casilla<Triangulo>> recogerCasillas;
        for (Casilla<Triangulo> c : casillas){
            if (c.isArriba()){
                this.caminoRegion = this.obtenerCaminoRegion();
                recogerCasillas = new RecogerCasillas<>(6);
                c.aplicar(recogerCasillas, this.caminoRegion);
                if (recogerCasillas.recogidas() == 6){
                    r = new Region<>(id++, new MismosColores<Casilla<Triangulo>>());
                    r.setCasillas(recogerCasillas.getCasillas());
                    Regiones.add(r);
                }
            }
        }
        return Regiones;
    };
}
