package com.rat6.game.boulder;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rat6.game.Assets;
import com.rat6.game.MyGdxGame;
import com.rat6.game.bullet.BulletsHandler;

public class BoulderWall {
    private TextureRegion[] boulders;
    private int destructionPhase;
    private Rectangle boundingBox;
    private BulletsHandler bulletsHandler;

    public BoulderWall(Assets assets, float x, float y) {
        this.destructionPhase = 0;
        this.boulders = assets.boulders;
        this.boundingBox = new Rectangle(x, y, boulders[destructionPhase].getRegionWidth(), boulders[destructionPhase].getRegionHeight());
        this.bulletsHandler = MyGdxGame.bulletsHandler;
    }

    public void update() {
        // Здесь вы можете добавить логику для булдера, если это необходимо
        // Например, проверку на столкновение с игроком или другими объектами
        if(bulletsHandler.hit(boundingBox)){
            destructionPhase++;
        }
    }

    public void render(SpriteBatch batch) {
        if(isRuined()){
            return;
        }
        batch.draw(boulders[destructionPhase], boundingBox.x, boundingBox.y);
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public boolean isRuined(){
        return destructionPhase >= boulders.length;
    }
}
