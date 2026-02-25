package org.iesalandalus.programacion.cuatroenraya;

import org.iesalandalus.programacion.cuatroenraya.modelo.CuatroEnRaya;
import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class Main {

    public static void main(String[] args) {
        Jugador jugador1 = Consola.leerJugador();
        Ficha color1 = jugador1.colorFichas();
        Ficha color2 = (color1 == Ficha.AZUL) ? Ficha.VERDE : Ficha.AZUL;
        Jugador jugador2 = Consola.leerJugador(color2);

        CuatroEnRaya juego = new CuatroEnRaya(jugador1, jugador2);
        juego.jugar();
    }
}
