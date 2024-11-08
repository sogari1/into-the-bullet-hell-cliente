package com.intothebullethell.game.entidades;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.managers.EntidadManager;

public class EnemigoRapido extends Enemigo {
    public EnemigoRapido(Jugador[] jugadores, List<Enemigo> enemigos, EntidadManager entidadManager) {
        super(RecursoRuta.ENEMIGO, 20, 15, 10f, 1, 100, RecursoRuta.PROYECTIL_ESCOPETA, jugadores, enemigos, entidadManager);
    }
    @Override
    public void atacar() {
        Jugador jugadorObjetivo = obtenerJugadorMasCercano();
        if (jugadorObjetivo != null) {
            Vector2 position = new Vector2(getX() + getWidth() / 2, getY() + getHeight() / 2);
            Vector2 target = new Vector2(jugadorObjetivo.getX() + jugadorObjetivo.getWidth() / 2, jugadorObjetivo.getY() + jugadorObjetivo.getHeight() / 2);

//            entidadManager.grupoProyectiles.añadirProyectil(new Proyectil(getProjectilTextura(), position, target, projectilVelocidad, daño, false)); 
        }
    }

}
