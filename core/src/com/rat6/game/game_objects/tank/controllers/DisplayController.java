package com.rat6.game.game_objects.tank.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rat6.game.Assets;
import com.rat6.game.TouchInputProcessor;
import com.rat6.game.game_objects.ObjectDirection;
import com.rat6.game.game_objects.tank.Tank;

public class DisplayController extends TankController {
    private float WORLD_WIDTH, WORLD_HEIGHT;
    private TouchInputProcessor inputProcessor;
    private Assets assets;
    private TextureRegion controller, controllerShotButton, controllerPauseButton;
    Rectangle   rightButton,
                leftButton,
                downButton,
                upButton,
                shotButton;
    float   shotButtonLowerLeftX,
            shotButtonLowerLeftY,
            width,
            height;

    public DisplayController(Tank tank) {
        super(tank);
    }

    public DisplayController setWorldSize(float width, float height){
        this.WORLD_WIDTH = width;
        this.WORLD_HEIGHT = height;
        return this;
    }
    public DisplayController setInputProcessor(TouchInputProcessor inputProcessor){
        this.inputProcessor = inputProcessor;
        return this;
    }
    public DisplayController setAssets(Assets assets){
        this.assets = assets;

        return this;
    }

    public DisplayController build(){
        this.controller = assets.controller;
        this.controllerShotButton = assets.controllerShotButton;
        this.controllerPauseButton = assets.controllerPauseButton;

        width = controller.getRegionWidth();
        height = controller.getRegionHeight();
        leftButton = new Rectangle(0, height, width, height);
        rightButton = new Rectangle(width*2f, height, width, height);

        downButton = new Rectangle(width, 0, width, height);
        upButton = new Rectangle(width, height*2, width, height);

        shotButtonLowerLeftX = WORLD_WIDTH - width * 2f;
        shotButtonLowerLeftY = height;
        shotButton = new Rectangle(shotButtonLowerLeftX, shotButtonLowerLeftY, width, height);

        return this;
    }
    @Override
    public void update(float deltaTime) {
        if(deltaTime == 0){
            return;
        }

        // Обработка стрельбы
        if (inputProcessor.pressed(shotButton) != -1) {
            tank.shoot();
        }

        // Обновляем положение танка в зависимости от касаний
        if (inputProcessor.pressed(leftButton) != -1) {
            tank.move(ObjectDirection.LEFT);
        }
        else if (inputProcessor.pressed(rightButton) != -1) {
            tank.move(ObjectDirection.RIGHT);
        }
        else if (inputProcessor.pressed(upButton) != -1) {
            tank.move(ObjectDirection.UP);
        }
        else if (inputProcessor.pressed(downButton) != -1) {
            tank.move(ObjectDirection.DOWN);
        }
        else {
            tank.stop();
        }
    }


    @Override
    public void render(SpriteBatch batch){
        batch.draw(controller, 0, 0, width*3f, height*3f);
        batch.draw(controllerShotButton, shotButtonLowerLeftX, shotButtonLowerLeftY, width, height);
    }
}
