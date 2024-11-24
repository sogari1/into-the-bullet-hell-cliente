package com.intothebullethell.game.inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.intothebullethell.game.entidades.Jugador;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {
	private Jugador jugador;
    private boolean pausaSolicitada;

    private boolean up, down, left, right, recargar, disparar, usoBengala;
    private boolean upJustReleased, downJustReleased, leftJustReleased, rightJustReleased, recargarJustReleased, disparandoJustReleased, usoBengalaJustReleased;
    private boolean recargarJustPresed, usoBengalaJustPressed;
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setPausaSolicitada(boolean pausaSolicitada) {
        this.pausaSolicitada = pausaSolicitada;
    }

    public boolean isPausaSolicitada() {
        return pausaSolicitada;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.W:
                up = true;
                upJustReleased = false;
                break;
            case Keys.A:
                left = true;
                leftJustReleased = false;
                break;
            case Keys.D:
                right = true;
                rightJustReleased = false;
                break;
            case Keys.S:
                down = true;
                downJustReleased = false;
                break;
            case Keys.R:
                recargar = recargarJustPresed = true;
                recargarJustReleased = false;
                break;
            case Keys.Q:
            	usoBengala  = usoBengalaJustPressed = true;
            	usoBengalaJustReleased = false;
            	break;
            case Keys.ESCAPE:
                pausaSolicitada = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.W:
                up = false;
                upJustReleased = true;
                break;
            case Keys.A:
                left = false;
                leftJustReleased = true;
                break;
            case Keys.D:
                right = false;
                rightJustReleased = true;
                break;
            case Keys.S:
                down = false;
                downJustReleased = true;
                break;
            case Keys.R:
                recargar = recargarJustPresed = false;
                recargarJustReleased = true;
                break;
            case Keys.Q:
            	usoBengala  = usoBengalaJustPressed = false;
            	usoBengalaJustReleased = true;
            	break;
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            disparar = true;
            disparandoJustReleased = false;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            disparar = false;
            disparandoJustReleased = true;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        jugador.setMousePosition(screenX, screenY);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    public boolean isUpJustReleased() {
        if (upJustReleased) {
            upJustReleased = false;
            return true;
        }
        return false;
    }

    public boolean isDownJustReleased() {
        if (downJustReleased) {
            downJustReleased = false;
            return true;
        }
        return false;
    }

    public boolean isLeftJustReleased() {
        if (leftJustReleased) {
            leftJustReleased = false;
            return true;
        }
        return false;
    }

    public boolean isRightJustReleased() {
        if (rightJustReleased) {
            rightJustReleased = false;
            return true;
        }
        return false;
    }
    public boolean isDisparandoJustReleased() {
        if (disparandoJustReleased) {
            disparandoJustReleased = false;
            return true;
        }
        return false;
    }
    public boolean isRecargarJustReleased() {
    	if(recargarJustReleased) {
    		recargarJustReleased = false;
    		return true;
    	}
    	return false;
    }
    public boolean isUsoBengalaReleased() {
    	if(usoBengalaJustReleased) {
    		usoBengalaJustReleased = false;
    		return true;
    	}
    	return false;
    }
    public boolean isRecargarJustPressed() {
    	if(recargarJustPresed) {
    		recargarJustPresed = false;
    		return true;
    	}
    	return false;
    }
    public boolean isUsoBengalaPressed() {
    	if(usoBengalaJustPressed) {
    		usoBengalaJustPressed = false;
    		return true;
    	}
    	return false;
    }
    public boolean isUp() { return up; }
    public boolean isDown() { return down; }
    public boolean isLeft() { return left; }
    public boolean isRight() { return right; }
    public boolean isRecargar() { return recargar; }
    public boolean isDisparar() { return disparar; }
    public boolean isUsoBengala() { return usoBengala; }

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}
}
