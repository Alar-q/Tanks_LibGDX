package com.rat6.game.game_objects.tank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rat6.game.game_objects.tank.enums.TankState;

public class ShootAnimator {
    private Tank tank;
    private float shootTime = 0.4f;

    private float stateTime = 0; // Время, прошедшее с момента начала выстрела
    protected Animation<TextureRegion> shootAnimation = null; // Текущая анимация выстрела

    public ShootAnimator(Tank tank){
        this.tank = tank;
    }

    public void shoot() {
        // Добавьте здесь логику стрельбы, включая создание пули и задание ей начальной позиции и скорости.
        // В зависимости от направления стрельбы, выбирайте соответствующую анимацию.
        stateTime = 0;
        // Определите анимацию стрельбы в зависимости от текущего состояния танка
        switch (tank.direction) {
            case LEFT:
                shootAnimation = new Animation<TextureRegion>(shootTime/tank.tankLShots.length, tank.tankLShots);
                break;
            case RIGHT:
                shootAnimation = new Animation<TextureRegion>(shootTime/tank.tankLShots.length, tank.tankRShots);
                break;
            case UP:
                shootAnimation = new Animation<TextureRegion>(shootTime/tank.tankLShots.length, tank.tankUShots);
                break;
            case DOWN:
                shootAnimation = new Animation<TextureRegion>(shootTime/tank.tankLShots.length, tank.tankDShots);
                break;
        }
    }

    public TextureRegion getKeyFrame(){
        return shootAnimation.getKeyFrame(stateTime, false);
    }


    public void update() {
        // Обновление логики выстрела
        if (tank.state == TankState.SHOOTING) {
            stateTime += Gdx.graphics.getDeltaTime();
            if (shootAnimation.isAnimationFinished(stateTime)) {
                tank.state = TankState.IDLE;
            }
        }
        // Добавьте здесь логику обновления танка, например, стрельбу и другие действия.
    }

}
