package com.rat6.game.tank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;
import com.rat6.game.MyGdxGame;
import com.rat6.game.bullet.BulletsHandler;
import com.rat6.game.tank.enums.TankDirection;
import com.rat6.game.tank.enums.TankState;

public class Tank {
    private TextureRegion[] tankR, tankL, tankU, tankD;
    protected TextureRegion[] tankRShots, tankLShots, tankUShots, tankDShots;

    private Animation<TextureRegion> animationR, animationL, animationU, animationD;

    private Vector2 center;
    private float speed;
    private Rectangle boundingBox;
    private TextureRegion frame;
    protected TankState state = TankState.IDLE;
    protected TankDirection direction = TankDirection.RIGHT;;
    private float stateTime;

    private ShootAnimator shootAnimator;
    private BulletsHandler bulletsHandler;
    private Assets assets;

    public Tank(TextureRegion[] r, TextureRegion[] l, TextureRegion[] u, TextureRegion[] d,
                TextureRegion[] rShots, TextureRegion[] lShots, TextureRegion[] uShots, TextureRegion[] dShots,
                float x, float y, float speed) {
        this(MyGdxGame.assets, r, l, u, d, rShots, lShots, uShots, dShots, x, y, speed, MyGdxGame.bulletsHandler);
    }
    protected Tank(Assets assets, TextureRegion[] r, TextureRegion[] l, TextureRegion[] u, TextureRegion[] d,
                   TextureRegion[] rShots, TextureRegion[] lShots, TextureRegion[] uShots, TextureRegion[] dShots,
                   float x, float y, float speed,
                   BulletsHandler bulletsHandler
    ) {
        this.assets = assets;
        this.shootAnimator = new ShootAnimator(this);

        this.tankR = r;
        this.tankL = l;
        this.tankU = u;
        this.tankD = d;
        this.tankRShots = rShots;
        this.tankLShots = lShots;
        this.tankUShots = uShots;
        this.tankDShots = dShots;

        // Инициализация других массивов анимаций, если есть

        if(direction == TankDirection.RIGHT){
            this.frame = r[0];
        } else if(direction == TankDirection.LEFT){
            this.frame = l[0];
        } else if(direction == TankDirection.UP){
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

        this.animationR = new Animation<TextureRegion>(0.2f, r);
        this.animationL = new Animation<TextureRegion>(0.2f, l);
        this.animationU = new Animation<TextureRegion>(0.2f, u);
        this.animationD = new Animation<TextureRegion>(0.2f, d);

        this.bulletsHandler = bulletsHandler;
//        bulletsHandler.createBullet(this.center.y, this.center.y, direction, 0);
    }

    public void moveLeft() {
        if(state == TankState.DEAD){
            return;
        }
        if(state == TankState.SHOOTING) {
            return;
        }
        center.x -= speed * Gdx.graphics.getDeltaTime();
        state = TankState.MOVING_LEFT;
        direction = TankDirection.LEFT;
    }

    public void moveRight() {
        if(state == TankState.DEAD){
            return;
        }
        if(state == TankState.SHOOTING) {
            return;
        }
        center.x += speed * Gdx.graphics.getDeltaTime();
        state = TankState.MOVING_RIGHT;
        direction = TankDirection.RIGHT;
    }

    public void moveUp() {
        if(state == TankState.DEAD){
            return;
        }
        if(state == TankState.SHOOTING) {
            return;
        }
        center.y += speed * Gdx.graphics.getDeltaTime();
        state = TankState.MOVING_UP;
        direction = TankDirection.UP;
    }

    public void moveDown() {
        if(state == TankState.DEAD){
            return;
        }
        if(state == TankState.SHOOTING) {
            return;
        }
//        System.out.println(speed + ", " + speed * Gdx.graphics.getDeltaTime());
        center.y -= speed * Gdx.graphics.getDeltaTime();
        state = TankState.MOVING_DOWN;
        direction = TankDirection.DOWN;
    }

    public void stopMoving(){
        if(state == TankState.DEAD){
            return;
        }
        if(state == TankState.SHOOTING) {
            return;
        }
        state = TankState.IDLE;
    }

    public void shoot(){
        if(state == TankState.DEAD){
            return;
        }
        if(state == TankState.SHOOTING) {
            return;
        }
        state = TankState.SHOOTING;
        shootAnimator.shoot();

        float bulletX = center.x; //(center.x - frame.getRegionWidth() / 2); // Начальная позиция X для пули
        //+ frame.getRegionHeight()/8f не имеет значения, просто для красоты, дуло расположено не ровно по центру
        float bulletY = center.y + frame.getRegionHeight()/8f; //direction==TankDirection.DOWN || direction==TankDirection.UP ? center.y : center.y + frame.getRegionHeight()/8;//(center.y - frame.getRegionHeight() / 2); // Начальная позиция Y для пули
        bulletsHandler.createBullet(bulletX, bulletY, direction, 500f);
    }

    public void update(){
        if(bulletsHandler.hit(boundingBox)){
            state = TankState.DEAD;
        }
    }

    public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        shootAnimator.update();

        switch (state) {
            case SHOOTING:
                frame = shootAnimator.getKeyFrame();
                break;
            case MOVING_LEFT:
                frame = animationL.getKeyFrame(stateTime, true);
                break;
            case MOVING_RIGHT:
                frame = animationR.getKeyFrame(stateTime, true);
                break;
            case MOVING_UP:
                frame = animationU.getKeyFrame(stateTime, true);
                break;
            case MOVING_DOWN:
                frame = animationD.getKeyFrame(stateTime, true);
                break;
            case DEAD:
                frame = destroyedFrame();
                break;
            case IDLE:
                // анимация для состояния IDLE (например, стоит на месте)
                break;
        }

        boundingBox.setWidth(frame.getRegionWidth());
        boundingBox.setHeight(frame.getRegionHeight());
        boundingBox.setPosition(center.x - frame.getRegionWidth()/2f, center.y - frame.getRegionHeight()/2f);

        if(direction == TankDirection.LEFT){
            // frame.getRegionWidth() / 6f не имеет строгого значения и нужен потому, что текстурка ассиметричная и немного смещена
            batch.draw(frame, center.x - frame.getRegionWidth() / 2f - frame.getRegionWidth() / 6f, center.y - frame.getRegionHeight() / 2f);
        }
        else if(direction == TankDirection.RIGHT){
            // frame.getRegionWidth() / 6f не имеет строгого значения и нужен потому, что текстурка ассиметричная и немного смещена
            batch.draw(frame, center.x - frame.getRegionWidth() / 2f + frame.getRegionWidth() / 6f, center.y - frame.getRegionHeight() / 2f);
        }
        else {
            batch.draw(frame, center.x - frame.getRegionWidth() / 2f, center.y - frame.getRegionHeight() / 2f);
        }
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public TextureRegion destroyedFrame(){
        switch (direction) {
            case LEFT:
                return assets.destroyedTankL;
            case RIGHT:
                return assets.destroyedTankR;
            case UP:
                return assets.destroyedTankU;
            case DOWN:
                return assets.destroyedTankD;
        }
        return null;
    }
}
