package com.intothebullethell.game.objects.agarrables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.sonido.EfectoSonido;

public abstract class Agarrable extends Sprite {
    protected boolean recogido;
    protected EfectoSonido efectoSonido; 
    
    public Agarrable(Texture textura, EfectoSonido efectoSonido) {
        super(textura);
        this.recogido = false;
        this.efectoSonido = efectoSonido; 
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
