package com.intothebullethell.game.objects.armas;

import com.badlogic.gdx.graphics.Texture;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;
import com.intothebullethell.sonido.EfectoSonido;

public class Bengala {
	private EfectoSonido efectosSonido = SonidoRuta.BENGALA;
	private Texture spriteBengala = RecursoRuta.BENGALA;
	
    private int usosMaximos = 2;
    private int usosRestantes = usosMaximos;
    public int getUsosRestantes() {
        return usosRestantes;
    }
    public void setUsosRestantes(int usosRestantes) {
		this.usosRestantes = usosRestantes;
	}
    public Texture getSpriteBengala() {
		return spriteBengala;
	}
}