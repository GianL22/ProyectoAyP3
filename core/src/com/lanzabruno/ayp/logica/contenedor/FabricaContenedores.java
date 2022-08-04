package com.lanzabruno.ayp.logica.contenedor;

import com.lanzabruno.ayp.logica.generadores.GenMolde;
import com.lanzabruno.ayp.logica.generadores.GeneradorContenedor;
import com.lanzabruno.ayp.logica.formas.Poligono;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.casilla.GenerarColor;

import java.util.ArrayList;
import java.util.Random;

public class FabricaContenedores<D extends Poligono, C extends Casilla> {
    Random random = new Random();
    GenMolde genMolde;
    ArrayList<D> direcciones;
    ArrayList<TipoContenedor> tiposContenedores;
    GenerarColor generarColor;
    public FabricaContenedores(GenMolde<D> genMolde, ArrayList<D> direcciones,
                               ArrayList<TipoContenedor> tipos, GenerarColor generarColor){
        this.genMolde = genMolde;
        this.direcciones = direcciones;
        this.tiposContenedores = tipos;
        this.generarColor = generarColor;
    }
    public Contenedor<C,D> generarContenedor(){
        ArrayList<C> casillas = this.genMolde.generar();
        TipoContenedor tipoContenedor = tiposContenedores.get(random.nextInt(this.tiposContenedores.size()));
        GeneradorContenedor generadorContenedor = tipoContenedor.generador();
        int contenedorNFichas = random.nextInt(generadorContenedor.getMaxFichas()) + 1;
        generadorContenedor.setNFichas(contenedorNFichas);
        generadorContenedor.setColorFichas(this.generarColor.generarColor());
        ArrayList<D> camino = new ArrayList<>();
        casillas.get(0).aplicar(generadorContenedor, new ArrayList<>(), this.direcciones, camino);
        return new Contenedor<>(contenedorNFichas, generadorContenedor.getCasillas(), camino,
                tipoContenedor, 0,generadorContenedor.getColorFichas());
    }
    private ArrayList<D> invertirDireccion(){
        ArrayList<D> dir = new ArrayList<>();
        for (D d: this.direcciones){
            dir.add(0, d);
        }
        return dir;
    }
    public Contenedor<C,D> generarContenedorRotado(TipoContenedor<C> tipo, int nFichas, int rotacion, String color){
        ArrayList<D> auxDir;
        ArrayList<C> casillas = this.genMolde.generar();
        GeneradorContenedor generadorContenedor = tipo.generador();
        generadorContenedor.setColorFichas(color);
        generadorContenedor.setNFichas(nFichas);
        ArrayList<D> camino = new ArrayList<>();
        rotacion += 60;
        if (rotacion >= 360) rotacion = 0;
        if (rotacion >= 180)  auxDir = this.invertirDireccion();
        else auxDir = this.direcciones;
        C casillaInicial = casillas.get(rotacion/ 60); //60 grados es lo que rota la figura
        casillaInicial.aplicar(generadorContenedor, new ArrayList<>(), auxDir, camino);
        return new Contenedor<>(nFichas, generadorContenedor.getCasillas(), camino,tipo, rotacion,color);
    }
}
