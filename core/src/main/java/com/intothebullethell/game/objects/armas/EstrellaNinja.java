package com.intothebullethell.game.objects.armas;


import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class EstrellaNinja extends Arma {

	public EstrellaNinja() {
		super("Estrella ninja", 200, 20, 4.0f, 1, 4,  RecursoRuta.PROYECTIL_ESTRELLA_1, RecursoRuta.PROYECTIL_ESTRELLA_2, RecursoRuta.ARMA_ESTRELLA, SonidoRuta.DISPARO_ESTRELLA);
	}
}
