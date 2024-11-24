package com.intothebullethell.game.entidades;

import com.intothebullethell.game.globales.RecursoRuta;

public class EnemigoNormal extends Enemigo {
    public EnemigoNormal() {
        super(RecursoRuta.ENEMIGO, 15, 10, 12f, 1, 60, RecursoRuta.PROYECTIL_ENEMIGO);
    }
    @Override
    public void atacar() {
    }
}

