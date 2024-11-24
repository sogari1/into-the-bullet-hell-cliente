package com.intothebullethell.game.objects.activos;

import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.RecursoRuta;

public class Adrenalina extends Activo {


    public Adrenalina() {
        super("Adrenalina", 7.0f, RecursoRuta.ADRENALINA, null);
    }

	@Override
	protected void aplicarEfecto(Jugador jugador) {
	}

	@Override
	protected void revertirEfecto(Jugador jugador) {
	}
}
