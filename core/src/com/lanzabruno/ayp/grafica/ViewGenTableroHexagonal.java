package com.lanzabruno.ayp.grafica;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Triangulo;

import java.util.ArrayList;

public class ViewGenTableroHexagonal {
    ArrayList<ViewCasilla> vcs;
    Texture texture;
    Stage stage;
    ArrayList<Casilla<Triangulo>> casillas;
    ViewGenLineaHexagono genlinea;
    ViewMartillo viewMartillo;
    public ViewGenTableroHexagonal(Texture texture,ArrayList<Casilla<Triangulo>> casillas,
                                   Stage stage, ViewMartillo viewMartillo){
        this.casillas = casillas;
        this.texture = texture;
        this.stage = stage;
        this.viewMartillo = viewMartillo;
    }

    public void generar(){
        genlinea = new ViewGenLineaHexagono(this.texture, this.stage);
        int limitei = -5, limitef = 0, aux=0 ;
        for(int i = 0; i < 3; i++){
            aux = limitef - limitei + 2;
            limitei = limitef;
            limitef += aux;
            genlinea.generar(stage.getWidth()/2,
                    stage.getHeight()/2 + 38 * (3 - i),
                    this.casillas.subList(limitei,limitef), this.viewMartillo);
        }
        for(int i = 0; i < 3; i++){
            limitei = limitef;
            limitef += aux;
            genlinea.generar(stage.getWidth()/2,
                    stage.getHeight()/2  - 38 * (i),
                    this.casillas.subList(limitei,limitef),this.viewMartillo);
            aux -=2;
        }


        this.vcs = genlinea.getVcs();

    }

    public ArrayList<ViewCasilla> getVcs() {
        return this.genlinea.getVcs();
    }
}
