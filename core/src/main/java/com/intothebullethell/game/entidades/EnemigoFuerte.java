package com.intothebullethell.game.entidades;


import com.intothebullethell.game.globales.RecursoRuta;

public class EnemigoFuerte extends Enemigo {
	public EnemigoFuerte() {
		super(RecursoRuta.SPRITE_SLIME_1, RecursoRuta.SPRITE_SLIME_2, 10, 18);
	}
	@Override
	public void atacar() {
	}


}
