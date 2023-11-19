package com.rat6.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TouchInputProcessor implements InputProcessor {
    private OrthographicCamera camera;
    private float touchX = 0;
    private float touchY = 0;
    private boolean touched = false;
    public TouchInputProcessor(OrthographicCamera camera){
        this.camera = camera;
    }
    public Vector2 screenToWorldCoordinates(float screenX, float screenY) {
        Vector3 screenCoords = new Vector3(screenX, screenY, 0);
        camera.unproject(screenCoords); // Преобразует экранные координаты в координаты мира
        return new Vector2(screenCoords.x, screenCoords.y);
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldCoordinates = screenToWorldCoordinates(screenX, screenY);

        touchX = worldCoordinates.x;
        touchY = worldCoordinates.y;
        touched = true;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touched = false;
        return true;
    }

    // Остальные методы интерфейса InputProcessor...

    public boolean isTouched() {
        return touched;
    }

    public float getTouchX() {
        return touchX;
    }

    public float getTouchY() {
        return touchY;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
