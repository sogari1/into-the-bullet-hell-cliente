package com.intothebullethell.game.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.intothebullethell.game.entidades.Enemigo;
import com.intothebullethell.game.entidades.EnemigoFuerte;
import com.intothebullethell.game.entidades.EnemigoNormal;
import com.intothebullethell.game.entidades.EnemigoRapido;
import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.entidades.Proyectil;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.objects.objetos.Balas;
import com.intothebullethell.game.objects.objetos.Corazon;
import com.intothebullethell.game.objects.objetos.Objeto;

public class EntidadManager {

	public EnemigoManager grupoEnemigos;
	public ProyectilManager grupoProyectiles;
	public ObjetoManager grupoObjetos;
	
	private Jugador[] jugadores;
	
	public EntidadManager(OrthographicCamera camara, Jugador[] jugadores) {
		this.jugadores = jugadores;
		crearGrupo();
	}
	public void crearGrupo() {
		this.grupoObjetos = new ObjetoManager();
		this.grupoEnemigos = new EnemigoManager();
		this.grupoProyectiles = new ProyectilManager();
	}
	public void draw() {
		grupoEnemigos.draw();
		grupoProyectiles.draw();
		grupoObjetos.draw();
	}
	public void reset() {
		grupoEnemigos.reset();
		grupoProyectiles.reset();
		grupoObjetos.reset();
	}
	public void añadirEnemigo(String tipoEnemigo, float x, float y) {
		Enemigo enemigo = crearEnemigoDesdeTipo(tipoEnemigo);
		enemigo.setPosition(x, y);
		grupoEnemigos.añadirEnemigo(enemigo);
	}
	public void moverEnemigo(int enemigoId, float x, float y) {
		grupoEnemigos.getEnemigos().get(enemigoId).setPosition(x, y);
	}
	public void removerEnemigo(int enemigoId) {
		grupoEnemigos.getEnemigos().remove(enemigoId);
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
	public void añadirObjeto(String tipoObjeto, float x, float y) {
		Objeto objeto = obtenerObjetoDesdeTipo(tipoObjeto);
		objeto.setPosition(x, y);
		grupoObjetos.agregarObjeto(objeto);	
	}
	public void removerObjeto(int objetoId) {
		grupoObjetos.getObjetos().remove(objetoId);
	}
	public EnemigoManager getgrupoEnemigos(){
        return grupoEnemigos;
	}
	public ProyectilManager getGrupoProyectiles() {
	        return grupoProyectiles;
	}
	public ObjetoManager getObjetoManager() {
		return grupoObjetos;
	}
	private Enemigo crearEnemigoDesdeTipo(String tipoEnemigo) {
		Enemigo enemigo = null;
		switch (tipoEnemigo) {
		case "Normal":
			enemigo = new EnemigoNormal(jugadores, this);
			break;
		case "Rapido":
			enemigo = new EnemigoRapido(jugadores, this);
			break;
		case "Fuerte":
			enemigo = new EnemigoFuerte(jugadores, this);
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
	private Objeto obtenerObjetoDesdeTipo(String tipoObjeto) {
		Objeto objeto = null;
	    switch (tipoObjeto) {
	    case "corazon":
	    	objeto = new Corazon();
	    	break;
	    case "balas":
	    	objeto = new Balas();
	    	break;
	    }
		return objeto;
	}
}
