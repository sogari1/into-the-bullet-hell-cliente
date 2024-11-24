package com.intothebullethell.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class ScreenManager {

    public static Game gameApp;

    public static void setScreen(Screen screen){
        gameApp.setScreen(screen);
    }

}
