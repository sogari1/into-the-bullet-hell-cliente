package com.intothebullethell.game.network;

public interface NetworkActionsListener {
	
	void empezarJuego();
	void actualizarJugadorPosicion(int jugadorId, float x, float y);
	void añadirEnemigo(float x, float y);
	void moverEnemigo(int enemigoId, float x, float y);
	void removerEnemigo();
	void actualizarProyectilPosicion(int proyectilId, float x, float y);
	void actualizarTiempo(int tiempo);
}
