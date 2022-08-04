package com.lanzabruno.ayp.logica.formas;

public enum Triangulo implements Poligono{
    DER, IZQ, ABA;
    public Triangulo opuesto() {
        switch (this) {
            case IZQ: return DER;
            case DER: return IZQ;
            case ABA: return ABA;
            default: return this;
        }
    }
}
