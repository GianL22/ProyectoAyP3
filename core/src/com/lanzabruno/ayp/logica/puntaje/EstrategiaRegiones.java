package com.lanzabruno.ayp.logica.puntaje;

public class EstrategiaRegiones implements Estrategia<Integer>{
    @Override
    public int calcular(Integer n) {
        return n*100;
    }
}
