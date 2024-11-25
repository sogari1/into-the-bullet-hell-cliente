package com.intothebullethell.game.entidades;

import com.intothebullethell.game.globales.RecursoRuta;

public class EnemigoNormal extends Enemigo {
    public EnemigoNormal() {
        super(RecursoRuta.SPRITE_ROBOT_1, RecursoRuta.SPRITE_ROBOT_2, 15, 10);
    }
    @Override
    public void atacar() {
    }
}

