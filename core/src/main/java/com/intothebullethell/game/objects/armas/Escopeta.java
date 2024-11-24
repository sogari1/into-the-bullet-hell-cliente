package com.intothebullethell.game.objects.armas;


import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class Escopeta extends Arma {
    public Escopeta() {
        super("Escopeta", 70, 3, 1.5f, 8, 64, RecursoRuta.PROYECTIL_ESCOPETA, RecursoRuta.ARMA_ESCOPETA, SonidoRuta.DISPARIO_ESCOPETA);
    }


}
