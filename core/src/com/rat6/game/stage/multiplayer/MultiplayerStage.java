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
import com.rat6.game.world.StandardWorld;

import java.util.ArrayList;
import java.util.List;

public class MultiplayerStage {
    private MyGdxGame myGdxGame;
    private TouchInputProcessor inputProcessor;
    private Assets assets;
    private StandardWorld standardWorld;
    private PauseButton pauseButton;
    private boolean onPause = false;
    private List<SettingsButton> settingsButtons;

    public MultiplayerStage(MyGdxGame myGdxGame, Assets assets, TouchInputProcessor inputProcessor){
        this.myGdxGame = myGdxGame;
        this.inputProcessor = inputProcessor;
        this.assets = assets;
        standardWorld = new StandardWorld(assets);

        Tank playerTank = standardWorld.createTank(TankColor.BLUE, 100, 100);
        standardWorld.addTankController(new KeyboardController(playerTank));
        // Display Controller
        Tank playerTank2 = standardWorld.createTank(TankColor.BLUE, 500, 100);
        standardWorld.addTankController(new KeyboardControllerWASD(playerTank2));
        // Display Controller


        pauseButton = new PauseButton(assets, 0, -1)
                .setInputProcessor(inputProcessor)
                .onTouch(()->{
                    onPause = true;
                });

        settingsButtons = new ArrayList<>();
        settingsButtons.add(new SettingsButton(assets, "continue", WORLD_WIDTH/2f - 192/2f, 400).setInputProcessor(inputProcessor).onTouch(()->{onPause = false;}));
        settingsButtons.add(new SettingsButton(assets, "restart", WORLD_WIDTH/2f - 192/2f, 400 - 96*1).setInputProcessor(inputProcessor).onTouch(()->{myGdxGame.setStage(Stage.MULTI_GAME);}));
        settingsButtons.add(new SettingsButton(assets, "sound", WORLD_WIDTH/2f - 192/2f, 400 - 96*2).setInputProcessor(inputProcessor).onTouch(()->{assets.mute_toggle();}));
        settingsButtons.add(new SettingsButton(assets, "exit", WORLD_WIDTH/2f - 192/2f, 400 - 96*3).setInputProcessor(inputProcessor).onTouch(()->{myGdxGame.setStage(Stage.MENU);}));
    }
    public void update(float deltaTime){
        pauseButton.update();
        if(!onPause){
            standardWorld.update(deltaTime);
        } else {
            for(SettingsButton button: settingsButtons){
                button.update();
            }
        }
    }
    public void render(SpriteBatch batch){
        standardWorld.render(batch);
        pauseButton.render(batch);
        if(onPause){
            batch.draw(assets.blur, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
            for(SettingsButton button: settingsButtons){
                button.render(batch);
            }
        }
		/*for(Tank tank: standardWorld.tanks){
			Circle circle = tank.circle; // Замените yourCircleObject на ваш объект Circle
			batch.draw(assets.cannonball, circle.x-circle.radius, circle.y-circle.radius, circle.radius*2, circle.radius*2); // Отрисуйте круг
		}
		for(Boulder boulder: standardWorld.boulders){
			Circle circle = boulder.circle; // Замените yourCircleObject на ваш объект Circle
			batch.draw(assets.cannonball, circle.x-circle.radius, circle.y-circle.radius, circle.radius*2, circle.radius*2); // Отрисуйте круг
		}*/
    }
}
