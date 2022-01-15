/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Matriz;

/**
 *
 * @author Yeri
 */
public class Vibora extends Actor implements InputProcessor{

    Texture vibora;
    Texture viboraCuerpo;
    Matriz matriz;
    public int valorAnteriorX, valorAnteriorY;
    boolean vivo, movio;
    int manzanasComidas;
    int delay = 75;
    char direccion = 'D';
    long tiempoAnterior;
    long tiempoActual;

    public Vibora(Texture vibora, Matriz matriz) {
        this.matriz = matriz;
        this.vibora = vibora;
        this.vivo = true;
        this.movio = false;
        setSize(this.matriz.getTamanoUnidad(), this.matriz.getTamanoUnidad());
        tiempoAnterior = System.currentTimeMillis();
        tiempoActual = System.currentTimeMillis();
        manzanasComidas = 0;
        Gdx.input.setInputProcessor(this);
    }
    
    public void setDelay(int delay){
        this.delay = delay;
    }
    public int getManzanasComidas(){
        return this.manzanasComidas;
    }
    public void comioUnaManzana(){
        this.manzanasComidas++;
    }
    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean isMovio(){
        return movio;
    }
    
    public void setMovio(boolean movio){
        this.movio = movio;
    }
    @Override
    public void act(float delta) {
        tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoAnterior >= delay && this.isVivo()) {
            this.movio = true;
            this.valorAnteriorX = matriz.getposicionCeldaEnX((int) getX());
            this.valorAnteriorY = matriz.getposicionCeldaEnY((int) getY());
            matriz.mapa[this.valorAnteriorY][this.valorAnteriorX] = 4;
            switch (direccion) {
                case 'U':
                    setY(getY() + matriz.getTamanoUnidad());
                    break;
                case 'D':
                    setY(getY() - matriz.getTamanoUnidad());
                    break;
                case 'L':
                    setX(getX() - matriz.getTamanoUnidad());
                    break;
                case 'R':
                    setX(getX() + matriz.getTamanoUnidad());
                    break;
            }
            tiempoAnterior = tiempoActual;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (this.isVivo()) {

            batch.draw(vibora, getX(), getY(), this.matriz.getTamanoUnidad(), this.matriz.getTamanoUnidad());
        }

    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                if (direccion != 'D') {
                    direccion = 'U';
                }   break;
            case Input.Keys.DOWN:
                if (direccion != 'U') {
                    direccion = 'D';
                }   break;
            case Input.Keys.LEFT:
                if (direccion != 'R') {
                    direccion = 'L';
                }   break;
            case Input.Keys.RIGHT:
                if (direccion != 'L') {
                    direccion = 'R';
                }   break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
       return false;
    }

}
