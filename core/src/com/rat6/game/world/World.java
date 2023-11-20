package com.rat6.game.world;

import static com.rat6.game.MyGdxGame.WORLD_HEIGHT;
import static com.rat6.game.MyGdxGame.WORLD_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;
import com.rat6.game.game_objects.GameObject;
import com.rat6.game.game_objects.ObjectDirection;
import com.rat6.game.game_objects.boulder.Boulder;
import com.rat6.game.game_objects.bullet.Bullet;
import com.rat6.game.game_objects.bullet.BulletsHandler;
import com.rat6.game.game_objects.explosion.Explosion;
import com.rat6.game.game_objects.explosion.ExplosionsHandler;
import com.rat6.game.game_objects.headquarters.Headquarters;
import com.rat6.game.game_objects.tank.Tank;
import com.rat6.game.game_objects.tank.TankColor;
import com.rat6.game.game_objects.tank.controllers.TankController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class World {
    protected List<TankController> tankControllers;
    protected Assets assets;
    public List<Tank> tanks;
    public List<Bullet> bullets;
    public BulletsHandler bulletsHandler;
    public List<Boulder> boulders;
    public Headquarters headquarters;
    public List<Explosion> explosions;
    public ExplosionsHandler explosionsHandler;

    public World(Assets assets){
        this.assets = assets;
        tankControllers = new ArrayList<>();

        tanks = new ArrayList<>();

        bullets = new ArrayList<>();
        bulletsHandler = new BulletsHandler(this, assets, bullets);

        boulders = new ArrayList<>();

        explosions = new ArrayList<>();
        explosionsHandler = new ExplosionsHandler(explosions);

        headquarters = new Headquarters(this, assets, WORLD_WIDTH / 2f, 18f);
    }

    public void addTankController(TankController tankController){
        tankControllers.add(tankController);
    }

    public void createExplosion(float x, float y, boolean massive) {
//        System.out.println("createExplosion: x=" + x + ", y=" + y);
        TextureRegion[] explosionFrames = massive ? assets.explosionFrames : Arrays.copyOf(assets.explosionFrames, assets.explosionFrames.length - 2);
        Explosion explosion = new Explosion(explosionFrames, x, y, 0.1f); // 0.1f - длительность кадра в секундах
        explosions.add(explosion);
    }
    public Tank createTank(TankColor color, float x, float y){
//        System.out.println(color.toString());
        Tank newTank = new Tank(this, assets, color, x, y, 200);
        tanks.add(newTank);
        return newTank;
    }

    public Bullet createBullet(Tank tank){
        TextureRegion bulletTexture = assets.cannonball;
        Vector2 velocity = getVelocity(tank.direction, 500f);
        //+ tank.circle.radius/4f не имеет значения, просто для красоты, так как дуло расположено не ровно по центру
        Bullet newBullet = new Bullet(this, tank, bulletTexture,
                tank.circle.x,
                tank.circle.y + tank.circle.radius/4f,
                velocity.x,
                velocity.y
        );
        bullets.add(newBullet);
        return newBullet;
    }
    /*public Bullet createBullet(TextureRegion bulletTexture, float x, float y, Vector2 velocity){
        Bullet newBullet = new Bullet(this, bulletTexture,
                x,
                y,
                velocity.x,
                velocity.y
        );
        bullets.add(newBullet);
        return newBullet;
    }*/

    public Vector2 getVelocity(ObjectDirection direction, float speed) {
        switch (direction) {
            case RIGHT:
                return new Vector2(speed, 0);
            case LEFT:
                return new Vector2(-speed, 0);
            case UP:
                return new Vector2(0, speed);
            case DOWN:
                return new Vector2(0, -speed);
            default:
                return new Vector2();
        }
    }

    public Boulder createBoulder(float x, float y){
        Boulder newBoulder = new Boulder(this, assets, x, y);
        boulders.add(newBoulder);
        return newBoulder;
    }

    public List<GameObject> getInteractingObjects(){
        List<GameObject> objects = new ArrayList<>();
        objects.addAll(tanks);
        objects.addAll(boulders);
        objects.add(headquarters);
        return objects;
    }

    /**
     * Если Tank стреляет, то Tank.id передается Bullet.id
     * Так мы понимаем, что пуля именно этого танка.
     * Получается, есть огонь по своим.
     * */
    public int bulletHit(GameObject gameObject){
        if(gameObject.shape != GameObject.Shape.CIRCLE){
            // Пока сделаю только для кругов
            return 0;
        }

        List<Bullet> toRemove = new ArrayList<>();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b1 = bullets.get(i);
            if(gameObject instanceof Tank){
                if(b1.color == ((Tank)gameObject).color){
                    continue;
                }
            }
            if(b1.id == gameObject.id){
                continue;
            }
            if (b1.circle.overlaps(gameObject.circle)) {
                toRemove.add(b1);
                // Создание взрыва или другого эффекта
                createExplosion((b1.circle.x + gameObject.circle.x) / 2f, (b1.circle.y + gameObject.circle.y) / 2f, false);
                createExplosion(gameObject.circle.x, gameObject.circle.y, true);
            }
        }

        bullets.removeAll(toRemove);

        return toRemove.size();
    }

    public boolean isOutOfBounds(GameObject gameObject) {
        if(gameObject.shape != GameObject.Shape.CIRCLE){
            // Пока сделаю только для кругов
            return false;
        }
        Circle circle = gameObject.circle;
        return circle.x < circle.radius || circle.y < circle.radius || circle.x > WORLD_WIDTH - circle.radius || circle.y > WORLD_HEIGHT - circle.radius;
    }

    public boolean isOutOfBounds(Vector2 center, float radius) {
        return center.x < radius || center.y < radius || center.x > WORLD_WIDTH - radius || center.y > WORLD_HEIGHT - radius;
    }

    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch spriteBatch);
}
