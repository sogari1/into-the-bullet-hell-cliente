package com.intothebullethell.game.network;


public interface NetworkActionsListener {
	
	//jugador
	void empezarJuego();
	void actualizarJugadorPosicion(int jugadorId, float x, float y);
	void actualizarDireccionJugador(int jugadorId, String region);
	void actualizarVidaJugador(int jugadorId, int vidaActual);
	
	//enemigo
	void añadirEnemigo(String tipoEnemigo, float x, float y);
	void moverEnemigo(int enemigoId, float x, float y);
	void removerEnemigo(int enemigoId);
	
	//proyectil
	void añadirProyectil(String tipoProyectil, float x, float y, float velocidad, int daño, boolean disparadoPorJugador);
	void actualizarProyectilPosicion(int proyectilId, float x, float y);
	void removerProyectil(int proyectilId);
	
	//hud
	void actualizarTiempo(int tiempo);
	void actualizarRonda(int ronda);
	void actualizarEnemigosRestantes(int cantidad);
}
