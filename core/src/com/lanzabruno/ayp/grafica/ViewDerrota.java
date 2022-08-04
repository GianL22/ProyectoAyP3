package com.lanzabruno.ayp.grafica;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lanzabruno.ayp.logica.EstadoJuego;

public class ViewDerrota extends ImageButton {
    EstadoJuego estadoJuego;
    Stage stage;
    public ViewDerrota(Texture textura, EstadoJuego estadoJuego, Stage stage){
        super(new TextureRegionDrawable(new TextureRegion(textura)));
        this.estadoJuego = estadoJuego;
        this.stage = stage;
        this.setWidth(250);
        this.setHeight(150);
        this.getImage().setOrigin(100,50);
        this.setPosition((stage.getWidth()/2)-125, 0);
        this.addAction(sequence(alpha(0),fadeIn(0.5f)));
    }
    public void notificar(){
        if (this.estadoJuego.getDerrota()){
            stage.addActor(this);
        }
    }
}
