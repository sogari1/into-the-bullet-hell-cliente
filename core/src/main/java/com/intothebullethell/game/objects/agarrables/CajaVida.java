package com.intothebullethell.game.objects.agarrables;

import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class CajaVida extends Agarrable {

    public CajaVida() {
        super(RecursoRuta.CAJA_VIDA, SonidoRuta.CAJA_VIDA);
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
    }

	@Override
	public String getTipo() {
		return "CajaVida";
	}
}
