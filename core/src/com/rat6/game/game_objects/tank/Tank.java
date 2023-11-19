package com.rat6.game.game_objects.tank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;
import com.rat6.game.game_objects.bullet.Bullet;
import com.rat6.game.game_objects.bullet.BulletsHandler;
import com.rat6.game.game_objects.GameObject;
import com.rat6.game.game_objects.ObjectDirection;
import com.rat6.game.game_objects.ObjectState;
import com.rat6.game.world.World;

import java.util.List;
import java.util.UUID;

public class Tank extends GameObject {
    private Assets assets;
    private World world;
    private TankAnimator tankAnimator;

    /**
     * @x x-axis of center
     * @y y-axis of center
     * */
    public Tank(World world, Assets assets, TankColor color, float x, float y, float speed) {
        direction = ObjectDirection.RIGHT;

        this.assets = assets;
        this.world = world;
        this.speed = speed;
        this.velocity = world.getVelocity(direction, speed);

        this.tankAnimator = new TankAnimator(this, assets, color);

        circle(x, y, tankAnimator.RADIUS);
    }

    public void shoot(){
        if(state == ObjectState.DEAD){
            return;
        }
        if(state == ObjectState.SHOOTING) {
            return;
        }
        state = ObjectState.SHOOTING;
        tankAnimator.shootingAnimator.newShoot();

        world.createBullet(this);
    }

    @Override
    public void update(float deltaTime){
        List<GameObject> objects = world.getInteractingObjects();


        // Update position
        // Обновляем позицию, только если танк не уперся во что-нибудь
        boolean isBumped = false;
        if(state == ObjectState.MOVING){
            Vector2 nextPosition = nextPosition(deltaTime);
            for(GameObject gameObject: objects){
                if(id == gameObject.id){
//                System.out.println(id + ", " + gameObject.id);
                    continue;
                }
                if(gameObject.overlap(new Circle(nextPosition.x, nextPosition.y, circle.radius))){
                    isBumped = true;
                    break;
                }
            }
            if(world.isOutOfBounds(nextPosition, circle.radius)){
                isBumped = true;
            }
            if(!isBumped){
                circle.x = nextPosition.x;
                circle.y = nextPosition.y;
            }
        }

        // Bullet hits
        int hits = world.bulletHit(this);
        if(hits > 0){
            state = ObjectState.DEAD;
            return;
        }

        tankAnimator.update(deltaTime);
    }

    public Vector2 nextPosition(float deltaTime){
        velocity = world.getVelocity(direction, speed);
        return new Vector2(circle.x + velocity.x * deltaTime, circle.y + velocity.y * deltaTime);
    }
    @Override
    public void render(SpriteBatch batch) {
        TextureRegion frame = tankAnimator.getKeyFrame();

        if(direction == ObjectDirection.LEFT){
            // frame.getRegionWidth() / 6f не имеет строгого значения и нужен потому, что текстурка ассиметричная и немного смещена
            batch.draw(frame, circle.x - frame.getRegionWidth() / 2f - frame.getRegionWidth() / 6f, circle.y - frame.getRegionHeight() / 2f);
        }
        else if(direction == ObjectDirection.RIGHT){
            // frame.getRegionWidth() / 6f не имеет строгого значения и нужен потому, что текстурка ассиметричная и немного смещена
            batch.draw(frame, circle.x - frame.getRegionWidth() / 2f + frame.getRegionWidth() / 6f, circle.y - frame.getRegionHeight() / 2f);
        }
        else {
            batch.draw(frame, circle.x - frame.getRegionWidth() / 2f, circle.y - frame.getRegionHeight() / 2f);
        }
    }


}
