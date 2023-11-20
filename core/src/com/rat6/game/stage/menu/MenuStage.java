package com.rat6.game.stage.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rat6.game.Assets;
import com.rat6.game.MyGdxGame;
import com.rat6.game.TouchInputProcessor;
import com.rat6.game.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MenuStage {
    private MyGdxGame myGdxGame;
    private Assets assets;
    public List<MenuButton> menuButtons;
    public TouchInputProcessor inputProcessor;

    public MenuStage(MyGdxGame myGdxGame, Assets assets, TouchInputProcessor inputProcessor) {
        this.myGdxGame = myGdxGame;
        this.assets = assets;
        this.inputProcessor = inputProcessor;
        this.menuButtons = new ArrayList<>();
        float startX = 205f;
        menuButtons.add(new MenuButton(assets, "1 Player", startX, 120)
                .setInputProcessor(inputProcessor)
                .onTouch(()->{
                    myGdxGame.setStage(Stage.SINGLE_GAME);
                })
        );
        menuButtons.add(new MenuButton(assets, "2 Player", startX + 192, 120)
                .setInputProcessor(inputProcessor)
                .onTouch(()->{
                    myGdxGame.setStage(Stage.MULTI_GAME);
                })
        );
        menuButtons.add(new MenuButton(assets, "settings", startX + 192, 120 - 92)
                .setInputProcessor(inputProcessor)
                .onTouch(()->{
                    myGdxGame.setStage(Stage.SETTINGS);
                })
        );
        menuButtons.add(new MenuButton(assets, "Online", startX + 192 * 2, 120)
                .setInputProcessor(inputProcessor)
                .onTouch(()->{
//                    myGdxGame.setStage(Stage.GAME);
                })
        );
    }

    public void update(float deltaTime) {
        for (MenuButton button : menuButtons) {
            button.update();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(assets.menu, 0, 0);
        /*assets.font.drawText(batch, "Some text", 0, 0);
        for (Integer pointer : inputProcessor.getActivePointers()) {
            Vector2 touchCoords = inputProcessor.getTouchCoordinates(pointer);
            if (touchCoords != null) {
                assets.font.drawText(batch, "Touch " + pointer + ": " + touchCoords.x + ", " + touchCoords.y, 100, 150 + pointer * 20);
            }
        }*/
        for(MenuButton button: menuButtons){
            button.render(batch);
        }
    }
}