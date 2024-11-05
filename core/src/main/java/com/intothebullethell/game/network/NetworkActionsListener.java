package com.intothebullethell.game.network;


public interface NetworkActionsListener {
	
	void empezarJuego();
	void actualizarJugadorPosicion(int jugadorId, float x, float y);
	void actualizarDireccionJugador(int jugadorId, String region);
	
	void añadirEnemigo(String tipoEnemigo, float x, float y);
	void moverEnemigo(int enemigoId, float x, float y);
	void removerEnemigo(int enemigoId);
	
	void añadirProyectil(String tipoProyectil, float x, float y, float velocidad, int daño, boolean disparadoPorJugador);
	void actualizarProyectilPosicion(int proyectilId, float x, float y);
	void removerProyectil(int proyectilId);
	
	void actualizarTiempo(int tiempo);
}
