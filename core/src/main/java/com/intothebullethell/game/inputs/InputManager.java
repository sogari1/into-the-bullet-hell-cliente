package com.intothebullethell.game.inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.intothebullethell.game.entidades.Jugador;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {
	private Jugador jugador;
    private boolean pausaSolicitada;

    private boolean up, down, left, right, recargar, disparar;
    private boolean upJustPressed, downJustPressed, leftJustPressed, rightJustPressed, disparandoJustPressed;
    private boolean upJustReleased, downJustReleased, leftJustReleased, rightJustReleased, disparandoJustReleased;


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
                up = upJustPressed = true;
                upJustReleased = false;
                break;
            case Keys.A:
                left = leftJustPressed = true;
                leftJustReleased = false;
                break;
            case Keys.D:
                right = rightJustPressed = true;
                rightJustReleased = false;
                break;
            case Keys.S:
                down = downJustPressed = true;
                downJustReleased = false;
                break;
            case Keys.R:
                recargar = true;
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
                up = upJustPressed = false;
                upJustReleased = true;
                break;
            case Keys.A:
                left = leftJustPressed = false;
                leftJustReleased = true;
                break;
            case Keys.D:
                right = rightJustPressed = false;
                rightJustReleased = true;
                break;
            case Keys.S:
                down = downJustPressed = false;
                downJustReleased = true;
                break;
            case Keys.R:
                recargar = false;
                break;
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            disparar = disparandoJustPressed = true;
            disparandoJustReleased = false;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            disparar = disparandoJustPressed = false;
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

    public boolean isUpJustPressed() {
        if (upJustPressed) {
            upJustPressed = false;
            return true;
        }
        return false;
    }

    public boolean isDownJustPressed() {
        if (downJustPressed) {
            downJustPressed = false;
            return true;
        }
        return false;
    }

    public boolean isLeftJustPressed() {
        if (leftJustPressed) {
            leftJustPressed = false;
            return true;
        }
        return false;
    }

    public boolean isRightJustPressed() {
        if (rightJustPressed) {
            rightJustPressed = false;
            return true;
        }
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

    public boolean isUp() { return up; }
    public boolean isDown() { return down; }
    public boolean isLeft() { return left; }
    public boolean isRight() { return right; }
    


    public boolean isRecargar() {
    	return recargar;
    }
    public boolean isDisparar() {
        return disparar;
    }

    public boolean isDisparandoJustPressed() {
        if (disparandoJustPressed) {
            disparandoJustPressed = false;
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
	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}
}
