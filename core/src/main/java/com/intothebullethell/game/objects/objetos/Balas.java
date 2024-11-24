package com.intothebullethell.game.objects.objetos;

import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.RecursoRuta;

public class Balas extends Objeto {

	public Balas() {
		super(RecursoRuta.BALAS);
	}
    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.getArmaEquipada().aumentarBalasEnReserva(30);; 
        this.recogido = true; 
    }
	@Override
	public String getTipo() {
		return "balas";
	}
}
