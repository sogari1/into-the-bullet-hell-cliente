package com.intothebullethell.game.objects.armas;

import com.intothebullethell.game.managers.EntidadManager;

public class Bengala {

    private int usosMaximos = 2;
    private int usosRestantes = usosMaximos;
    private float cooldown = 5.0f; 
    private float tiempoDesdeUltimoUso = 0;

    public boolean puedeUsarse() {
        return usosRestantes > 0 && tiempoDesdeUltimoUso >= cooldown;
    }

    public void usar(EntidadManager entidadManager) {
        if (puedeUsarse()) {
        	 entidadManager.getGrupoProyectiles().eliminarProyectilesEnemigos();

            usosRestantes--;
            tiempoDesdeUltimoUso = 0; 
        }
    }

    public void update(float delta) {
        if (tiempoDesdeUltimoUso < cooldown) {
            tiempoDesdeUltimoUso += delta; 
        }
    }

    public int getUsosRestantes() {
        return usosRestantes;
    }

    public float getCooldownRestante() {
        return Math.max(0, cooldown - tiempoDesdeUltimoUso);
    }
}