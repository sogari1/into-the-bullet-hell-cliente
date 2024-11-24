package com.intothebullethell.game.entidades;


import com.badlogic.gdx.graphics.Texture;
import com.intothebullethell.game.managers.EntidadManager;
import com.intothebullethell.game.managers.ProyectilManager;
public abstract class Enemigo extends Entidad {
    protected Jugador[] jugadores;
    protected ProyectilManager proyectilManager;
    protected EntidadManager entidadManager; 
    
    protected float intervaloAtaque;
    protected float tiempoAtaque;
    protected float projectilVelocidad;
    protected int daño;

    public Enemigo(Texture texture, int vida, int velocidad, float intervaloAtaque, int daño, float projectilVelocidad, Texture projectilTextura, Jugador[] jugadores, EntidadManager entidadManager) {
        super(texture, vida, velocidad, projectilTextura);
        this.jugadores = jugadores;
        this.intervaloAtaque = intervaloAtaque;
        this.tiempoAtaque = intervaloAtaque;
        this.daño = daño;
        this.projectilVelocidad = projectilVelocidad;
        this.entidadManager = entidadManager; 
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void atacar() {
    }

    @Override
    public void recibirDaño(int daño) {
    }
}
