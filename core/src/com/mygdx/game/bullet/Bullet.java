package com.mygdx.game.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.tank.enums.TankDirection;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

public class Bullet {
    private TextureRegion bulletTextureRegion;
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle boundingBox;

    public Bullet(TextureRegion textureRegion, float x, float y, float velocityX, float velocityY) {
        this.bulletTextureRegion = textureRegion;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(velocityX, velocityY);
        this.boundingBox = new Rectangle(x, y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    public void update() {
        System.out.println("x=" + position.x + ", y=" + position.y);
        position.x += velocity.x * Gdx.graphics.getDeltaTime();;
        position.y += velocity.y * Gdx.graphics.getDeltaTime();;
        boundingBox.setPosition(position);
    }

    public void render(SpriteBatch batch) {
        batch.draw(bulletTextureRegion, position.x, position.y);
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public boolean isOutOfBounds() {
        // Проверка, вышла ли пуля за пределы игрового поля
        return position.x < 0 || position.y < 0 || position.x > WORLD_WIDTH || position.y > WORLD_HEIGHT;
    }
}
