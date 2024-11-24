package com.intothebullethell.game.managers;
import com.intothebullethell.game.entidades.Proyectil;
import java.util.ArrayList;
import java.util.List;

public class ProyectilManager {
    private List<Proyectil> proyectiles = new ArrayList<>();

    public void agregarProyectil(Proyectil proyectil) {
        proyectiles.add(proyectil);
    }

    public void eliminarProyectilesEnemigos() {
        proyectiles.removeIf(proyectil -> !proyectil.isDisparadoPorJugador());
    }
    public void draw() {
        for (Proyectil proyectil : new ArrayList<>(proyectiles)) { 
            proyectil.draw(RenderManager.batchRender);
        }
    }

    public List<Proyectil> getProyectiles() {
        return proyectiles;
    }

    public void dispose() {
        for (Proyectil proyectil : proyectiles) {
            proyectil.getTexture().dispose();
        }
    }

    public void reset() {
        proyectiles.clear();
    }
}
