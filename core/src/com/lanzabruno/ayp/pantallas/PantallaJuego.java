package com.lanzabruno.ayp.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.lanzabruno.ayp.Aplicacion;
import com.lanzabruno.ayp.grafica.ContTextura;
import com.lanzabruno.ayp.grafica.DetectarColisiones;
import com.lanzabruno.ayp.grafica.ViewBasurita;
import com.lanzabruno.ayp.grafica.ViewCasilla;
import com.lanzabruno.ayp.grafica.ViewDerrota;
import com.lanzabruno.ayp.grafica.ViewGenTableroHexagonal;
import com.lanzabruno.ayp.grafica.ViewMartillo;
import com.lanzabruno.ayp.grafica.ViewPoolCont;
import com.lanzabruno.ayp.logica.EstadoJuego;
import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.comodines.Basurita;
import com.lanzabruno.ayp.logica.comodines.Martillo;
import com.lanzabruno.ayp.logica.contenedor.FabricaContenedores;
import com.lanzabruno.ayp.logica.generadores.GenLineaTriangulo;
import com.lanzabruno.ayp.logica.generadores.GenMoldeHexagono;
import com.lanzabruno.ayp.logica.generadores.GenRegionesHexagono;
import com.lanzabruno.ayp.logica.generadores.Generador;
import com.lanzabruno.ayp.logica.casilla.GenerarColor;
import com.lanzabruno.ayp.logica.contenedor.GestorContenedores;
import com.lanzabruno.ayp.logica.region.GestorRegiones;
import com.lanzabruno.ayp.logica.puntaje.Puntaje;
import com.lanzabruno.ayp.logica.region.Region;
import com.lanzabruno.ayp.logica.contenedor.TipoContAdyacentes;
import com.lanzabruno.ayp.logica.contenedor.TipoContAislados;
import com.lanzabruno.ayp.logica.contenedor.TipoContenedor;
import com.lanzabruno.ayp.logica.formas.Triangulo;
import com.lanzabruno.ayp.logica.generadores.UnirLineasTriangulo;

import java.util.ArrayList;

public class PantallaJuego implements Screen {
    private final Aplicacion app;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private FabricaContenedores<Triangulo, Casilla<Triangulo>> fabrica;
    private ContTextura contTextura;
    private GestorRegiones gestorRegiones;
    private GestorContenedores gestorContenedores;
    private ViewPoolCont viewPoolCont;
    private ViewGenTableroHexagonal genVTablero;
    private DetectarColisiones detectarColisiones;
    private EstadoJuego estadoJuego;
    private Puntaje puntaje;
    private Generador<Triangulo, Casilla<Triangulo>> generador;
    private BitmapFont font;
    private ViewDerrota viewDerrota;
    private ViewMartillo viewMartillo;
    private ViewBasurita viewBasurita;
    public PantallaJuego(final Aplicacion app){
        stage = new Stage(new StretchViewport(app.camera.viewportWidth,
                app.camera.viewportHeight,
                app.camera), app.batch);
        this.app = app;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        this.contTextura = new ContTextura(app.assets);
        this.font = this.app.getFont();
    }

    @Override
    public void show() {
        //Se establece el stage como el input processor
        ArrayList<Region<Triangulo,Casilla<Triangulo>>> regiones;
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        //Grafo
        this.puntaje = new Puntaje();
        this.viewMartillo = new ViewMartillo(new Martillo(50, this.puntaje),
                                            this.app.assets.get("martillo.png", Texture.class),
                                            this.stage
                                            );

        this.generador = new Generador<>(6, new GenLineaTriangulo(), new UnirLineasTriangulo());
        generador.generarTablero();
        regiones = generador.generarRegiones(new GenRegionesHexagono());
        this.gestorRegiones = new GestorRegiones(regiones,this.puntaje);
        //grafica
        genVTablero = new ViewGenTableroHexagonal(
                this.app.assets.get("casilla.png",Texture.class),
                generador.getCasillas(),
                this.stage,
                this.viewMartillo
                );
        genVTablero.generar();

        ArrayList<Triangulo> direcciones = new ArrayList<>();

        direcciones.add(Triangulo.DER);
        direcciones.add(Triangulo.ABA);
        direcciones.add(Triangulo.IZQ);
        TipoContAislados<Casilla<Triangulo>> tipoContAislados = new TipoContAislados<>();
        TipoContAdyacentes<Casilla<Triangulo>> tipoContAdyacentes = new TipoContAdyacentes<>();
        TipoContAdyacentes<Casilla<Triangulo>> tipoContAdyacentes2 = new TipoContAdyacentes<>();
        ArrayList<TipoContenedor> tipos = new ArrayList<>();
        tipos.add(tipoContAdyacentes);
        tipos.add(tipoContAdyacentes2);
        tipos.add(tipoContAislados);
        GenerarColor generarColor = new GenerarColor();
        generarColor.agregarColor("azul");
        generarColor.agregarColor("rojo");
        generarColor.agregarColor("naranja");
        generarColor.agregarColor("lima");
        generarColor.agregarColor("violeta");
        estadoJuego = new EstadoJuego(direcciones);
        viewDerrota = new ViewDerrota(this.app.assets.get("loose.png", Texture.class),
                                estadoJuego,this.stage);
        fabrica = new FabricaContenedores<>(new GenMoldeHexagono(), direcciones, tipos,generarColor);
        this.gestorContenedores = new GestorContenedores(this.fabrica, 3,this.gestorRegiones, this.estadoJuego, this.puntaje);
        this.viewBasurita = new ViewBasurita(
                new Basurita(50, this.puntaje,this.gestorContenedores),
                app.assets.get("basurita.png", Texture.class), this.stage
        );
        shapeRenderer.setProjectionMatrix(app.camera.combined);
        this.viewBasurita = new ViewBasurita(
                            new Basurita(50, this.puntaje,this.gestorContenedores),
                            app.assets.get("basurita.png", Texture.class), this.stage
                            );
        this.viewPoolCont = new ViewPoolCont(this.gestorContenedores,this.viewDerrota, this.viewBasurita, this.contTextura, this.stage);
        this.detectarColisiones = new DetectarColisiones(
                this.viewPoolCont.getContenedores(),
                this.genVTablero.getVcs()
        );
        this.viewPoolCont.setDetetector(this.detectarColisiones );
    }
    private void update(float delta){
        for (ViewCasilla c : this.genVTablero.getVcs()){
            c.actualizarColor();
        }
        this.detectarColisiones.detectar();
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        //Para colocar un color
        Gdx.gl.glClearColor(0.10f,0.11f,0.20f,1f);
        //Para limpiar la pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        //Violación Leu de demeter
        this.app.getBatch().begin();
        this.font.draw(this.app.getBatch(),String.valueOf(this.puntaje.getPuntos()),
                24 , this.stage.getHeight() - 60 );
        this.font.draw(this.app.getBatch(),String.valueOf(this.viewMartillo.getPrecio()),
                stage.getWidth() - 100, 40);
        this.font.draw(this.app.getBatch(),String.valueOf(this.viewBasurita.getPrecio()),
                80, 40);
        this.app.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
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
        this.stage.dispose();
        this.font.dispose();
    }
}
