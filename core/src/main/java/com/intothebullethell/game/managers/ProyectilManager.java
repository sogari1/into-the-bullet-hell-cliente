package com.intothebullethell.game.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.intothebullethell.game.entidades.Enemigo;
import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.entidades.Proyectil;
import com.intothebullethell.game.objects.armas.Arma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProyectilManager {
    public List<Proyectil> proyectiles = new ArrayList<>();
    private TileColisionManager tileColisionManager = new TileColisionManager();
    private List<Proyectil> proyectilesAEliminar = new ArrayList<>(); // Lista temporal para proyectiles a eliminar

    public void agregarProyectil(Proyectil proyectil) {
        proyectiles.add(proyectil);
    }

    public void actualizarProyectiles(float delta, List<Enemigo> enemigos, Jugador[] jugadores) {
        proyectilesAEliminar.clear(); // Limpiamos la lista de proyectiles a eliminar antes de actualizar

        for (Proyectil proyectil : proyectiles) {
            proyectil.update(delta);

            if (chequearColisionProyectil(proyectil, enemigos, jugadores) || tileColisionManager.esColision(proyectil.getBoundingRectangle())) {
                proyectilesAEliminar.add(proyectil); // Añadimos el proyectil a la lista temporal
            }
        }
    }

    public void actualizarProyectilPosicion(int proyectilId, float x, float y) {
        for (Proyectil proyectil : proyectiles) {
            proyectil.setPosition(x, y);
        }
    }

    private boolean chequearColisionProyectil(Proyectil proyectil, List<Enemigo> enemigos, Jugador[] jugadores) {
        for (Enemigo enemigo : enemigos) {
            if (proyectil.collidesWith(enemigo) && proyectil.isDisparadoPorJugador()) {
                enemigo.recibirDaño(proyectil.getDaño());
                return true;
            }
        }

        for (Jugador jugador : jugadores) {
            if (proyectil.collidesWith(jugador) && !proyectil.isDisparadoPorJugador()) {
                jugador.recibirDaño(proyectil.getDaño());
                return true;
            }
        }

        return false;
    }

    public void dispararProyectil(OrthographicCamera camara, Arma arma, float jugadorX, float jugadorY, int screenX, int screenY) {
        if (arma.puedeDisparar()) {
            Vector3 unprojected = camara.unproject(new Vector3(screenX, screenY, 0));
            Vector2 target = new Vector2(unprojected.x, unprojected.y);
            Vector2 position = new Vector2(jugadorX, jugadorY);

            arma.disparar(position, target, proyectiles);

            if (!arma.esMunicionInfinita()) {
                arma.dispararProyectil(position, target, proyectiles);
            }
        }
    }

    public void draw() {
        for (Proyectil proyectil : proyectiles) {
            proyectil.draw(RenderManager.batchRender);
        }

        // Eliminamos proyectiles después de completar el ciclo de dibujo para evitar conflictos
        proyectiles.removeAll(proyectilesAEliminar);
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
        proyectilesAEliminar.clear();
    }
}


