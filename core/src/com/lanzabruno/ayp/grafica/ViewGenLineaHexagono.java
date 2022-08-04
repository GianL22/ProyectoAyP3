package com.lanzabruno.ayp.grafica;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Triangulo;

import java.util.ArrayList;
import java.util.List;

public class ViewGenLineaHexagono {
    ArrayList<ViewCasilla> vcs;
    Stage stage;
    Texture texture;
    public ViewGenLineaHexagono(Texture texture, Stage stage){
        this.texture = texture;
        this.stage = stage;
        vcs = new ArrayList<>();

    }
    public void generar(float x, float y, List<Casilla<Triangulo>> casillas, ViewMartillo viewMartillo){
        int cont = 0;
        ViewCasilla vc;
        for (int i = -casillas.size()/2; i < ( casillas.size()/2) + 1; i++){
            vc = new ViewCasilla(this.texture, casillas.get(casillas.size()/2 + i), viewMartillo,x + 24*i, y + 6);
            vc.addAction(Actions.fadeIn(2f));
            this.stage.addActor(vc);
            vcs.add(vc);
        }
    }
    public ArrayList<ViewCasilla> getVcs() {
        return this.vcs;
    }
}
