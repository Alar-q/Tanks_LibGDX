package com.rat6.game.stage.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rat6.game.Assets;
import com.rat6.game.TouchInputProcessor;

public class MenuButton {
    public Assets assets;
    public TextureRegion[] menuButtons;
    public String text;
    public float x, y;
    public TouchInputProcessor inputProcessor;

    public MenuButton(Assets assets, String text, float x, float y){
        this.assets = assets;
        this.menuButtons = assets.menuButtons;
        this.text = text;
        this.x = x;
        this.y = y;
    }
    public MenuButton setInputProcessor(TouchInputProcessor inputProcessor){
        this.inputProcessor = inputProcessor;
        return this;
    }

    public void update(){

    }

    public void render(SpriteBatch batch){
        batch.draw(menuButtons[1], x, y);
        assets.font.drawText(batch, text, x, y);
    }
}
