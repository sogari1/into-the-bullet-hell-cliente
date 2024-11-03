package com.intothebullethell.game.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.intothebullethell.game.globales.AssetRuta;

public abstract class RenderManager {

	public static SpriteBatch batch = new SpriteBatch();
	public static TiledMap mapa = new TmxMapLoader().load(AssetRuta.MAPA);;
	public static OrthogonalTiledMapRenderer render = new OrthogonalTiledMapRenderer(mapa);;
	public static SpriteBatch batchRender = (SpriteBatch) render.getBatch();
	
    public static void renderizarCamara(OrthographicCamera camara) {
    	render.setView(camara);
    	render.render();
    }

}
