package com.intothebullethell.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.intothebullethell.game.managers.RenderManager;
import com.intothebullethell.game.managers.ScreenManager;
import com.intothebullethell.game.ui.Boton;
import com.intothebullethell.game.ui.Texto;

public class MenuPantalla implements Screen {

    private Stage stage;
    private Music menuMusic;
    private Texto tituloJuego;
    private Boton playSingleplayerButton, playMultiplayerButton,exitButton;

    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sonidos/musica/DarkSouls.mp3"));
        menuMusic.setLooping(true);
//        menuMusic.play();

        inicializarTextos();
        inicializarBotones();
        
        stage.addActor(playSingleplayerButton);
        stage.addActor(playMultiplayerButton);
        stage.addActor(exitButton);
    }
    public void inicializarTextos() {

        tituloJuego = new Texto("Into The Bullet Hell", 48, Color.WHITE, 0, Gdx.graphics.getHeight() - 300);
        tituloJuego.setShadow(6, 6, Color.GRAY);
        tituloJuego.centerX();
    }
    public void inicializarBotones() {
    	playSingleplayerButton = new Boton(new Texto("Singleplayer", 24, Color.WHITE, 0, 200));
        playSingleplayerButton.centrarX();
        
        playMultiplayerButton = new Boton(new Texto("Multiplayer", 24, Color.WHITE, 0, 150));
        playMultiplayerButton.centrarX();
        
        exitButton = new Boton(new Texto("Salir", 24, Color.WHITE, 0, 100));
        exitButton.centrarX();
    }

    @Override
    public void render(float delta) {
    	 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
         RenderManager.batch.begin();
         tituloJuego.draw(); 
         playSingleplayerButton.draw();  
         playMultiplayerButton.draw();  
         exitButton.draw();  
         RenderManager.batch.end();
         stage.draw();
         
         if (playSingleplayerButton.isClicked()) {
//             ((Game) Gdx.app.getApplicationListener()).setScreen(new SingleplayerPantalla()));
             menuMusic.stop();
         }
         if (playMultiplayerButton.isClicked()) {
        	 ScreenManager.setScreen(new MultiplayerPantalla());
             menuMusic.stop();
         }
         if (exitButton.isClicked()) {
             Gdx.app.exit(); 
         }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        menuMusic.dispose();  
        tituloJuego.dispose(); 
        playSingleplayerButton.dispose();  
        playMultiplayerButton.dispose();
        exitButton.dispose();  
    }
}
