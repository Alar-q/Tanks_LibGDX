package com.rat6.game.stage.settings;

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
import com.rat6.game.stage.Stage;
import com.rat6.game.stage.game.PauseButton;
import com.rat6.game.stage.game.SettingsButton;
import com.rat6.game.world.StandardWorld;

import java.util.ArrayList;
import java.util.List;

public class SettingsStage {
    private MyGdxGame myGdxGame;
    private TouchInputProcessor inputProcessor;
    private Assets assets;
    private List<SettingsButton> settingsButtons;

    public SettingsStage(MyGdxGame myGdxGame, Assets assets, TouchInputProcessor inputProcessor){
        this.myGdxGame = myGdxGame;
        this.inputProcessor = inputProcessor;
        this.assets = assets;

        settingsButtons = new ArrayList<>();
        settingsButtons.add(new SettingsButton(assets, "sound", WORLD_WIDTH/2f - 192/2f, 400 - 96*0).setInputProcessor(inputProcessor).onTouch(()->{assets.mute_toggle();}));
        settingsButtons.add(new SettingsButton(assets, "exit", WORLD_WIDTH/2f - 192/2f, 400 - 96*1).setInputProcessor(inputProcessor).onTouch(()->{myGdxGame.setStage(Stage.MENU);}));
    }
    public void update(float deltaTime){
        for(SettingsButton button: settingsButtons){
            button.update();
        }
    }
    public void render(SpriteBatch batch){
        batch.draw(assets.blur, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        for(SettingsButton button: settingsButtons){
            button.render(batch);
        }
    }
}
