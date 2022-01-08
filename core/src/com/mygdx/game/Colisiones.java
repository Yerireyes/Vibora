/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Actores.Comida;
import com.mygdx.game.Actores.Obstaculo;
import com.mygdx.game.Actores.Vibora;

/**
 *
 * @author Yeri
 */
public class Colisiones {

    public static void AllColisions(Objetos objetos) {
        Colisiones.Comio(objetos.vibora, objetos.comida);
        Colisiones.ColisionCuerpo(objetos.vibora);
        Colisiones.ColisionObstaculo(objetos.vibora, objetos.obstaculo);
        Colisiones.ColisionObstaculo(objetos.vibora, objetos.obstaculo2);
        Colisiones.ColisionObstaculo(objetos.vibora, objetos.obstaculo3);
        Colisiones.ColisionObstaculo(objetos.vibora, objetos.obstaculo4);
        Colisiones.ColisionComidaObjeto(objetos.obstaculo, objetos.comida);
        Colisiones.ColisionComidaObjeto(objetos.obstaculo2, objetos.comida);
        Colisiones.ColisionComidaObjeto(objetos.obstaculo3, objetos.comida);
        Colisiones.ColisionComidaObjeto(objetos.obstaculo4, objetos.comida);
        Colisiones.ColisionPared(objetos.vibora);
        Colisiones.ColisionComidaVibora(objetos.vibora, objetos.comida);
    }

    public static void Comio(Vibora vibora, Comida comida) {
        if (vibora.isVivo()
                && (vibora.getX() + vibora.getWidth() > comida.getX() && vibora.getX() < comida.getX() + comida.getWidth()
                && vibora.getY() + vibora.getHeight() > comida.getY() && vibora.getY() < comida.getY() + comida.getHeight())) {
            comida.generarComida();
            vibora.setBodyParts(vibora.getBodyParts() + 1);
        }
    }

    public static void ColisionCuerpo(Vibora vibora) {
        if (vibora.isVivo()) {
            for (int i = 1; i < vibora.getBodyParts(); i++) {
                if (vibora.getCabezaX() == vibora.getCuerpoX(i) && vibora.getCabezaY() == vibora.getCuerpoY(i)) {
                    vibora.setVivo(false);
                }
            }
        }
    }

    public static void ColisionObstaculo(Vibora vibora, Obstaculo obstaculo) {
        if (vibora.isVivo()
                && (vibora.getX() + vibora.getWidth() > obstaculo.getX() && vibora.getX() < obstaculo.getX() + obstaculo.getWidth()
                && vibora.getY() + vibora.getHeight() > obstaculo.getY() && vibora.getY() < obstaculo.getY() + obstaculo.getHeight())) {
            vibora.setVivo(false);
        }
    }

    public static void ColisionPared(Vibora vibora) {
        if (vibora.getCabezaX() < 0) {
            vibora.setVivo(false);
        } else if (vibora.getCabezaX() >= Gdx.graphics.getWidth()) {
            vibora.setVivo(false);
        } else if (vibora.getCabezaY() < 0) {
            vibora.setVivo(false);
        } else if (vibora.getCabezaY() >= Gdx.graphics.getHeight()) {
            vibora.setVivo(false);
        }
    }

    public static void ColisionComidaVibora(Vibora vibora, Comida comida) {
        for (int i = 0; i < vibora.getBodyParts(); i++) {
            if (vibora.getCuerpoX(i) + vibora.getWidth() > comida.getX() && vibora.getCuerpoX(i) < comida.getX() + comida.getWidth()
                    && vibora.getCuerpoY(i) + vibora.getHeight() > comida.getY() && vibora.getCuerpoY(i) < comida.getY() + comida.getHeight()) {
                comida.setComidaBienGenerada(false);
            }
        }
        while (!comida.isComidaBienGenerada()) {
            comida.generarComida();
            for (int i = 0; i < vibora.getBodyParts(); i++) {
                if (!(vibora.getCuerpoX(i) + vibora.getWidth() > comida.getX() && vibora.getCuerpoX(i) < comida.getX() + comida.getWidth()
                        && vibora.getCuerpoY(i) + vibora.getHeight() > comida.getY() && vibora.getCuerpoY(i) < comida.getY() + comida.getHeight())) {
                    comida.setComidaBienGenerada(true);
                }
            }
        }
    }

    public static void ColisionComidaObjeto(Obstaculo obstaculo, Comida comida) {
        if ((obstaculo.getX() + obstaculo.getWidth() > comida.getX() && obstaculo.getX() < comida.getX() + comida.getWidth()
                && obstaculo.getY() + obstaculo.getHeight() > comida.getY() && obstaculo.getY() < comida.getY() + comida.getHeight())) {
            comida.setComidaBienGenerada(false);
        }
        while (!comida.isComidaBienGenerada()) {
            if (!(obstaculo.getX() + obstaculo.getWidth() > comida.getX() && obstaculo.getX() < comida.getX() + comida.getWidth()
                    && obstaculo.getY() + obstaculo.getHeight() > comida.getY() && obstaculo.getY() < comida.getY() + comida.getHeight())) {
                comida.setComidaBienGenerada(true);
            }
        }
    }

}
