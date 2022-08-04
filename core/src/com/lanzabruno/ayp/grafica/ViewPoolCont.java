package com.lanzabruno.ayp.grafica;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lanzabruno.ayp.logica.contenedor.Contenedor;
import com.lanzabruno.ayp.logica.contenedor.GestorContenedores;

import java.util.ArrayList;



public class ViewPoolCont {
    ArrayList<ViewContenedor> viewcontenedores;
    GestorContenedores gestor;
    ContTextura contTextura;
    Stage stage;
    DetectarColisiones detetector;
    ViewDerrota viewDerrota;
    ViewBasurita viewBasurita;
    public ViewPoolCont(GestorContenedores gestor, ViewDerrota viewDerrota,
                        ViewBasurita viewBasurita,ContTextura contTextura, Stage stage){
        this.stage = stage;
        this.viewBasurita = viewBasurita;
        this.contTextura = contTextura;
        this.gestor = gestor;
        this.viewDerrota = viewDerrota;
        this.generar();
    }
    private void generar(){
        this.viewcontenedores = new ArrayList<>();
        ViewContenedor viewContenedor;
        int i = -1;
        for (Contenedor c: this.gestor.getContenedores()) {
            viewContenedor = new ViewContenedor(contTextura.generarTextura(c),
                             this ,
                             c, this.viewBasurita,
                             (stage.getWidth()/2 + 100*i),
                          100
                             );
            this.stage.addActor(viewContenedor);
            this.viewcontenedores.add(viewContenedor);
            i++;
        }
    }

    public void generarNuevoViewContenedor(ViewContenedor viewcontenedor){
        if (this.viewcontenedores.contains(viewcontenedor)) {
            int index = this.viewcontenedores.indexOf(viewcontenedor);
            float xo = viewcontenedor.getXo();
            this.viewcontenedores.remove(viewcontenedor);
            viewcontenedor = new ViewContenedor(
                    contTextura.generarTextura(this.gestor.getContenedor(index)),
                    this ,
                    this.gestor.getContenedor(index),
                    this.viewBasurita,
                    xo,
                    100
            );
            this.stage.addActor(viewcontenedor);
            this.viewcontenedores.add(index, viewcontenedor);
        }
    }

    public void rotar(ViewContenedor viewcontenedor){
        if (this.viewcontenedores.contains(viewcontenedor)) {
            int index = this.viewcontenedores.indexOf(viewcontenedor);
            viewcontenedor.setLogcontenedor(this.gestor.getContenedor(index));
        }
    }
    public void jugada(){
        this.viewDerrota.notificar();
    }
    public void setDetetector(DetectarColisiones detetector){
        this.detetector = detetector;
    }
    public ViewCasilla colision(ViewContenedor vcont){
        return this.detetector.detectar(vcont);
    }
    public ArrayList<ViewContenedor> getContenedores() {
        return this.viewcontenedores;
    }
}
