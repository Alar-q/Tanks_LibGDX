package com.rat6.game.stage.game;

import static com.rat6.game.MyGdxGame.WORLD_HEIGHT;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rat6.game.Assets;
import com.rat6.game.TouchInputProcessor;

public class PauseButton {
    interface OnTouch {
        void apply();
    }
    private OnTouch onTouch;

    public Assets assets;
    public TextureRegion buttonTexture;
    public float x, y, width, height;
    public TouchInputProcessor inputProcessor;
    public boolean touched = false;
    public Rectangle rectangle;

    public PauseButton(Assets assets, float x, float y){
        this.assets = assets;
        this.buttonTexture = assets.pauseButton;
        this.width = buttonTexture.getRegionWidth()*2f;
        this.height = buttonTexture.getRegionHeight()*2f;
        this.x = x;
        this.y = WORLD_HEIGHT - height;

        touched = false;
        rectangle = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public PauseButton onTouch(OnTouch onTouch){
        this.onTouch = onTouch;
        return this;
    }

    public PauseButton setInputProcessor(TouchInputProcessor inputProcessor){
        this.inputProcessor = inputProcessor;
        return this;
    }

    public void update(){
        if(inputProcessor.pressed(rectangle) != -1){
            System.out.println("pressed");
        }
        if(touched){
            onTouch.apply();
        }
        touched = (inputProcessor.pressed(rectangle) != -1);
    }

    public void render(SpriteBatch batch){
        TextureRegion frame = buttonTexture;
        batch.draw(frame, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
