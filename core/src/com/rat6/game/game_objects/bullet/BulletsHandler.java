package com.rat6.game.game_objects.bullet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;
import com.rat6.game.explosion.ExplosionsHandler;
import com.rat6.game.game_objects.tank.enums.TankDirection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BulletsHandler {
    private List<Bullet> bullets;
    private Assets assets;
    private ExplosionsHandler explosionsHandler;

    public BulletsHandler(Assets assets) {
        this.bullets = new ArrayList<>();
        this.assets = assets;
        explosionsHandler = new ExplosionsHandler(assets);
    }

    public void createBullet(float x, float y, TankDirection direction, float velocity) {
        Vector2 v = getBulletVelocity(direction, velocity);
        createBullet(x, y, v.x, v.y);
    }

    private void createBullet(float x, float y, float velocityX, float velocityY) {
        Bullet newBullet = new Bullet(assets.cannonball, x - assets.cannonball.getRegionWidth()/2f, y - assets.cannonball.getRegionHeight()/2f, velocityX, velocityY);
        bullets.add(newBullet);
    }

    public void update() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.update();

            if (bullet.isOutOfBounds()) {
                iterator.remove();
            }
        }

        checkBulletsCollisions();

        explosionsHandler.update();
    }

    private void checkBulletsCollisions() {
        List<Bullet> toRemove = new ArrayList<>();

        for (int i = 0; i < bullets.size(); i++) {
            // Сравнение идет между каждым элементом только один раз, без повторений
            for (int j = i + 1; j < bullets.size(); j++) {
                Bullet b1 = bullets.get(i);
                Bullet b2 = bullets.get(j);

                if (areBulletsColliding(b1, b2)) {
                    toRemove.add(b1);
                    toRemove.add(b2);
                    // Создание взрыва или другого эффекта
                    explosionsHandler.createExplosion(b1.getPosition().x, b1.getPosition().y);
                    explosionsHandler.createExplosion(b2.getPosition().x, b2.getPosition().y);
                }
            }
        }

        bullets.removeAll(toRemove);
    }

    private boolean areBulletsColliding(Bullet b1, Bullet b2) {
        Circle c1 = b1.getBoundingCircle();
        Circle c2 = b2.getBoundingCircle();
        return c1.overlaps(c2);
    }

    public boolean hit(Rectangle rectangle){
//        for (int i = 0; i < bullets.size(); i++) {
        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {

//            Bullet b = bullets.get(i);
            Bullet b = it.next();
            Circle bulletCircle = b.getBoundingCircle();

            // Проверяем, пересекается ли круг пули с прямоугольником
            if (Intersector.overlaps(bulletCircle, rectangle)) {
                it.remove(); // Удаляем пулю при обнаружении столкновения
                explosionsHandler.createExplosion(rectangle.x, rectangle.y);
                return true;
            }
        }
        return false;
    }

    public boolean hit(Circle circle){
//        for (int i = 0; i < bullets.size(); i++) {
        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {

//            Bullet b = bullets.get(i);
            Bullet b = it.next();
            Circle bulletCircle = b.getBoundingCircle();

            // Проверяем, пересекается ли круг пули с прямоугольником
            if (Intersector.overlaps(bulletCircle, circle)) {
                it.remove(); // Удаляем пулю при обнаружении столкновения
                explosionsHandler.createExplosion(circle.x, circle.y);
                return true;
            }
        }
        return false;
    }

    public void render(SpriteBatch batch) {
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
        explosionsHandler.render(batch);
    }

    private Vector2 getBulletVelocity(TankDirection direction, float bulletSpeed) {
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

    // Методы для обработки взрывов или других эффектов
}
