package com.rat6.game.game_objects.tank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rat6.game.game_objects.bullet.BulletsHandler;

public class TankController {
    private Tank playerTank;
    public TankController(Tank playerTank){
        this.playerTank = playerTank;
    }
    public void update(BulletsHandler bulletsHandler){
        // Обработка стрельбы
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            playerTank.shoot(bulletsHandler);
        }
        // Обновляем положение танка в зависимости от нажатых клавиш
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerTank.moveLeft();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerTank.moveRight();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerTank.moveUp();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerTank.moveDown();
        }

        else {
            playerTank.stopMoving();
        }
    }
}
