/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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
public class StartUpMenuScreen extends PantallaBase{
    private final Stage stage;
    private final Skin skin;
    private final Image snake;
    private final TextButton start;
    private final Music musicStart;
    
    public StartUpMenuScreen(final MyGdxGame game) {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        snake = new Image(game.getManager().get("inicio.png", Texture.class));
        start = new TextButton("Start", skin);
        musicStart = game.getManager().get("start.ogg");
        start.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.gameScreen.nivel = 1;
                game.gameScreen.vida = 3;
                game.setScreen(game.gameScreen);
            }
        });
        snake.setSize(200, 100);
        snake.setPosition(320 - snake.getWidth()/2, 250 - snake.getHeight()/2);
        start.setSize(200, 100);
        start.setPosition(220, 50);
        stage.addActor(start);
        stage.addActor(snake);
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        musicStart.setVolume(0.2f);
        musicStart.play();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        musicStart.stop();
    }

    
    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
