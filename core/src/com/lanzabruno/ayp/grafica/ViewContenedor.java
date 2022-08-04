package com.lanzabruno.ayp.grafica;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.contenedor.Contenedor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


public class ViewContenedor extends ImageButton {
    public Contenedor logcontenedor;
    Rectangle rect;
    Image myImage;
    ViewPoolCont poolCont;
    ViewBasurita viewBasurita;
    public float xo,yo;

    public ViewContenedor(Texture texture, ViewPoolCont poolCont, Contenedor contenedor,ViewBasurita viewBasurita, float x, float y){
        super(new TextureRegionDrawable(new TextureRegion(texture)));
        this.setWidth(90);
        this.setHeight(90);
        this.viewBasurita = viewBasurita;
        this.xo = x;
        this.yo = y;
        this.poolCont = poolCont;
        myImage = this.getImage();
        myImage.setOrigin(this.getWidth()/2, this.getHeight()/2);
        this.setPosition(this.xo  - this.getWidth() / 2, this.yo );
        rect = new Rectangle(this.getX() + this.getWidth()  / 2 - 6,this.getY() + this.getHeight()  / 2, 10,10);
        this.logcontenedor = contenedor;


        this.addListener(new DragListener() {
            public void dragStop (InputEvent event, float x, float y, int pointer) {
                ViewContenedor vc = (ViewContenedor) event.getListenerActor();
                ViewCasilla vCasillasColis = vc.colision();
                if (vCasillasColis != null && vc.encaja(vCasillasColis)){
                        vc.encajar(vCasillasColis);
                }else{
                    vc.setPosition(vc.getXo() - vc.getWidth() / 2, vc.getYo() );
                    rect = new Rectangle(vc.getX() + vc.getWidth()  / 2 - 6,vc.getY() + vc.getHeight()  / 2, 10,10);
                }
            }
            public void drag(InputEvent event, float x, float y, int pointer) {
                ViewContenedor vc = (ViewContenedor) event.getListenerActor();
                event.getListenerActor().moveBy((x - 24 )/ 2, (y - 24)/ 2);
                rect = new Rectangle(vc.getX() + vc.getWidth()  / 2 - 6,vc.getY() + vc.getHeight()  / 2, 10,10);
            }

        });
        this.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y){
                ViewContenedor vc = (ViewContenedor) event.getListenerActor();
                vc.aplicarComodin();
                vc.rotar();
                vc.getImage().addAction(Actions.rotateBy(-60f,0.2f, Interpolation.smooth));
            }
        });
        this.actualizarColor();
        this.addAction(sequence(alpha(0),fadeIn(0.2f)));

    }
    public void actualizarColor(){
        switch (this.logcontenedor.getColor()){
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
    public boolean encaja(ViewCasilla viewCasilla){
        return this.logcontenedor.encaja(viewCasilla.getLogCasilla());
    }
    public void encajar(ViewCasilla viewCasilla){
        this.logcontenedor.encajar(viewCasilla.getLogCasilla());
        this.poolCont.generarNuevoViewContenedor(this);
        this.poolCont.jugada();
        this.remove();
    }
    public void rotar(){
        if (!viewBasurita.getActivo()){
            this.logcontenedor.rotar();
            this.poolCont.rotar(this);
        }
    }
    public void setLogcontenedor(Contenedor contenedor){
        this.logcontenedor = contenedor;
    }
    public Contenedor getLogcontenedor() {
        return this.logcontenedor;
    }

    public ViewPoolCont getPoolCont() {
        return this.poolCont;
    }

    public float getXo() {
        return this.xo;
    }
    public float getYo() {
        return this.yo;
    }

    public ViewCasilla colision(){
        return this.poolCont.colision(this);
    }
    public Rectangle getRect() {
        return this.rect;
    }
    public void aplicarComodin(){
        if (this.viewBasurita.getActivo()) {
            this.viewBasurita.aplicar(this);
            this.poolCont.generarNuevoViewContenedor(this);
            this.remove();
        }
    }
}
