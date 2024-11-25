package com.intothebullethell.game.objects.armas;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.intothebullethell.game.entidades.Proyectil;
import com.intothebullethell.game.globales.NetworkData;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class Escopeta extends Arma {
    public Escopeta() {
        super("Escopeta", 280, 3, 0.8f, 8, 64, RecursoRuta.PROYECTIL_ESCOPETA, RecursoRuta.PROYECTIL_ESCOPETA, RecursoRuta.ARMA_ESCOPETA, SonidoRuta.DISPARO_ESCOPETA);
    }
}
