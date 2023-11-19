package com.rat6.game.stage.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rat6.game.Assets;
import com.rat6.game.TouchInputProcessor;

public class MenuStage {
    private Assets assets;
    TouchInputProcessor inputProcessor;

    public MenuStage(Assets assets, TouchInputProcessor inputProcessor) {
        this.assets = assets;
        this.inputProcessor = inputProcessor;

    }

    public void update(float deltaTime) {
    }

    public void render(SpriteBatch batch) {
        batch.draw(assets.menu, 0, 0);
        assets.font.drawText(batch, "Some text", 0, 0);
        if (inputProcessor.isTouched()) {
            assets.font.drawText(batch, "Touch coordinates: " + inputProcessor.getTouchX() + ", " + inputProcessor.getTouchY(), 100, 150);
        }
    }
}