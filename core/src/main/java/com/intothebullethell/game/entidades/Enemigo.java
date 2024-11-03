package com.intothebullethell.game.entidades;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.intothebullethell.game.managers.MapManager;
import com.intothebullethell.game.managers.ProyectilManager;
public abstract class Enemigo extends Entidad {
    protected float intervaloAtaque;
    protected float tiempoAtaque;
    protected Jugador[] jugadores;
    protected List<Enemigo> enemigos;
    protected float projectilVelocidad;
    protected int daño;
    protected ProyectilManager proyectilManager;
    protected MapManager mapManager; 

    public Enemigo(Texture texture, int vida, int velocidad, float intervaloAtaque, int daño, float projectilVelocidad, Texture projectilTextura, Jugador[] jugadores, List<Enemigo> enemigos,  MapManager mapManager) {
        super(texture, vida, velocidad, projectilTextura);
        this.jugadores = jugadores;
        this.enemigos = enemigos;
        this.intervaloAtaque = intervaloAtaque;
        this.tiempoAtaque = intervaloAtaque;
        this.daño = daño;
        this.projectilVelocidad = projectilVelocidad;
        this.mapManager = mapManager; 
    }

    @Override
    public void update(float delta) {
        moverHaciaJugador();
 
        tiempoAtaque -= delta;
        if (tiempoAtaque <= 0) {
            atacar();
            tiempoAtaque = intervaloAtaque;
        }
    }

    private void moverHaciaJugador() {
        Jugador jugadorObjetivo = obtenerJugadorMasCercano();
        if (jugadorObjetivo != null) {
            Vector2 position = new Vector2(getX(), getY());
            Vector2 target = new Vector2(jugadorObjetivo.getX(), jugadorObjetivo.getY());
            Vector2 direction = target.sub(position).nor();

            velocity.set(direction).scl(velocidad);

            mover(velocity);
        }
    }
    protected Jugador obtenerJugadorMasCercano() {
        Jugador jugadorCercano = null;
        float distanciaMinima = Float.MAX_VALUE;

        for (Jugador j : jugadores) {
            float distancia = Vector2.dst(getX(), getY(), j.getX(), j.getY());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                jugadorCercano = j;
            }
        }
        return jugadorCercano;
    }


    @Override
    public void atacar() {
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        boundingBox.setPosition(x, y); 
    }

    @Override
    public void recibirDaño(int daño) {
        vidaMaxima -= daño;
    }
    public boolean estaMuerto() {
    	if(vidaMaxima <= 0) {
    		return true;
    	}
    	return false;
    }
}
