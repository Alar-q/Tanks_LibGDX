package com.rat6.game.stage.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rat6.game.Assets;
import com.rat6.game.TouchInputProcessor;
import com.rat6.game.world.StandardWorld;

public class GameStage {
    private TouchInputProcessor inputProcessor;
    private Assets assets;
    private StandardWorld standardWorld;
    private Settings settings;

    public GameStage(Assets assets, TouchInputProcessor inputProcessor){
        this.inputProcessor = inputProcessor;
        this.assets = assets;
        standardWorld = new StandardWorld(assets);
        settings = new Settings();
    }
    public void update(float deltaTime){
        standardWorld.update(deltaTime);
    }
    public void render(SpriteBatch batch){
        standardWorld.render(batch);
        assets.font.drawText(batch, "Some text", 100, 100);

        /*
		for(Tank tank: standardWorld.tanks){
			Circle circle = tank.circle; // Замените yourCircleObject на ваш объект Circle
			batch.draw(assets.cannonball, circle.x-circle.radius, circle.y-circle.radius, circle.radius*2, circle.radius*2); // Отрисуйте круг
		}
		for(Boulder boulder: standardWorld.boulders){
			Circle circle = boulder.circle; // Замените yourCircleObject на ваш объект Circle
			batch.draw(assets.cannonball, circle.x-circle.radius, circle.y-circle.radius, circle.radius*2, circle.radius*2); // Отрисуйте круг
		}
		*/
    }
}
