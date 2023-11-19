package com.rat6.game.levels;

public class Level {
    /*public static final float WORLD_WIDTH = 960;
    public static final float WORLD_HEIGHT = 640;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    public static Assets assets;
    private Tank playerTank;
    private TankController tankController;
    public static BulletsHandler bulletsHandler;

    private GameMap grassMap;
    private BoulderWall boulder;

    private Enemy enemy;


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
                .blueTank(assets)
                .setPosition(100, 100) // Установите начальную позицию танка
                .setSpeed(200) // Установите скорость танка
                .build();
        tankController = new TankController(playerTank);

        enemy = new Enemy(assets);

        grassMap = new GrassMap(assets);
        boulder = new BoulderWall(assets, 0, 0);
    }

    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tankController.update(); // слушатель кнопок, вызывает методы передвижения танка

        enemy.update();
        bulletsHandler.update();
        boulder.update();

        batch.begin();
        grassMap.render(batch);
        playerTank.render(batch);
        enemy.render(batch);
        boulder.render(batch);
        bulletsHandler.render(batch);
        batch.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
    }

    
    public void dispose() {
        batch.dispose();
        assets.dispose();
    }*/
}
