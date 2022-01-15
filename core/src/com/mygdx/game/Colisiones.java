/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Actores.Cola;
import com.mygdx.game.Actores.Comida;
import com.mygdx.game.Actores.Vibora;
import java.util.Random;

/**
 *
 * @author Yeri
 */
public class Colisiones {

    public static void AllColisions(Objetos objetos, Stage stage, Sound eatSound, Sound dieSound) {
        if (!objetos.vibora.isMovio()) {
            return;
        }
        Colisiones.ColisionPared(objetos.vibora, objetos.matriz);
        Colisiones.Comio(objetos.vibora, objetos.comida, objetos.cola, eatSound, objetos.matriz);
        Colisiones.Cola(objetos.vibora, objetos.cola, objetos.matriz);
        Colisiones.ColisionObstaculos(objetos.vibora, objetos.matriz);
        if (objetos.vibora.isVivo()) {
            objetos.matriz.mapa[objetos.matriz.getposicionCeldaEnY((int) objetos.vibora.getY())]
                    [objetos.matriz.getposicionCeldaEnX((int) objetos.vibora.getX())] = 3;
        }else{
            dieSound.play();
        }
        Colisiones.CambiarNivel(objetos, stage);
    }

    public static void Cola(Vibora vibora, Cola cola, Matriz matriz) {
        vibora.setMovio(false);
        cola.actualizar(matriz.posicionCeldaEnX(vibora.valorAnteriorX), matriz.posicionCeldaEnY(vibora.valorAnteriorY));
    }

    public static void Comio(Vibora vibora, Comida comida, Cola cola, Sound eatSound, Matriz matriz) {
        if (!vibora.isVivo()) {
            return;
        }
        int posicionX = matriz.getposicionCeldaEnX((int) vibora.getX());
        int posicionY = matriz.getposicionCeldaEnY((int) vibora.getY());
        if (matriz.mapa[posicionY][posicionX] == 2) {
            matriz.mapa[posicionY][posicionX] = 0;
            Random random = new Random();
            int randomX = random.nextInt((int) (matriz.getPantallaAncho() / matriz.getTamanoUnidad())) * matriz.getTamanoUnidad();
            int randomY = random.nextInt((int) (matriz.getPantallaAlto() / matriz.getTamanoUnidad())) * matriz.getTamanoUnidad();
            while (matriz.mapa[matriz.getposicionCeldaEnY(randomY)][matriz.getposicionCeldaEnX(randomX)] != 0) {
                randomX = random.nextInt((int) (matriz.getPantallaAncho() / matriz.getTamanoUnidad())) * matriz.getTamanoUnidad();
                randomY = random.nextInt((int) (matriz.getPantallaAlto() / matriz.getTamanoUnidad())) * matriz.getTamanoUnidad();
            }
            eatSound.play();
            cola.aumentarCola();
            vibora.comioUnaManzana();
            comida.setX(randomX);
            comida.setY(randomY);
            matriz.mapa[matriz.getposicionCeldaEnY(randomY)][matriz.getposicionCeldaEnX(randomX)] = 2;
        }
    }

    public static void ColisionObstaculos(Vibora vibora, Matriz matriz) {
        if (!vibora.isVivo()) {
            return;
        }
        int posicionX = matriz.getposicionCeldaEnX((int) vibora.getX());
        int posicionY = matriz.getposicionCeldaEnY((int) vibora.getY());
        if (matriz.mapa[posicionY][posicionX] == 1 ||
                matriz.mapa[posicionY][posicionX] == 4) {
            vibora.setVivo(false);
        }
    }

    public static void ColisionPared(Vibora vibora, Matriz matriz) {
        int posicionX = (int) vibora.getX();
        int posicionY = (int) vibora.getY();
        if (posicionX < 0) {
            vibora.setVivo(false);
        } else if (posicionX >= Gdx.graphics.getWidth()) {
            vibora.setVivo(false);
        } else if (posicionY < 0) {
            vibora.setVivo(false);
        } else if (posicionY >= Gdx.graphics.getHeight()) {
            vibora.setVivo(false);
        } 
        
    }
    
    public static void CambiarNivel(Objetos objetos, Stage stage){
        byte nivel = objetos.getNivel();
        switch(nivel){
            case 1:
                if (objetos.vibora.getManzanasComidas() == 3) {
                    objetos.sisguienteNivel();
                    objetos.cargarNivel2();
                    stage.clear();
                    objetos.addActor(stage);
                    objetos.vibora.setDelay(70);
                }
                break;
            case 2:
                if (objetos.vibora.getManzanasComidas() == 5) {
                    objetos.sisguienteNivel();
                    objetos.cargarNivel3();
                    stage.clear();
                    objetos.addActor(stage);
                    objetos.vibora.setDelay(60);
                }
                break;
        }
    }

}
