package com.rat6.game.stage.multiplayer;

import static com.rat6.game.MyGdxGame.WORLD_HEIGHT;
import static com.rat6.game.MyGdxGame.WORLD_WIDTH;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rat6.game.Assets;
import com.rat6.game.MyGdxGame;
import com.rat6.game.TouchInputProcessor;
import com.rat6.game.game_objects.tank.Tank;
import com.rat6.game.game_objects.tank.TankColor;
import com.rat6.game.game_objects.tank.controllers.DisplayController;
import com.rat6.game.game_objects.tank.controllers.KeyboardController;
import com.rat6.game.game_objects.tank.controllers.KeyboardControllerWASD;
import com.rat6.game.stage.Stage;
import com.rat6.game.stage.game.GameStage;
import com.rat6.game.world.StandardWorld;

import java.util.ArrayList;
import java.util.List;

public class MultiplayerStage extends GameStage {

    public MultiplayerStage(MyGdxGame myGdxGame, Assets assets, TouchInputProcessor inputProcessor){
        super(myGdxGame, assets, inputProcessor);
        Tank playerTank2 = standardWorld.createTank(TankColor.BLUE, 700, 100);
        standardWorld.addTankController(new KeyboardControllerWASD(playerTank2));
    }
}
