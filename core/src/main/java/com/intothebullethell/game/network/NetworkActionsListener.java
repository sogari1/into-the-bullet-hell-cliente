package com.intothebullethell.game.network;


public interface NetworkActionsListener {
	//juego
	void empezarJuego();
	void gameOver();
	void mostrarError(String mensaje);
	
	//jugador
	void actualizarJugadorPosicion(int jugadorId, float x, float y);
	void actualizarDireccionJugador(int jugadorId, String region);
	void actualizarVidaJugador(int jugadorId, int vidaActual);
	void actualizarArmaJugador(int jugadorId, String nombreArma);
	void actualizarBalasArmaJugador(int jugadorId, int balasEnReserva, int balasEnMunicion);
	void actualizarActivoJugador(int jugadorId, String nombreActivo);
	void activoUsadoJugador(int jugadorId, boolean usado);
	void jugadorMuerto(int jugadorId);
	
	//enemigo
	void añadirEnemigo(String tipoEnemigo, float x, float y);
	void moverEnemigo(int enemigoId, float x, float y);
	void removerEnemigo(int enemigoId);
	
	//proyectil
	void añadirProyectil(String tipoProyectil, float x, float y, float velocidad, int daño, boolean disparadoPorJugador);
	void actualizarProyectilPosicion(int proyectilId, float x, float y);
	void removerProyectil(int proyectilId);
	
	//objetos
	void añadirObjeto(String tipoObjeto, float x, float y);
	void removerObjeto(int objetoId);
	
	//hud
	void actualizarTiempo(int tiempo);
	void actualizarRonda(int ronda);
	void actualizarEnemigosRestantes(int cantidad);
}
