/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Matriz;

/**
 *
 * @author Yeri
 */
public class Vibora extends Actor {

    Texture vibora;
    Texture viboraCuerpo;
    Matriz matriz;
    boolean vivo;
    int x[];
    int y[];
    int partesDelCuerpo = 6;
    int manzanasComidas;
    static final int DELAY = 75;
    char direccion = 'R';
    long tiempoAnterior;
    long tiempoActual;

    public Vibora(Texture vibora, Texture viboraCuerpo, Matriz matriz) {
        this.matriz = matriz;
        this.vibora = vibora;
        this.viboraCuerpo = viboraCuerpo;
        this.vivo = true;
        setSize(this.matriz.getTamanoUnidad(), this.matriz.getTamanoUnidad());
        this.x = new int[this.matriz.getcantidadDeUnidades()];
        this.y = new int[this.matriz.getcantidadDeUnidades()];
        tiempoAnterior = System.currentTimeMillis();
        tiempoActual = System.currentTimeMillis();
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getBodyParts() {
        return partesDelCuerpo;
    }

    public void setBodyParts(int partesDelCuerpo) {
        this.partesDelCuerpo = partesDelCuerpo;
    }

    public int getCabezaX() {
        return x[0];
    }

    public int getCabezaY() {
        return y[0];
    }

    public int getCuerpoX(int posicion) {
        return x[posicion];
    }

    public int getCuerpoY(int posicion) {
        return y[posicion];
    }

    @Override
    public void setPosition(float x, float y) {
        this.x[0] = (int) x;
        this.y[0] = (int) y;
        for (int i = 1; i < this.getBodyParts(); i++) {
            this.x[i] = this.x[i - 1] - matriz.getTamanoUnidad();
            this.y[i] = this.y[i - 1];
        }
    }

    @Override
    public void act(float delta) {

        tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoAnterior >= DELAY && this.isVivo()) {
            for (int i = partesDelCuerpo; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            switch (direccion) {
                case 'U':
                    y[0] = y[0] + matriz.getTamanoUnidad();
                    break;
                case 'D':
                    y[0] = y[0] - matriz.getTamanoUnidad();
                    break;
                case 'L':
                    x[0] = x[0] - matriz.getTamanoUnidad();
                    break;
                case 'R':
                    x[0] = x[0] + matriz.getTamanoUnidad();
                    break;
            }
            tiempoAnterior = tiempoActual;
            setX(x[0]);
            setY(y[0]);
        }
        moverVibora();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < partesDelCuerpo; i++) {
            if (i == 0) {
                batch.draw(vibora, x[i], y[i], matriz.getTamanoUnidad(), matriz.getTamanoUnidad());
            } else {
                batch.draw(viboraCuerpo, x[i], y[i], matriz.getTamanoUnidad(), matriz.getTamanoUnidad());
            }
        }
    }

    public void moverVibora() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (direccion != 'D') {
                direccion = 'U';
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (direccion != 'U') {
                direccion = 'D';
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (direccion != 'R') {
                direccion = 'L';
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (direccion != 'L') {
                direccion = 'R';
            }
        }
    }

}
