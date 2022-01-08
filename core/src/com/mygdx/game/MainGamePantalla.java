/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;


/**
 *
 * @author Yeri
 */
public class MainGamePantalla extends PantallaBase{
    Objetos objetos;
    private Stage stage;
    
    boolean runing = false;
    Timer timer;
    
    public MainGamePantalla(MyGdxGame game) { 
        super(game);
        objetos = new Objetos();
    }

    @Override
    public void show() {
        stage = new Stage();
        stage.setDebugAll(true);
        objetos.addActor(stage);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        Colisiones.AllColisions(objetos);
        stage.draw();
    }

    @Override
    public void dispose() {
        objetos.dispose();
    }
}
