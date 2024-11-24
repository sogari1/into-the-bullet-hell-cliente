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
    private Texto tituloJuego, errorTexto;
    private Boton jugarMultiplayerBoton, salirBoton;

    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sonidos/musica/DarkSouls.mp3"));
        menuMusic.setLooping(true);
//        menuMusic.play();

        inicializarTextos();
        inicializarBotones();
        
        stage.addActor(jugarMultiplayerBoton);
        stage.addActor(salirBoton);
    }
    public void inicializarTextos() {
        tituloJuego = new Texto("Into The Bullet Hell", 48, Color.WHITE, 0, Gdx.graphics.getHeight() - 300);
        tituloJuego.setShadow(6, 6, Color.GRAY);
        tituloJuego.centerX();
        
     	errorTexto = new Texto("", 15, Color.RED, 20, 30);
    	errorTexto.setShadow(2, 2, Color.BLACK);
    }
    public void inicializarBotones() {
        
    	jugarMultiplayerBoton = new Boton(new Texto("Multiplayer", 24, Color.WHITE, 0, 150));
        jugarMultiplayerBoton.centrarX();
        
        salirBoton = new Boton(new Texto("Salir", 24, Color.WHITE, 0, 100));
        salirBoton.centrarX();
    }

    @Override
    public void render(float delta) {
    	 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
         RenderManager.batch.begin();
         tituloJuego.draw(); 
         jugarMultiplayerBoton.draw();  
         salirBoton.draw();  
         RenderManager.batch.end();
         stage.draw();
         
         if (jugarMultiplayerBoton.isClicked()) {
        	 ScreenManager.setScreen(new MultiplayerPantalla());
             menuMusic.stop();
         }
         if (salirBoton.isClicked()) {
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
        jugarMultiplayerBoton.dispose();
        salirBoton.dispose();  
    }
}
