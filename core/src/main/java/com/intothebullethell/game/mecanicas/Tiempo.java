package com.intothebullethell.game.mecanicas;

import com.intothebullethell.game.entidades.Jugador;

public class Tiempo extends Thread {
    private static int tiempo;
    private int tiempoSobrevivido = 0;
    private Jugador[] jugadores;
    private boolean running;
    private boolean paused;

    public Tiempo(Jugador[] jugadores) {
        Tiempo.tiempo = 30;
        this.jugadores = jugadores;
        this.running = true;
        this.paused = false;  
    }

    @Override
    public void run() {
        while (running) {
            if (!paused) { 
                try {
                    Thread.sleep(1000);
                    System.out.println("Aleatorizar arma en: " + tiempo + " segundos");
                    tiempo--;
                    tiempoSobrevivido++;
                    if (tiempo < 0) {
                        for (Jugador jugador : jugadores) {
//                            jugador.cambiarArma();
                        }
                        tiempo = 30;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    running = false;
                }
            } else {
                try {
                    Thread.sleep(100);  
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getTiempo() {
        return tiempo;
    }
    public int getTiempoSobrevivido() {
    	return tiempoSobrevivido;
	}
    public void detener() {
        running = false;
    }

    public void pausar() {
        paused = true;
    }

    public void reanudar() {
        paused = false;
    }
}

