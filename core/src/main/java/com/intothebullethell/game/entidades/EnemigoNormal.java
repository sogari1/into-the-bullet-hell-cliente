package com.intothebullethell.game.entidades;

import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.managers.EntidadManager;

public class EnemigoNormal extends Enemigo {
    public EnemigoNormal(Jugador[] jugadores, EntidadManager entidadManager) {
        super(RecursoRuta.ENEMIGO, 15, 10, 12f, 1, 60, RecursoRuta.PROYECTIL_ESCOPETA, jugadores, entidadManager);
    }
    @Override
    public void atacar() {
    }
}

