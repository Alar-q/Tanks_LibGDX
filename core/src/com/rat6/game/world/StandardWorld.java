package com.rat6.game.world;

import static com.rat6.game.MyGdxGame.WORLD_HEIGHT;
import static com.rat6.game.MyGdxGame.WORLD_WIDTH;

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
import java.util.Random;

public class StandardWorld extends World {
    private Random random;
    private GameMap grassMap;

    public int score = 0;
    public boolean gameOver = false;

    public StandardWorld(Assets assets) {
        super(assets);
        random = new Random();
        grassMap = new GrassMap(assets);

        /*Tank enemyTank = createTank(TankColor.RED, 200, 200);
        addTankController(new EnemyController(enemyTank));

        createBoulder(50, 50);*/
        generateEnemiesAndBoulders();
        createBaseAndSurroundWithBoulders();
    }
    private void generateEnemiesAndBoulders() {
        // Параметры генерации
        int maxEnemies = 5; // Максимальное количество врагов
        int maxBoulders = 10; // Максимальное количество камней
        int mapWidth = (int) WORLD_WIDTH - 40; // Ширина карты
        int mapHeight = (int) WORLD_HEIGHT - 40; // Высота карты

        // Генерация врагов
        for (int i = 0; i < maxEnemies; i++) {
            int x = random.nextInt(mapWidth);
            int y = random.nextInt(mapHeight);
            Tank enemyTank = createTank(TankColor.RED, x, y);
            addTankController(new EnemyController(enemyTank));
        }

        // Генерация камней
        for (int i = 0; i < maxBoulders; i++) {
            int x = random.nextInt(mapWidth);
            int y = random.nextInt(mapHeight);
            createBoulder(x, y);
        }
    }
    private void createBaseAndSurroundWithBoulders() {
        // Предполагается, что у вас есть метод для создания базы
        // Предположим, что база находится в (baseX, baseY)
        int baseX = (int) headquarters.circle.x; // Пример координат базы
        int baseY = (int) headquarters.circle.y;
        int boulderSpacing = 50; // Расстояние между камнями

        // Обложим базу камнями, создавая их вокруг базы
        for (int x = baseX - boulderSpacing; x <= baseX + boulderSpacing; x += boulderSpacing) {
            for (int y = baseY - boulderSpacing; y <= baseY + boulderSpacing; y += boulderSpacing) {
                // Проверяем, чтобы не разместить камень непосредственно на базе
                if (x != baseX || y != baseY) {
                    createBoulder(x, y);
                }
            }
        }
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

        boolean hasBlue = false;
        Iterator<Tank> tanksIterator = tanks.iterator();
        while(tanksIterator.hasNext()){
            Tank tank = tanksIterator.next();
            tank.update(deltaTime);
            if(tank.isRuined()){
                if(tank.color == TankColor.RED){
                    score++;
                }
                tanksIterator.remove();
            } else {
                hasBlue = tank.color == TankColor.BLUE;
            }
        }
        gameOver = !hasBlue;

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
