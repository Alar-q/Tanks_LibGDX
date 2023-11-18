package com.rat6.game.game_objects.tank;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rat6.game.Assets;

public class TankBuilder {
    // массивы анимаций
    private TextureRegion[] tankR, tankL, tankU, tankD;
    private TextureRegion[] tankRShots, tankLShots, tankUShots, tankDShots;
    private float x, y;
    private float speed;
//    private BulletsHandler bulletsHandler;

//    public TankBuilder setBulletsHandler(BulletsHandler bulletsHandler) {
//        this.bulletsHandler = bulletsHandler;
//        return this;
//    }

    public TankBuilder setTankR(TextureRegion[] tankR) {
        this.tankR = tankR;
        return this;
    }

    public TankBuilder setTankL(TextureRegion[] tankL) {
        this.tankL = tankL;
        return this;
    }

    public TankBuilder setTankU(TextureRegion[] tankU) {
        this.tankU = tankU;
        return this;
    }

    public TankBuilder setTankD(TextureRegion[] tankD) {
        this.tankD = tankD;
        return this;
    }

    public TankBuilder setTankRShots(TextureRegion[] tankRShots) {
        this.tankRShots = tankRShots;
        return this;
    }

    public TankBuilder setTankLShots(TextureRegion[] tankLShots) {
        this.tankLShots = tankLShots;
        return this;
    }

    public TankBuilder setTankUShots(TextureRegion[] tankUShots) {
        this.tankUShots = tankUShots;
        return this;
    }

    public TankBuilder setTankDShots(TextureRegion[] tankDShots) {
        this.tankDShots = tankDShots;
        return this;
    }

    // Другие методы для установки других массивов анимаций

    public TankBuilder setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public TankBuilder setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public Tank build() {
//        return new Tank(tankR, tankL, tankU, tankD, tankRShots, tankLShots, tankUShots, tankDShots, x, y, speed, bulletsHandler);
        return new Tank(tankR, tankL, tankU, tankD, tankRShots, tankLShots, tankUShots, tankDShots, x, y, speed);
    }

    public TankBuilder redTank(Assets assets){
        return new TankBuilder()
                .setTankR(assets.redTankRs)
                .setTankL(assets.redTankLs)
                .setTankU(assets.redTankUs)
                .setTankD(assets.redTankDs)
                .setTankRShots(assets.redTankRShots)
                .setTankLShots(assets.redTankLShots)
                .setTankUShots(assets.redTankUShots)
                .setTankDShots(assets.redTankDShots);
    }

    public TankBuilder blueTank(Assets assets){
        return new TankBuilder()
                .setTankR(assets.blueTankRs)
                .setTankL(assets.blueTankLs)
                .setTankU(assets.blueTankUs)
                .setTankD(assets.blueTankDs)
                .setTankRShots(assets.blueTankRShots)
                .setTankLShots(assets.blueTankLShots)
                .setTankUShots(assets.blueTankUShots)
                .setTankDShots(assets.blueTankDShots);
    }
}