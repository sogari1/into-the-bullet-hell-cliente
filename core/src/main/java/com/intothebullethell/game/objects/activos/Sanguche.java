package com.intothebullethell.game.objects.activos;

import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.RecursoRuta;

public class Sanguche extends Activo {


    public Sanguche() {
        super("Sanguche", 7.0f, RecursoRuta.SANGUCHE, null);
    }

    @Override
    protected void aplicarEfecto(Jugador jugador) {
    }

	@Override
	protected void revertirEfecto(Jugador jugador) {
	}
}
