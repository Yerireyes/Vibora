/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;

/**
 *
 * @author Yeri
 */
public class MainGamePantalla extends PantallaBase {

    Objetos objetos;
    private Stage stage;
    int vida;
    public byte nivel;
    boolean runing = false;
    Timer timer;
    Sound eatSound, dieSound;

    public MainGamePantalla(MyGdxGame game) {
        super(game);
        eatSound = game.getManager().get("eat.ogg");
        dieSound = game.getManager().get("die.ogg");
        vida = 3;
        nivel = 1;
    }

    @Override
    public void show() {
        stage = new Stage();
        objetos = new Objetos(nivel);
        if (objetos.getNivel() == 2) {
            objetos.cargarNivel2();
        }else if(objetos.getNivel() == 3){
            objetos.cargarNivel3();
        }
        objetos.addActor(stage);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        Colisiones.AllColisions(objetos, stage, eatSound, dieSound);
        stage.draw();
        if (!objetos.vibora.isVivo()) {
            vida--;
            if (vida > 0) {
                objetos.vibora.setVivo(true);
                nivel = objetos.getNivel();
                game.setScreen(game.gameScreen);
            }else{
                stage.addAction(
                    Actions.sequence(
                            Actions.delay(0.5f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(game.gameOverScreen);
                        }
                    })));
            }   
        }
    }

    @Override
    public void dispose() {
        objetos.dispose();
    }
}
