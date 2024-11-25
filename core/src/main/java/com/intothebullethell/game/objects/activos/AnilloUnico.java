package com.intothebullethell.game.objects.activos;

import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class AnilloUnico extends Activo {

	public AnilloUnico() {
		   super("Adrenalina", 20.0f, RecursoRuta.ANILLO_UNICO, SonidoRuta.ANILLO_UNICO);
	}

	@Override
	protected void aplicarEfecto(Jugador jugador) {
	}

	@Override
	protected void revertirEfecto(Jugador jugador) {
	}

}
