package com.rat6.game.game_objects.animators;

import static com.rat6.game.game_objects.ObjectDirection.*;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rat6.game.game_objects.GameObject;

public class MovingAnimator implements Animator {
    private GameObject gameObject;
    public TextureRegion[] r, l, u, d;
    private Animation<TextureRegion> animationR, animationL, animationU, animationD;

    public MovingAnimator(GameObject gameObject){
        this.gameObject = gameObject;
    }

    public MovingAnimator setR(float frameDuration, TextureRegion... r){
        this.r = r;
        this.animationR = new Animation<TextureRegion>(frameDuration, r);
        return this;
    }
    public MovingAnimator setL(float frameDuration, TextureRegion... l){
        this.l = l;
        this.animationL = new Animation<TextureRegion>(frameDuration, l);
        return this;
    }
    public MovingAnimator setU(float frameDuration, TextureRegion... u){
        this.u = u;
        this.animationU = new Animation<TextureRegion>(frameDuration, u);
        return this;
    }
    public MovingAnimator setD(float frameDuration, TextureRegion... d){
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
