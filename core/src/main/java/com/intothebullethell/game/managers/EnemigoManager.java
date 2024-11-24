package com.intothebullethell.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.intothebullethell.game.entidades.Enemigo;

public class EnemigoManager {

	private List<Enemigo> enemigos = new ArrayList<>();
	    
	public void añadirEnemigo(Enemigo enemigo) {
		enemigos.add(enemigo);
	}
	public void update(float delta) {}
	
	public void draw() {
		for (Enemigo enemigo : enemigos) {
			enemigo.draw(RenderManager.batchRender);
		}
	}
	public List<Enemigo> getEnemigos() {
		return enemigos;
	}
	public void reset(){
		enemigos.clear();
	}
}
