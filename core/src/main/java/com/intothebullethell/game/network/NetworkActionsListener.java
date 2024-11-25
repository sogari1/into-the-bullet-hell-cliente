package com.intothebullethell.game.network;


public interface NetworkActionsListener {
	//juego
	void empezarJuego();
	void gameOver();
	void mostrarError(String mensaje);
	
	//jugador
	void actualizarJugadorPosicion(int jugadorId, float x, float y);
	void actualizarDireccionJugador(int jugadorId, String region, boolean moviendose);
	void actualizarVidaJugador(int jugadorId, int vidaMaxima, int vidaActual);
	void actualizarArmaJugador(int jugadorId, String nombreArma);
	void actualizarActivoJugador(int jugadorId, String nombreActivo);
	void actualizarOpacidadJugador(int jugadorId, float opacidad);
	void jugadorMuerto(int jugadorId);
	
	//enemigo
	void añadirEnemigo(String tipoEnemigo);
	void moverEnemigo(int enemigoId, float x, float y);
	void removerEnemigo(int enemigoId);
	
	//proyectil
	void añadirProyectil(String tipoProyectil);
	void actualizarProyectilPosicion(int proyectilId, float x, float y);
	void removerProyectil(int proyectilId);
	
	//objetos agarrables
	void añadirAgarrable(String tipoAgarrable, float x, float y);
	void removerAgarrable(int agarrableId);
	
	//hud
	void actualizarTiempo(int tiempo);
	void actualizarRonda(int ronda);
	void actualizarEnemigosRestantes(int cantidad);
	void actualizarCantidadBengalas(int jugadorId, int cantidad);
	void actualizarBalasArmaJugador(int jugadorId, int balasEnReserva, int balasEnMunicion);
	void activoUsadoJugador(int jugadorId, boolean usado);
}
