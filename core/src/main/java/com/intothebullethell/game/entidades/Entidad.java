package com.intothebullethell.game.entidades;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.intothebullethell.game.managers.TileColisionManager;

public abstract class Entidad extends Sprite {
	private TileColisionManager tileCollisionManager = new TileColisionManager();
    protected int vidaMaxima;
    protected float velocidad;
    protected Texture projectilTextura;
    protected Rectangle boundingBox = new Rectangle(getX(), getY(), getWidth(), getHeight());
    public Vector2 velocity = new Vector2(); 

    public Entidad(Texture texture, int vidaMaxima, int velocidad, Texture projectilTextura) {
        super(texture);
        this.vidaMaxima = vidaMaxima;
        this.velocidad = velocidad;
        this.projectilTextura = projectilTextura;
    }

    public void mover(Vector2 velocity) {
        float oldX = getX();
        float oldY = getY();

        float newX = getX() + (velocity.x * Gdx.graphics.getDeltaTime());
        float newY = getY() + (velocity.y * Gdx.graphics.getDeltaTime());
        
        tileCollisionManager.setPosicionChequearColision(this, newX, newY, oldX, oldY);
    }


    public void updateBoundingBox() {
        boundingBox.setPosition(getX(), getY());
        boundingBox.setSize(getWidth(), getHeight());
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVida(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public Texture getProjectilTextura() {
        return projectilTextura;
    }

    public void setProjectilTextura(Texture projectilTextura) {
        this.projectilTextura = projectilTextura;
    }

    public void recibirDaño(int daño) {}

    public abstract void update(float delta);

	public void atacar() {}
}
