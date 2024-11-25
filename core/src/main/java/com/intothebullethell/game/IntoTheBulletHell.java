package com.intothebullethell.game;

import com.badlogic.gdx.Game;
import com.intothebullethell.game.managers.ScreenManager;
import com.intothebullethell.game.pantallas.MenuPantalla;

public class IntoTheBulletHell extends Game {
    
    @Override
    public void create() {
        ScreenManager.juegoApp = this;
        ScreenManager.setScreen(new MenuPantalla());
    }
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    	getScreen().dispose();
    }

}
