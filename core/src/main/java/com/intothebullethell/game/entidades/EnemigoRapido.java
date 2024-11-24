package com.intothebullethell.game.entidades;


import com.intothebullethell.game.globales.RecursoRuta;

public class EnemigoRapido extends Enemigo {
    public EnemigoRapido() {
        super(RecursoRuta.ENEMIGO, 20, 15, 10f, 1, 100, RecursoRuta.PROYECTIL_ENEMIGO);
    }
    @Override
    public void atacar() {
    }

}
