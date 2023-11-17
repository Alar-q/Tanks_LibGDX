package com.rat6.game.explosion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;

public class Explosion {
    private TextureRegion[] explosionFrames; // Кадры анимации взрыва
    private Vector2 position;
    private float frameDuration; // Длительность отображения одного кадра
    private float elapsedTime; // Время, прошедшее с начала взрыва

    public Explosion(Assets assets, Vector2 position, float frameDuration) {
        this.explosionFrames = assets.explosionFrames;
        this.position = position;
        this.frameDuration = frameDuration;
        this.elapsedTime = 0;
    }

    public void update(float deltaTime) {
        elapsedTime += deltaTime;
    }

    public void render(SpriteBatch batch) {
        int frameIndex = (int)(elapsedTime / frameDuration);
        if (frameIndex < explosionFrames.length) {
            batch.draw(explosionFrames[frameIndex], position.x, position.y);
        }
    }

    public boolean isFinished() {
        return elapsedTime >= frameDuration * explosionFrames.length;
    }
}
