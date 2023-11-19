package com.rat6.game.game_objects.bullet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;
import com.rat6.game.game_objects.GameObject;
import com.rat6.game.game_objects.explosion.ExplosionsHandler;
import com.rat6.game.game_objects.ObjectDirection;
import com.rat6.game.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BulletsHandler {
    private List<Bullet> bullets;
    private World world;

    public BulletsHandler(World world, List<Bullet> bullets) {
        this.world = world;
        this.bullets = bullets;
    }

    public void update(float deltaTime) {
        Iterator<Bullet> iterator = bullets.iterator();
        while(iterator.hasNext()){
            Bullet bullet = iterator.next();
            bullet.update(deltaTime);
            if (world.isOutOfBounds(bullet)) {
                iterator.remove();
            }
        }

        List<Bullet> toRemove = new ArrayList<>();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b1 = bullets.get(i);

            // Сравнение идет между каждым элементом только один раз, без повторений
            for (int j = i + 1; j < bullets.size(); j++) {
                Bullet b2 = bullets.get(j);

                if (b1.circle.overlaps(b2.circle)) {
                    toRemove.add(b1);
                    toRemove.add(b2);
                    // Создание взрыва или другого эффекта
                    world.createExplosion(b1.circle.x, b1.circle.y, false);
                    world.createExplosion(b2.circle.x, b2.circle.y, false);
                }
            }
        }
        bullets.removeAll(toRemove);
    }


}
