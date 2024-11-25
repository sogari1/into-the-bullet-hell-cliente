package com.intothebullethell.game.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Proyectil extends Sprite {
    private boolean disparadoPorJugador;
    private AnimacionEntidad animacionEntidad;
    
    public Proyectil(Texture sprite1, Texture sprite2) {
        super(sprite1);
        this.animacionEntidad = new AnimacionEntidad(new Texture[]{sprite1, sprite2});
    }
    public boolean isDisparadoPorJugador() {
        return disparadoPorJugador;
    }

    @Override
    public void draw(Batch batch) {
    	batch.draw(animacionEntidad.actualizarAnimacionEntidad(Gdx.graphics.getDeltaTime()), getX(), getY(), getWidth(), getHeight());
    }
}
