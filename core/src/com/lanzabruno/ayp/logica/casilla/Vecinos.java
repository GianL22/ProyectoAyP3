package com.lanzabruno.ayp.logica.casilla;
import com.lanzabruno.ayp.logica.formas.Poligono;

import java.util.*;

public class Vecinos<D extends Poligono,C extends Casilla> {
    HashMap<D,C> mapeo = new HashMap<>();
    public void agregar(D dir, C casilla){
        this.mapeo.put(dir,casilla);
    }
    public C get(D dir){
        return this.mapeo.get(dir);
    }
    public ArrayList<C> getAll(){
        ArrayList<C> Adyacencias = new ArrayList<C>();
        Set<D> keys = this.mapeo.keySet();
        for (D dir : keys) {
            Adyacencias.add(get(dir));
        }
        return Adyacencias;
    }

}
