package com.intothebullethell.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.intothebullethell.game.entidades.Enemigo;
import com.intothebullethell.game.entidades.EnemigoFuerte;
import com.intothebullethell.game.entidades.EnemigoNormal;
import com.intothebullethell.game.entidades.EnemigoRapido;
import com.intothebullethell.game.entidades.Proyectil;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.objects.agarrables.Agarrable;
import com.intothebullethell.game.objects.agarrables.CajaMunicion;
import com.intothebullethell.game.objects.agarrables.CajaVida;

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
	public void añadirEnemigo(String tipoEnemigo) {
		grupoEnemigos.añadirEnemigo(crearEnemigoDesdeTipo(tipoEnemigo));
	}
	public void moverEnemigo(int enemigoId, float x, float y) {
		grupoEnemigos.getEnemigos().get(enemigoId).setPosition(x, y);
	}
	public void removerEnemigo(int enemigoId) {
		grupoEnemigos.getEnemigos().remove(enemigoId);
	}
	
	public void añadirProyectil(String tipoProyectil) {
		Texture[] texturas = obtenerTexturaProyectil(tipoProyectil);
		grupoProyectiles.agregarProyectil(new Proyectil(texturas[0], texturas[1]));
	}
	public void moverProyectil(int proyectilId, float x, float y) {
        grupoProyectiles.getProyectiles().get(proyectilId).setPosition(x, y);
    }
	public void removerProyectil(int proyectilId) {
		grupoProyectiles.getProyectiles().remove(proyectilId);
	}
	public void añadirAgarrable(String tipoAgarrable, float x, float y) {
		Agarrable agarrable = obtenerObjetoDesdeTipo(tipoAgarrable);
		agarrable.setPosition(x, y);
		grupoAgarrables.agregarAgarrable(agarrable);	
	}
	public void removerAgarrable(int agarrableId) {
		grupoAgarrables.getAgarrables().remove(agarrableId);
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
	private Texture[] obtenerTexturaProyectil(String tipoProyectil) {
		Texture texturaUno = null;
		Texture texturaDos = null;
	    switch (tipoProyectil) {
	    case "Enemigo":
	    	texturaUno = RecursoRuta.PROYECTIL_ENEMIGO_1;
	    	texturaDos = RecursoRuta.PROYECTIL_ENEMIGO_2;
	    	break;
	    case "Pistola":
	    	texturaUno = RecursoRuta.PROYECTIL_PISTOLA;
	    	texturaDos = RecursoRuta.PROYECTIL_PISTOLA;
	    	break;
		case "Escopeta":
			texturaUno = RecursoRuta.PROYECTIL_ESCOPETA;
			texturaDos = RecursoRuta.PROYECTIL_ESCOPETA;
			break;
		case "BFG 9000":
			texturaUno = RecursoRuta.PROYECTIL_BFG9000_1;
			texturaDos = RecursoRuta.PROYECTIL_BFG9000_2;
			break;
		case "Sniper":
			texturaUno = RecursoRuta.PROYECTIL_SNIPER;
			texturaDos = RecursoRuta.PROYECTIL_SNIPER;
			break;
		case "AWP":
			texturaUno = RecursoRuta.PROYECTIL_AWP;
			texturaDos = RecursoRuta.PROYECTIL_AWP;
			break;
		case "Blaster":
			texturaUno = RecursoRuta.PROYECTIL_BLASTER_1;
			texturaDos = RecursoRuta.PROYECTIL_BLASTER_2;
			break;
		case "Estrella ninja":
			texturaUno = RecursoRuta.PROYECTIL_ESTRELLA_1;
			texturaDos = RecursoRuta.PROYECTIL_ESTRELLA_2;
			break;
			
	    }
	    return new Texture[]{texturaUno, texturaDos};
	}
	private Agarrable obtenerObjetoDesdeTipo(String tipoAgarrable) {
		Agarrable agarrable = null;
	    switch (tipoAgarrable) {
	    case "CajaVida":
	    	agarrable = new CajaVida();
	    	break;
	    case "CajaMunicion":
	    	agarrable = new CajaMunicion();
	    	break;
	    }
		return agarrable;
	}
}
