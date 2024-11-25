package com.intothebullethell.game.objects.armas;


import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class AWP extends Arma {

	public AWP() {
		super("AWP", 500, 6, 2.0f, 10, 20,  RecursoRuta.PROYECTIL_AWP, RecursoRuta.PROYECTIL_AWP, RecursoRuta.ARMA_AWP, SonidoRuta.DISPARO_AWP);
	}

}
