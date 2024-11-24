package com.intothebullethell.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.intothebullethell.game.entidades.Enemigo;
import com.intothebullethell.game.entidades.EnemigoFuerte;
import com.intothebullethell.game.entidades.EnemigoNormal;
import com.intothebullethell.game.entidades.EnemigoRapido;
import com.intothebullethell.game.entidades.Proyectil;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.objects.objetos.Agarrable;
import com.intothebullethell.game.objects.objetos.CajaMunicion;
import com.intothebullethell.game.objects.objetos.CajaVida;

public class EntidadManager {

	public EnemigoManager grupoEnemigos;
	public ProyectilManager grupoProyectiles;
	public AgarrableManager grupoAgarrables;
	
	
	public EntidadManager() {
		crearGrupo();
	}
	public void crearGrupo() {
		this.grupoAgarrables = new AgarrableManager();
		this.grupoEnemigos = new EnemigoManager();
		this.grupoProyectiles = new ProyectilManager();
	}
	public void draw() {
		grupoEnemigos.draw();
		grupoProyectiles.draw();
		grupoAgarrables.draw();
	}
	public void reset() {
		grupoEnemigos.reset();
		grupoProyectiles.reset();
		grupoAgarrables.reset();
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
		Agarrable agarrable = obtenerObjetoDesdeTipo(tipoObjeto);
		agarrable.setPosition(x, y);
		grupoAgarrables.agregarAgarrable(agarrable);	
	}
	public void removerObjeto(int objetoId) {
		grupoAgarrables.getObjetos().remove(objetoId);
	}
	public EnemigoManager getgrupoEnemigos(){
        return grupoEnemigos;
	}
	public ProyectilManager getGrupoProyectiles() {
	        return grupoProyectiles;
	}
	public AgarrableManager getObjetoManager() {
		return grupoAgarrables;
	}
	private Enemigo crearEnemigoDesdeTipo(String tipoEnemigo) {
		Enemigo enemigo = null;
		switch (tipoEnemigo) {
		case "Normal":
			enemigo = new EnemigoNormal();
			break;
		case "Rapido":
			enemigo = new EnemigoRapido();
			break;
		case "Fuerte":
			enemigo = new EnemigoFuerte();
			break;
		}
		 return enemigo;
	}
	private Texture obtenerTexturaProyectil(String tipoProyectil) {
		Texture textura = null;
	    switch (tipoProyectil) {
	    case "Enemigo":
	    	textura = RecursoRuta.PROYECTIL_ENEMIGO;
	    	break;
	    case "Pistola":
	    	textura = RecursoRuta.PROYECTIL_PISTOLA;
	    	break;
		case "Escopeta":
			textura = RecursoRuta.PROYECTIL_ESCOPETA;
			break;
	    }
		return textura;
	}
	private Agarrable obtenerObjetoDesdeTipo(String tipoObjeto) {
		Agarrable objeto = null;
	    switch (tipoObjeto) {
	    case "CajaVida":
	    	objeto = new CajaVida();
	    	break;
	    case "CajaMunicion":
	    	objeto = new CajaMunicion();
	    	break;
	    }
		return objeto;
	}
}
