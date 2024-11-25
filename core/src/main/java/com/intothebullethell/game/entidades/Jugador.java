package com.intothebullethell.game.entidades;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.intothebullethell.game.globales.GameData;
import com.intothebullethell.game.globales.NetworkData;
import com.intothebullethell.game.inputs.InputManager;
import com.intothebullethell.game.objects.activos.Activo;
import com.intothebullethell.game.objects.activos.Adrenalina;
import com.intothebullethell.game.objects.activos.AnilloUnico;
import com.intothebullethell.game.objects.activos.Sanguche;
import com.intothebullethell.game.objects.armas.AWP;
import com.intothebullethell.game.objects.armas.Arma;
import com.intothebullethell.game.objects.armas.BFG9000;
import com.intothebullethell.game.objects.armas.Bengala;
import com.intothebullethell.game.objects.armas.Blaster;
import com.intothebullethell.game.objects.armas.Escopeta;
import com.intothebullethell.game.objects.armas.EstrellaNinja;
import com.intothebullethell.game.objects.armas.Pistola;
import com.intothebullethell.game.objects.armas.Sniper;

public class Jugador extends Entidad {
	public OrthographicCamera camara;
	private Bengala bengala = new Bengala();
    private Vector2 mousePosition = new Vector2();
    private AnimacionEntidad animacionJugador = new AnimacionEntidad();;
    private Arma armaEquipada;
    private Activo activoEquipado;
    private InputManager inputManager;
    
    private int id; 
    private float opacidad = 1.0f;
    
    private boolean disparando = false;
    private boolean muerto = false;
    private boolean moviendose = false;

    public Jugador(int id, TextureRegion sprite, OrthographicCamera camara, InputManager inputManager) {
    	super(sprite.getTexture(), 20, 150, null);
    	this.id = id;
        this.camara = camara;
        this.inputManager = inputManager;
        this.inputManager.setJugador(this);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch); 
    }

    @Override
    public void update(float delta) {
    	if (GameData.clienteNumero == this.getId() && !estaMuerto()) { 
    		setColor(1.0f, 1.0f, 1.0f, opacidad); 
    		actualizarSprite();
    		actualizarCamara();
    	}
    }
    private void actualizarSprite() {
        Vector2 jugadorCentro = new Vector2(getX() + getWidth() / 2, getY() + getHeight() / 2);
        Vector3 mouseWorldPos3 = camara.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        Vector2 mouseWorldPos = new Vector2(mouseWorldPos3.x, mouseWorldPos3.y);
        Vector2 direction = mouseWorldPos.sub(jugadorCentro).nor();
        float angulo = direction.angleDeg();

        String direccion;

        if (angulo >= 45 && angulo < 135) {
            animacionJugador.actualizarAnimacionJugador(this, "arriba", this.moviendose);
            direccion = "arriba";
        } else if (angulo >= 135 && angulo < 225) {
            animacionJugador.actualizarAnimacionJugador(this, "izquierda", this.moviendose);
            direccion = "izquierda";
        } else if (angulo >= 225 && angulo < 315) {
            animacionJugador.actualizarAnimacionJugador(this, "abajo", this.moviendose);
            direccion = "abajo";
        } else {
            animacionJugador.actualizarAnimacionJugador(this, "derecha", this.moviendose);
            direccion = "derecha";
        }
        NetworkData.clientThread.enviarMensajeAlServidor("mover!direccion!" + GameData.clienteNumero + "!" + direccion);
    }
    public void actualizarCamara() {
        camara.position.set(getX() + getWidth() / 2, getY() + getHeight() / 2, 0);
        camara.update();
    }
    public void setMousePosition(int screenX, int screenY) {
        mousePosition.set(screenX, screenY);
        mousePosition = mousePosition.scl(1, -1).add(0, Gdx.graphics.getHeight());
    }
    public void morir() {
    	this.muerto = true;
    }
    public boolean estaMuerto() {
        return muerto;
    }
    public void reiniciar() {
    	disparando = false;
    	velocity.x = 0;
    	velocity.y = 0;
    }
    public void cambiarArma(String nombreArma) {
        this.armaEquipada = obtenerArma(nombreArma);
    }
    public void cambiarActivo(String nombrActivo) {
        this.activoEquipado = obtenerActivo(nombrActivo);
    }
    public void usoRestantesBengala(int cantidad) {
    	this.bengala.setUsosRestantes(cantidad);
    }
    public void setDisparando(boolean disparando) { 
    	this.disparando = disparando; 
    }
    public boolean isDisparando() {
        return disparando;
    }
    public Arma getArmaEquipada() { 
    	return armaEquipada; 
    }
    public Activo getActivoEquipado() { 
    	return activoEquipado; 
    }
    public Bengala getBengala() {
		return bengala;
	}
    public Texture getArmaTextura() {
    	return armaEquipada.getArmaTextura();
    }
    public void aumentarVelocidad(int velocidad) {
    	this.velocidad += velocidad;
    }
    public AnimacionEntidad getAnimacionJugador() {
        return animacionJugador;
    }
    public void setOpacidad(float opacidad) {
		this.opacidad = opacidad;
	}
    public void setMoviendose(boolean moviendose) {
		this.moviendose = moviendose;
	}
    public int getId() {
        return id;
    }
    public TextureRegion obtenerRegionDesdeNombre(String region) {
    	TextureRegion textura = null;
        switch(region) {
            case "arriba":
            	textura = animacionJugador.getJugadorAnimacionesArribaUno();
                break;
            case "abajo":
            	textura = animacionJugador.getJugadorAnimacionesAbajoUno();
             	break;
            case "izquierda":
            	textura = animacionJugador.getJugadorAnimacionesIzquierdaUno();
            	break;
            case "derecha":
            	textura = animacionJugador.getJugadorAnimacionesDerechaUno();
             	break;
        }
        return textura;
    }
    private Arma obtenerArma(String nombreArma) {
    	Arma arma = null;
    	switch (nombreArma) {
		case "Pistola":
			arma = new Pistola();
			break;
		case "Escopeta":
			arma = new Escopeta();
			break;
		case "AWP":
			arma = new AWP();
			break;
		case "Sniper":
			arma = new Sniper();
			break;
		case "Blaster":
			arma = new Blaster();
			break;
		case "Estrella ninja":
			arma = new EstrellaNinja();
			break;
		case "BFG 9000":
			arma = new BFG9000();
			break;
		}
		return arma;
	}
    private Activo obtenerActivo(String nombreActivo) {
    	Activo activo = null;
    	switch (nombreActivo) {
		case "Adrenalina":
			activo = new Adrenalina();
			break;
		case "Sanguche":
			activo =  new Sanguche();
			break;
		case "Anillo Unico":
			activo =  new AnilloUnico();
			break;
		}
		return activo;
	}
    
	
}
