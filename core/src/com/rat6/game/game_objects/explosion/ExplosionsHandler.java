package com.rat6.game.game_objects.explosion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;
import com.rat6.game.game_objects.GameObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExplosionsHandler {
    private List<Explosion> explosions;

    public ExplosionsHandler(List<Explosion> explosions){
        this.explosions = explosions;
    }

    public void update(float deltaTime) {
        // Обновление взрывов
        Iterator<Explosion> explosionIterator = explosions.iterator();
        while (explosionIterator.hasNext()) {
            Explosion explosion = explosionIterator.next();
            explosion.update(deltaTime);
            if (explosion.isFinished()) {
                explosionIterator.remove();
            }
        }
    }
}
