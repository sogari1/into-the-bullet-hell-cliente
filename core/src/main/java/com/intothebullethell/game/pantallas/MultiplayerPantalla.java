package com.intothebullethell.game.pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.intothebullethell.game.IntoTheBulletHell;
import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.AssetRuta;
import com.intothebullethell.game.globales.GameData;
import com.intothebullethell.game.globales.JuegoEstado;
import com.intothebullethell.game.globales.NetworkData;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.inputs.InputManager;
import com.intothebullethell.game.managers.MapManager;
import com.intothebullethell.game.managers.ProyectilManager;
import com.intothebullethell.game.managers.RenderManager;
import com.intothebullethell.game.managers.TileColisionManager;
import com.intothebullethell.game.mecanicas.Tiempo;
import com.intothebullethell.game.network.ClientThread;
import com.intothebullethell.game.network.NetworkActionsListener;
import com.intothebullethell.game.ui.Hud;
import com.intothebullethell.game.ui.Texto;
import com.intothebullethell.sonido.Musica;

public class MultiplayerPantalla implements Screen, NetworkActionsListener {

	private final int NUM_JUGADORES = 2;
	private Jugador[] jugadores = new Jugador[NUM_JUGADORES];
	private Hud[] huds = new Hud[NUM_JUGADORES]; 

	private MapManager mapManager;
    private OrthographicCamera camara;
    private IntoTheBulletHell game;
    private Stage stage;
    private ProyectilManager proyectilManager;
    private InputManager inputManager;
    private TileColisionManager tileCollisionManager;
    
    private ClientThread clientThread;
    private JuegoEstado juegoEstado = JuegoEstado.ESPERANDO;
    
    private Texto esperandoJugador;
    
    public MultiplayerPantalla(IntoTheBulletHell game) {
        this.game = game;
        this.tileCollisionManager = new TileColisionManager();
        this.mapManager = new MapManager(camara, RenderManager.mapa, jugadores, tileCollisionManager);
    	this.camara = new OrthographicCamera();
    	this.inputManager = new InputManager();
    	Gdx.input.setInputProcessor(inputManager);
           
        this.proyectilManager = new ProyectilManager();
        
        crearJugadores();  
        crearHudJugadores();
        
        
        setCustomCursor(AssetRuta.CURSOR);
        
        Musica musica = game.getMusica();
        Musica.gameMusic = musica.getGameMusic();
        Musica.gameMusic.setLooping(true);
//        Musica.gameMusic.play();
        
        this.stage = new Stage(new ScreenViewport());
        
        esperandoJugador = new Texto("Esperando al otro jugador", 24, Color.RED, 0, Gdx.graphics.getHeight() - 400);
        esperandoJugador.setShadow(4, 4, Color.GRAY);
        esperandoJugador.centerX();
        
        GameData.networkListener = this;
        clientThread = new ClientThread();
        NetworkData.clientThread = clientThread;
        clientThread.start();
        clientThread.enviarMensajeAlServidor("connect");
    }
    private void crearJugadores() {
    	for (int i = 0; i < NUM_JUGADORES; i++) {
			jugadores[i] = new Jugador(RecursoRuta.SPRITE_ABAJO, RecursoRuta.SPRITE_ARRIBA,  RecursoRuta.SPRITE_ABAJO, RecursoRuta.SPRITE_IZQUIERDA,  RecursoRuta.SPRITE_DERECHA, camara, inputManager, mapManager, proyectilManager);
			jugadores[i].setPosition((15 + (i*2)) * tileCollisionManager.collisionLayer.getTileWidth(), 15 * tileCollisionManager.collisionLayer.getTileHeight());
		}
    }
    private void crearHudJugadores() {
        for (int i = 0; i < NUM_JUGADORES; i++) {
            huds[i] = new Hud(RenderManager.batchRender, jugadores[i]);
            jugadores[i].setHud(huds[i]);
        }
    }
    
