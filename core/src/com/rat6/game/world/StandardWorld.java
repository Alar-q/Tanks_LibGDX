package com.rat6.game.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rat6.game.Assets;
import com.rat6.game.game_objects.boulder.Boulder;
import com.rat6.game.game_objects.bullet.Bullet;
import com.rat6.game.game_objects.explosion.Explosion;
import com.rat6.game.game_objects.map.GameMap;
import com.rat6.game.game_objects.map.GrassMap;
import com.rat6.game.game_objects.tank.controllers.EnemyController;
import com.rat6.game.game_objects.tank.Tank;
import com.rat6.game.game_objects.tank.TankColor;
import com.rat6.game.game_objects.tank.controllers.KeyboardController;
import com.rat6.game.game_objects.tank.controllers.TankController;

import java.util.ArrayList;
import java.util.Iterator;

public class StandardWorld extends World {
    private GameMap grassMap;

    public StandardWorld(Assets assets) {
        super(assets);

        tanks = new ArrayList<>();

        Tank enemyTank = createTank(TankColor.RED, 200, 200);
        addTankController(new EnemyController(enemyTank));

        grassMap = new GrassMap(assets);
        createBoulder(50, 50);
    }

    @Override
    public void update(float deltaTime){
        // слушатель кнопок, изменяет состояние танка

        Iterator<TankController> tankControllersIterator = tankControllers.iterator();
        while(tankControllersIterator.hasNext()){
            TankController tankController = tankControllersIterator.next();
            tankController.update(deltaTime);
            if(tankController.tank.isRuined()){
                tankControllersIterator.remove();
            }
        }

        bulletsHandler.update(deltaTime);

        Iterator<Tank> tanksIterator = tanks.iterator();
        while(tanksIterator.hasNext()){
            Tank tank = tanksIterator.next();
            tank.update(deltaTime);
            if(tank.isRuined()){
                tanksIterator.remove();
            }
        }

        headquarters.update(deltaTime);

        Iterator<Boulder> iterator = boulders.iterator();
        while(iterator.hasNext()){
            Boulder boulder = iterator.next();
            boulder.update(deltaTime);
            if(boulder.isRuined()){
                iterator.remove();
            }
        }

        explosionsHandler.update(deltaTime);
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
        headquarters.render(batch);
        for (Explosion explosion : explosions) {
            explosion.render(batch);
        }

        for(TankController tankController: tankControllers){
            tankController.render(batch);
        }
    }

}
