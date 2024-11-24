package com.intothebullethell.game.objects.activos;
import com.badlogic.gdx.graphics.Texture;
import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.sonido.EfectoSonido;

public abstract class Activo {
    private String nombre; 
    private float tiempoDeUso; 
    private boolean usado;
    private Texture texturaObjeto; 
    private EfectoSonido efectoSonido; 

    public Activo(String nombre, float tiempoDeUso, Texture texturaObjeto, EfectoSonido efectoSonido) {
        this.nombre = nombre;
        this.tiempoDeUso = tiempoDeUso;
        this.usado = false;
        this.texturaObjeto = texturaObjeto;
        this.efectoSonido = efectoSonido;
    }

    public String getNombre() {
        return nombre;
    }

    public float getTiempoDeUso() {
        return tiempoDeUso;
    }

    public boolean isUsado() {
        return usado;
    }

    public Texture getActivoTextura() {
        return texturaObjeto;
    }

    public EfectoSonido getEfectoSonido() {
        return efectoSonido;
    }

    public void usar(Jugador jugador) {
        if (!usado) {
            aplicarEfecto(jugador);
            usado = true;

            if (efectoSonido != null) {
                efectoSonido.reproducirSonido();
            }

            System.out.println(nombre + " ha sido usado. Duración: " + tiempoDeUso + " segundos.");

            new Thread(() -> {
                try {
                    Thread.sleep((long) (tiempoDeUso * 1000)); 
                    revertirEfecto(jugador); 
                    System.out.println("El efecto de " + nombre + " ha terminado.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            System.out.println("Este objeto activo ya ha sido usado y no se puede reutilizar.");
        }
    }

    protected abstract void aplicarEfecto(Jugador jugador);
    protected abstract void revertirEfecto(Jugador jugador);
}
