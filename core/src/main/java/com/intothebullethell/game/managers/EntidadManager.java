package com.intothebullethell.game.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.intothebullethell.game.entidades.Enemigo;
import com.intothebullethell.game.entidades.EnemigoFuerte;
import com.intothebullethell.game.entidades.EnemigoNormal;
import com.intothebullethell.game.entidades.EnemigoRapido;
import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.entidades.Proyectil;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.mecanicas.GenerarEnemigos;

public class EntidadManager {

	public EnemigoManager grupoEnemigos;
	public ProyectilManager grupoProyectiles;
	
	private GenerarEnemigos generadorEnemigos;
	private Jugador[] jugadores;
	
	public EntidadManager(OrthographicCamera camara, TiledMap map, Jugador[] jugadores, TileColisionManager tileCollisionManager) {
		this.jugadores = jugadores;
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
	public void añadirEnemigo(String tipoEnemigo, float x, float y) {
		Enemigo enemigo = crearEnemigoDesdeTipo(tipoEnemigo);
		enemigo.setPosition(x, y);
		grupoEnemigos.añadirEntidad(enemigo);
	}
	public void moverEnemigo(int enemigoId, float x, float y) {
		grupoEnemigos.getEntidades().get(enemigoId).setPosition(x, y);
	}
	public void removerEnemigo(int enemigoId) {
		grupoEnemigos.getEntidades().remove(enemigoId);
	}
	
	public void añadirProyectil(String tipoProyectil, float x, float y, float velocidad, int daño, boolean disparadoPorJugador) {
		grupoProyectiles.agregarProyectil(new Proyectil(obtenerTexturaProyectil(tipoProyectil), new Vector2(x, y), new Vector2(x, y), velocidad, daño, disparadoPorJugador));
	}
	public void moverProyectil(int proyectilId, float x, float y) {
        grupoProyectiles.getProyectiles().get(proyectilId).setPosition(x, y);
    }
	public void removerProyectil(int proyectilId) {
		grupoProyectiles.getProyectiles().remove(proyectilId);
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
	private Enemigo crearEnemigoDesdeTipo(String tipoEnemigo) {
		Enemigo enemigo = null;
		switch (tipoEnemigo) {
		case "Normal":
			enemigo = new EnemigoNormal(jugadores, grupoEnemigos.getEntidades(), this);
			break;
		case "Rapido":
			enemigo = new EnemigoRapido(jugadores, grupoEnemigos.getEntidades(), this);
			break;
		case "Fuerte":
			enemigo = new EnemigoFuerte(jugadores, grupoEnemigos.getEntidades(), this);
			break;
		}
		 return enemigo;
	}
	private Texture obtenerTexturaProyectil(String tipoProyectil) {
		Texture textura = null;
	    switch (tipoProyectil) {
	    case "Pistola":
	    	textura = RecursoRuta.PROYECTIL_PISTOLA;
	    	break;
		case "Escopeta":
			textura = RecursoRuta.PROYECTIL_ESCOPETA;
			break;
	    }
		return textura;
	}
}
