package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.boulder.BoulderWall;
import com.mygdx.game.bullet.BulletsHandler;
import com.mygdx.game.map.GameMap;
import com.mygdx.game.map.GrassMap;
import com.mygdx.game.tank.Tank;
import com.mygdx.game.tank.TankBuilder;
import com.mygdx.game.tank.TankController;

public class MyGdxGame extends ApplicationAdapter {
	public static final float WORLD_WIDTH = 960;
	public static final float WORLD_HEIGHT = 640;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;

	private Assets assets;
	private Tank playerTank;
	private TankController tankController;
	private BulletsHandler bulletsHandler;

	private GameMap grassMap;
	private BoulderWall boulder;

	@Override
	public void create() {
		batch = new SpriteBatch();

		// Создание камеры и Viewport
		camera = new OrthographicCamera();
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		viewport.apply();
		camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

		assets = new Assets();
		bulletsHandler = new BulletsHandler(assets);

		playerTank = new TankBuilder()
				.redTank(assets)
				.setBulletsHandler(bulletsHandler)
				.setPosition(100, 100) // Установите начальную позицию танка
				.setSpeed(200) // Установите скорость танка
				.build();
		tankController = new TankController(playerTank);

		grassMap = new GrassMap(assets);
		boulder = new BoulderWall(assets, 0, 0);
	}

	@Override
	public void render() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		bulletsHandler.update();
		tankController.update(); // слушатель кнопок, вызывает методы передвижения танка

		batch.begin();
		grassMap.render(batch);
		bulletsHandler.render(batch);
		playerTank.render(batch);
		boulder.render(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.dispose();
	}
}
