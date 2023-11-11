package com.mygdx.game.bullet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.tank.enums.TankDirection;

import java.util.ArrayList;
import java.util.Iterator;

public class BulletsHandler {
    private ArrayList<Bullet> bullets;
    private TextureRegion bulletTexture;
    private TextureRegion[] explosions;

    public BulletsHandler(Assets assets) {
        this.bullets = new ArrayList<>();
        this.bulletTexture = assets.cannonball;
        this.explosions = assets.explosions;
    }

    public void createBullet(float x, float y, TankDirection direction, float velocity) {
        Vector2 v = getBulletVelocity(direction, velocity);
        createBullet(x, y, v.x, v.y);
    }

    public void createBullet(float x, float y, float velocityX, float velocityY) {
        Bullet newBullet = new Bullet(bulletTexture, x - bulletTexture.getRegionWidth()/2f, y - bulletTexture.getRegionHeight()/2f, velocityX, velocityY);
        bullets.add(newBullet);
    }

    /**
     * Обновление положения каждой пули в мире
     * */
    public void update() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.update();

            if (bullet.isOutOfBounds()) {
                iterator.remove(); // Безопасно удаляем пулю из списка
            }
        }
    }


    public void render(SpriteBatch batch) {
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
    }

    private Vector2 getBulletVelocity(TankDirection direction, float bulletSpeed) {
        // Здесь возвращаем вектор скорости в зависимости от направления танка
        // float bulletSpeed = 300; // Пример скорости пули
        switch (direction) {
            case RIGHT:
                return new Vector2(bulletSpeed, 0);
            case LEFT:
                return new Vector2(-bulletSpeed, 0);
            case UP:
                return new Vector2(0, bulletSpeed);
            case DOWN:
                return new Vector2(0, -bulletSpeed);
            default:
                return new Vector2();
        }
    }
    // Дополнительные методы, если они вам нужны, например, для проверки столкновений
}