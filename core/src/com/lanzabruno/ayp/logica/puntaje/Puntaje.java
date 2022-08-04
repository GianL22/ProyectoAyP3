package com.lanzabruno.ayp.logica.puntaje;

public class Puntaje {
    private int puntos;

    public Puntaje(){
        this.puntos = 0;
    }

    public void sumarPuntos(int n, Estrategia e){
        this.puntos += e.calcular(n);
    }

    public void aumentarPuntos(int i){
        this.puntos+=i;
    }

    public int getPuntos() {
        return puntos;
    }


}