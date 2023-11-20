package com.rat6.game.stage.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rat6.game.Assets;
import com.rat6.game.TouchInputProcessor;

public class SettingsButton {
    public interface OnTouch {
        void apply();
    }
    private OnTouch onTouch;

    public Assets assets;
    public TextureRegion[] menuButtons;
    public String text;
    public float x, y, width, height;
    public TouchInputProcessor inputProcessor;
    public int touched = -1;
    public Rectangle rectangle;

    public SettingsButton(Assets assets, String text, float x, float y){
        this.assets = assets;
        this.menuButtons = assets.menuButtons;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = menuButtons[0].getRegionWidth();
        this.height = menuButtons[0].getRegionHeight();
        rectangle = new Rectangle(x+16, y+12, width-32, height-24);
    }

    public SettingsButton onTouch(OnTouch onTouch){
        this.onTouch = onTouch;
        return this;
    }

    public SettingsButton setInputProcessor(TouchInputProcessor inputProcessor){
        this.inputProcessor = inputProcessor;
        return this;
    }

    public void update(){
        if(inputProcessor == null){
            System.out.println("Define TouchInputProcessor");
            return;
        }
        int id = inputProcessor.pressed(rectangle);

        if(touched != -1 && id == -1){
            onTouch.apply();
        }

        touched = id;
    }

    public void render(SpriteBatch batch){
        TextureRegion frame = menuButtons[touched!=-1?1:0];
        batch.draw(frame, x, y);
        assets.font.drawText(batch, text, rectangle);
    }
}
