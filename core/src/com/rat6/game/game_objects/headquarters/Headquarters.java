package com.rat6.game.game_objects.headquarters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rat6.game.Assets;
import com.rat6.game.game_objects.GameObject;
import com.rat6.game.world.World;

public class Headquarters extends GameObject {

    private World world;
    private TextureRegion[] headquarters;
    private int health = 1;
    private float reduction = 0.8f;

    public Headquarters(World world, Assets assets, float x, float y) {
        this.world = world;
        this.headquarters = assets.headquarters;
        circle(
                x,
                y,
                headquarters[0].getRegionWidth() / 2f
        );

        System.out.println("x:"+x+", y:"+
                y + ", width: " +
                headquarters[0].getRegionWidth()+ ", height:"+
                headquarters[0].getRegionHeight());
    }

    @Override
    public void update(float deltaTime) {
        int hits = world.bulletHit(this);
        health-=hits;
    }

    @Override
    public void render(SpriteBatch batch) {
        if(health > 0){
            batch.draw(headquarters[0], circle.x - circle.radius, circle.y - circle.radius);
        }
        else {
            batch.draw(headquarters[1], circle.x - circle.radius, circle.y - circle.radius);
        }
    }

}
