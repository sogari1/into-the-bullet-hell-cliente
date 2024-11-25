package com.intothebullethell.game.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.intothebullethell.game.globales.RecursoRuta;

public class AnimacionEntidad {
	private TextureRegion[] jugadorAnimacionesArriba;
    private TextureRegion[] jugadorAnimacionesAbajo;
    private TextureRegion[] jugadorAnimacionesIzquierda;
    private TextureRegion[] jugadorAnimacionesDerecha;

	private Texture[] enemigoAnimacion;
	
    private float tiempoAnimacion = 0.6f; 
    private float temporizador = 0;  
    private int indiceFrame = 0; 

    public AnimacionEntidad() {
        this.jugadorAnimacionesArriba = new TextureRegion[]{RecursoRuta.SPRITE_ARRIBA_1, RecursoRuta.SPRITE_ARRIBA_2};
        this.jugadorAnimacionesAbajo = new TextureRegion[]{RecursoRuta.SPRITE_ABAJO_1, RecursoRuta.SPRITE_ABAJO_2};
        this.jugadorAnimacionesIzquierda = new TextureRegion[]{RecursoRuta.SPRITE_IZQUIERDA_1, RecursoRuta.SPRITE_IZQUIERDA_2};
        this.jugadorAnimacionesDerecha = new TextureRegion[]{RecursoRuta.SPRITE_DERECHA_1, RecursoRuta.SPRITE_DERECHA_2};
    }
    
    public AnimacionEntidad(Texture[] enemigoAnimacion) {
        this.enemigoAnimacion = enemigoAnimacion;
    }
    
    public void actualizarAnimacionJugador(Jugador jugador, String direccion, boolean moviendose) {
        if (moviendose) {
            temporizador += Gdx.graphics.getDeltaTime();

            if (temporizador >= tiempoAnimacion) {
                indiceFrame = (indiceFrame + 1) % 2; 
                temporizador = 0;
            }
        } else {
            indiceFrame = 0;
        }
        switch (direccion) {
            case "arriba":
                jugador.setRegion(jugadorAnimacionesArriba[indiceFrame]);
                break;
            case "abajo":
                jugador.setRegion(jugadorAnimacionesAbajo[indiceFrame]);
                break;
            case "izquierda":
                jugador.setRegion(jugadorAnimacionesIzquierda[indiceFrame]);
                break;
            case "derecha":
                jugador.setRegion(jugadorAnimacionesDerecha[indiceFrame]);
                break;
        }
    }

    public Texture actualizarAnimacionEntidad(float delta) {
        temporizador += delta;

        if (temporizador >= tiempoAnimacion) {
            indiceFrame = (indiceFrame + 1) % enemigoAnimacion.length;
            temporizador = 0;
        }
        return enemigoAnimacion[indiceFrame];
    }
    public TextureRegion getJugadorAnimacionesArribaUno() {
		return jugadorAnimacionesArriba[0];
	}
    public TextureRegion getJugadorAnimacionesAbajoUno() {
		return jugadorAnimacionesAbajo[0];
	}
    public TextureRegion getJugadorAnimacionesIzquierdaUno() {
		return jugadorAnimacionesIzquierda[0];
	}
    public TextureRegion getJugadorAnimacionesDerechaUno() {
		return jugadorAnimacionesDerecha[0];
	}
}
