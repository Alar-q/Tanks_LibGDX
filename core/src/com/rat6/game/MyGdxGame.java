package com.rat6.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rat6.game.stage.Stage;
import com.rat6.game.stage.game.GameStage;
import com.rat6.game.stage.menu.MenuStage;
import com.rat6.game.world.StandardWorld;


public class MyGdxGame extends ApplicationAdapter {

	public static final float 	WORLD_WIDTH = 960,
								WORLD_HEIGHT = 640;

	public Assets assets;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;

	private Stage stage;
	private GameStage gameStage;
	private MenuStage menuStage;
	public TouchInputProcessor inputProcessor;

	@Override
	public void create() {
		batch = new SpriteBatch();

		// Создание камеры и Viewport
		camera = new OrthographicCamera();
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		viewport.apply();
		camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);

		inputProcessor = new TouchInputProcessor(viewport);
		Gdx.input.setInputProcessor(inputProcessor);

		assets = new Assets();

		stage = Stage.MENU;
		gameStage = new GameStage(assets, inputProcessor);
		menuStage = new MenuStage(assets, inputProcessor);

		Application.ApplicationType appType = Gdx.app.getType();
		System.out.println(appType);
	}

	@Override
	public void render() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(stage == Stage.MENU){
			menuStage.update(Gdx.graphics.getDeltaTime());
		}
		else if(stage == Stage.GAME){
			gameStage.update(Gdx.graphics.getDeltaTime());
		}

		batch.begin();

		if(stage == Stage.MENU){
			menuStage.render(batch);
		}
		else if(stage == Stage.GAME){
			gameStage.render(batch);
		}

		batch.end();
	}



	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.dispose();
	}
}
