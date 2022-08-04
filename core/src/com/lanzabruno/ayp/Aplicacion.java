package com.lanzabruno.ayp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import com.lanzabruno.ayp.pantallas.PantallaCarga;
import com.lanzabruno.ayp.pantallas.PantallaJuego;


public class Aplicacion extends Game {
	//Atributos base de la aplicacion
	public static final String TITULO = "PROYECTO AYP";
	public static final float VERSION = 0.01f;
	public static final int V_ANCHO = 270;
	public static final int V_ALTO = 600;

	//Camara, AssetManager y Batch
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public AssetManager assets;

	//Pantallas
	public PantallaJuego pantallaJuego;
	public PantallaCarga pantallaCarga;
	public BitmapFont font;
	private FreeTypeFontGenerator fontgenerator;
	@Override
	public void create () {
		//Instanciamos batch y camara
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, V_ANCHO, V_ALTO);
		assets = new AssetManager();

		//Configuración font
		FreeTypeFontGenerator fontgenerator = new FreeTypeFontGenerator(
				Gdx.files.internal("Bellerose.ttf")
		);
		FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter =
				new FreeTypeFontGenerator.FreeTypeFontParameter();
		freeTypeFontParameter.size = 24;
		fontgenerator.generateData(freeTypeFontParameter);
		this.font = fontgenerator.generateFont(freeTypeFontParameter);

		//Instanciamos las pantallas
		pantallaJuego = new PantallaJuego(this);
		pantallaCarga = new PantallaCarga(this);

		//establecemos la pantalla principal
		this.setScreen(pantallaCarga);

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		assets.dispose();
		pantallaJuego.dispose();
		pantallaCarga.dispose();

	}
	public BitmapFont getFont(){
		return this.font;
	}
	public Batch getBatch(){
		return this.batch;
	}
}
