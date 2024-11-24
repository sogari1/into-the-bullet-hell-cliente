package com.intothebullethell.game.objects.objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.intothebullethell.game.entidades.Jugador;

public abstract class Objeto extends Sprite {
    protected boolean recogido;
    
    public Objeto(Texture textura) {
        super(textura);
        this.recogido = false;
    }

    public abstract void aplicarEfecto(Jugador jugador);

    public boolean isRecogido() {
        return recogido;
    }

    public void setRecogido(boolean recogido) {
        this.recogido = recogido;
    }
    public abstract String getTipo();
}
