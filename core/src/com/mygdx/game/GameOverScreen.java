/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 *
 * @author Yeri
 */
public class GameOverScreen extends PantallaBase{
    private final Stage stage;
    private final Skin skin;
    private final Image gameOver;
    private final TextButton retry;
    private final Music gameOverMusic;
    
    public GameOverScreen(final MyGdxGame game) {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        gameOver = new Image(game.getManager().get("gameover.png", Texture.class));
        gameOverMusic = game.getManager().get("gameOver.ogg");
        retry = new TextButton("Retry", skin);
        
        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.gameScreen.nivel = 1;
                game.gameScreen.vida = 3;
                game.setScreen(game.gameScreen);
            }
        });
        gameOver.setPosition(320 - gameOver.getWidth()/2, 250 - gameOver.getHeight()/2);
        retry.setSize(200, 100);
        retry.setPosition(220, 50);
        stage.addActor(retry);
        stage.addActor(gameOver);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        gameOverMusic.setVolume(0.3f);
        gameOverMusic.play();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        gameOverMusic.stop();
    }

    
    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    
    
    
}
