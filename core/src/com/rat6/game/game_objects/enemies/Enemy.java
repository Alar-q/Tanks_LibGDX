package com.rat6.game.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rat6.game.Assets;
import com.rat6.game.MyGdxGame;
import com.rat6.game.game_objects.bullet.BulletsHandler;
import com.rat6.game.game_objects.tank.Tank;
import com.rat6.game.game_objects.tank.TankColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
   /* private List<Tank> enemyTanks;
    private Random random;
    private float movementInterval;

    private Assets assets;

    public Enemy(Assets assets) {
        this.assets = assets;
        this.enemyTanks = new ArrayList<>();
        this.random = new Random();
        this.movementInterval = 1.0f; // Интервал передвижения врагов в секундах
        createEnemies(); // Создание врагов

    }

    private void createEnemies() {
        // Определение области появления врагов
        float spawnMinX = 0;
        float spawnMaxX = MyGdxGame.WORLD_WIDTH;
        float spawnMinY = MyGdxGame.WORLD_HEIGHT * 0.75f; // Начало верхней четверти карты
        float spawnMaxY = MyGdxGame.WORLD_HEIGHT;

        int numberOfEnemies = 5; // Количество врагов, которое вы хотите создать

        for (int i = 0; i < numberOfEnemies; i++) {
            float x = random.nextFloat() * (spawnMaxX - spawnMinX) + spawnMinX;
            float y = random.nextFloat() * (spawnMaxY - spawnMinY) + spawnMinY;

            // Создание вражеского танка в случайной позиции
            // Убедитесь, что у вас есть все необходимые текстуры и параметры для создания врага
            Tank enemy = new Tank(assets, TankColor.RED, x, y, 200);
            enemyTanks.add(enemy);
        }
    }

    public void update(BulletsHandler bulletsHandler) {
        for (Tank enemy : enemyTanks) {
            if (random.nextFloat() < 0.1) { // Случайная выборка для действий врагов
                randomizeEnemyMovement(enemy);
            }
            enemy.update(); // Обновление каждого танка
        }
    }

    private void randomizeEnemyMovement(Tank enemy) {
        int directionIndex = random.nextInt(4);
        switch (directionIndex) {
            case 0:
                enemy.moveUp();
                break;
            case 1:
                enemy.moveDown();
                break;
            case 2:
                enemy.moveLeft();
                break;
            case 3:
                enemy.moveRight();
                break;
        }
    }

    public void render(SpriteBatch batch) {
        for (Tank enemy : enemyTanks) {
            enemy.render(batch);
        }
    }*/
}