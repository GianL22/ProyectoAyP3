package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Triangulo;

import java.util.ArrayList;

public class GenMoldeHexagono implements GenMolde<Triangulo>{

    private ArrayList<Casilla<Triangulo>> genlinea(int n){
        int id = (n - 1)*3;
        ArrayList<Casilla<Triangulo>> casillas = new ArrayList<>();
        Casilla<Triangulo> casillaCabeza = new Casilla<>(n % 2 != 0, id);
        casillas.add(casillaCabeza);
        Casilla<Triangulo> c;
        for (int i = 1; i < 3; i++){
            c = new Casilla<>((id + i) % 2 == 0, id + i);
            casillaCabeza.agregarVecino(Triangulo.DER, c);
            casillas.add(c);
        }
        return casillas;
    }

    public ArrayList<Casilla<Triangulo>> generar(){
        ArrayList<Casilla<Triangulo>> casillas = new ArrayList<>();
        ArrayList<Casilla<Triangulo>> linea1 = genlinea(1);
        ArrayList<Casilla<Triangulo>> linea2 = genlinea(2);
        casillas.addAll(linea1);
        for (Casilla<Triangulo> c: linea2){
            casillas.add(3,c);
        }
        UnirLineasTriangulo unidor = new UnirLineasTriangulo();
        unidor.unir(linea1,linea2,1);
        return casillas;
    }
}
