package com.rat6.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rat6.game.font.Font;

public class Assets {

    public Texture atlas;
    public Texture menu;

    public TextureRegion[]
            // blue tank
                            blueTankRs,
                            blueTankLs,
                            blueTankDs,
                            blueTankUs,
            // blue tank shots
                            blueTankRShots,
                            blueTankLShots,
                            blueTankUShots,
                            blueTankDShots,
            // red tank
                            redTankRs,
                            redTankLs,
                            redTankDs,
                            redTankUs,
            // red tank shots
                            redTankRShots,
                            redTankLShots,
                            redTankUShots,
                            redTankDShots;

    public  TextureRegion   destroyedTankR,
                            destroyedTankL,
                            destroyedTankU,
                            destroyedTankD;

    public  TextureRegion controller, controllerShotButton, pauseButton;

    public  TextureRegion cannonball;
    public  TextureRegion blur;

    public  TextureRegion turf;
    public  TextureRegion[] shabby_turfs;
    public  TextureRegion[] boulders;
    public  TextureRegion[] explosionFrames;
    public  TextureRegion[] depots;
    public  TextureRegion[] headquarters;

    public  TextureRegion scrollBarRed;
    public  TextureRegion scrollBarYellow;
    public  TextureRegion slider;
    public  TextureRegion[] menuButtons;

    public boolean mute = false;
    public void mute_toggle() {
        mute = !mute;
        if (mute) {
            lastVolume = volume;
            setVolume(0);
        } else {
            setVolume(lastVolume);
        }
    }
    public float lastVolume, volume = 1.0f; // Начальная громкость (1.0 - максимальная громкость)
    public Music kalinkaMusic;
    public Sound    explosionSound,
                    tank_shotSound,
                    boulderSound,
                    bulletsCollision,
                    clickSound;

    public Font font;
    public Assets(){
        load();
    }

