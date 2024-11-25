package com.intothebullethell.game.entidades;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.managers.EntidadManager;
import com.intothebullethell.game.managers.ProyectilManager;
public abstract class Enemigo extends Entidad {
	private static final Texture[] PROYECTIL_TEXTURA = new Texture[]{RecursoRuta.PROYECTIL_ENEMIGO_1, RecursoRuta.PROYECTIL_ENEMIGO_2};
    protected ProyectilManager proyectilManager;
    protected EntidadManager entidadManager; 
    private AnimacionEntidad animacionEnemigo;
    
    protected float intervaloAtaque;
    protected float tiempoAtaque;
    protected float projectilVelocidad;
    protected int daño;

    public Enemigo(Texture sprite1, Texture sprite2, int vida, int velocidad) {
        super(sprite1, vida, velocidad, PROYECTIL_TEXTURA[0]);
        this.animacionEnemigo = new AnimacionEntidad(new Texture[]{sprite1, sprite2}); 
    }
    @Override
    public void draw(Batch batch) {
        batch.draw(animacionEnemigo.actualizarAnimacionEntidad(Gdx.graphics.getDeltaTime()), getX(), getY(), getWidth(), getHeight());
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
