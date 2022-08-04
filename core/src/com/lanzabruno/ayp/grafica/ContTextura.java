package com.lanzabruno.ayp.grafica;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.lanzabruno.ayp.logica.contenedor.Contenedor;


public class ContTextura {
    AssetManager assetManager;
    public ContTextura(AssetManager assetManager){
        this.assetManager = assetManager;
    }
    public Texture generarTextura(Contenedor contenedor){
        if (contenedor.getCamino().size()  + 1 == contenedor.getnCasillasOcupadas()){
            String root = "ady" + contenedor.getnCasillasOcupadas() + ".png";
            return assetManager.get(root, Texture.class);
        }
        String root = "ais" + contenedor.getnCasillasOcupadas() + ".png";
        return assetManager.get(root, Texture.class);
    }
}
