package com.lanzabruno.ayp.grafica;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Triangulo;

public class ViewCasilla extends ImageButton {
    private int id;
    public Casilla logCasilla;
    Rectangle rect;
    ViewMartillo viewMartillo;
    public ViewCasilla(Texture texture, final Casilla<Triangulo> logCasilla, ViewMartillo viewMartillo,
                       float x, float y){
        super(new TextureRegionDrawable(new TextureRegion(texture)));
        this.logCasilla = logCasilla;
        if (!logCasilla.isArriba()) {
            this.getImage().setScaleY(-1);
        }
        this.viewMartillo = viewMartillo;
        this.setWidth(28);
        this.setHeight(28);
        this.getImage().setOrigin(this.getWidth()/2, this.getHeight()/2);
        this.addAction(sequence(alpha(0),fadeIn(0.5f)));

        this.setPosition(x - this.getWidth() / 2,y);
        rect = new Rectangle(this.getX() +  5,this.getY() + 5 , 14,14);
        actualizarColor();

        this.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y){
                ViewCasilla viewCasilla = (ViewCasilla) event.getListenerActor();
                viewCasilla.aplicarComodin();
                viewCasilla.actualizarColor();
            }
        });

    }
    public void actualizarColor(){
        switch (logCasilla.getContenido()){
            case "azul":
                this.getImage().setColor(Color.BLUE);
                break;
            case "rojo":
                this.getImage().setColor(Color.RED);
                break;
            case "naranja":
                this.getImage().setColor(Color.ORANGE);
                break;
            case "lima":
                this.getImage().setColor(Color.LIME);
                break;
            case "violeta":
                this.getImage().setColor(Color.VIOLET);
                break;
            case "VACIO":
                this.getImage().setColor(0.36f,0.42f,0.56f, 1f);
                break;
        }
    }
    public Casilla<Triangulo> getLogCasilla(){
        return this.logCasilla;
    }
    public Rectangle getRect() {
        return this.rect;
    }
    public void aplicarComodin(){
        this.viewMartillo.aplicar(this);
    }
}
