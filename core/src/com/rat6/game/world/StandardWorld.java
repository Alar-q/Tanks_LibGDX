package com.rat6.game.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;
import com.rat6.game.game_objects.boulder.Boulder;
import com.rat6.game.game_objects.bullet.Bullet;
import com.rat6.game.game_objects.bullet.BulletsHandler;
import com.rat6.game.game_objects.enemies.Enemy;
import com.rat6.game.game_objects.explosion.Explosion;
import com.rat6.game.game_objects.explosion.ExplosionsHandler;
import com.rat6.game.game_objects.map.GameMap;
import com.rat6.game.game_objects.map.GrassMap;
import com.rat6.game.game_objects.tank.Tank;
import com.rat6.game.game_objects.tank.TankColor;
import com.rat6.game.game_objects.tank.KeyboardController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StandardWorld extends World {
    private KeyboardController keyboardController;

    private GameMap grassMap;
    private Enemy enemy;

    public StandardWorld(Assets assets) {
        super(assets);

        tanks = new ArrayList<>();
        Tank playerTank = createTank(TankColor.BLUE, 100, 100);
        createBullet(playerTank);
        keyboardController = new KeyboardController(playerTank);

//        enemy = new Enemy(assets);

        grassMap = new GrassMap(assets);
        createBoulder(500, 500);
    }

    @Override
    public void update(float deltaTime){
        // слушатель кнопок, изменяет состояние танка
        keyboardController.update();

        explosionsHandler.update(deltaTime);

        for(Tank tank: tanks){
            tank.update(deltaTime);
        }

        bulletsHandler.update(deltaTime);

//        enemy.update(deltaTime);

        Iterator<Boulder> iterator = boulders.iterator();
        while(iterator.hasNext()){
            Boulder boulder = iterator.next();
            boulder.update(deltaTime);
            if(boulder.isRuined()){
                iterator.remove();
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        grassMap.render(batch);
        for(Tank tank: tanks){
            tank.render(batch);
        }
        for(Bullet bullet: bullets){
            bullet.render(batch);
        }
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
//        enemy.render(batch);
        for(Boulder boulder: boulders){
            boulder.render(batch);
        }
        for (Explosion explosion : explosions) {
            explosion.render(batch);
        }
    }

}
