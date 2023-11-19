package com.rat6.game.stage.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rat6.game.Assets;
import com.rat6.game.TouchInputProcessor;

public class MenuButton {
    interface OnTouch {
        void apply();
    }
    private OnTouch onTouch;

    public Assets assets;
    public TextureRegion[] menuButtons;
    public String text;
    public float x, y, width, height;
    public TouchInputProcessor inputProcessor;
    public boolean touched = false;
    public Rectangle rectangle;

    public MenuButton(Assets assets, String text, float x, float y){
        this.assets = assets;
        this.menuButtons = assets.menuButtons;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = menuButtons[0].getRegionWidth();
        this.height = menuButtons[0].getRegionHeight();
        rectangle = new Rectangle(x+16, y+12, width-32, height-24);
    }

    public MenuButton onTouch(OnTouch onTouch){
        this.onTouch = onTouch;
        return this;
    }

    public MenuButton setInputProcessor(TouchInputProcessor inputProcessor){
        this.inputProcessor = inputProcessor;
        return this;
    }

    public void update(){
        if(inputProcessor == null){
            System.out.println("Define TouchInputProcessor");
            return;
        }
        if(touched){
            onTouch.apply();
        }
        touched = (inputProcessor.pressed(rectangle) != -1);
    }

    public void render(SpriteBatch batch){
        TextureRegion frame = menuButtons[touched?1:0];
        batch.draw(frame, x, y);
        assets.font.drawText(batch, text, x + 8 + width/text.length(), y + height/2.5f);
    }
}
