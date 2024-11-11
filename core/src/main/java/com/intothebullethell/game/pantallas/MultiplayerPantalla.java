package com.intothebullethell.game.pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector3;
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
import com.intothebullethell.game.managers.EntidadManager;
import com.intothebullethell.game.managers.RenderManager;
import com.intothebullethell.game.managers.TileColisionManager;
import com.intothebullethell.game.network.ClientThread;
import com.intothebullethell.game.network.NetworkActionsListener;
import com.intothebullethell.game.ui.Hud;
import com.intothebullethell.game.ui.Texto;
import com.intothebullethell.sonido.Musica;

public class MultiplayerPantalla implements Screen, NetworkActionsListener {

	private final int NUM_JUGADORES = 2;
	private Jugador[] jugadores = new Jugador[NUM_JUGADORES];
	private Hud[] huds = new Hud[NUM_JUGADORES]; 

	private EntidadManager entidadManager;
    private OrthographicCamera camara;
    private IntoTheBulletHell game;
    private Stage stage;
    private InputManager inputManager;
    private TileColisionManager tileCollisionManager;
    
    private ClientThread clientThread;
    private JuegoEstado juegoEstado = JuegoEstado.ESPERANDO;
    
    private Texto esperandoJugador;
    
    public MultiplayerPantalla(IntoTheBulletHell game) {
        this.game = game;
        this.tileCollisionManager = new TileColisionManager();
        this.entidadManager = new EntidadManager(camara, RenderManager.mapa, jugadores, tileCollisionManager);
    	this.camara = new OrthographicCamera();
    	this.inputManager = new InputManager();
    	Gdx.input.setInputProcessor(inputManager);
           
        
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
			jugadores[i] = new Jugador(i, RecursoRuta.SPRITE_ABAJO, RecursoRuta.SPRITE_ARRIBA,  RecursoRuta.SPRITE_ABAJO, RecursoRuta.SPRITE_IZQUIERDA,  RecursoRuta.SPRITE_DERECHA, camara, inputManager, entidadManager);
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
	public void show() {}
	
	@Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    RenderManager.renderizarCamara(camara);
	    update(delta);   
	    draw();
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
	    	
//	        for (Hud hud : huds) {
//	            hud.actualizarTemporizador(Tiempo.getTiempo());
//	        }
	    }
	}



	private void draw() {
		RenderManager.batchRender.begin();
		entidadManager.draw();
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
        if (inputManager.isUp() && inputManager.isDown()) {
        	jugadores[GameData.clienteNumero].velocity.y = 0;
        } else if (inputManager.isUp()) {
        	jugadores[GameData.clienteNumero].moverArriba();
        	clientThread.enviarMensajeAlServidor("mover!arriba!" + GameData.clienteNumero);
        } else if (inputManager.isDown()) {
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
        if (inputManager.isLeft() && inputManager.isRight()) {
        	jugadores[GameData.clienteNumero].velocity.x = 0;
        } else if (inputManager.isLeft()) {
        	jugadores[GameData.clienteNumero].moverIzquierda();
        	clientThread.enviarMensajeAlServidor("mover!izquierda!" + GameData.clienteNumero);
        } else if (inputManager.isRight()) {
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

        if (inputManager.isRecargar()) {
        	jugadores[GameData.clienteNumero].recargarArma();
        	clientThread.enviarMensajeAlServidor("recargar!"+GameData.clienteNumero);
        }
        
        if (inputManager.isDisparar()) {
        	jugadores[GameData.clienteNumero].setDisparando(true);
        	Vector3 unprojected = camara.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        	clientThread.enviarMensajeAlServidor("disparo!disparar!" + GameData.clienteNumero + "!" + (int) unprojected.x + "!" + (int) unprojected.y);
        } else if (inputManager.isDisparandoJustReleased()) {
        	clientThread.enviarMensajeAlServidor("disparo!dispararrelease!" + GameData.clienteNumero);
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
	public void actualizarDireccionJugador(int jugadorId, String region) {
	    this.jugadores[jugadorId].setRegion(this.jugadores[jugadorId].obtenerRegionDesdeNombre(region));;
	}
	@Override
	public void moverEnemigo(int enemyId, float xPos, float yPos) {
		entidadManager.moverEnemigo(enemyId, xPos, yPos);
	}
	@Override
	public void removerEnemigo(int enemigoId) {
		entidadManager.removerEnemigo(enemigoId);
	}
	@Override
	public void añadirEnemigo(String tipoEnemigo, float x, float y) {
		entidadManager.añadirEnemigo(tipoEnemigo, x, y);
	}
	@Override
	public void añadirProyectil(String tipoProyectil, float x, float y, float velocidad, int daño, boolean disparadoPorJugador ) {
		entidadManager.añadirProyectil(tipoProyectil, x, y, velocidad, daño, disparadoPorJugador );
	}
	public void actualizarProyectilPosicion(int projectileId, float xPos, float yPos) {
		entidadManager.moverProyectil(projectileId, xPos, yPos);
	}
	@Override
	public void removerProyectil(int proyectilId) {
		entidadManager.removerProyectil(proyectilId);
	}
	@Override
	public void actualizarTiempo(int tiempo) {
		for (Hud hud : huds) {
            hud.actualizarTemporizador(tiempo);
        }
	}
	@Override
	public void actualizarRonda(int ronda) {
		for (Hud hud : huds) {
            hud.actualizarRonda(ronda);
        }
	}
	@Override
	public void actualizarEnemigosRestantes(int cantidad) {
		for (Hud hud : huds) {
            hud.actualizarEnemigosRestantes(cantidad);
        }
	}
	@Override
	public void actualizarVidaJugador(int jugadorId, int vidaActual) {
		this.jugadores[jugadorId].setVida(vidaActual);
	}

 }
