package com.rat6.game.game_objects.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static com.rat6.game.MyGdxGame.WORLD_HEIGHT;
import static com.rat6.game.MyGdxGame.WORLD_WIDTH;

public class Bullet {
    private TextureRegion bulletTextureRegion;
    private Vector2 position;
    private Vector2 velocity;
    private Circle boundingCircle;

    public Bullet(TextureRegion textureRegion, float x, float y, float velocityX, float velocityY) {
        this.bulletTextureRegion = textureRegion;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(velocityX, velocityY);
        float radius = Math.max(textureRegion.getRegionWidth(), textureRegion.getRegionHeight()) / 2f;
        this.boundingCircle = new Circle(x + radius, y + radius, radius);
    }

    public void update() {
        position.x += velocity.x * Gdx.graphics.getDeltaTime();
        position.y += velocity.y * Gdx.graphics.getDeltaTime();
        boundingCircle.setPosition(position.x + boundingCircle.radius, position.y + boundingCircle.radius);
    }

    public void render(SpriteBatch batch) {
        batch.draw(bulletTextureRegion, position.x, position.y);
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public boolean isOutOfBounds() {
        return position.x < 0 || position.y < 0 || position.x > WORLD_WIDTH || position.y > WORLD_HEIGHT;
    }

    public Vector2 getPosition(){
        return position;
    }
}