	@Override
	public void show() {
		
	}
	@Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    RenderManager.renderizarCamara(camara);
	    draw();
	    update(delta);   
    }
	
	@Override
    public void resize(int width, int height) {
        camara.viewportWidth = width - 320;
        camara.viewportHeight = height - 300;
        stage.getViewport().update(width, height, true);
    }
	private void update(float delta) {
		chequearInputs();
	    if(juegoEstado.equals(JuegoEstado.JUGANDO)) {
	    	
	    	mapManager.update(delta, jugadores);
	    	
	        for (Hud hud : huds) {
	            hud.actualizarTemporizador(Tiempo.getTiempo());
	        }
	    }
	}



	private void draw() {
		RenderManager.batchRender.begin();
	    mapManager.draw();
		    for (Jugador jugador : jugadores) {
		    	jugador.draw(RenderManager.batchRender);
		    }
	    RenderManager.batchRender.end();
	    
	    RenderManager.batch.begin();
		    for (Hud hud : huds) {
	            hud.draw();
	        }
		    if (juegoEstado.equals(JuegoEstado.ESPERANDO)) {
		        esperandoJugador.draw();
		    }
	    RenderManager.batch.end();
	    stage.draw();
	}
	private void chequearInputs() {
        if(juegoEstado.equals(JuegoEstado.JUGANDO)) {
        	manejarJuegoInputs();
        } else if(juegoEstado.equals(JuegoEstado.ESPERANDO)){
        }
    }
	private void manejarJuegoInputs() {
		//vertical
        if (inputManager.isUpPressed() && inputManager.isDownPressed()) {
        	jugadores[GameData.clienteNumero].velocity.y = 0;
        } else if (inputManager.isUpPressed()) {
        	jugadores[GameData.clienteNumero].moverArriba();
        	clientThread.enviarMensajeAlServidor("mover!arriba!" + GameData.clienteNumero);
        } else if (inputManager.isDownPressed()) {
        	jugadores[GameData.clienteNumero].moverAbajo();
        	clientThread.enviarMensajeAlServidor("mover!abajo!" + GameData.clienteNumero);
        } else {
        	jugadores[GameData.clienteNumero].velocity.y = 0;
        }
        //vertical release
        if (inputManager.isUpJustReleased()) {
            clientThread.enviarMensajeAlServidor("mover!arribarelease!" + GameData.clienteNumero);
        } else if (inputManager.isDownJustReleased()) {
            clientThread.enviarMensajeAlServidor("mover!abajorelease!" + GameData.clienteNumero);
        }
        
        //horizontal
        if (inputManager.isLeftPressed() && inputManager.isRightPressed()) {
        	jugadores[GameData.clienteNumero].velocity.x = 0;
        } else if (inputManager.isLeftPressed()) {
        	jugadores[GameData.clienteNumero].moverIzquierda();
        	clientThread.enviarMensajeAlServidor("mover!izquierda!" + GameData.clienteNumero);
        } else if (inputManager.isRightPressed()) {
        	jugadores[GameData.clienteNumero].moverDerecha();
        	clientThread.enviarMensajeAlServidor("mover!derecha!" + GameData.clienteNumero);
        } else {
        	jugadores[GameData.clienteNumero].velocity.x = 0;
        }
        //horizontal release
        if (inputManager.isLeftJustReleased()) {
            clientThread.enviarMensajeAlServidor("mover!izquierdarelease!" + GameData.clienteNumero);
        } else if (inputManager.isRightJustReleased()) {
            clientThread.enviarMensajeAlServidor("mover!derecharelease!" + GameData.clienteNumero);
        }

        if (inputManager.isRecargarPressed()) {
        	jugadores[GameData.clienteNumero].recargarArma();
        	clientThread.enviarMensajeAlServidor("recargar!"+GameData.clienteNumero);
        }
        
        if (inputManager.isDisparandoJustPressed()) {
        	jugadores[GameData.clienteNumero].setDisparando(true);
        } else if (inputManager.isDisparandoJustReleased()) {
        	jugadores[GameData.clienteNumero].setDisparando(false);
        }
    }
	@Override
	public void pause() {
	}
	@Override
	public void resume() {
	}
	@Override
	public void hide() {
	}
	@Override
	public void dispose() {
		clientThread.enviarMensajeAlServidor("disconnect!"+GameData.clienteNumero);
		clientThread.end();
	}
	private void setCustomCursor(String cursorPath) {
		Pixmap pixmap = new Pixmap(Gdx.files.internal(cursorPath));
		Cursor cursor = Gdx.graphics.newCursor(pixmap, pixmap.getWidth() / 2, pixmap.getHeight() / 2);
		Gdx.graphics.setCursor(cursor);
		pixmap.dispose(); 
	}
	@Override
	public void empezarJuego() {
		juegoEstado = JuegoEstado.JUGANDO;
	}
	@Override
	public void actualizarJugadorPosicion(int jugadorId, float xPos, float yPos) {
		this.jugadores[jugadorId].setPosition(xPos, yPos);
	}
	@Override
	public void moverEnemigo(int enemyId, float xPos, float yPos) {
		mapManager.moverEnemigo(enemyId, xPos, yPos);
	}
	@Override
	public void actualizarProyectilPosicion(int projectileId, float xPos, float yPos) {
	    proyectilManager.actualizarProyectilPosicion(projectileId, xPos, yPos);
	}
	@Override
	public void removerEnemigo() {
		
	}
	@Override
	public void añadirEnemigo(float x, float y) {
		
	}
	@Override
	public void actualizarTiempo(int tiempo) {
		for (Hud hud : huds) {
            hud.actualizarTemporizador(tiempo);
        }
	}

 }
