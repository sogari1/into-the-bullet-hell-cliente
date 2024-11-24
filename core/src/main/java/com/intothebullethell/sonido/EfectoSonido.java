package com.intothebullethell.sonido;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class EfectoSonido {
    private Sound sonido;

    public EfectoSonido(String rutaSonido) {
        this.sonido = Gdx.audio.newSound(Gdx.files.internal(rutaSonido));
    }

    public void reproducirSonido() {
    	sonido.play();
    }

    public void dispose() {
    	sonido.dispose();
    }
}
