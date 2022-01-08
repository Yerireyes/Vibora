/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Actores.*;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Yeri
 */
public class Objetos {
    public Vibora vibora;
    public Comida comida;
    public Obstaculo obstaculo, obstaculo2, obstaculo3, obstaculo4;
    Matriz matriz;
    private final Texture viboraTextura, viboraCuerpoTextura, comidaTextura, obstaculoTextura;
    private final TextureRegion comidaRegion;
    
    public Objetos(){
        matriz = new Matriz();
        viboraTextura = new Texture("cabeza.png");
        viboraCuerpoTextura = new Texture("cuerpo.png");
        comidaTextura = new Texture("manzana.png");
        obstaculoTextura = new Texture("obstaculo.jpg");
        comidaRegion = new TextureRegion(comidaTextura, 0, 0, 512, 512);
    }
    
    public void addActor(Stage stage){
        this.vibora = new Vibora(viboraTextura, viboraCuerpoTextura, matriz);
        this.comida = new Comida(comidaRegion, matriz);
        this.obstaculo = new Obstaculo(obstaculoTextura, matriz);
        this.obstaculo2 = new Obstaculo(obstaculoTextura, matriz);
        this.obstaculo3 = new Obstaculo(obstaculoTextura, matriz);
        this.obstaculo4 = new Obstaculo(obstaculoTextura, matriz);
        stage.addActor(this.vibora);
        stage.addActor(this.obstaculo);
        stage.addActor(this.obstaculo2);
        stage.addActor(this.obstaculo3);
        stage.addActor(this.obstaculo4);
        stage.addActor(this.comida);
//        stage.addActor(obstaculo);
        obstaculo.setPosition(200, 200);
        obstaculo2.setPosition(200, 225);
        obstaculo3.setPosition(200, 250);
        obstaculo4.setPosition(200, 275);
        vibora.setPosition(200, 50);
        comida.setPosition(450, 250);
    }

    void dispose() {
        viboraTextura.dispose();
        viboraCuerpoTextura.dispose();
        obstaculoTextura.dispose();
        comidaTextura.dispose();
    }
}
