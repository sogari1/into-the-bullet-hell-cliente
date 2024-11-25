package com.intothebullethell.game.objects.agarrables;

import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class CajaMunicion extends Agarrable {

	public CajaMunicion() {
		super(RecursoRuta.CAJA_MUNICION, SonidoRuta.CAJA_MUNICION);
	}
    @Override
    public void aplicarEfecto(Jugador jugador) {
        
    }
	@Override
	public String getTipo() {
		return "CajaMunicion";
	}
}
