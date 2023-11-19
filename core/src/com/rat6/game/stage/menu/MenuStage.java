package com.rat6.game.stage.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.rat6.game.Assets;
import com.rat6.game.TouchInputProcessor;

import java.util.ArrayList;
import java.util.List;

public class MenuStage {
    private Assets assets;
    public List<MenuButton> menuButtons;
    public TouchInputProcessor inputProcessor;

    public MenuStage(Assets assets, TouchInputProcessor inputProcessor) {
        this.assets = assets;
        this.inputProcessor = inputProcessor;
        this.menuButtons = new ArrayList<>();
        menuButtons.add(new MenuButton(assets, "Hello", 100, 100));
    }

    public void update(float deltaTime) {
    }

    public void render(SpriteBatch batch) {
        batch.draw(assets.menu, 0, 0);
        assets.font.drawText(batch, "Some text", 0, 0);
        if (inputProcessor.isTouched()) {
            assets.font.drawText(batch, "Touch coordinates: " + inputProcessor.getTouchX() + ", " + inputProcessor.getTouchY(), 100, 150);
        }
        for(MenuButton button: menuButtons){
            button.render(batch);
        }
    }
}