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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Yeri
 */
public class Cola extends Actor{
    Texture cola;
    Matriz matriz;
    public List<Integer> valorX;
    public List<Integer> valorY;
    public Cola(Texture cola, Matriz matriz){
        this.matriz = matriz;
        this.cola = cola;
        setSize(this.matriz.getTamanoUnidad(), this.matriz.getTamanoUnidad());
        valorX = new LinkedList<>();
        valorY = new LinkedList<>();
    }

    @Override
    public void setPosition(float width, float height) {
        valorX.add((int)width);
        valorY.add((int)height);
    }
    
    

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < this.valorX.size(); i++) {
            batch.draw(cola, this.valorX.get(i), this.valorY.get(i), matriz.getTamanoUnidad(), matriz.getTamanoUnidad());
        }
    }
    
    
    public void actualizar(int valorAntiguoX, int valorAntiguoY){
        matriz.mapa[matriz.getposicionCeldaEnY(this.valorY.get(this.valorY.size() - 1))]
                [matriz.getposicionCeldaEnX(this.valorX.get(this.valorX.size() - 1))] = 0;
        for (int i = this.valorX.size() - 1; i > 0; i--) {
            this.valorX.set(i, this.valorX.get(i-1));
            this.valorY.set(i, this.valorY.get(i-1));
        }
        this.valorX.set(0, valorAntiguoX);
        this.valorY.set(0, valorAntiguoY);
        matriz.mapa[matriz.getposicionCeldaEnY(this.valorY.get(0))]
                [matriz.getposicionCeldaEnX(this.valorX.get(0))] = 4;
    }
    
    public void aumentarCola(){
        int posicionXFinal = valorX.get(valorX.size() - 1);
        int posicionYFinal = valorY.get(valorY.size() - 1);
        valorX.add(posicionXFinal);
        valorY.add(posicionYFinal);
        matriz.mapa[matriz.getposicionCeldaEnY(posicionYFinal)]
                [matriz.getposicionCeldaEnX(posicionXFinal)] = 4;
    }
}
