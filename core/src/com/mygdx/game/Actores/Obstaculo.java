/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Matriz;

/**
 *
 * @author Yeri
 */
public class Obstaculo extends Actor{
    Texture obstaculo;
    Matriz matriz;
    
    public Obstaculo(Texture obstaculo, Matriz matriz){
        this.obstaculo = obstaculo;
        this.matriz = matriz;
        setSize(this.matriz.getTamanoUnidad(), this.matriz.getTamanoUnidad());
        
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(obstaculo, getX(), getY(), this.matriz.getTamanoUnidad(), this.matriz.getTamanoUnidad());
    }

    @Override
    public void act(float delta) {
        
    }
       
}
