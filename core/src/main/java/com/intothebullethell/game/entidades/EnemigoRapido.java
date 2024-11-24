package com.intothebullethell.game.entidades;


import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.managers.EntidadManager;

public class EnemigoRapido extends Enemigo {
    public EnemigoRapido(Jugador[] jugadores, EntidadManager entidadManager) {
        super(RecursoRuta.ENEMIGO, 20, 15, 10f, 1, 100, RecursoRuta.PROYECTIL_ESCOPETA, jugadores, entidadManager);
    }
    @Override
    public void atacar() {
    }

}
