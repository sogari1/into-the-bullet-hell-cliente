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
import com.intothebullethell.game.entidades.Jugador;
import com.intothebullethell.game.globales.AssetRuta;
import com.intothebullethell.game.globales.GameData;
import com.intothebullethell.game.globales.JuegoEstado;
import com.intothebullethell.game.globales.NetworkData;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.inputs.InputManager;
import com.intothebullethell.game.managers.EntidadManager;
import com.intothebullethell.game.managers.RenderManager;
import com.intothebullethell.game.managers.ScreenManager;
import com.intothebullethell.game.managers.TileColisionManager;
import com.intothebullethell.game.network.ClientThread;
import com.intothebullethell.game.network.NetworkActionsListener;
import com.intothebullethell.game.ui.Boton;
import com.intothebullethell.game.ui.Hud;
import com.intothebullethell.game.ui.Texto;

public class MultiplayerPantalla implements Screen, NetworkActionsListener {

	private final int NUM_JUGADORES = 2;
	private Jugador[] jugadores = new Jugador[NUM_JUGADORES];
	private Hud hud;

	private EntidadManager entidadManager;
    private OrthographicCamera camara;
    private Stage stage;
    private InputManager inputManager;
    private TileColisionManager tileCollisionManager;
    
    private ClientThread clientThread;
    private JuegoEstado juegoEstado = JuegoEstado.ESPERANDO;
    
    private Texto esperandoJugadorTexto, gameOverTexto, muerteTexto, errorTexto, estadisticasTexto;
    private Boton reiniciarBoton, salirBoton;
    
    @Override
	public void show() {
     	
        this.tileCollisionManager = new TileColisionManager();
        this.entidadManager = new EntidadManager(camara, RenderManager.mapa, jugadores, tileCollisionManager);
    	this.camara = new OrthographicCamera();
    	this.inputManager = new InputManager();
    	Gdx.input.setInputProcessor(inputManager);
    	
    	crearJugadores(); 
        this.hud = new Hud();
        inicializarTextos();
        crearBotones();
        
        setCustomCursor(AssetRuta.CURSOR);
        
        this.stage = new Stage(new ScreenViewport());
        
     	GameData.networkListener = this;
     	clientThread = new ClientThread();
     	NetworkData.clientThread = clientThread;
     	clientThread.start();
     	if(!clientThread.conectarAlServidor()) {
     		ScreenManager.setScreen(new MenuPantalla());
     	}
	}
    private void crearJugadores() {
    	for (int i = 0; i < NUM_JUGADORES; i++) {
			jugadores[i] = new Jugador(i, RecursoRuta.SPRITE_ABAJO, RecursoRuta.SPRITE_ARRIBA,  RecursoRuta.SPRITE_ABAJO, RecursoRuta.SPRITE_IZQUIERDA,  RecursoRuta.SPRITE_DERECHA, camara, inputManager, entidadManager);
			jugadores[i].setPosition((15 + (i*2)) * tileCollisionManager.collisionLayer.getTileWidth(), 15 * tileCollisionManager.collisionLayer.getTileHeight());
		}
    }
    
