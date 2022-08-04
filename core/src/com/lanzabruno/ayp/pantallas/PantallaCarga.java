package com.lanzabruno.ayp.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.lanzabruno.ayp.Aplicacion;

public class PantallaCarga implements Screen {
    private final Aplicacion app;

    //ShapeRenderer para renderizar la barra de carga
    private ShapeRenderer shapeRenderer;

    //Se usa para mostrar el progreso de la carga
    private float progresoCarga;

    public PantallaCarga(final Aplicacion app){
        this.app = app;
        shapeRenderer = new ShapeRenderer();
        System.out.println("Se entro por aqui");

    }

    private void colaRecursos(){
        //Aqui se cargan todos los assets del juego
        app.assets.load("casilla.png", Texture.class);
        app.assets.load("ady1.png", Texture.class);
        app.assets.load("ady2.png", Texture.class);
        app.assets.load("ady3.png", Texture.class);
        app.assets.load("ady4.png", Texture.class);
        app.assets.load("ady5.png", Texture.class);
        app.assets.load("ais1.png", Texture.class);
        app.assets.load("ais2.png", Texture.class);
        app.assets.load("ais3.png", Texture.class);
        app.assets.load("loose.png", Texture.class);
        app.assets.load("martillo.png", Texture.class);
        app.assets.load("basurita.png", Texture.class);

    }

    @Override
    public void show() {
        //Cuando se muestra esta pantalla, se inicia a cargar todos los assets
        progresoCarga = 0f;
        //Ajustar la resolución en android
        shapeRenderer.setProjectionMatrix(app.camera.combined);
        colaRecursos();
    }

    private void update(float delta){
        progresoCarga = MathUtils.lerp(progresoCarga,app.assets.getProgress(),.1f );
        if (app.assets.update() && progresoCarga >= app.assets.getProgress() - 0.01f){
            app.setScreen(app.pantallaJuego);
        }
    }
    @Override
    public void render(float delta) {
        //Se colorea el fondo
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        //Para limpiar la pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Se evalua si se cargaron todos los assets
        update(delta);

        //Se dibuja la barra de carga
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.LIME);
        shapeRenderer.rect(32,app.camera.viewportHeight / 2,progresoCarga * (app.camera.viewportWidth - 64),8);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
