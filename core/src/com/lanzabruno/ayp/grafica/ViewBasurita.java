package com.lanzabruno.ayp.grafica;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lanzabruno.ayp.logica.comodines.Basurita;
import com.lanzabruno.ayp.logica.comodines.Martillo;

public class ViewBasurita extends ImageButton {
    Stage stage;
    Basurita logbasurita;
    boolean activado;
    public ViewBasurita(Basurita logbasurita, Texture textura, Stage stage){
        super(new TextureRegionDrawable(new TextureRegion(textura)));
        this.activado = false;
        this.stage = stage;
        this.logbasurita= logbasurita;
        this.setPosition(10, 10);
        this.setWidth(40); this.setHeight(40);
        this.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y){
                ViewBasurita viewBasurita = (ViewBasurita) event.getListenerActor();
                viewBasurita.tocar();
                System.out.println("Click " + viewBasurita.getActivo());
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
    public void aplicar(ViewContenedor viewContenedor){
        System.out.println(this.logbasurita +  " " + viewContenedor.getLogcontenedor());
        if (this.activado) {
            this.logbasurita.aplicar(viewContenedor.getLogcontenedor());
        }
    }
    public int getPrecio(){
        return this.logbasurita.getPrecio();
    }
}
