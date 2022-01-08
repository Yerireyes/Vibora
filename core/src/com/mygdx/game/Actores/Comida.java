/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Matriz;
import java.util.Random;

/**
 *
 * @author Yeri
 */
public class Comida extends Actor {

    private final TextureRegion comida;
    Random random;
    Matriz matriz;
    int randomX;
    int randomY;
    public boolean comidaBienGenerada;

    public Comida(TextureRegion comida, Matriz matriz) {
        this.matriz = matriz;
        this.comida = comida;
        comidaBienGenerada = true;
        setSize(this.matriz.getTamanoUnidad(), this.matriz.getTamanoUnidad());
        random = new Random();
    }

    public boolean isComidaBienGenerada() {
        return comidaBienGenerada;
    }

    public void setComidaBienGenerada(boolean comidaBienGenerada) {
        this.comidaBienGenerada = comidaBienGenerada;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(comida, getX(), getY(), this.matriz.getTamanoUnidad(), this.matriz.getTamanoUnidad());
    }

    @Override
    public void act(float delta) {
    }

    public void generarComida() {
        randomX = random.nextInt((int) (this.matriz.getPantallaAncho() / this.matriz.getTamanoUnidad())) * this.matriz.getTamanoUnidad();
        randomY = random.nextInt((int) (this.matriz.getPantallaAlto() / this.matriz.getTamanoUnidad())) * this.matriz.getTamanoUnidad();
        setX(randomX);
        setY(randomY);
    }

}
