package com.rat6.game.game_objects.tank;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rat6.game.Assets;
import com.rat6.game.game_objects.GameObject;
import com.rat6.game.game_objects.animators.DestroyedAnimator;
import com.rat6.game.game_objects.animators.MovingAnimator;
import com.rat6.game.game_objects.animators.ShootingAnimator;

public class TankAnimator {

    private GameObject gameObject;
    private TextureRegion frame;
    private float stateTime;
    public MovingAnimator movingAnimator;
    public ShootingAnimator shootingAnimator;
    public DestroyedAnimator destroyedAnimator;

    public float WIDTH, HEIGHT, RADIUS;

    public TankAnimator(GameObject gameObject, Assets assets, TankColor color){

        this.gameObject = gameObject;

        if(color == TankColor.RED){
            movingAnimator = new MovingAnimator(gameObject)
                    .setR(0.2f, assets.redTankRs)
                    .setL(0.2f, assets.redTankLs)
                    .setU(0.2f, assets.redTankUs)
                    .setD(0.2f, assets.redTankDs);

            shootingAnimator = new ShootingAnimator(gameObject, 0.4f)
                    .setRShots(assets.redTankRShots)
                    .setLShots(assets.redTankLShots)
                    .setUShots(assets.redTankUShots)
                    .setDShots(assets.redTankDShots);

        }

        else {
            movingAnimator = new MovingAnimator(gameObject)
                    .setR(0.2f, assets.blueTankRs)
                    .setL(0.2f, assets.blueTankLs)
                    .setU(0.2f, assets.blueTankUs)
                    .setD(0.2f, assets.blueTankDs);

            shootingAnimator = new ShootingAnimator(gameObject, 0.4f)
                    .setRShots(assets.blueTankRShots)
                    .setLShots(assets.blueTankLShots)
                    .setUShots(assets.blueTankUShots)
                    .setDShots(assets.blueTankDShots);
        }

        // There's something about everyone ending up the same.
        destroyedAnimator = new DestroyedAnimator(gameObject)
                .setR(0.2f, assets.destroyedTankR)
                .setL(0.2f, assets.destroyedTankL)
                .setU(0.2f, assets.destroyedTankU)
                .setD(0.2f, assets.destroyedTankD);

        switch (gameObject.direction){
            case RIGHT:
                frame = movingAnimator.r[0];
                break;
            case LEFT:
                frame = movingAnimator.l[0];
                break;
            case UP:
                frame = movingAnimator.u[0];
                break;
            case DOWN:
                frame = movingAnimator.d[0];
                break;
        }

        WIDTH = frame.getRegionWidth();
        HEIGHT = frame.getRegionHeight();
        RADIUS = Math.min(WIDTH, HEIGHT) / 3f; // почему не 2 лол, потому что коллизии
//        System.out.println("RADIUS: " + RADIUS);
    }


    public void update(float deltaTime){
        stateTime += deltaTime;
        shootingAnimator.update(deltaTime);
    }


    public TextureRegion getKeyFrame(){
        switch (gameObject.state) {
            case SHOOTING:
                frame = shootingAnimator.getKeyFrame(stateTime);
                break;
            case MOVING:
                frame = movingAnimator.getKeyFrame(stateTime);
                break;
            case DEAD:
                frame = destroyedAnimator.getKeyFrame(stateTime);
                break;
            case STANDING:
                // анимация для STAND
                break;
        }
        return frame;
    }
}
