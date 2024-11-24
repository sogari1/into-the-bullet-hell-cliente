package com.intothebullethell.game.entidades;


import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.managers.EntidadManager;

public class EnemigoFuerte extends Enemigo {
	public EnemigoFuerte(Jugador[] jugadores, EntidadManager entidadManager) {
		super(RecursoRuta.ENEMIGO, 10, 18, 14f, 2, 50, RecursoRuta.PROYECTIL_ESCOPETA, jugadores, entidadManager);
	}
	@Override
	public void atacar() {
	}


}
