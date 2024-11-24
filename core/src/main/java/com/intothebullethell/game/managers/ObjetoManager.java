package com.intothebullethell.game.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.objects.objetos.Objeto;

public class ObjetoManager {
	
	private List<Objeto> objetos = new ArrayList<>();

    public void agregarObjeto(Objeto objeto) {
    	objetos.add(objeto);
    }


    public void draw() {
        for (Objeto objeto : objetos) {
            objeto.draw(RenderManager.batchRender);
        }
    }
    public List<Objeto> getObjetos() {
		return objetos;
	}
    public void reset() {
    	objetos.clear();
    }
}
