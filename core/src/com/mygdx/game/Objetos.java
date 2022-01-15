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

/**
 *
 * @author Yeri
 */
public class Objetos {

    Matriz matriz;
    private final Texture viboraTextura, viboraCuerpoTextura, comidaTextura, obstaculoTextura;
    private final TextureRegion comidaRegion;
    Vibora vibora;
    Comida comida;
    Cola cola;
    byte nivel;
    boolean cambioDeNivel;
    int delayVibora = 75;

    public Objetos(byte nivel) {
        cambioDeNivel = false;
        this.nivel = nivel;
        matriz = new Matriz();
        viboraTextura = new Texture("cabeza.png");
        viboraCuerpoTextura = new Texture("cuerpo.png");
        comidaTextura = new Texture("manzana.png");
        obstaculoTextura = new Texture("obstaculo.jpg");
        comidaRegion = new TextureRegion(comidaTextura, 0, 0, 512, 512);
    }

    public void addActor(Stage stage) {
        System.out.println(matriz.getCantFilas());
        System.out.println(matriz.getCantColumnas());
        for (int i = 0; i < matriz.getCantFilas(); i++) {
            for (int j = 0; j < matriz.getCantColumnas(); j++) {

                switch (matriz.mapa[i][j]) {
                    case 0:
                        break;
                    case 1:
                        Obstaculo obstaculo = new Obstaculo(obstaculoTextura, matriz);
                        stage.addActor(obstaculo);
                        obstaculo.setPosition(matriz.posicionCeldaEnX(j), matriz.posicionCeldaEnY(i));
                        break;
                    case 2:
                        comida = new Comida(comidaRegion, matriz);
                        stage.addActor(comida);
                        comida.setPosition(matriz.posicionCeldaEnX(j), matriz.posicionCeldaEnY(i));
                        break;
                    case 3:
                        vibora = new Vibora(viboraTextura, matriz);
                        stage.addActor(vibora);
                        vibora.setDelay(this.delayVibora);
                        vibora.setPosition(matriz.posicionCeldaEnX(j), matriz.posicionCeldaEnY(i));
                        break;
                    case 4:
                        if (cola == null) {
                            cola = new Cola(viboraCuerpoTextura, matriz);
                            stage.addActor(cola);
                        }

                        cola.setPosition(matriz.posicionCeldaEnX(j), matriz.posicionCeldaEnY(i));
                        break;
                }
            }
        }
    }

    void dispose() {
        viboraTextura.dispose();
        viboraCuerpoTextura.dispose();
        obstaculoTextura.dispose();
        comidaTextura.dispose();
    }

    public void sisguienteNivel() {
        this.nivel++;
        this.cola = null;
        this.vibora = null;
        this.comida = null;
    }

    public void cargarNivel2() {
        this.matriz.cargarNivel2();
        this.delayVibora = 70;
    }

    public void cargarNivel3() {
        this.matriz.cargarNivel3();
        this.delayVibora = 60;
    }

    public byte getNivel() {
        return this.nivel;
    }

    public boolean isCambioDeNivel() {
        return this.cambioDeNivel;
    }

    public void setCambioDeNivel(boolean cambioDeNivel) {
        this.cambioDeNivel = cambioDeNivel;
    }
}
