package com.rat6.game.game_objects.tank.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rat6.game.game_objects.tank.Tank;

public abstract class TankController {
    public Tank tank;
    public TankController(Tank tank){
        this.tank = tank;
    }
    public abstract void update(float deltaTime);
    public void render(SpriteBatch batch){}
}
