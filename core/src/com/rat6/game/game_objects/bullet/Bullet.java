package com.rat6.game.game_objects.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.game_objects.GameObject;
import com.rat6.game.world.World;

import static com.rat6.game.MyGdxGame.WORLD_HEIGHT;
import static com.rat6.game.MyGdxGame.WORLD_WIDTH;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Bullet extends GameObject {
    private World world;
    private TextureRegion bulletTextureRegion;
    private Vector2 velocity;

    public Bullet(World world, UUID id, TextureRegion textureRegion, float x, float y, float velocityX, float velocityY) {
        super();
        this.id = id;
        this.world = world;
        this.bulletTextureRegion = textureRegion;
        this.velocity = new Vector2(velocityX, velocityY);
        float radius = Math.min(textureRegion.getRegionWidth(), textureRegion.getRegionHeight()) / 2f;
        circle(x, y, radius);
    }

    public void update(float deltaTime) {
        circle.x += velocity.x * deltaTime;
        circle.y += velocity.y * deltaTime;
    }

    public void render(SpriteBatch batch) {
        batch.draw(bulletTextureRegion, circle.x - circle.radius, circle.y - circle.radius);
    }

}

