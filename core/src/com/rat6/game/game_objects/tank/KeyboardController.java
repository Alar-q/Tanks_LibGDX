package com.rat6.game.game_objects.tank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rat6.game.game_objects.ObjectDirection;

public class KeyboardController {
    private Tank playerTank;
    public KeyboardController(Tank playerTank){
        this.playerTank = playerTank;
    }
    public void update(float deltaTime){
        if(deltaTime == 0){
            return;
        }
        // Обработка стрельбы
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            playerTank.shoot();
        }
        // Обновляем положение танка в зависимости от нажатых клавиш
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerTank.move(ObjectDirection.LEFT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerTank.move(ObjectDirection.RIGHT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerTank.move(ObjectDirection.UP);

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerTank.move(ObjectDirection.DOWN);
        }
        else {
            playerTank.stop();
        }
    }
}
