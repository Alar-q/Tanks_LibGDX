package com.rat6.game.game_objects.animators;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.game_objects.GameObject;
import com.rat6.game.game_objects.ObjectDirection;

public class StandingAnimator implements Animator {
    /*public StandingAnimator(){
        // Инициализация других массивов анимаций, если есть

        if(direction == ObjectDirection.RIGHT){
            this.frame = r[0];
        } else if(direction == ObjectDirection.LEFT){
            this.frame = l[0];
        } else if(direction == ObjectDirection.UP){
            this.frame = u[0];
        } else { // (direction == TankDirection.DOWN){}
            this.frame = d[0];
        }
        float tankWidth = frame.getRegionWidth();
        float tankHeight = frame.getRegionHeight();
//        System.out.println("TANK: width=" + tankWidth + ", height=" + tankHeight);
        this.center = new Vector2(x + tankWidth/2f, y + tankHeight/2f);

        this.speed = speed;
        this.boundingBox = new Rectangle(this.center.x, this.center.y, tankWidth, tankHeight);

        this.stateTime = 0;
    }*/


    private GameObject gameObject;
    private TextureRegion[] r, l, u, d;
    private Animation<TextureRegion> animationR, animationL, animationU, animationD;

    public StandingAnimator(GameObject gameObject){
        this.gameObject = gameObject;
    }

    public StandingAnimator setR(float frameDuration, TextureRegion... r){
        this.r = r;
        this.animationR = new Animation<TextureRegion>(frameDuration, r);
        return this;
    }
    public StandingAnimator setL(float frameDuration, TextureRegion... l){
        this.l = l;
        this.animationL = new Animation<TextureRegion>(frameDuration, l);
        return this;
    }
    public StandingAnimator setU(float frameDuration, TextureRegion... u){
        this.u = u;
        this.animationU = new Animation<TextureRegion>(frameDuration, u);
        return this;
    }
    public StandingAnimator setD(float frameDuration, TextureRegion... d){
        this.d = d;
        this.animationD = new Animation<TextureRegion>(frameDuration, d);
        return this;
    }

    @Override
    public TextureRegion getKeyFrame(float stateTime) {
        switch (gameObject.direction) {
            case LEFT:
                return animationL.getKeyFrame(stateTime, true);
            case RIGHT:
                return animationR.getKeyFrame(stateTime, true);
            case UP:
                return animationU.getKeyFrame(stateTime, true);
            case DOWN:
                return animationD.getKeyFrame(stateTime, true);
            default:
                return null;
        }
    }
}
