package com.rat6.game.game_objects.tank.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rat6.game.game_objects.ObjectDirection;
import com.rat6.game.game_objects.tank.Tank;

public class KeyboardControllerWASD extends TankController {
    public KeyboardControllerWASD(Tank playerTank) {
        super(playerTank);
    }

    @Override
    public void update(float deltaTime) {
        if (deltaTime == 0) {
            return;
        }

        // Обработка стрельбы
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            tank.shoot();
        }

        // Обновляем положение танка в зависимости от нажатых клавиш WASD
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            tank.move(ObjectDirection.LEFT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            tank.move(ObjectDirection.RIGHT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            tank.move(ObjectDirection.UP);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            tank.move(ObjectDirection.DOWN);
        }
        else {
            tank.stop();
        }
    }
}
