package com.intothebullethell.game.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.intothebullethell.game.globales.RecursoRuta;
import com.intothebullethell.game.managers.RenderManager;
import com.intothebullethell.game.objects.activos.Activo;
import com.intothebullethell.game.objects.armas.Arma;

public class Hud {
    private Stage stage;
    private Texture armaSprite, activoSprite;
    private Texto textoRonda, textoTiempo, textoMunicion, textoEnemigosRestantes;
    private Arma armaEquipada;
    private Activo activoEquipado;
    
    private int vidaMaxima, vidaActual;
    private boolean activoUsado = false;
    
    public Hud() {
        this.stage = new Stage(new ScreenViewport(), RenderManager.batchRender);

        textoRonda = new Texto("Ronda: 0", 20, Color.WHITE, 10, Gdx.graphics.getHeight() - 20);
        textoRonda.setShadow(4, 4, Color.BLACK);
        
        textoTiempo = new Texto("Randomizador en: 0s", 20, Color.WHITE, 10, Gdx.graphics.getHeight() - 20);
        textoTiempo.setShadow(4, 4, Color.BLACK);
        textoTiempo.centerX();
        
        textoMunicion = new Texto("", 20, Color.WHITE, 10, Gdx.graphics.getHeight() - 850);
        textoMunicion.setShadow(4, 4, Color.BLACK);
        
        textoEnemigosRestantes = new Texto("Enemigos restantes: 0", 20, Color.WHITE, 10, Gdx.graphics.getHeight() - 60);
        textoEnemigosRestantes.setShadow(4, 4, Color.BLACK);
        
    }

    public void draw() {
    	
    	int x = Gdx.graphics.getWidth() - (vidaMaxima / 2 * RecursoRuta.CORAZON_LLENO.getWidth());
    	int y = Gdx.graphics.getHeight() - RecursoRuta.CORAZON_LLENO.getHeight();
    		
    	HudUtiles.dibujarCorazones(RecursoRuta.CORAZON_LLENO, RecursoRuta.CORAZON_MITAD, RecursoRuta.CORAZON_VACIO, vidaMaxima, vidaActual, x, y);
    	HudUtiles.dibujarMunicion(armaEquipada, textoMunicion);
    	if(armaSprite != null) {
    		RenderManager.batch.draw(armaSprite, Gdx.graphics.getWidth() - armaSprite.getWidth() * 3 - 20, 0, armaSprite.getWidth() * 3 - 10, armaSprite.getHeight() * 3);
    	}
    	if(activoSprite != null && !activoUsado) {
    		RenderManager.batch.draw(activoSprite, Gdx.graphics.getWidth() - activoSprite.getWidth() * 3 - 20, armaSprite.getHeight() * 3 + 10, activoSprite.getWidth() * 3 - 10, activoSprite.getHeight() * 3);
    	}
    	textoRonda.draw();
        textoTiempo.draw();
        textoEnemigosRestantes.draw();
        stage.draw();
    }

    public void actualizarRonda(int nuevaRonda) {
        textoRonda.setTexto("Ronda: " + nuevaRonda);
    }

    public void actualizarTemporizador(int tiempo) {
        textoTiempo.setTexto("Aleatorizando en: " + tiempo + "s");
    }
    public void actualizarEnemigosRestantes(int cantidad) {
    	textoEnemigosRestantes.setTexto("Enemigos restantes: " + cantidad);
    }
    public void actualizarVida(int vidaMaxima, int vidaActual) {
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaActual;
    }
    public void actualizarArma(Arma arma) {
        this.armaEquipada = arma;
        this.armaSprite = armaEquipada.getArmaTextura();
    }
    public void actualizarActivo(Activo activo) {
        this.activoEquipado = activo;
        this.activoSprite = activoEquipado.getActivoTextura();
    }
    public void setActivoUsado(boolean activoUsado) {
    	this.activoUsado = activoUsado;
    }
    public void dispose() {
        stage.dispose();
        textoRonda.dispose();
        textoTiempo.dispose();
        textoMunicion.dispose();
        textoEnemigosRestantes.dispose();
    }
}
