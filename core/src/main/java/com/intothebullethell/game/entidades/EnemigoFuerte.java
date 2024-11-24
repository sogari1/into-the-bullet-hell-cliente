package com.intothebullethell.game.entidades;


import com.intothebullethell.game.globales.RecursoRuta;

public class EnemigoFuerte extends Enemigo {
	public EnemigoFuerte() {
		super(RecursoRuta.ENEMIGO, 10, 18, 14f, 2, 50, RecursoRuta.PROYECTIL_ENEMIGO);
	}
	@Override
	public void atacar() {
	}


}
