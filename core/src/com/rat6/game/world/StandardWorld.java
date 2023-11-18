package com.rat6.game.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rat6.game.game_objects.boulder.BoulderWall;
import com.rat6.game.game_objects.bullet.BulletsHandler;
import com.rat6.game.game_objects.enemies.Enemies;
import com.rat6.game.game_objects.map.GameMap;
import com.rat6.game.game_objects.map.GrassMap;
import com.rat6.game.game_objects.tank.Tank;
import com.rat6.game.game_objects.tank.TankBuilder;
import com.rat6.game.game_objects.tank.TankController;

import static com.rat6.game.MyGdxGame.assets;

public class StandardWorld implements World {
    private Tank playerTank;
    private TankController tankController;
    public static BulletsHandler bulletsHandler;

    private GameMap grassMap;
    private BoulderWall boulder;

    private Enemies enemies;

    public StandardWorld() {
        bulletsHandler = new BulletsHandler(assets);

        playerTank = new TankBuilder()
                .blueTank(assets)
                .setPosition(100, 100) // Установите начальную позицию танка
                .setSpeed(200) // Установите скорость танка
                .build();
        tankController = new TankController(playerTank);

        enemies = new Enemies(assets);

        grassMap = new GrassMap(assets);
        boulder = new BoulderWall(assets, 0, 0);
    }

    @Override
    public void update(){
        tankController.update(); // слушатель кнопок, вызывает методы передвижения танка

        enemies.update();
        bulletsHandler.update();
        boulder.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        grassMap.render(batch);
        playerTank.render(batch);
        enemies.render(batch);
        boulder.render(batch);
        bulletsHandler.render(batch);
    }

}
