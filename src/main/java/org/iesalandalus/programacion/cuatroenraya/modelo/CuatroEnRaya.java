package org.iesalandalus.programacion.cuatroenraya.modelo;

import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class CuatroEnRaya {

    private static final int NUMERO_JUGADORES = 2;

    private final Jugador[] jugadores;
    private final Tablero tablero;

    public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
        if (jugador1 == null || jugador2 == null) {
            throw new NullPointerException("Los jugadores no pueden ser nulos.");
        }
        if (jugador1.colorFichas() == jugador2.colorFichas()) {
            throw new IllegalArgumentException("Los jugadores no pueden tener el mismo color de fichas.");
        }
        jugadores = new Jugador[NUMERO_JUGADORES];
        jugadores[0] = jugador1;
        jugadores[1] = jugador2;
        tablero = new Tablero();
    }

    public boolean tirar(Jugador jugador) {
        boolean jugadaGanadora = false;
        boolean jugadaValida = false;
        do {
            int columna = Consola.leerColumna(jugador);
            try {
                jugadaGanadora = tablero.introducirFicha(columna, jugador.colorFichas());
                jugadaValida = true;
            } catch (CuatroEnRayaExcepcion e) {
                System.out.println("Columna llena. Elige otra columna.");
            }
        } while (!jugadaValida);
        return jugadaGanadora;
    }

    public void jugar() {
        boolean ganador = false;
        int turno = 0;

        while (!tablero.estaLleno() && !ganador) {
            System.out.println(tablero);
            Jugador jugadorActual = jugadores[turno];
            ganador = tirar(jugadorActual);
            if (!ganador) {
                turno = (turno + 1) % NUMERO_JUGADORES;
            }
        }

        System.out.println(tablero);
        if (ganador) {
            System.out.printf("¡Ha ganado %s!%n", jugadores[turno].nombre());
        } else {
            System.out.println("No quedan más casillas libres. Empate.");
        }
    }
}