package com.rat6.game.game_objects.explosion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;

import java.util.Arrays;

public class Explosion {
    private TextureRegion[] explosionFrames; // Кадры анимации взрыва
    private float x, y;
    private float frameDuration; // Длительность отображения одного кадра
    private float elapsedTime; // Время, прошедшее с начала взрыва

    public Explosion(TextureRegion[] explosionFrames, float x, float y, float frameDuration) {
        this.explosionFrames = explosionFrames;
        this.x = x;
        this.y = y;
        this.frameDuration = frameDuration;
        this.elapsedTime = 0;
    }

    public void update(float deltaTime) {
        elapsedTime += deltaTime;
    }

    public void render(SpriteBatch batch) {
        int frameIndex = (int)(elapsedTime / frameDuration);
        if (frameIndex < explosionFrames.length) {
            batch.draw(explosionFrames[frameIndex],
                    x - explosionFrames[frameIndex].getRegionWidth()/2f,
                    y - explosionFrames[frameIndex].getRegionHeight()/2f
            );
        }
    }

    public boolean isFinished() {
        return elapsedTime >= frameDuration * explosionFrames.length;
    }
}
