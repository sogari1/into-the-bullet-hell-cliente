package com.intothebullethell.game.objects.objetos;

import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.RecursoRuta;

public class Corazon extends Objeto {

    public Corazon() {
        super(RecursoRuta.CORAZON);
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.aumentarVida(2); 
        this.recogido = true; 
    }

	@Override
	public String getTipo() {
		return "corazon";
	}
}
