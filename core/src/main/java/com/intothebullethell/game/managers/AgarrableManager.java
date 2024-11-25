package com.intothebullethell.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.intothebullethell.game.objects.agarrables.Agarrable;


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
    public List<Agarrable> getAgarrables() {
		return agarrables;
	}
    public void reset() {
    	agarrables.clear();
    }
}
