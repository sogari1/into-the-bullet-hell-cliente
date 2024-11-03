package com.intothebullethell.game.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.intothebullethell.game.entidades.Enemigo;
import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.entidades.Proyectil;
import com.intothebullethell.game.mecanicas.GenerarEnemigos;

public class MapManager {

	public EnemigoManager grupoEnemigos;
	public ProyectilManager grupoProyectiles;
	
	private GenerarEnemigos generadorEnemigos;
	
	public MapManager(OrthographicCamera camara, TiledMap map, Jugador[] jugadores, TileColisionManager tileCollisionManager) {
		crearGrupo();
		//CLIENTE NO GENERA ENEMIGOS
//		this.generadorEnemigos = new GenerarEnemigos(camara, RenderManager.mapa, grupoEnemigos.getEntidades(), jugadores, tileCollisionManager, this);
	}
	public void crearGrupo() {
		this.grupoEnemigos = new EnemigoManager();
		this.grupoProyectiles = new ProyectilManager();
	}
	public void update(float delta, Jugador[] jugadores) {
//		CLIENTE NO ACTUALIZA 
//		grupoEnemigos.update(delta);
//		grupoProyectiles.actualizarProyectiles(delta, grupoEnemigos.getEntidades(), jugadores);
		
	}
	public void draw() {
		grupoEnemigos.draw();
		grupoProyectiles.draw();
	}
	public void reset() {
		grupoEnemigos.reset();
		grupoProyectiles.reset();
	}
	public void moverEnemigo(int enemigoId, float x, float y) {
		grupoEnemigos.getEntidades().get(enemigoId).setPosition(x, y);
	}
	public void añadirEnemigo(Enemigo enemigo, float x, float y) {
		enemigo.setPosition(x, y);
		grupoEnemigos.añadirEntidad(enemigo);
	}
	public void removerEnemigo(int enemigoId) {
		grupoEnemigos.getEntidades().remove(enemigoId);
	}
	public void añadirProyectil(Proyectil proyectil) {
		grupoProyectiles.agregarProyectil(proyectil);
	}
	public void moverProyectil(int proyectilId, float x, float y) {
        grupoProyectiles.actualizarProyectilPosicion(proyectilId, x, y);
    }
	public void generarEnemigos() {
        generadorEnemigos.generarEnemigos();
    }
	public EnemigoManager getgrupoEnemigos(){
        return grupoEnemigos;
    }
	 public ProyectilManager getGrupoProyectiles() {
	        return grupoProyectiles;
	}
}
