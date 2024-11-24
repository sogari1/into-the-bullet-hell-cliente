package com.intothebullethell.game.globales;

import com.intothebullethell.sonido.EfectoSonido;


public class SonidoRuta {
	//JUGADOR
	public static final EfectoSonido JUGADOR_GOLPE = new EfectoSonido("sonidos/otros/golpe.wav");
	
	//DISPAROS ARMAS
	public static final EfectoSonido DISPARIO_PISTOLA = new EfectoSonido("sonidos/armas/Pistola.wav");
	public static final EfectoSonido DISPARIO_ESCOPETA = new EfectoSonido("sonidos/armas/Escopeta.wav");
	
	//BENGALA SONIDO
	public static final EfectoSonido BENGALA = new EfectoSonido("sonidos/otros/bengala.mp3");
	
	//OBJETOS AGARRABLES
	public static final EfectoSonido CAJA_VIDA = new EfectoSonido("sonidos/objetosAgarrables/caja_vida.wav");
	public static final EfectoSonido CAJA_MUNICION = new EfectoSonido("sonidos/objetosAgarrables/caja_municion.wav");
	
	//OBJETOS ACTIVOS
	public static final EfectoSonido ADRENALINA = new EfectoSonido("sonidos/objetosActivos/adrenalina.wav");
	public static final EfectoSonido SANGUCHE = new EfectoSonido("sonidos/objetosActivos/sanguche.wav");
	
	//OTROS
	public static final EfectoSonido RANDOMIZADOR = new EfectoSonido("sonidos/otros/randomizer.wav");
}
