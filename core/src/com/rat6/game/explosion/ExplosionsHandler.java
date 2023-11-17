package com.rat6.game.explosion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExplosionsHandler {
    private List<Explosion> explosions = new ArrayList<>();
    private Assets assets;

    public ExplosionsHandler(Assets assets){
        this.assets = assets;
    }

    public void createExplosion(float x, float y) {
        System.out.println("createExplosion: x=" + x + ", y=" + y);
        Explosion explosion = new Explosion(assets, new Vector2(x, y), 0.1f); // 0.1f - длительность кадра в секундах
        explosions.add(explosion);
    }

    public void update() {
        // Обновление взрывов
        Iterator<Explosion> explosionIterator = explosions.iterator();
        while (explosionIterator.hasNext()) {
            Explosion explosion = explosionIterator.next();
            explosion.update(Gdx.graphics.getDeltaTime());
            if (explosion.isFinished()) {
                explosionIterator.remove();
            }
        }
    }

    public void render(SpriteBatch batch) {
        // Отрисовка взрывов
        for (Explosion explosion : explosions) {
            explosion.render(batch);
        }
    }
}
