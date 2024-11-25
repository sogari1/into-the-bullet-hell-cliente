package com.intothebullethell.game.objects.armas;


import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class Pistola extends Arma {
    public Pistola() {
        super("Pistola", 300, 1, 0.3f, 10, 120, RecursoRuta.PROYECTIL_PISTOLA, RecursoRuta.PROYECTIL_PISTOLA, RecursoRuta.ARMA_PISTOLA, SonidoRuta.DISPARO_PISTOLA);
    }
    
}
