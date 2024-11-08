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
import com.intothebullethell.game.managers.ProyectilManager;
import com.intothebullethell.game.mecanicas.ArmaAleatoria;
import com.intothebullethell.game.objects.armas.Arma;
import com.intothebullethell.game.ui.Hud;

public class Jugador extends Entidad {
	public OrthographicCamera camara;
    private Vector2 mousePosition = new Vector2();
    private Arma armaEquipada;
    private ArmaAleatoria armaAleatoria = new ArmaAleatoria();
    private TextureRegion upSprite, downSprite, leftSprite, rightSprite;
    private Hud hud;
    private InputManager inputManager;
    private EntidadManager entidadManager;
    
    private float shootTimer = 0;
    private float opacidad = 1.0f;
    private float escudoCoolDown = 0;
    private final float escudoCoolDownMaximo = 2.5f; 
    private int vidaActual;
    private int id; 
    
    private boolean disparando = false;

    public Jugador(int id, TextureRegion sprite, TextureRegion upSprite, TextureRegion downSprite, TextureRegion leftSprite, TextureRegion rightSprite, OrthographicCamera camara, InputManager inputManager, EntidadManager entidadManager, ProyectilManager proyectilManager) {
    	super(sprite.getTexture(), 20, 100, null);
    	this.id = id;
        this.upSprite = upSprite;
        this.downSprite = downSprite;
        this.leftSprite = leftSprite;
        this.rightSprite = rightSprite;
        this.camara = camara;
        this.vidaActual = vidaMaxima;
        this.armaEquipada = armaAleatoria.obtenerArmaAleatoria();
        this.inputManager = inputManager;
        this.inputManager.setJugador(this);
        this.entidadManager = entidadManager;
    }

    @Override
    public void draw(Batch batch) {
    	update(Gdx.graphics.getDeltaTime());
        super.draw(batch); 
        entidadManager.grupoProyectiles.draw();

    }

    @Override
    public void update(float delta) {
//    	Jugador[] jugadores = new Jugador[]{this};
    	 if (GameData.clienteNumero == this.getId()) { 
    	 if (escudoCoolDown > 0) {
    		 escudoCoolDown -= delta; 
    		 opacidad = 0.5f;
         }
    	 else {
             opacidad = 1.0f; 
    	 }
    	 setColor(1.0f, 1.0f, 1.0f, opacidad); 
//    	 actualizarMovimiento();
//         manejarDisparos(delta);
         actualizarSprite();
         actualizarCamara();
//         entidadManager.grupoProyectiles.actualizarProyectiles(delta, entidadManager.getgrupoEnemigos().getEntidades(), jugadores);
    	 }
    }

    private void actualizarMovimiento() {
        mover(velocity);
    }
    
    public void moverArriba() {
        velocity.y = velocidad;
    }
    public void moverAbajo() {
        velocity.y = -velocidad;
    }
    public void moverIzquierda() {
        velocity.x = -velocidad;
    }
    public void moverDerecha() {
        velocity.x = velocidad;
    }
    
    private void manejarDisparos(float delta) {
        if (isDisparando()) {
            shootTimer -= delta;
            if (shootTimer <= 0) {
            	entidadManager.grupoProyectiles.dispararProyectil(camara, armaEquipada, getX() + getWidth() / 2, getY() + getHeight() / 2, Gdx.input.getX(), Gdx.input.getY());
                shootTimer = armaEquipada.getRatioFuego(); 
            }
        }
    }
    private void actualizarCamara() {
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

    public void setMousePosition(int screenX, int screenY) {
        mousePosition.set(screenX, screenY);
        mousePosition = mousePosition.scl(1, -1).add(0, Gdx.graphics.getHeight());
    }
    public void recargarArma() {
    	armaEquipada.reload();
    }
    public int getVidaActual() {
        return vidaActual;
    }
    public boolean chequearMuerte() {
        if (vidaActual == 0) {
            return true; 
        }
        return false; 
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
    public float getShieldCooldown() {
        return escudoCoolDown;
    }
	public void setHud(Hud hud) { 
    	this.hud = hud; 
    }
    public void cambiarArma() {
        this.armaEquipada = armaAleatoria.obtenerArmaAleatoria();
        hud.updateWeaponSprite(); 
    }
    public void setArma(Arma arma) {
        this.armaEquipada = arma;
        hud.updateWeaponSprite();
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
    public float getShootTimer() {
        return shootTimer;
    }
    public void setShootTimer(float shootTimer) {
        this.shootTimer = shootTimer;
    }

    public Texture getArmaTextura() {
    	return armaEquipada.getArmaTextura();
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
    public int getId() {
        return id;
    }
	
}
