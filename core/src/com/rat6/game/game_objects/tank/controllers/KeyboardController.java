package com.rat6.game.game_objects.tank.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rat6.game.game_objects.ObjectDirection;
import com.rat6.game.game_objects.tank.Tank;

public class KeyboardController extends TankController {
    public KeyboardController(Tank playerTank){
        super(playerTank);
    }
    @Override
    public void update(float deltaTime){
        if(deltaTime == 0){
            return;
        }
        // Обработка стрельбы
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            tank.shoot();
        }
        // Обновляем положение танка в зависимости от нажатых клавиш
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            tank.move(ObjectDirection.LEFT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            tank.move(ObjectDirection.RIGHT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            tank.move(ObjectDirection.UP);

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            tank.move(ObjectDirection.DOWN);
        }
        else {
            tank.stop();
        }
    }
}
