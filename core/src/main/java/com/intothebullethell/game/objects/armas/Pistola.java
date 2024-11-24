package com.intothebullethell.game.objects.armas;


import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class Pistola extends Arma {
    public Pistola() {
        super("Pistola", 75, 1, 0.4f, 10, 300, RecursoRuta.PROYECTIL_PISTOLA, RecursoRuta.ARMA_PISTOLA, SonidoRuta.DISPARIO_PISTOLA);
    }
}
