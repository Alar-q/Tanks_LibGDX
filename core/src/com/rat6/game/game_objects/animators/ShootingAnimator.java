package com.rat6.game.game_objects.animators;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rat6.game.game_objects.GameObject;
import com.rat6.game.game_objects.ObjectState;

public class ShootingAnimator implements Animator {
    protected TextureRegion[] RShots, LShots, UShots, DShots;

    private GameObject gameObject;
    private float shootTime = 0.4f;

    private float _stateTime = 0; // Время, прошедшее с момента начала выстрела
    protected Animation<TextureRegion> shootAnimation = null; // Текущая анимация выстрела

    public ShootingAnimator(GameObject gameObject, float shootTime){
        this.gameObject = gameObject;
        this.shootTime = shootTime;
    }

    public ShootingAnimator setRShots(TextureRegion... RShots){
        this.RShots = RShots;
        return this;
    }
    public ShootingAnimator setLShots(TextureRegion... LShots){
        this.LShots = LShots;
        return this;
    }
    public ShootingAnimator setUShots(TextureRegion... UShots){
        this.UShots = UShots;
        return this;
    }
    public ShootingAnimator setDShots(TextureRegion... DShots){
        this.DShots = DShots;
        return this;
    }

    public void newShoot() {
        // Добавьте здесь логику стрельбы, включая создание пули и задание ей начальной позиции и скорости.
        // В зависимости от направления стрельбы, выбирайте соответствующую анимацию.
        _stateTime = 0;
        // Определите анимацию стрельбы в зависимости от текущего состояния танка
        switch (gameObject.direction) {
            case LEFT:
                shootAnimation = new Animation<TextureRegion>(shootTime / LShots.length, LShots);
                break;
            case RIGHT:
                shootAnimation = new Animation<TextureRegion>(shootTime / LShots.length, RShots);
                break;
            case UP:
                shootAnimation = new Animation<TextureRegion>(shootTime / LShots.length, UShots);
                break;
            case DOWN:
                shootAnimation = new Animation<TextureRegion>(shootTime / LShots.length, DShots);
                break;
        }
    }

    public void update(float deltaTime) {
        // Обновление логики выстрела
        if (gameObject.state == ObjectState.SHOOTING) {
            _stateTime += deltaTime;
            if (shootAnimation.isAnimationFinished(_stateTime)) {
//                gameObject.stop(); // так нельзя делать
                gameObject.state = ObjectState.STANDING;
            }
        }
        // Добавьте здесь логику обновления танка, например, стрельбу и другие действия.
    }

    @Override
    public TextureRegion getKeyFrame(float stateTime) {
        return shootAnimation.getKeyFrame(_stateTime, false);
    }
}
