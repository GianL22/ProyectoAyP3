package com.lanzabruno.ayp.logica.casilla;

import com.lanzabruno.ayp.logica.region.Funcion;
import com.lanzabruno.ayp.logica.formas.Poligono;
import com.lanzabruno.ayp.logica.region.Region;

import java.util.ArrayList;
import java.util.List;

public class Casilla<D extends Poligono>{
    private boolean arriba;
    public Estado estado;
    private int id;
    private ArrayList<Region<D,Casilla<D>>> regiones;
    private Vecinos vecinos;
    
    public Casilla(boolean arriba, int id){
        this.arriba = arriba;
        this.id = id;
        this.vecinos = new Vecinos();
        this.regiones = new ArrayList<>();
        //Pasarle por el constructor el estado
        this.estado = new Vacio();
    }
    public int getid() {
        return this.id;
    }
    public boolean isArriba() {
        return arriba;
    }
    public boolean ocupado() {
        return !this.estado.insertable();
    }
    public void desocupar(){
        this.estado = this.estado.desocupar();
    }
    public void actualizarEstado(Estado e){
        this.estado = this.estado.insertar(e);
        for (Region<D,Casilla<D>> region : this.regiones){
            region.actualizar();
        }
    }
    public Estado getEstado(){
        return this.estado;
    }
    public String getContenido(){
        return this.estado.getContenido();
    }

    public Casilla getVecinos(D dir){
        return this.vecinos.get(dir);
    }
    public void agregarRegion(Region<D,Casilla<D>> region){
        this.regiones.add(region);
    }
    public void agregarVecino(D dir, Casilla<D> c){
        if (this.vecinos.get(dir) != null) {
            this.vecinos.get(dir).agregarVecino(dir, c);
        } else {
          c.vecinos.agregar(dir.opuesto(), this);
          this.vecinos.agregar(dir,c);
        }
    }
    //Para buscar y generar contenededor
    public  void  aplicar(Funcion<Casilla<D>> f, List<Integer> v, ArrayList<D> dirreciones, ArrayList<D> camino) {
        f.aplicar(this);
        v.add(this.id);
        for (D dir : dirreciones) {
            Casilla<D> c = this.getVecinos(dir);
            if (c != null && f.predicado(c) && !v.contains(c.getid())) {
                camino.add(dir);
                c.aplicar(f, v, dirreciones,camino);
            }
        }
    }
    // encaja y encajar
    public  void  aplicar(Funcion<Casilla<D>> f, ArrayList<D> camino){
        if (f.predicado(this)) f.aplicar(this);
        if (camino.size() > 0){
            Casilla<D> c = this.getVecinos(camino.get(0));
            camino.remove(0);
            if (c != null) c.aplicar(f, camino);
        }
    }
}
