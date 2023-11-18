package com.rat6.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rat6.game.game_objects.tank.Tank;

public class Enemy extends Tank {
    public Enemy(TextureRegion[] r, TextureRegion[] l, TextureRegion[] u, TextureRegion[] d, TextureRegion[] rShots, TextureRegion[] lShots, TextureRegion[] uShots, TextureRegion[] dShots, float x, float y, float speed) {
        super(r, l, u, d, rShots, lShots, uShots, dShots, x, y, speed);
    }
}
