package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class MyGdxGame extends Game {

    private AssetManager aManager;
    public MainGamePantalla gameScreen;
    public GameOverScreen gameOverScreen;
    public StartUpMenuScreen startUpMenuScreen;
    public AssetManager getManager() {
        return aManager;
    }

    @Override
    public void create() {
        aManager = new AssetManager();
        aManager.load("gameover.png", Texture.class);
        aManager.load("inicio.png", Texture.class);
        aManager.load("die.ogg", Sound.class);
        aManager.load("eat.ogg", Sound.class);
        aManager.load("start.ogg", Music.class);
        aManager.load("gameOver.ogg", Music.class);
        aManager.finishLoading();
        gameScreen = new MainGamePantalla(this);
        gameOverScreen = new GameOverScreen(this);
        startUpMenuScreen = new StartUpMenuScreen(this);
        setScreen(startUpMenuScreen);
    }
}
