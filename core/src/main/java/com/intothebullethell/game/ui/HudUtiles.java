package com.intothebullethell.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.intothebullethell.game.managers.RenderManager;
import com.intothebullethell.game.objects.armas.Arma;

public class HudUtiles {
	
	public static void dibujarCorazones(Texture corazonLleno, Texture corazonMitad, Texture corazonVacio, int vidaMaxima, int vidaActual, int x, int y) {
	    int vidaPorCorazon = 2;
	    int corazonesMaximos = vidaMaxima / vidaPorCorazon;
	    int corazonesLlenos = vidaActual / vidaPorCorazon;
	    boolean tieneMedioCorazon = (vidaActual % vidaPorCorazon) > 0;

	    int corazonesPorFila = 5;
	    int filaEspaciado = corazonLleno.getHeight() + 5; 

	    for (int i = 0; i < corazonesMaximos; i++) {
	        int row = i / corazonesPorFila; 
	        int col = i % corazonesPorFila; 

	        int posX = x + col * corazonLleno.getWidth();
	        int posY = y - row * filaEspaciado;

	        if (i < corazonesLlenos) {
	        	RenderManager.batch.draw(corazonLleno, posX, posY);
	        } else if (tieneMedioCorazon && i == corazonesLlenos) {
	        	RenderManager.batch.draw(corazonMitad, posX, posY);
	        } else {
	        	RenderManager.batch.draw(corazonVacio, posX, posY);
	        }
	    }
	}


	public static void dibujarMunicion(Arma armaActual, Texto municionArma) {
	    if (armaActual != null) {
	        String ammoText = armaActual.esMunicionInfinita() ? 
	            "Reserva: " + armaActual.getBalasEnReserva() + " / INF" :
	            "Cargador: " + armaActual.getBalasEnMunicion() + " / Reserva: " + armaActual.getBalasEnReserva();
	        
	        municionArma.setTexto(ammoText);
	        municionArma.setPosition(10, Gdx.graphics.getHeight() - 675);

	        municionArma.draw();
	    }
	}

}
