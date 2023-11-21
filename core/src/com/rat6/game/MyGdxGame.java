package com.rat6.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rat6.game.stage.Stage;
import com.rat6.game.stage.game.GameStage;
import com.rat6.game.stage.menu.MenuStage;
import com.rat6.game.stage.multiplayer.MultiplayerStage;
import com.rat6.game.stage.settings.SettingsStage;

/**
 * I know that the Stages principle was originally implemented in LibGDX and does not work that way.
 * The reason I did it this way is because I didn't have much time, so I did it as fast as I could.
 *
 * I wanted to realise online game as well.
 * In the end I did not implement Online.
 * There is uncomplicated way to realise it with Socket.IO
 * (https://www.youtube.com/watch?v=uIPAaDslhPM&list=RDCMUCO9JvZ75Usyzgd1puurLF6A&start_radio=1&rv=uIPAaDslhPM&t=1).
 * I've been thinking about synchronisation, but I haven't figured out how to implement it properly yet.
 * It seems the best solution would be to keep all game state on the server,
 * players would only send controller information. But, there was no time to re-implement the game on the server.
 * */
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
	private SettingsStage settingsStage;
	private MultiplayerStage multiplayerStage;

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
		gameStage = new GameStage(this, assets, inputProcessor);
		menuStage = new MenuStage(this, assets, inputProcessor);
		settingsStage = new SettingsStage(this, assets, inputProcessor);
		multiplayerStage = new MultiplayerStage(this, assets, inputProcessor);

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
		else if(stage == Stage.SINGLE_GAME){
			gameStage.update(Gdx.graphics.getDeltaTime());
		}
		else if(stage == Stage.SETTINGS) {
			settingsStage.update(Gdx.graphics.getDeltaTime());
		}
		else if(stage == Stage.MULTI_GAME) {
			multiplayerStage.update(Gdx.graphics.getDeltaTime());
		}

		batch.begin();

		if(stage == Stage.MENU){
			menuStage.render(batch);
		}
		else if(stage == Stage.SINGLE_GAME){
			gameStage.render(batch);
		}
		else if(stage == Stage.SETTINGS){
			settingsStage.render(batch);
		}
		else if(stage == Stage.MULTI_GAME){
			multiplayerStage.render(batch);
		}

		batch.end();
	}

	public void setStage(Stage stage){
		this.stage = stage;
		gameStage = new GameStage(this, assets, inputProcessor);
		menuStage = new MenuStage(this, assets, inputProcessor);
		settingsStage = new SettingsStage(this, assets, inputProcessor);
		multiplayerStage = new MultiplayerStage(this, assets, inputProcessor);
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
