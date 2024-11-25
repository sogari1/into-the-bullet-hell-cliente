package com.intothebullethell.game.entidades;


import com.intothebullethell.game.globales.RecursoRuta;

public class EnemigoRapido extends Enemigo {
    public EnemigoRapido() {
        super(RecursoRuta.SPRITE_MEDUSA_1, RecursoRuta.SPRITE_MEDUSA_2, 20, 15);
    }
    @Override
    public void atacar() {
    }

}
