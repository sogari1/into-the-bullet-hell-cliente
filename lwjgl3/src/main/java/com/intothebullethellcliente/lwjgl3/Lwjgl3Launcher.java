package com.intothebullethellcliente.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.intothebullethell.game.IntoTheBulletHell;
import com.intothebullethell.game.globales.ConfiguracionJuego;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; 
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new IntoTheBulletHell(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle(ConfiguracionJuego.TITULO);
        configuration.useVsync(false);
        configuration.setForegroundFPS(60);
        configuration.setWindowedMode(ConfiguracionJuego.ANCHO, ConfiguracionJuego.ALTURA);
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        configuration.setResizable(false);
        return configuration;
    }
}