package com.intothebullethell.game.objects.armas;


import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class Sniper extends Arma {

	public Sniper() {
		super("Sniper", 400, 4, 1.0f, 10, 30,  RecursoRuta.PROYECTIL_SNIPER, RecursoRuta.PROYECTIL_SNIPER, RecursoRuta.ARMA_SNIPER, SonidoRuta.DISPARO_SNIPER);
	}

}
