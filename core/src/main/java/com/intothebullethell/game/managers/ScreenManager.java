package com.intothebullethell.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class ScreenManager {

    public static Game juegoApp;

    public static void setScreen(Screen screen){
    	juegoApp.setScreen(screen);
    }

}
