package com.rat6.game.stage.game;

import static com.rat6.game.MyGdxGame.WORLD_HEIGHT;
import static com.rat6.game.MyGdxGame.WORLD_WIDTH;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.rat6.game.Assets;
import com.rat6.game.MyGdxGame;
import com.rat6.game.TouchInputProcessor;
import com.rat6.game.game_objects.boulder.Boulder;
import com.rat6.game.game_objects.tank.Tank;
import com.rat6.game.game_objects.tank.TankColor;
import com.rat6.game.game_objects.tank.controllers.DisplayController;
import com.rat6.game.game_objects.tank.controllers.KeyboardController;
import com.rat6.game.world.StandardWorld;

public class GameStage {
    private MyGdxGame myGdxGame;
    private TouchInputProcessor inputProcessor;
    private Assets assets;
    private StandardWorld standardWorld;
    private Settings settings;

    public GameStage(MyGdxGame myGdxGame, Assets assets, TouchInputProcessor inputProcessor){
        this.myGdxGame = myGdxGame;
        this.inputProcessor = inputProcessor;
        this.assets = assets;
        standardWorld = new StandardWorld(assets);
        settings = new Settings();

        Tank playerTank = standardWorld.createTank(TankColor.BLUE, 100, 100);
        standardWorld.addTankController(new KeyboardController(playerTank));
        // Display Controller
        if(!Gdx.app.getType().equals(Application.ApplicationType.Desktop)){
            standardWorld.addTankController(new DisplayController(playerTank)
                    .setInputProcessor(inputProcessor)
                    .setAssets(assets)
                    .setWorldSize(WORLD_WIDTH, WORLD_HEIGHT)
                    .build()
            );
        }
    }
    public void update(float deltaTime){
        standardWorld.update(deltaTime);
    }
    public void render(SpriteBatch batch){
        standardWorld.render(batch);

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
