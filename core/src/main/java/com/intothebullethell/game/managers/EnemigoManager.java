package com.intothebullethell.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.intothebullethell.game.entidades.Enemigo;

public class EnemigoManager {

	private List<Enemigo> enemigos = new ArrayList<>();
	    
	public void añadirEntidad(Enemigo entidad) {
		enemigos.add(entidad);
	}
	public void update(float delta) {
		for (int i = 0; i < enemigos.size(); i++) {
			Enemigo enemigo = enemigos.get(i);
			enemigo.update(delta); 
			if (enemigo.estaMuerto()) {
				removerEntidad(i);
				i--; 
			}
		}
	}
	public void draw() {
		for (Enemigo enemigo : enemigos) {
			enemigo.draw(RenderManager.batchRender);
		}
	}
	private void removerEntidad(int index) {
		enemigos.remove(index);
	}
	public List<Enemigo> getEntidades() {
		return enemigos;
	}
	public void reset(){
		enemigos.clear();
	}
}
