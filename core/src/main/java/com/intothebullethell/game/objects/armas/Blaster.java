package com.intothebullethell.game.objects.armas;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.intothebullethell.game.entidades.Proyectil;
import com.intothebullethell.game.globales.NetworkData;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class Blaster extends Arma {

	public Blaster() {
		super("Blaster", 500, 6, 2.0f, 10, 20,  RecursoRuta.PROYECTIL_BLASTER_1, RecursoRuta.PROYECTIL_BLASTER_2, RecursoRuta.ARMA_BLASTER, SonidoRuta.DISPARO_BLASTER);
	}

}
