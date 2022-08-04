package com.lanzabruno.ayp.logica.generadores;

import com.lanzabruno.ayp.logica.casilla.Casilla;
import com.lanzabruno.ayp.logica.formas.Triangulo;

import java.util.ArrayList;

public class GenLineaTriangulo implements GenLinea<Casilla<Triangulo>> {
    @Override
    public ArrayList<Casilla<Triangulo>> generar(int l) {
        int nCasillas, id ;
        Casilla<Triangulo> c;
        switch (l){
            case 1: id = 0;
                    break;
            case 2: id = 7;
                    break;
            case 3: id = 16;
                    break;
            case 4: id = 27;
                    break;
            case 5: id = 38;
                    break;
            case 6: id = 47;
                    break;
            default: id = 0;
        }

        if (l <= 3) nCasillas = 5 + 2*l;
        else nCasillas = 13 - 2*(l-3);

        Casilla<Triangulo> cCabeza = new Casilla<>(l <= 3,  id);
        ArrayList<Casilla<Triangulo>> casillasLinea = new ArrayList<>();
        casillasLinea.add(cCabeza);
        for (int i = 1; i < nCasillas; i++) {
            id++;
            if (l < 4) c = new Casilla<>(((id + (l % 2)) % 2 != 0 &&  l < 4), id);
            else c = new Casilla<>(((id + (l % 2)) % 2 == 0 &&  l >= 4), id);
            casillasLinea.add(c);
            cCabeza.agregarVecino(Triangulo.DER, c);
        }
        return casillasLinea;
    }
}
