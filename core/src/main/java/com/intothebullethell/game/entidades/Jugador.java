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
import com.intothebullethell.game.managers.EntidadManager;
import com.intothebullethell.game.objects.activos.Activo;
import com.intothebullethell.game.objects.activos.Adrenalina;
import com.intothebullethell.game.objects.activos.Sanguche;
import com.intothebullethell.game.objects.armas.Arma;
import com.intothebullethell.game.objects.armas.Escopeta;
import com.intothebullethell.game.objects.armas.Pistola;

public class Jugador extends Entidad {
	public OrthographicCamera camara;
    private Vector2 mousePosition = new Vector2();
    private Arma armaEquipada;
    private Activo activoEquipado;
    private TextureRegion upSprite, downSprite, leftSprite, rightSprite;
    private InputManager inputManager;
    
    private float shootTimer = 0;
    private float opacidad = 1.0f;
    private float escudoCoolDown = 0;
    private final float escudoCoolDownMaximo = 2.5f; 
    private int id; 
    
    private boolean disparando = false;
    private boolean muerto = false;

    public Jugador(int id, TextureRegion sprite, TextureRegion upSprite, TextureRegion downSprite, TextureRegion leftSprite, TextureRegion rightSprite, OrthographicCamera camara, InputManager inputManager, EntidadManager entidadManager) {
    	super(sprite.getTexture(), 20, 150, null);
    	this.id = id;
        this.upSprite = upSprite;
        this.downSprite = downSprite;
        this.leftSprite = leftSprite;
        this.rightSprite = rightSprite;
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
    		manejarEscudoCoolDown(delta);
    		actualizarSprite();
    		actualizarCamara();
    	}
    }
    public void actualizarCamara() {
        camara.position.set(getX() + getWidth() / 2, getY() + getHeight() / 2, 0);
        camara.update();
    }

    private void actualizarSprite() {
        Vector2 jugadorCentro = new Vector2(getX() + getWidth() / 2, getY() + getHeight() / 2);
        Vector3 mouseWorldPos3 = camara.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        Vector2 mouseWorldPos = new Vector2(mouseWorldPos3.x, mouseWorldPos3.y);
        Vector2 direction = mouseWorldPos.sub(jugadorCentro).nor();
        float angulo = direction.angleDeg();

        String direccion;

        if (angulo >= 45 && angulo < 135) {
            setRegion(upSprite);  // Arriba
            direccion = "arriba";
        } else if (angulo >= 135 && angulo < 225) {
            setRegion(leftSprite);  // Izquierda
            direccion = "izquierda";
        } else if (angulo >= 225 && angulo < 315) {
            setRegion(downSprite);  // Abajo
            direccion = "abajo";
        } else {
            setRegion(rightSprite);  // Derecha
            direccion = "derecha";
        }
        NetworkData.clientThread.enviarMensajeAlServidor("mover!direccion!" + GameData.clienteNumero + "!" + direccion);
    }
    private void manejarEscudoCoolDown(float delta) {
    	if (escudoCoolDown > 0) {
    		escudoCoolDown -= delta;
    		opacidad = 0.5f; 
    	} else {
    		opacidad = 1.0f;
    	}
    	setColor(1.0f, 1.0f, 1.0f, opacidad); 
    }
    public void setMousePosition(int screenX, int screenY) {
        mousePosition.set(screenX, screenY);
        mousePosition = mousePosition.scl(1, -1).add(0, Gdx.graphics.getHeight());
    }
    public void recargarArma() {
    	armaEquipada.recargar();
    }
    public int getVidaActual() {
        return vidaActual;
    }
    @Override
    public void recibirDaño(int daño) {
        if (escudoCoolDown <= 0) {
            vidaActual -= daño;
            if (vidaActual < 0) {
                vidaActual = 0; 
            }
            escudoCoolDown = escudoCoolDownMaximo; 
        }
    }
    public void aumentarVida(int vida) {
    	this.vidaActual += vida;
    	if(this.vidaActual > this.vidaMaxima) {
    		this.vidaActual = this.vidaMaxima;
    	}
    }
    public boolean chequearMuerte() {
        if (vidaActual <= 0) {
            return true; 
        }
        return false; 
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
    public float getShieldCooldown() {
        return escudoCoolDown;
    }
    public void cambiarArma(String nombreArma) {
        this.armaEquipada = obtenerArma(nombreArma);
    }
    public void cambiarActivo(String nombrActivo) {
        this.activoEquipado = obtenerActivo(nombrActivo);
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
    public float getShootTimer() {
        return shootTimer;
    }
    public void setShootTimer(float shootTimer) {
        this.shootTimer = shootTimer;
    }
    public Texture getArmaTextura() {
    	return armaEquipada.getArmaTextura();
    }
    public void aumentarVelocidad(int velocidad) {
    	this.velocidad += velocidad;
    }
    public TextureRegion obtenerRegionDesdeNombre(String region) {
    	TextureRegion textura = null;
        switch(region) {
            case "arriba":
            	textura = upSprite;
                break;
            case "abajo":
            	textura = downSprite;
             	break;
            case "izquierda":
            	textura = leftSprite;
            	break;
            case "derecha":
            	textura = rightSprite;
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
			arma =  new Escopeta();
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
		}
		return activo;
	}
    public int getId() {
        return id;
    }
    
	
}
