package com.rat6.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TouchInputProcessor implements InputProcessor {
    private Viewport viewport;
    private Map<Integer, Vector2> touches = new HashMap<>();

    public TouchInputProcessor(Viewport viewport){
        this.viewport = viewport;
    }

    public Vector2 screenToWorldCoordinates(float screenX, float screenY) {
        Vector3 screenCoords = new Vector3(screenX, screenY, 0);
        viewport.unproject(screenCoords);
        return new Vector2(screenCoords.x, screenCoords.y);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldCoordinates = screenToWorldCoordinates(screenX, screenY);
        touches.put(pointer, worldCoordinates);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touches.remove(pointer);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (touches.containsKey(pointer)) {
            Vector2 newCoords = screenToWorldCoordinates(screenX, screenY);
            touches.put(pointer, newCoords);
        }
        return true;
    }

    // Остальные методы...

    public boolean isTouched(int pointer) {
        return touches.containsKey(pointer);
    }
    public Set<Integer> getActivePointers() {
        return touches.keySet();
    }
    public Vector2 getTouchCoordinates(int pointer) {
        return touches.get(pointer);
    }
    public int pressed(Rectangle rectangle){
        for (Integer pointer : getActivePointers()) {
            Vector2 touchCoords = getTouchCoordinates(pointer);
            if (touchCoords != null && rectangle.contains(touchCoords.x, touchCoords.y)) {
                return pointer;
            }
        }
        return -1;
    }
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
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
