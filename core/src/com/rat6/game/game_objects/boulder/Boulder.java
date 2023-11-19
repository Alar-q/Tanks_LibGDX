package com.rat6.game.game_objects.boulder;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rat6.game.Assets;
import com.rat6.game.game_objects.GameObject;
import com.rat6.game.game_objects.ObjectState;
import com.rat6.game.game_objects.bullet.Bullet;
import com.rat6.game.game_objects.bullet.BulletsHandler;
import com.rat6.game.world.World;

public class Boulder extends GameObject {

    private World world;
    private TextureRegion[] boulders;
    private int destructionPhase;
    private float reduction = 0.8f;
    public Boulder(World world, Assets assets, float x, float y) {
        this.world = world;
        this.destructionPhase = 0;
        this.boulders = assets.boulders;
        circle(
                x,
                y,
                boulders[destructionPhase].getRegionWidth() / 2f
        );

        /*System.out.println("x:"+x+", y:"+
                y + ", width: " +
                boulders[destructionPhase].getRegionWidth()+ ", height:"+
                boulders[destructionPhase].getRegionHeight());*/
    }

    @Override
    public void update(float deltaTime) {
        int hits = world.bulletHit(this);
       destructionPhase+=hits;
    }

    @Override
    public void render(SpriteBatch batch) {
        if(isRuined()){
            return;
        }
        batch.draw(boulders[destructionPhase], circle.x - circle.radius, circle.y - circle.radius);
    }

    public boolean isRuined(){
        return destructionPhase >= boulders.length;
    }
}
