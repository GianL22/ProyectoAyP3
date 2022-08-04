package com.lanzabruno.ayp.grafica;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lanzabruno.ayp.logica.comodines.Martillo;

public class ViewMartillo extends ImageButton {
    Stage stage;
    Martillo logmartillo;
    boolean activado;
    public ViewMartillo(Martillo logmartillo, Texture textura, Stage stage){
        super(new TextureRegionDrawable(new TextureRegion(textura)));
        this.activado = false;
        this.stage = stage;
        this.logmartillo= logmartillo;
        this.setPosition(stage.getWidth() - 48, 10);
        this.setWidth(48); this.setHeight(48);
        this.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y){
                ViewMartillo viewMartillo= (ViewMartillo) event.getListenerActor();
                viewMartillo.tocar();
                System.out.println("Click " + viewMartillo.getActivo());
            }
        });
        this.actualizarColor();
        this.stage.addActor(this);
    }
    private void actualizarColor(){
        if (this.activado)  this.getImage().setColor(Color.ORANGE);
        else this.getImage().setColor(Color.WHITE);
    }
    public boolean getActivo(){
        return this.activado;
    }
    public void tocar(){
        this.activado = !this.activado;
        this.actualizarColor();
    }
    public void aplicar(ViewCasilla viewCasilla){
        if (this.activado) this.logmartillo.aplicar(viewCasilla.getLogCasilla());
    }
    public int getPrecio(){
        return this.logmartillo.getPrecio();
    }

}
