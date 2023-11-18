package com.rat6.game.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public abstract class GameObject extends Circle {
    public abstract void update();
    public abstract void render(SpriteBatch batch);
}
