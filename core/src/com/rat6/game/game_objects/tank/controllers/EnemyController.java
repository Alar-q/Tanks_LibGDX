package com.rat6.game.game_objects.tank.controllers;

import com.badlogic.gdx.math.MathUtils;
import com.rat6.game.game_objects.ObjectDirection;
import com.rat6.game.game_objects.ObjectState;
import com.rat6.game.game_objects.tank.Tank;

public class EnemyController extends TankController {
    private float timeSinceLastMove = 0;
    private float timeSinceLastShoot = 0;
    private float moveInterval = 1.0f; // Интервал между изменениями направления
    private float shootInterval = 0.5f; // Интервал между выстрелами

    public EnemyController(Tank tank){
        super(tank);
    }

    public void update(float deltaTime){
        timeSinceLastMove += deltaTime;
        timeSinceLastShoot += deltaTime;

        // Изменение направления каждые moveInterval секунд
        if (tank.state == ObjectState.STANDING || timeSinceLastMove >= moveInterval) {
            changeDirection();
            timeSinceLastMove = 0;
        }

        // Стрельба каждые shootInterval секунд
        if (timeSinceLastShoot >= shootInterval) {
            if(Math.random() < 0.5d)
                tank.shoot();
            timeSinceLastShoot = 0;
        }
    }

    private void changeDirection() {
        // Выбор случайного направления для движения, исключая UNDEFINED
        int directionIndex = MathUtils.random(1, ObjectDirection.values().length - 1);
        ObjectDirection newDirection = ObjectDirection.values()[directionIndex];
        tank.move(newDirection);
    }
}
