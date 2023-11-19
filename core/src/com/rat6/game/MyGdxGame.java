package com.rat6.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rat6.game.world.StandardWorld;

public class MyGdxGame extends ApplicationAdapter {
	public static final float 	WORLD_WIDTH = 960,
								WORLD_HEIGHT = 640;

	public Assets assets;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;

	private StandardWorld standardWorld;

	@Override
	public void create() {
		batch = new SpriteBatch();

		// Создание камеры и Viewport
		camera = new OrthographicCamera();
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		viewport.apply();
		camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

		assets = new Assets();

		standardWorld = new StandardWorld(assets);
	}

	@Override
	public void render() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		standardWorld.update(Gdx.graphics.getDeltaTime());

		batch.begin();
		standardWorld.render(batch);

		// Начните рисовать круг
//		Circl/e circle = standardWorld.tanks.get(0).circle; // Замените yourCircleObject на ваш объект Circle
//		batch.draw(assets.cannonball, circle.x-circle.radius, circle.y-circle.radius, circle.radius*2, circle.radius*2); // Отрисуйте круг
//
//		Circle circleB = standardWorld.boulders.get(0).circle; // Замените yourCircleObject на ваш объект Circle
//		batch.draw(assets.cannonball, circleB.x - circleB.radius, circleB.y - circleB.radius, circleB.radius*2, circleB.radius*2); // Отрисуйте круг

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
