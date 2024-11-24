package com.intothebullethell.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.intothebullethell.game.objects.objetos.Agarrable;


public class AgarrableManager {
	
	private List<Agarrable> agarrables = new ArrayList<>();

    public void agregarAgarrable(Agarrable agarrable) {
    	agarrables.add(agarrable);
    }

    public void draw() {
        for (Agarrable objeto : agarrables) {
            objeto.draw(RenderManager.batchRender);
        }
    }
    public List<Agarrable> getObjetos() {
		return agarrables;
	}
    public void reset() {
    	agarrables.clear();
    }
}
