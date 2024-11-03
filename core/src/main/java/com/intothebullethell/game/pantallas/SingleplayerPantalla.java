package com.intothebullethell.game.pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.intothebullethell.game.IntoTheBulletHell;
import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.AssetRuta;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.inputs.InputManager;
import com.intothebullethell.game.managers.MapManager;
import com.intothebullethell.game.managers.ProyectilManager;
import com.intothebullethell.game.managers.RenderManager;
import com.intothebullethell.game.managers.TileColisionManager;
import com.intothebullethell.game.mecanicas.GenerarEnemigos;
import com.intothebullethell.game.mecanicas.Tiempo;
import com.intothebullethell.game.ui.Hud;
import com.intothebullethell.sonido.Musica;

public class SingleplayerPantalla implements Screen {

	private MapManager mapManager;
    private OrthographicCamera camara;
    private IntoTheBulletHell game;
    private Jugador[] jugador;
    private Stage stage;
    private Hud hud;
    private Tiempo tiempo;
    private PausaPantalla pausaPantalla;
    private GameOverPantalla gameOverPantalla;
    private ProyectilManager proyectilManager;
    private InputManager inputManager;
    private TileColisionManager tileCollisionManager;

    private boolean pausado = false;
    private int ronda = 1;

    public SingleplayerPantalla(IntoTheBulletHell game) {
        this.game = game;
        this.pausaPantalla = new PausaPantalla(game, this);
        this.gameOverPantalla = new GameOverPantalla(game);
        this.camara = new OrthographicCamera();
        this.inputManager = new InputManager();
        this.mapManager = new MapManager(camara, RenderManager.mapa, jugador, tileCollisionManager);
        this.proyectilManager = new ProyectilManager();
        Gdx.input.setInputProcessor(inputManager);

        this.tileCollisionManager = new TileColisionManager();

        this.jugador = new Jugador[1]; 
        this.jugador[0] = new Jugador(RecursoRuta.SPRITE_ABAJO, RecursoRuta.SPRITE_ARRIBA, RecursoRuta.SPRITE_ABAJO, RecursoRuta.SPRITE_IZQUIERDA, RecursoRuta.SPRITE_DERECHA, camara, inputManager, mapManager, proyectilManager);
        this.jugador[0].setPosition(15 * tileCollisionManager.collisionLayer.getTileWidth(), 15 * tileCollisionManager.collisionLayer.getTileHeight());
        this.hud = new Hud(RenderManager.batchRender, jugador[0]);
        this.jugador[0].setHud(hud);
        this.tiempo = new Tiempo(jugador);
        tiempo.start();

        setCustomCursor(AssetRuta.CURSOR);

        Musica musica = game.getMusica();
        Musica.gameMusic = musica.getGameMusic();
        Musica.gameMusic.setLooping(true);
//        Musica.gameMusic.play();

        this.stage = new Stage(new ScreenViewport());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (inputManager.isPausaSolicitada()) {
            pause();
        }
        if (!pausado) {
            update(delta);          
            draw();
        }
    }

    @Override
    public void show() {}

    private void update(float delta) {
        manejarJuegoInputs();
        RenderManager.renderizarCamara(camara);
        
        jugador[0].update(delta);
        mapManager.update(delta, jugador);
        
        if (jugador[0].chequearMuerte()) {
            gameOver();
        }

//        if (enemigos.isEmpty()) {
//            ronda++;
//            hud.actualizarRonda(ronda);
//            generadorEnemigos.generarEnemigos();
//        }
        
//        hud.actualizarEnemigosRestantes(enemigos.size());
        hud.actualizarTemporizador(Tiempo.getTiempo());
    }

    private void draw() {
    	RenderManager.batchRender.begin();
	        jugador[0].draw(RenderManager.batchRender);
	        mapManager.draw();
        RenderManager.batchRender.end();
        
        RenderManager.batch.begin();
        	hud.draw();
        RenderManager.batch.end();
        
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camara.viewportWidth = width - 320;
        camara.viewportHeight = height - 300;
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        RenderManager.mapa.dispose();
        RenderManager.render.dispose();
        stage.dispose();
        hud.dispose(); 
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow); 
    }

    @Override
    public void pause() {
        pausado = true;
        inputManager.setPausaSolicitada(false);
        tiempo.pausar();  
        Musica.playPauseMusic();
        game.setScreen(pausaPantalla);
    }

    public void resume() {
        pausado = false;
        tiempo.reanudar(); 
        Musica.stopAllMusic();
        Musica.gameMusic.play();
    }

    public void gameOver() {
        Musica.gameMusic.pause();
        game.setScreen(gameOverPantalla);
    }

    private void manejarJuegoInputs() {
        if (inputManager.isUpPressed() && inputManager.isDownPressed()) {
            jugador[0].velocity.y = 0;
        } else if (inputManager.isUpPressed()) {
            jugador[0].moverArriba();
        } else if (inputManager.isDownPressed()) {
            jugador[0].moverAbajo();
        } else {
            jugador[0].velocity.y = 0;
        }

        if (inputManager.isLeftPressed() && inputManager.isRightPressed()) {
            jugador[0].velocity.x = 0;
        } else if (inputManager.isLeftPressed()) {
            jugador[0].moverIzquierda();
        } else if (inputManager.isRightPressed()) {
            jugador[0].moverDerecha();
        } else {
            jugador[0].velocity.x = 0;
        }

        if (inputManager.isRecargarPressed()) {
            jugador[0].recargarArma();
        }

        if (inputManager.isDisparandoJustPressed()) {
            jugador[0].setDisparando(true);
        } else if (inputManager.isDisparandoJustReleased()) {
            jugador[0].setDisparando(false);
        }
    }

    @Override
    public void hide() {}

    private void setCustomCursor(String cursorPath) {
        Pixmap pixmap = new Pixmap(Gdx.files.internal(cursorPath));
        Cursor cursor = Gdx.graphics.newCursor(pixmap, pixmap.getWidth() / 2, pixmap.getHeight() / 2);
        Gdx.graphics.setCursor(cursor);
        pixmap.dispose(); 
    }
}
