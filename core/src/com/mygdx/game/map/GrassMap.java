package com.mygdx.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Assets;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

public class GrassMap implements GameMap {
    private Assets assets;
    private TextureRegion[][] map;
    private int tileWidth, tileHeight;

    public GrassMap(Assets assets) {
        this.assets = assets;
        this.map = generateMap();
        this.tileWidth = assets.turf.getRegionWidth();
        this.tileHeight = assets.turf.getRegionHeight();
    }

    /**
     * [min, max)
     * */
    public int rand(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private TextureRegion[][] generateMap(){
        this.tileWidth = assets.turf.getRegionWidth();
        this.tileHeight = assets.turf.getRegionHeight();
        TextureRegion[][] _map = new TextureRegion[(int)WORLD_WIDTH/tileWidth][(int)WORLD_HEIGHT/tileHeight];
        for (int x = 0; x < _map.length; x++) {
            for (int  y = 0; y < _map[0].length; y++) {
                TextureRegion grassTexture = assets.shabby_turfs[rand(0, 6)];
                _map[x][y] = grassTexture;
            }
        }
        return _map;
    }

    @Override
    public void render(SpriteBatch batch) {
        for (int x = 0; x < map.length; x++) {
            for (int  y = 0; y < map[0].length; y++) {
                batch.draw(map[x][y], x * tileWidth, y * tileHeight);
            }
        }
    }
}
