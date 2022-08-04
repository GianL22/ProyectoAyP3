package com.lanzabruno.ayp.grafica;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class DetectarColisiones {
    ArrayList<ViewContenedor> viewContenedores;
    ArrayList<ViewCasilla> viewCasillas;
    public DetectarColisiones( ArrayList<ViewContenedor> viewContenedores,
                               ArrayList<ViewCasilla> viewCasillas){
        this.viewCasillas = viewCasillas;
        this.viewContenedores = viewContenedores;
    }
    //Esto es por ahora
    public void detectar(){
        for(ViewCasilla vc : this.viewCasillas){
            for (ViewContenedor vcont: this.viewContenedores){
                if (vcont.getRect().overlaps(vc.getRect())){
                    vc.getImage().setColor(1f,1f,1f,1f);
                }
            }
        }
    }
    public ViewCasilla detectar(ViewContenedor vcont){
        for(ViewCasilla vc : this.viewCasillas){
            if (vcont.getRect().overlaps(vc.getRect())){
                return vc;
            }
        }
        return null;
    }
}
