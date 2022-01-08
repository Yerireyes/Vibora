/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author Yeri
 */
public class Matriz {
    int pantallaAncho;
    int pantallaAlto;
    int tamanoUnidad;
    int cantidadDeUnidades;
    
    public Matriz(){
        pantallaAlto = Gdx.graphics.getHeight();
        pantallaAncho = Gdx.graphics.getWidth();
        tamanoUnidad = 25;
        cantidadDeUnidades = (pantallaAncho*pantallaAlto)/tamanoUnidad;
    }
    
    public int getPantallaAlto(){
        return this.pantallaAlto;
    }
    
    public int getPantallaAncho(){
        return this.pantallaAlto;
    }
    
    public int getTamanoUnidad(){
        return this.tamanoUnidad;
    }
    
    public int getcantidadDeUnidades(){
        return this.cantidadDeUnidades;
    }
}
