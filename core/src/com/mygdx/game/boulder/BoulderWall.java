package com.mygdx.game.boulder;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Assets;

public class BoulderWall {
    private TextureRegion[] boulders;
    private int destructionPhase;
    private Rectangle boundingBox;

    public BoulderWall(Assets assets, float x, float y) {
        this.destructionPhase = 0;
        this.boulders = assets.boulders;
        this.boundingBox = new Rectangle(x, y, boulders[destructionPhase].getRegionWidth(), boulders[destructionPhase].getRegionHeight());
    }

    public void update() {
        // Здесь вы можете добавить логику для булдера, если это необходимо
        // Например, проверку на столкновение с игроком или другими объектами
    }

    public void render(SpriteBatch batch) {
        if(destructionPhase >= boulders.length){
            return;
        }
        batch.draw(boulders[destructionPhase], boundingBox.x, boundingBox.y);
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