    private void inicializarTextos() {
    	esperandoJugadorTexto = new Texto("Esperando al otro jugador", 24, Color.RED, 0, Gdx.graphics.getHeight() - 400);
    	esperandoJugadorTexto.setShadow(4, 4, Color.BLACK);
    	esperandoJugadorTexto.centerX();
    	
    	gameOverTexto = new Texto("GAME OVER", 40, Color.RED, 0, Gdx.graphics.getHeight() - 400);
    	gameOverTexto.setShadow(4, 4, Color.BLACK);
    	gameOverTexto.centerX();
    	
    	muerteTexto = new Texto("HAZ MUERTO", 40, Color.RED, 0, Gdx.graphics.getHeight() - 400);
    	muerteTexto.setShadow(4, 4, Color.BLACK);
    	muerteTexto.centerX();
    	
    	errorTexto = new Texto("", 15, Color.RED, 20, 30);
    	errorTexto.setShadow(2, 2, Color.BLACK);
    	
    	estadisticasTexto = new Texto("", 30, Color.WHITE, 0, Gdx.graphics.getHeight() - 400);
    	estadisticasTexto.setShadow(4, 4, Color.GRAY);
    	estadisticasTexto.centerX();
    }
    private void crearBotones() {
    	reiniciarBoton = new Boton(new Texto("Reiniciar", 24, Color.WHITE, 0, 200));
    	reiniciarBoton.centrarX();
    	
    	salirBoton = new Boton(new Texto("SALIR", 24, Color.WHITE, 0, 150));
    	salirBoton.centrarX();
    }
	
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
		for (Jugador jugador : jugadores) {
			jugador.update(delta);
		}
	    if(juegoEstado.equals(JuegoEstado.GAME_OVER)) {
	    	if(reiniciarBoton.isClicked()) {
	    		reiniciarJuego();
	    	}
	    	if(salirBoton.isClicked()) {
	    		ScreenManager.setScreen(new MenuPantalla()); 
	    	}
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
	    	if (juegoEstado.equals(JuegoEstado.JUGANDO)) {
	    		hud.draw();
		    }
		    if (juegoEstado.equals(JuegoEstado.ESPERANDO)) {
		        esperandoJugadorTexto.draw();
		    }
		    if (juegoEstado.equals(JuegoEstado.GAME_OVER)) {
		        gameOverTexto.draw();
		        reiniciarBoton.draw();
		        salirBoton.draw();
		        errorTexto.draw();
		    }
		    if (juegoEstado.equals(JuegoEstado.MUERTO)) {
		        muerteTexto.draw();
		    }
	    RenderManager.batch.end();
	    stage.draw();
	}
	private void chequearInputs() {
        if(juegoEstado.equals(JuegoEstado.JUGANDO)) {
        	manejarJuegoInputs();
        } 
    }
	private void manejarJuegoInputs() {
		//vertical
        if (inputManager.isUp()) {
        	clientThread.enviarMensajeAlServidor("mover!arriba!" + GameData.clienteNumero);
        } else if (inputManager.isDown()) {
        	clientThread.enviarMensajeAlServidor("mover!abajo!" + GameData.clienteNumero);
        } 
        //vertical release
        if (inputManager.isUpJustReleased()) {
            clientThread.enviarMensajeAlServidor("mover!arribarelease!" + GameData.clienteNumero);
        } else if (inputManager.isDownJustReleased()) {
            clientThread.enviarMensajeAlServidor("mover!abajorelease!" + GameData.clienteNumero);
        }
        
        //horizontal
        if (inputManager.isLeft()) {
        	clientThread.enviarMensajeAlServidor("mover!izquierda!" + GameData.clienteNumero);
        } else if (inputManager.isRight()) {
        	clientThread.enviarMensajeAlServidor("mover!derecha!" + GameData.clienteNumero);
        }
        //horizontal release
        if (inputManager.isLeftJustReleased()) {
            clientThread.enviarMensajeAlServidor("mover!izquierdarelease!" + GameData.clienteNumero);
        } else if (inputManager.isRightJustReleased()) {
            clientThread.enviarMensajeAlServidor("mover!derecharelease!" + GameData.clienteNumero);
        }
        // recargar
        if (inputManager.isRecargarJustPressed()) {
        	clientThread.enviarMensajeAlServidor("recargar!" + GameData.clienteNumero);
        } 
        // bengala
        if (inputManager.isUsoBengalaPressed()) {
        	clientThread.enviarMensajeAlServidor("bengala!" + GameData.clienteNumero);
        } 	
        
        //disparos
        if (inputManager.isDisparar()) {
        	Vector3 jugadorMousePosicion = camara.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        	clientThread.enviarMensajeAlServidor("disparo!disparar!" + GameData.clienteNumero + "!" + (int) jugadorMousePosicion.x + "!" + (int) jugadorMousePosicion.y);
        } else if (inputManager.isDisparandoJustReleased()) {
        	clientThread.enviarMensajeAlServidor("disparo!dispararrelease!" + GameData.clienteNumero);
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
		clientThread.enviarMensajeAlServidor("desconectar!" + GameData.clienteNumero);
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
	public void gameOver() {
		juegoEstado = JuegoEstado.GAME_OVER;
	}
	private void reiniciarJuego() {
		juegoEstado = JuegoEstado.ESPERANDO;
		for (Jugador jugador : jugadores) {
			jugador.reiniciar();
	    }
		crearJugadores();
		entidadManager.reset();
		if(!clientThread.conectarAlServidor()) {
     		ScreenManager.setScreen(new MenuPantalla());
     	}
	}
	@Override
	public void mostrarError(String mensaje) {
	    errorTexto.setTexto("ERROR: " + mensaje);
	}

	@Override
	public void actualizarJugadorPosicion(int jugadorId, float x, float y) {
		this.jugadores[jugadorId].setPosition(x, y);
	}
	@Override
	public void actualizarDireccionJugador(int jugadorId, String region) {
	    this.jugadores[jugadorId].setRegion(this.jugadores[jugadorId].obtenerRegionDesdeNombre(region));;
	}
	@Override
	public void jugadorMuerto(int jugadorId) {
		if (jugadorId == GameData.clienteNumero) { 
		 this.jugadores[jugadorId].morir();
		 juegoEstado = JuegoEstado.MUERTO;
		}
	}
	@Override
	public void actualizarVidaJugador(int jugadorId, int vidaActual) {
		if (jugadorId == GameData.clienteNumero) {
		this.hud.actualizarVida(this.jugadores[jugadorId].getVidaMaxima(), vidaActual);
		}
	}
	@Override
	public void actualizarArmaJugador(int jugadorId, String nombreArma) {
		this.jugadores[jugadorId].cambiarArma(nombreArma);
		if (jugadorId == GameData.clienteNumero) {
			this.hud.actualizarArma(this.jugadores[jugadorId].getArmaEquipada());
		}
	}
	@Override
	public void actualizarBalasArmaJugador(int jugadorId, int balasEnReserva, int balasEnMunicion) {
		this.jugadores[jugadorId].getArmaEquipada().setBalasEnReserva(balasEnReserva);
		this.jugadores[jugadorId].getArmaEquipada().setBalasEnMunicion(balasEnMunicion);
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
		entidadManager.añadirProyectil(tipoProyectil, x, y, velocidad, daño, disparadoPorJugador);
	}
	public void actualizarProyectilPosicion(int projectileId, float x, float y) {
		entidadManager.moverProyectil(projectileId, x, y);
	}
	@Override
	public void removerProyectil(int proyectilId) {
		entidadManager.removerProyectil(proyectilId);
	}
	@Override
	public void añadirObjeto(String tipoObjeto, float x, float y) {
		entidadManager.añadirObjeto(tipoObjeto, x, y);
	}
	@Override
	public void removerObjeto(int objetoId) {
		entidadManager.removerObjeto(objetoId);
	}
	@Override
	public void actualizarTiempo(int tiempo) {
		this.hud.actualizarTemporizador(tiempo);
	}
	@Override
	public void actualizarRonda(int ronda) {
		this.hud.actualizarRonda(ronda);
	}
	@Override
	public void actualizarEnemigosRestantes(int cantidad) {
		this.hud.actualizarEnemigosRestantes(cantidad);
	}

 }
