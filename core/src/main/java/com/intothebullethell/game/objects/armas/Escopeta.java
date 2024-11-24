package com.intothebullethell.game.objects.armas;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.intothebullethell.game.entidades.Proyectil;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.globales.SonidoRuta;

public class Escopeta extends Arma {
    public Escopeta() {
        super("Escopeta", 70, 3, 1.5f, 8, false, 64, RecursoRuta.PROYECTIL_ESCOPETA, RecursoRuta.ARMA_ESCOPETA, SonidoRuta.DISPARIO_ESCOPETA);
    }

    @Override
    public void disparar(Vector2 position, Vector2 target, List<Proyectil> proyectiles) {
    }

}