    public void load(){
        atlas = new Texture("atlas++.png");
        menu = new Texture("menu.png");

        loadBlueTank();
        loadRedTank();
        loadDestroyedTank();

        loadEnvironment();

        controller              = new TextureRegion(atlas, 640, 0,   96, 96);
        controllerShotButton    = new TextureRegion(atlas, 640, 96,  32, 32);
        pauseButton = new TextureRegion(atlas, 672, 96,  32, 32);

        cannonball              = new TextureRegion(atlas, 640, 128, 16, 16);
        blur              = new TextureRegion(atlas, 640, 144, 64, 64);

        slider          = new TextureRegion(atlas, 0,  1440, 64, 64);
        scrollBarRed    = new TextureRegion(atlas, 0,  1504, 192, 64);
        scrollBarYellow = new TextureRegion(atlas, 0,  1568, 192 , 64);

        menuButtons = new TextureRegion[]{
                new TextureRegion(atlas, 192,  1376 + 96, 192 , 96),
                new TextureRegion(atlas, 192,  1376,192 , 96),
        };

        kalinkaMusic = Gdx.audio.newMusic(Gdx.files.internal("kalinka.mp3"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
        tank_shotSound = Gdx.audio.newSound(Gdx.files.internal("tank_shot.mp3"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        boulderSound = Gdx.audio.newSound(Gdx.files.internal("boulder.mp3"));
        bulletsCollision = Gdx.audio.newSound(Gdx.files.internal("bulletsCollision.mp3"));

        playMusic(kalinkaMusic);

        font = new Font(atlas, 736, 0, 16, 16, 20);
    }

    public void loadEnvironment(){
        turf = new TextureRegion(atlas, 0, 832, 64, 64);

        shabby_turfs = new TextureRegion[7];
        for(int i=0; i<7; i++){
            shabby_turfs[i]     = new TextureRegion(atlas, 0, 832 + 64 * i,  64, 64);
        }

        boulders = new TextureRegion[]{
                new TextureRegion(atlas, 64, 832 + 64 * 0,  64, 64),
                new TextureRegion(atlas, 64, 832 + 64 * 1,  64, 64)
        };

        explosionFrames = new TextureRegion[]{
                new TextureRegion(atlas, 64, 832 + 64 * 2,  64, 64),
                new TextureRegion(atlas, 64, 832 + 64 * 3,  64, 64),
                new TextureRegion(atlas, 64, 832 + 64 * 6,  64, 64),
        };

        depots = new TextureRegion[]{
                new TextureRegion(atlas, 64, 832 + 64 * 4,  64, 64),
                new TextureRegion(atlas, 64, 832 + 64 * 5,  64, 64),
        };

        headquarters = new TextureRegion[]{
                new TextureRegion(atlas, 128, 832, 64, 96),
                new TextureRegion(atlas, 128, 832 + 96, 64, 96),
        };

    }
    public void playMusic(Music music) {
        if (music != null) {
            music.setLooping(true);
            music.setVolume(volume/2f);
            music.play();
        }
    }
    public void playSound(Sound sound){
        if(sound != null) {
            sound.play(volume);
        }
    }

    public void setVolume(float volume){
        this.volume = volume;
        kalinkaMusic.setVolume(volume);
    }

    private void loadDestroyedTank(){
        destroyedTankR = new TextureRegion(atlas, 0,  1280, 96, 64);
        destroyedTankL = new TextureRegion(atlas, 96, 1280, 96, 64);
        destroyedTankU = new TextureRegion(atlas, 64, 1344, 64, 96);
        destroyedTankD = new TextureRegion(atlas, 0,  1344, 64, 96);
    }

    private  void loadRedTank(){
        redTankRs = new TextureRegion[4];
        redTankLs = new TextureRegion[4];
        redTankDs = new TextureRegion[4];
        redTankUs = new TextureRegion[4];
        for(int i=0; i<4; i++){
            redTankRs[i] = new TextureRegion(atlas, 320, 512 + 64 * i, 96, 64);
            redTankLs[i] = new TextureRegion(atlas, 416, 512 + 64 * i, 96, 64);
            redTankDs[i] = new TextureRegion(atlas, 512, 768 + 96 * i, 64, 96);
            redTankUs[i] = new TextureRegion(atlas, 576, 768 + 96 * i, 64, 96);
        }

        redTankRShots = new TextureRegion[8];
        redTankLShots = new TextureRegion[8];
        redTankDShots = new TextureRegion[8];
        redTankUShots = new TextureRegion[8];
        for(int i=0; i<8; i++){
            redTankRShots[i] = new TextureRegion(atlas, 320, 64 * i, 96, 64);
            redTankLShots[i] = new TextureRegion(atlas, 416, 64 * i, 96, 64);
            redTankDShots[i] = new TextureRegion(atlas, 516, 96 * i, 64, 96);
            redTankUShots[i] = new TextureRegion(atlas, 576, 96 * i, 64, 96);
        }
    }
    private  void loadBlueTank(){
        blueTankRs = new TextureRegion[4];
        blueTankLs = new TextureRegion[4];
        blueTankDs = new TextureRegion[4];
        blueTankUs = new TextureRegion[4];
        for(int i=0; i<4; i++){
            blueTankRs[i] = new TextureRegion(atlas, 0,   512 + 64 * i, 64, 64);
            blueTankLs[i] = new TextureRegion(atlas, 128, 512 + 64 * i, 64, 64);
            blueTankDs[i] = new TextureRegion(atlas, 192, 768 + 96 * i, 64, 96);
            blueTankUs[i] = new TextureRegion(atlas, 256, 768 + 96 * i, 64, 96);
        }

        blueTankRShots = new TextureRegion[8];
        blueTankLShots = new TextureRegion[8];
        blueTankDShots = new TextureRegion[8];
        blueTankUShots = new TextureRegion[8];
        for(int i=0; i<8; i++) {
            blueTankRShots[i] = new TextureRegion(atlas, 0,   64 * i, 96, 64);
            blueTankLShots[i] = new TextureRegion(atlas, 96,  64 * i, 96, 64);
            blueTankDShots[i] = new TextureRegion(atlas, 196, 96 * i, 64, 96);
            blueTankUShots[i] = new TextureRegion(atlas, 256, 96 * i, 64, 96);
        }
    }

    public void dispose(){
        atlas.dispose();
        kalinkaMusic.dispose();
    }
}
